import presenter.MainForm;
import util.ServiceFactory;

public class Main {

    public static void main(String[] args) {

        new MainForm(ServiceFactory.getMasterService());

    }

}
