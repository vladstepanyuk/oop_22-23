package factory.suppliers;

import factory.components.Accessory;
import factory.storage.AccessoryStorage;
import factory.utils.Flag;

public class AccessorySupplier extends Supplier<Accessory> {

    public AccessorySupplier(AccessoryStorage storage, Flag isRunning, Object monitor) {
        super(Accessory.class, storage, monitor, isRunning);
    }
}
