package com.tr.classroster.dao;

import com.tr.classroster.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoDB implements StudentDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Student getStudentById(int id) {
        try {
            final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
            return jdbc.queryForObject(SELECT_STUDENT_BY_ID, new StudentMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
        return jdbc.query(SELECT_ALL_STUDENTS, new StudentMapper());
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        final String INSERT_STUDENT = "INSERT INTO student(firstName, lastName) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_STUDENT,
                student.getFirstName(),
                student.getLastName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        student.setId(newId);
        return student;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        final String UPDATE_STUDENT = "UPDATE student SET firstName = ?, lastName = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_STUDENT,
                student.getFirstName(),
                student.getLastName(),
                student.getId());
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE studentId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);

        final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
        jdbc.update(DELETE_STUDENT, id);
    }
}
