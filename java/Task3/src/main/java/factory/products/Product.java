package factory.products;

public class Product {
    private final long ID;

    public Product(long ID) {
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }
}
