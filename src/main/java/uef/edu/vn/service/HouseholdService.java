/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uef.edu.vn.model.Household;
import uef.edu.vn.repository.HouseholdRepository;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public List<Household> getAll() {
        return householdRepository.findAll();
    }

    public Household getById(int id) {
        return householdRepository.findById(id);
    }

    public void add(Household h) {
        householdRepository.save(h);
    }

    public void update(Household h) {
        householdRepository.update(h);
    }

    public void delete(int id) {
        householdRepository.delete(id);
    }
}
