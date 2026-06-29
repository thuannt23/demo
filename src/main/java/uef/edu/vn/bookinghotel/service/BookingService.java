package uef.edu.vn.bookinghotel.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import uef.edu.vn.bookinghotel.model.Booking;
import uef.edu.vn.bookinghotel.model.Room;

@Service
public class BookingService extends BaseService {

    private final InvoiceService invoiceService;
    private final RoomService roomService;

    public BookingService(JdbcTemplate jdbc, InvoiceService invoiceService, RoomService roomService) {
        super(jdbc);
        this.invoiceService = invoiceService;
        this.roomService = roomService;
    }

    public Booking book(int customerId, int roomId, LocalDate in, LocalDate out) {
        Room room = roomService.room(roomId);
        if (room == null || !canBook(roomId, in, out)) {
            return null;
        }

        BigDecimal total = totalAmount(room, in, out);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO bookings (customer_id, room_id, check_in_date, check_out_date, status, total_amount) "
                    + "VALUES (?, ?, ?, ?, 'BOOKED', ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customerId);
            statement.setInt(2, roomId);
            statement.setDate(3, Date.valueOf(in));
            statement.setDate(4, Date.valueOf(out));
            statement.setBigDecimal(5, total);
            return statement;
        }, keyHolder);

        Number bookingId = keyHolder.getKey();
        if (bookingId == null) {
            return null;
        }
        jdbc.update("UPDATE rooms SET status = 'BOOKED' WHERE id = ?", roomId);
        return booking(bookingId.intValue());
    }

    public boolean canBook(int roomId, LocalDate in, LocalDate out) {
        Room room = roomService.room(roomId);
        return room != null
                && "AVAILABLE".equals(room.getStatus())
                && !hasOverlap(roomId, in, out);
    }

    public BigDecimal quoteTotal(int roomId, LocalDate in, LocalDate out) {
        Room room = roomService.room(roomId);
        return room == null ? BigDecimal.ZERO : totalAmount(room, in, out);
    }

    public Booking booking(int id) {
        return first(jdbc.query(bookingSql() + " WHERE b.id = ?", bookingMapper, id));
    }

    public List<Booking> bookings() {
        return jdbc.query(bookingSql() + " ORDER BY b.id DESC", bookingMapper);
    }

    public List<Booking> bookingsOf(int customerId) {
        return jdbc.query(
                bookingSql() + " WHERE b.customer_id = ? ORDER BY b.id DESC",
                bookingMapper,
                customerId);
    }

    public boolean customerOwnsBooking(int customerId, int bookingId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM bookings WHERE id = ? AND customer_id = ?",
                Integer.class,
                bookingId,
                customerId);
        return count != null && count > 0;
    }

    public void updateBooking(int id, LocalDate in, LocalDate out) {
        Booking booking = booking(id);
        if (booking == null) {
            return;
        }

        Room room = roomService.room(booking.getRoomId());
        if (room == null) {
            return;
        }

        long nights = Math.max(1, ChronoUnit.DAYS.between(in, out));
        jdbc.update(
                "UPDATE bookings SET check_in_date=?, check_out_date=?, total_amount=? WHERE id=?",
                Date.valueOf(in),
                Date.valueOf(out),
                room.getPricePerNight().multiply(BigDecimal.valueOf(nights)),
                id);
    }

    public void confirmBooking(int id) {
        jdbc.update("UPDATE bookings SET status = 'BOOKED' WHERE id = ? AND status = 'BOOKED'", id);
    }

    public void checkIn(int id) {
        changeStatus(id, "BOOKED", "CHECK_IN", "CHECK_IN");
    }

    public void checkOut(int id) {
        Booking booking = changeStatus(id, "CHECK_IN", "CHECK_OUT", "AVAILABLE");
        if (booking == null) {
            return;
        }
        invoiceService.createInvoice(booking, roomService.room(booking.getRoomId()));
    }

    public void cancel(int id) {
        changeStatus(id, "BOOKED", "CANCELLED", "AVAILABLE");
    }

    private Booking changeStatus(
            int id,
            String expectedBookingStatus,
            String bookingStatus,
            String roomStatus) {
        Booking booking = booking(id);
        if (booking == null || !expectedBookingStatus.equals(booking.getStatus())) {
            return null;
        }

        jdbc.update("UPDATE bookings SET status = ? WHERE id = ?", bookingStatus, id);
        jdbc.update("UPDATE rooms SET status = ? WHERE id = ?", roomStatus, booking.getRoomId());
        return booking;
    }

    private boolean hasOverlap(int roomId, LocalDate in, LocalDate out) {
        Integer overlaps = jdbc.queryForObject(
                "SELECT COUNT(*) FROM bookings WHERE room_id = ? AND status IN ('BOOKED','CHECK_IN') "
                + "AND check_in_date < ? AND check_out_date > ?",
                Integer.class,
                roomId,
                Date.valueOf(out),
                Date.valueOf(in));
        return overlaps != null && overlaps > 0;
    }

    private BigDecimal totalAmount(Room room, LocalDate in, LocalDate out) {
        long nights = Math.max(1, ChronoUnit.DAYS.between(in, out));
        return room.getPricePerNight().multiply(BigDecimal.valueOf(nights));
    }

    private String bookingSql() {
        return "SELECT b.*, c.full_name AS customer_name, r.room_number, rt.name AS room_type "
                + "FROM bookings b "
                + "JOIN customers c ON c.id = b.customer_id "
                + "JOIN rooms r ON r.id = b.room_id "
                + "JOIN room_types rt ON rt.id = r.room_type_id";
    }
}
