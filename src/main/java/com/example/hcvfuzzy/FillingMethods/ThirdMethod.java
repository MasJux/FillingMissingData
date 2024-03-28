package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Constructors.Record;

import java.util.List;

public class ThirdMethod {

    int sumRadiusDecisionTwo, sumRadiusDecisionFour = 0;
    int sumTextureDecisionTwo, sumTextureDecisionFour = 0;
    int sumPerimeterDecisionTwo, sumPerimeterDecisionFour = 0;
    int sumAreaDecisionTwo, sumAreaDecisionFour = 0;
    int sumSmoothnessDecisionTwo, sumSmoothnessDecisionFour = 0;
    int sumCompactnessDecisionTwo, sumCompactnessDecisionFour = 0;
    int sumConcavityDecisionTwo, sumConcavityDecisionFour = 0;
    int sumConcavePointsDecisionTwo, sumConcavePointsDecisionFour = 0;
    int sumSymmetryDecisionTwo, sumSymmetryDecisionFour = 0;
    int sumFractalDimensionDecisionTwo, sumFractalDimensionDecisionFour = 0;
    int countRadiusDecisionTwo, countRadiusDecisionFour = 0;
    int countTextureDecisionTwo, countTextureDecisionFour = 0;
    int countPerimeterDecisionTwo, countPerimeterDecisionFour = 0;
    int countAreaDecisionTwo, countAreaDecisionFour = 0;
    int countSmoothnessDecisionTwo, countSmoothnessDecisionFour = 0;
    int countCompactnessDecisionTwo, countCompactnessDecisionFour = 0;
    int countConcavityDecisionTwo, countConcavityDecisionFour = 0;
    int countConcavePointsDecisionTwo, countConcavePointsDecisionFour = 0;
    int countSymmetryDecisionTwo, countSymmetryDecisionFour = 0;
    int countFractalDimensionDecisionTwo, countFractalDimensionDecisionFour = 0;

