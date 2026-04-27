package com.plurasight;

import java.util.ArrayList;
import java.util.Objects;

public class LedgerUI {

    private String blueTextColor;
    private String yellowTextColor;
    private String greenTextColor;
    private String resetTextColor;
    private String productDetailsTitle;

    public LedgerUI(){
        homeScreenInit();
    }

    private void homeScreenInit(){
        displayTitle("MAIN MENU");
        String[] mainMenuOptions = {"Add Deposit", "Make Payment", "Ledger", "Exit"};
        showMenuOptions(mainMenuOptions);

    }


//    public void displayProductDetails(){
//        int borderLength;
//        String border = "\t";
//        for(int i = 0; i < borderLength; i++){
//            border += "-";
//        }
//        System.out.println(border);
//        System.out.println();
//    }

    public void displayProduct(Object object){
        System.out.println("\t" +"* "+ resetTextColor + object + blueTextColor);
    }

    public void displayProductsInArray(ArrayList<Object> objects){
            for (Object object : objects) {
               displayProduct(object);
            }
    }

    private void showMenuOptions(String[] options){
        System.out.println("Select an option:");
        int menuCount = 1;
        for (String option: options){
            System.out.println("\t"+menuCount+ ". " + option);
            menuCount++;
        }
        displayDivider(50);
        System.out.print("Enter command: ");
    }

    //helper methods to display decorative components
    private void displayTitle(String title){
        String titleDisplay = "\n";
        int numberString = 50 - title.length();
        for(int i = 0; i < numberString; i++){
            titleDisplay += "=";
            if(i == (numberString/2)){
                titleDisplay += title;
            }
        }System.out.println(titleDisplay + "\n");
    }
    public void displayDivider(int length){
        String divider ="";
        for (int i = 0; i < length; i++){
            divider += "--";
        }
        System.out.println(divider);
    }
}
