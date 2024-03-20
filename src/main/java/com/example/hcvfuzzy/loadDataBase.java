package com.example.hcvfuzzy;

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
    private int parseCell(String cell) {
        if (cell.equals("?")) {
            return 0; // Zamień znak "?" na 0
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

        TableColumn<Record, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tableView.getColumns().add(idColumn);

        //ustawienie nazwy kolumny z pierwszego wiersza
        for (String columnName : firstRow) {
            TableColumn<Record, String> column = new TableColumn<>(columnName);
            tableView.getColumns().add(column);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] cells = lines.get(i).split(delimiter);
            Record record = new Record(
                    i,
                    parseCell(cells[0]),
                    parseCell(cells[1]),
                    parseCell(cells[2]),
                    parseCell(cells[3]),
                    parseCell(cells[4]),
                    parseCell(cells[5]),
                    parseCell(cells[6]),
                    parseCell(cells[7]),
                    parseCell(cells[8]),
                    parseCell(cells[9]),
                    parseCell(cells[10])
            );
            record.getRadius();
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
