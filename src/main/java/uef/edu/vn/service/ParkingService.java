/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uef.edu.vn.model.Parking;
import uef.edu.vn.repository.ParkingRepository;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    public Parking getById(int id) {
        return parkingRepository.findById(id);
    }

    public void add(Parking p) {
        parkingRepository.save(p);
    }

    public void update(Parking p) {
        parkingRepository.update(p);
    }

    public void delete(int id) {
        parkingRepository.delete(id);
    }
}
