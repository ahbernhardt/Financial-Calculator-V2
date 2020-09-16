package controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculate {
    private double PrincipalAmount; //the loan amount, principal sum, initial investment//
    private int NumberOfMonths;// total of payment for the entire loan//
    private double APR;// interest rate per period//
    private double MonthlyAmount;//the payment of each equal payment//
    private double LastAmount;


    public Calculate() {
    }
    /* CASE 1
     *   Calculate the monthly payment
     */

    public Calculate(double APR, int NumberOfMonths, double Principal) {
        this.APR = APR;
        this.NumberOfMonths = NumberOfMonths;
        this.PrincipalAmount = Principal;
    }


    /* CASE 2
     * Calculate the Annual Payment Rate (APR)
     */

    public Calculate(int NumberOfMonths, double MonthlyAmount, double Principal) {
        this.MonthlyAmount = MonthlyAmount;
        this.NumberOfMonths =  NumberOfMonths;
        this.PrincipalAmount = Principal;
    }


    /* CASE 3
     * Calculate the number of payment
     */

    public Calculate(double MonthlyAmount, double APR, double Principal) {
        this.MonthlyAmount = MonthlyAmount;
        this.APR = APR;
        this.PrincipalAmount = Principal;
    }


    // CASE 4
    //Calculate the Annual Payment Rate (APR)
    //

    public Calculate(double MonthlyAmount, double APR, int NumberOfMonths) {
        this.MonthlyAmount = MonthlyAmount;
        this.APR = APR;
        this.NumberOfMonths = NumberOfMonths;

    }

    // Getter and Setter of Monthly Amount

    public double getMonthlyAmount(String text) {
        //set 2 decimal point for output
        DecimalFormat format = new DecimalFormat( "#.##", DecimalFormatSymbols.getInstance( Locale.ENGLISH ) );
        format.setRoundingMode( RoundingMode.DOWN );

        if (APR > 0.0) { // APR input = 0.0
            double monthlyAPR = APR / 1200;
            MonthlyAmount = (PrincipalAmount * monthlyAPR)/ (1 - Math.pow( 1 + monthlyAPR, - NumberOfMonths ));
        } else if ((APR == 0.00) && ((PrincipalAmount %NumberOfMonths) == 0.00)) //APR input is 0.0 and the remainder for monthly amount is exactly even
        {
            MonthlyAmount = (PrincipalAmount / NumberOfMonths);
        } else { //APR input is 0.0 and the remainder for monthly amount is not .00
            double monthly = PrincipalAmount / NumberOfMonths ; // find monthly amount
            String Amount = format.format( monthly ); //convert double to String to get 2 decimal place
            MonthlyAmount = Double.parseDouble(Amount);
        } return MonthlyAmount;
    }

    public double getLastAmount(String text){
        DecimalFormat format = new DecimalFormat( "#.##", DecimalFormatSymbols.getInstance( Locale.ENGLISH ) );
        format.setRoundingMode( RoundingMode.DOWN );

        double monthly = PrincipalAmount / NumberOfMonths; // find monthly amount
        String Amount = format.format( monthly ); //convert double to String to get 2 decimal place

        double remainder = PrincipalAmount - (Double.parseDouble( Amount ) * NumberOfMonths);// find remainder
        String Remainder = format.format( remainder );//convert double to String to get 2 decimal place

        double lastPayment = Float.valueOf( Amount ) + Float.valueOf( Remainder ); //total for the last payment
        String LastPayment = format.format( lastPayment );//convert double to String to get 2 decimal place
        //System.out.println("The first " + (NumberOfMonths - 1) + " payment is " + Amount + " the last payment is " + LastPayment );
        LastAmount = Double.parseDouble(LastPayment );
        return LastAmount;
    }

    // Set a new paymentAmount
    public void setMonthlyAmount (double MonthlyAmount)
    {
        this.MonthlyAmount = MonthlyAmount;
    }


    // Getter and Setter APR
    public double getAPR(String text) {
        double num = Double.valueOf( NumberOfMonths );
        double q = Math.log( 1 + (1 / num) ) / Math.log( 2 );
        double rate1 = Math.pow( (1 + (MonthlyAmount / PrincipalAmount)), (1 / q) ) - 1;
        APR = (Math.pow( rate1, q ) - 1) * 1200;
        return APR;
    }
    // Set a new annualInterestRate
    public void setAPR(double APR) {
        this.APR = APR;
    }

    // Return Principal Amount
    public double getPrincipal(String text) {
        if (APR == 0) {
            PrincipalAmount = MonthlyAmount * NumberOfMonths;
        }else {
            double r = (APR / 1200);
            PrincipalAmount = (MonthlyAmount / r) * (1 - Math.pow( (1 + r), -NumberOfMonths ));
        } return PrincipalAmount;
    }

    // Set a Principal Amount
    public void setLoanAmount(double loanAmount) {
        this.PrincipalAmount = loanAmount;
    }

    // Getter and setter NumberOfPayment
    public int getNumberOfMonths(String text) {
        if (APR > 0) {
            double r = (APR / 1200);
            double NumberOfMonths = (-(Math.log( 1 - (((r) * PrincipalAmount) / MonthlyAmount) )) / (Math.log( 1 + (r) )));
        } else {
            NumberOfMonths = (int) (PrincipalAmount / MonthlyAmount);
        }
        return NumberOfMonths;
    }

    //Set a new numberOfPayment
    public void setNumberOfMonths(int numberOfMonths) {
        this.NumberOfMonths = numberOfMonths;
    }
}
