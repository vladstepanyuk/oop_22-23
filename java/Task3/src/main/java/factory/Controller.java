package factory;

import factory.storage.CarStorage;
import factory.utils.Flag;
import factory.workers.WorkShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller extends Thread {

    private final WorkShop workShop;
    private final CarStorage carStorage;
    private final Flag isRunning;

    private final Object monitor;

    final Logger logger = LogManager.getLogger("Controller");



    public Controller(WorkShop workShop, CarStorage carStorage, Flag isRunning, Object monitor) {
        this.workShop = workShop;
        this.carStorage = carStorage;
        this.isRunning = isRunning;
        this.monitor = monitor;
    }

//    private  Car product;

    @Override
    public void run() {
        try {
            while (true) {
                if (!isRunning.isTrue())
                    synchronized (monitor) {
                        monitor.wait();
                    }

                workShop.submitTask();

                synchronized (carStorage) {
                    carStorage.wait();
                }
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }


    }

}
