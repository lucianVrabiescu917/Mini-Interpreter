package View;

import Model.Exception.MyException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;


    public TextMenu() {
        commands = new HashMap<>();
    }

    public TextMenu(Map<String, Command> commands) {
        this.commands = commands;
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.values()) {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show() throws MyException, IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            com.execute();
        }
    }

    public TextMenu deepCopy() {
        return new TextMenu(commands);
    }
}