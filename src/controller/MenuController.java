package controller;


import entities.Applicant;
import entities.Request;
import repository.DataAccessObject;
import view.ApplicantInput;
import view.MenuInput;
import view.RequestInput;
import view.enums.ApplicantEnum;
import view.enums.MenuEnum;
import view.enums.OptionEnum;
import view.enums.RequestEnum;

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

    public int selectOrListRequest(){
        OptionEnum option = MenuInput.selectOrList();
        if(option == OptionEnum.LIST){
            requestMenuList();
        } else if (option == OptionEnum.RETURN) {
            return 0;
        }
        return MenuInput.requestId();
    }

    public void requestMenu(){
        while (true) {
            RequestEnum option = MenuInput.request();
            switch (option) {
                case ADD -> requestMenuAdd();
                case EDIT -> requestMenuEdit();
                case LIST -> requestMenuList();
                case DELETE -> requestMenuDelete();
            }
            break;
        }
    }

    public void requestMenuAdd(){
        Request request = new Request();
        Applicant applicant;
        while (true) {
            try {
                System.out.println("Select Applicant ID");
                applicant = dao.getApplicantById(selectOrListApplicant());
                RequestInput.add(applicant, request);
                dao.addRequest(request);
                System.out.println("Applicant added");
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void requestMenuEdit(){
        Request request = new Request();
        Applicant applicant;
        RequestEnum option = MenuInput.requestEdit();

        switch (option) {
            case APPLICANT:
                while (true) {
                    try {
                        System.out.println("Select Request ID");
                        int requestId = selectOrListRequest();
                        if (requestId == 0) {
                           break;
                        }
                        request = dao.getRequestById(requestId);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                while (true){
                    try {
                        System.out.println("Select new Applicant ID");
                        int applicantId = selectOrListApplicant();
                        if(applicantId == 0){
                            break;
                        }
                        applicant = dao.getApplicantById(applicantId);
                        request.setApplicant(applicant);
                        dao.editRequest(request.getId(), request);
                        break;
                }catch (Exception e){
                        System.out.println(e.getMessage());}
                }
                break;

            case OPENING:
                while (true){
                    try {
                        System.out.println("Select Request ID");
                        int requestId = selectOrListRequest();
                        if(requestId == 0){
                            break;
                        }
                        request = dao.getRequestById(requestId);
                        RequestInput.editOpeningDate(request);
                        dao.editRequest(request.getId(), request);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                break;

            case CLOSING:
                while (true){
                    try {
                        System.out.println("Select Request ID");
                        int requestId = selectOrListRequest();
                        if(requestId == 0){
                            break;
                        }
                        request = dao.getRequestById(requestId);
                        RequestInput.editClosingDate(request);
                        dao.editRequest(request.getId(), request);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                break;

            case DESCRIPTION:
                while (true){
                    try {
                        System.out.println("Select Request ID");
                        int requestId = selectOrListRequest();
                        if(requestId == 0){
                            break;
                        }
                        request = dao.getRequestById(requestId);
                        RequestInput.editDescription(request);
                        dao.editRequest(request.getId(), request);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }

            case RETURN:
                break;
        }
    }

    public void requestMenuList() {
        Scanner scanner = new Scanner(System.in);
        RequestEnum option = MenuInput.requestList();
        List<Request> requests;
        switch (option) {
            case ALL:
                requests = dao.listAllRequests();
                for (Request request : requests) {
                    System.out.println(request);
                }
                System.out.println("Press enter to continue");
                scanner.nextLine();
                break;

            case ID:
                int applicantId = selectOrListApplicant();
                requests = dao.listRequestsByApplicantId(applicantId);
                for (Request request : requests) {
                    System.out.println(request);
                }
                System.out.println("Press enter to continue");
                scanner.nextLine();
                break;

            case DATE:
                Request requestDate = new Request();
                while(true) {
                    try {
                        RequestInput.editOpeningDate(requestDate);
                        requests = dao.listRequestsByOpeningDate(requestDate.getOpeningDate());
                        for (Request request : requests) {
                            System.out.println(request);
                        }
                        System.out.println("Press enter to continue");
                        scanner.nextLine();
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            case RETURN:
                break;
        }
    }

    public void requestMenuDelete(){
        while (true) {
            try {
                int requestId = selectOrListRequest();
                if (requestId == 0) {
                    break;
                }
                dao.removeRequest(requestId);
                System.out.println("Request removed");
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void mainMenu(){
        MenuEnum option;
        while (true) {
            try {
                option = MenuInput.requestControl();
                switch (option) {
                    case APPLICANT:
                        applicantMenu();
                        break;
                    case REQUEST:
                        requestMenuList();
                        break;
                    case EXIT:
                        System.exit(0);
            }
        }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
