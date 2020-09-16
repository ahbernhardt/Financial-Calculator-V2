package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {
    public static Stage mainStage;
//    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        Setting Stage
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/welcome.fxml"));
        primaryStage.setTitle("Financial Calculator 2.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
