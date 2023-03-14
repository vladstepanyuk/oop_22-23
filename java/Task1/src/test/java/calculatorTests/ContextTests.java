package calculatorTests;

import calculator.context.ProgramContext;
import calculator.exception.context.ContextException;
import calculator.exception.context.PeekException;
import calculator.exception.context.PopException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ProgramContext class tests")
public class ContextTests {
    ProgramContext context = new ProgramContext();

    @Test
    @DisplayName("Stack test")
    void pushPopTest() throws ContextException {
        context.push(1.1111);
        assertThat(context.peek()).isEqualTo(1.1111);

        assertThat(context.stackIsEmpty()).isFalse();
        context.pop();
        assertThat(context.stackIsEmpty()).isTrue();

        assertThatThrownBy(()->{
            ProgramContext context1 = new ProgramContext();
            context1.pop();
        })
                .isInstanceOf(PopException.class)
                .hasMessageContaining("stack is empty");

        assertThatThrownBy(()->{
            ProgramContext context1 = new ProgramContext();
            context1.peek();
        })
                .isInstanceOf(PeekException.class)
                .hasMessageContaining("stack is empty");
    }

    @Test
    @DisplayName("Map of variables test")
    void mapTest(){
        assertThat(context.containsVariable("a")).isFalse();
        context.putVariableValue("a", 1.1111);
        assertThat(context.containsVariable("a")).isTrue();
        assertThat(context.getVariableValue("a")).isEqualTo(1.1111);

    }

}
