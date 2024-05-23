//package com.example.hcvfuzzy.FillingMethods;
//
//import com.example.hcvfuzzy.Objects.NormalizedRecord;
//
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.List;
//import java.util.Locale;
//
//public class ThirdMethod {
//
//    double sumRadiusDecisionTwo, sumRadiusDecisionFour = 0.0;
//    double sumTextureDecisionTwo, sumTextureDecisionFour = 0.0;
//    double sumPerimeterDecisionTwo, sumPerimeterDecisionFour = 0.0;
//    double sumAreaDecisionTwo, sumAreaDecisionFour = 0.0;
//    double sumSmoothnessDecisionTwo, sumSmoothnessDecisionFour = 0.0;
//    double sumCompactnessDecisionTwo, sumCompactnessDecisionFour = 0.0;
//    double sumConcavityDecisionTwo, sumConcavityDecisionFour = 0.0;
//    double sumConcavePointsDecisionTwo, sumConcavePointsDecisionFour = 0.0;
//    double sumSymmetryDecisionTwo, sumSymmetryDecisionFour = 0.0;
//    double sumFractalDimensionDecisionTwo, sumFractalDimensionDecisionFour = 0.0;
//    double countRadiusDecisionTwo, countRadiusDecisionFour = 0.0;
//    double countTextureDecisionTwo, countTextureDecisionFour = 0.0;
//    double countPerimeterDecisionTwo, countPerimeterDecisionFour = 0.0;
//    double countAreaDecisionTwo, countAreaDecisionFour = 0.0;
//    double countSmoothnessDecisionTwo, countSmoothnessDecisionFour = 0.0;
//    double countCompactnessDecisionTwo, countCompactnessDecisionFour = 0.0;
//    double countConcavityDecisionTwo, countConcavityDecisionFour = 0.0;
//    double countConcavePointsDecisionTwo, countConcavePointsDecisionFour = 0.0;
//    double countSymmetryDecisionTwo, countSymmetryDecisionFour = 0.0;
//    double countFractalDimensionDecisionTwo, countFractalDimensionDecisionFour = 0.0;
//
//    public void thirdFillingMethod(List<NormalizedRecord> dataList) {
//        resetCountsThirdMethod();
//        resetSumThirdMethod();
//        for (NormalizedRecord rec : dataList) {
//            int id = rec.getID();
//            double radius = rec.getNormalizedRadius();
//            double texture = rec.getNormalizedTexture();
//            double perimeter = rec.getNormalizedPerimeter();
//            double area = rec.getNormalizedArea();
//            double smoothness = rec.getNormalizedSmoothness();
//            double compactness = rec.getNormalizedCompactness();
//            double concavity = rec.getNormalizedConcavity();
//            double concavePoints = rec.getNormalizedConcavePoints();
//            double symmetry = rec.getNormalizedSymmetry();
//            double fractalDimension = rec.getNormalizedFractalDimension();
//            int decision = rec.getDecision();
//
//            if (decision == 2) {
//                if (radius != -1) {
//                    countRadiusDecisionTwo += 1;
//                    sumRadiusDecisionTwo += radius;
//                }
//                if (texture != -1) {
//                    countTextureDecisionTwo += 1;
//                    sumTextureDecisionTwo += texture;
//                }
//                if (perimeter != -1) {
//                    countPerimeterDecisionTwo += 1;
//                    sumPerimeterDecisionTwo += perimeter;
//                }
//                if (area != -1) {
//                    countAreaDecisionTwo += 1;
//                    sumAreaDecisionTwo += area;
//                }
//                if (smoothness != -1) {
//                    countSmoothnessDecisionTwo += 1;
//                    sumSmoothnessDecisionTwo += smoothness;
//                }
//                if (compactness != -1) {
//                    countCompactnessDecisionTwo += 1;
//                    sumCompactnessDecisionTwo += compactness;
//                }
//                if (concavity != -1) {
//                    countConcavityDecisionTwo += 1;
//                    sumConcavityDecisionTwo += concavity;
//                }
//                if (concavePoints != -1) {
//                    countConcavePointsDecisionTwo += 1;
//                    sumConcavePointsDecisionTwo += concavePoints;
//                }
//                if (symmetry != -1) {
//                    countSymmetryDecisionTwo += 1;
//                    sumSymmetryDecisionTwo += symmetry;
//                }
//                if (fractalDimension != -1) {
//                    countFractalDimensionDecisionTwo += 1;
//                    sumFractalDimensionDecisionTwo += fractalDimension;
//                }
//            }
//            if(decision == 4){
//                if (radius != -1) {
//                    countRadiusDecisionFour += 1;
//                    sumRadiusDecisionFour += radius;
//                }
//                if (texture != -1) {
//                    countTextureDecisionFour += 1;
//                    sumTextureDecisionFour += texture;
//                }
//                if (perimeter != -1) {
//                    countPerimeterDecisionFour += 1;
//                    sumPerimeterDecisionFour += perimeter;
//                }
//                if (area != -1) {
//                    countAreaDecisionFour += 1;
//                    sumAreaDecisionFour += area;
//                }
//                if (smoothness != -1) {
//                    countSmoothnessDecisionFour += 1;
//                    sumSmoothnessDecisionFour += smoothness;
//                }
//                if (compactness != -1) {
//                    countCompactnessDecisionFour += 1;
//                    sumCompactnessDecisionFour += compactness;
//                }
//                if (concavity != -1) {
//                    countConcavityDecisionFour += 1;
//                    sumConcavityDecisionFour += concavity;
//                }
//                if (concavePoints != -1) {
//                    countConcavePointsDecisionFour += 1;
//                    sumConcavePointsDecisionFour += concavePoints;
//                }
//                if (symmetry != -1) {
//                    countSymmetryDecisionFour += 1;
//                    sumSymmetryDecisionFour += symmetry;
//                }
//                if (fractalDimension != -1) {
//                    countFractalDimensionDecisionFour += 1;
//                    sumFractalDimensionDecisionFour += fractalDimension;
//                }
//            }
//        }
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
//        symbols.setDecimalSeparator('.');
//        DecimalFormat df = new DecimalFormat("#.#####",symbols);
//        double avgRadiusDecisionTwo = Double.parseDouble(df.format((double)sumRadiusDecisionTwo / countRadiusDecisionTwo));
//        double avgTextureDecisionTwo = Double.parseDouble(df.format((double)sumTextureDecisionTwo / countTextureDecisionTwo));
//        double avgPerimeterDecisionTwo = Double.parseDouble(df.format((double)sumPerimeterDecisionTwo / countPerimeterDecisionTwo));
//        double avgAreaDecisionTwo = Double.parseDouble(df.format((double)sumAreaDecisionTwo / countAreaDecisionTwo));
//        double avgSmoothnessDecisionTwo = Double.parseDouble(df.format((double)sumSmoothnessDecisionTwo / countSmoothnessDecisionTwo));
//        double avgCompactnessDecisionTwo = Double.parseDouble(df.format((double)sumCompactnessDecisionTwo / countCompactnessDecisionTwo));
//        double avgConcavityDecisionTwo = Double.parseDouble(df.format((double)sumConcavityDecisionTwo / countConcavityDecisionTwo));
//        double avgConcavePointsDecisionTwo = Double.parseDouble(df.format((double)sumConcavePointsDecisionTwo / countConcavePointsDecisionTwo));
//        double avgSymmetryDecisionTwo = Double.parseDouble(df.format((double)sumSymmetryDecisionTwo / countSymmetryDecisionTwo));
//        double avgFractalDimensionDecisionTwo = Double.parseDouble(df.format((double)sumFractalDimensionDecisionTwo / countFractalDimensionDecisionTwo));
//
//        double avgRadiusDecisionFour = Double.parseDouble(df.format((double)sumRadiusDecisionFour / countRadiusDecisionFour));
//        double avgTextureDecisionFour = Double.parseDouble(df.format((double)sumTextureDecisionFour / countTextureDecisionFour));
//        double avgPerimeterDecisionFour = Double.parseDouble(df.format((double)sumPerimeterDecisionFour / countPerimeterDecisionFour));
//        double avgAreaDecisionFour = Double.parseDouble(df.format((double)sumAreaDecisionFour / countAreaDecisionFour));
//        double avgSmoothnessDecisionFour = Double.parseDouble(df.format((double)sumSmoothnessDecisionFour / countSmoothnessDecisionFour));
//        double avgCompactnessDecisionFour = Double.parseDouble(df.format((double)sumCompactnessDecisionFour / countCompactnessDecisionFour));
//        double avgConcavityDecisionFour = Double.parseDouble(df.format((double)sumConcavityDecisionFour / countConcavityDecisionFour));
//        double avgConcavePointsDecisionFour = Double.parseDouble(df.format((double)sumConcavePointsDecisionFour / countConcavePointsDecisionFour));
//        double avgSymmetryDecisionFour = Double.parseDouble(df.format((double)sumSymmetryDecisionFour / countSymmetryDecisionFour));
//        double avgFractalDimensionDecisionFour = Double.parseDouble(df.format((double)sumFractalDimensionDecisionFour / countFractalDimensionDecisionFour));
//
//        System.out.println("--------------------");
//        System.out.println("Average Radius for decision two: "+avgRadiusDecisionTwo);
//        System.out.println("Sum and Count D2: "+sumRadiusDecisionTwo+" - "+countRadiusDecisionTwo);
//        System.out.println("Average Radius for decision four: "+avgRadiusDecisionFour);
//        System.out.println("Sum and Count D4: "+sumRadiusDecisionFour+" - "+countRadiusDecisionFour);
//
//
//        for (NormalizedRecord updateRecord : dataList) {
//            if (updateRecord.getDecision() == 2) {
//                if (updateRecord.getNormalizedRadius() == -1) {
//                    updateRecord.setNormalizedRadius(avgRadiusDecisionTwo);
//                }
//                if (updateRecord.getNormalizedTexture() == -1) {
//                    updateRecord.setNormalizedTexture(avgTextureDecisionTwo);
//                }
//                if (updateRecord.getNormalizedPerimeter() == -1) {
//                    updateRecord.setNormalizedPerimeter(avgPerimeterDecisionTwo);
//                }
//                if (updateRecord.getNormalizedArea() == -1) {
//                    updateRecord.setNormalizedArea(avgAreaDecisionTwo);
//                }
//                if (updateRecord.getNormalizedSmoothness() == -1) {
//                    updateRecord.setNormalizedSmoothness(avgSmoothnessDecisionTwo);
//                }
//                if (updateRecord.getNormalizedCompactness() == -1) {
//                    updateRecord.setNormalizedCompactness(avgCompactnessDecisionTwo);
//                }
//                if (updateRecord.getNormalizedConcavity() == -1) {
//                    updateRecord.setNormalizedConcavity(avgConcavityDecisionTwo);
//                }
//                if (updateRecord.getNormalizedConcavePoints() == -1) {
//                    updateRecord.setNormalizedConcavePoints(avgConcavePointsDecisionTwo);
//                }
//                if (updateRecord.getNormalizedSymmetry() == -1) {
//                    updateRecord.setNormalizedSymmetry(avgSymmetryDecisionTwo);
//                }
//                if (updateRecord.getNormalizedFractalDimension() == -1) {
//                    updateRecord.setNormalizedFractalDimension(avgFractalDimensionDecisionTwo);
//                }
//            }
//            if (updateRecord.getDecision() == 4){
//                if (updateRecord.getNormalizedRadius() == -1) {
//                    updateRecord.setNormalizedRadius(avgRadiusDecisionFour);
//                }
//                if (updateRecord.getNormalizedTexture() == -1) {
//                    updateRecord.setNormalizedTexture(avgTextureDecisionFour);
//                }
//                if (updateRecord.getNormalizedPerimeter() == -1) {
//                    updateRecord.setNormalizedPerimeter(avgPerimeterDecisionFour);
//                }
//                if (updateRecord.getNormalizedArea() == -1) {
//                    updateRecord.setNormalizedArea(avgAreaDecisionFour);
//                }
//                if (updateRecord.getNormalizedSmoothness() == -1) {
//                    updateRecord.setNormalizedSmoothness(avgSmoothnessDecisionFour);
//                }
//                if (updateRecord.getNormalizedCompactness() == -1) {
//                    updateRecord.setNormalizedCompactness(avgCompactnessDecisionFour);
//                }
//                if (updateRecord.getNormalizedConcavity() == -1) {
//                    updateRecord.setNormalizedConcavity(avgConcavityDecisionFour);
//                }
//                if (updateRecord.getNormalizedConcavePoints() == -1) {
//                    updateRecord.setNormalizedConcavePoints(avgConcavePointsDecisionFour);
//                }
//                if (updateRecord.getNormalizedSymmetry() == -1) {
//                    updateRecord.setNormalizedSymmetry(avgSymmetryDecisionFour);
//                }
//                if (updateRecord.getNormalizedFractalDimension() == -1) {
//                    updateRecord.setNormalizedFractalDimension(avgFractalDimensionDecisionFour);
//                }
//            }
//        }
//    }
//
//    public void resetCountsThirdMethod() {
//        countRadiusDecisionTwo = 0;
//        countTextureDecisionTwo = 0;
//        countPerimeterDecisionTwo = 0;
//        countAreaDecisionTwo = 0;
//        countSmoothnessDecisionTwo = 0;
//        countCompactnessDecisionTwo = 0;
//        countConcavityDecisionTwo = 0;
//        countConcavePointsDecisionTwo = 0;
//        countSymmetryDecisionTwo = 0;
//        countFractalDimensionDecisionTwo = 0;
//
//        countRadiusDecisionFour = 0;
//        countTextureDecisionFour= 0;
//        countPerimeterDecisionFour = 0;
//        countAreaDecisionFour = 0;
//        countSmoothnessDecisionFour = 0;
//        countCompactnessDecisionFour = 0;
//        countConcavityDecisionFour = 0;
//        countConcavePointsDecisionFour = 0;
//        countSymmetryDecisionFour = 0;
//        countFractalDimensionDecisionFour = 0;
//    }
//    public void resetSumThirdMethod() {
//        sumRadiusDecisionTwo = 0;
//        sumTextureDecisionTwo = 0;
//        sumPerimeterDecisionTwo = 0;
//        sumAreaDecisionTwo = 0;
//        sumSmoothnessDecisionTwo = 0;
//        sumCompactnessDecisionTwo = 0;
//        sumConcavityDecisionTwo = 0;
//        sumConcavePointsDecisionTwo = 0;
//        sumSymmetryDecisionTwo = 0;
//        sumFractalDimensionDecisionTwo = 0;
//
//        sumRadiusDecisionFour = 0;
//        sumTextureDecisionFour= 0;
//        sumPerimeterDecisionFour = 0;
//        sumAreaDecisionFour = 0;
//        sumSmoothnessDecisionFour = 0;
//        sumCompactnessDecisionFour = 0;
//        sumConcavityDecisionFour = 0;
//        sumConcavePointsDecisionFour = 0;
//        sumSymmetryDecisionFour = 0;
//        sumFractalDimensionDecisionFour = 0;
//    }
//}
