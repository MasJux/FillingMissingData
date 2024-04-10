package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;
import com.example.hcvfuzzy.Constructors.Record;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Normalization {
    private final ObservableList<NormalizedRecord> normalizedDataList = FXCollections.observableArrayList();
    public List<NormalizedRecord> publicNormalizedDataList = new ArrayList<>();
    private final ObservableList<NormalizedRecord> normalizeDataList = FXCollections.observableArrayList();
    private static final List<String> ATTRIBUTES = Arrays.asList("radius", "texture", "perimeter", "area", "smoothness", "compactness"
            , "concavity", "concavePoints", "symmetry", "fractalDimension");

    public List<NormalizedRecord> getPublicNormalizedDataList() {
        return normalizedDataList;
    }
    public List<NormalizedRecord> normalizeData(List<Record> dataList) throws ParseException {
        // Znajdź min i max wartości dla każdego atrybutu
        Map<String, Integer> minValues = new HashMap<>();
        Map<String, Integer> maxValues = new HashMap<>();
        for (String attributeName : ATTRIBUTES) {
            minValues.put(attributeName, Integer.MAX_VALUE);
            maxValues.put(attributeName, Integer.MIN_VALUE);
        }

        for (Record record : dataList) {
            for (String attributeName : ATTRIBUTES) {
                int attributeValue = record.getAttributeValue(attributeName);
                int minAttributeValue = minValues.get(attributeName);
                int maxAttributeValue = maxValues.get(attributeName);
                minValues.put(attributeName, Math.min(attributeValue, minAttributeValue));
                maxValues.put(attributeName, Math.max(attributeValue, maxAttributeValue));
            }
        }
        DecimalFormat df = new DecimalFormat("#.#####");

        // Utwórz obiekty NormalizedRecord dla każdego rekordu i ustaw znormalizowane wartości atrybutów
        for (Record record : dataList) {
            NormalizedRecord normalizedRecord = new NormalizedRecord();

            for (String attributeName : ATTRIBUTES) {
                int attributeValue = record.getAttributeValue(attributeName);
                int minAttributeValue = minValues.get(attributeName);
                int maxAttributeValue = maxValues.get(attributeName);
                double normalizedValue = df.parse(df.format((double) (attributeValue - minAttributeValue) / (maxAttributeValue - minAttributeValue))).doubleValue();

                normalizedRecord.setAttributeValue(attributeName, normalizedValue);

            }
            // Dodaj obiekt NormalizedRecord do listy
            publicNormalizedDataList.add(normalizedRecord);
        }
        for (int i = 0; i < publicNormalizedDataList.size(); i++) {
            NormalizedRecord normalizedRecord = publicNormalizedDataList.get(i);
            //System.out.println("Normalized Record " + (i + 1) + ":");
            for (String attributeName : ATTRIBUTES) {
                double normalizedValue = normalizedRecord.getAttributeValue(attributeName);
                //System.out.println("\t" + attributeName + ": " + String.format("%.5f", normalizedValue));
            }
            System.out.println(); // Nowa linia między rekordami
        }
        return publicNormalizedDataList;
    }

    public TableView<NormalizedRecord> updateTableViewWithNormalizedData( List<Record> dataList) throws ParseException {
        normalizeData(dataList);
        TableView<NormalizedRecord> newTableView = new TableView<>();
        newTableView.getItems().clear();
        TableColumn<NormalizedRecord, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        newTableView.getColumns().add(idColumn);
        TableColumn<NormalizedRecord, Integer> decisionColumn = new TableColumn<>("Decision");
        decisionColumn.setCellValueFactory(new PropertyValueFactory<>("Decision"));

        List<String> attributeNames = Arrays.asList("radius", "texture", "perimeter", "area", "smoothness", "compactness", "concavity", "concavePoints", "symmetry", "fractalDimension");
        for (String attributeName : attributeNames) {
            TableColumn<NormalizedRecord, String> column = new TableColumn<>(attributeName);
            column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAttributeValue(attributeName)).asObject().asString());
            newTableView.getColumns().add(column);
        }

        // Ustaw dane dla tabeli
        newTableView.setItems(FXCollections.observableArrayList(normalizeDataList));
        newTableView.getColumns().add(decisionColumn);


        for (int i = 0; i < publicNormalizedDataList.size(); i++) {
            Record originalRecord = dataList.get(i);
            NormalizedRecord normalizedRecord = publicNormalizedDataList.get(i);
            NormalizedRecord newRecord = new NormalizedRecord();

            newRecord.setID(originalRecord.getID());
            newRecord.setNormalizedRadius(normalizedRecord.getNormalizedRadius());
            newRecord.setNormalizedTexture(normalizedRecord.getNormalizedTexture());
            newRecord.setNormalizedPerimeter(normalizedRecord.getNormalizedPerimeter());
            newRecord.setNormalizedArea(normalizedRecord.getNormalizedArea());
            newRecord.setNormalizedSmoothness(normalizedRecord.getNormalizedSmoothness());
            newRecord.setNormalizedCompactness(normalizedRecord.getNormalizedCompactness());
            newRecord.setNormalizedConcavity(normalizedRecord.getNormalizedConcavity());
            newRecord.setNormalizedConcavePoints(normalizedRecord.getNormalizedConcavePoints());
            newRecord.setNormalizedSymmetry(normalizedRecord.getNormalizedSymmetry());
            newRecord.setNormalizedFractalDimension(normalizedRecord.getNormalizedFractalDimension());
            newRecord.setDecision(originalRecord.getDecision());

            normalizeDataList.add(newRecord);

        }
        newTableView.setItems(normalizeDataList);
        return newTableView;
    }
}