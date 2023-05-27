package minesweeper.gui;

import minesweeper.gui.button.FieldButton;

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

public class GamePanel extends JPanel {

    private static final int DELAY = 100;
    private static final int PERIOD = 100;
    private static final int FIELD_WIDTH = 10;

    private final JPanel minesPanel;
    private final Context context;
    private FieldButton[][] buttons;
    private static final HashMap<Integer, BufferedImage> numberImageMap;

    private JPanel curPanel;

    private final JTextField timerField;

    static {
        try {
            numberImageMap = new HashMap<>();
            for (int i = 0; i <= 8; i++) {
                numberImageMap.put(i, ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource(i + ".png"))));
            }
            numberImageMap.put(FLAG, ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("flag.png"))));
            numberImageMap.put(BOOM, ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("mine.png"))));
            numberImageMap.put(NOT_OPENED, ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("notOpen.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public GamePanel(Context context) {
        setLayout(new BorderLayout());
        this.context = context;
        buttons = new FieldButton[context.getGame().getColumnsLength()][context.getGame().getLinesLength()];

        JPanel jp = new JPanel();
        minesPanel = jp;
        jp.setLayout(new GridLayout(0, context.getGame().getLinesLength()));
        JButton jb;
        int[][] field = context.getGame().getMinesAround();
        for (int i = 0; i < context.getGame().getColumnsLength(); i++) {
            for (int j = 0; j < context.getGame().getLinesLength(); j++) {
                buttons[i][j] = new FieldButton(i, j, context);
                jp.add(buttons[i][j]);
            }
        }

        add(jp);

        String[] items = {
                "Restart",
                "Record table",
                "Settings"
        };

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new Menu(context));
        context.getWin().setJMenuBar(menuBar);
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        JTextField timer = new JTextField(FIELD_WIDTH);
        timer.setText("0");
        timer.setEditable(false);
        timer.setMaximumSize(new Dimension(10, 10));
        timer.setMinimumSize(new Dimension(10, 10));

        timerField = timer;

        northPanel.add(timer);
        add(northPanel, BorderLayout.NORTH);

        curPanel = minesPanel;

        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new CurrentTask(), DELAY, PERIOD);

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
        buttons = new FieldButton[context.getGame().getColumnsLength()][context.getGame().getLinesLength()];
        for (int i = 0; i < context.getGame().getColumnsLength(); i++) {
            for (int j = 0; j < context.getGame().getLinesLength(); j++) {
                buttons[i][j] = new FieldButton(i, j, context);
                minesPanel.add(buttons[i][j]);
            }
        }
        updateUI();
    }

    class CurrentTask extends TimerTask {
        @Override
        public void run() {
            timerField.setText("" + (int) context.getGame().getTime());
        }
    }
}