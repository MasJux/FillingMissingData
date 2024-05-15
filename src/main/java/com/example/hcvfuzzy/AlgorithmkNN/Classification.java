package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;

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

    private double calculateEuclideanDistance(NormalizedRecord obj1, NormalizedRecord obj2) {
        double sum = 0.0;

        for (String attributeName : ATTRIBUTES) {
            double diff = obj1.getAttributeValue(attributeName) - obj2.getAttributeValue(attributeName);
            sum += Math.pow(diff, 2);
        }

        return Math.sqrt(sum);
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
