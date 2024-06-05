package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.FillingMethods.EntropyMethod;

import java.util.*;

public class Metrics {
    public void evaluateKNNWithEntropy(List<NormalizedRecord> dataset) {
        double totalACC = 0.0;
        double totalSENS = 0.0;
        double totalSPEC = 0.0;
        double totalPREC = 0.0;

        int k = 5;  // liczba najbliższych sąsiadów

        // Uzupełnienie brakujących wartości danych treningowych za pomocą metody entropii
        EntropyMethod entropyMethod = new EntropyMethod();
        entropyMethod.completeMissingValue(dataset);

        // 10-krotna walidacja krzyżowa
        int folds = 10;
        int foldSize = dataset.size() / folds;
        long seed = System.nanoTime();
        Collections.shuffle(dataset, new Random(seed));

        for (int i = 0; i < folds; i++) {
            List<NormalizedRecord> trainData = new ArrayList<>();
            List<NormalizedRecord> testData = new ArrayList<>();

            for (int j = 0; j < dataset.size(); j++) {
                if (j >= i * foldSize && j < (i + 1) * foldSize) {
                    testData.add(dataset.get(j));
                } else {
                    trainData.add(dataset.get(j));
                }
            }

            Classification classification = new Classification();
            List<Integer> originalDecisionList = new ArrayList<>();
            List<Integer> classifiedDecisionList = new ArrayList<>();

            // Klasyfikacja IV-kNN dla zbioru testowego
            for (NormalizedRecord testObject : testData) {
                int originalDecision = testObject.getDecision();
                originalDecisionList.add(originalDecision);
                int classifiedDecision = classification.classifyNewObject(testObject, trainData, k);
                classifiedDecisionList.add(classifiedDecision);
            }

            double ACC = calculateAccuracy(originalDecisionList, classifiedDecisionList);
            double SENS = calculateSensivity(originalDecisionList, classifiedDecisionList);
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

        System.out.println("srednia dokladnosc (ACC): " + avgACC);
        System.out.println("srednia czulosc (SENS): " + avgSENS);
        System.out.println("srednia swoistosc (SPEC): " + avgSPEC);
        System.out.println("srednia precyzja (PREC): " + avgPREC);
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
    public static double calculateSensivity(List<Integer> originalDecisionList, List<Integer> classifiedDecisionList) {
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

    //metryka specyficzności
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
