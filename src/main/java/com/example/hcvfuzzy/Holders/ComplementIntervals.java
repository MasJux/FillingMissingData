package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class ComplementIntervals {
    private static final List<NormalizedRecord> listOfComplementIntervals = new ArrayList<>();

    public static List<NormalizedRecord> getComplementIntervalsList() {
        return listOfComplementIntervals;
    }
    public static void addComplementRecord(NormalizedRecord complementRecord) {
        listOfComplementIntervals.add(complementRecord);
    }
    public static void clear() {
        listOfComplementIntervals.clear();
    }
}
