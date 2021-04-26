import controller.MainController;

/**
 * Created by Moon on 24/04/2021
 */
public class IChoonApp {
    private static MainController menuController = new MainController();
    public static void main(String[] args) {
        menuController.start();
    }
}
