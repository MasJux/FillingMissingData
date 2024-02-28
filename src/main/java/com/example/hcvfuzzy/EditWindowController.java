package com.example.hcvfuzzy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditWindowController implements Initializable {
    private Record selectedRecord;
    private AnchorPane anchorPane;
    private TableView<Record> tableView;
    private Stage stage;

    @FXML
    TextField RadiusField, TextureField, PerimeterField, AreaField, SmoothnessField;
    @FXML
    TextField CompactnessField, ConcavityField, ConcavePointsField, SymmetryField, FractalDimensionField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void initData(Record selectedRecord) {
        this.selectedRecord = selectedRecord;
        RadiusField.setText(selectedRecord.getRadius());
        TextureField.setText(selectedRecord.getTexture());
        PerimeterField.setText(selectedRecord.getPerimeter());
        AreaField.setText(selectedRecord.getArea());
        SmoothnessField.setText(selectedRecord.getSmoothness());
        CompactnessField.setText(selectedRecord.getCompactness());
        ConcavityField.setText(selectedRecord.getConcavity());
        ConcavePointsField.setText(selectedRecord.getConcavePoints());
        SymmetryField.setText(selectedRecord.getSymmetry());
        FractalDimensionField.setText(selectedRecord.getFractalDimension());
    }
    public void setAnchorPane(AnchorPane anchorPane) {
    this.anchorPane = anchorPane;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(windowEvent -> {
            anchorPane.setDisable(false);
        });
    }
    public void setTableView(TableView<Record> tableView) {
        this.tableView = tableView;
    }
    @FXML
    private void updateRow(){
        anchorPane.setDisable(false);
        int newRadius = Integer.parseInt(RadiusField.getText());
        int newTextureField = Integer.parseInt(TextureField.getText());
        int newPerimeter = Integer.parseInt(PerimeterField.getText());
        int newArea = Integer.parseInt(AreaField.getText());
        int newSmoothness = Integer.parseInt(SmoothnessField.getText());
        int newCompactness = Integer.parseInt(CompactnessField.getText());
        int newConcavity = Integer.parseInt(ConcavityField.getText());
        int newConcavePoints = Integer.parseInt(ConcavePointsField.getText());
        int newSymmetry = Integer.parseInt(SymmetryField.getText());
        int newFractalDimension = Integer.parseInt(FractalDimensionField.getText());
        selectedRecord.setRadius(String.valueOf(newRadius));
        selectedRecord.setTexture(String.valueOf(newTextureField));
        selectedRecord.setPerimeter(String.valueOf(newPerimeter));
        selectedRecord.setArea(String.valueOf(newArea));
        selectedRecord.setSmoothness(String.valueOf(newSmoothness));
        selectedRecord.setCompactness(String.valueOf(newCompactness));
        selectedRecord.setConcavity(String.valueOf(newConcavity));
        selectedRecord.setConcavePoints(String.valueOf(newConcavePoints));
        selectedRecord.setSymmetry(String.valueOf(newSymmetry));
        selectedRecord.setFractalDimension(String.valueOf(newFractalDimension));
        tableView.refresh();
        stage.close();
    }
}
