package com.javarush.task.task29.task2909.human;
/*
7.1. Параметризация метода.
Замени методы incAverageGradeBy01() и incAverageGradeBy02() одним методом incAverageGrade(double delta).
7.2. Передача всего объекта.
Перепиши метод addInfoAboutStudent(), чтобы он в качестве параметра принимал объект типа Student.
7.3. Замена параметра вызовом метода.
Перепиши метод printInfoAboutStudent(), чтобы он не требовал в качестве параметра имя студента, а получал его, вызвав соответствующий метод у переданного объекта.
7.4. Замена параметров объектом.
 Перепиши методы setBeginningOfSession и setEndOfSession,чтобы они вместо набора параметров принимали по одному объекту даты.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends UniversityPerson {

    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public int getCourse() {
        return course;
    }

    public Student(String name, int age, double averageGrade) {
        super(name,age);

        this.averageGrade = averageGrade;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }

    public void live() {
        learn();
    }

    public void learn() {
    }

    public void incAverageGrade(double delta) {
        setAverageGrade(getAverageGrade() + delta);
    }


    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}