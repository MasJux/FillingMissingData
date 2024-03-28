package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Constructors.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Normalization {
    private int minRecordObject;
    private int maxRecordObject;
    public void normalizeData(List<Record> dataList){
        checkMinMax(dataList, Record::getRadius);

        //System.out.println("Max: "+maxRecordObject+" - Min: "+minRecordObject);
//        checkMinMax(dataList, Record::getTexture);
//        System.out.println("Max: "+maxRecordObject+" - Min: "+minRecordObject);
    }
    private void checkMinMax(List<Record> dataList, Function<Record, Integer> extractor) {
        minRecordObject = Integer.MAX_VALUE;
        maxRecordObject = Integer.MIN_VALUE;
        for(int i=0;i<dataList.size();i++) {
            int recordObject = extractor.apply(dataList.get(i));
            if (recordObject < minRecordObject) {
                minRecordObject = recordObject;
            }
            if (recordObject > maxRecordObject) {
                maxRecordObject = recordObject;
            }
        }
        for(int i=0;i<dataList.size();i++) {
            int recordObject = extractor.apply(dataList.get(i));
            System.out.println("recordObject: "+recordObject);
            System.out.println("Max: "+maxRecordObject+" - Min: "+minRecordObject);
            double normalized = ((double)recordObject - minRecordObject)/(maxRecordObject-minRecordObject);
            String formattedValue = String.format("%.6f", normalized);
            System.out.println(i+1+" - "+formattedValue);
        }
    }
}
