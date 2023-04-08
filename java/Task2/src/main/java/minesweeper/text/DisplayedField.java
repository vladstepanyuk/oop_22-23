package minesweeper.text;

import minesweeper.Field;

import java.util.Arrays;

public class DisplayedField {

    final char emptyCell = 9643;
    final char BOOM = 0x80;
    final char flag = '☼';
    char[][] displayedField;

    public DisplayedField(int lineLength, int columnLength) {
        displayedField = new char[columnLength][lineLength];

        for (int i = 0; i < columnLength; i++) {
            Arrays.fill(displayedField[i], emptyCell);
        }


    }

    public void print() {
        for (char[] chars : displayedField) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.print('\n');
        }
    }

    public void setCell(int i, int j, int value) {
        if (value >= 0) displayedField[i][j] = (char) ((char) value + '0');
        if (value == Field.BOOM) displayedField[i][j] = BOOM;
    }

    public void setFlag(int x, int y) {
        displayedField[x][y] = flag;
    }

    public void deleteFlag(int x, int y) {
        displayedField[x][y] = emptyCell;
    }
}
