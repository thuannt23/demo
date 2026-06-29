package uef.edu.vn.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.Household;

@Repository
public class HouseholdRepository {
@Autowired
    private JdbcTemplate jdbcTemplate;
    private Household mapRow(ResultSet rs, int rowNum) throws SQLException {
        Household household = new Household();
        household.setHouseholdID(rs.getInt("HouseholdID"));
        household.setApartmentID(rs.getInt("ApartmentID"));
        household.setHeadOfHousehold(rs.getString("HeadOfHousehold"));
        household.setContactNumber(rs.getString("ContactNumber"));
        household.setEmail(rs.getString("Email"));
        return household;
    }
    public List<Household> findAll() {
        String sql = "SELECT * FROM household";
        return jdbcTemplate.query(sql, this::mapRow);
    }
    public Household findById(int id) {
        String sql = "SELECT * FROM household WHERE HouseholdID = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, id);
    }
    public void save(Household household) {
        String sql = "INSERT INTO household (ApartmentID, HeadOfHousehold, ContactNumber, Email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                household.getApartmentID(),
                household.getHeadOfHousehold(),
                household.getContactNumber(),
                household.getEmail());
    }
    public void update(Household household) {
        String sql = "UPDATE household SET ApartmentID = ?, HeadOfHousehold = ?, ContactNumber = ?, Email = ? WHERE HouseholdID = ?";
        jdbcTemplate.update(sql,
                household.getApartmentID(),
                household.getHeadOfHousehold(),
                household.getContactNumber(),
                household.getEmail(),
                household.getHouseholdID());
    }
    public void delete(int id) {
        String sql = "DELETE FROM household WHERE HouseholdID = ?";
        jdbcTemplate.update(sql, id);
    }
}