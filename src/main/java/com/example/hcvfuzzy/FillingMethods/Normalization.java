package com.example.hcvfuzzy.FillingMethods;

import com.example.hcvfuzzy.Holders.NormalizedIntervals;
import com.example.hcvfuzzy.Objects.Interval;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Objects.Record;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

public class Normalization {
    private ObservableList<NormalizedRecord> normalizeDataList = FXCollections.observableArrayList();
    private static final List<String> ATTRIBUTES = Arrays.asList("texture", "perimeter", "area", "smoothness", "compactness"
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
        DecimalFormat df = new DecimalFormat("#.#####");
        NormalizedIntervals.clear();
        // Utwórz obiekty NormalizedRecord dla każdego rekordu i ustaw znormalizowane wartości atrybutów
        for (Record record : dataList) {
            NormalizedRecord normalizedRecord = new NormalizedRecord();
            int decision = record.getDecision();

            for (String attributeName : ATTRIBUTES) {
                int attributeValue = record.getAttributeValue(attributeName);
                if (attributeValue >= 0) {
                    int minAttributeValue = minValues.get(attributeName);
                    int maxAttributeValue = maxValues.get(attributeName);

                    double normalizedValue = df.parse(df.format((double) (attributeValue - minAttributeValue) / (maxAttributeValue - minAttributeValue))).doubleValue();
                    Interval intervalAttribute = calculateInterval(normalizedValue);

                    normalizedRecord.setAttributeValue(attributeName, intervalAttribute);
                }
                normalizedRecord.setDecision(decision);
            }

            NormalizedIntervals.addNormalizedRecord(normalizedRecord);
        }
    }
    public static double g(double a) {
        return Math.min(a,1-a);
    }
    private Interval calculateInterval(double x) {
        double lowerComp;
        double upperComp;
        lowerComp = x * (1 - 0.25 * 2 * g(x));
        upperComp = lowerComp + (0.25 * 2 * g(x));

        DecimalFormat df = new DecimalFormat("#.#####");
        lowerComp = Double.parseDouble(df.format(lowerComp).replace(',', '.'));
        upperComp = Double.parseDouble(df.format(upperComp).replace(',', '.'));

        return new Interval(lowerComp, upperComp);
    }

    public TableView<NormalizedRecord> updateTableViewWithNormalizedData( List<Record> dataList) throws ParseException {
        TableView<NormalizedRecord> newTableView = new TableView<>();
        newTableView.getItems().clear();

        Map<String, String> columnLabels = new HashMap<>();
        columnLabels.put("Radius", "ID");
        columnLabels.put("texture", "Clump Thickness");
        columnLabels.put("perimeter", "Uniformity of Cell Size");
        columnLabels.put("area", "Uniformity of Cell Shape");
        columnLabels.put("smoothness", "Marginal Adhesion");
        columnLabels.put("compactness", "Single Epithelial Cell Size");
        columnLabels.put("concavity", "Bare Nuclei");
        columnLabels.put("concavePoints", "Bland Chromatin");
        columnLabels.put("symmetry", "Normal Nucleoli");
        columnLabels.put("fractalDimension", "Mitoses");
        columnLabels.put("Decision", "Decision");

        TableColumn<NormalizedRecord, Integer> radiusColumn = new TableColumn<>("ID");
        radiusColumn.setCellValueFactory(new PropertyValueFactory<>("Radius"));
        TableColumn<NormalizedRecord, Integer> decisionColumn = new TableColumn<>("Decision");
        decisionColumn.setCellValueFactory(new PropertyValueFactory<>("Decision"));
        newTableView.getColumns().add(radiusColumn);
        List<String> attributeNames = Arrays.asList("texture", "perimeter", "area", "smoothness", "compactness", "concavity", "concavePoints", "symmetry", "fractalDimension");
        for (String attributeName : attributeNames) {
            TableColumn<NormalizedRecord, String> column = new TableColumn<>(columnLabels.get(attributeName));
            column.setCellValueFactory(cellData -> {
                NormalizedRecord record = cellData.getValue();
                // Pobranie odpowiedniego atrybutu znormalizowanego na podstawie nazwy kolumny
                Interval attributeValues = switch (attributeName) {
                    case "texture" -> record.getNormalizedTexture();
                    case "perimeter" -> record.getNormalizedPerimeter();
                    case "area" -> record.getNormalizedArea();
                    case "smoothness" -> record.getNormalizedSmoothness();
                    case "compactness" -> record.getNormalizedCompactness();
                    case "concavity" -> record.getNormalizedConcavity();
                    case "concavePoints" -> record.getNormalizedConcavePoints();
                    case "symmetry" -> record.getNormalizedSymmetry();
                    case "fractalDimension" -> record.getNormalizedFractalDimension();
                    default -> null;
                };
                // Konwersja tablicy wartości na ciąg znaków do wyświetlenia w komórce
                if (attributeValues != null) {
                    return new SimpleStringProperty("[" + attributeValues.getLower() + ", " + attributeValues.getUpper() + "]");
                } else {
                    return new SimpleStringProperty("null");
                }
            });
            column.setCellFactory(new Callback<TableColumn<NormalizedRecord, String>, TableCell<NormalizedRecord, String>>() {
                @Override
                public TableCell<NormalizedRecord, String> call(TableColumn<NormalizedRecord, String> param) {
                    return new TableCell<NormalizedRecord, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item == null || empty) {
                                setText(null);
                                setStyle("");
                            } else {
                                setText(item);
                                if ("null".equals(item)) {
                                    setStyle("-fx-background-color: #BFBFBF; -fx-text-fill: FF0000;");
                                } else {
                                    setStyle("");
                                }
                            }
                        }
                    };
                }
            });

            newTableView.getColumns().add(column);
        }

        newTableView.setItems(FXCollections.observableArrayList(normalizeDataList));
        newTableView.getColumns().add(decisionColumn);
        List<NormalizedRecord> normalizedDataList = NormalizedIntervals.getNormalizedIntervalsList();

        for (int i = 0; i < normalizedDataList.size(); i++) {
            Record originalRecord = dataList.get(i);
            NormalizedRecord normalizedRecord = normalizedDataList.get(i);

                normalizedRecord.setRadius(originalRecord.getRadius());
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