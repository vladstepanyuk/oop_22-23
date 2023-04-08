package minesweeper.exception.parser;

public class UnknownCommandException extends ParserException{

    public UnknownCommandException(String name){
        super("unknown command: "+name);
    }
}
