package debug;

import controller.MenuController;

public class debugMenuMain {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        while (true){
            menuController.mainMenu();
            break;
        }
    }
}
