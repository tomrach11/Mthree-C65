package com.tr.classroster.dao;

import com.tr.classroster.model.Course;
import com.tr.classroster.model.Student;
import com.tr.classroster.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentDaoDBTest {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @BeforeEach
    void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeacher();
        for(Teacher teacher : teachers) {
            teacherDao.deleteTeacherById(teacher.getId());
        }

        List<Student> students = studentDao.getAllStudents();
        for(Student student : students) {
            studentDao.deleteStudentById(student.getId());
        }

        List<Course> courses = courseDao.getAllCourse();
        for(Course course : courses) {
            courseDao.deleteCourseById(course.getId());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddAndGetStudent() {
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);

        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);
    }

    @Test
    public void testGetAllStudents() {
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);

        Student student2 = new Student();
        student2.setFirstName("Test Student First 2");
        student2.setLastName("Test Student Last 2");
        student2 = studentDao.addStudent(student2);

        List<Student> students = studentDao.getAllStudents();

        assertEquals(2, students.size());
        assertTrue(students.contains(student));
        assertTrue(students.contains(student2));
    }

    @Test
    public void testUpateStudent() {
        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);

        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);

        student.setFirstName("New Test Student First");
        studentDao.updateStudent(student);

        assertNotEquals(student, fromDao);

        fromDao = studentDao.getStudentById(student.getId());

        assertEquals(student, fromDao);
    }

    @Test
    public void testDeleteStudentById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);

        Course course = new Course();
        course.setName("Test Course");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);

        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);

        studentDao.deleteStudentById(student.getId());

        fromDao = studentDao.getStudentById(student.getId());
        assertNull(fromDao);
    }
}