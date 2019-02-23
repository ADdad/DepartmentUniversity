package dao.impl;

import dao.config.ConnectionFactory;
import dao.interfaces.BaseDao;
import model.Teacher;
import util.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherDao implements BaseDao<Teacher> {
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
        return null;
    }

    @Override
    public boolean delete(Teacher teacher) {
        return false;
    }

    @Override
    public boolean update(Teacher teacher) {
        return false;
    }
}
