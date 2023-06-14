package factory.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IDManager {
    private static final AtomicInteger IDCounter;

    static {
        IDCounter = new AtomicInteger(0);
    }

    public static synchronized int getUniqueID(){
        return IDCounter.getAndIncrement();
    }

}
