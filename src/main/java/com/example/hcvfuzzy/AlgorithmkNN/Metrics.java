package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Controllers.NavigationController;
import com.example.hcvfuzzy.FillingMethods.Normalization;
import com.example.hcvfuzzy.Objects.Interval;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.FillingMethods.EntropyMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.*;

public class Metrics {

    public void evaluateKNNWithEntropy(List<NormalizedRecord> dataset, String completeMissingValuesDistanceType, String classificationDistanceType, int k) {
        double totalACC = 0.0;
        double totalSENS = 0.0;
        double totalSPEC = 0.0;
        double totalPREC = 0.0;


        List<NormalizedRecord> datasetCopy = new ArrayList<>();
        for (NormalizedRecord record : dataset) {
            datasetCopy.add(record.copy());
        }

        // Uzupełnienie brakujących wartości danych treningowych za pomocą metody entropii
        EntropyMethod entropyMethod = new EntropyMethod();
        entropyMethod.completeMissingValue(datasetCopy,completeMissingValuesDistanceType);

        // 10-krotna walidacja krzyżowa
        int folds = 10;
        int foldSize = datasetCopy.size() / folds;
        long seed = System.nanoTime();
        Collections.shuffle(datasetCopy, new Random(seed));

        for (int i = 0; i < folds; i++) {
            List<NormalizedRecord> trainData = new ArrayList<>();
            List<NormalizedRecord> testData = new ArrayList<>();

            for (int j = 0; j < datasetCopy.size(); j++) {
                if (j >= i * foldSize && j < (i + 1) * foldSize) {
                    testData.add(datasetCopy.get(j));
                } else {
                    trainData.add(datasetCopy.get(j));
                }
            }
            Classification classification = new Classification();
            List<Integer> originalDecisionList = new ArrayList<>();
            List<Integer> classifiedDecisionList = new ArrayList<>();

            for (NormalizedRecord testObject : testData) {
                int originalDecision = testObject.getDecision();
                originalDecisionList.add(originalDecision);
                int classifiedDecision = classification.classifyNewObject(testObject, trainData, classificationDistanceType ,k);
                classifiedDecisionList.add(classifiedDecision);
            }

            double ACC = calculateAccuracy(originalDecisionList, classifiedDecisionList);
            double SENS = calculateSensitivity(originalDecisionList, classifiedDecisionList);
            double SPEC = calculateSpecificity(originalDecisionList, classifiedDecisionList);
            double PREC = calculatePrecision(originalDecisionList, classifiedDecisionList);

            totalACC += ACC;
            totalSENS += SENS;
            totalSPEC += SPEC;
            totalPREC += PREC;
        }

        double avgACC = totalACC / folds;
        double avgSENS = totalSENS / folds;
        double avgSPEC = totalSPEC / folds;
        double avgPREC = totalPREC / folds;

        System.out.println("Liczba sąsiadów: "+k);
        System.out.println("Miara odleglosci(uzupełnianie): "+completeMissingValuesDistanceType+" || Miara odleglosci(klasyfikacja): "+classificationDistanceType);
        System.out.println("średnia dokładność (ACC): " + String.format("%.7f", avgACC));
        System.out.println("średnia czułość (SENS): " + String.format("%.7f", avgSENS));
        System.out.println("średnia swoistość (SPEC): " + String.format("%.7f", avgSPEC));
        System.out.println("średnia precyzja (PREC): " + String.format("%.7f", avgPREC));
        System.out.println("--------------------------------------");
    }

    //metryka dokładności
    public static double calculateAccuracy(List<Integer> originalDecisionList, List<Integer> classifiedDecisionList) {
        int tp = 0, tn = 0, fp = 0, fn = 0;
        for (int i = 0; i < originalDecisionList.size(); i++) {
            if (originalDecisionList.get(i) == 2 && classifiedDecisionList.get(i) == 2) {
                tp++;
            } else if (originalDecisionList.get(i) == 4 && classifiedDecisionList.get(i) == 4) {
                tn++;
            } else if (originalDecisionList.get(i) == 4 && classifiedDecisionList.get(i) == 2) {
                fp++;
            } else if (originalDecisionList.get(i) == 2 && classifiedDecisionList.get(i) == 4) {
                fn++;
            }
        }
        return (double) (tp + tn) / (tp + tn + fp + fn);
    }

    //metryka czułości
    public static double calculateSensitivity(List<Integer> originalDecisionList, List<Integer> classifiedDecisionList) {
        int tp = 0, fn = 0;
        for (int i = 0; i < originalDecisionList.size(); i++) {
            if (originalDecisionList.get(i) == 2 && classifiedDecisionList.get(i) == 2) {
                tp++;
            } else if (originalDecisionList.get(i) == 2 && classifiedDecisionList.get(i) == 4) {
                fn++;
            }
        }
        return (double) tp / (tp + fn);
    }

    //metryka swoistości
    public static double calculateSpecificity(List<Integer> originalDecisionList, List<Integer> classifiedDecisionList) {
        int tn = 0, fp = 0;
        for (int i = 0; i < originalDecisionList.size(); i++) {
            if (originalDecisionList.get(i) == 4 && classifiedDecisionList.get(i) == 4) {
                tn++;
            } else if (originalDecisionList.get(i) == 4 && classifiedDecisionList.get(i) == 2) {
                fp++;
            }
        }
        return (double) tn / (tn + fp);
    }

    //metryka precyzji
    public static double calculatePrecision(List<Integer> originalDecisionList, List<Integer> classifiedDecisionList) {
        int tp = 0, fp = 0;
        for (int i = 0; i < originalDecisionList.size(); i++) {
            if (originalDecisionList.get(i) == 2 && classifiedDecisionList.get(i) == 2) {
                tp++;
            } else if (originalDecisionList.get(i) == 4 && classifiedDecisionList.get(i) == 2) {
                fp++;
            }
        }
        return (double) tp / (tp + fp);
    }
}
