package factory.suppliers;

import factory.components.CarBody;
import factory.storage.CarBodyStorage;
import factory.utils.Flag;

public class CarBodySupplier extends Supplier<CarBody> {

    public CarBodySupplier(CarBodyStorage storage, Flag isRunning, Object monitor) {
        super(CarBody.class, storage, monitor, isRunning);
    }
}
