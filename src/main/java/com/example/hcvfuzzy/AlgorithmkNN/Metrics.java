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

        // 10-krotna walidacja krzyżowa
//        List<NormalizedRecord> dataAfterEntropyFilling = DataAfterEntropyFilling.getDataAfterEntropyFilling();
//TODO mieszanie itr
    for (int i = 0; i < 10; i++) {
        double trainPercentage = 0.7;
        List<NormalizedRecord> trainData = new ArrayList<>();
        List<NormalizedRecord> testData = new ArrayList<>();

        long seed = System.nanoTime();
        Random random = new Random(seed);
        List<NormalizedRecord> shuffledData = new ArrayList<>(dataset);

        for (int a = shuffledData.size() - 1; a > 0; a--) {
            int index = random.nextInt(a + 1);
            NormalizedRecord temp = shuffledData.get(index);
            shuffledData.set(index, shuffledData.get(a));
            shuffledData.set(a, temp);
        }
        int trainSize = (int) (shuffledData.size() * trainPercentage);
        for (int x = 0; x < trainSize; x++) {
            trainData.add(shuffledData.get(x));
        }
        for (int y = trainSize; y < shuffledData.size(); y++) {
            testData.add(shuffledData.get(y));
        }

        // Uzupełnienie brakujących wartości danych treningowych za pomocą metody entropii
        EntropyMethod entropyMethod = new EntropyMethod();

        entropyMethod.completeMissingValue(trainData);

        Classification classification = new Classification();
        List<Integer> originalDecisionList = new ArrayList<>();
        List<Integer> classifiedDecisionList = new ArrayList<>();
        // Klasyfikacja IV-kNN dla zbioru testowego danych uzupełnionych
        for (NormalizedRecord testObject : testData) {
            int originalDecision = testObject.getDecision();
            originalDecisionList.add(originalDecision);
            int classifiedDecision = classification.classifyNewObject(testObject, trainData, 5);
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
        double avgACC = totalACC / 10;
        double avgSENS = totalSENS / 10;
        double avgSPEC = totalSPEC / 10;
        double avgPREC = totalPREC / 10;
        System.out.println("ACC: " + avgACC);
        System.out.println("SENS: " + avgSENS);
        System.out.println("SPEC: " + avgSPEC);
        System.out.println("PREC: " + avgPREC);
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








//            // Obliczenie metryk klasyfikacji
//            double ACC = calculateAccuracy(testingData);
//            double SENS = calculateSensitivity(testingData);
//            double SPEC = calculateSpecificity(testingData);
//            double PREC = calculatePrecision(testingData);
//
//            // Dodanie wyników do sumy całkowitej
//            totalACC += ACC;
//            totalSENS += SENS;
//            totalSPEC += SPEC;
//            totalPREC += PREC;
//        }
//
//        // Obliczenie średniej arytmetycznej metryk
//        double avgACC = totalACC / 10;
//        double avgSENS = totalSENS / 10;
//        double avgSPEC = totalSPEC / 10;
//        double avgPREC = totalPREC / 10;
//
//        // Wyświetlenie wyników w postaci tabeli
//        displayResults(avgACC, avgSENS, avgSPEC, avgPREC);
//    }
//
