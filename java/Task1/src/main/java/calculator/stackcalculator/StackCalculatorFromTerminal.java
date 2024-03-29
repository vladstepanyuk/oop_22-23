package calculator.stackcalculator;

import calculator.Calculator;
import calculator.context.ProgramContext;
import calculator.exception.CalculatorException;
import calculator.factory.FactoryOperations;
import calculator.operations.Operation;
import calculator.utils.Command;
import calculator.utils.CommandParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Scanner;

public class StackCalculatorFromTerminal implements Calculator {
    private static final String configFileName = "src/main/resources/configuration.txt";
    ProgramContext context;

    public StackCalculatorFromTerminal() throws CalculatorException {
        logger.info("initializing calculator data");
        try {
            FileInputStream inputStream = new FileInputStream(configFileName);
            FactoryOperations.getResourceAsStream(inputStream);
        } catch (Exception e) {
            throw new CalculatorException("unable to configure calculator", e);
        }
        context = new ProgramContext();
    }

    static final Logger logger = LogManager.getLogger(StackCalculatorFromTerminal.class.getName());

    @Override
    public double doCalculation() throws CalculatorException {
        logger.info("start calculation");
        int linesNumber = 1;
        try {
            Scanner scanner = new Scanner(System.in);
            logger.info("read line");
            String line = scanner.nextLine();
            while (line != null && !line.equals("exit")) {
                Command cmd = CommandParser.pars(line);
                Operation op = FactoryOperations.make(cmd.getId());
                op.exec(context, cmd.getArgs());

                logger.info("read line");
                line = scanner.nextLine();
                linesNumber += 1;
            }

            logger.info("finishing the calculations");

        } catch (Exception e) {
            logger.error("error in " + linesNumber +" line");
            throw new CalculatorException("error in " + linesNumber +" line", e);
        }

        return 0;
    }
}
