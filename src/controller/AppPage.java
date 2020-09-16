package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppPage implements Initializable {
    @FXML
    private Button cal_monthly_select,
            cal_APR_select,
            cal_numMonth_select,
            cal_principal_select,
            all_result_select;

    @FXML
    void click_button(javafx.event.ActionEvent actionEvent) throws IOException {
        Object button = actionEvent.getSource();
        if (button == cal_monthly_select) {
            System.out.println("Calculate Monthly Amount");
            loadStage("/view/monthly_amount.fxml");
        } else if (button == cal_APR_select) {
            System.out.println("Calculate APR");
            loadStage("/view/apr.fxml");
        } else if (button == cal_numMonth_select) {
            System.out.println("Calculate Number of Months");
            loadStage("/view/num_month.fxml");
        } else if (button == cal_principal_select) {
            System.out.println("Calculate Principal");
            loadStage("/view/principal.fxml");
        } else if (button == all_result_select) {
            System.out.println("Display all result");
            loadStage("/view/all_result.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

}
