package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Objects.Record;

import java.util.ArrayList;
import java.util.List;

public class DataBeforeDeleting {
    private static final List<Record> dataBeforeDeleting = new ArrayList<>();

    public static List<Record> getListWithoutMissingValues() {
        return dataBeforeDeleting;
    }
    public static void addRecord(Record recordWithoutMissing) {
        dataBeforeDeleting.add(recordWithoutMissing);
    }
    public static void clear() {
        dataBeforeDeleting.clear();
    }
}