    public void thirdFillingMethod(List<Record> dataList) {
        resetCountsThirdMethod();
        resetSumThirdMethod();
        for (Record rec : dataList) {
            int id = rec.getID();
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
            int decision = rec.getDecision();

            if (decision == 2) {
                if (radius != -1) {
                    countRadiusDecisionTwo += 1;
                    sumRadiusDecisionTwo += radius;
                }
                if (texture != -1) {
                    countTextureDecisionTwo += 1;
                    sumTextureDecisionTwo += texture;
                }
                if (perimeter != -1) {
                    countPerimeterDecisionTwo += 1;
                    sumPerimeterDecisionTwo += perimeter;
                }
                if (area != -1) {
                    countAreaDecisionTwo += 1;
                    sumAreaDecisionTwo += area;
                }
                if (smoothness != -1) {
                    countSmoothnessDecisionTwo += 1;
                    sumSmoothnessDecisionTwo += smoothness;
                }
                if (compactness != -1) {
                    countCompactnessDecisionTwo += 1;
                    sumCompactnessDecisionTwo += compactness;
                }
                if (concavity != -1) {
                    countConcavityDecisionTwo += 1;
                    sumConcavityDecisionTwo += concavity;
                }
                if (concavePoints != -1) {
                    countConcavePointsDecisionTwo += 1;
                    sumConcavePointsDecisionTwo += concavePoints;
                }
                if (symmetry != -1) {
                    countSymmetryDecisionTwo += 1;
                    sumSymmetryDecisionTwo += symmetry;
                }
                if (fractalDimension != -1) {
                    countFractalDimensionDecisionTwo += 1;
                    sumFractalDimensionDecisionTwo += fractalDimension;
                }
            }
            if(decision == 4){
                if (radius != -1) {
                    countRadiusDecisionFour += 1;
                    sumRadiusDecisionFour += radius;
                }
                if (texture != -1) {
                    countTextureDecisionFour += 1;
                    sumTextureDecisionFour += texture;
                }
                if (perimeter != -1) {
                    countPerimeterDecisionFour += 1;
                    sumPerimeterDecisionFour += perimeter;
                }
                if (area != -1) {
                    countAreaDecisionFour += 1;
                    sumAreaDecisionFour += area;
                }
                if (smoothness != -1) {
                    countSmoothnessDecisionFour += 1;
                    sumSmoothnessDecisionFour += smoothness;
                }
                if (compactness != -1) {
                    countCompactnessDecisionFour += 1;
                    sumCompactnessDecisionFour += compactness;
                }
                if (concavity != -1) {
                    countConcavityDecisionFour += 1;
                    sumConcavityDecisionFour += concavity;
                }
                if (concavePoints != -1) {
                    countConcavePointsDecisionFour += 1;
                    sumConcavePointsDecisionFour += concavePoints;
                }
                if (symmetry != -1) {
                    countSymmetryDecisionFour += 1;
                    sumSymmetryDecisionFour += symmetry;
                }
                if (fractalDimension != -1) {
                    countFractalDimensionDecisionFour += 1;
                    sumFractalDimensionDecisionFour += fractalDimension;
                }
            }
        }
        int avgRadiusDecisionTwo = sumRadiusDecisionTwo / countRadiusDecisionTwo;
        int avgTextureDecisionTwo = sumTextureDecisionTwo / countTextureDecisionTwo;
        int avgPerimeterDecisionTwo = sumPerimeterDecisionTwo / countPerimeterDecisionTwo;
        int avgAreaDecisionTwo = sumAreaDecisionTwo / countAreaDecisionTwo;
        int avgSmoothnessDecisionTwo = sumSmoothnessDecisionTwo / countSmoothnessDecisionTwo;
        int avgCompactnessDecisionTwo = sumCompactnessDecisionTwo / countCompactnessDecisionTwo;
        int avgConcavityDecisionTwo = sumConcavityDecisionTwo / countConcavityDecisionTwo;
        int avgConcavePointsDecisionTwo = sumConcavePointsDecisionTwo / countConcavePointsDecisionTwo;
        int avgSymmetryDecisionTwo = sumSymmetryDecisionTwo / countSymmetryDecisionTwo;
        int avgFractalDimensionDecisionTwo = sumFractalDimensionDecisionTwo / countFractalDimensionDecisionTwo;

        int avgRadiusDecisionFour = sumRadiusDecisionFour / countRadiusDecisionFour;
        int avgTextureDecisionFour = sumTextureDecisionFour / countTextureDecisionFour;
        int avgPerimeterDecisionFour = sumPerimeterDecisionFour / countPerimeterDecisionFour;
        int avgAreaDecisionFour = sumAreaDecisionFour / countAreaDecisionFour;
        int avgSmoothnessDecisionFour = sumSmoothnessDecisionFour / countSmoothnessDecisionFour;
        int avgCompactnessDecisionFour = sumCompactnessDecisionFour / countCompactnessDecisionFour;
        int avgConcavityDecisionFour = sumConcavityDecisionFour / countConcavityDecisionFour;
        int avgConcavePointsDecisionFour = sumConcavePointsDecisionFour / countConcavePointsDecisionFour;
        int avgSymmetryDecisionFour = sumSymmetryDecisionFour / countSymmetryDecisionFour;
        int avgFractalDimensionDecisionFour = sumFractalDimensionDecisionFour / countFractalDimensionDecisionFour;

        System.out.println("--------------------");
        System.out.println("Average Radius for decision two: "+avgRadiusDecisionTwo);
        System.out.println("Sum and Count D2: "+sumRadiusDecisionTwo+" - "+countRadiusDecisionTwo);
        System.out.println("Average Radius for decision four: "+avgRadiusDecisionFour);
        System.out.println("Sum and Count D4: "+sumRadiusDecisionFour+" - "+countRadiusDecisionFour);


        for (Record updateRecord : dataList) {
            if (updateRecord.getDecision() == 2) {
                if (updateRecord.getRadius() == -1) {
                    updateRecord.setRadius(avgRadiusDecisionTwo);
                }
                if (updateRecord.getTexture() == -1) {
                    updateRecord.setTexture(avgTextureDecisionTwo);
                }
                if (updateRecord.getPerimeter() == -1) {
                    updateRecord.setPerimeter(avgPerimeterDecisionTwo);
                }
                if (updateRecord.getArea() == -1) {
                    updateRecord.setArea(avgAreaDecisionTwo);
                }
                if (updateRecord.getSmoothness() == -1) {
                    updateRecord.setSmoothness(avgSmoothnessDecisionTwo);
                }
                if (updateRecord.getCompactness() == -1) {
                    updateRecord.setCompactness(avgCompactnessDecisionTwo);
                }
                if (updateRecord.getConcavity() == -1) {
                    updateRecord.setConcavity(avgConcavityDecisionTwo);
                }
                if (updateRecord.getConcavePoints() == -1) {
                    updateRecord.setConcavePoints(avgConcavePointsDecisionTwo);
                }
                if (updateRecord.getSymmetry() == -1) {
                    updateRecord.setSymmetry(avgSymmetryDecisionTwo);
                }
                if (updateRecord.getFractalDimension() == -1) {
                    updateRecord.setFractalDimension(avgFractalDimensionDecisionTwo);
                }
            }
            if (updateRecord.getDecision() == 4){
                if (updateRecord.getRadius() == -1) {
                    updateRecord.setRadius(avgRadiusDecisionFour);
                }
                if (updateRecord.getTexture() == -1) {
                    updateRecord.setTexture(avgTextureDecisionFour);
                }
                if (updateRecord.getPerimeter() == -1) {
                    updateRecord.setPerimeter(avgPerimeterDecisionFour);
                }
                if (updateRecord.getArea() == -1) {
                    updateRecord.setArea(avgAreaDecisionFour);
                }
                if (updateRecord.getSmoothness() == -1) {
                    updateRecord.setSmoothness(avgSmoothnessDecisionFour);
                }
                if (updateRecord.getCompactness() == -1) {
                    updateRecord.setCompactness(avgCompactnessDecisionFour);
                }
                if (updateRecord.getConcavity() == -1) {
                    updateRecord.setConcavity(avgConcavityDecisionFour);
                }
                if (updateRecord.getConcavePoints() == -1) {
                    updateRecord.setConcavePoints(avgConcavePointsDecisionFour);
                }
                if (updateRecord.getSymmetry() == -1) {
                    updateRecord.setSymmetry(avgSymmetryDecisionFour);
                }
                if (updateRecord.getFractalDimension() == -1) {
                    updateRecord.setFractalDimension(avgFractalDimensionDecisionFour);
                }
            }
        }
    }

