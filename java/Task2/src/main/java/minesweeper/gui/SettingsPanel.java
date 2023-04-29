package minesweeper.gui;

import minesweeper.exception.MinesweeperException;
import javax.swing.*;

public class SettingsPanel{
    private static final int FIELD_WIDTH = 10;


    SettingsPanel(Context context) throws MinesweeperException {

        JTextField linesField = new JTextField(FIELD_WIDTH);
        linesField.setText(String.format("%d", context.getGame().getLinesLength()));
        JTextField columnField = new JTextField(FIELD_WIDTH);
        columnField.setText(String.format("%d", context.getGame().getColumnsLength()));
        JTextField minesField = new JTextField(FIELD_WIDTH);
        minesField.setText(String.format("%d", context.getGame().getMinesNumber()));




        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Line Length:"));
        myPanel.add(linesField);

        myPanel.add(new JLabel("Column Length:"));
        myPanel.add(columnField);
        myPanel.add(new JLabel("Mines number:"));
        myPanel.add(minesField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Settings", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int linesNum = Integer.parseInt(linesField.getText());
            int columnNum = Integer.parseInt(columnField.getText());
            int minesNum = Integer.parseInt(minesField.getText());

            context.getGame().changeSettings(linesNum, columnNum, minesNum);
        }
    }

}
