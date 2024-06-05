package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class NormalizedIntervals {
    private static final List<NormalizedRecord> listOfNormalizedIntervals = new ArrayList<>();

    public static List<NormalizedRecord> getNormalizedIntervalsList() {
        return listOfNormalizedIntervals;
    }
    public static void addNormalizedRecord(NormalizedRecord record) {
        listOfNormalizedIntervals.add(record);
    }
    public static void clear() {
        listOfNormalizedIntervals.clear();
    }
}
