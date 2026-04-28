package com.plurasight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class AccountLedgerApp {

    private static Transaction transaction;
    private static LedgerUI ui;
    private static Ledger ledger;
    private static Scanner scanner = new Scanner(System.in);
    private static int intUserInput;
    private static LocalDate date;
    private static LocalTime time;

    public static void main(String[] args){
        ui= new LedgerUI();
        ledger = new Ledger();
        mainMenu();
    }

    private static void mainMenu(){
        boolean isRunning = true;
        do {
            ui.homeScreenInit();
            intUserInput = getIntUserInput();
            switch (intUserInput) {
                case 1:
                    //add deposit section
                    addDeposit();
                    break;
                case 2:
                    //make payment section
                    addPayment();
                    break;
                case 3:
                    //display ledger section
                    showLedger();
                    break;
                case 4:
                    // Exit
                    isRunning = false;
                    //display goodbyes ui
                    break;
                default:
                    break;
            }
        }while(isRunning);
    }

    public static void addDeposit(){
        //ui.addDepositUiInit();
        ui.displayTitle("ADD DEPOSIT");
        ui.promptUser("deposit amount");
        double depositAmount = scanner.nextDouble();
        scanner.nextLine();
        ui.promptUser("vendor ");
        String vendor = scanner.nextLine();
        ui.promptUser("description");
        String description = scanner.nextLine();
        date = LocalDate.now();
        time = LocalTime.now();
        transaction = new Transaction(date,time,description,vendor,depositAmount);
        ledger.addDeposit(transaction);
    }
    private static void addPayment(){
        ui.displayTitle("ADD PAYMENT");
        ui.promptUser("payment amount");
        double depositAmount = scanner.nextDouble();
        scanner.nextLine();
        ui.promptUser("vendor ");
        String vendor = scanner.nextLine();
        ui.promptUser("description");
        String description = scanner.nextLine();
        confirmTransaction();
        if(intUserInput == 1) {
            date = LocalDate.now();
            time = LocalTime.now();
            transaction = new Transaction(date, time, description, vendor, depositAmount);
            ledger.addPayment(transaction);
        }
    }

    public static void showLedger(){

    }


    public static void confirmTransaction(){
        ui.confirmTransaction();
        intUserInput = getIntUserInput();
        System.out.println("Enter command: ");
    }

    public static int getIntUserInput(){
        int intUserInput = scanner.nextInt();
        scanner.nextLine();
        return intUserInput;
    }
}