package com.univer.dao.interfaces;

import com.univer.model.Scientist;

public interface ScientistDao extends BaseDao<Scientist> {
    Scientist getByName(String name);
}
