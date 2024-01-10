package com.example.hcvfuzzy;

import javafx.beans.property.SimpleStringProperty;

public class Record {
    //Assume each record have 6 elements, all String
    private SimpleStringProperty BIRADSassessment;
    private SimpleStringProperty Age;
    private SimpleStringProperty Shape;
    private SimpleStringProperty Margin;
    private SimpleStringProperty Density;
    private SimpleStringProperty Severity;

    public String getBIRADSassessment() {
        return BIRADSassessment.get();
    }

    public SimpleStringProperty BIRADSassessmentProperty() {
        return BIRADSassessment;
    }

    public void setBIRADSassessment(String BIRADSassessment) {
        this.BIRADSassessment.set(BIRADSassessment);
    }

    public String getAge() {
        return Age.get();
    }

    public SimpleStringProperty ageProperty() {
        return Age;
    }

    public void setAge(String age) {
        this.Age.set(age);
    }

    public String getShape() {
        return Shape.get();
    }

    public SimpleStringProperty shapeProperty() {
        return Shape;
    }

    public void setShape(String shape) {
        this.Shape.set(shape);
    }

    public String getMargin() {
        return Margin.get();
    }

    public SimpleStringProperty marginProperty() {
        return Margin;
    }

    public void setMargin(String margin) {
        this.Margin.set(margin);
    }

    public String getDensity() {
        return Density.get();
    }

    public SimpleStringProperty densityProperty() {
        return Density;
    }

    public void setDensity(String density) {
        this.Density.set(density);
    }

    public String getSeverity() {
        return Severity.get();
    }

    public SimpleStringProperty severityProperty() {
        return Severity;
    }

    public void setSeverity(String severity) {
        this.Severity.set(severity);
    }



    public Record(String BIRADSassessment, String age, String shape, String margin, String density, String severity) {
        this.BIRADSassessment = new SimpleStringProperty(BIRADSassessment);
        this.Age = new SimpleStringProperty(age);
        this.Shape = new SimpleStringProperty(shape);
        this.Margin = new SimpleStringProperty(margin);
        this.Density = new SimpleStringProperty(density);
        this.Severity = new SimpleStringProperty(severity);
    }
}