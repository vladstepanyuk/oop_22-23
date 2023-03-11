import calculator.Calculator;
import calculator.factory.FactoryCalculator;
import calculator.stackcalculator.StackCalculatorFromFile;
import calculator.exception.CalculatorException;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator calculator = FactoryCalculator.make(args);
            assert calculator != null;
            calculator.doCalculation();
        } catch (CalculatorException e){
            System.err.print(e.getMessage());
            e.printStackTrace();
        }

    }
}