package dao.interfaces;

import model.Teacher;

public interface TeacherDao extends BaseDao<Teacher> {
    Teacher getTeacherByName(String name);
}
