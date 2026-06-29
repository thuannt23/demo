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
import uef.edu.vn.model.Apartment;
import uef.edu.vn.service.ApartmentService;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;
    private final String path = "/WEB-INF/views/";
    // Hiển thị danh sách căn hộ

    @GetMapping
    public String listApartments(Model model) {
        List<Apartment> apartments = apartmentService.getAll();
        model.addAttribute("apartments", apartments);
        model.addAttribute("body", path + "apartment/list.jsp");
        return "shared/main";
    }
// Hiển thị form thêm mới

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("body", path + "apartment/form.jsp");
        return "shared/main";
    }
    // Xử lý thêm mới

    @PostMapping("/add")
    public String addApartment(@ModelAttribute Apartment apartment) {
        apartmentService.add(apartment);
        return "redirect:/apartments";
    }
    // Hiển thị form cập nhật

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Apartment apartment = apartmentService.getById(id);
        model.addAttribute("apartment", apartment);
        model.addAttribute("body", path + "apartment/form.jsp");
        return "shared/main";
    }
    // Xử lý cập nhật

    @PostMapping("/edit")
    public String updateApartment(@ModelAttribute Apartment apartment) {
        apartmentService.update(apartment);
        return "redirect:/apartments";
    }
    // Xử lý xóa

    @GetMapping("/delete/{id}")
    public String deleteApartment(@PathVariable("id") int id) {
        apartmentService.delete(id);
        return "redirect:/apartments";
    }
}
