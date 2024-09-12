package view;

import entities.Applicant;
import entities.Request;

import java.util.Scanner;

public class RequestInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static void add(Applicant applicant, Request request) {
        String openingDate;
        String description;

        while (true) {
            try {
                System.out.println("Insert request opening date (dd/MM/yyyy): ");
                openingDate = scanner.nextLine();
                if (openingDate.isEmpty()) {
                    System.out.println("Opening date cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading opening date. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Insert request description: ");
                description = scanner.nextLine();
                if (description.isEmpty()) {
                    System.out.println("Description cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading description. Please try again.");
            }
        }

        request.setApplicant(applicant);
        request.setOpeningDate(openingDate);
        request.setDescription(description);
    }


    public static void editOpeningDate(Request request) {
        String openingDate;

        while (true) {
            try {
                System.out.println("Insert new request opening date (dd/MM/yyyy): ");
                openingDate = scanner.nextLine();
                if (openingDate.isEmpty()) {
                    System.out.println("Opening date cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading opening date. Please try again.");
            }
        }

        request.setOpeningDate(openingDate);
    }

    public static void editClosingDate(Request request) {
        String closingDate;

        while (true) {
            try {
                System.out.println("Insert request closing date (dd/MM/yyyy): ");
                closingDate = scanner.nextLine();
                if (closingDate.isEmpty()) {
                    System.out.println("Closing date cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading closing date. Please try again.");
            }
        }

        request.setClosingDate(closingDate);
    }

    public static void editDescription(Request request) {
        String description;

        while (true) {
            try {
                System.out.println("Insert new request description: ");
                description = scanner.nextLine();
                if (description.isEmpty()) {
                    System.out.println("Description cannot be empty. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error reading description. Please try again.");
            }
        }

        request.setDescription(description);
    }
}
