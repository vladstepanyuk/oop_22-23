package factory.ui;

import factory.Factory;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Slider extends JSlider {
    public enum SliderType {
        ACCESSORY,
        CAR_BODY,
        DEALER,
        ENGINE;
    }

    private static final int MIN_DELAY = 50;
    private static final int MAX_DELAY = 1000;
    private static final int STAND_DELAY = 100;

    private final Factory factory;
    private final SliderType type;

    public class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int value = (int)source.getValue();
                switch (type) {
                    case DEALER -> factory.setDealersDelay(value);
                    case ENGINE -> factory.setEngineSupplierDelay(value);
                    case CAR_BODY -> factory.setCarBodySupplierDelay(value);
                    case ACCESSORY -> factory.setAccessorySuppliersDelay(value);
                }
            }

        }
    }


    Slider(Factory factory, SliderType type) {
        super(JSlider.HORIZONTAL, MIN_DELAY, MAX_DELAY, STAND_DELAY);
        this.factory = factory;
        this.type = type;
        addChangeListener(new SliderListener());
        setPaintTicks(true);
        setPaintLabels(true);
        setMajorTickSpacing(100);
        setMinorTickSpacing(1);
//        sliderPanel.add(DelayAccessorySupplier);
    }

}
