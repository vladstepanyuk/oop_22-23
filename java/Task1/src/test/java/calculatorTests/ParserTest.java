package calculatorTests;

import calculator.exception.operation.NoSuchOperationException;
import calculator.exception.parser.ArgsNumberException;
import calculator.exception.parser.ParserException;
import calculator.factory.FactoryOperations;
import calculator.operations.OperationIds;
import calculator.utils.Command;
import calculator.utils.CommandParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParserTest {

    static {
        try {
            FileInputStream in = new FileInputStream("src/main/resources/configuration.txt");
            FactoryOperations.getResourceAsStream(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @DisplayName("Parsing invalid operation test")
    @Test
    void InvalidOperationTest() {
        String invalidOperation = "jkrgbjg kglrkgn";
        assertThatThrownBy(()->{
            CommandParser.pars(invalidOperation);
        })
                .isInstanceOf(ParserException.class)
                .hasCause(new NoSuchOperationException("jkrgbjg"))
                .hasMessageContaining("parser error");
    }

    @DisplayName("Parsing Push operation test")
    @Test
    void PushTest() throws ParserException {
        String pushString = "PUSH a";
        Command comm = CommandParser.pars(pushString);
        assertThat(comm.getArgs()).isEqualTo(new String[]{"a"});
        assertThat(comm.getId()).isEqualTo(OperationIds.PUSH);

        assertThatThrownBy(() ->{
            CommandParser.pars("PUSH bhbfdj;h dlghdkjgh");
        })
                .isInstanceOf(ArgsNumberException.class)
                .hasMessageContaining("wrong args number");

        assertThatThrownBy(() ->{
            CommandParser.pars("PUSH");
        })
                .isInstanceOf(ArgsNumberException.class)
                .hasMessageContaining("wrong args number");

    }

    @DisplayName("Parsing Define operation test")
    @Test
    void DefineTest() throws ParserException {
        String defineString = "DEFINE a 4";
        Command comm = CommandParser.pars(defineString);
        assertThat(comm.getArgs()).isEqualTo(new String[]{"a", "4"});
        assertThat(comm.getId()).isEqualTo(OperationIds.DEFINE);

        assertThatThrownBy(() ->{
            CommandParser.pars("DEFINE a");
        })
                .isInstanceOf(ArgsNumberException.class)
                .hasMessageContaining("wrong args number");

        assertThatThrownBy(() ->{
            CommandParser.pars("DEFINE a a a");
        })
                .isInstanceOf(ArgsNumberException.class)
                .hasMessageContaining("wrong args number");
    }
}
