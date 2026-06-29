/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uef.edu.vn.model.Fee;
import uef.edu.vn.repository.FeeRepository;

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    public List<Fee> getAll() {
        return feeRepository.findAll();
    }

    public Fee getById(int id) {
        return feeRepository.findById(id);
    }

    public void add(Fee f) {
        feeRepository.save(f);
    }

    public void update(Fee f) {
        feeRepository.update(f);
    }

    public void delete(int id) {
        feeRepository.delete(id);
    }
}
