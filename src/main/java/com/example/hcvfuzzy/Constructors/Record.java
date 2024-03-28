package com.example.hcvfuzzy.Constructors;

import javafx.beans.property.SimpleIntegerProperty;

public class Record {


    private final SimpleIntegerProperty ID;
    private final SimpleIntegerProperty Decision;
    private final SimpleIntegerProperty Radius;
    private final SimpleIntegerProperty Texture;
    private final SimpleIntegerProperty Perimeter;
    private final SimpleIntegerProperty Area;
    private final SimpleIntegerProperty Smoothness;
    private final SimpleIntegerProperty Compactness;
    private final SimpleIntegerProperty Concavity;
    private final SimpleIntegerProperty ConcavePoints;
    private final SimpleIntegerProperty Symmetry;
    private final SimpleIntegerProperty FractalDimension;

    public Record(int id, int radius, int texture, int perimeter, int area, int smoothness, int compactness, int concavity, int concavePoints, int symmetry, int fractalDimension,int decision) {
        this.ID = new SimpleIntegerProperty(id);
        this.Radius = new SimpleIntegerProperty(radius);
        this.Texture = new SimpleIntegerProperty(texture);
        this.Perimeter = new SimpleIntegerProperty(perimeter);
        this.Area = new SimpleIntegerProperty(area);
        this.Smoothness = new SimpleIntegerProperty(smoothness);
        this.Compactness = new SimpleIntegerProperty(compactness);
        this.Concavity = new SimpleIntegerProperty(concavity);
        this.ConcavePoints = new SimpleIntegerProperty(concavePoints);
        this.Symmetry = new SimpleIntegerProperty(symmetry);
        this.FractalDimension = new SimpleIntegerProperty(fractalDimension);
        this.Decision = new SimpleIntegerProperty(decision);

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

    public int getRadius() {
        return Radius.get();
    }

    public void setRadius(int radius) {
        this.Radius.set(radius);
    }

    public SimpleIntegerProperty radiusProperty() {
        return Radius;
    }

    public int getTexture() {
        return Texture.get();
    }

    public void setTexture(int texture) {
        this.Texture.set(texture);
    }

    public SimpleIntegerProperty textureProperty() {
        return Texture;
    }

    public int getPerimeter() {
        return Perimeter.get();
    }

    public void setPerimeter(int perimeter) {
        this.Perimeter.set(perimeter);
    }

    public SimpleIntegerProperty perimeterProperty() {
        return Perimeter;
    }

    public int getArea() {
        return Area.get();
    }

    public void setArea(int area) {
        this.Area.set(area);
    }

    public SimpleIntegerProperty areaProperty() {
        return Area;
    }

    public int getSmoothness() {
        return Smoothness.get();
    }

    public void setSmoothness(int smoothness) {
        this.Smoothness.set(smoothness);
    }

    public SimpleIntegerProperty smoothnessProperty() {
        return Smoothness;
    }

    public int getCompactness() {
        return Compactness.get();
    }

    public void setCompactness(int compactness) {
        this.Compactness.set(compactness);
    }

    public SimpleIntegerProperty compactnessProperty() {
        return Compactness;
    }

    public int getConcavity() {
        return Concavity.get();
    }

    public void setConcavity(int concavity) {
        this.Concavity.set(concavity);
    }

    public SimpleIntegerProperty concavityProperty() {
        return Concavity;
    }

    public int getConcavePoints() {
        return ConcavePoints.get();
    }

    public void setConcavePoints(int concavePoints) {
        this.ConcavePoints.set(concavePoints);
    }

    public SimpleIntegerProperty concavePointsProperty() {
        return ConcavePoints;
    }

    public int getSymmetry() {
        return Symmetry.get();
    }

    public void setSymmetry(int symmetry) {
        this.Symmetry.set(symmetry);
    }

    public SimpleIntegerProperty symmetryProperty() {
        return Symmetry;
    }

    public int getFractalDimension() {
        return FractalDimension.get();
    }

    public void setFractalDimension(int fractalDimension) {
        this.FractalDimension.set(fractalDimension);
    }

    public SimpleIntegerProperty fractalDimensionProperty() {
        return FractalDimension;
    }

    public int getDecision() {
        return Decision.get();
    }

    public void setDecision(int decision) {
        this.Decision.set(decision);
    }

    public SimpleIntegerProperty decisionProperty() {
        return Decision;
    }

    public boolean checkIfEmpty() {
        return Radius.get() == 0 || Texture.get() == 0 || Perimeter.get() == 0 || Area.get() == 0 || Smoothness.get() == 0 ||
                Compactness.get() == 0 || Concavity.get() == 0 || ConcavePoints.get() == 0 || Symmetry.get() == 0 ||
                FractalDimension.get() == 0;
    }

}