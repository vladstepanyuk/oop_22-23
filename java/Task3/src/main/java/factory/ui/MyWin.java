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

    private static final int DELAY = 100;
    private static final int PERIOD = 100;
    private static final int MAX_DELAY = 1000;
    private static final int STAND_DELAY = 100;
    private JTextField CarsNumber;
    private JTextField CarBodyNumber;
    private JTextField EngineNumber;
    private JTextField AccessoryNumber;
    private JTextField CarsProduced;
    private JPanel standardPanel;

    private JButton start_stop;

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
        SliderListener.setFactory(factory);


        JSlider DelayEngineSupplier = new JSlider(JSlider.HORIZONTAL,
                MIN_DELAY, MAX_DELAY, STAND_DELAY);
        DelayEngineSupplier.addChangeListener(SliderListener.ENGINE);
        DelayEngineSupplier.setPaintTicks(true);
        DelayEngineSupplier.setPaintLabels(true);
        DelayEngineSupplier.setMajorTickSpacing(100);
        DelayEngineSupplier.setMinorTickSpacing(1);

        sliderPanel.add(DelayEngineSupplier);

        mainPanel.add(sliderPanel);

        sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Accessory Supplier Delay"));

        JSlider DelayAccessorySupplier = new JSlider(JSlider.HORIZONTAL,
                MIN_DELAY, MAX_DELAY, STAND_DELAY);
        DelayAccessorySupplier.addChangeListener(SliderListener.ACCESSORY);
        DelayAccessorySupplier.setPaintTicks(true);
        DelayAccessorySupplier.setPaintLabels(true);
        DelayAccessorySupplier.setMajorTickSpacing(100);
        DelayAccessorySupplier.setMinorTickSpacing(1);
        sliderPanel.add(DelayAccessorySupplier);

        mainPanel.add(sliderPanel);
        sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Car body Supplier Delay"));

        JSlider CarBodySupplierDelay = new JSlider(JSlider.HORIZONTAL,
                MIN_DELAY, MAX_DELAY, STAND_DELAY);
        CarBodySupplierDelay.addChangeListener(SliderListener.CAR_BODY);

        CarBodySupplierDelay.setPaintTicks(true);
        CarBodySupplierDelay.setPaintLabels(true);
        CarBodySupplierDelay.setMajorTickSpacing(100);
        CarBodySupplierDelay.setMinorTickSpacing(1);
        sliderPanel.add(CarBodySupplierDelay);

        mainPanel.add(sliderPanel);
        sliderPanel = new JPanel(new GridLayout(0, 1));
        sliderPanel.add(new JLabel("Dealer  Delay"));

        JSlider DealerDelay = new JSlider(JSlider.HORIZONTAL,
                MIN_DELAY, MAX_DELAY, STAND_DELAY);
        DealerDelay.addChangeListener(SliderListener.DEALER);
        DealerDelay.setPaintTicks(true);
        DealerDelay.setPaintLabels(true);
        DealerDelay.setMajorTickSpacing(100);
        DealerDelay.setMinorTickSpacing(1);
        sliderPanel.add(DealerDelay);

        java.util.Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new Task(), MIN_DELAY, PERIOD);
        standardPanel.add(northPanel, BorderLayout.NORTH);
        standardPanel.add(mainPanel, BorderLayout.CENTER);
        add(standardPanel);

        mainPanel.add(sliderPanel);
    }


}