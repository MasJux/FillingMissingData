package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.AlgorithmkNN.Metrics;
import com.example.hcvfuzzy.ExportTableView;
import com.example.hcvfuzzy.Holders.*;
import com.example.hcvfuzzy.Objects.Interval;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Objects.Record;
import com.example.hcvfuzzy.Database.loadDataBase;
import com.example.hcvfuzzy.FillingMethods.EntropyMethod;
import com.example.hcvfuzzy.FillingMethods.Normalization;
//import com.example.hcvfuzzy.FillingMethods.SecondMethod;
//import com.example.hcvfuzzy.FillingMethods.ThirdMethod;
import com.example.hcvfuzzy.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigationController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    Button efficiencyButton;
    @FXML
    Button deletingPanelButton;
    @FXML
    Button normalizeButton;
    @FXML
    Button showInitialDataButton;
    @FXML
    Button secondMethodButton;
    @FXML
    Button thirdMethodButton;
    File file = new File("src/main/resources/database/breastCancer.csv");
    boolean isDataNormalized = false;
    boolean isNormalizedTableViewExist = false;
    Normalization normalization = new Normalization();
    loadDataBase dataBase = new loadDataBase();
    private DeletingCellsController deletingCellsController;
    private TestingController testingController;
    TableView<Record> tableView;
    TableView<NormalizedRecord> newTableView;
    private Record selectedRecord;
    private int id;
    private int radius, texture, perimeter, area, smoothness, compactness, concavity, concavePoints, symmetry, fractalDimension;
    private Parent deletingPaneRoot;
    private Parent testingPane;
    @FXML
    private ChoiceBox<String> missingValuesDistanceType;
    @FXML
    private ChoiceBox<String> classificationDistanceType;
    @FXML
    private ChoiceBox<Integer> choiceNeighbors;
    public NavigationController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        tableView = dataBase.getTableView();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    handleSelection(newValue);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Inicjalizacja ChoiceBox i dodanie opcji
        missingValuesDistanceType.getItems().addAll("euclidean", "hamming");
        missingValuesDistanceType.setValue("euclidean");

        classificationDistanceType.getItems().addAll("euclidean", "hamming");
        classificationDistanceType.setValue("euclidean");

        choiceNeighbors.getItems().addAll(5,7,9,11,13,15);
        choiceNeighbors.setValue(5);
    }

    private void loadData() {
        try {
            TableView<Record> createdTableView = dataBase.readCSV(file, ",");
            anchorPane.getChildren().add(createdTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleSelection(Record selectedRecord) throws IOException {
        this.selectedRecord = selectedRecord;
        id = selectedRecord.getID();
        radius = selectedRecord.getRadius();
        texture = selectedRecord.getTexture();
        perimeter = selectedRecord.getPerimeter();
        area = selectedRecord.getArea();
        smoothness = selectedRecord.getSmoothness();
        compactness = selectedRecord.getCompactness();
        concavity = selectedRecord.getConcavity();
        concavePoints = selectedRecord.getConcavePoints();
        symmetry = selectedRecord.getSymmetry();
        fractalDimension = selectedRecord.getFractalDimension();

        System.out.println("ID: " + id);
        System.out.println("Radius: " + radius);
        System.out.println("Texture: " + texture);
        System.out.println("Perimeter: " + perimeter);
        System.out.println("area: " + area);
        System.out.println("smooth: " + smoothness);
        System.out.println("compactness: " + compactness);
        System.out.println("concavity: " + concavity);
        System.out.println("concave Points: " + concavePoints);
        System.out.println("symmetry: " + symmetry);
        System.out.println("fractal: " + fractalDimension);
    }
    @FXML
    private void home(MouseEvent event) {
        borderPane.setCenter(anchorPane);
    }
    @FXML
    private void deletingPane(MouseEvent event) {
        try {
            if(deletingCellsController == null){
                FXMLLoader loader = new FXMLLoader(main.class.getResource("deleting-pane.fxml"));
                deletingPaneRoot = loader.load();
                deletingCellsController = loader.getController();
                deletingCellsController.setTableView(tableView);
                borderPane.setCenter(deletingPaneRoot);
                deletingCellsController.setNormalizeButton(normalizeButton);
            }else{
                borderPane.setCenter(deletingPaneRoot);
            }
        } catch (IOException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void testingPane(MouseEvent event) throws IOException {
        try {
            if(testingController == null){
                FXMLLoader loader = new FXMLLoader(main.class.getResource("testing-pane.fxml"));
                testingPane = loader.load();
                testingController = loader.getController();
                borderPane.setCenter(testingPane);
            }else{
                borderPane.setCenter(testingPane);
            }
        } catch (IOException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void editRow() throws IOException {
        FXMLLoader loader = new FXMLLoader(main.class.getResource("editing-pane.fxml"));
        Parent root = loader.load();
        EditWindowController controller = loader.getController();
        controller.initData(selectedRecord);
        controller.setAnchorPane(anchorPane);
        controller.setTableView(tableView);
        anchorPane.setDisable(true);
        Stage stage = new Stage();
        controller.setStage(stage);
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.show();
    }
    @FXML
    private void resetTableView() {
        tableView.getItems().clear();
        newTableView.getItems().clear();
        newTableView.refresh();
        tableView.refresh();
        DataAfterDeleting.clear();
        DataBeforeDeleting.clear();
        NormalizedIntervals.clear();
        isNormalizedTableViewExist = false;
        isDataNormalized = false;
        loadData();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    handleSelection(newValue);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        if(deletingCellsController != null) {
            deletingCellsController.setDeletionPerformed(false);
            Button deleteButton = deletingCellsController.getDeleteButton();
            deleteButton.setDisable(false);
            efficiencyButton.setDisable(true);
            newTableView.getColumns().clear();
            normalizeButton.setDisable(true);
            if(isNormalizedTableViewExist){
                borderPane.getChildren().remove(newTableView);
                isNormalizedTableViewExist = false;
            }
        }
    }

    @FXML
    private void normalizeData() throws ParseException {

        List<Record> dataAfterDeleting = DataAfterDeleting.getListWithMissingValues();
        if (!isDataNormalized) {
            normalization.normalizeData(dataAfterDeleting);
            isDataNormalized = true;
            efficiencyButton.setDisable(false);
            deletingPanelButton.setDisable(false);
//            showInitialDataButton.setDisable(false);
            normalizeButton.setDisable(true);
            efficiencyButton.setDisable(false);
            System.out.println("wykonanie normalizeData()");
        }
        if(isDataNormalized && !isNormalizedTableViewExist){
            newTableView = normalization.updateTableViewWithNormalizedData(dataAfterDeleting);
            anchorPane.getChildren().remove(tableView);
            anchorPane.getChildren().add(newTableView);
            isNormalizedTableViewExist = true;
            System.out.println("dodano newTableView");
            newTableView.refresh();

            }else{
                System.out.println("newTableView juz istnieje");
            }
        }
    @FXML
    private void calculateEfficiency(){
        Metrics metrics = new Metrics();
        List<NormalizedRecord> normalizedIntervalsList = NormalizedIntervals.getNormalizedIntervalsList();
        String completingValuesDistanceType = missingValuesDistanceType.getValue();
        String classificateDistanceType = classificationDistanceType.getValue();
        Integer choiceCountOfNeighbors = choiceNeighbors.getValue();
        metrics.evaluateKNNWithEntropy(normalizedIntervalsList,completingValuesDistanceType, classificateDistanceType, choiceCountOfNeighbors);

    }
    @FXML
    private void showInitialData(){
        List<NormalizedRecord> normalizedDataList = NormalizedIntervals.getNormalizedIntervalsList();
        System.out.println(normalizedDataList);
        for(int i = 0; i<699;i++){
            NormalizedRecord nr = normalizedDataList.get(i);

            System.out.println(nr.getNormalizedTexture());
        }
    }
}


