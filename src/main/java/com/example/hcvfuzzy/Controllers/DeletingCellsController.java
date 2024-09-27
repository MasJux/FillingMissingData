package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Holders.DataAfterDeleting;
import com.example.hcvfuzzy.Holders.DataBeforeDeleting;
import com.example.hcvfuzzy.Objects.Record;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class DeletingCellsController implements Initializable {
    private boolean deletionFlag = false;
    int amountRows;
    int amountCells;
    private Button normalizeButton;
    @FXML
    private Label infoLabel;
    @FXML
    private Button deleteButton;
    private TableView<Record> tableView;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public void setNormalizeButton(Button normalizeButton) {
        this.normalizeButton = normalizeButton;
    }

    public void setTableView(TableView<Record> tableView) {
        this.tableView = tableView;
    }
    @FXML
    private void deleteRowsAndCells() {
        // Utwórz listę opcji procentowych
        List<String> choices = FXCollections.observableArrayList("5%", "25%", "50%");

        // Utwórz okno dialogowe z wyboru opcji
        ChoiceDialog<String> dialog = new ChoiceDialog<>("5%", choices);
        dialog.setTitle("Wybierz procent danych do usunięcia");
        dialog.setHeaderText(null);
        dialog.setContentText("Wybierz procent:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            double percentage = 0;
            switch (choice) {
                case "5%":
                    percentage = 0.05;
                    infoLabel.setText("You have removed 5% of attributes");
                    break;
                case "25%":
                    percentage = 0.25;
                    infoLabel.setText("You have removed 25% of attributes");
                    break;
                case "50%":
                    percentage = 0.50;
                    infoLabel.setText("You have removed 50% of attributes");
                    break;
            }

            // Oblicz ilość wierszy do usunięcia na podstawie procentu
            int rowCount = tableView.getItems().size();
            amountRows = (int) (rowCount * percentage);
            amountCells = 1;
            // Przejdź do usuwania wierszy i komórek
            executeDeletion();
        }
    }
    private void executeDeletion() {
        int rowCount = tableView.getItems().size();
        int rowIndex;
        int columnIndex;
        deletionFlag = true;
        Random random = new Random();
        ArrayList<Integer> listOfRows = new ArrayList<>();

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


        if(amountRows == 0 || amountCells==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill in the number of rows and cells to delete.");
            alert.showAndWait();
            deleteButton.setDisable(false);
        }
        if (normalizeButton != null) {
            normalizeButton.setDisable(false);
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
