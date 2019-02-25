package com.univer.dao.interfaces;

import com.univer.model.Teacher;

public interface TeacherDao extends BaseDao<Teacher> {
    Teacher getTeacherByName(String name);
}
