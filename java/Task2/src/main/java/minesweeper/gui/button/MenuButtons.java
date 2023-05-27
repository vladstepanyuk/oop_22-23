package minesweeper.gui.button;

import minesweeper.gui.Context;

import javax.swing.*;

public class MenuButtons extends JButton {
//    private static final int FIELD_WIDTH = 10;


    private MenuButtons.MenuButtonsType type;

    public void setType(MenuButtons.MenuButtonsType type) {
        this.type = type;
    }

    public enum MenuButtonsType {
        FLAG("flag"),
        RESTART("restart"),
        SETTINGS("settings"),
        RECORD_TABLE("record table");

        private final String name;
        MenuButtonsType(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

    }
    public MenuButtons.MenuButtonsType getType() {
        return type;
    }
    public MenuButtons(MenuButtonsType t, Context context) {
        super();
        setType(t);
        addActionListener(new MenuButtonsListener(context));
        setText(t.getName());
    }
}
