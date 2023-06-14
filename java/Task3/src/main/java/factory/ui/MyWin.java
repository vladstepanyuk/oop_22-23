package factory.ui;

import factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class MyWin extends JFrame {
    private final Factory factory;
    private static final int MIN_DELAY = 50;

    private static final int DELAY = 100;
    private static final int PERIOD = 100;
    private static final int MAX_DELAY = 1000;
    private static final int STAND_DELAY = 100;
    JTextField CarsNumber;
    JTextField CarBodyNumber;
    JTextField EngineNumber;
    JTextField AccessoryNumber;
    JTextField CarsProduced;

    private class Task extends TimerTask {
        @Override
        public void run() {
            CarsProduced.setText("Cars  produced number: " + factory.getTotalCarProduced());
            CarsNumber.setText("Cars number: " + factory.getCarsNumber());
            EngineNumber.setText("Engine number: " + factory.getEnginesNumber());
            CarBodyNumber.setText("Car body number: " + factory.getCarBodyNumber());
            AccessoryNumber.setText("Accessory number: " + factory.getEnginesNumber());

        }
    }

    public MyWin(Factory factory) {
        this.factory = factory;

        JPanel standardPanel = new JPanel(new GridLayout(0, 1));
        add(standardPanel);
        CarsNumber = new JTextField();
        CarsNumber.setText("Cars number: " + factory.getCarsNumber());
        CarsNumber.setEditable(false);
        CarsNumber.setMaximumSize(new Dimension(10, 10));
        CarsNumber.setMinimumSize(new Dimension(10, 10));
        CarsProduced = new JTextField();
        CarsProduced.setText("Cars  produced number: " + factory.getTotalCarProduced());
        CarsProduced.setEditable(false);
        CarsProduced.setMaximumSize(new Dimension(10, 10));
        CarsProduced.setMinimumSize(new Dimension(10, 10));
        standardPanel.add(CarsProduced);
        CarBodyNumber = new JTextField();
        CarBodyNumber.setText("Car body number: " + factory.getCarBodyNumber());
        CarBodyNumber.setEditable(false);
        CarBodyNumber.setMaximumSize(new Dimension(10, 10));
        CarBodyNumber.setMinimumSize(new Dimension(10, 10));
        standardPanel.add(CarBodyNumber);
        AccessoryNumber = new JTextField();
        AccessoryNumber.setText("Accessory number: " + factory.getAccessoryNumber());
        AccessoryNumber.setEditable(false);
        AccessoryNumber.setMaximumSize(new Dimension(10, 10));
        AccessoryNumber.setMinimumSize(new Dimension(10, 10));
        standardPanel.add(AccessoryNumber);
        EngineNumber = new JTextField();
        EngineNumber.setText("Engine number: " + factory.getEnginesNumber());
        EngineNumber.setEditable(false);
        EngineNumber.setMaximumSize(new Dimension(10, 10));
        EngineNumber.setMinimumSize(new Dimension(10, 10));
        standardPanel.add(EngineNumber);
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

        standardPanel.add(sliderPanel);

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

        standardPanel.add(sliderPanel);
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

        standardPanel.add(sliderPanel);
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
        factory.start();

        standardPanel.add(sliderPanel);
    }


}