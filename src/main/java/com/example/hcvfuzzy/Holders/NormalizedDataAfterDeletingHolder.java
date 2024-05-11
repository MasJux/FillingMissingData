package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class NormalizedDataAfterDeletingHolder {
    private static final List<NormalizedRecord> afterDeletingPublicNormalizedDataList = new ArrayList<>();

    public static List<NormalizedRecord> getAfterDeletingPublicNormalizedDataList() {
        return afterDeletingPublicNormalizedDataList;
    }
}
