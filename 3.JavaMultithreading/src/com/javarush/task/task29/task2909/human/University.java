package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student student = null;

        for (Student studentTmp: students) {
            if (studentTmp.getAverageGrade() == averageGrade)
                student = studentTmp;
        }

        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student student = null;
        double minAverageGrade = Double.MIN_VALUE;

        for (Student studentTmp: students) {
            if (studentTmp.getAverageGrade() > minAverageGrade) {
                student = studentTmp;
                minAverageGrade = studentTmp.getAverageGrade();
            }
        }

        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student student = null;
        double maxAverageGrade = Double.MAX_VALUE;

        for (Student studentTmp: students) {
            if (studentTmp.getAverageGrade() < maxAverageGrade) {
                student = studentTmp;
                maxAverageGrade = studentTmp.getAverageGrade();
            }
        }

        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}