package com.example.hcvfuzzy.AlgorithmkNN;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;
import com.example.hcvfuzzy.Holders.NormalizedDataAfterDeletingHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingTestingData {
    public void splitTrainAndTestData(){
        List<NormalizedRecord> normalizedDataListAfterDeleting = NormalizedDataAfterDeletingHolder.getAfterDeletingPublicNormalizedDataList();

        double trainPercentage = 0.7;
        List<NormalizedRecord> trainData = new ArrayList<>();
        List<NormalizedRecord> testData = new ArrayList<>();

        long seed = System.nanoTime();
        Random random = new Random(seed);
        List<NormalizedRecord> shuffledData = new ArrayList<>(normalizedDataListAfterDeleting);

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
    }
}
