package com.example.hcvfuzzy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigationController implements Initializable {
    EntrophyMethods entrophyMethods = new EntrophyMethods();
    loadDataBase dataBase = new loadDataBase();
    TableView<Record> tableView;
    File file = new File("src/main/resources/database/breastCancer.csv");
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    private Record selectedRecord;
    private int id;
    private int radius, texture, perimeter, area, smoothness, compactness, concavity, concavePoints, symmetry, fractalDimension;

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

    @FXML
    private void home(MouseEvent event) {
        borderPane.setCenter(anchorPane);
    }
    @FXML
    private void deletingPane(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deleting-pane.fxml"));
            Parent root = loader.load();
            DeletingCellsController controller = loader.getController();
            controller.setTableView(tableView);
            borderPane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPane(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        borderPane.setCenter(root);
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
    private void editRow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editing-pane.fxml"));
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
        entrophyMethods.resetCounts();
        entrophyMethods.resetSum();
    }
    @FXML
    private void restoreData(){
        List<Record> publicDataList = dataBase.getPublicDataList();

        entrophyMethods.secondMethod(publicDataList);
        entrophyMethods.resetCounts();
        entrophyMethods.resetSum();
        tableView.refresh();

        //entrophyMethods.getMinMaxValues(publicDataList);
        //entrophyMethods.euclidesDistance(publicDataList);
    }
}

