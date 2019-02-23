import dao.impl.MasterDao;
import presenter.MastersTable;

public class Main {

    public static void main(String[] args) {
        MasterDao masterDao = new MasterDao();
        new MastersTable(masterDao.getAll());
    }

}
