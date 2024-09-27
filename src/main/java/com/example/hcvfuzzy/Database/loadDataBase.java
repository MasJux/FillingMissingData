package com.example.hcvfuzzy.Database;

import com.example.hcvfuzzy.Objects.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class loadDataBase extends TableView<String> {
    public List<Record> publicDataList = new ArrayList<>();
    private final ObservableList<Record> dataList = FXCollections.observableArrayList();
    private TableView<Record> tableView;

    public TableView<Record> getTableView() {
        return tableView;
    }
    public void setTableView(TableView<Record> tableView) {
        this.tableView = tableView;
    }
    public static double calculateMean(List<String[]> data, int columnIndex) {
        double sum = 0;
        int count = 0;
        for (String[] row : data) {
            if (!row[columnIndex].equals("?")) {
                sum += Double.parseDouble(row[columnIndex]);
                count++;
            }
        }
        return sum / count;
    }
    private int parseCellOrMean(String cell, double mean) {
        if (cell.equals("?")) {
            return (int) mean; // Zastąp brakującą wartość średnią z kolumny
        } else {
            return Integer.parseInt(cell); // Konwertuj tekst na int
        }
    }

    public List<Record> getPublicDataList() {
        return publicDataList;
    }
    public TableView<Record> readCSV(File file, String delimiter) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
        String[] firstRow = lines.get(0).split(delimiter);
        tableView = new TableView<>();
        publicDataList.clear();

        setTableView(tableView);

        String[] columnLabels = {"ID", "Clump Thickness", "Uniformity of Cell Size", "Uniformity of Cell Shape",
                                "Marginal Adhesion", "Single Epithelial Cell Size", "Bare Nuclei", "Bland Chromatin",
                                "Normal Nucleoli", "Mitoses", "Decision"};

        //ustawienie nazwy kolumny z pierwszego wiersza
        for (int i = 0; i < firstRow.length; i++) {
            String columnName = firstRow[i];
            TableColumn<Record, String> column = new TableColumn<>(columnLabels[i]);
            tableView.getColumns().add(column);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
        }
        List<String[]> rawData = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] cells = lines.get(i).split(delimiter);
            rawData.add(cells);
        }

        int numberOfColumns = firstRow.length;
        double[] columnMeans = new double[numberOfColumns];
        for (int col = 0; col < numberOfColumns; col++) {
            columnMeans[col] = calculateMean(rawData, col);
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] cells = lines.get(i).split(delimiter);
            Record record = new Record(
                    parseCellOrMean(cells[0], columnMeans[0]),
                    parseCellOrMean(cells[1], columnMeans[1]),
                    parseCellOrMean(cells[2], columnMeans[2]),
                    parseCellOrMean(cells[3], columnMeans[3]),
                    parseCellOrMean(cells[4], columnMeans[4]),
                    parseCellOrMean(cells[5], columnMeans[5]),
                    parseCellOrMean(cells[6], columnMeans[6]),
                    parseCellOrMean(cells[7], columnMeans[7]),
                    parseCellOrMean(cells[8], columnMeans[8]),
                    parseCellOrMean(cells[9], columnMeans[9]),
                    parseCellOrMean(cells[10], columnMeans[10])
            );
            record.getTexture();
            record.getPerimeter();
            record.getArea();
            record.getSmoothness();
            record.getCompactness();
            record.getConcavity();
            record.getConcavePoints();
            record.getSymmetry();
            record.getFractalDimension();

            dataList.add(record);
            publicDataList.add(record);
        }
        tableView.setItems(dataList);
        return tableView;
    }
}
