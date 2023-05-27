package minesweeper.gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenu {

    private record MenuActionListener(Context context) implements ActionListener {

        @Override
            public void actionPerformed(ActionEvent e) {
                var button = ((JMenuItem) e.getSource());

                try {
                    switch (button.getText()) {
                        case ("Новая игра") -> {
                            context.getGame().restartGame();
                            context.setNeedToRestart(false);
                            ((GamePanel) context.getWin().getCurPanel()).updatePanel();
                        }
                        case ("Настройки") -> context.getWin().openSettings();
                        case ("Рекорды") -> context.getWin().showRecordTable();
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    Menu(Context context){
        super("Меню");
        JMenuItem newGame = new JMenuItem("Новая игра");
        newGame.addActionListener(new MenuActionListener(context));
        add(newGame);

        JMenuItem settings = new JMenuItem("Настройки");
        settings.addActionListener(new MenuActionListener(context));
        add(settings);

        JMenuItem records = new JMenuItem("Рекорды");
        records.addActionListener(new MenuActionListener(context));
        add(records);

        addSeparator();

    }
}
