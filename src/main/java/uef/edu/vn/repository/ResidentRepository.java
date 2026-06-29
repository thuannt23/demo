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
import uef.edu.vn.model.Resident;

@Repository
public class ResidentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resident r = new Resident();
        r.setResidentID(rs.getInt("ResidentID"));
        r.setHouseholdID(rs.getInt("HouseholdID"));
        r.setFullName(rs.getString("FullName"));
        r.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
        r.setGender(rs.getString("Gender"));
        r.setRelationship(rs.getString("Relationship"));
        return r;
    }

    public List<Resident> findAll() {
        return jdbcTemplate.query("SELECT * FROM resident", this::mapRow);
    }

    public Resident findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM resident WHERE ResidentID =  ?", this::mapRow, id);
    }

    public void save(Resident r) {
        String sql = "INSERT INTO Residents (HouseholdID, FullName, DateOfBirth, Gender, Relationship) VALUES( ?,  ?,  ?,  ?,  ?)";
        jdbcTemplate.update(sql, r.getHouseholdID(), r.getFullName(), r.getDateOfBirth(),
                r.getGender(), r.getRelationship());
    }

    public void update(Resident r) {
        String sql = "UPDATE Residents SET HouseholdID=?, FullName=?, DateOfBirth=?,Gender =  ?, Relationship =  ? WHERE  ResidentID =  ?";
        jdbcTemplate.update(sql, r.getHouseholdID(), r.getFullName(), r.getDateOfBirth(),
                r.getGender(), r.getRelationship(), r.getResidentID());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM resident WHERE ResidentID=?", id);
    }
}
