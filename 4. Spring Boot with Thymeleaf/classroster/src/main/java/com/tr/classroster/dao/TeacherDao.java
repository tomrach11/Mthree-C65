package com.tr.classroster.dao;

import com.tr.classroster.model.Teacher;

import java.util.List;

public interface TeacherDao {
    Teacher getTeacherById (int id);
    List<Teacher> getAllTeacher();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}
