import dao.impl.MasterDao;
import presenter.MainTable;

public class Main {

    public static void main(String[] args) {
        MasterDao masterDao = new MasterDao();
        new MainTable(masterDao.getAll());
    }

}
