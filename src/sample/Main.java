/*
Written By: GS and Darren Brunelle
Date: 5/14/21
Language: Java, FXML
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.setTitle("Scheduler");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}