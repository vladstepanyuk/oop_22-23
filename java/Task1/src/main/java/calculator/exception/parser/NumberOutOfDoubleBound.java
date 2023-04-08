package calculator.exception.parser;

import calculator.utils.CommandParser;

public class NumberOutOfDoubleBound extends ParserException {
    public NumberOutOfDoubleBound(int i) {
        super(i + " number to large. Max size: " + CommandParser.MAX_SIZE);
    }
}
