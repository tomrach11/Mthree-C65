package com.tr.classroster.model;

import java.util.List;

public class Course {
    private int id;
    private String name;
    private String description;
    private Teacher teacher;
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        if (getId() != course.getId()) return false;
        if (getName() != null ? !getName().equals(course.getName()) : course.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(course.getDescription()) : course.getDescription() != null)
            return false;
        if (getTeacher() != null ? !getTeacher().equals(course.getTeacher()) : course.getTeacher() != null)
            return false;
        return getStudents() != null ? getStudents().equals(course.getStudents()) : course.getStudents() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getTeacher() != null ? getTeacher().hashCode() : 0);
        result = 31 * result + (getStudents() != null ? getStudents().hashCode() : 0);
        return result;
    }
}
