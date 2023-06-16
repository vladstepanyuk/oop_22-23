package factory.components;

public class Component {
    private final long ID;

    public Component(long ID) {
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }
}
