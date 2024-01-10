package com.example.hcvfuzzy;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class loadDataBase extends TableView<String> {

    private final ObservableList<Record> dataList = FXCollections.observableArrayList();
    private TableView<Record> tableView;

    public TableView<Record> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<Record> tableView) {
        this.tableView = tableView;
    }

    public TableView<Record> readCSV(File file, String delimiter) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
        String[] firstRow = lines.get(0).split(delimiter);
        tableView = new TableView<>();

        setTableView(tableView);

        for (String columnName : firstRow) {
            TableColumn<Record, String> column = new TableColumn<>(columnName);
            tableView.getColumns().add(column);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] cells = lines.get(i).split(delimiter);
            Record record = new Record(
                    cells[0],
                    cells[1],
                    cells[2],
                    cells[3],
                    cells[4],
                    cells[5]
            );
            dataList.add(record);
        }
        tableView.setItems(dataList);
        return tableView;

    }
}
