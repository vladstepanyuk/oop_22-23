package factory.storage;

import factory.components.Accessory;

public class AccessoryStorage extends Storage<Accessory> {
    public AccessoryStorage(int storageSize) {
        super(storageSize, Accessory.class);
    }
}
