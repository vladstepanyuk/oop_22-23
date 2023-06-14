package factory.suppliers;

import factory.exception.SupplierException;
import factory.products.Product;
import factory.storage.Storage;
import factory.utils.IDManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Supplier<T extends Product> extends Thread {
    private int Delay = 100;
    private final Class<T> tClass;

    private final Storage<T> tStorage;
    private T product;

    final Logger logger;

    public void setDelay(int delay) {
        Delay = delay;
    }


    public Supplier(Class<T> tClass, Storage<T> tStorage) {
        this.tClass = tClass;
        this.tStorage = tStorage;
        logger = LogManager.getLogger(tClass.getName() + " supplier");

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
                if (product == null) {
                    makeProduct();
                }
                supplyToTheStorage();
            }
        } catch (SupplierException e) {
            logger.error(e.getMessage() + ". cause: " + e.getCause().getMessage());
        }
    }
}
