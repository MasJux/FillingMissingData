package com.example.hcvfuzzy.Holders;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;

import java.util.ArrayList;
import java.util.List;

public class NormalizedDataBeforeDeletingHolder {
    private static final List<NormalizedRecord> defaultPublicNormalizedDataList = new ArrayList<>();

    public static List<NormalizedRecord> getDefaultPublicNormalizedDataList() {
        return defaultPublicNormalizedDataList;
    }
}
