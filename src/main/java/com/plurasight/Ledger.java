package com.plurasight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Ledger {
    ArrayList<Transaction> ledgerArrayList;
    HashMap<LocalDate, Transaction> dateTransactionHashMap;
    HashMap<LocalTime, Transaction> timeTransactionHashMap;
    HashMap<String, Transaction> vendorTransactionHashMap;
    HashMap<Double,Transaction> amountTransactionHashMap;
    private double totalAmount;

    public Ledger(){
        ledgerArrayList = new ArrayList<>();
        dateTransactionHashMap = new HashMap<>();
        timeTransactionHashMap = new HashMap<>();
        vendorTransactionHashMap = new HashMap<>();
        amountTransactionHashMap = new HashMap<>();
        totalAmount = 0.0;
        loadLedger("transaction_sorted.csv");
    }

    public double getTotalAmount(){
        return totalAmount;
    }

    public void addDeposit(Transaction transaction){
        totalAmount += transaction.getAmount();
        addTransaction(transaction);
    }

    public void addPayment(Transaction transaction){
        double paymentAmount = transaction.getAmount();
        paymentAmount = -paymentAmount;
        transaction.setAmount(paymentAmount);
        totalAmount += paymentAmount;
        addTransaction(transaction);
    }

    private void sortLedger(){
        ledgerArrayList.sort(Comparator.comparing(Transaction::getDate));
        reverseLedgerOrder();
        //ledgerArrayList.sort(Comparator.comparing(Transaction::getTime));
    }
    public void reverseLedgerOrder(){
        Collections.reverse(ledgerArrayList);
    }

    private void addTransaction(Transaction newTransaction){
        ledgerArrayList.add(newTransaction);
        dateTransactionHashMap.put(newTransaction.getDate(),newTransaction);
        timeTransactionHashMap.put(newTransaction.getTime(),newTransaction);
        vendorTransactionHashMap.put(newTransaction.getVendor(), newTransaction);
        amountTransactionHashMap.put(newTransaction.getAmount(), newTransaction);
        sortLedger();
    }
    public void removeTransaction(Transaction transaction){
        ledgerArrayList.remove(transaction);
        dateTransactionHashMap.remove(transaction.getDate());
        timeTransactionHashMap.remove(transaction.getTime());
        vendorTransactionHashMap.remove(transaction.getVendor());
        amountTransactionHashMap.remove(transaction.getAmount());
    }

    public Transaction getTransactionByDate(LocalDate date){
        return dateTransactionHashMap.get(date);
    }
    public Transaction getTransactionByTime(LocalTime time){
        return timeTransactionHashMap.get(time);
    }
    public Transaction getTransactionByAmount(double amount){
        return amountTransactionHashMap.get(amount);
    }

    public void loadLedger(String filePath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
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
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    this.addTransaction(transaction);
                    //System.out.println("date: " + date + "time: " + time + "description: " + description + "vendor: " + vendor + "amount: " + amount);
                }count++;
            }
            sortLedger();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
