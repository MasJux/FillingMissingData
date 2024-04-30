package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;
import com.example.hcvfuzzy.Constructors.Record;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class SecondMethod {
    double sumRadius = 0;
    double sumTexture = 0;
    double sumPerimeter = 0;
    double sumArea = 0;
    double sumSmoothness = 0;
    double sumCompactness = 0;
    double sumConcavity = 0;
    double sumConcavePoints = 0;
    double sumSymmetry = 0;
    double sumFractalDimension = 0;
    double countRadius = 0;
    double countTexture = 0;
    double countPerimeter = 0;
    double countArea = 0;
    double countSmoothness = 0;
    double countCompactness = 0;
    double countConcavity = 0;
    double countConcavePoints = 0;
    double countSymmetry = 0;
    double countFractalDimension = 0;

    public void secondFillingMethod(List<NormalizedRecord> dataList) {
        resetCounts();
        resetSum();
        for (NormalizedRecord rec : dataList) {
            double radius = rec.getNormalizedRadius();
            double texture = rec.getNormalizedTexture();
            double perimeter = rec.getNormalizedPerimeter();
            double area = rec.getNormalizedArea();
            double smoothness = rec.getNormalizedSmoothness();
            double compactness = rec.getNormalizedCompactness();
            double concavity = rec.getNormalizedConcavity();
            double concavePoints = rec.getNormalizedConcavePoints();
            double symmetry = rec.getNormalizedSymmetry();
            double fractalDimension = rec.getNormalizedFractalDimension();
            if (radius != -1) {
                countRadius += 1;
                sumRadius += radius;
            }
            if (texture != -1) {
                countTexture += 1;
                sumTexture += texture;
            }
            if (perimeter != -1) {
                countPerimeter += 1;
                sumPerimeter += perimeter;
            }
            if (area != -1) {
                countArea += 1;
                sumArea += area;
            }
            if (smoothness != -1) {
                countSmoothness += 1;
                sumSmoothness += smoothness;
            }
            if (compactness != -1) {
                countCompactness += 1;
                sumCompactness += compactness;
            }
            if (concavity != -1) {
                countConcavity += 1;
                sumConcavity += concavity;
            }
            if (concavePoints != -1) {
                countConcavePoints += 1;
                sumConcavePoints += concavePoints;
            }
            if (symmetry != -1) {
                countSymmetry += 1;
                sumSymmetry += symmetry;
            }
            if (fractalDimension != -1) {
                countFractalDimension += 1;
                sumFractalDimension += fractalDimension;
            }
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.#####",symbols);
        double avgRadius = Double.parseDouble(df.format((double)sumRadius / countRadius));
        double avgTexture = Double.parseDouble(df.format((double)sumTexture / countTexture));
        double avgPerimeter = Double.parseDouble(df.format((double)sumPerimeter / countPerimeter));
        double avgArea = Double.parseDouble(df.format((double)sumArea / countArea));
        double avgSmoothness = Double.parseDouble(df.format((double)sumSmoothness / countSmoothness));
        double avgCompactness = Double.parseDouble(df.format((double)sumCompactness / countCompactness));
        double avgConcavity = Double.parseDouble(df.format((double)sumConcavity / countConcavity));
        double avgConcavePoints = Double.parseDouble(df.format((double)sumConcavePoints / countConcavePoints));
        double avgSymmetry = Double.parseDouble(df.format((double)sumSymmetry / countSymmetry));
        double avgFractalDimension = Double.parseDouble(df.format((double)sumFractalDimension / countFractalDimension));
        System.out.println("---------------------");
        System.out.println("Average Radius: "+avgRadius);
        System.out.println("Sum: "+sumRadius);
        System.out.println("Count: "+countRadius);

        for (NormalizedRecord updateRecord : dataList) {
            if (updateRecord.getNormalizedRadius() == -1) {
                updateRecord.setNormalizedRadius(avgRadius);
            }
            if (updateRecord.getNormalizedTexture() == -1) {
                updateRecord.setNormalizedTexture(avgTexture);
            }
            if (updateRecord.getNormalizedPerimeter() == -1) {
                updateRecord.setNormalizedPerimeter(avgPerimeter);
            }
            if (updateRecord.getNormalizedArea() == -1) {
                updateRecord.setNormalizedArea(avgArea);
            }
            if (updateRecord.getNormalizedSmoothness() == -1) {
                updateRecord.setNormalizedSmoothness(avgSmoothness);
            }
            if (updateRecord.getNormalizedCompactness() == -1) {
                updateRecord.setNormalizedCompactness(avgCompactness);
            }
            if (updateRecord.getNormalizedConcavity() == -1) {
                updateRecord.setNormalizedConcavity(avgConcavity);
            }
            if (updateRecord.getNormalizedConcavePoints() == -1) {
                updateRecord.setNormalizedConcavePoints(avgConcavePoints);
            }
            if (updateRecord.getNormalizedSymmetry() == -1) {
                updateRecord.setNormalizedSymmetry(avgSymmetry);
            }
            if (updateRecord.getNormalizedFractalDimension() == -1) {
                updateRecord.setNormalizedFractalDimension(avgFractalDimension);
            }
        }
    }

    public void resetCounts() {
        countRadius = 0;
        countTexture = 0;
        countPerimeter = 0;
        countArea = 0;
        countSmoothness = 0;
        countCompactness = 0;
        countConcavity = 0;
        countConcavePoints = 0;
        countSymmetry = 0;
        countFractalDimension = 0;
    }
    public void resetSum() {
        sumRadius = 0;
        sumTexture = 0;
        sumPerimeter = 0;
        sumArea = 0;
        sumSmoothness = 0;
        sumCompactness = 0;
        sumConcavity = 0;
        sumConcavePoints = 0;
        sumSymmetry = 0;
        sumFractalDimension = 0;
    }
}
/*
    public void getMinMaxValues(List<Record> dataList) {
        int minRadius = Integer.MAX_VALUE;
        int maxRadius = Integer.MIN_VALUE;
        int minTexture = Integer.MAX_VALUE;
        int maxTexture = Integer.MIN_VALUE;
        int minPerimeter = Integer.MAX_VALUE;
        int maxPerimeter = Integer.MIN_VALUE;
        int minArea = Integer.MAX_VALUE;
        int maxArea = Integer.MIN_VALUE;
        int minSmoothness = Integer.MAX_VALUE;
        int maxSmoothness = Integer.MIN_VALUE;
        int minCompactness = Integer.MAX_VALUE;
        int maxCompactness = Integer.MIN_VALUE;
        int minConcavity = Integer.MAX_VALUE;
        int maxConcavity = Integer.MIN_VALUE;
        int minConcavePoints = Integer.MAX_VALUE;
        int maxConcavePoints = Integer.MIN_VALUE;
        int minSymmetry = Integer.MAX_VALUE;
        int maxSymmetry = Integer.MIN_VALUE;
        int minFractalDimension = Integer.MAX_VALUE;
        int maxFractalDimension = Integer.MIN_VALUE;

        for (Record rec : dataList) {
            int radius = rec.getRadius();
            int texture = rec.getTexture();
            int perimeter = rec.getPerimeter();
            int area = rec.getArea();
            int smoothness = rec.getSmoothness();
            int compactness = rec.getCompactness();
            int concavity = rec.getConcavity();
            int concavePoints = rec.getConcavePoints();
            int symmetry = rec.getSymmetry();
            int fractalDimension = rec.getFractalDimension();
            for (int i = 0; i < dataList.size(); i++) {
                switch (i) {
                    case 0 -> {
                        if (radius < minRadius) {
                            minRadius = radius;
                        }
                        if (radius > maxRadius) {
                            maxRadius = radius;
                        }
                    }
                    case 1 -> {
                        if (texture < minTexture) {
                            minTexture = texture;
                        }
                        if (texture > maxTexture) {
                            maxTexture = texture;
                        }
                    }
                    case 2 -> {
                        if (perimeter < minPerimeter) {
                            minPerimeter = perimeter;
                        }
                        if (perimeter > maxPerimeter) {
                            maxPerimeter = perimeter;
                        }
                    }
                    case 3 -> {
                        if (area < minArea) {
                            minArea = area;
                        }
                        if (area > maxArea) {
                            maxArea = area;
                        }
                    }
                    case 4 -> {
                        if (smoothness < minSmoothness) {
                            minSmoothness = smoothness;
                        }
                        if (smoothness > maxSmoothness) {
                            maxSmoothness = smoothness;
                        }
                    }
                    case 5 -> {
                        if (compactness < minCompactness) {
                            minCompactness = compactness;
                        }
                        if (compactness > maxCompactness) {
                            maxCompactness = compactness;
                        }
                    }
                    case 6 -> {
                        if (concavity < minConcavity) {
                            minConcavity = concavity;
                        }
                        if (concavity > maxConcavity) {
                            maxConcavity = concavity;
                        }
                    }
                    case 7 -> {
                        if (concavePoints < minConcavePoints) {
                            minConcavePoints = concavePoints;
                        }
                        if (concavePoints > maxConcavePoints) {
                            maxConcavePoints = concavePoints;
                        }
                    }
                    case 8 -> {
                        if (symmetry < minSymmetry) {
                            minSymmetry = symmetry;
                        }
                        if (symmetry > maxSymmetry) {
                            maxSymmetry = symmetry;
                        }
                    }
                    case 9 -> {
                        if (fractalDimension < minFractalDimension) {
                            minFractalDimension = fractalDimension;
                        }
                        if (fractalDimension > maxFractalDimension) {
                            maxFractalDimension = fractalDimension;
                        }
                    }
                }
            }
        }
        System.out.println("max radius: "+maxRadius);
        System.out.println("min radius: "+minRadius);
        System.out.println("max texture: "+maxTexture);
        System.out.println("min texture: "+minTexture);
    }
*/
/*
    public double euclidesDistance(List<Record> dataList) {

        Map<Integer, Double> euclidesMap = new HashMap<>();

        for (Record record1 : dataList) {
            double sum = 0.0;
            //System.out.println("record 1: "+record1.getID());
            int[] object1 = {record1.getRadius(), record1.getTexture(), record1.getPerimeter(), record1.getArea(), record1.getSmoothness(),
                    record1.getCompactness(), record1.getConcavity(), record1.getConcavePoints(), record1.getSymmetry(), record1.getFractalDimension()};

            for (Record record2 : dataList) {
                if (record1 == record2) {
                    continue;
                }
                //System.out.println("record 2: "+record2.getID());
                int[] object2 = {record2.getRadius(), record2.getTexture(), record2.getPerimeter(), record2.getArea(), record2.getSmoothness(),
                        record2.getCompactness(), record2.getConcavity(), record2.getConcavePoints(), record2.getSymmetry(), record2.getFractalDimension()};
                for (int i = 0; i < object1.length; i++) {
                    sum += Math.pow(object1[i] - object2[i], 2);
                    //System.out.println("suma: "+sum);
                }
            }
            double total = Math.sqrt(sum);
            euclidesMap.put(record1.getID(), total);
        }
        for(Map.Entry<Integer, Double> entry : euclidesMap.entrySet()){
            int key = entry.getKey();
            double value = entry.getValue();
            System.out.println("Key: "+key+", Value: "+value);
        }

        return euclidesMap.get(dataList.get(dataList.size()-1).getID());
    }
}
/*

/*
    public List<Object> mostSimilar(List<Record> dataset, int n) {
        List<Object> mostSimilarList = new ArrayList<>();
        Map<Object, Double> probability = new HashMap<>();

        for (Record basicObject : dataset) {
            int radius1 = basicObject.getRadius();
            int texture1 = basicObject.getTexture();
            int perimeter1 = basicObject.getPerimeter();
            int area1 = basicObject.getArea();
            int smoothness1 = basicObject.getSmoothness();
            int compactness1 = basicObject.getCompactness();
            int concavity1 = basicObject.getConcavity();
            int concavePoints1 = basicObject.getConcavePoints();
            int symmetry1 = basicObject.getSymmetry();
            int fractalDimension1 = basicObject.getFractalDimension();

                for (Record comparedObject:dataset) {
                    int radius2 = comparedObject.getRadius();
                    int texture2 = comparedObject.getTexture();
                    int perimeter2 = comparedObject.getPerimeter();
                    int area2 = comparedObject.getArea();
                    int smoothness2 = comparedObject.getSmoothness();
                    int compactness2 = comparedObject.getCompactness();
                    int concavity2 = comparedObject.getConcavity();
                    int concavePoints2 = comparedObject.getConcavePoints();
                    int symmetry2 = comparedObject.getSymmetry();
                    int fractalDimension2 = comparedObject.getFractalDimension();
                    boolean checkIfEmpty = comparedObject.checkIfEmpty();

                    if (basicObject == comparedObject || checkIfEmpty) {
                        continue;
                    }

                    double distance = distanceEuclides(radius1, texture1, perimeter1, area1, smoothness1,
                            compactness1, concavity1, concavePoints1, symmetry1, fractalDimension1, radius2,
                            texture2, perimeter2, area2, smoothness2, compactness2, concavity2, concavePoints2,
                            symmetry2, fractalDimension2);
                    probability.put(comparedObject,distance);
                }
            List<Map.Entry<Object, Double>> sortedDistances = probability.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue()).toList();
            for (int i = 0; i < n; i++) {
                mostSimilarList.add(sortedDistances.get(i).getKey());
            }
        }

            return mostSimilarList;
        }
    private double distanceEuclides(int radius1, int texture1, int perimeter1,
                                              int area1, int smoothness1, int compactness1,
                                              int concavity1, int concavePoints1, int symmetry1,
                                              int fractalDimension1, int radius2, int texture2,
                                              int perimeter2, int area2, int smoothness2,
                                              int compactness2, int concavity2, int concavePoints2,
                                              int symmetry2, int fractalDimension2) {
        // Oblicz odległość euklidesową dla każdego atrybutu
        double odlegloscKwadratowa = Math.pow(radius1 - radius2, 2)
                + Math.pow(texture1 - texture2, 2)
                + Math.pow(perimeter1 - perimeter2, 2)
                + Math.pow(area1 - area2, 2)
                + Math.pow(smoothness1 - smoothness2,2)
                + Math.pow(compactness1 - compactness2, 2)
                + Math.pow(concavity1 - concavity2, 2)
                + Math.pow(concavePoints1 - concavePoints2, 2)
                + Math.pow(symmetry1 - symmetry2, 2)
                + Math.pow(fractalDimension1 - fractalDimension2, 2);

        // Pierwiastek kwadratowy z sumy kwadratów różnic
        double odleglosc = Math.sqrt(odlegloscKwadratowa);
        return odleglosc;
    }
 */

