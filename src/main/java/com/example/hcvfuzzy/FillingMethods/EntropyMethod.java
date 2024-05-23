package com.example.hcvfuzzy.FillingMethods;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Holders.DataAfterEntropyFilling;

import java.util.*;

public class EntropyMethod {
    HashMap<NormalizedRecord,NormalizedRecord> bestObjectAndEntropy;
    public HashMap completeMissingValue(List<NormalizedRecord> listObjectWithMissingValue) {
        bestObjectAndEntropy = new HashMap<>();
        NormalizedRecord objectWithMissingValue;
        NormalizedRecord winningRecord = null;
        String nameOfMissingAttribute;
        int missingObjectDecision;

        // Szukamy obiektu z brakujaca dana
        for (NormalizedRecord record : listObjectWithMissingValue) {
            double minEntropy = Double.MAX_VALUE;
            double[][] attributes = record.getAttributes();
            System.out.println(record.getMissingAttributeName()+" name");
            System.out.println(record.containsMissingValue(attributes));
            if (record.containsMissingValue(attributes)) {
                objectWithMissingValue = record;
                nameOfMissingAttribute = objectWithMissingValue.getMissingAttributeName();
                missingObjectDecision = objectWithMissingValue.getDecision();
                System.out.println("=============New Iteration===============");
                System.out.println("Brakująca dana: " + nameOfMissingAttribute+" z ID: "+objectWithMissingValue.getID()+" z decyzją: "+missingObjectDecision);
            } else {
                continue;
            }

            for (NormalizedRecord object : listObjectWithMissingValue) {
                String checkingNameOfMissingAttribute = null;
                int checkingObjectDecision = object.getDecision();
                if(object.containsMissingValue(attributes)){
                   checkingNameOfMissingAttribute = object.getMissingAttributeName();
                }
                if (!(object.equals(objectWithMissingValue)) && (checkingNameOfMissingAttribute==null || checkingNameOfMissingAttribute.equals(nameOfMissingAttribute)) && missingObjectDecision == checkingObjectDecision) {
                    double entropy = calculateEntropy(objectWithMissingValue, object);
                    // aktualizacja wartosci entropii
                    if (entropy < minEntropy) {
                        minEntropy = entropy;
                        winningRecord = object;
//                       System.out.println("[[ID Best Object: "+object.getID()+". Best Object:"+object+". ID Missing Object"+objectWithMissingValue.getID()+". Missing Object: "+objectWithMissingValue+". Entropy: "+minEntropy);
                    }
                }
            }
            bestObjectAndEntropy.put(objectWithMissingValue,winningRecord);
            System.out.println(bestObjectAndEntropy);
        }
        fillMissingValues();
        updateRecords(listObjectWithMissingValue, bestObjectAndEntropy);
//        System.out.println("Best Object and Entropy: "+bestObjectAndEntropy);
//        System.out.println(bestObjectAndEntropy.size());
            return bestObjectAndEntropy;
        }
    private void fillMissingValues(){
        for (NormalizedRecord recordWithMissingValue : bestObjectAndEntropy.keySet()) {
            NormalizedRecord recordWithBestValue = bestObjectAndEntropy.get(recordWithMissingValue);
            String nameRecordWithMissingValue = recordWithMissingValue.getMissingAttributeName();
//            double value = recordWithBestValue.getAttributeValue(nameRecordWithMissingValue);
//            recordWithMissingValue.setAttributeValue(nameRecordWithMissingValue, value);
//            System.out.println("Value: " +value +" from "+recordWithBestValue.getID()+"attribute name: "+nameRecordWithMissingValue+". Filling object: "+recordWithMissingValue.getID());
        }
    }
    public void updateRecords(List<NormalizedRecord> listObjectWithMissingValue, Map<NormalizedRecord, NormalizedRecord> bestObjectAndEntropy) {

        for (NormalizedRecord recordWithMissingValue : listObjectWithMissingValue) {
            NormalizedRecord recordWithBestValue = bestObjectAndEntropy.get(recordWithMissingValue);
            NormalizedRecord recordWithMissingValueCopy = new NormalizedRecord(recordWithMissingValue);
            if (recordWithBestValue != null) {
                String missingAttributeName = recordWithMissingValue.getMissingAttributeName();
                double[] value = recordWithBestValue.getAttributeValue(missingAttributeName);

                // Ustawianie zaktualizowanej wartości w odpowiednim atrybucie
                recordWithMissingValueCopy.setAttributeValue(missingAttributeName, value);

                DataAfterEntropyFilling.getDataAfterEntropyFilling().add(recordWithMissingValueCopy);
            }else{
                DataAfterEntropyFilling.getDataAfterEntropyFilling().add(recordWithMissingValueCopy);

            }
        }
    }
    private static double calculateEntropy(NormalizedRecord missingAttributeObject, NormalizedRecord fullAttributesObject) {

        double distance = calculateDistance(missingAttributeObject, fullAttributesObject);

        return 1 / distance;
    }
    private static double calculateDistance(NormalizedRecord objectWithMissingAttribute, NormalizedRecord objectWithoutMissingAttribute) {
        double[][] attributesWithMissingValue = objectWithMissingAttribute.getAttributes();
        double[][] attributesWithoutMissingValue = objectWithoutMissingAttribute.getAttributes();
        double sumOfSquares = 0.0;
        int n = attributesWithMissingValue.length;

        for (int i = 0; i < n; i++) {
            if (Arrays.equals(attributesWithMissingValue[i], new double[]{0, 0})) {
                continue;
            }
            double lowerCompDiff = attributesWithMissingValue[i][0] - attributesWithoutMissingValue[i][0];
            double upperCompDiff = attributesWithMissingValue[i][1] - attributesWithoutMissingValue[i][1];
            double maxDiffSquared = Math.max(Math.pow(lowerCompDiff, 2), Math.pow(upperCompDiff, 2));
            sumOfSquares += maxDiffSquared;
        }
        return Math.sqrt(sumOfSquares / n);
    }
}
/**
 * liczenie odległości euklidesowej między obiektem z brakującymi danymi a resztą obiektów(ta sama klasa decyzyjna)
 * pozniej korzystając z odl. euklidesowej liczymy entropie, im mniejsza tym bardziej podobna.
 * Powinno zapisywać entropię do danego obiektu i wybierać najlepsze dopasowanie a pozniej uzupełnic tym dane.
 *

 * TODO
 * uporządkować metody w normalizedRecord i wykonać:
 * (Jezeli mamy obiekt w ktorym brakuje radius to przy przeszukiwaniu odrzucamy wszystkie obiekty w których jest brak innych
 *      atrybutów np.
 *      obiekt z brakiem radius - 0
 *      odrzucamy obiekt z brakiem concavity - 0)
 * Różne entropie lub rozne miary (jako element badawczy) mozna odwrocic ten distance zeby nie szukalo najmniejszego tylko
 * najwieksze pradopodobienstwo
 *
 */
