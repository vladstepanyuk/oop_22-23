package minesweeper.gui;

import minesweeper.Minesweeper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.TimerTask;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;

import static minesweeper.Field.BOOM;
import static minesweeper.Minesweeper.FLAG;
import static minesweeper.Minesweeper.NOT_OPENED;

public class MyPanel extends JPanel {

    private static final int DELAY = 100;
    private static final int PERIOD = 100;

    JPanel minesPanel;
    Context context;
    Listener listener;
    MyButton[][] buttons;
    static HashMap<Integer, BufferedImage> numberImageMap;

    JPanel curPanel;
    JPanel prevPanel;

    JTextField timerField;
    Timer timer;

    static {
        try {
            numberImageMap = new HashMap<>();
            for (int i = 0; i <= 8; i++) {
                numberImageMap.put(i, ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource(  i + ".png"))));
            }
            numberImageMap.put(FLAG, ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("flag.png"))));
            numberImageMap.put(BOOM, ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("mine.png"))));
            numberImageMap.put(NOT_OPENED, ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("notOpen.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public MyPanel(Context context, Listener listener) {
        setLayout(new BorderLayout());
        this.context = context;
        buttons = new MyButton[context.getGame().getColumnsLength()][context.getGame().getLinesLength()];

        this.listener = listener;
        JPanel jp = new JPanel();
        minesPanel = jp;
        jp.setLayout(new GridLayout(0, context.getGame().getLinesLength()));
        JButton jb;
        int[][] field = context.getGame().getMinesAround();
        for (int i = 0; i < context.getGame().getColumnsLength(); i++) {
            for (int j = 0; j < context.getGame().getLinesLength(); j++) {
                buttons[i][j] = new MyButton(i, j);
                buttons[i][j].addActionListener(listener);
                jp.add(buttons[i][j]);
            }
        }

        add(jp);
        jb = new JButton("flag");
        jb.addActionListener(listener);
        add(jb, BorderLayout.SOUTH);

        jb = new JButton("record table");
        jb.addActionListener(listener);
        add(jb, BorderLayout.EAST);

        jb = new JButton("restart");
        jb.addActionListener(listener);
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(jb);
        JTextField timer = new JTextField();
        timer.setText("0");
        timer.setEditable(false);
        timer.setMaximumSize(new Dimension(10, 10));
        timer.setMinimumSize(new Dimension(10, 10));

        timerField = timer;

        northPanel.add(timer);
        add(northPanel, BorderLayout.NORTH);
        jb = new JButton("settings");
        jb.addActionListener(listener);
        add(jb, BorderLayout.WEST);
        curPanel = minesPanel;

        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new CurrentTask(), DELAY, PERIOD);

    }

    public void updateView() {
        int[][] field = context.getGame().getMinesAround();
        for (int i = 0; i < context.getGame().getColumnsLength(); i++) {
            for (int j = 0; j < context.getGame().getLinesLength(); j++) {
                if (field[i][j] != NOT_OPENED && field[i][j] != FLAG) {
                    buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
                    buttons[i][j].setContentAreaFilled(false);
                    buttons[i][j].setIcon(new ImageIcon(numberImageMap.get(field[i][j])));
                } else if (field[i][j] == FLAG) {
                    buttons[i][j].setIcon(new ImageIcon(numberImageMap.get(field[i][j])));
                } else {
                    buttons[i][j].setIcon(null);
                }
            }
        }
    }

    public void updatePanel() {
        minesPanel.removeAll();
        minesPanel.setLayout(new GridLayout(0, context.getGame().getLinesLength()));
        int[][] field = context.getGame().getMinesAround();
        buttons = new MyButton[context.getGame().getColumnsLength()][context.getGame().getLinesLength()];
        for (int i = 0; i < context.getGame().getColumnsLength(); i++) {
            for (int j = 0; j < context.getGame().getLinesLength(); j++) {
                buttons[i][j] = new MyButton(i, j);
                buttons[i][j].addActionListener(listener);
                minesPanel.add(buttons[i][j]);
            }
        }
        updateUI();
    }


    public void goBack() {
        curPanel = prevPanel;
        removeAll();
        add(curPanel);
        updateUI();
    }

    class CurrentTask extends TimerTask {
        @Override
        public void run() {
            timerField.setText(""+(int)context.getGame().getTime());
        }
    }
}