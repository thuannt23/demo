/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

/**
 *
 * @author ADMIN
 */
import jakarta.validation.constraints.*;

public class Apartment {
    private int apartmentID;
    @NotBlank(message = "Apartment number is required")
    @Size(max = 10, message = "Max 10 characters allowed")
    private String apartmentNumber;
    @Min(value = 1, message = "Floor must be greater than 0")
    private int floor;
    @DecimalMin(value = "10.0", message = "Area must be at least 10 m²")
    private double area;
    @NotBlank(message = "Status is required")
    private String status;
    // Constructors

    public Apartment() {
    }

    public Apartment(int apartmentID, String apartmentNumber, int floor, double area, String status) {
        this.apartmentID = apartmentID;
        this.apartmentNumber = apartmentNumber;
        this.floor = floor;
        this.area = area;
        this.status = status;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
