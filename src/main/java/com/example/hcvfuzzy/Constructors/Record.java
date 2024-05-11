package com.example.hcvfuzzy.Constructors;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Record {
    private int ID;
    private int Radius;
    private int Texture;
    private int Perimeter;
    private int Area;
    private int Smoothness;
    private int Compactness;
    private int Concavity;
    private int ConcavePoints;
    private int Symmetry;
    private int FractalDimension;
    private int Decision;


    public Record(int ID, int radius, int texture, int perimeter, int area, int smoothness, int compactness, int concavity, int concavePoints, int symmetry, int fractalDimension, int decision) {
        this.ID = ID;
        this.Radius = radius;
        this.Texture = texture;
        this.Perimeter = perimeter;
        this.Area = area;
        this.Smoothness = smoothness;
        this.Compactness = compactness;
        this.Concavity = concavity;
        this.ConcavePoints = concavePoints;
        this.Symmetry = symmetry;
        this.FractalDimension = fractalDimension;
        this.Decision = decision;
    }

    public int getAttributeValue(String attributeName) {
        return switch (attributeName) {
            case "radius" -> Radius;
            case "texture" -> Texture;
            case "perimeter" -> Perimeter;
            case "area" -> Area;
            case "smoothness" -> Smoothness;
            case "compactness" -> Compactness;
            case "concavity" -> Concavity;
            case "concavePoints" -> ConcavePoints;
            case "symmetry" -> Symmetry;
            case "fractalDimension" -> FractalDimension;
            default -> throw new IllegalArgumentException("Nieprawidłowa nazwa atrybutu: " + attributeName);
        };
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDecision() {
        return Decision;
    }

    public void setDecision(int decision) {
        Decision = decision;
    }

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    public int getTexture() {
        return Texture;
    }

    public void setTexture(int texture) {
        Texture = texture;
    }

    public int getPerimeter() {
        return Perimeter;
    }

    public void setPerimeter(int perimeter) {
        Perimeter = perimeter;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }

    public int getSmoothness() {
        return Smoothness;
    }

    public void setSmoothness(int smoothness) {
        Smoothness = smoothness;
    }

    public int getCompactness() {
        return Compactness;
    }

    public void setCompactness(int compactness) {
        Compactness = compactness;
    }

    public int getConcavity() {
        return Concavity;
    }

    public void setConcavity(int concavity) {
        Concavity = concavity;
    }

    public int getConcavePoints() {
        return ConcavePoints;
    }

    public void setConcavePoints(int concavePoints) {
        ConcavePoints = concavePoints;
    }

    public int getSymmetry() {
        return Symmetry;
    }

    public void setSymmetry(int symmetry) {
        Symmetry = symmetry;
    }

    public int getFractalDimension() {
        return FractalDimension;
    }

    public void setFractalDimension(int fractalDimension) {
        FractalDimension = fractalDimension;
    }
}
//    public void normalizeData(List<Record> dataList) throws IllegalAccessException, NoSuchFieldException {
//
//        List<String> FieldsArray = new ArrayList<>(Arrays.asList("Radius","Texture"));
//
//        Class<?> objClass = getClass();
//
//        // Get all fields of the class including private fields
//        Field[] fields = objClass.getDeclaredFields();
//
//        // Iterate over the fields
//        for (Field field : fields) {
//            int minRecordObject= Integer.MAX_VALUE;
//            int maxRecordObject= Integer.MIN_VALUE;
//            field.setAccessible(true); // Make the private field accessible
//            if(FieldsArray.contains(field.getName())){
//                Field field1 = objClass.getDeclaredField("Normalized"+field.getName());
//                int value = Integer.parseInt(field.get(this).toString();
//                if (value < minRecordObject) {
//                    minRecordObject = value;
//                }
//                if (value > maxRecordObject) {
//                    maxRecordObject = value;
//                }
//            }
//            Object value = field.get(this); // Get the value of the field from the object
//            System.out.println(field.getName() + ": " + value);
//        }
//
//
//
//
//        for (int i = 0; i < dataList.size(); i++) {
//            Record record = dataList.get(i);
//
//            int recordObject = extractor.apply(dataList.get(i));
//            //System.out.println("recordObject: "+recordObject);
//            System.out.println("Max: " + maxRecordObject + " - Min: " + minRecordObject);
//            double normalized = (((double) recordObject - minRecordObject) / (maxRecordObject - minRecordObject));
//            String formattedValue = String.format("%.5f", normalized);
//            System.out.println(i + 1 + " - " + formattedValue);
//        }
//    }



