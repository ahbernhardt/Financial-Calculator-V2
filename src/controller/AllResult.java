package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllResult {
    public static List<Result> allResult = new ArrayList<>(  );
    @FXML
    private Button home, display;
    @FXML
    private TextArea taDisplay;

    @FXML
    void click_button(javafx.event.ActionEvent actionEvent) throws IOException {
        Object button = actionEvent.getSource();
        if (button == home) {
            System.out.println("Home App Page");
            loadStage("/view/app_page.fxml");
        } else if (button == display) {
            System.out.println("Display All Saved Result");
            display();
        }
    }
    private void loadStage(String fxml){
        Parent window;
        try{
            window = FXMLLoader.load(getClass().getResource(fxml));
            Stage homeStage;
            Scene homeScene = new Scene(window);
            homeStage = AppMain.mainStage;
            homeStage.setScene(homeScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void display(){
        for (int i = 0; i < allResult.size(); i++) {
            Result save = allResult.get( i );
            save.toString();
            taDisplay.appendText( save.toString() );
        }
    }
}
