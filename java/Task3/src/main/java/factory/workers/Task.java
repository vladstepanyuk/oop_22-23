package factory.workers;

import factory.products.Accessory;
import factory.products.Car;
import factory.products.CarBody;
import factory.products.Engine;
import factory.storage.*;
import factory.utils.IDManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Runnable {
    private final CarBodyStorage carBodyStorage;
    private final EngineStorage engineStorage;
    private final AccessoryStorage accessoryStorage;
    private final Logger logger;

    private final CarStorage carStorage;

    private final AtomicInteger integer;

    public Task(CarBodyStorage carBodyStorage, EngineStorage engineStorage, AccessoryStorage accessoryStorage, Logger logger, CarStorage carStorage, AtomicInteger integer) {
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.logger = logger;
        this.carStorage = carStorage;
        this.integer = integer;
    }

    @Override
    public void run() {
        Car product = null;
        try {
            product = Car.class.getConstructor(Integer.TYPE, CarBody.class, Engine.class, Accessory.class).newInstance(IDManager.getUniqueID(),
                    carBodyStorage.get(), engineStorage.get(), accessoryStorage.get());
        } catch (Exception ignored) {
        }
        integer.incrementAndGet();
        carStorage.supply(product);
        logger.info("make Auto <" + product.getID()+"> (Body: <"+product.getCarBodyID() + ">, Engine: <" +
                product.getEngineID()+ ">, Accessory: <" +
                product.getAccessoryID() +">)");

    }
}
