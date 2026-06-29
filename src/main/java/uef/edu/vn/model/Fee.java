/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class Fee {

    private int feeID;
    @Min(value = 1, message = "Household ID is required")
    private int householdID;
    @NotBlank(message = "Fee type is required")
    private String feeType;
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private double amount;
    @NotNull(message = "Due date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "Paid|Unpaid", message = "Status must be Paid or Unpaid")
    private String status;
    // Constructors

    public Fee() {
    }

    public Fee(int feeID, int householdID, String feeType, double amount, LocalDate dueDate,
            String status) {
        this.feeID = feeID;
        this.householdID = householdID;
        this.feeType = feeType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getFeeID() {
        return feeID;
    }

    public void setFeeID(int feeID) {
        this.feeID = feeID;
    }

    public int getHouseholdID() {
        return householdID;
    }

    public void setHouseholdID(int householdID) {
        this.householdID = householdID;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
