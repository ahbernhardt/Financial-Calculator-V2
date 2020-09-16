package controller;

import java.util.ArrayList;

public class Result {

    private String APR;
    private String NumberMonths;
    private String MonthlyAmount;
    private String Principal;
    private String LastAmount;
    private ArrayList<Result> allResult= new ArrayList<Result>(  );

    public Result(){

    }

    public Result(String APR, String NumberMonths, String MonthAmounts, String Principal, String LastAmount){
        this.APR = APR;
        this.NumberMonths= NumberMonths;
        this.MonthlyAmount = MonthAmounts;
        this.Principal = Principal;
        this.LastAmount = LastAmount;
    }
    public Result(String APR, String NumberMonths, String MonthAmounts, String Principal){
        this.APR = APR;
        this.NumberMonths= NumberMonths;
        this.MonthlyAmount = MonthAmounts;
        this.Principal = Principal;

    }


    public ArrayList<Result> getAllResult(){
        for (int i = 0; i < allResult.size(); i++)
        {
            Result save = allResult.get(i);
            save.toString();
        }
        return allResult;
    }


    //Format output for print result
    public String toString() {
        return
                "Number of Months: " + this.NumberMonths + "\n"+
                        "Principal Amount: " +  this.Principal  + "\n"+
                        "APR: " +  this.APR +"\n" +
                        "Monthly Payment Amount: " + this.MonthlyAmount  + "\n"+
                        "Last Payment Amount: " +  this.LastAmount  + "\n"
                        + "\n" ;
    }
}

