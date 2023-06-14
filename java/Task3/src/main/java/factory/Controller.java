package factory;

import factory.storage.CarStorage;
import factory.workers.Task;
import factory.workers.WorkShop;

public class Controller extends Thread {

    private final WorkShop workShop;
    private final CarStorage carStorage;
    private final Object monitor = new Object();

    private void checkStorage() {
        while (true) {
            workShop.submitTask();

            try {
                synchronized (carStorage) {
                    carStorage.wait();

                }
            } catch (InterruptedException ignored) {

            }
        }
    }

    public synchronized void Notify() {
        notify();
    }

    public Controller(WorkShop workShop, CarStorage carStorage) {
        this.workShop = workShop;
        this.carStorage = carStorage;
    }

//    private  Car product;

    @Override
    public void run() {

        checkStorage();
    }

}
