package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

public class MonthlyAmount {

    @FXML
    private TextField tfAPR,tfMonths,tfPrincipal,tfMonthlyAmount,tfLastAmount;

    @FXML
    private Button home,results,save_button,reset_button, calculate_button;


    @FXML
    void click_button(javafx.event.ActionEvent actionEvent) throws IOException {
        Object button = actionEvent.getSource();
        if (button == save_button) {
            System.out.println("Save result");
            save_Input();
        } else if (button == reset_button) {
            System.out.println("Reset Input");
            reset_Input();
        } else if (button == calculate_button) {
            if (tfAPR.getText().equals( "" )) {
                displayDialog( "Please enter the Annual Interest Rate" );
            } else if (tfMonths.getText().equals( "" ))
                displayDialog( "Please enter the number of months" );
            else if (tfPrincipal.getText().equals( "" ))
                displayDialog( "Please enter the Principal Amount" );
            else {
                System.out.println("Calculate Inputs");
                calculatePayment();
            }
        } else if (button == home) {
            System.out.println("Home App Page");
            loadStage("/view/app_page.fxml");
        }else if (button == results) {
            System.out.println("Save Results page");
            loadStage("/view/all_result.fxml");
        }
    }
    @FXML
    public void save_Input() {
        String APR = tfAPR.getText();
        String numberOfMonths = tfMonths.getText();
        String loanAmount = tfPrincipal.getText();
        String monthlyAmount= tfMonthlyAmount.getText();
        String lastAmount= tfLastAmount.getText();

        Result result = new Result(APR, numberOfMonths, monthlyAmount, loanAmount,lastAmount);
        if (!AllResult.allResult.contains( result )) {
            AllResult.allResult.add( result );
        }
    }

    public void reset_Input() {
        tfAPR.clear();
        tfMonths.clear();
        tfPrincipal.clear();
        tfMonthlyAmount.clear();
        tfLastAmount.clear();
    }
    public static void displayDialog(String message) {
        Alert alert = new Alert( Alert.AlertType.ERROR );
        alert.setTitle( "Error" );
        alert.setHeaderText( "Empty Field" );
        alert.setContentText( message );
        alert.showAndWait();
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

    public void calculatePayment() {
        try {
            // Get values from text fields
            double APR = Double.parseDouble(tfAPR.getText() );
            int numberOfMonths = Integer.parseInt(tfMonths.getText() );
            double loanAmount = Double.parseDouble(tfPrincipal.getText() );
            // Create a monthly object
            Calculate monthlyAmount = new Calculate(APR,numberOfMonths,loanAmount);
            Calculate lastAmount = new Calculate( APR,numberOfMonths,loanAmount );

            // Set format for input
            NumberFormat percentFormat = NumberFormat.getPercentInstance();
            percentFormat.setMinimumFractionDigits( 1 );
            tfAPR.setText(percentFormat.format( APR / 100 ) );

            NumberFormat numberForm = NumberFormat.getNumberInstance();
            tfMonths.setText(numberForm.format( numberOfMonths ) );

            NumberFormat currencyForm = NumberFormat.getCurrencyInstance();
            tfPrincipal.setText( currencyForm.format( loanAmount ) );

            // Display monthly payment
            NumberFormat curForm = NumberFormat.getCurrencyInstance();
            tfMonthlyAmount.setText(curForm.format( monthlyAmount.getMonthlyAmount(tfMonthlyAmount.getText()) ) );
            NumberFormat cur2Form = NumberFormat.getCurrencyInstance();
            tfLastAmount.setText(cur2Form.format( lastAmount.getLastAmount(tfLastAmount.getText()) ) );

        } catch (NumberFormatException ex) {
            displayDialog( "All the inputs must be in numbers only" );
        }
    }


}
