package com.tr.classroster.dao;

import com.tr.classroster.model.Course;
import com.tr.classroster.model.Student;
import com.tr.classroster.model.Teacher;

import java.util.List;

public interface CourseDao {
    Course getCourseById(int id);
    List<Course> getAllCourse();
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);

    List<Course> getCourseForTeacher(Teacher teacher);
    List<Course> getCourseForStudent(Student student);
}

