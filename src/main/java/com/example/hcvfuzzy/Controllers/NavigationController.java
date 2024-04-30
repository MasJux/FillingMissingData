package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Constructors.NormalizedRecord;
import com.example.hcvfuzzy.Constructors.Record;
import com.example.hcvfuzzy.Database.loadDataBase;
import com.example.hcvfuzzy.FillingMethods.EntropyMethod;
import com.example.hcvfuzzy.FillingMethods.Normalization;
import com.example.hcvfuzzy.FillingMethods.SecondMethod;
import com.example.hcvfuzzy.FillingMethods.ThirdMethod;
import com.example.hcvfuzzy.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
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
    Button test;
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
    SecondMethod secondMethod = new SecondMethod();
    ThirdMethod thirdMethod = new ThirdMethod();
    loadDataBase dataBase = new loadDataBase();
    DeletingCellsController buttonsController = new DeletingCellsController();
    private DeletingCellsController deletingCellsController;
    private EntropyMethod entropyMethod = new EntropyMethod();
    TableView<Record> tableView;
    TableView<NormalizedRecord> newTableView;
    List<Record> publicDataList = dataBase.getPublicDataList();
    private final ObservableList<NormalizedRecord> updateDataInTableView = FXCollections.observableArrayList();
    private Record selectedRecord;
    private int id;
    private int radius, texture, perimeter, area, smoothness, compactness, concavity, concavePoints, symmetry, fractalDimension;
    private Parent deletingPaneRoot;
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
    private void getElementsFromNormalizedRecordAndSetInTableView(List<NormalizedRecord> listAfterDeleting){
        for (int i = 0; i < listAfterDeleting.size(); i++) {
            NormalizedRecord normalizedRecord = listAfterDeleting.get(i);
            NormalizedRecord updateNormalizedRecord = new NormalizedRecord();

            updateNormalizedRecord.setID(normalizedRecord.getID());
            updateNormalizedRecord.setNormalizedRadius(normalizedRecord.getNormalizedRadius());
            updateNormalizedRecord.setNormalizedTexture(normalizedRecord.getNormalizedTexture());
            updateNormalizedRecord.setNormalizedPerimeter(normalizedRecord.getNormalizedPerimeter());
            updateNormalizedRecord.setNormalizedArea(normalizedRecord.getNormalizedArea());
            updateNormalizedRecord.setNormalizedSmoothness(normalizedRecord.getNormalizedSmoothness());
            updateNormalizedRecord.setNormalizedCompactness(normalizedRecord.getNormalizedCompactness());
            updateNormalizedRecord.setNormalizedConcavity(normalizedRecord.getNormalizedConcavity());
            updateNormalizedRecord.setNormalizedConcavePoints(normalizedRecord.getNormalizedConcavePoints());
            updateNormalizedRecord.setNormalizedSymmetry(normalizedRecord.getNormalizedSymmetry());
            updateNormalizedRecord.setNormalizedFractalDimension(normalizedRecord.getNormalizedFractalDimension());
            updateNormalizedRecord.setDecision(normalizedRecord.getDecision());

            updateDataInTableView.add(updateNormalizedRecord);
        }
        newTableView.setItems(updateDataInTableView);
        newTableView.refresh();
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
                deletingCellsController.setMethodButton(secondMethodButton, thirdMethodButton);
                deletingCellsController.setTableView(newTableView);
                borderPane.setCenter(deletingPaneRoot);
            }else{
                borderPane.setCenter(deletingPaneRoot);
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
        }
    }
    @FXML
    private void restoreSecondMethod(){
        //List<Record> publicDataList = dataBase.getPublicDataList();
        List<NormalizedRecord> listAfterDeleting = deletingCellsController.getUpdatedRecords();
        secondMethod.secondFillingMethod(listAfterDeleting);
        getElementsFromNormalizedRecordAndSetInTableView(listAfterDeleting);
        secondMethod.resetCounts();
        secondMethod.resetSum();
    }
    @FXML
    private void restoreThirdMethod(){
        //List<Record> publicDataList = dataBase.getPublicDataList();
        List<NormalizedRecord> listAfterDeleting = deletingCellsController.getUpdatedRecords();
        thirdMethod.thirdFillingMethod(listAfterDeleting);
        getElementsFromNormalizedRecordAndSetInTableView(listAfterDeleting);
        thirdMethod.resetSumThirdMethod();
        thirdMethod.resetCountsThirdMethod();
    }
    @FXML
    private void normalizeData() throws ParseException {

        if (!isDataNormalized) {
            normalization.normalizeData(publicDataList);
            isDataNormalized = true;
            test.setDisable(false);
            deletingPanelButton.setDisable(false);
            showInitialDataButton.setDisable(false);
            normalizeButton.setDisable(true);
            System.out.println("wykonanie normalizeData()");
        }
        if(isDataNormalized && !isNormalizedTableViewExist){
            newTableView = normalization.updateTableViewWithNormalizedData(publicDataList);
            anchorPane.getChildren().remove(tableView);
            anchorPane.getChildren().add(newTableView);
            isNormalizedTableViewExist = true;
            System.out.println("dodano newTableView");
            }else{
                System.out.println("newTableView juz istnieje");
            }
        }
    @FXML
    private void testButton(){
        List<NormalizedRecord> listAfterDeleting = deletingCellsController.getUpdatedRecords();
//        for(NormalizedRecord rec: listAfterDeleting){
//            System.out.println(rec.getID()+" - "+ rec.getNormalizedRadius());
//        }
        entropyMethod.completeMissingValue(listAfterDeleting);
    }
    @FXML
    private void showInitialData(){
        System.out.println(normalization.defaultPublicNormalizedDataList);
    }
    /**
     * ustawienie metod uzupełniania pod znormalizowane dane (done)
     * ew. dać opcje dla normalnych i znormalizowanych
     * działanie resetowania
     * najprawdopodobniej usunięcie edytowania
     * ustawienie unikalnych randomów dla usuwania
     * metoda wyliczania entropii dla usuwania (artykuł)
     * druga metoda entropii
     * podłączenie 3 Pane'a pod wykresy (kolumny, liniowe)
     * ew. analiza odchylenia standardowego 
     */
}


