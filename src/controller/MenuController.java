package controller;


import entities.Applicant;
import repository.DataAccessObject;
import view.ApplicantInput;
import view.MenuInput;
import view.MenuPrinter;
import view.enums.ApplicantEnum;
import view.enums.OptionEnum;

import java.util.List;
import java.util.Scanner;

public class MenuController {
    DataAccessObject dao = new DataAccessObject();

    public void applicantMenu() {
        while (true) {
            ApplicantEnum option = MenuInput.applicant();
            switch (option) {
                case ADD -> applicantMenuAdd();
                case EDIT -> applicantMenuEdit();
                case LIST -> applicantMenuList();
                case DELETE -> applicantMenuDelete();
            }
            break;
        }

    }

    public void applicantMenuAdd() {
        Applicant applicant = new Applicant();
        while (true) {
            try {
                ApplicantInput.add(applicant);
                dao.addApplicant(applicant);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Applicant added");
    }

    public void applicantMenuEdit() {
        Applicant applicant = new Applicant();
        ApplicantEnum option = MenuInput.applicantEdit();
        int applicantId;
        switch (option) {
            case NAME:
                while (true){
                    try {
                        applicantId = selectOrListApplicant();
                        if (applicantId == 0) {
                            break;
                        }
                        ApplicantInput.editName(applicant);
                        dao.editApplicant(applicantId, applicant);
                        System.out.println("applicant name edited");
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case DOCUMENT:
                while (true){
                    try {
                        applicantId = selectOrListApplicant();
                        if (applicantId == 0) {
                            break;
                        }
                        ApplicantInput.editDocument(applicant);
                        dao.editApplicant(applicantId, applicant);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("applicant document edited");
                break;
            case RETURN:
                break;
        }
    }

    public void applicantMenuList() {
        Scanner scanner = new Scanner(System.in);
        ApplicantEnum option = MenuInput.applicantList();
        List<Applicant> applicants;
        switch (option) {
            case ALL:
                applicants = dao.listAllApplicants();
                for (Applicant applicant : applicants) {
                    System.out.println(applicant);
                }
                System.out.println("Press enter to continue");
                scanner.nextLine();
                break;
            case NAME:
                applicants = dao.listApplicantsByName(MenuInput.applicantName());
                for (Applicant applicant : applicants) {
                    System.out.println(applicant);
                }
                System.out.println("Press enter to continue");
                scanner.nextLine();
                break;
            case DOCUMENT:
                applicants = dao.listApplicantsByDocument(MenuInput.applicantDocument());
                for (Applicant applicant : applicants) {
                    System.out.println(applicant);
                }
                System.out.println("Press enter to continue");
                scanner.nextLine();
            case RETURN:
                break;
        }
    }

    public void applicantMenuDelete() {
        while (true) {
            try {
                int applicantId = selectOrListApplicant();
                if (applicantId == 0) {
                    break;
                }
                dao.removeApplicant(applicantId);
                System.out.println("Applicant removed");
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public int selectOrListApplicant(){
        OptionEnum option = MenuInput.selectOrList();
        if(option == OptionEnum.LIST){
            applicantMenuList();
        } else if (option == OptionEnum.RETURN) {
            return 0;
        }
        return MenuInput.applicantId();
    }
}