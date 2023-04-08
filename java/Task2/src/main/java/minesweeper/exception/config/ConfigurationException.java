package minesweeper.exception.config;

import minesweeper.exception.MinesweeperException;

public class ConfigurationException extends MinesweeperException {
    public ConfigurationException(String message, Throwable cause){
        super(message, cause);
    }
    public ConfigurationException(String message){
        super(message);
    }
    public ConfigurationException(){
        super();
    }
}
