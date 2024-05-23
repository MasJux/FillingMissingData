package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Objects.Record;

import java.util.ArrayList;
import java.util.List;

public class DataAfterDeleting {
    private static final List<Record> dataAfterDeleting = new ArrayList<>();

    public static List<Record> getListWithMissingValues() {
        return dataAfterDeleting;
    }
    public static void addDeletedRecord(Record record) {
        dataAfterDeleting.add(record);
    }
    public static void clear() {
        dataAfterDeleting.clear();
    }
}
