package com.javarush.task.task29.task2909.human;

public class Soldier extends Human {


    public Soldier(String name, int age) {
        super();
        setName(name);
        setAge(age);
    }

    public void live() {
        if (this instanceof Soldier)
            fight();
    }

    public void fight() {
    }
}
