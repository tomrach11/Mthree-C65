package com.tr.classroster.dao;

import com.tr.classroster.model.Course;
import com.tr.classroster.model.Student;
import com.tr.classroster.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoDB implements CourseDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Course getCourseById(int id) {
        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
            Course course = jdbc.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
            course.setTeacher(getTeacherForCourse(id));
            course.setStudents(getStudentsForCourse(id));
            return course;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    private Teacher getTeacherForCourse(int id) {
        final String SELECT_TEACHER_FOR_COURSE = "SELECT * FROM teacher t JOIN course c ON c.teacherId = t.id WHERE c.id = ?";
        return jdbc.queryForObject(SELECT_TEACHER_FOR_COURSE, new TeacherMapper(), id);
    }

    private List<Student> getStudentsForCourse(int id) {
        final String SELECT_STUDENTS_FOR_COURSE = "SELECT s.* FROM student s JOIN course_student cs ON cs.studentId = s.id WHERE cs.courseId = ?";
        List<Student> students = jdbc.query(SELECT_STUDENTS_FOR_COURSE, new StudentMapper(), id);
        return students;
    }

    @Override
    public List<Course> getAllCourse() {
        final String SELECT_ALL_COURSES = "SELECT * FROM course";
        List<Course> courses = jdbc.query(SELECT_ALL_COURSES, new CourseMapper());
        associateTeacherAndStudents(courses);
        return courses;
    }

    private void associateTeacherAndStudents(List<Course> courses) {
        for (Course course : courses) {
            course.setTeacher(getTeacherForCourse(course.getId()));
            course.setStudents(getStudentsForCourse(course.getId()));
        }
    }

    @Override
    public Course addCourse(Course course) {
        final String INSERT_COURSE = "INSERT INTO course (name, description, teacherId) VALUES (?,?,?)";
        jdbc.update(INSERT_COURSE, course.getName(), course.getDescription(), course.getTeacher().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        course.setId(newId);
        insertCourseStudent(course);
        return course;
    }

    private void insertCourseStudent(Course course) {
        for (Student student : course.getStudents()) {
            final String INSERT_COURSE_STUDENT = "INSERT INTO course_student (courseId, studentId) VALUES (?,?)";
            jdbc.update(INSERT_COURSE_STUDENT, course.getId(), student.getId());
        }

    }

    @Override
    public void updateCourse(Course course) {
        final String UPDATE_COURSE = "UPDATE course SET name = ?, description = ?, teacherId = ? WHERE id = ?";
        jdbc.update(UPDATE_COURSE,  course.getName(), course.getDescription(), course.getTeacher().getId(), course.getId());

        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, course.getId());

        insertCourseStudent(course);
    }

    @Override
    public void deleteCourseById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);

        final String DELETE_COURSE = "DELETE FROM course WHERE id = ?";
        jdbc.update(DELETE_COURSE, id);
    }

    @Override
    public List<Course> getCourseForTeacher(Teacher teacher) {
        final String SELECT_COURSES_FOR_TEACHER = "SELECT * FROM course WHERE teacherId = ?";
        List<Course> courses =  jdbc.query(SELECT_COURSES_FOR_TEACHER, new CourseMapper(), teacher.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }

    @Override
    public List<Course> getCourseForStudent(Student student) {
        final String SELECT_COURSES_FOR_STUDENT = "SELECT * FROM course c JOIN course_student cs ON cs.courseId = c.id WHERE cs.studentId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_STUDENT, new CourseMapper(), student.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }
}
