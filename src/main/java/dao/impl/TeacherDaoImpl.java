package dao.impl;

import dao.config.ConnectionFactory;
import dao.interfaces.BaseDao;
import dao.interfaces.TeacherDao;
import model.Master;
import model.Teacher;
import util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public Teacher getById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_TEACHER_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTeacherFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Teacher extractTeacherFromRS(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setScientistId(rs.getString("scientist_id"));
        teacher.setSecondName(rs.getString("second_name"));
        teacher.setPhoneNumber(rs.getString("phone_number"));
        teacher.setGender(rs.getString("gender"));
        teacher.setCathedraId(rs.getString("cathedra_id"));
        teacher.setPosition(rs.getString("position"));
        teacher.setDegree(rs.getString("degree"));
        return teacher;
    }

    @Override
    public boolean add(Teacher teacher) {
        return false;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_TEACHERS);
            while (rs.next()) {
                Teacher teacher = extractTeacherFromRS(rs);
                teachers.add(teacher);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teachers;
    }

    @Override
    public boolean delete(String teacher) {
        return false;
    }

    @Override
    public boolean update(Teacher teacher) {
        return false;
    }

    @Override
    public Teacher getTeacherByName(String name) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_TEACHER_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTeacherFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
