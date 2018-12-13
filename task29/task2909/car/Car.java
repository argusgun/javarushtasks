package com.javarush.task.task29.task2909.car;

import java.util.Date;

public class Car {
    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;


    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    public static Car create(int type, int numberOfPassengers)
    {
        Car car=null;
        switch (type)
        {
            case 0: car=new Truck(numberOfPassengers); break;
            case 1: car= new Sedan(numberOfPassengers); break;
            case 2: car= new Cabriolet(numberOfPassengers); break;
        }
        return car;
    }
    protected Car (int type, int numberOfPassengers)
    {
        Car car=null;
        switch (type)
        {
            case 0: car=new Truck(numberOfPassengers); break;
            case 1: car= new Sedan(numberOfPassengers); break;
            case 2: car= new Cabriolet(numberOfPassengers); break;
        }

    }
    protected Car(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) {
        if (numberOfLiters < 0)
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        fuel += numberOfLiters;
    }

    public boolean isSummer(Date date , Date summerStart, Date summerEnd)
    {
        if (date.after(summerStart) && date.before(summerEnd)) {
            return  true;
        }
        return false;
    }

    public double getWinterConsumption(int length)
    {
        return length * winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length)
    {
        return length * summerFuelConsumption;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (!isSummer(date,SummerStart,SummerEnd)) {
            consumption = getWinterConsumption(length);
        } else {
            consumption = getSummerConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (!isDriverAvailable())
            return 0;
        if (fuel <= 0)
            return 0;

        return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
            fastenDriverBelt();
        } else {
            fastenDriverBelt();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public int getMaxSpeed() {
        if (this instanceof Truck)
            return 80;
        if (this instanceof Sedan)
            return 120;
        return 90;
    }
}