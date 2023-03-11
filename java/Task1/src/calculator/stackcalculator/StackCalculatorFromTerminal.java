package calculator.stackcalculator;

import calculator.Calculator;
import calculator.context.ProgramContext;
import calculator.exception.CalculatorException;
import calculator.factory.FactoryOperations;
import calculator.operations.Operation;
import calculator.utils.Command;
import calculator.utils.CommandParser;
import java.util.Scanner;

public class StackCalculatorFromTerminal implements Calculator {
    ProgramContext context;

    public StackCalculatorFromTerminal(){
        context = new ProgramContext();
    }

    @Override
    public double doCalculation() throws CalculatorException {
        int linesNumber = 1;
        try {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            while (line != null && !line.equals("exit")) {
                Command cmd = CommandParser.pars(line);
                Operation op = FactoryOperations.make(cmd.getId());
                op.exec(context, cmd.getArgs());

                line = scanner.nextLine();;
                linesNumber += 1;
            }
        } catch (Exception e) {
            throw new CalculatorException("error in " + linesNumber +" line", e);
        }

        return 0;
    }
}
