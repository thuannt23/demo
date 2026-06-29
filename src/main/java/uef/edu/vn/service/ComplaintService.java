/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import uef.edu.vn.model.Complaint;
import uef.edu.vn.repository.ComplaintRepository;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }

    public Complaint getById(int id) {
        return complaintRepository.findById(id);
    }

    public void add(Complaint c) {
        complaintRepository.save(c);
    }

    public void update(Complaint c) {
        complaintRepository.update(c);
    }

    public void delete(int id) {
        complaintRepository.delete(id);
    }
}
