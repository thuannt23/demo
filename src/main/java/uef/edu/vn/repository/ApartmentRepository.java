/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import uef.edu.vn.model.Apartment;

@Repository
public class ApartmentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // Mapping ResultSet to Apartment object

    private Apartment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Apartment apartment = new Apartment();
        apartment.setApartmentID(rs.getInt("ApartmentID"));
        apartment.setApartmentNumber(rs.getString("ApartmentNumber"));
        apartment.setFloor(rs.getInt("Floor"));
        apartment.setArea(rs.getDouble("Area"));
        apartment.setStatus(rs.getString("Status"));
        return apartment;
    }
    // SELECT * FROM apartment

    public List<Apartment> findAll() {
        String sql = "SELECT * FROM apartment";
        return jdbcTemplate.query(sql, this::mapRow);
    }
    // SELECT * FROM apartment WHERE ApartmentID = ?

    public Apartment findById(int id) {
        String sql = "SELECT * FROM apartment WHERE ApartmentID = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, id);
    }
    // INSERT INTO apartment (...)

    public void save(Apartment apartment) {
        String sql = "INSERT INTO apartment (ApartmentNumber, Floor, Area, Status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                apartment.getApartmentNumber(),
                apartment.getFloor(),
                apartment.getArea(),
                apartment.getStatus());
    }
    // UPDATE apartment SET ... WHERE ApartmentID = ?

    public void update(Apartment apartment) {
        String sql = "UPDATE apartment SET ApartmentNumber = ?, Floor = ?, Area = ?, Status = ? WHERE ApartmentID = ?";
        jdbcTemplate.update(sql,
                apartment.getApartmentNumber(),
                apartment.getFloor(),
                apartment.getArea(),
                apartment.getStatus(),
                apartment.getApartmentID());
    }
    // DELETE FROM apartment WHERE ApartmentID = ?

    public void delete(int id) {
        String sql = "DELETE FROM apartment WHERE ApartmentID = ?";
        jdbcTemplate.update(sql, id);
    }
}
