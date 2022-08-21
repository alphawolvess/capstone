package sample;
/*
Written By: GS and Darren Brunelle
Date: 5/14/21
Language: Java, FXML
 */

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Vector;

public class GenSchedule {

    GenSchedule(){}

    @FXML
    void fill(GridPane Schedule, Accordion Accord){
        String[] Names = new String[15];
        var scheduleChildren = Accord.getChildrenUnmodifiable();
        //ArrayList<String> Names_Times = new ArrayList<String>(); // Could be used to store Name{Avails}
        for(int i = 0; i <= scheduleChildren.size() - 1; i++){
            String s = scheduleChildren.get(i).toString();
            String requiredString = s.substring(s.indexOf("'") + 1, s.length() - 1);// skip 9
            //System.out.println(requiredString + i);
            Names[i] = requiredString;
        }

        for(int i = 0; i <= Names.length - 1; i++){
            if((!Accord.getPanes().get(i).getText().equals("Empty"))){
                Label lb = new Label();
                lb.setText(Names[i]);
                lb.setId(Names[i]);
                lb.setPrefSize(100, 34);
                lb.setAlignment(Pos.CENTER);
                Schedule.setRowIndex(lb, i+1);
                Schedule.getChildren().add(lb);
                //System.out.println(Names[i]);

                for(int j = 1; j <= 7; j++){//fill availability
                    Label lb2 = new Label();
                    var newAP = (AnchorPane)Accord.getPanes().get(i).getContent();
                    var checky = (CheckBox) newAP.getChildren().get(j);
                    if((checky.isSelected() == true)){
                        lb2.setText("x");
                        lb2.setPrefSize(100, 34);
                        lb2.setAlignment(Pos.CENTER);
                        Schedule.setRowIndex(lb2, i+1);
                        Schedule.setColumnIndex(lb2, j);
                        Schedule.getChildren().add(lb2);
                    }
                }
            }
        }
    }

    @FXML
    void clear(GridPane Schedule, Accordion Accord){
        Vector<String> Names = new Vector<String>();
        var scheduleChildren = Schedule.getChildrenUnmodifiable();

        for(int i = 0; i <= scheduleChildren.size() - 1; i++){
            String s = scheduleChildren.get(i).toString();
            String requiredString = s.substring(s.indexOf("'") + 1, s.length() - 1);
            Names.add(requiredString);
        }

        for(int i = Names.size() - 1; i > 8; i--)
            Schedule.getChildren().remove(i);
    }

}
