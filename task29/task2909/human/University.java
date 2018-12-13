package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;
/*
6.2. Добавление параметра. Добавить параметр с типом double в метод getStudentWithAverageGrade(),
чтобы было понятно с каким средним балом нужен студент.
Реализуй метод getStudentWithAverageGrade().
6.3. Удаление параметра. Убери параметр из метода getStudentWithMaxAverageGrade().
Реализуй этот метод, он должен возвращать студента с максимальным средним балом.
6.4. Разделение запроса и модификатора. Раздели метод getStudentWithMinAverageGradeAndExpel
на Student getStudentWithMinAverageGrade() и void expel(Student student).
Первый метод должен возвратить студента с минимальным средним балом, а второй - отчислить переданного студента
 (удалять из списка students).
 */

public class University   {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }



    public University(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student student = null;
        for(Student s: students)
        {
            if(s.getAverageGrade()==averageGrade) student=s;
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student student = students.get(0);
        for(Student s: students)
        {
            if(student.getAverageGrade()<s.getAverageGrade()) student=s;
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        Student student = students.get(0);
        for(Student s: students)
        {
            if(student.getAverageGrade()>s.getAverageGrade()) student=s;
        }
        return student;
    }

    public void expel(Student student)
    {
        students.remove(student);
    }
}