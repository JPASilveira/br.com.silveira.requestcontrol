package view;

import utilities.DocumentFormatter;
import view.enums.ApplicantEnum;
import view.enums.MenuEnum;
import view.enums.OptionEnum;
import view.enums.RequestEnum;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInput {
    public static MenuEnum requestControl(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.MainMenu();
        while (true){
            try {
                input = scanner.nextLine();
                if(input == null || input.isEmpty()){
                    throw new InputMismatchException();
                }
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "A" -> MenuEnum.APPLICANT;
                    case "R" -> MenuEnum.REQUEST;
                    case "E" -> MenuEnum.EXIT;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static ApplicantEnum applicant(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.applicantMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "A" -> ApplicantEnum.ADD;
                    case "E" -> ApplicantEnum.EDIT;
                    case "L" -> ApplicantEnum.LIST;
                    case "D" -> ApplicantEnum.DELETE;
                    case "R" -> ApplicantEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static ApplicantEnum applicantEdit(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.applicantEditMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "N" -> ApplicantEnum.NAME;
                    case "D" -> ApplicantEnum.DOCUMENT;
                    case "R" -> ApplicantEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static ApplicantEnum applicantList(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.listApplicantMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "A" -> ApplicantEnum.ALL;
                    case "N" -> ApplicantEnum.NAME;
                    case "D" -> ApplicantEnum.DOCUMENT;
                    case "R" -> ApplicantEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static String applicantName(){
        Scanner scanner = new Scanner(System.in);
        String name;
        while (true) {
            try {
                System.out.println("Please enter applicant name: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again.");
                    continue;
                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static String applicantDocument(){
        Scanner scanner = new Scanner(System.in);
        String document;
        while (true) {
            try {
                System.out.println("Insert applicant document: ");
                document = scanner.nextLine();
                if (document.isEmpty()) {
                    System.out.println("Document cannot be empty. Please try again.");
                    continue;
                }
                return DocumentFormatter.formatCpfOrCnpj(document);
            } catch (Exception e) {
                System.out.println("Error reading document. Please try again.");
            }
        }
    }

    public static int applicantId(){
        Scanner scanner = new Scanner(System.in);
        int id;
        while (true) {
            try {
                System.out.println("Please enter applicant id: ");
                id = scanner.nextInt();
                if (id < 0 ){
                    System.out.println("Invalid input, please try again.");
                    scanner.next();
                    continue;
                }
                return id;
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
                scanner.next();
            }
        }
    }

    public static RequestEnum request(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.requestMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "A" -> RequestEnum.ADD;
                    case "E" -> RequestEnum.EDIT;
                    case "L" -> RequestEnum.LIST;
                    case "D" -> RequestEnum.DELETE;
                    case "R" -> RequestEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static RequestEnum requestEdit(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.requestEditMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "A" -> RequestEnum.APPLICANT;
                    case "O" -> RequestEnum.OPENING;
                    case "C" -> RequestEnum.CLOSING;
                    case "D" -> RequestEnum.DESCRIPTION;
                    case "R" -> RequestEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static RequestEnum requestList(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.listRequestMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "A" -> RequestEnum.ALL;
                    case "I" -> RequestEnum.ID;
                    case "D" -> RequestEnum.DATE;
                    case "R" -> RequestEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static int requestId(){
        Scanner scanner = new Scanner(System.in);
        int id;
        while (true) {
            try {
                System.out.println("Please enter request id: ");
                id = scanner.nextInt();
                if (id < 0 ){
                    System.out.println("Invalid input, please try again.");
                    scanner.next();
                    continue;
                }
                return id;
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
                scanner.next();
            }
        }
    }

    public static OptionEnum selectOrList(){
        Scanner scanner = new Scanner(System.in);
        String input;
        MenuPrinter.optionMenu();
        while (true){
            try {
                input = scanner.nextLine();
                return switch (input.substring(0,1).trim().toUpperCase()){
                    case "S" -> OptionEnum.SELECT;
                    case "L" -> OptionEnum.LIST;
                    case "R" -> OptionEnum.RETURN;
                    default -> throw new InputMismatchException();
                };
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }
}
