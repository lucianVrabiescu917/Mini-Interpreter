package View;

import Controller.Controller;
import Model.Exception.MyException;

import java.io.IOException;

public class Console {

    private Controller controller;

    public Console(Controller controller) {
        this.controller = controller;
    }

    public void inputProgram() throws MyException {
        try {
            System.out.println(controller.allStep());
        } catch (MyException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
