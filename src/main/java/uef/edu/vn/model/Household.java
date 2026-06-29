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

public class Household {

    private int householdID;
    @Min(value = 1, message = "Apartment ID must be selected")
    private int apartmentID;
    @NotBlank(message = "Head of household name is required")
    @Size(max = 100)
    private String headOfHousehold;
    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be 10–15 digits")
    private String contactNumber;
    @Email(message = "Invalid email format")
    private String email;

    public Household() {
    }

    public Household(int householdID, int apartmentID, String headOfHousehold, String contactNumber, String email) {
        this.householdID = householdID;
        this.apartmentID = apartmentID;
        this.headOfHousehold = headOfHousehold;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public int getHouseholdID() {
        return householdID;
    }

    public void setHouseholdID(int householdID) {
        this.householdID = householdID;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getHeadOfHousehold() {
        return headOfHousehold;
    }

    public void setHeadOfHousehold(String headOfHousehold) {
        this.headOfHousehold = headOfHousehold;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
