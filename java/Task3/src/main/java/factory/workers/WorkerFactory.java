//package factory.workers;
//
//import factory.products.Accessory;
//import factory.products.Car;
//import factory.products.CarBody;
//import factory.products.Engine;
//import factory.storage.AccessoryStorage;
//import factory.storage.CarBodyStorage;
//import factory.storage.CarStorage;
//import factory.storage.EngineStorage;
//import factory.utils.IDManager;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class WorkerFactory implements ThreadFactory {
//    private final CarStorage carStorage;
//    private final AccessoryStorage accessoryStorage;
//    private final CarBodyStorage carBodyStorage;
//    private final EngineStorage engineStorage;
//
//
//    private static class Worker extends Thread {
//        static private final AtomicInteger idPool;
//        final Logger logger;
//
//        private final CarStorage carStorage;
//        private final AccessoryStorage accessoryStorage;
//        private final CarBodyStorage carBodyStorage;
//        private final EngineStorage engineStorage;
//
//        static {
//            idPool = new AtomicInteger(0);
//        }
//        public Worker(CarStorage carStorage, AccessoryStorage accessoryStorage, CarBodyStorage carBodyStorage, EngineStorage engineStorage) {
//            this.carStorage = carStorage;
//            this.accessoryStorage = accessoryStorage;
//            this.carBodyStorage = carBodyStorage;
//            this.engineStorage = engineStorage;
//            logger =  LogManager.getLogger("Worker N" + idPool.getAndIncrement());
//
//        }
//
//        @Override
//        public void run(){
//            logger.info("trying to make a car");
//            Car product = null;
//            try {
//                product = Car.class.getConstructor(Integer.TYPE, CarBody.class, Engine.class, Accessory.class).newInstance(IDManager.getUniqueID(),
//                        carBodyStorage.get(), engineStorage.get(), accessoryStorage.get());
//            } catch (Exception ignored) {
//            }
//            carStorage.supply(product);
//            logger.info("make Auto <" + product.getID()+"> (Body: <"+product.getCarBodyID() + ">, Engine: <" +
//                    product.getEngineID()+ ">, Accessory: <" +
//                    product.getAccessoryID() +">)");
//        }
//    }
//
//
//    public WorkerFactory(CarStorage carStorage, AccessoryStorage accessoryStorage, CarBodyStorage carBodyStorage, EngineStorage engineStorage) {
//        this.carStorage = carStorage;
//        this.accessoryStorage = accessoryStorage;
//        this.carBodyStorage = carBodyStorage;
//        this.engineStorage = engineStorage;
//    }
//
//
//    @Override
//    public Thread newThread(Runnable r) {
//        return new Worker(carStorage, accessoryStorage, carBodyStorage, engineStorage);
//    }
//}
