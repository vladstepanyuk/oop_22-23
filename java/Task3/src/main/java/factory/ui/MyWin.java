package factory.ui;

import factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class MyWin extends JFrame {
    private final Factory factory;
    private static final int MIN_DELAY = 50;
    private static final int PERIOD = 100;
    private final JTextField CarsNumber;
    private final JTextField CarBodyNumber;
    private final JTextField EngineNumber;
    private final JTextField AccessoryNumber;
    private final JTextField CarsProduced;
    private final JPanel standardPanel;

    private final JButton start_stop;

    private class Task extends TimerTask {
        @Override
        public void run() {
            CarsProduced.setText("Cars  produced number: " + factory.getTotalCarProduced());
            CarsNumber.setText("Cars number: " + factory.getCarsNumber());
            EngineNumber.setText("Engine number: " + factory.getEnginesNumber());
            CarBodyNumber.setText("Car body number: " + factory.getCarBodyNumber());
            AccessoryNumber.setText("Accessory number: " + factory.getAccessoryNumber());

        }
    }

    private class StartStopButtonListener implements ActionListener {
        boolean isRunning = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isRunning) {
                start_stop.setText("stop");
                start_stop.setBackground(Color.RED);
                factory.start();
            } else {
                start_stop.setText("start");
                start_stop.setBackground(Color.GREEN);
                factory.stop();
            }
            isRunning = !isRunning;
            standardPanel.updateUI();
        }
    }

    public MyWin(Factory factory) {
        this.factory = factory;

        standardPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel();
        start_stop = new JButton();
        start_stop.setText("start");
        start_stop.setBackground(Color.GREEN);
        start_stop.addActionListener(new StartStopButtonListener());
        northPanel.add(start_stop);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        add(mainPanel);
        CarsNumber = new JTextField();
        CarsNumber.setText("Cars number: " + factory.getCarsNumber());
        CarsNumber.setEditable(false);
        CarsNumber.setMaximumSize(new Dimension(10, 10));
        CarsNumber.setMinimumSize(new Dimension(10, 10));
        mainPanel.add(CarsNumber);
        CarsProduced = new JTextField();
        CarsProduced.setText("Cars  produced number: " + factory.getTotalCarProduced());
        CarsProduced.setEditable(false);
        CarsProduced.setMaximumSize(new Dimension(10, 10));
        CarsProduced.setMinimumSize(new Dimension(10, 10));
        mainPanel.add(CarsProduced);
        CarBodyNumber = new JTextField();
        CarBodyNumber.setText("Car body number: " + factory.getCarBodyNumber());
        CarBodyNumber.setEditable(false);
        CarBodyNumber.setMaximumSize(new Dimension(10, 10));
        CarBodyNumber.setMinimumSize(new Dimension(10, 10));
        mainPanel.add(CarBodyNumber);
        AccessoryNumber = new JTextField();
        AccessoryNumber.setText("Accessory number: " + factory.getAccessoryNumber());
        AccessoryNumber.setEditable(false);
        AccessoryNumber.setMaximumSize(new Dimension(10, 10));
        AccessoryNumber.setMinimumSize(new Dimension(10, 10));
        mainPanel.add(AccessoryNumber);
        EngineNumber = new JTextField();
        EngineNumber.setText("Engine number: " + factory.getEnginesNumber());
        EngineNumber.setEditable(false);
        EngineNumber.setMaximumSize(new Dimension(10, 10));
        EngineNumber.setMinimumSize(new Dimension(10, 10));
        mainPanel.add(EngineNumber);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Engine Supplier Delay"));
        sliderPanel.add(new Slider(factory, Slider.SliderType.ENGINE));
        mainPanel.add(sliderPanel);

        sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Accessory Supplier Delay"));
        sliderPanel.add(new Slider(factory, Slider.SliderType.ACCESSORY));
        mainPanel.add(sliderPanel);

        sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Car body Supplier Delay"));
        sliderPanel.add(new Slider(factory, Slider.SliderType.CAR_BODY));
        mainPanel.add(sliderPanel);

        sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Dealer  Delay"));
        sliderPanel.add(new Slider(factory, Slider.SliderType.DEALER));

        java.util.Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new Task(), MIN_DELAY, PERIOD);
        standardPanel.add(northPanel, BorderLayout.NORTH);
        standardPanel.add(mainPanel, BorderLayout.CENTER);
        add(standardPanel);

        mainPanel.add(sliderPanel);
    }


}