package sample;/*
/*
Written By: GS and Darren Brunelle
Date: 5/14/21
Language: Java, FXML
 */

import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Controller {
    int empID=0;

    @FXML
    private TextField Name;
    @FXML
    private Accordion Accord;
    @FXML
    private GridPane Schedule;

    @FXML
    void Clear(ActionEvent event) {
        GenSchedule Sched = new GenSchedule();
        Sched.clear(Schedule, Accord);
    }

    @FXML
    void Generate(ActionEvent event) {
        GenSchedule Sched = new GenSchedule();
        Sched.fill(Schedule, Accord);
    }

    @FXML
    void PDF(ActionEvent event) throws IOException, DocumentException {
        schedulePDF testSched = new schedulePDF();
        testSched.createSchedule(Schedule, Accord);

        ///C:/Users/alpha/IdeaProjects/JavaFXCapstoneTest/
        File file = new File("ScheduleStorage/TheSchedule.pdf");
        if ((file.exists()) && (file.toString().endsWith(".pdf"))){
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        }
    }

    @FXML
    void delEmployee(MouseEvent event) {
        int i = empID - 1;
        while (!Accord.getPanes().get(i).getText().equals(Name.getText()) && i != 0) {
            i--;
        }
        if (Accord.getPanes().get(i).getText().equals(Name.getText())) {
            Accord.getPanes().get(i).setText("Empty");
            Name.clear();
            empID--;
        }
    }

    @FXML
    void newEmployee(MouseEvent event) {
        int i=0;
        if (!Name.getText().equals("")) {
            while(!Accord.getPanes().get(i).getText().equals("Empty")){
                i++;
            }
            Accord.getPanes().get(i).setText(Name.getText());

            Name.clear();
            empID++;
        }
    }
}