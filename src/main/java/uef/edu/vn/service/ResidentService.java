/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uef.edu.vn.model.Resident;
import uef.edu.vn.repository.ResidentRepository;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    public List<Resident> getAll() {
        return residentRepository.findAll();
    }

    public Resident getById(int id) {
        return residentRepository.findById(id);
    }

    public void add(Resident r) {
        residentRepository.save(r);
    }

    public void update(Resident r) {
        residentRepository.update(r);
    }

    public void delete(int id) {
        residentRepository.delete(id);
    }
}
