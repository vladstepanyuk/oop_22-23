package calculator.utils;

import calculator.operations.OperationIds;

public class Command {
    OperationIds id;
    String[] args;

    public Command(OperationIds id, String[] args){
        this.id = id;
        this.args = args;
    }
    public OperationIds getId(){
        return id;
    }

    public String[] getArgs() {
        return args;
    }
}
