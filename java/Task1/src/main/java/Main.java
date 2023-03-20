import calculator.Calculator;
import calculator.factory.FactoryCalculator;
import calculator.exception.CalculatorException;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator calculator = FactoryCalculator.make(args);
            assert calculator != null;
            calculator.doCalculation();
        } catch (CalculatorException e){
            e.printStackTrace();
        }

    }
}