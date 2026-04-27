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

public class AccountLedgerApp {

    private static Transaction transaction;
    private static ArrayList<Transaction>transactionList = new ArrayList<>();
    private static LedgerUI ui;
    public static void main(String[] args){
        readFile("transaction_sorted.csv");
        ui= new LedgerUI();
    }

    public static void readFile(String file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String fileInput ="";
            int count = 0;
            while ((fileInput = bufferedReader.readLine()) != null){
                if(count > 0) {
                    String[] fileParts = fileInput.split("\\|");
                   // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate date = LocalDate.parse(fileParts[0]);
                    //formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime time = LocalTime.parse(fileParts[1]);
                    String description = fileParts[2];
                    String vendor = fileParts[3];
                    double amount = Double.parseDouble(fileParts[4]);
                    transaction = new Transaction(date, time, description, vendor, amount);
                    transactionList.add(transaction);
                    //System.out.println("date: " + date + "time: " + time + "description: " + description + "vendor: " + vendor + "amount: " + amount);
                }count++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}