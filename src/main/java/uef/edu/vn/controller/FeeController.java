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
import uef.edu.vn.model.Fee;
import uef.edu.vn.model.Household;
import uef.edu.vn.service.FeeService;
import uef.edu.vn.service.HouseholdService;

@Controller
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;
    @Autowired
    private HouseholdService householdService;
    private final String path = "/WEB-INF/views/";
    // Hiển thị danh sách phí

    @GetMapping
    public String listFees(Model model) {
        List<Fee> fees = feeService.getAll();
        model.addAttribute("fees", fees);
        model.addAttribute("body", path + "fee/list.jsp");
        return "shared/main";
    }
    // Hiển thị form thêm phí

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("fee", new Fee());
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("body", path + "fee/form.jsp");
        return "shared/main";
    }
    // Xử lý thêm mới

    @PostMapping("/add")
    public String addFee(@ModelAttribute Fee fee) {
        feeService.add(fee);
        return "redirect:/fees";
    }
    // Hiển thị form chỉnh sửa

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Fee fee = feeService.getById(id);
        model.addAttribute("fee", fee);
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("body", path + "fee/form.jsp");
        return "shared/main";
    }
    // Xử lý cập nhật

    @PostMapping("/edit")
    public String updateFee(@ModelAttribute Fee fee) {
        feeService.update(fee);
        return "redirect:/fees";
    }
    // Xử lý xóa

    @GetMapping("/delete/{id}")
    public String deleteFee(@PathVariable("id") int id) {
        feeService.delete(id);
        return "redirect:/fees";
    }
}
