package com.example.hcvfuzzy.FillingMethods;
import com.example.hcvfuzzy.Constructors.NormalizedRecord;
import com.example.hcvfuzzy.Controllers.DeletingCellsController;

import java.lang.reflect.Array;
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
            double[] attributes = record.getAttributes();
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
                int checkingObjectDecision;
                checkingObjectDecision = object.getDecision();
                if(object.containsMissingValue(attributes)){
                   checkingNameOfMissingAttribute = object.getMissingAttributeName();
                   System.out.println("ID: "+object.getID()+". Missing Value: "+checkingNameOfMissingAttribute);
                }
                if (!(object.equals(objectWithMissingValue)) && (checkingNameOfMissingAttribute==null || checkingNameOfMissingAttribute.equals(nameOfMissingAttribute)) && missingObjectDecision == checkingObjectDecision) {
                    double entropy = calculateEntropy(objectWithMissingValue, object);
                    //System.out.println("objmissin: " + objectWithMissingValue + " - " + object);
//                    System.out.println("Entropia:" + entropy);
                    // aktualizacja wartosci entropii
                    if (entropy < minEntropy) {
                        minEntropy = entropy;
                        winningRecord = object;
//                        System.out.println(objectWithMissingValueID+" - "+winningRecordID);
                       System.out.println("[[ID Best Object: "+object.getID()+". Best Object:"+object+". ID Missing Object"+objectWithMissingValue.getID()+". Missing Object: "+objectWithMissingValue+". Entropy: "+minEntropy);
                    }
                }else{
                    System.out.println("nie moge wykonac");
                }
            }
            bestObjectAndEntropy.put(objectWithMissingValue,winningRecord);
        }
        fillMissingValues();
        System.out.println("Best Object and Entropy: "+bestObjectAndEntropy);
        System.out.println(bestObjectAndEntropy.size());
            return bestObjectAndEntropy;
        }
    private void fillMissingValues(){
        for (NormalizedRecord recordWithMissingValue : bestObjectAndEntropy.keySet()) {
            NormalizedRecord recordWithBestValue = bestObjectAndEntropy.get(recordWithMissingValue);
            String nameRecordWithMissingValue = recordWithMissingValue.getMissingAttributeName();
            double value = recordWithBestValue.getAttributeValue(nameRecordWithMissingValue);
            System.out.println();
            System.out.println("Value: " +value +" from "+recordWithBestValue.getID()+"attribute name: "+nameRecordWithMissingValue+". Filling object: "+recordWithMissingValue.getID());
        }
    }
    private static double calculateEntropy(NormalizedRecord missingAttributeObject, NormalizedRecord fullAttributesObject) {
        double[] attributesWithMissingValue = missingAttributeObject.getAttributes();
        double[] attributesWithoutMissingValue = fullAttributesObject.getAttributes();
        // euklides
        double distance = calculateDistance(attributesWithMissingValue, attributesWithoutMissingValue);
        // wychwycenie najmniejszej entropii
        return 1 / distance;
    }
    private static double calculateDistance(double[] missingAttributeObject, double[] fullAttributesObject) {
        double sumSquaredDiff = 0.0;

        // roznica kwadratów
        for (int i = 0; i < missingAttributeObject.length; i++) {
            double diff = missingAttributeObject[i] - fullAttributesObject[i];
            sumSquaredDiff += Math.pow(diff, 2);
        }

        // pierwiastek sumy kwadratów różnic
        double distance = Math.sqrt(sumSquaredDiff);

        return distance;
    }

}
/**
 * liczenie odległości euklidesowej między obiektem z brakującymi danymi a resztą obiektów(ta sama klasa decyzyjna)
 * pozniej korzystając z odl. euklidesowej liczymy entropie, im mniejsza tym bardziej podobna.
 * Powinno zapisywać entropię do danego obiektu i wybierać najlepsze dopasowanie a pozniej uzupełnic tym dane.
 *
 * Oprócz tego powinno pomijać obiekty w których brakuje jakiejs danej(?)
 * Czy ma być np. 100 wierszy z jedną brakującą daną? Czy na cały dataset ma miec 1 brakująca.
 * Czy metoda licząca jest odpowiednia.
 * TODO
 * !IMPORTANT uporządkować metody w normalizedRecord i wykonać:
 * (Jezeli mamy obiekt w ktorym brakuje radius to przy przeszukiwaniu odrzucamy wszystkie obiekty w których jest brak innych
 *      atrybutów np.
 *      obiekt z brakiem radius - 0
 *      odrzucamy obiekt z brakiem concavity - 0)
 * Różne entropie lub rozne miary (jako element badawczy) mozna odwrocic ten distance zeby nie szualo najmniejszego tylko
 * najwieksze pradopodobienstwo
 */
