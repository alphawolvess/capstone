/*
Written By: GS and Darren Brunelle
Date: 5/14/21
Language: Java, FXML
 */

package sample;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

import javafx.scene.control.Accordion;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.CheckBox;

import javax.naming.Name;

public class schedulePDF {
    @FXML
    void createSchedule(GridPane Schedule, Accordion Accord) throws DocumentException, FileNotFoundException {
        //Find & Delete old File
        File file = new File("ScheduleStorage/TheSchedule.pdf");
        if(file.exists()){
            file.delete();
        }
        //Serialize GridPane information
        String[] Names = new String[15];
        var scheduleChildren = Schedule.getChildren();
        int nameIterator = 0;
        for(int i = 9; i <= scheduleChildren.size() - 1; i++){
            String s = scheduleChildren.get(i).toString();
            String requiredString = s.substring(s.indexOf("'") + 1, s.length() - 1);// skip 9
            if((!requiredString.equals("x")) && ((!requiredString.equals("Empty")))) {
                Names[nameIterator] = requiredString;
                nameIterator++;
            }
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream( "ScheduleStorage/" +  "TheSchedule.pdf"));// Doc made, pick location
        document.open();

        //Font font = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
        var paragraph = new Paragraph("Schedule");
        var table = new PdfPTable(8);

        Stream.of("Employee", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday").forEach(table::addCell);//Header

        for(int i = 0; i <= Names.length - 1; i++){
            table.addCell(Names[i]);

            for(int j = 1; j <= 7; j++){//fill availability
                var newAP = (AnchorPane)Accord.getPanes().get(i).getContent();
                var checky = (CheckBox) newAP.getChildren().get(j);
                if((checky.isSelected() == true) && (!Accord.getPanes().get(i).getText().equals("Empty"))){
                    table.addCell("x");
                }else{
                    table.addCell(" ");
                }

            }
        }

        paragraph.add(table);
        document.add(paragraph);
        document.close();
    }
}
