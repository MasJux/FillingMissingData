package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;
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
    @FXML
    private Slider rowSlider, cellSlider;
    @FXML
    private Label rowLabel, cellLabel;
    @FXML
    private Button deleteButton;
    private TableView<NormalizedRecord> newTableView;
    private List<NormalizedRecord> updatedRecords = new ArrayList<>();

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
    public void setTableView(TableView<NormalizedRecord> newTableView) {
        this.newTableView = newTableView;
    }
    @FXML
    private void deleteRowsAndCells() {
        int rowCount = newTableView.getItems().size();
        int rowIndex;
        int columnIndex;
        deletionFlag = true;
        //TODO ustawić unikalne randomy
        Random random = new Random();
        ArrayList<Integer> listOfRows = new ArrayList<>();

        for (int i = 0; i < amountRows; i++) {
            do {
                rowIndex = random.nextInt(rowCount);
            } while (listOfRows.contains(rowIndex)); // Sprawdź, czy rowIndex już istnieje w liście
            listOfRows.add(rowIndex);
        }
        for (int row : listOfRows) {
            NormalizedRecord record = newTableView.getItems().get(row);
            Set<Integer> usedColumnIndexes = new HashSet<>();
            for (int n = 0; n < amountCells; n++) {
                do {
                    columnIndex = random.nextInt(10) + 1;
                } while (usedColumnIndexes.contains(columnIndex));

                usedColumnIndexes.add(columnIndex);
                int newValue = -1;
                switch (columnIndex) {
                    case 1 -> record.setNormalizedRadius(newValue);
                    case 2 -> record.setNormalizedTexture(newValue);
                    case 3 -> record.setNormalizedPerimeter(newValue);
                    case 4 -> record.setNormalizedArea(newValue);
                    case 5 -> record.setNormalizedSmoothness(newValue);
                    case 6 -> record.setNormalizedCompactness(newValue);
                    case 7 -> record.setNormalizedConcavity(newValue);
                    case 8 -> record.setNormalizedConcavePoints(newValue);
                    case 9 -> record.setNormalizedSymmetry(newValue);
                    case 10 -> record.setNormalizedFractalDimension(newValue);
                }
                newTableView.refresh();
            }

        }
        updateRecords();
        deleteButton.setDisable(isDeletionPerformed());
        if(amountRows == 0 || amountCells==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill in the number of rows and cells to delete.");
            alert.showAndWait();
            deleteButton.setDisable(false);
        }
    }
    private void updateRecords() {
        for (NormalizedRecord record : newTableView.getItems()) {
            NormalizedRecord recordAfterDeleting = new NormalizedRecord();
            recordAfterDeleting.setID(record.getID());
            recordAfterDeleting.setNormalizedRadius(record.getNormalizedRadius());
            recordAfterDeleting.setNormalizedTexture(record.getNormalizedTexture());
            recordAfterDeleting.setNormalizedPerimeter(record.getNormalizedPerimeter());
            recordAfterDeleting.setNormalizedArea(record.getNormalizedArea());
            recordAfterDeleting.setNormalizedSmoothness(record.getNormalizedSmoothness());
            recordAfterDeleting.setNormalizedCompactness(record.getNormalizedCompactness());
            recordAfterDeleting.setNormalizedConcavity(record.getNormalizedConcavity());
            recordAfterDeleting.setNormalizedConcavePoints(record.getNormalizedConcavePoints());
            recordAfterDeleting.setNormalizedSymmetry(record.getNormalizedSymmetry());
            recordAfterDeleting.setNormalizedFractalDimension(record.getNormalizedFractalDimension());
            recordAfterDeleting.setDecision(record.getDecision());

            updatedRecords.add(recordAfterDeleting);
        }
    }
//Lista rekordów po usunięciu niektórych atrybutów
    public List<NormalizedRecord> getUpdatedRecords() {
        return updatedRecords;
    }
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
