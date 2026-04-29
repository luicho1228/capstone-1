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
                    ledger.saveLedger();
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
        confirmTransaction();
        if(intUserInput == 1) {
            date = LocalDate.now();
            time = LocalTime.now();
            transaction = new Transaction(date, time, description, vendor, depositAmount);
            transaction.setType("deposit");
            ledger.addDeposit(transaction);
        }
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
            transaction.setType("payment");
            ledger.addPayment(transaction);
        }
    }


    public static void showLedger(){
        boolean isShowingList = true;
        displayTransactionList(ledger.getLedgerArrayList());
        do {

            intUserInput = getIntUserInput();
            if (intUserInput == 1) {
                //show all
                displayTransactionList(ledger.getLedgerArrayList());
            } else if (intUserInput == 2) {
                //show deposits
                displayTransactionList(ledger.getDepositsArrayList());

            } else if (intUserInput == 3) {
                //show payments
                displayTransactionList(ledger.getPaymentArrayList());
            } else if (intUserInput == 4) {
                //show reports
                showReports();
                displayTransactionList(ledger.getLedgerArrayList());
            } else if (intUserInput == 5) {
                //back to main menu
                isShowingList = false;
            }
        }while (isShowingList);


    }

    public static void showReports(){
        boolean isRunning = true;
        do {
            ui.displayTitle("REPORTS");
            ui.promptUser("search filters");
            // String[] reportsOptions = {"Start Date","End Date","Description", "Vendor", "Amount"};
            String[] reportsOptions = {"Month to Date", "Previous Month", "Year to Date", "Previous Year", "Search by Vendor", "Back"};
            ui.showMenuOptions(reportsOptions);
            intUserInput = getIntUserInput();
            if (intUserInput == 1) {
                //show month to date report
                ui.displayProductsInArray(ledger.getMonthToDateList());
                //showStartDateReport();
            } else if (intUserInput == 2) {
                //show previous month report
                ui.displayProductsInArray(ledger.getPreviousMonthList());
            } else if (intUserInput == 3) {
                //show years to date report
                ui.displayProductsInArray(ledger.getYearsToDateList());
            } else if (intUserInput == 4) {
                //show previous year report
                ui.displayProductsInArray(ledger.getPreviousYearList());
            } else if (intUserInput == 5) {
                //show search by vendor report
                ui.promptUser("Vendor");
                String vendorName = scanner.nextLine();
                ui.displayProductsInArray(ledger.getTransactionListByVendor(vendorName));
            } else if (intUserInput == 6) {
                // Back to main exit option
                isRunning = false;
            }
        }while (isRunning);
    }
    public static void showStartDateReport(){
        ui.promptUser("start date(yyyy-mm-dd)");
        String userInputString = scanner.nextLine();

    }

    public static void displayTransactionList(ArrayList<Transaction> transactions){
        ui.displayTitle("LEDGER");
        ui.displayProductsInArray(transactions);
        String[] ledgerOptions = {"Show All", "Show Deposits", "Show Payments","Show Reports","Home"};
        ui.displayTitle("FILTER SEARCH AND REPORTS");
        ui.showMenuOptions(ledgerOptions);
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