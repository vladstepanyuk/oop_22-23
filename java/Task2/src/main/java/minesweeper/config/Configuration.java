package minesweeper.config;

import minesweeper.exception.config.ConfigurationException;

public class Configuration {
    private int columnLength = 9;
    private int lineLength = 9;
    private int minesNumber = 10;




    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) throws ConfigurationException {
        if (lineLength <= 0) throw new ConfigurationException("wrong lines number");
        this.lineLength = lineLength;
    }


    public int getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(int columnLength) throws ConfigurationException {
        if (columnLength <= 0) throw new ConfigurationException("wrong columns number");
        this.columnLength = columnLength;
    }



    public int getMinesNumber() {
        return minesNumber;
    }

    public void setMinesNumber(int minesNumber) throws ConfigurationException {
        if (minesNumber < 0 || minesNumber >= lineLength * columnLength) throw new ConfigurationException("wrong mines number");

        this.minesNumber = minesNumber;
    }


}
