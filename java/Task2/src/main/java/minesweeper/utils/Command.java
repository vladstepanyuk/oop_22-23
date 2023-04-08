package minesweeper.utils;

import java.util.HashMap;

public class Command {
    public enum CommandIds {
        RESTART(0),
        CLICK(2),
        SET_SETTINGS(3),
        FLAG(2),
        UNFLAG(2),

        SHOW_RECORDS(0),
        EXIT(0);




        private final int argsNum;

        CommandIds(int argsNum) {
            this.argsNum = argsNum;
        }
        public int getArgsNum() {
            return argsNum;
        }


    }

    CommandIds id;
    int[] args;


    public Command(CommandIds id, int[] args) {
        this.id = id;
        this.args = args;
    }

    public CommandIds getId() {
        return id;
    }


    public int[] getArgs() {
        return args;
    }

}
