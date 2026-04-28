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
    private static ArrayList<Transaction>transactionList = new ArrayList<>();
    private static LedgerUI ui;
    private static Ledger ledger;
    private static Scanner scanner = new Scanner(System.in);
    private static int intUserInput;
    public static void main(String[] args){
        readFile("transaction_sorted.csv");
        ui= new LedgerUI();
        ledger = new Ledger();
        mainMenu();
    }

    private static void mainMenu(){
        boolean isRunning = true;
        do {
            intUserInput = getIntUserInput();
            switch (intUserInput) {
                case 1:
                    //add deposit section
                    addDeposit();
                    break;
                case 2:
                    //make payment section
                    break;
                case 3:
                    //display ledger section
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
        ui.addDepositUiInit();
    }

    public static int getIntUserInput(){
        int intUserInput = scanner.nextInt();
        scanner.nextLine();
        return intUserInput;
    }
    public static void readFile(String file){

    }
}