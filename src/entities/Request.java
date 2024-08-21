package entities;

import utilities.DateFormatter;

import java.time.LocalDate;

public class Request {
    private Integer id;
    private Applicant applicant;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private String description;

    public Request() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getOpeningDate() {
        return DateFormatter.formatDate(openingDate);
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = DateFormatter.formatDate(openingDate);
    }

    public String getClosingDate() {
        return DateFormatter.formatDate(closingDate);
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = DateFormatter.formatDate(closingDate);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    @Override
    public String toString() {
        return "Request [id= " + id + ", applicant= " + applicant.getApplicantName() + ", openingDate= " + openingDate + ", closingDate= " + closingDate + ", description= " + description + "]";
    }
}
