package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {

    @FXML
    public void next_button(ActionEvent event) throws IOException {
            System.out.println("Welcome");
            Parent window1 = FXMLLoader.load(getClass().getResource("/view/app_page.fxml"));
            Stage homeScreenStage = AppMain.mainStage;;
            Scene homeScreenScene = new Scene(window1);
            homeScreenStage.setScene(homeScreenScene);
            homeScreenStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
