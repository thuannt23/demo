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
import uef.edu.vn.model.Complaint;
import uef.edu.vn.model.Household;
import uef.edu.vn.service.ComplaintService;
import uef.edu.vn.service.HouseholdService;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private HouseholdService householdService;
    private final String path = "/WEB-INF/views/";
    // Hiển thị danh sách khiếu nại

    @GetMapping
    public String listComplaints(Model model) {
        List<Complaint> complaints = complaintService.getAll();
        model.addAttribute("complaints", complaints);
        model.addAttribute("body", path + "complaint/list.jsp");
        return "shared/main";
    }
    // Hiển thị form thêm mới khiếu nại

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("body", path + "complaint/form.jsp");
        return "shared/main";
    }
    // Xử lý thêm mới

    @PostMapping("/add")
    public String addComplaint(@ModelAttribute Complaint complaint) {
        complaintService.add(complaint);
        return "redirect:/complaints";
    }
    // Hiển thị form chỉnh sửa

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Complaint complaint = complaintService.getById(id);
        model.addAttribute("complaint", complaint);
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("body", path + "complaint/form.jsp");
        return "shared/main";
    }
    // Xử lý cập nhật

    @PostMapping("/edit")
    public String updateComplaint(@ModelAttribute Complaint complaint) {
        complaintService.update(complaint);
        return "redirect:/complaints";
    }
    // Xử lý xóa

    @GetMapping("/delete/{id}")
    public String deleteComplaint(@PathVariable("id") int id) {
        complaintService.delete(id);
        return "redirect:/complaints";
    }
}
