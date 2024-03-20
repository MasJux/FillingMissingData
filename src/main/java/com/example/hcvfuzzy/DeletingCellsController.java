package com.example.hcvfuzzy;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class DeletingCellsController implements Initializable {

    int amountRows;
    int amountCells;
    @FXML
    private Slider rowSlider, cellSlider;
    @FXML
    private Label rowLabel, cellLabel;
    @FXML
    private Button deleteButton;
    private TableView<Record> tableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rowSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                amountRows = (int) rowSlider.getValue();
                rowLabel.setText(Integer.toString(amountRows));
            }
        });
        cellSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                amountCells = (int) cellSlider.getValue();
                cellLabel.setText(Integer.toString(amountCells));
            }
        });
    }

    public void setTableView(TableView<Record> tableView) {
        this.tableView = tableView;
    }

    @FXML
    private void deleteRowsAndCells() {
        int rowCount = tableView.getItems().size();
        int rowIndex;
        int columnIndex;
        Random random = new Random();
        ArrayList<Integer> listOfRows = new ArrayList<>();

        for (int i = 0; i < amountRows; i++) {
            rowIndex = random.nextInt(rowCount);
            listOfRows.add(rowIndex);
        }
        for (int row : listOfRows) {
            Record record = tableView.getItems().get(row);
            Set<Integer> usedColumnIndexes = new HashSet<>();
            for (int n = 0; n < amountCells; n++) {
                do {
                    columnIndex = random.nextInt(10) + 1;
                } while (usedColumnIndexes.contains(columnIndex));

                usedColumnIndexes.add(columnIndex);
                int newValue = 0;
                switch (columnIndex) {
                    case 1 -> record.setRadius(newValue);
                    case 2 -> record.setTexture(newValue);
                    case 3 -> record.setPerimeter(newValue);
                    case 4 -> record.setArea(newValue);
                    case 5 -> record.setSmoothness(newValue);
                    case 6 -> record.setCompactness(newValue);
                    case 7 -> record.setConcavity(newValue);
                    case 8 -> record.setConcavePoints(newValue);
                    case 9 -> record.setSymmetry(newValue);
                    case 10 -> record.setFractalDimension(newValue);
                }
                tableView.refresh();
            }
        }
        deleteButton.setDisable(true);
        if(amountRows == 0 || amountCells==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill in the number of rows and cells to delete.");
            alert.showAndWait();
            deleteButton.setDisable(false);
        }

//        System.out.println(rowCount+": Row Count");
//        System.out.println(rowIndex+1+": Row Index");
//        System.out.println(columnIndex+": Column Index");
//        System.out.println(columnName+" - "+value);
    }
}
