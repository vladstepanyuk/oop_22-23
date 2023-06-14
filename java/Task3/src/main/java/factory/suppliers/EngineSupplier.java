package factory.suppliers;

import factory.products.Engine;
import factory.storage.EngineStorage;
import factory.storage.Storage;

public class EngineSupplier extends Supplier<Engine> {

    public EngineSupplier(EngineStorage storage) {
        super(Engine.class, storage);
    }
}
