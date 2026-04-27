package com.plurasight;

public class LedgerUI {

    public LedgerUI(){
        homeScreenInit();
    }

    private void homeScreenInit(){
        displayTitle("MAIN MENU");
    }


    private void displayTitle(String title){
        String titleDisplay = "\n";
        int numberString = 100 - title.length();
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
