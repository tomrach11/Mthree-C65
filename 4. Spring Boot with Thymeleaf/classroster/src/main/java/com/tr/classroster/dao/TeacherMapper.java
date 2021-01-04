package com.tr.classroster.dao;

import com.tr.classroster.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int i) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("id"));
        teacher.setFirstName(rs.getString("firstName"));
        teacher.setLastName(rs.getString("lastName"));
        teacher.setSpecialty(rs.getString("specialty"));
        return teacher;
    }
}
