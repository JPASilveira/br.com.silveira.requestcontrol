package debug;

import controller.MenuController;
import repository.DataAccessObject;

public class debugMenuRequest {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        while (true) {
                DataAccessObject dao = new DataAccessObject();
                menuController.requestMenu();

        }
    }
}
