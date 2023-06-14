package factory.suppliers;

import factory.products.CarBody;
import factory.storage.CarBodyStorage;

public class CarBodySupplier extends Supplier<CarBody> {

    public CarBodySupplier(CarBodyStorage storage) {
        super(CarBody.class, storage);
    }
}
