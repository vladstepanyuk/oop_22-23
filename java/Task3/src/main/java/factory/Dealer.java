package factory;

import factory.components.Car;
import factory.storage.CarStorage;
import factory.utils.Flag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class Dealer extends Thread {
    private int Delay = 100;
    static private final AtomicInteger idPool;

    private final CarStorage storage;
    private Car product;

    final Logger logger;
    private final Flag isRunning;
    private final Object monitor;

    static {
        idPool = new AtomicInteger(0);
    }


    public void setDelay(int delay) {
        Delay = delay;
    }



    public Dealer(CarStorage storage, Flag isRunning, Object monitor) {
        this.storage = storage;
        this.isRunning = isRunning;
        this.monitor = monitor;
        logger =  LogManager.getLogger("Car dealer N"+idPool.getAndIncrement());

    }

    public void getProduct() throws InterruptedException {
        logger.info("trying to get product");
        sleep(Delay);
        product =  storage.get();

    }


    public void run() {
        try {
            while (true) {
                if (!isRunning.isTrue())
                    synchronized (monitor) {
                        monitor.wait();
                    }
                getProduct();
                logger.info("sell Auto <" + product.getID()+"> (Body: <"+product.getCarBodyID() + ">, Engine: <" +
                        product.getEngineID()+ ">, Accessory: <" +
                        product.getAccessoryID() +">)");
            }
        } catch (Exception e) {
            logger.error("unable to get a car. cause: "+ e.getCause().getMessage());
        }
    }
}
