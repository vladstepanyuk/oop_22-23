package calculatorTests;


import calculator.context.ProgramContext;
import calculator.exception.operation.*;
import calculator.exception.context.*;
import calculator.factory.FactoryOperations;
import calculator.operations.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Operations tests")
public class OperationsTests {
    ProgramContext context = new ProgramContext();
    @Test
    @DisplayName("Define operation test")
    void DefineTest() throws OperationException {

        assertThatThrownBy(()->{
            Define op = new Define();
            op.exec(context, new String[]{});
        })
                .isInstanceOf(ArgsNumberException.class)
                .hasMessageContaining("wrong args number");

        assertThatThrownBy(()->{
            Define op = new Define();
            op.exec(context, new String[]{"a", "a"});
        })
                .isInstanceOf(ArgsFormatException.class)
                .hasMessageContaining(2 +" argument has wrong class. must be "+ Number.class.getName());

        assertThatThrownBy(()->{
            Define op = new Define();
            op.exec(context, new String[]{"4", "4"});
        })
                .isInstanceOf(ArgsFormatException.class)
                .hasMessageContaining(1 +" argument has wrong class. must be "+ String.class.getName());

        Define op = new Define();
        op.exec(context, new String[]{"a", "4"});
        assertThat(context.getVariableValue("a")).isEqualTo(4);
    }

    @ParameterizedTest(name = "{index}  {0} test")
    @EnumSource(value = OperationIds.class, names = {"DIVISION", "MULTIPLY", "SUM", "SUBTRACTION", "SQRT"})
    void NumberOperationsTests(OperationIds id) throws OperationException, PeekException, PopException {
        assertThatThrownBy(()->{
            Operation op = FactoryOperations.make(id);
            op.exec(context, null);
        })
                .isInstanceOf(ExecuteException.class)
                .hasCause(new PopException("stack is empty"))
                .hasMessageContaining("unable to execute "+ id.getName());


        context.push(4);
        context.push(2);
        Operation op = FactoryOperations.make(id);
        op.exec(context, null);
        switch (id) {
            case DIVISION -> assertThat(context.peek()).isEqualTo(2.0 / 4.0);
            case MULTIPLY -> assertThat(context.peek()).isEqualTo(2.0 * 4.0);
            case SUM -> assertThat(context.peek()).isEqualTo(2.0 + 4.0);
            case SUBTRACTION -> assertThat(context.peek()).isEqualTo(2.0 - 4.0);
            case SQRT -> assertThat(context.peek()).isEqualTo(Math.sqrt(2.0));
        }

        while (!context.stackIsEmpty()) context.pop();

    }
}



