package uef.edu.vn.bookinghotel.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uef.edu.vn.bookinghotel.model.Room;

@Service
public class RoomService extends BaseService {

    public RoomService(JdbcTemplate jdbc) {
        super(jdbc);
    }

    public List<Room> rooms(String keyword, Integer typeId, String status) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT r.*, rt.name AS type_name, rt.price_per_night "
                + "FROM rooms r JOIN room_types rt ON rt.id = r.room_type_id WHERE 1=1");

        if (!blank(keyword)) {
            sql.append(" AND r.room_number LIKE ?");
            params.add("%" + keyword + "%");
        }
        if (typeId != null) {
            sql.append(" AND r.room_type_id = ?");
            params.add(typeId);
        }
        if (!blank(status)) {
            sql.append(" AND r.status = ?");
            params.add(status);
        }

        sql.append(" ORDER BY r.id");
        return jdbc.query(sql.toString(), roomMapper, params.toArray());
    }

    public Room room(int id) {
        return first(jdbc.query(
                "SELECT r.*, rt.name AS type_name, rt.price_per_night "
                + "FROM rooms r JOIN room_types rt ON rt.id = r.room_type_id WHERE r.id = ?",
                roomMapper,
                id));
    }

    public List<Room> availableRooms(LocalDate in, LocalDate out, Integer typeId) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT r.*, rt.name AS type_name, rt.price_per_night "
                + "FROM rooms r JOIN room_types rt ON rt.id = r.room_type_id "
                + "WHERE r.status = 'AVAILABLE'");

        if (typeId != null) {
            sql.append(" AND r.room_type_id = ?");
            params.add(typeId);
        }

        sql.append(" AND NOT EXISTS (SELECT 1 FROM bookings b "
                + "WHERE b.room_id = r.id AND b.status IN ('BOOKED','CHECK_IN') "
                + "AND b.check_in_date < ? AND b.check_out_date > ?) ORDER BY r.id");
        params.add(Date.valueOf(out));
        params.add(Date.valueOf(in));
        return jdbc.query(sql.toString(), roomMapper, params.toArray());
    }

    public void saveRoom(Room room) {
        String status = normalizeRoomStatus(room.getStatus());
        String image = blank(room.getImageUrl()) ? "/assets/img/rooms/1.png" : room.getImageUrl();

        if (room.getId() == 0) {
            jdbc.update(
                    "INSERT INTO rooms (room_type_id, room_number, status, image_url, description) VALUES (?, ?, ?, ?, ?)",
                    room.getRoomTypeId(),
                    room.getRoomNumber(),
                    status,
                    image,
                    room.getDescription());
            return;
        }

        jdbc.update(
                "UPDATE rooms SET room_type_id=?, room_number=?, status=?, image_url=?, description=? WHERE id=?",
                room.getRoomTypeId(),
                room.getRoomNumber(),
                status,
                image,
                room.getDescription(),
                room.getId());
    }

    public void deleteRoom(int id) {
        jdbc.update(
                "DELETE FROM rooms WHERE id = ? AND NOT EXISTS (SELECT 1 FROM bookings WHERE room_id = ? AND status <> 'CANCELLED')",
                id,
                id);
    }

    public void updateRoomStatus(int id, String status) {
        jdbc.update("UPDATE rooms SET status = ? WHERE id = ?", normalizeRoomStatus(status), id);
    }

    private String normalizeRoomStatus(String status) {
        if ("BOOKED".equals(status) || "CHECK_IN".equals(status) || "CHECK_OUT".equals(status)) {
            return status;
        }
        return "AVAILABLE";
    }
}
