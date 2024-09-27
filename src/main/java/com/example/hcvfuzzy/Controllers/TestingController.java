package com.example.hcvfuzzy.Controllers;

import com.example.hcvfuzzy.Holders.*;
import com.example.hcvfuzzy.Objects.Interval;
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
        List<NormalizedRecord> normalizedIntervalsList = NormalizedIntervals.getNormalizedIntervalsList();

        firstVBox.getChildren().clear();
        secondVBox.getChildren().clear();
        thirdVBox.getChildren().clear();

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


            Interval texture = nr.getNormalizedTexture();
            Interval perimeter = nr.getNormalizedPerimeter();
            Interval area = nr.getNormalizedArea();
            int decision = nr.getDecision();

            Label label = new Label( " "
                    + (texture != null ? texture.getLower() : "null") + " "
                    + (texture != null ? texture.getUpper() : "null")
                    + ", decision: " + decision);

            // Wyświetlanie etykiety (przykładowo w konsoli)
            System.out.println(label.getText());
            thirdVBox.getChildren().add(label);
        }
        System.out.println("data before deleting size: "+dataBeforeDeleting.size());
        System.out.println("data after deleting size: "+dataAfterDeleting.size());
    }
}
