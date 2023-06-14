package factory;

import factory.exception.SupplierException;
import factory.products.Car;
import factory.storage.CarStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class Dealer extends Thread {
    private static int Delay = 100;
    static private final AtomicInteger idPool;

    private final CarStorage storage;
    private Car product;

    final Logger logger;

    static {
        idPool = new AtomicInteger(0);
    }


    public static void setDelay(int delay) {
        Delay = delay;
    }



    public Dealer(CarStorage storage) {
        this.storage = storage;
        logger =  LogManager.getLogger("Car dealer N"+idPool.getAndIncrement());

    }

    public void getProduct() throws SupplierException {
        try {
            logger.info("trying to get product");
            sleep(Delay);
            product =  storage.get();

        } catch (Exception e) {
            throw new SupplierException("unable to make a product", e);
        }

    }


    public void run() {
        try {
            while (true) {
                getProduct();
                logger.info("sell Auto <" + product.getID()+"> (Body: <"+product.getCarBodyID() + ">, Engine: <" +
                        product.getEngineID()+ ">, Accessory: <" +
                        product.getAccessoryID() +">)");
            }
        } catch (SupplierException e) {
            logger.error("unable to get a car. cause: "+ e.getCause().getMessage());
        }
    }
}
