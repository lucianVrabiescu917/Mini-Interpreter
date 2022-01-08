package View;

import Controller.Controller;
import Model.Exception.MyException;

import java.io.IOException;

public class RunExampleCommand extends Command {
    private Controller ctr;
    
    public RunExampleCommand(String key, String description, Controller ctr) {
        super(key, description);
        this.ctr = ctr;
    }

    @Override
    public void execute() throws MyException, IOException {
        try{
            String res = ctr.allStep();
//            System.out.println(res);
        } catch (MyException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    Command deepCopy() {
        return new RunExampleCommand(getKey(), getDescription(), ctr);
    }
}