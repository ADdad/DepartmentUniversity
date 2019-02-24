package dao.interfaces;

import java.util.List;

public interface BaseDao<Model> {

    Model getById(String id);

    boolean add(Model model);

    List<Model> getAll();

    boolean delete(String id);

    boolean update(Model model);

}
