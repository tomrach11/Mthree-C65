package com.tr.classroster.model;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private String specialty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;

        Teacher teacher = (Teacher) o;

        if (getId() != teacher.getId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(teacher.getFirstName()) : teacher.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(teacher.getLastName()) : teacher.getLastName() != null)
            return false;
        return getSpecialty() != null ? getSpecialty().equals(teacher.getSpecialty()) : teacher.getSpecialty() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getSpecialty() != null ? getSpecialty().hashCode() : 0);
        return result;
    }
}
