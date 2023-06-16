package factory.suppliers;

import factory.components.Engine;
import factory.storage.EngineStorage;
import factory.utils.Flag;

public class EngineSupplier extends Supplier<Engine> {

    public EngineSupplier(EngineStorage storage, Flag isRunning, Object monitor) {
        super(Engine.class, storage, monitor, isRunning);
    }
}
