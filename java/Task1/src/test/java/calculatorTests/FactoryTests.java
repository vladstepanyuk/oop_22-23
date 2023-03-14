package calculatorTests;

import calculator.factory.*;
import calculator.operations.*;
import calculator.stackcalculator.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Factory's tests")
public class FactoryTests {

    @Test
    @DisplayName("Calculator factory test")
    void FactoryCalculatorTest(){
        assertThat(FactoryCalculator.make(new String[]{})).isInstanceOf(StackCalculatorFromTerminal.class);
        assertThat(FactoryCalculator.make(new String[]{"in.txt"})).isInstanceOf(StackCalculatorFromFile.class);
    }

    @Test
    @DisplayName("Operation factory test")
    void FactoryOperationsTest(){
        assertThat(FactoryOperations.make(OperationIds.DEFINE)).isInstanceOf(Define.class);
        assertThat(FactoryOperations.make(OperationIds.DIVISION)).isInstanceOf(Division.class);
        assertThat(FactoryOperations.make(OperationIds.MULTIPLY)).isInstanceOf(Multiply.class);
        assertThat(FactoryOperations.make(OperationIds.POP)).isInstanceOf(Pop.class);
        assertThat(FactoryOperations.make(OperationIds.PRINT)).isInstanceOf(Print.class);
        assertThat(FactoryOperations.make(OperationIds.PUSH)).isInstanceOf(Push.class);
        assertThat(FactoryOperations.make(OperationIds.SQRT)).isInstanceOf(Sqrt.class);
        assertThat(FactoryOperations.make(OperationIds.SUBTRACTION)).isInstanceOf(Subtraction.class);
        assertThat(FactoryOperations.make(OperationIds.SUM)).isInstanceOf(Sum.class);
    }
}
