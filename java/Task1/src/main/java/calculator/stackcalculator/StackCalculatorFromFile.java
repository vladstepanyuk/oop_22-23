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


import java.io.*;

public class StackCalculatorFromFile implements Calculator {
    String fileName;
    ProgramContext context;
    Logger logger = LogManager.getLogger(StackCalculatorFromFile.class.getName());

    public StackCalculatorFromFile(String fileName) {
        logger.info("initializing calculator data");
        if (fileName == null) {
            throw new NullPointerException();
        }
        this.fileName = fileName;
        context = new ProgramContext();
    }
    @Override
    public double doCalculation() throws CalculatorException {
        logger.info("start calculation from " + fileName);
        int linesNumber = 1;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            logger.info("read line");
            String line = reader.readLine();
            while (line != null) {
                Command cmd = CommandParser.pars(line);
                Operation op = FactoryOperations.make(cmd.getId());
                op.exec(context, cmd.getArgs());

                logger.info("read line");
                line = reader.readLine();
                linesNumber += 1;
            }
            logger.info("finishing the calculations");
            reader.close();
            fr.close();
        } catch (IOException e) {
            logger.error("unable to read " + fileName);
            throw new CalculatorException("unable to read file", e);
        } catch (Exception e) {
            logger.error("error in " + linesNumber +" line: " + e.getLocalizedMessage());
            throw new CalculatorException("error in " + linesNumber +" line", e);
        }

        return 0;
    }
}
