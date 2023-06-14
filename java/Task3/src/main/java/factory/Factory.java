package factory;

import factory.storage.AccessoryStorage;
import factory.storage.CarBodyStorage;
import factory.storage.CarStorage;
import factory.storage.EngineStorage;
import factory.suppliers.AccessorySupplier;
import factory.suppliers.CarBodySupplier;
import factory.suppliers.EngineSupplier;
import factory.suppliers.Supplier;
import factory.workers.WorkShop;

import java.util.ArrayList;
import java.util.function.UnaryOperator;


public class Factory {
    private ArrayList<AccessorySupplier> accessorySuppliers;
    private CarBodySupplier carBodySupplier;
    private EngineSupplier engineSupplier;

    private Context context;

    private final ArrayList<Dealer> carDealers;

    private final AccessoryStorage accessoryStorage;
    private final CarBodyStorage carBodyStorage;
    private final EngineStorage engineStorage;
    private final CarStorage carStorage;
    private WorkShop workersThreadPool;

    private Controller controller;


//    private static final int STANDARD_POOL_SIZE = 3;


    public Factory(Context context) {
        accessoryStorage = new AccessoryStorage(context.getStorageAccessorySize());
        carBodyStorage = new CarBodyStorage(context.getStorageBodySize());
        engineStorage = new EngineStorage(context.getStorageMotorSize());


        accessorySuppliers = new ArrayList<>(context.getAccessorySuppliers());
        for (int i = 0; i < context.getAccessorySuppliers(); i++)
            accessorySuppliers.add(new AccessorySupplier(accessoryStorage));
        carStorage = new CarStorage(context.getStorageAutoSize());
        workersThreadPool = new WorkShop(context.getWorkers(), carStorage, accessoryStorage, engineStorage, carBodyStorage);


        carDealers = new ArrayList<>(context.getDealers());
        for (int i = 0; i < context.getDealers(); i++)
            carDealers.add(new Dealer(carStorage));



    }

    public void start() {
        UnaryOperator<AccessorySupplier> uo = s -> new AccessorySupplier(accessoryStorage);
        accessorySuppliers.replaceAll(uo);


        carBodySupplier = new CarBodySupplier(carBodyStorage);
        engineSupplier = new EngineSupplier(engineStorage);
        controller = new Controller(workersThreadPool, carStorage);


        UnaryOperator<Dealer> uo1 = s -> new Dealer(carStorage);
        carDealers.replaceAll(uo1);



        for (var sup : accessorySuppliers)
            sup.start();


        carBodySupplier.start();
        engineSupplier.start();
        controller.start();


        for (var dealer : carDealers)
            dealer.start();

    }

    public void stop() {
        carBodySupplier.interrupt();
        engineSupplier.interrupt();
        for (var sup : accessorySuppliers) {
            sup.interrupt();
        }
        for (var sup : carDealers) {
            sup.interrupt();
        }
    }

    public AccessoryStorage getAccessoryStorage() {
        return accessoryStorage;
    }

    public CarBodyStorage getCarBodyStorage() {
        return carBodyStorage;
    }

    public EngineStorage getEngineStorage() {
        return engineStorage;
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
        Dealer.setDelay(delay);
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
