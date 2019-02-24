import presenter.MainForm;
import presenter.ScientificJobsWindow;
import util.ServiceFactory;

public class Main {

    public static void main(String[] args) {

//        new MainForm(ServiceFactory.getMasterService());
        new ScientificJobsWindow(ServiceFactory.getMasterService());
    }

}
