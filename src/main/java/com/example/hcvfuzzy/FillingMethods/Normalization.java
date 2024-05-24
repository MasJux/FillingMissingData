package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Holders.NormalizedIntervalDataHolder;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Objects.Record;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

public class Normalization {
    private final ObservableList<NormalizedRecord> normalizeDataList = FXCollections.observableArrayList();
    //public List<NormalizedRecord> defaultPublicNormalizedDataList = new ArrayList<>();
    private static final List<String> ATTRIBUTES = Arrays.asList("radius", "texture", "perimeter", "area", "smoothness", "compactness"
            , "concavity", "concavePoints", "symmetry", "fractalDimension");

    public void normalizeData(List<Record> dataList) throws ParseException {
        Map<String, Integer> minValues = new HashMap<>();
        Map<String, Integer> maxValues = new HashMap<>();
        for (String attributeName : ATTRIBUTES) {
            minValues.put(attributeName, Integer.MAX_VALUE);
            maxValues.put(attributeName, Integer.MIN_VALUE);
        }
        //szukaj min i max
        for (Record record : dataList) {
            for (String attributeName : ATTRIBUTES) {
                int attributeValue = record.getAttributeValue(attributeName);
                if (attributeValue >= 0) {
                    int minAttributeValue = minValues.get(attributeName);
                    int maxAttributeValue = maxValues.get(attributeName);
                    minValues.put(attributeName, Math.min(attributeValue, minAttributeValue));
                    maxValues.put(attributeName, Math.max(attributeValue, maxAttributeValue));
                }
            }
        }
        System.out.println(minValues+" - "+maxValues);
        DecimalFormat df = new DecimalFormat("#.#####");

        // Utwórz obiekty NormalizedRecord dla każdego rekordu i ustaw znormalizowane wartości atrybutów
        for (Record record : dataList) {
            NormalizedRecord normalizedRecord = new NormalizedRecord();
            int id = record.getID();
            int decision = record.getDecision();

            for (String attributeName : ATTRIBUTES) {
                int attributeValue = record.getAttributeValue(attributeName);
                if (attributeValue >= 0) {
                    int minAttributeValue = minValues.get(attributeName);
                    int maxAttributeValue = maxValues.get(attributeName);

                    double normalizedValue = df.parse(df.format((double) (attributeValue - minAttributeValue) / (maxAttributeValue - minAttributeValue))).doubleValue();
                    double[] intervalAttribute = calculateInterval(normalizedValue);

                    normalizedRecord.setAttributeValue(attributeName, intervalAttribute);
                }

                normalizedRecord.setID(id);
                normalizedRecord.setDecision(decision);
            }

            NormalizedIntervalDataHolder.addNormalizedRecord(normalizedRecord);
        }
    }
    public static double g(double a) {
        return Math.min(a,1-a);
    }
    private double[] calculateInterval(double x) {
        double lowerComp;
        double upperComp;
        lowerComp = x * (1 - 0.25 * 2 * g(x));
        upperComp = x * (1 - 0.25 * 2 * g(x) + 0.25 * 2 * g(x));

        DecimalFormat df = new DecimalFormat("#.#####");
        lowerComp = Double.parseDouble(df.format(lowerComp).replace(',', '.'));
        upperComp = Double.parseDouble(df.format(upperComp).replace(',', '.'));
        return new double[]{lowerComp, upperComp};
    }

    public TableView<NormalizedRecord> updateTableViewWithNormalizedData( List<Record> dataList) throws ParseException {
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
            //??
            column.setCellValueFactory(cellData -> {
                NormalizedRecord record = cellData.getValue();
                // Pobranie odpowiedniego atrybutu znormalizowanego na podstawie nazwy kolumny
                double[] attributeValues = switch (attributeName) {
                    case "radius" -> record.getNormalizedRadius();
                    case "texture" -> record.getNormalizedTexture();
                    case "perimeter" -> record.getNormalizedPerimeter();
                    case "area" -> record.getNormalizedArea();
                    case "smoothness" -> record.getNormalizedSmoothness();
                    case "compactness" -> record.getNormalizedCompactness();
                    case "concavity" -> record.getNormalizedConcavity();
                    case "concavePoints" -> record.getNormalizedConcavePoints();
                    case "symmetry" -> record.getNormalizedSymmetry();
                    case "fractalDimension" -> record.getNormalizedFractalDimension();
                    default -> new double[0]; // Domyślna wartość, jeśli atrybut nie jest obsługiwany
                };
                // Konwersja tablicy wartości na ciąg znaków do wyświetlenia w komórce
                return new SimpleStringProperty(Arrays.toString(attributeValues));
            });
            newTableView.getColumns().add(column);
        }


        newTableView.setItems(FXCollections.observableArrayList(normalizeDataList));
        newTableView.getColumns().add(decisionColumn);
        List<NormalizedRecord> normalizedDataList = NormalizedIntervalDataHolder.getNormalizedIntervalsList();

        for (int i = 0; i < normalizedDataList.size(); i++) {
            Record originalRecord = dataList.get(i);
            NormalizedRecord normalizedRecord = normalizedDataList.get(i);

                normalizedRecord.setID(originalRecord.getID());
                normalizedRecord.setNormalizedRadius(normalizedRecord.getNormalizedRadius());
                normalizedRecord.setNormalizedTexture(normalizedRecord.getNormalizedTexture());
                normalizedRecord.setNormalizedPerimeter(normalizedRecord.getNormalizedPerimeter());
                normalizedRecord.setNormalizedArea(normalizedRecord.getNormalizedArea());
                normalizedRecord.setNormalizedSmoothness(normalizedRecord.getNormalizedSmoothness());
                normalizedRecord.setNormalizedCompactness(normalizedRecord.getNormalizedCompactness());
                normalizedRecord.setNormalizedConcavity(normalizedRecord.getNormalizedConcavity());
                normalizedRecord.setNormalizedConcavePoints(normalizedRecord.getNormalizedConcavePoints());
                normalizedRecord.setNormalizedSymmetry(normalizedRecord.getNormalizedSymmetry());
                normalizedRecord.setNormalizedFractalDimension(normalizedRecord.getNormalizedFractalDimension());
                normalizedRecord.setDecision(originalRecord.getDecision());

                normalizeDataList.add(normalizedRecord);
            }

        newTableView.setItems(normalizeDataList);
        return newTableView;
    }
}