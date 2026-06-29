package uef.edu.vn.bookinghotel.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uef.edu.vn.bookinghotel.model.Room;
import uef.edu.vn.bookinghotel.model.RoomType;
import uef.edu.vn.bookinghotel.service.RoomService;
import uef.edu.vn.bookinghotel.service.RoomTypeService;

@Controller
public class RoomController {

    private final RoomService roomService;
    private final RoomTypeService roomTypeService;

    public RoomController(RoomService roomService, RoomTypeService roomTypeService) {
        this.roomService = roomService;
        this.roomTypeService = roomTypeService;
    }

    @GetMapping("/rooms")
    public String rooms(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "roomTypeId", required = false) Integer roomTypeId,
            @RequestParam(value = "status", required = false) String status,
            Model model) {
        model.addAttribute("rooms", roomService.rooms(keyword, roomTypeId, status));
        model.addAttribute("roomTypes", roomTypeService.roomTypes());
        return "public/rooms/list";
    }

    @GetMapping("/rooms/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        model.addAttribute("room", roomService.room(id));
        return "public/rooms/detail";
    }

    @PostMapping("/rooms")
    public String saveRoom(@ModelAttribute Room room, HttpSession session) {
        String guard = Access.requireRole(session, "STAFF", "ADMIN");
        if (guard != null) {
            return guard;
        }

        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @PostMapping("/rooms/delete")
    public String deleteRoom(@RequestParam("id") int id, HttpSession session) {
        String guard = Access.requireRole(session, "ADMIN");
        if (guard != null) {
            return guard;
        }

        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @PostMapping("/rooms/status")
    public String status(
            @RequestParam("id") int id,
            @RequestParam("status") String status,
            HttpSession session) {
        String guard = Access.requireRole(session, "STAFF", "ADMIN");
        if (guard != null) {
            return guard;
        }

        roomService.updateRoomStatus(id, status);
        return "redirect:/rooms";
    }

    @GetMapping("/room-types")
    public String types(Model model, HttpSession session) {
        String guard = Access.requireRole(session, "RECEPTIONIST", "MANAGER", "ADMIN");
        if (guard != null) {
            return guard;
        }

        model.addAttribute("roomTypes", roomTypeService.roomTypes());
        return "admin/room-types/list";
    }

    @PostMapping("/room-types")
    public String saveType(@ModelAttribute RoomType type, HttpSession session) {
        String guard = Access.requireRole(session, "ADMIN");
        if (guard != null) {
            return guard;
        }

        roomTypeService.saveRoomType(type);
        return "redirect:/room-types";
    }

    @PostMapping("/room-types/delete")
    public String deleteType(@RequestParam("id") int id, HttpSession session) {
        String guard = Access.requireRole(session, "ADMIN");
        if (guard != null) {
            return guard;
        }

        roomTypeService.deleteRoomType(id);
        return "redirect:/room-types";
    }
}
