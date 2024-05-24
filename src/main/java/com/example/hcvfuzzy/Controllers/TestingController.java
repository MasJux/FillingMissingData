package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Holders.*;
import com.example.hcvfuzzy.Objects.NormalizedRecord;
import com.example.hcvfuzzy.Objects.Record;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TestingController implements Initializable {
@FXML
    private VBox firstVBox;
@FXML
    private VBox secondVBox;
    @FXML
    private VBox thirdVBox;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void generateButton(){
        List<Record> dataBeforeDeleting = DataBeforeDeleting.getListWithoutMissingValues();
        List<Record> dataAfterDeleting = DataAfterDeleting.getListWithMissingValues();
        List<NormalizedRecord> normalizedIntervalsList = NormalizedIntervalDataHolder.getNormalizedIntervalsList();
        List<NormalizedRecord> dataAfterEntropy = DataAfterEntropyFilling.getDataAfterEntropyFilling();


        for(Record nr: dataBeforeDeleting){
            int id  = nr.getID();
            int[] attributes = nr.getRecordAttributes();
            int decision = nr.getDecision();
            Label label = new Label(id+ Arrays.toString(attributes) +decision);
            firstVBox.getChildren().add(label);
        }
        for(Record nr: dataAfterDeleting){
            int id  = nr.getID();
            int[] attributes = nr.getRecordAttributes();
            int decision = nr.getDecision();
            Label label = new Label(id+ Arrays.toString(attributes) +decision);
            secondVBox.getChildren().add(label);
        }
        for(NormalizedRecord nr: normalizedIntervalsList){
            int id  = nr.getID();
            double[] radius = nr.getNormalizedRadius();
            double[] texture = nr.getNormalizedTexture();
            double[] perimeter = nr.getNormalizedPerimeter();
            double[] area = nr.getNormalizedArea();
            int decision = nr.getDecision();
            Label label = new Label(id +" "+ Arrays.toString(radius) +" "+ Arrays.toString(texture) +" "+ Arrays.toString(perimeter) +" "+ Arrays.toString(area) +" "+decision);
            thirdVBox.getChildren().add(label);
        }
    }
}
