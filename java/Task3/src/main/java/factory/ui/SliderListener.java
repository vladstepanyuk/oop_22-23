package factory.ui;

import factory.Factory;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public enum SliderListener implements ChangeListener {
    ACCESSORY,
    CAR_BODY,
    DEALER,
    ENGINE;
    private static Factory factory;

    public static void setFactory(Factory factory1) {
        factory = factory1;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int value = (int)source.getValue();
            switch (this) {
                case DEALER -> factory.setDealersDelay(value);
                case ENGINE -> factory.setEngineSupplierDelay(value);
                case CAR_BODY -> factory.setCarBodySupplierDelay(value);
                case ACCESSORY -> factory.setAccessorySuppliersDelay(value);
            }
        }

    }
}
