package factory.storage;

import factory.products.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage<T extends Product> {
    private ArrayBlockingQueue<T> queue;
    private int storageSize;
    Class<T> tClass;
    final Logger logger;


    public Storage(int storageSize, Class<T> tClass) {
        this.storageSize = storageSize;
        queue = new ArrayBlockingQueue<>(storageSize, true);
        this.tClass = tClass;
        logger = LogManager.getLogger(tClass.getName() + " storage");
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void supply(T product) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        logger.info("supply product: <" + product.getID() + ">");
        queue.add(product);
        notify();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        logger.info("supply product: <" + queue.peek().getID() + ">");
        notifyAll();
        return queue.remove();
    }

    public boolean isFull() {
        return queue.remainingCapacity() == 0;
    }

    public int getProductsNumber() {
        return storageSize - queue.remainingCapacity();
    }

    public void changeSize(int newSize) {
        storageSize = newSize;
        queue = new ArrayBlockingQueue<>(storageSize, true, queue);
    }
}
