package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Objects.Record;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditWindowController implements Initializable {
    @FXML
    TextField RadiusField, TextureField, PerimeterField, AreaField, SmoothnessField;
    @FXML
    TextField CompactnessField, ConcavityField, ConcavePointsField, SymmetryField, FractalDimensionField;
    private Record selectedRecord;
    private AnchorPane anchorPane;
    private TableView<Record> tableView;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void initData(Record selectedRecord) {
        this.selectedRecord = selectedRecord;
        RadiusField.setText(String.valueOf(selectedRecord.getRadius()));
        TextureField.setText(String.valueOf(selectedRecord.getTexture()));
        PerimeterField.setText(String.valueOf(selectedRecord.getPerimeter()));
        AreaField.setText(String.valueOf(selectedRecord.getArea()));
        SmoothnessField.setText(String.valueOf(selectedRecord.getSmoothness()));
        CompactnessField.setText(String.valueOf(selectedRecord.getCompactness()));
        ConcavityField.setText(String.valueOf(selectedRecord.getConcavity()));
        ConcavePointsField.setText(String.valueOf(selectedRecord.getConcavePoints()));
        SymmetryField.setText(String.valueOf(selectedRecord.getSymmetry()));
        FractalDimensionField.setText(String.valueOf(selectedRecord.getFractalDimension()));
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
    private void updateRow() {
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
        selectedRecord.setRadius(newRadius);
        selectedRecord.setTexture(newTextureField);
        selectedRecord.setPerimeter(newPerimeter);
        selectedRecord.setArea(newArea);
        selectedRecord.setSmoothness(newSmoothness);
        selectedRecord.setCompactness(newCompactness);
        selectedRecord.setConcavity(newConcavity);
        selectedRecord.setConcavePoints(newConcavePoints);
        selectedRecord.setSymmetry(newSymmetry);
        selectedRecord.setFractalDimension(newFractalDimension);
        tableView.refresh();
        stage.close();
    }
}
