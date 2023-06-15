package factory;

import factory.storage.CarStorage;
import factory.utils.Flag;
import factory.workers.WorkShop;

public class Controller extends Thread {

    private final WorkShop workShop;
    private final CarStorage carStorage;
    //    private final Object monitor = new Object();
    private final Flag isRunning;

    private final Object Monitor;



    public Controller(WorkShop workShop, CarStorage carStorage, Flag isRunning, Object monitor) {
        this.workShop = workShop;
        this.carStorage = carStorage;
        this.isRunning = isRunning;
        Monitor = monitor;
    }

//    private  Car product;

    @Override
    public void run() {
        try {
            while (true) {
                if (!isRunning.isTrue())
                    synchronized (Monitor) {
                        Monitor.wait();
                    }

                workShop.submitTask();

                synchronized (carStorage) {
                    carStorage.wait();
                }
            }
        } catch (InterruptedException ignored) {

        }


    }

}
