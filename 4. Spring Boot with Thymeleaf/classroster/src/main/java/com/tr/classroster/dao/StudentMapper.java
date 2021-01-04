package com.tr.classroster.dao;

import com.tr.classroster.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int i) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        return student;
    }
}
