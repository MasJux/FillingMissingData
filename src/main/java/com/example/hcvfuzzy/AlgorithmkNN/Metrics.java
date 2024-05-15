package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;
import com.example.hcvfuzzy.FillingMethods.EntropyMethod;
import com.example.hcvfuzzy.Holders.DataAfterEntropyFilling;
import com.example.hcvfuzzy.Holders.NormalizedDataAfterDeletingHolder;

import java.util.*;

public class Metrics {
    List<Integer> originalDecisionList = new ArrayList<>();
    List<Integer> classifiedDecisionList = new ArrayList<>();
    public void evaluateKNNWithEntropy(List<NormalizedRecord> dataset) {
        double totalACC;
        double totalSENS;
        double totalSPEC;
        double totalPREC;

        // 10-krotna walidacja krzyżowa
//        List<NormalizedRecord> dataAfterEntropyFilling = DataAfterEntropyFilling.getDataAfterEntropyFilling();
//TODO mieszanie itr
        double trainPercentage = 0.7;
        List<NormalizedRecord> trainData = new ArrayList<>();
        List<NormalizedRecord> testData = new ArrayList<>();

        long seed = System.nanoTime();
        Random random = new Random(seed);
        List<NormalizedRecord> shuffledData = new ArrayList<>(dataset);

        for (int i = shuffledData.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            NormalizedRecord temp = shuffledData.get(index);
            shuffledData.set(index, shuffledData.get(i));
            shuffledData.set(i, temp);
        }
        int trainSize = (int) (shuffledData.size() * trainPercentage);
        for (int i = 0; i < trainSize; i++) {
            trainData.add(shuffledData.get(i));
        }
        for (int i = trainSize; i < shuffledData.size(); i++) {
            testData.add(shuffledData.get(i));
        }

        // Uzupełnienie brakujących wartości danych treningowych za pomocą metody entropii
        Classification classification = new Classification();
        EntropyMethod entropyMethod = new EntropyMethod();
        entropyMethod.completeMissingValue(trainData);

        // Klasyfikacja IV-kNN dla zbioru testowego danych uzupełnionych
        for (NormalizedRecord testObject : testData) {
            int originalDecision = testObject.getDecision();
            originalDecisionList.add(originalDecision);
            int classifiedDecision = classification.classifyNewObject(testObject, trainData, 5);
            classifiedDecisionList.add(classifiedDecision);
        }
        totalACC = calculateAccuracy(originalDecisionList, classifiedDecisionList);
        totalSENS = calculateSensivity(originalDecisionList, classifiedDecisionList);
        totalSPEC = calculateSpecificity(originalDecisionList, classifiedDecisionList);
        totalPREC = calculatePrecision(originalDecisionList, classifiedDecisionList);
        System.out.println("ACC: "+totalACC);
        System.out.println("SENS: "+totalSENS);
        System.out.println("SPEC: "+totalSPEC);
        System.out.println("PREC: "+totalPREC);
    }

    //metryka dokładności
    public static double calculateAccuracy(List<Integer> originalDecisionList, List <Integer> classifiedDecisionList) {
        int correctCount = 0;
        for (int i = 0; i < originalDecisionList.size(); i++) {
            if (originalDecisionList.get(i).equals(classifiedDecisionList.get(i))) {
                correctCount++;
            }
        }
        return (double) correctCount / originalDecisionList.size();
    }
    //metryka czułości
    public static double calculateSensivity(List<Integer> originalDecisionList, List <Integer> classifiedDecisionList){
        int truePositive = 0;
        int positiveCount = 0;
        for(int i = 0; i< originalDecisionList.size();i++){
            if(originalDecisionList.get(i) == 2){
                positiveCount++;
                if(classifiedDecisionList.get(i) == 2){
                    truePositive++;
                }
            }
        }
        return (double) truePositive/positiveCount;
    }
    //metryka specyficzności
    public static double calculateSpecificity(List<Integer> originalDecisionList, List <Integer> classifiedDecisionList){
        int trueNegative = 0;
        int negativeCount = 0;
        for(int i = 0;i<originalDecisionList.size();i++){
            if(originalDecisionList.get(i) == 4){
                negativeCount++;
                if(classifiedDecisionList.get(i) == 4){
                    trueNegative++;
                }
            }
        }
        return (double) trueNegative/negativeCount;
    }
    //metryka precyzji
    public static double calculatePrecision(List<Integer> originalDecisionList, List <Integer> classifiedDecisionList){
        int truePositive = 0;
        int positiveCount = 0;
        for(int i = 0; i< classifiedDecisionList.size();i++){
            if(classifiedDecisionList.get(i) == 2){
                positiveCount++;
                if(originalDecisionList.get(i)==2){
                    truePositive++;
                }
            }
        }
        return (double) truePositive/positiveCount;
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
