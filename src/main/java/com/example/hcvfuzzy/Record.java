package com.example.hcvfuzzy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Record {
    private SimpleIntegerProperty ID;
    private SimpleStringProperty Radius;
    private SimpleStringProperty Texture;
    private SimpleStringProperty Perimeter;
    private SimpleStringProperty Area;
    private SimpleStringProperty Smoothness;
    private SimpleStringProperty Compactness;
    private SimpleStringProperty Concavity;
    private SimpleStringProperty ConcavePoints;
    private SimpleStringProperty Symmetry;
    private SimpleStringProperty FractalDimension;

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getRadius() {
        return Radius.get();
    }

    public SimpleStringProperty radiusProperty() {
        return Radius;
    }

    public void setRadius(String radius) {
        this.Radius.set(radius);
    }

    public String getTexture() {
        return Texture.get();
    }

    public SimpleStringProperty textureProperty() {
        return Texture;
    }

    public void setTexture(String texture) {
        this.Texture.set(texture);
    }

    public String getPerimeter() {
        return Perimeter.get();
    }

    public SimpleStringProperty perimeterProperty() {
        return Perimeter;
    }

    public void setPerimeter(String perimeter) {
        this.Perimeter.set(perimeter);
    }

    public String getArea() {
        return Area.get();
    }

    public SimpleStringProperty areaProperty() {
        return Area;
    }

    public void setArea(String area) {
        this.Area.set(area);
    }

    public String getSmoothness() {
        return Smoothness.get();
    }

    public SimpleStringProperty smoothnessProperty() {
        return Smoothness;
    }

    public void setSmoothness(String smoothness) {
        this.Smoothness.set(smoothness);
    }

    public String getCompactness() {
        return Compactness.get();
    }

    public SimpleStringProperty compactnessProperty() {
        return Compactness;
    }

    public void setCompactness(String compactness) {
        this.Compactness.set(compactness);
    }

    public String getConcavity() {
        return Concavity.get();
    }

    public SimpleStringProperty concavityProperty() {
        return Concavity;
    }

    public void setConcavity(String concavity) {
        this.Concavity.set(concavity);
    }

    public String getConcavePoints() {
        return ConcavePoints.get();
    }

    public SimpleStringProperty concavePointsProperty() {
        return ConcavePoints;
    }

    public void setConcavePoints(String concavePoints) {
        this.ConcavePoints.set(concavePoints);
    }

    public String getSymmetry() {
        return Symmetry.get();
    }

    public SimpleStringProperty symmetryProperty() {
        return Symmetry;
    }

    public void setSymmetry(String symmetry) {
        this.Symmetry.set(symmetry);
    }

    public String getFractalDimension() {
        return FractalDimension.get();
    }

    public SimpleStringProperty fractalDimensionProperty() {
        return FractalDimension;
    }

    public void setFractalDimension(String fractalDimension) {
        this.FractalDimension.set(fractalDimension);
    }

    public Record(int id,String radius, String texture, String perimeter, String area, String smoothness, String compactness, String concavity, String concavePoints, String symmetry, String fractalDimension) {
        this.ID = new SimpleIntegerProperty(id);
        this.Radius = new SimpleStringProperty(radius);
        this.Texture = new SimpleStringProperty(texture);
        this.Perimeter = new SimpleStringProperty(perimeter);
        this.Area = new SimpleStringProperty(area);
        this.Smoothness = new SimpleStringProperty(smoothness);
        this.Compactness = new SimpleStringProperty(compactness);
        this.Concavity = new SimpleStringProperty(concavity);
        this.ConcavePoints = new SimpleStringProperty(concavePoints);
        this.Symmetry = new SimpleStringProperty(symmetry);
        this.FractalDimension = new SimpleStringProperty(fractalDimension);
    }
}