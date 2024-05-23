package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Objects.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class NormalizedDataAfterDeletingHolder {
    private static final List<NormalizedRecord> listAfterDeleting = new ArrayList<>();

    public static List<NormalizedRecord> getAfterDeletingPublicNormalizedDataList() {
        return listAfterDeleting;
    }
    public static void addNormalizedRecord(NormalizedRecord record) {
        listAfterDeleting.add(record);
    }
    public static void clear() {
        listAfterDeleting.clear();
    }
}
