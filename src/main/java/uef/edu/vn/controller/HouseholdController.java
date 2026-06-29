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
import uef.edu.vn.model.Household;
import uef.edu.vn.model.Apartment;
import uef.edu.vn.service.ApartmentService;
import uef.edu.vn.service.HouseholdService;

@Controller
@RequestMapping("/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;
    @Autowired
    private ApartmentService apartmentService;
    private final String path = "/WEB-INF/views/";
    // Hiển thị danh sách hộ dân

    @GetMapping
    public String listHouseholds(Model model) {
        List<Household> households = householdService.getAll();
        model.addAttribute("households", households);
        model.addAttribute("body", path + "household/list.jsp");
        return "shared/main";
    }
    // Hiển thị form thêm mới

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("household", new Household());
        model.addAttribute("apartments", apartmentService.getAll());
        model.addAttribute("body", path + "household/form.jsp");
        return "shared/main";
    }
    // Xử lý thêm mới

    @PostMapping("/add")
    public String addHousehold(@ModelAttribute Household household) {
        householdService.add(household);
        return "redirect:/households";
    }
    // Hiển thị form cập nhật

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Household household = householdService.getById(id);
        model.addAttribute("household", household);
        model.addAttribute("apartments", apartmentService.getAll());
        model.addAttribute("body", path + "household/form.jsp");
        return "shared/main";
    }
    // Xử lý cập nhật

    @PostMapping("/edit")
    public String updateHousehold(@ModelAttribute Household household) {
        householdService.update(household);
        return "redirect:/households";
    }
    // Xử lý xóa

    @GetMapping("/delete/{id}")
    public String deleteHousehold(@PathVariable("id") int id) {
        householdService.delete(id);
        return "redirect:/households";
    }
}
