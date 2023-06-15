package factory.utils;

public class Flag {
    private boolean isRunning = false;

    public void changeState() {
        isRunning = !isRunning;
    }

    public boolean isTrue() {
        return isRunning;
    }

}
