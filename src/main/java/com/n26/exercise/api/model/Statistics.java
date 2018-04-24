package com.n26.exercise.api.model;


import java.io.Serializable;

public class Statistics implements Serializable{

    private double Sum;
    private double average;
    private double max;
    private double min;
    private int count;

    public double getSum() {
        return Sum;
    }

    public void setSum(double sum) {
        Sum = sum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
