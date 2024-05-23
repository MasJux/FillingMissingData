package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class NormalizedDataBeforeDeletingHolder {
    private static final List<NormalizedRecord> listBeforeDeleting = new ArrayList<>();

    public static List<NormalizedRecord> getDefaultPublicNormalizedDataList() {
        return listBeforeDeleting;
    }
    public static void addNormalizedRecord(NormalizedRecord normalizedRecord) {
        listBeforeDeleting.add(normalizedRecord);
    }
    public static void clear() {
        listBeforeDeleting.clear();
    }
}
