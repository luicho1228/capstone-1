package com.plurasight;

import java.util.ArrayList;
import java.util.Objects;

public class LedgerUI {

    private String blueTextColor;
    private String yellowTextColor;
    private String greenTextColor;
    private String resetTextColor;
    private String boldText;
    private String productDetailsTitle;

    public LedgerUI(){
        blueTextColor = "\u001B[34m";
        yellowTextColor ="\u001B[33m";
        resetTextColor = "\u001B[0m";
        greenTextColor = "\u001B[32m";
        boldText = "\u001B[1m";

        //homeScreenInit();
    }

    public void homeScreenInit(){
        System.out.println("\n"+addTabs()+"  ░██████                                                ░████████                         ░██       \n" +
               addTabs()+ " ░██   ░██                                               ░██    ░██                        ░██       \n" +
               addTabs()+ "░██         ░██    ░██ ░████████   ░███████  ░██░████    ░██    ░██   ░██████   ░████████  ░██    ░██\n" + addTabs()+" ░████████  ░██    ░██ ░██    ░██ ░██    ░██ ░███        ░████████         ░██  ░██    ░██ ░██   ░██ \n" +
               addTabs()+ "        ░██ ░██    ░██ ░██    ░██ ░█████████ ░██         ░██     ░██  ░███████  ░██    ░██ ░███████  \n" +
               addTabs()+ " ░██   ░██  ░██   ░███ ░███   ░██ ░██        ░██         ░██     ░██ ░██   ░██  ░██    ░██ ░██   ░██ \n" +
               addTabs()+ "  ░██████    ░█████░██ ░██░█████   ░███████  ░██         ░█████████   ░█████░██ ░██    ░██ ░██    ░██\n" +
               addTabs()+ "                       ░██                                                                           \n" +
               addTabs()+ "                       ░██                                                                           \n" +
               addTabs()+ "                                                                                                     ");
        displayTitle("MAIN MENU");
        String[] mainMenuOptions = {"Add Deposit", "Make Payment", "Ledger", "Exit"};
        showMenuOptions(mainMenuOptions);

    }

    public String addTabs(){
        return "\t\t\t\t\t";
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

    public void promptUser(String prompt){
        System.out.print(addTabs() + "Enter " + prompt + ":");

    }

    public void displayProduct(Transaction transaction){
        System.out.println(addTabs() + "\t" +"* "+ resetTextColor + transaction + blueTextColor);
    }

    public void displayProductsInArray(ArrayList<Transaction> transactions){
            for (Transaction transaction : transactions) {
               displayProduct(transaction);
            }
    }

    public void confirmTransaction(){
        System.out.println(addTabs() + "Are you sure you want to submit this transaction? " +
              "\n"+ addTabs() + "1.Yes\t\t\t\t2.No");
    }
    public void showMenuOptions(String[] options){
        System.out.println(addTabs() + blueTextColor+"Select an option:");
        int menuCount = 1;
        for (String option: options){
            System.out.println(addTabs() + addTabs()+"\t"+menuCount+ ". " + option);
            menuCount++;
        }
        displayDivider(50);
        System.out.print(addTabs() + "Enter command: ");
    }
    public void displayInputError(){
        System.out.println(addTabs() + "Please try again and enter one of the options provided");
    }
    public void displayAmountInputError(){
        System.out.println(addTabs() + "the input should be a decimal value representing deposits or payments.");
    }

    //helper methods to display decorative components
    public void displayTitle(String title){
        String titleDisplay = "\n" + addTabs();
        int numberString = 100 - title.length();
        for(int i = 0; i < numberString; i++){
            titleDisplay += "=";
            if(i == (numberString/2)){
                titleDisplay += title;
            }
        }System.out.println(boldText + titleDisplay + "\n");
    }
    public void displayDivider(int length){
        String divider ="";
        for (int i = 0; i < length; i++){
            divider += "-";
        }
        System.out.println(addTabs() + divider);
    }
}
