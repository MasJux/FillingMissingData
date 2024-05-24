package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.*;

public class Classification {
    private static final List<String> ATTRIBUTES = Arrays.asList("radius", "texture", "perimeter", "area", "smoothness", "compactness"
            , "concavity", "concavePoints", "symmetry", "fractalDimension");

    public int classifyNewObject(NormalizedRecord normalizedObject,List<NormalizedRecord> normalizedDataset, int k) {

        //obliczanie podobieństw
        Map<NormalizedRecord, Double> similarities = calculateSimilarities(normalizedObject, normalizedDataset);
        //sortowanie sąsiadów względem odległości
        List<NormalizedRecord> nearestNeighbors = findNearestNeighbors(similarities, k);
        //agregacja
        int classifiedDecision = aggregateDecision(nearestNeighbors, k);

        return classifiedDecision;
    }

    public Map<NormalizedRecord, Double> calculateSimilarities(NormalizedRecord newObject, List<NormalizedRecord> dataset) {
        Map<NormalizedRecord, Double> similarities = new HashMap<>();

        for (NormalizedRecord normalizedRecord : dataset) {
            if (normalizedRecord != newObject) {
                double similarity = calculateEuclideanDistance(newObject, normalizedRecord);
                similarities.put(normalizedRecord, similarity);
            }
        }
        return similarities;
    }
    private static final double[] MISSING_VALUE = null;
    private static double calculateEuclideanDistance(NormalizedRecord objectWithMissingAttribute, NormalizedRecord objectWithoutMissingAttribute) {
        double[][] attributesWithMissingValue = objectWithMissingAttribute.getAttributes();
        double[][] attributesWithoutMissingValue = objectWithoutMissingAttribute.getAttributes();
        double sumOfSquares = 0.0;
        int n = attributesWithMissingValue.length;

        for (int i = 0; i < n; i++) {
            if (Arrays.equals(attributesWithMissingValue[i], MISSING_VALUE)) {
                continue;
            }
            double lowerCompDiff = attributesWithMissingValue[i][0] - attributesWithoutMissingValue[i][0];
            double upperCompDiff = attributesWithMissingValue[i][1] - attributesWithoutMissingValue[i][1];
            double maxDiffSquared = Math.max(Math.pow(lowerCompDiff, 2), Math.pow(upperCompDiff, 2));
            sumOfSquares += maxDiffSquared;
        }
        return Math.sqrt(sumOfSquares / n);
    }

    public int aggregateDecision(List<NormalizedRecord> nearestNeighbors, int k) {
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
