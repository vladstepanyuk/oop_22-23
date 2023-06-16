package factory.storage;

import factory.components.Engine;

public class EngineStorage extends Storage<Engine> {
    public EngineStorage(int storageSize) {
        super(storageSize, Engine.class);
    }
}
