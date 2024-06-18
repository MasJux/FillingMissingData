package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Objects.Interval;
import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class EntropyMethod {
    public void completeMissingValue(List<NormalizedRecord> listObjectWithMissingValue, String distanceType) {
        for(NormalizedRecord trainRecord: listObjectWithMissingValue) {
            NormalizedRecord missingRecord = findMissingRecord(trainRecord);
            if(missingRecord == null){
                continue;
            }
            NormalizedRecord bestEntropyRecord = findBestEntropy(listObjectWithMissingValue, missingRecord, distanceType);
            fillMissingValues(missingRecord, bestEntropyRecord);
        }
    }
    private NormalizedRecord findMissingRecord(NormalizedRecord trainRecord) {

        NormalizedRecord objectWithMissingValue;

            Interval[] attributes = trainRecord.getAttributes();
            if (trainRecord.containsMissingValue(attributes)) {
                objectWithMissingValue = trainRecord;
                return objectWithMissingValue;
            }
        return null;
    }

    private NormalizedRecord findBestEntropy(List<NormalizedRecord> listObjectWithMissingValue, NormalizedRecord objectWithMissingValue, String distanceType) {
        double minEntropy = Double.MAX_VALUE;
        NormalizedRecord winningRecord = null;

        int missingObjectDecision = objectWithMissingValue.getDecision();

        for (NormalizedRecord object : listObjectWithMissingValue) {

            if (object.equals(objectWithMissingValue)) {
                continue;
            }
            if (object.getDecision() != missingObjectDecision) {
                continue; // pomijamy obiekty z różnymi decyzjami
            }
            if (!object.containsMissingValue(object.getAttributes())) {
                double entropy = calculateEntropy(object, distanceType);

                // aktualizacja wartosci entropii
                if (entropy < minEntropy) {
                    minEntropy = entropy;
                    winningRecord = object;
                }
            }
        }
        return winningRecord;
    }
    private static double calculateEuclideanDistance(NormalizedRecord objectWithoutMissingAttribute) {

        Interval[] attributesWithoutMissingValue = objectWithoutMissingAttribute.getAttributes();

        double sumOfSquares = 0.0;
        int n = attributesWithoutMissingValue.length;

        List<Double> lowerList = new ArrayList<>();
        List<Double> upperList = new ArrayList<>();
        List<Double> negationLowerList = new ArrayList<>();
        List<Double> negationUpperList = new ArrayList<>();
        int id = objectWithoutMissingAttribute.getID();
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("ID obiektu: "+id);
//z pełnego rekordu wyciągnięcie przedziałow i wrzucenie ich do list
        for (Interval interval : attributesWithoutMissingValue) {
            lowerList.add(interval.getLower());
            upperList.add(interval.getUpper());
            System.out.println("---------------------------------");
            System.out.println("Przed negacja:");
            System.out.println("["+interval.getLower()+"; "+ interval.getUpper()+"]");
            Interval negation = interval.negation();
            negationLowerList.add(negation.getLower());
            negationUpperList.add(negation.getUpper());
            System.out.println("Po negacji:");
            System.out.println("["+interval.negation().getLower()+"; "+ interval.negation().getUpper()+"]");
        }
//obliczenie znormalizowanej odl. euklidesowej
        for (int i = 0; i < n; i++) {
            double lowerCompDiff = lowerList.get(i) - negationLowerList.get(i);
            double upperCompDiff = upperList.get(i) - negationUpperList.get(i);
            double maxDiffSquared = Math.max(Math.pow(lowerCompDiff, 2), Math.pow(upperCompDiff, 2));
            sumOfSquares += maxDiffSquared;

        }
        return Math.sqrt(sumOfSquares / n);
    }
    private static double calculateHammingDistance(NormalizedRecord objectWithoutMissingAttribute) {
        Interval[] attributesWithoutMissingValue = objectWithoutMissingAttribute.getAttributes();
        double totalMaxDiff = 0.0;

        for (Interval interval : attributesWithoutMissingValue) {
            Interval negation = interval.negation();
            double maxDiff = Math.max(Math.abs(interval.getLower() - negation.getLower()), Math.abs(interval.getUpper() - negation.getUpper()));
            totalMaxDiff += maxDiff;
        }

        return totalMaxDiff / attributesWithoutMissingValue.length;
    }
    private static double calculateEntropy(NormalizedRecord fullAttributesObject, String distanceType) {
        double distance = 0;
        if ("euclidean".equals(distanceType)) {
            distance = calculateEuclideanDistance(fullAttributesObject);
        } else if ("hamming".equals(distanceType)) {
            distance = calculateHammingDistance(fullAttributesObject);
        } else {
            System.out.println("..");
        }

        return 1 - distance;
    }

    private void fillMissingValues(NormalizedRecord recordWithMissingAttribute, NormalizedRecord bestRecordToFill){
        String nameAttributeWithMissingAttribute = recordWithMissingAttribute.getMissingAttributeName();
        Interval bestValueToFill = bestRecordToFill.getAttributeValue(nameAttributeWithMissingAttribute);

        recordWithMissingAttribute.setAttributeValue(nameAttributeWithMissingAttribute,bestValueToFill);
    }

}

 /** TODO
 * uporządkować metody w normalizedRecord i wykonać:
 * (Jezeli mamy obiekt w ktorym brakuje radius to przy przeszukiwaniu odrzucamy wszystkie obiekty w których jest brak innych
 *      atrybutów np.
 *      obiekt z brakiem radius - 0
 *      odrzucamy obiekt z brakiem concavity - 0)
 * Różne entropie lub rozne miary (jako element badawczy) mozna odwrocic ten distance zeby nie szukalo najmniejszego tylko
 * najwieksze pradopodobienstwo
 *
 */
