package com.example.hcvfuzzy.FillingMethods;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Holders.DataAfterEntropyFilling;

import java.util.*;

public class EntropyMethod {
    //HashMap<NormalizedRecord,NormalizedRecord> bestObjectAndEntropy;
    public void completeMissingValue(List<NormalizedRecord> listObjectWithMissingValue) {
        for(NormalizedRecord trainRecord: listObjectWithMissingValue) {
            NormalizedRecord missingRecord = findMissingRecord(trainRecord);
            if(missingRecord == null){
                continue;
            }
            NormalizedRecord bestEntropyRecord = findBestEntropy(listObjectWithMissingValue, missingRecord);
            System.out.println("best - "+bestEntropyRecord);
            fillMissingValues(missingRecord, bestEntropyRecord);
        }
    }
    private NormalizedRecord findMissingRecord(NormalizedRecord trainRecord) {

        NormalizedRecord objectWithMissingValue;

            double[][] attributes = trainRecord.getAttributes();
            if (trainRecord.containsMissingValue(attributes)) {
                objectWithMissingValue = trainRecord;
                System.out.println("Empty: "+objectWithMissingValue.getID()+" - "+ Arrays.toString(objectWithMissingValue.getNormalizedRadius()));
                return objectWithMissingValue;
            }


        return null;
    }

    private NormalizedRecord findBestEntropy(List<NormalizedRecord> listObjectWithMissingValue, NormalizedRecord objectWithMissingValue) {
        double minEntropy = Double.MAX_VALUE;
        NormalizedRecord winningRecord = null;

        String nameOfMissingAttribute = objectWithMissingValue.getMissingAttributeName();
        int missingObjectDecision = objectWithMissingValue.getDecision();

        for (NormalizedRecord object : listObjectWithMissingValue) {

            if (object.equals(objectWithMissingValue)) {
                continue;
            }
            if (object.getDecision() != missingObjectDecision) {
                continue; // pomijamy obiekty z różnymi decyzjami
            }
            if (!object.containsMissingValue(object.getAttributes()) || object.getMissingAttributeName().equals(nameOfMissingAttribute)) {
                double entropy = calculateEntropy(objectWithMissingValue, object);
                // aktualizacja wartosci entropii
                if (entropy < minEntropy) {
                    minEntropy = entropy;
                    winningRecord = object;
                }
            }
        }
        return winningRecord;
    }
    private void fillMissingValues(NormalizedRecord recordWithMissingAttribute, NormalizedRecord bestRecordToFill){
        String nameAttributeWithMissingAttribute = recordWithMissingAttribute.getMissingAttributeName();
        double[] bestValueToFill = bestRecordToFill.getAttributeValue(nameAttributeWithMissingAttribute);

        recordWithMissingAttribute.setAttributeValue(nameAttributeWithMissingAttribute,bestValueToFill);
        System.out.println("Filled: "+recordWithMissingAttribute.getID()+ " - " + Arrays.toString(recordWithMissingAttribute.getNormalizedRadius()));
    }

    private static final double[] MISSING_VALUE = null;
    private static double calculateDistance(NormalizedRecord objectWithMissingAttribute, NormalizedRecord objectWithoutMissingAttribute) {
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
    private static double calculateEntropy(NormalizedRecord missingAttributeObject, NormalizedRecord fullAttributesObject) {

        double distance = calculateDistance(missingAttributeObject, fullAttributesObject);

        return 1 / distance;
    }
//    public void updateRecords(List<NormalizedRecord> listObjectWithMissingValue, NormalizedRecord missingObject, NormalizedRecord bestObjectToFill) {
//
//               String missingAttributeName = recordWithMissingValue.getMissingAttributeName();
//               bestObjectToFill.getAttributeValue()
//                recordWithMissingValueCopy.setAttributeValue(missingAttributeName, value);
//
//                DataAfterEntropyFilling.getDataAfterEntropyFilling().add(recordWithMissingValueCopy);
//            }else{
//                DataAfterEntropyFilling.getDataAfterEntropyFilling().add(recordWithMissingValueCopy);
//
//            }
//        }
//    }
}
/** TODO
 * Dac update listy po uzupełnieniu i przekazać ją w classifynewobject jako liste
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
