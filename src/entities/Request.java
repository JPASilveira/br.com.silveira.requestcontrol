package entities;

import utilities.DateFormatter;

import java.time.LocalDate;

public class Request {
    private Applicant applicant;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private String description;

    public Request() {}

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = DateFormatter.formatDate(openingDate);
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = DateFormatter.formatDate(closingDate);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
