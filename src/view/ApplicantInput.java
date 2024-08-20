package view;

import entities.Applicant;

import java.util.Scanner;

public class ApplicantInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static void add(Applicant applicant) {
        String name;
        String document;

        while (true) {
            try {
                System.out.println("Insert applicant name: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading name. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Insert applicant document: ");
                document = scanner.nextLine();
                if (document.isEmpty()) {
                    System.out.println("Document cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading document. Please try again.");
            }
        }

        applicant.setApplicantName(name);
        applicant.setApplicantDocument(document);
    }

    public static void editName(Applicant applicant) {
        String name;

        while (true) {
            try {
                System.out.println("Insert new applicant name: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again.");
                    continue;
                }
                applicant.setApplicantName(name);
                break;
            } catch (Exception e) {
                System.out.println("Error reading name. Please try again.");
            }
        }
    }

    public static void editDocument(Applicant applicant) {
        String document;

        while (true) {
            try {
                System.out.println("Insert new applicant document: ");
                document = scanner.nextLine();
                if (document.isEmpty()) {
                    System.out.println("Document cannot be empty. Please try again.");
                    continue;
                }
                applicant.setApplicantDocument(document);
                break;
            } catch (Exception e) {
                System.out.println("Error reading document. Please try again.");
            }
        }
    }
}
