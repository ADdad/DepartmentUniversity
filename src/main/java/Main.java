import presenter.AddMasterForm;
import presenter.MainForm;
import util.ServiceFactory;

public class Main {

    public static void main(String[] args) {

        //new AddMasterForm(ServiceFactory.getMasterService());
        new MainForm(ServiceFactory.getMasterService());

    }

}
