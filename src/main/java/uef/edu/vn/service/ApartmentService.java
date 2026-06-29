/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uef.edu.vn.model.Apartment;
import uef.edu.vn.repository.ApartmentRepository;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    public Apartment getById(int id) {
        return apartmentRepository.findById(id);
    }

    public void add(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    public void update(Apartment apartment) {
        apartmentRepository.update(apartment);
    }

    public void delete(int id) {
        apartmentRepository.delete(id);
    }
}
