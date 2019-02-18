package dao;

import java.util.List;

public interface BaseDao<Model> {

    Model getById(String id);

    boolean add(Model model);

    List<Model> getAll();

    boolean delete(Model model);

    boolean update(Model model);

}
