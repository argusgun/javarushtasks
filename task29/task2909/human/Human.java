package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    private BloodGroup bloodGroup;


    protected Size size;

    private List<Human> children = new ArrayList<>();

    public  class Size
    {
        public  int height, weight;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup=bloodGroup;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Human() {
        this.id = nextId;
        nextId++;
    }

    public String getPosition()
    {
        return "Человек";
    }

    public void printData() {
        System.out.println(this.getPosition()+": " + name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Human( String name, int age) {
        this.age = age;
        this.name = name;
        this.id = nextId;
        nextId++;
    }

    public void live() {

    }

    public int getId() {
        return id;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human children) {
        this.children.add(children);
    }

    public void removeChild(Human children) {
        this.children.remove(children);
    }

}