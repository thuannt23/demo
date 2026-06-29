/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class Complaint {

    private int complaintID;
    @Min(value = 1, message = "Household ID is required")
    private int householdID;
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be 10–1000 characters")
    private String description;
    @NotNull(message = "Submission date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate submissionDate;
    @NotBlank(message = "Status is required")
    private String status;
    // Constructors

    public Complaint() {
    }

    public Complaint(int complaintID, int householdID, String description, LocalDate submissionDate, String status) {
        this.complaintID = complaintID;
        this.householdID = householdID;
        this.description = description;
        this.submissionDate = submissionDate;
        this.status = status;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getHouseholdID() {
        return householdID;
    }

    public void setHouseholdID(int householdID) {
        this.householdID = householdID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
