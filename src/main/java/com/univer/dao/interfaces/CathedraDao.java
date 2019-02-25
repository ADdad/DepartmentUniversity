package com.univer.dao.interfaces;

import com.univer.model.Cathedra;

public interface CathedraDao extends BaseDao<Cathedra> {
    Cathedra getByName(String name);
}
