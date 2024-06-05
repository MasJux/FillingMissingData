package com.example.hcvfuzzy.FillingMethods;
import com.example.hcvfuzzy.Holders.ComplementIntervals;
import com.example.hcvfuzzy.Holders.DataBeforeDeleting;
import com.example.hcvfuzzy.Objects.Interval;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Objects.Record;

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

            Interval[] attributes = trainRecord.getAttributes();
            if (trainRecord.containsMissingValue(attributes)) {
                objectWithMissingValue = trainRecord;
                System.out.println("Empty: "+objectWithMissingValue.getID()+" - "+ objectWithMissingValue.getNormalizedRadius());
                return objectWithMissingValue;
            }


        return null;
    }

    private NormalizedRecord findBestEntropy(List<NormalizedRecord> listObjectWithMissingValue, NormalizedRecord objectWithMissingValue) {
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
                double entropy = calculateEntropy(object);
                // aktualizacja wartosci entropii
                if (entropy < minEntropy) {
                    minEntropy = entropy;
                    winningRecord = object;
                }
            }
        }
        return winningRecord;
    }
    private static double calculateDistance(NormalizedRecord objectWithoutMissingAttribute) {

        Interval[] attributesWithoutMissingValue = objectWithoutMissingAttribute.getAttributes();

        double sumOfSquares = 0.0;
        int n = 0;

        List<Double> withoutMissingAttributeLowerList = new ArrayList<>();
        List<Double> withoutMissingAttributeUpperList = new ArrayList<>();

        List<Double> withoutMissingAttributeNegationLowerList = new ArrayList<>();
        List<Double> withoutMissingAttributeNegationUpperList = new ArrayList<>();

//z pełnego rekordu wyciągnięcie przedziałow i wrzucenie ich do list
        for(Interval firstInterval:attributesWithoutMissingValue){

            double withoutMissingAttributeLowerComp = firstInterval.getLower();
            double withoutMissingAttributeUpperComp = firstInterval.getUpper();

            withoutMissingAttributeLowerList.add(withoutMissingAttributeLowerComp);
            withoutMissingAttributeUpperList.add(withoutMissingAttributeUpperComp);
        }
//negacja pełnego rekordu i wrzucenie jej do listy
        for(Interval secondInterval:attributesWithoutMissingValue){

            double withoutMissingAttributeLowerComp = secondInterval.negation().getLower();
            double withoutMissingAttributeUpperComp = secondInterval.negation().getUpper();

            withoutMissingAttributeNegationLowerList.add(withoutMissingAttributeLowerComp);
            withoutMissingAttributeNegationUpperList.add(withoutMissingAttributeUpperComp);
        }
//obliczenie znormalizowanej odl. euklidesowej
        for(int i = 0; i<withoutMissingAttributeLowerList.size();i++){

            double lowerCompDiff = withoutMissingAttributeLowerList.get(i)-withoutMissingAttributeNegationLowerList.get(i);
            double upperCompDiff = withoutMissingAttributeUpperList.get(i)-withoutMissingAttributeNegationUpperList.get(i);
            double maxDiffSquared = Math.max(Math.pow(lowerCompDiff, 2), Math.pow(upperCompDiff, 2));
            sumOfSquares += maxDiffSquared;
            n++;
        }

//        System.out.println("withoutMissingAttributeLowerList: "+withoutMissingAttributeLowerList);
//        System.out.println("withoutMissingAttributeUpperList: "+withoutMissingAttributeUpperList);

        return Math.sqrt(sumOfSquares / n);
    }
    private static double calculateEntropy(NormalizedRecord fullAttributesObject) {

        double distance = calculateDistance(fullAttributesObject);

        return 1 - distance;
    }
    private void fillMissingValues(NormalizedRecord recordWithMissingAttribute, NormalizedRecord bestRecordToFill){
        String nameAttributeWithMissingAttribute = recordWithMissingAttribute.getMissingAttributeName();
        Interval bestValueToFill = bestRecordToFill.getAttributeValue(nameAttributeWithMissingAttribute);

        recordWithMissingAttribute.setAttributeValue(nameAttributeWithMissingAttribute,bestValueToFill);
        System.out.println("Filled: "+recordWithMissingAttribute.getID()+ " - " + recordWithMissingAttribute.getNormalizedRadius());
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
