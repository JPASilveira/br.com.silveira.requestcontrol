package debug;

import controller.MenuController;

public class debugMenuController {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        while (true){
            menuController.applicantMenu();
        }
    }
}
