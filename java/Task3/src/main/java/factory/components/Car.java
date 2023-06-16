package factory.components;

public class Car extends Component {
    private final CarBody carBody;
    private final Engine engine;
    private final Accessory accessory;

    public Car(int ID, CarBody carBody, Engine engine, Accessory accessory) {
        super(ID);
        this.carBody = carBody;
        this.engine = engine;
        this.accessory = accessory;
    }

    public long getAccessoryID() {
        return accessory.getID();
    }

    public long getEngineID() {
        return engine.getID();
    }

    public long getCarBodyID() {
        return carBody.getID();
    }
}
