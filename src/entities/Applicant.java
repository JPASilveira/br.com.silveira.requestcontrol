package entities;

import utilities.DocumentFormatter;

public class Applicant {
    private String applicantName;
    private String applicantDocument;

    public Applicant() {}

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName.trim().toUpperCase();
    }

    public String getApplicantDocument() {
        return applicantDocument;
    }

    public void setApplicantDocument(String applicantDocument) {
        this.applicantDocument = DocumentFormatter.formatCpfOrCnpj(applicantDocument);
    }
}
