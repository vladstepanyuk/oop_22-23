package factory.workers;


import factory.storage.AccessoryStorage;
import factory.storage.CarBodyStorage;
import factory.storage.CarStorage;
import factory.storage.EngineStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class WorkShop {
    private final CarBodyStorage carBodyStorage;
    private final EngineStorage engineStorage;
    private final AccessoryStorage accessoryStorage;
    private final Logger logger;
    private final AtomicInteger totalCarsProduced = new AtomicInteger(0);

    private final CarStorage carStorage;

    private final ExecutorService workersThreadPool;

    public WorkShop(int size, CarStorage carStorage, AccessoryStorage accessoryStorage, EngineStorage engineStorage, CarBodyStorage carBodyStorage) {
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.logger = LogManager.getLogger("WorkShop");
        this.carStorage = carStorage;
        workersThreadPool = Executors.newFixedThreadPool(size);
    }


    public int getTotalCarProduced() {
        return totalCarsProduced.get();
    }


    public void submitTask() {
        workersThreadPool.execute(new Task(carBodyStorage, engineStorage, accessoryStorage, logger, carStorage, totalCarsProduced));
    }
}
