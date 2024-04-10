package com.example.hcvfuzzy.Constructors;

public class NormalizedRecord {
    private int ID;
    private double NormalizedRadius;
    private double NormalizedTexture;
    private double NormalizedPerimeter;
    private double NormalizedArea;
    private double NormalizedSmoothness;
    private double NormalizedCompactness;
    private double NormalizedConcavity;
    private double NormalizedConcavePoints;
    private double NormalizedSymmetry;
    private double NormalizedFractalDimension;
    private int Decision;

    public NormalizedRecord() {
    }
    public double getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "radius":
                return NormalizedRadius;
            case "texture":
                return NormalizedTexture;
            case "perimeter":
                return NormalizedPerimeter;
            case "area":
                return NormalizedArea;
            case "smoothness":
                return NormalizedSmoothness;
            case "compactness":
                return NormalizedCompactness;
            case "concavity":
                return NormalizedConcavity;
            case "concavePoints":
                return NormalizedConcavePoints;
            case "symmetry":
                return NormalizedSymmetry;
            case "fractalDimension":
                return NormalizedFractalDimension;
            default:
                throw new IllegalArgumentException("Nieprawidłowa nazwa atrybutu: " + attributeName);
        }
    }

    public void setAttributeValue(String attributeName, double normalizedValue) {
        switch (attributeName) {
            case "radius":
                setNormalizedRadius(normalizedValue);
                break;
            case "texture":
                setNormalizedTexture(normalizedValue);
                break;
            case "perimeter":
                setNormalizedPerimeter(normalizedValue);
                break;
            case "area":
                setNormalizedArea(normalizedValue);
                break;
            case "smoothness":
                setNormalizedSmoothness(normalizedValue);
                break;
            case "compactness":
                setNormalizedCompactness(normalizedValue);
                break;
            case "concavity":
                setNormalizedConcavity(normalizedValue);
                break;
            case "concavePoints":
                setNormalizedConcavePoints(normalizedValue);
                break;
            case "symmetry":
                setNormalizedSymmetry(normalizedValue);
                break;
            case "fractalDimension":
                setNormalizedFractalDimension(normalizedValue);
                break;
            default:
                throw new IllegalArgumentException("Nieprawidłowa nazwa atrybutu: " + attributeName);
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getNormalizedRadius() {
        return NormalizedRadius;
    }

    public void setNormalizedRadius(double normalizedRadius) {
        NormalizedRadius = normalizedRadius;
    }

    public double getNormalizedTexture() {
        return NormalizedTexture;
    }

    public void setNormalizedTexture(double normalizedTexture) {
        NormalizedTexture = normalizedTexture;
    }

    public double getNormalizedPerimeter() {
        return NormalizedPerimeter;
    }

    public void setNormalizedPerimeter(double normalizedPerimeter) {
        NormalizedPerimeter = normalizedPerimeter;
    }

    public double getNormalizedArea() {
        return NormalizedArea;
    }

    public void setNormalizedArea(double normalizedArea) {
        NormalizedArea = normalizedArea;
    }

    public double getNormalizedSmoothness() {
        return NormalizedSmoothness;
    }

    public void setNormalizedSmoothness(double normalizedSmoothness) {
        NormalizedSmoothness = normalizedSmoothness;
    }

    public double getNormalizedCompactness() {
        return NormalizedCompactness;
    }

    public void setNormalizedCompactness(double normalizedCompactness) {
        NormalizedCompactness = normalizedCompactness;
    }

    public double getNormalizedConcavity() {
        return NormalizedConcavity;
    }

    public void setNormalizedConcavity(double normalizedConcavity) {
        NormalizedConcavity = normalizedConcavity;
    }

    public double getNormalizedConcavePoints() {
        return NormalizedConcavePoints;
    }

    public void setNormalizedConcavePoints(double normalizedConcavePoints) {
        NormalizedConcavePoints = normalizedConcavePoints;
    }

    public double getNormalizedSymmetry() {
        return NormalizedSymmetry;
    }

    public void setNormalizedSymmetry(double normalizedSymmetry) {
        NormalizedSymmetry = normalizedSymmetry;
    }

    public double getNormalizedFractalDimension() {
        return NormalizedFractalDimension;
    }

    public void setNormalizedFractalDimension(double normalizedFractalDimension) {
        NormalizedFractalDimension = normalizedFractalDimension;
    }

    public int getDecision() {
        return Decision;
    }

    public void setDecision(int decision) {
        Decision = decision;
    }
}