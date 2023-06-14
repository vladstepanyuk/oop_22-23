package factory.storage;

import factory.products.Engine;

public class EngineStorage extends Storage<Engine> {
    public EngineStorage(int storageSize) {
        super(storageSize, Engine.class);
    }
}
