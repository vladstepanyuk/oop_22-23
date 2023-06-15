package factory.suppliers;

import factory.exception.SupplierException;
import factory.products.Product;
import factory.storage.Storage;
import factory.utils.Flag;
import factory.utils.IDManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Supplier<T extends Product> extends Thread {
    private int Delay = 100;
    private final Class<T> tClass;

    private final Storage<T> tStorage;
    private T product;

    final Logger logger;
    private final Object Monitor;


    final Flag isRunning;

    public void setDelay(int delay) {
        Delay = delay;
    }


    public Supplier(Class<T> tClass, Storage<T> tStorage, Object monitor, Flag isRunning) {
        this.tClass = tClass;
        this.tStorage = tStorage;
        logger = LogManager.getLogger(tClass.getName() + " supplier");
        Monitor = monitor;

        this.isRunning = isRunning;
    }

    public void makeProduct() throws SupplierException {
        try {
            logger.info("trying to make product");
            sleep(Delay);
            product = tClass.getConstructor(Integer.TYPE).newInstance(IDManager.getUniqueID());

        } catch (Exception e) {
            throw new SupplierException("unable to make a product", e);
        }

    }

    public void supplyToTheStorage() {
        logger.info("trying to supply product");
        tStorage.supply(product);
        product = null;
    }

    public void run() {
        try {
            while (true) {
                if (!isRunning.isTrue()) {
                    synchronized (Monitor) {
                        Monitor.wait();
                    }
                }
                if (product == null) {
                    makeProduct();
                }
                supplyToTheStorage();
            }
        } catch (SupplierException | InterruptedException e) {
            logger.error(e.getMessage() + ". cause: " + e.getCause().getMessage());
        }
    }
}
