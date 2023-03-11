package calculator.factory;

import calculator.Calculator;
import calculator.operations.*;
import calculator.stackcalculator.StackCalculatorFromFile;
import calculator.stackcalculator.StackCalculatorFromTerminal;

public class FactoryCalculator {
    public static Calculator make(String[] args) {
        if (args == null) {
            throw new NullPointerException();
        } else if (args.length == 0) {
            return new StackCalculatorFromTerminal();
        } else if (args.length == 1) {
            return new StackCalculatorFromFile(args[0]);
        } else return null;
    }
}
