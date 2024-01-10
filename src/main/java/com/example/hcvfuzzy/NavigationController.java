package com.example.hcvfuzzy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigationController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    loadDataBase dataBase = new loadDataBase();

    File file = new File("src/main/resources/mammographic.csv");



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        TableView<Record> tableView = dataBase.getTableView();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleSelection(newValue);
            }
        });
    }
    @FXML
    private void home(MouseEvent event){
        borderPane.setCenter(anchorPane);
    }
    @FXML
    private void page1(MouseEvent event){
        loadPane("page1");
    }
    private void loadPane(String page){
        Parent root = null;

        try{
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        }catch(IOException ex){
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE,null,ex);
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
    private void handleSelection(Record selectedRecord) {
        // Obsługa zdarzenia zaznaczenia w TableView
        String birads = selectedRecord.getBIRADSassessment();
        String age = selectedRecord.getAge();
        // ... itd.
        System.out.println("BIRADS: " + birads);
        System.out.println("Age: " + age);
        // ... itd.
    }
}

