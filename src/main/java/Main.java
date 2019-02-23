import presenter.MainFrame;
import util.ServiceFactory;

public class Main {

    public static void main(String[] args) {
        new MainFrame(ServiceFactory.getMasterService());
    }

}