    public void resetCountsThirdMethod() {
        countRadiusDecisionTwo = 0;
        countTextureDecisionTwo = 0;
        countPerimeterDecisionTwo = 0;
        countAreaDecisionTwo = 0;
        countSmoothnessDecisionTwo = 0;
        countCompactnessDecisionTwo = 0;
        countConcavityDecisionTwo = 0;
        countConcavePointsDecisionTwo = 0;
        countSymmetryDecisionTwo = 0;
        countFractalDimensionDecisionTwo = 0;

        countRadiusDecisionFour = 0;
        countTextureDecisionFour= 0;
        countPerimeterDecisionFour = 0;
        countAreaDecisionFour = 0;
        countSmoothnessDecisionFour = 0;
        countCompactnessDecisionFour = 0;
        countConcavityDecisionFour = 0;
        countConcavePointsDecisionFour = 0;
        countSymmetryDecisionFour = 0;
        countFractalDimensionDecisionFour = 0;
    }
    public void resetSumThirdMethod() {
        sumRadiusDecisionTwo = 0;
        sumTextureDecisionTwo = 0;
        sumPerimeterDecisionTwo = 0;
        sumAreaDecisionTwo = 0;
        sumSmoothnessDecisionTwo = 0;
        sumCompactnessDecisionTwo = 0;
        sumConcavityDecisionTwo = 0;
        sumConcavePointsDecisionTwo = 0;
        sumSymmetryDecisionTwo = 0;
        sumFractalDimensionDecisionTwo = 0;

        sumRadiusDecisionFour = 0;
        sumTextureDecisionFour= 0;
        sumPerimeterDecisionFour = 0;
        sumAreaDecisionFour = 0;
        sumSmoothnessDecisionFour = 0;
        sumCompactnessDecisionFour = 0;
        sumConcavityDecisionFour = 0;
        sumConcavePointsDecisionFour = 0;
        sumSymmetryDecisionFour = 0;
        sumFractalDimensionDecisionFour = 0;
    }
}
