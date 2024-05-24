package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Holders.DataAfterDeleting;
import com.example.hcvfuzzy.Holders.DataBeforeDeleting;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Holders.NormalizedIntervalDataHolder;
import com.example.hcvfuzzy.Objects.Record;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class DeletingCellsController implements Initializable {
    private boolean deletionFlag = false;
    int amountRows;
    int amountCells;
    private Button secondMethodButton, thirdMethodButton;
    @FXML
    private Slider rowSlider, cellSlider;
    @FXML
    private Label rowLabel, cellLabel;
    @FXML
    private Button deleteButton;
    private TableView<NormalizedRecord> newTableView;
    private TableView<Record> tableView;
    NormalizedIntervalDataHolder normalizedIntervalDataHolder;

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
    public void setMethodButton(Button secondMethodButton, Button thirdMethodButton) {
        this.secondMethodButton = secondMethodButton;
        this.thirdMethodButton = thirdMethodButton;
    }
    public void setTableView(TableView<Record> tableView) {
        this.tableView = tableView;
    }
    @FXML
    private void deleteRowsAndCells() {
        int rowCount = tableView.getItems().size();
        int rowIndex;
        int columnIndex;
        deletionFlag = true;
        Random random = new Random();
        ArrayList<Integer> listOfRows = new ArrayList<>();

        // Copy records to DataBeforeDeleting
        copyDataBeforeDeleting();

        for (int i = 0; i < amountRows; i++) {
            do {
                rowIndex = random.nextInt(rowCount);
            } while (listOfRows.contains(rowIndex)); // Sprawdź, czy rowIndex już istnieje w liście
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

                int newValue = -1;
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
        updateRecords();
        deleteButton.setDisable(isDeletionPerformed());
        secondMethodButton.setDisable(false);
        thirdMethodButton.setDisable(false);
        if(amountRows == 0 || amountCells==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill in the number of rows and cells to delete.");
            alert.showAndWait();
            deleteButton.setDisable(false);
        }
    }

    private void copyDataBeforeDeleting() {
        for (Record rec : tableView.getItems()) {
            DataBeforeDeleting.addRecord(rec.copy());
        }
    }
    private void updateRecords() {
        for (Record record : tableView.getItems()) {
            Record recordAfterDeleting = new Record();
            recordAfterDeleting.setID(record.getID());
            recordAfterDeleting.setRadius(record.getRadius());
            recordAfterDeleting.setTexture(record.getTexture());
            recordAfterDeleting.setPerimeter(record.getPerimeter());
            recordAfterDeleting.setArea(record.getArea());
            recordAfterDeleting.setSmoothness(record.getSmoothness());
            recordAfterDeleting.setCompactness(record.getCompactness());
            recordAfterDeleting.setConcavity(record.getConcavity());
            recordAfterDeleting.setConcavePoints(record.getConcavePoints());
            recordAfterDeleting.setSymmetry(record.getSymmetry());
            recordAfterDeleting.setFractalDimension(record.getFractalDimension());
            recordAfterDeleting.setDecision(record.getDecision());

        }
        for (Record afterDeletingRecord : tableView.getItems()) {
            DataAfterDeleting.addDeletedRecord(afterDeletingRecord);
        }
    }
//        List<Record> dataAfterDeleting = DataAfterDeleting.getListWithMissingValues();
//        for(Record rec: dataAfterDeleting){
//            System.out.println(rec.getID());
//            System.out.println(rec.getRadius());
//        }
//        System.out.println(dataAfterDeleting.size()+" size()");


//Blokowanie DeleteButton
    public boolean isDeletionPerformed() {
        return deletionFlag;
    }
    public void setDeletionPerformed(boolean deletionFlag) {
        this.deletionFlag = deletionFlag;
    }
    public Button getDeleteButton() {
        return deleteButton;
    }

}
