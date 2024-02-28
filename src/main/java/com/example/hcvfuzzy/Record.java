package com.example.hcvfuzzy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Record {
    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty Radius;
    private final SimpleStringProperty Texture;
    private final SimpleStringProperty Perimeter;
    private final SimpleStringProperty Area;
    private final SimpleStringProperty Smoothness;
    private final SimpleStringProperty Compactness;
    private final SimpleStringProperty Concavity;
    private final SimpleStringProperty ConcavePoints;
    private final SimpleStringProperty Symmetry;
    private final SimpleStringProperty FractalDimension;

    public Record(int id, String radius, String texture, String perimeter, String area, String smoothness, String compactness, String concavity, String concavePoints, String symmetry, String fractalDimension) {
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

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public String getRadius() {
        return Radius.get();
    }

    public void setRadius(String radius) {
        this.Radius.set(radius);
    }

    public SimpleStringProperty radiusProperty() {
        return Radius;
    }

    public String getTexture() {
        return Texture.get();
    }

    public void setTexture(String texture) {
        this.Texture.set(texture);
    }

    public SimpleStringProperty textureProperty() {
        return Texture;
    }

    public String getPerimeter() {
        return Perimeter.get();
    }

    public void setPerimeter(String perimeter) {
        this.Perimeter.set(perimeter);
    }

    public SimpleStringProperty perimeterProperty() {
        return Perimeter;
    }

    public String getArea() {
        return Area.get();
    }

    public void setArea(String area) {
        this.Area.set(area);
    }

    public SimpleStringProperty areaProperty() {
        return Area;
    }

    public String getSmoothness() {
        return Smoothness.get();
    }

    public void setSmoothness(String smoothness) {
        this.Smoothness.set(smoothness);
    }

    public SimpleStringProperty smoothnessProperty() {
        return Smoothness;
    }

    public String getCompactness() {
        return Compactness.get();
    }

    public void setCompactness(String compactness) {
        this.Compactness.set(compactness);
    }

    public SimpleStringProperty compactnessProperty() {
        return Compactness;
    }

    public String getConcavity() {
        return Concavity.get();
    }

    public void setConcavity(String concavity) {
        this.Concavity.set(concavity);
    }

    public SimpleStringProperty concavityProperty() {
        return Concavity;
    }

    public String getConcavePoints() {
        return ConcavePoints.get();
    }

    public void setConcavePoints(String concavePoints) {
        this.ConcavePoints.set(concavePoints);
    }

    public SimpleStringProperty concavePointsProperty() {
        return ConcavePoints;
    }

    public String getSymmetry() {
        return Symmetry.get();
    }

    public void setSymmetry(String symmetry) {
        this.Symmetry.set(symmetry);
    }

    public SimpleStringProperty symmetryProperty() {
        return Symmetry;
    }

    public String getFractalDimension() {
        return FractalDimension.get();
    }

    public void setFractalDimension(String fractalDimension) {
        this.FractalDimension.set(fractalDimension);
    }

    public SimpleStringProperty fractalDimensionProperty() {
        return FractalDimension;
    }
}