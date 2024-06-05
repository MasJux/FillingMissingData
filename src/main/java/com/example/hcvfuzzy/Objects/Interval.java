package com.example.hcvfuzzy.Objects;

public class Interval  {
    private double lower;
    private double upper;

    public Interval(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public double getLower() {
        return lower;
    }

    public double getUpper() {
        return upper;
    }
    public Interval negation() {
        return new Interval(1 - this.upper, 1 - this.lower);
    }
}