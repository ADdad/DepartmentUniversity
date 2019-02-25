package dao.interfaces;

import model.Scientist;

public interface ScientistDao extends BaseDao<Scientist> {
    Scientist getByName(String name);
}
