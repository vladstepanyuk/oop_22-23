package factory.ui;

import factory.Context;
import factory.Factory;
import factory.exception.ParsConfigException;

public class GraphicInterface {
    private Factory factory;
    private MyWin Win;
    public GraphicInterface() throws ParsConfigException {
        var context = new Context();
        factory = new Factory(context);
        Win = new MyWin(factory);
    }

    public void startGame(){
        Win.setVisible(true);
    }
}
