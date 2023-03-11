package calculator.stackcalculator;

import calculator.Calculator;
import calculator.context.ProgramContext;
import calculator.exception.CalculatorException;
import calculator.factory.FactoryOperations;
import calculator.operations.Operation;
import calculator.utils.Command;
import calculator.utils.CommandParser;

import java.io.*;

public class StackCalculatorFromFile implements Calculator {
    String fileName;
    ProgramContext context;

    public StackCalculatorFromFile(String fileName) {
        if (fileName == null) {
            throw new NullPointerException();
        }
        this.fileName = fileName;
        context = new ProgramContext();
    }
    @Override
    public double doCalculation() throws CalculatorException {
        int linesNumber = 1;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                Command cmd = CommandParser.pars(line);
                Operation op = FactoryOperations.make(cmd.getId());
                op.exec(context, cmd.getArgs());

                line = reader.readLine();
                linesNumber += 1;
            }
            reader.close();
            fr.close();
        } catch (IOException e) {
            throw new CalculatorException("unable to read file", e);
        } catch (Exception e) {
            throw new CalculatorException("error in " + linesNumber +" line", e);
        }

        return 0;
    }
}
