package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Objects.Interval;
import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.*;

public class Classification {

    public int classifyNewObject(NormalizedRecord testObject, List<NormalizedRecord> trainingDataset,String distanceType, int k) {

        //obliczanie podobieństw
        Map<NormalizedRecord, Double> similarities = calculateSimilarities(testObject, trainingDataset,distanceType);
        //sortowanie sąsiadów względem odległości
        List<NormalizedRecord> nearestNeighbors = findNearestNeighbors(similarities, k);
        //agregacja
        int classifiedDecision = aggregateDecision(nearestNeighbors);

        return classifiedDecision;
    }

    public Map<NormalizedRecord, Double> calculateSimilarities(NormalizedRecord newObject, List<NormalizedRecord> dataset, String distanceType) {
        Map<NormalizedRecord, Double> similarities = new HashMap<>();

        for (NormalizedRecord normalizedRecord : dataset) {
            if (normalizedRecord != newObject) {
                double similarity = calculateSimilarity(newObject,normalizedRecord, distanceType);
                similarities.put(normalizedRecord, similarity);
            }
        }
        return similarities;
    }

    private static double calculateEuclideanDistance(NormalizedRecord newObject,NormalizedRecord trainedRecord) {
        Interval[] attributesNewObject = newObject.getAttributes();
        Interval[] attributesTrainedRecord = trainedRecord.getAttributes();

        double sumOfSquares = 0.0;
        int n = 0;

        List<Double> newObjectAttributesLowerList = new ArrayList<>();
        List<Double> newObjectAttributesUpperList = new ArrayList<>();

        List<Double> trainedRecordAttributesLowerList = new ArrayList<>();
        List<Double> trainedRecordAttributesUpperList = new ArrayList<>();

//wyciagniecie atrybutów nowego obiektu(testowego)
        for(Interval firstInterval:attributesNewObject){

            double newObjectAttributeLowerComp = firstInterval.getLower();
            double newObjectAttributeUpperComp = firstInterval.getUpper();

            newObjectAttributesLowerList.add(newObjectAttributeLowerComp);
            newObjectAttributesUpperList.add(newObjectAttributeUpperComp);
        }
//wyciagniecie atrybutów z obiektu treninowego
        for(Interval secondInterval:attributesTrainedRecord){

            double trainedRecordAttributeLowerComp = secondInterval.negation().getLower();
            double trainedRecordAttributeUpperComp = secondInterval.negation().getUpper();

            trainedRecordAttributesLowerList.add(trainedRecordAttributeLowerComp);
            trainedRecordAttributesUpperList.add(trainedRecordAttributeUpperComp);
        }

        for(int i = 0; i<newObjectAttributesLowerList.size();i++){

            double lowerCompDiff = newObjectAttributesLowerList.get(i)-trainedRecordAttributesLowerList.get(i);
            double upperCompDiff = newObjectAttributesUpperList.get(i)-trainedRecordAttributesUpperList.get(i);
            double maxDiffSquared = Math.max(Math.pow(lowerCompDiff, 2), Math.pow(upperCompDiff, 2));
            sumOfSquares += maxDiffSquared;
            n++;
        }
//        System.out.println("missingAttributeLowerList: "+missingAttributeLowerList);
//        System.out.println("missingAttributeUpperList: "+missingAttributeUpperList);

        return Math.sqrt(sumOfSquares/n);
    }
    private static double calculateHammingDistance(NormalizedRecord newObject,NormalizedRecord trainedRecord) {
        Interval[] attributesNewObject = newObject.getAttributes();
        Interval[] attributesTrainedRecord = trainedRecord.getAttributes();
        double totalMaxDiff = 0.0;
        int n = 0;

        List<Double> newObjectAttributesLowerList = new ArrayList<>();
        List<Double> newObjectAttributesUpperList = new ArrayList<>();

        List<Double> trainedRecordAttributesLowerList = new ArrayList<>();
        List<Double> trainedRecordAttributesUpperList = new ArrayList<>();

//wyciagniecie atrybutów nowego obiektu(testowego)
        for(Interval firstInterval:attributesNewObject){

            double newObjectAttributeLowerComp = firstInterval.getLower();
            double newObjectAttributeUpperComp = firstInterval.getUpper();

            newObjectAttributesLowerList.add(newObjectAttributeLowerComp);
            newObjectAttributesUpperList.add(newObjectAttributeUpperComp);
        }
//wyciagniecie atrybutów z obiektu treninowego
        for(Interval secondInterval:attributesTrainedRecord){

            double trainedRecordAttributeLowerComp = secondInterval.negation().getLower();
            double trainedRecordAttributeUpperComp = secondInterval.negation().getUpper();

            trainedRecordAttributesLowerList.add(trainedRecordAttributeLowerComp);
            trainedRecordAttributesUpperList.add(trainedRecordAttributeUpperComp);
        }

        for(int i = 0; i<newObjectAttributesLowerList.size();i++){

            double lowerCompDiff = newObjectAttributesLowerList.get(i)-trainedRecordAttributesLowerList.get(i);
            double upperCompDiff = newObjectAttributesUpperList.get(i)-trainedRecordAttributesUpperList.get(i);
            double maxDiffSquared = Math.max(Math.pow(lowerCompDiff, 2), Math.pow(upperCompDiff, 2));
            totalMaxDiff += maxDiffSquared;
            n++;
        }

        return totalMaxDiff / n;
    }
    private static double calculateSimilarity(NormalizedRecord newObject, NormalizedRecord trainedObject,String distanceType) {

        double distance = 0;
        if ("euclidean".equals(distanceType)) {
            distance = calculateEuclideanDistance(newObject,trainedObject);
        } else if ("hamming".equals(distanceType)) {
            distance = calculateHammingDistance(newObject,trainedObject);
        } else {
            System.out.println("..");
        }

        return 1 - distance;
    }


    public int aggregateDecision(List<NormalizedRecord> nearestNeighbors) {
        Map<Integer, Integer> decisionCounts = new HashMap<>();

        // liczenie wystąpień decyzji wśród k najbliższych sąsiadów
        for (NormalizedRecord normalizedRecord : nearestNeighbors) {
            int decision = normalizedRecord.getDecision();
            decisionCounts.put(decision, decisionCounts.getOrDefault(decision, 0) + 1);
        }
        // wybór decyzji, która występuje najczęściej
        int maxCount = 0;
        int mostFrequentDecision = -1;
        for (Map.Entry<Integer, Integer> entry : decisionCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentDecision = entry.getKey();
            }
        }
        return mostFrequentDecision;
    }

    private List<NormalizedRecord> findNearestNeighbors(Map<NormalizedRecord, Double> similarities, int k) {
        // tworzymy listę wpisów z mapy podobieństw
        List<Map.Entry<NormalizedRecord, Double>> sortedSimilarities = new ArrayList<>(similarities.entrySet());

        // sortujemy listę wpisów względem wartości podobieństwa
        Collections.sort(sortedSimilarities, new Comparator<Map.Entry<NormalizedRecord, Double>>() {
            @Override
            public int compare(Map.Entry<NormalizedRecord, Double> entry1, Map.Entry<NormalizedRecord, Double> entry2) {
                return Double.compare(entry1.getValue(), entry2.getValue());
            }
        });

        // tworzymy listę k najbliższych sąsiadów
        List<NormalizedRecord> nearestNeighbors = new ArrayList<>();
        for (int i = 0; i < k && i < sortedSimilarities.size(); i++) {
            nearestNeighbors.add(sortedSimilarities.get(i).getKey());
        }
        return nearestNeighbors;
    }
}
