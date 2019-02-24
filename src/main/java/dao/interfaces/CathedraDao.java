package dao.interfaces;

import model.Cathedra;

public interface CathedraDao extends BaseDao<Cathedra> {
    Cathedra getByName(String name);
}
