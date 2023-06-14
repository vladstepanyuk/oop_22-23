package factory.suppliers;

import factory.products.Accessory;
import factory.storage.AccessoryStorage;

public class AccessorySupplier extends Supplier<Accessory> {

    public AccessorySupplier(AccessoryStorage storage) {
        super(Accessory.class, storage);
    }
}
