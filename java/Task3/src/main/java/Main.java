import factory.ui.GraphicInterface;

public class Main  {


    public static void main(String[] args) {
        try {
            new GraphicInterface().startGame();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}