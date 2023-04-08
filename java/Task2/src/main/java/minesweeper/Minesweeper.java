package minesweeper;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import minesweeper.config.Configuration;
import minesweeper.exception.MinesweeperException;
import minesweeper.exception.field.IndexesOutOfBoundException;
import minesweeper.exception.game.UsedAllFlagsException;
import minesweeper.utils.Pair;

import java.util.Arrays;


public class Minesweeper {
    public static final int NOT_OPENED = 9;
    public static final int FLAG = 10;
    Configuration configuration;
    Field field;
    boolean isFirstClick = true;

    long startTime = 0;
    long endTime = 0;

    Pair[] recordsTable;

    private static final int recordsTableSize = 10;

    public final int[][] getMinesAround() {
        return minesAround;
    }

    int[][] minesAround;

    public Minesweeper() {
        recordsTable = new Pair[recordsTableSize];
        for (int i = 0; i < recordsTableSize; i++) {
            recordsTable[i] = new Pair();
        }
        configuration = new Configuration();
        minesAround = new int[getColumnsLength()][getLinesLength()];
        for (int k = 0; k < configuration.getColumnLength(); k++) {
            Arrays.fill(minesAround[k], NOT_OPENED);
        }
    }

    void generateField(int i, int j) {
        minesAround = new int[configuration.getColumnLength()][configuration.getLineLength()];
        for (int k = 0; k < configuration.getColumnLength(); k++) {
            Arrays.fill(minesAround[k], NOT_OPENED);
        }
        field = new Field(configuration, i, j);
        isFirstClick = false;
        startTime = System.currentTimeMillis();
    }

    public int click(int i, int j) throws MinesweeperException {
        if (j >= configuration.getLineLength() || i >= configuration.getColumnLength() || i < 0 || j < 0)
            throw new IndexesOutOfBoundException(i, j, configuration);
        if (isFirstClick) generateField(i, j);

        minesAround[i][j] = field.openCell(i, j);
        if (minesAround[i][j] == Field.BOOM) {
            endTime = System.currentTimeMillis();
            isFirstClick = true;
        }
        return minesAround[i][j];
    }

    public void restartGame() {
        startTime = 0;
        endTime = 0;
        minesAround = new int[configuration.getColumnLength()][configuration.getLineLength()];
        for (int k = 0; k < configuration.getColumnLength(); k++) {
            Arrays.fill(minesAround[k], NOT_OPENED);
        }
        isFirstClick = true;
    }

    public void changeSettings(int linesNumber, int columnsNumber, int minesNumber) throws MinesweeperException {
        configuration.setColumnLength(columnsNumber);
        configuration.setLineLength(linesNumber);
        configuration.setMinesNumber(minesNumber);
    }

    public boolean isFirstClick() {
        return isFirstClick;
    }

    public int getLinesLength() {
        if (field == null || isFirstClick) {
            return configuration.getLineLength();
        }
        return field.getConfiguration().getLineLength();
    }

    public int getColumnsLength() {
        if (field == null || isFirstClick) {
            return configuration.getColumnLength();
        }
        return field.getConfiguration().getColumnLength();
    }

    public int getMinesNumber() {
        if (field == null || isFirstClick) {
            return configuration.getMinesNumber();
        }
        return field.getConfiguration().getMinesNumber();
    }

    public boolean flag(int x, int y) throws MinesweeperException {
        if (isFirstClick) generateField(x, y);

        if (field.getUsedFlags() == field.configuration.getMinesNumber()) throw new UsedAllFlagsException();
        int res = field.flag(x, y);
        isFirstClick = res == 0;
        minesAround[x][y] = FLAG;
        if (isFirstClick) endTime = System.currentTimeMillis();
        return res == 0;
    }

    public void unFlag(int x, int y) throws MinesweeperException {
        if (isFirstClick) return;
        int res = field.unflag(x, y);
        minesAround[x][y] = NOT_OPENED;
    }

    public boolean correctXY(int x, int y) {
        return field.correctXY(x, y);
    }

    public boolean isOpen(int x, int y) {
        return field.isOpen(x, y);
    }

    public double getTime() {
        if (endTime >= startTime) return (endTime - startTime) * 0.001;
        else return (System.currentTimeMillis() - startTime) * 0.001;
    }

    public void updateRecord(Pair pair) {
        if (recordsTable[recordsTable.length - 1].getSecond() != Pair.EMPTY && recordsTable[recordsTable.length - 1].getSecond() <= pair.getSecond())
            return;
        for (int i = 0; i < recordsTable.length; i++) {
            if (recordsTable[i].getSecond() > pair.getSecond()) {
                for (int j = recordsTable.length - 1; j > i; j--) {

                    recordsTable[j].swap(recordsTable[j - 1]);
                }
                recordsTable[i] = pair;
                break;
            } else if (recordsTable[i].getSecond() == Pair.EMPTY) {
                recordsTable[i] = pair;
                break;
            }
        }
    }

    public final Pair[] getRecordsTable() {
        return recordsTable;
    }
}
