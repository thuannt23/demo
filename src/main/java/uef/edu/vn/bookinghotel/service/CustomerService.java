package uef.edu.vn.bookinghotel.service;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uef.edu.vn.bookinghotel.model.Customer;
import uef.edu.vn.bookinghotel.model.User;

@Service
public class CustomerService extends BaseService {

    private final UserService userService;

    public CustomerService(JdbcTemplate jdbc, UserService userService) {
        super(jdbc);
        this.userService = userService;
    }

    public List<Customer> customers() {
        return jdbc.query("SELECT * FROM customers ORDER BY id", customerMapper);
    }

    public Customer customer(int id) {
        return first(jdbc.query("SELECT * FROM customers WHERE id = ?", customerMapper, id));
    }

    public Customer defaultCustomer() {
        return first(jdbc.query("SELECT * FROM customers ORDER BY id LIMIT 1", customerMapper));
    }

    public Customer customerOf(User user) {
        if (user == null) {
            return null;
        }
        return first(jdbc.query(
                "SELECT * FROM customers WHERE user_id = ?",
                customerMapper,
                user.getId()));
    }

    public void saveCustomer(Customer customer) {
        if (customer.getId() == 0) {
            jdbc.update(
                    "INSERT INTO customers (user_id, full_name, email, phone, identity_number, address) VALUES (?, ?, ?, ?, ?, ?)",
                    customer.getUserId(),
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getIdentityNumber(),
                    customer.getAddress());
            return;
        }

        jdbc.update(
                "UPDATE customers SET user_id=?, full_name=?, email=?, phone=?, identity_number=?, address=? WHERE id=?",
                customer.getUserId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getIdentityNumber(),
                customer.getAddress(),
                customer.getId());
    }

    public void deleteCustomer(int id) {
        jdbc.update(
                "DELETE FROM customers WHERE id = ? AND NOT EXISTS (SELECT 1 FROM bookings WHERE customer_id = ? AND status <> 'CANCELLED')",
                id,
                id);
    }

    public void updateProfile(User user, Customer customer) {
        userService.updateProfile(user, customer);
        saveCustomer(customer);
    }
}
