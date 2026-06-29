/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import uef.edu.vn.model.Parking;
import uef.edu.vn.model.Household;
import uef.edu.vn.service.HouseholdService;
import uef.edu.vn.service.ParkingService;

@Controller
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private HouseholdService householdService;
    private final String path = "/WEB-INF/views/";
    // Hiển thị danh sách chỗ đỗ xe

    @GetMapping
    public String listParkingSpots(Model model) {
        List<Parking> parkings = parkingService.getAll();
        model.addAttribute("parkings", parkings);
        model.addAttribute("body", path + "parking/list.jsp");
        return "shared/main";
    }
    // Hiển thị form thêm chỗ đỗ

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("parking", new Parking());
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("body", path + "parking/form.jsp");
        return "shared/main";
    }
    // Xử lý thêm mới

    @PostMapping("/add")
    public String addParking(@ModelAttribute Parking parking) {
        parkingService.add(parking);
        return "redirect:/parking";
    }
    // Hiển thị form cập nhật

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Parking parking = parkingService.getById(id);
        model.addAttribute("parking", parking);
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("body", path + "parking/form.jsp");
        return "shared/main";
    }
    // Xử lý cập nhật

    @PostMapping("/edit")
    public String updateParking(@ModelAttribute Parking parking) {
        parkingService.update(parking);
        return "redirect:/parking";
    }
    // Xử lý xóa

    @GetMapping("/delete/{id}")
    public String deleteParking(@PathVariable("id") int id) {
        parkingService.delete(id);
        return "redirect:/parking";
    }
}
