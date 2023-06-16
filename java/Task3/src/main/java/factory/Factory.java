package factory;

import factory.storage.AccessoryStorage;
import factory.storage.CarBodyStorage;
import factory.storage.CarStorage;
import factory.storage.EngineStorage;
import factory.suppliers.AccessorySupplier;
import factory.suppliers.CarBodySupplier;
import factory.suppliers.EngineSupplier;
import factory.utils.Flag;
import factory.workers.WorkShop;

import java.util.ArrayList;


public class Factory {
    private final ArrayList<AccessorySupplier> accessorySuppliers;
    private final CarBodySupplier carBodySupplier;
    private final EngineSupplier engineSupplier;


    private final ArrayList<Dealer> carDealers;

    private final AccessoryStorage accessoryStorage;
    private final CarBodyStorage carBodyStorage;
    private final EngineStorage engineStorage;
    private final CarStorage carStorage;
    private final WorkShop workersThreadPool;

    private final Object monitor = new Object();

    private final Flag isRunning = new Flag();


    public Factory(Context context) {
        accessoryStorage = new AccessoryStorage(context.getStorageAccessorySize());
        carBodyStorage = new CarBodyStorage(context.getStorageBodySize());
        engineStorage = new EngineStorage(context.getStorageMotorSize());


        accessorySuppliers = new ArrayList<>(context.getAccessorySuppliers());
        for (int i = 0; i < context.getAccessorySuppliers(); i++)
            accessorySuppliers.add(new AccessorySupplier(accessoryStorage, isRunning, monitor));
        carStorage = new CarStorage(context.getStorageAutoSize());
        workersThreadPool = new WorkShop(context.getWorkers(), carStorage, accessoryStorage, engineStorage, carBodyStorage);


        carDealers = new ArrayList<>(context.getDealers());
        for (int i = 0; i < context.getDealers(); i++)
            carDealers.add(new Dealer(carStorage, isRunning, monitor));


        carBodySupplier = new CarBodySupplier(carBodyStorage, isRunning, monitor);
        engineSupplier = new EngineSupplier(engineStorage, isRunning, monitor);
        Controller controller = new Controller(workersThreadPool, carStorage, isRunning, monitor);
        for (var sup : accessorySuppliers)
            sup.start();


        carBodySupplier.start();
        engineSupplier.start();
        controller.start();


        for (var dealer : carDealers)
            dealer.start();


    }

    public void start() {
        isRunning.changeState();


        synchronized (monitor) {
            monitor.notifyAll();
        }
    }

    public void stop() {
        isRunning.changeState();
    }



    public void setEngineSupplierDelay(int delay) {
        engineSupplier.setDelay(delay);
    }

    public void setCarBodySupplierDelay(int delay) {
        carBodySupplier.setDelay(delay);
    }

    public void setAccessorySuppliersDelay(int delay) {
        for (var sup : accessorySuppliers) {
            sup.setDelay(delay);
        }
    }

    public void setDealersDelay(int delay) {
        for (var carDealer : carDealers) {
            carDealer.setDelay(delay);
        }
    }

    public int getTotalCarProduced() {
        return workersThreadPool.getTotalCarProduced();
    }


    public int getCarsNumber() {
        return carStorage.getProductsNumber();
    }

    public int getEnginesNumber() {
        return engineStorage.getProductsNumber();
    }

    public int getAccessoryNumber() {
        return accessoryStorage.getProductsNumber();
    }

    public int getCarBodyNumber() {
        return carBodyStorage.getProductsNumber();
    }
}
