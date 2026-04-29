package com.plurasight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ledger {
    private ArrayList<Transaction> ledgerArrayList;
    private ArrayList<Transaction> depositsArrayList;
    private ArrayList<Transaction> paymentArrayList;
    private HashMap<LocalDate, Transaction> dateTransactionHashMap;
    private HashMap<LocalTime, Transaction> timeTransactionHashMap;
    private HashMap<String, Transaction> vendorTransactionHashMap;
    private HashMap<Double,Transaction> amountTransactionHashMap;
    private DateTimeFormatter formatter;
    private double totalAmount;
    private LocalDate today;

    public Ledger(){
        ledgerArrayList = new ArrayList<>();
        depositsArrayList = new ArrayList<>();
        paymentArrayList = new ArrayList<>();
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

    public ArrayList<Transaction> getLedgerArrayList(){
        return ledgerArrayList;
    }

    public ArrayList<Transaction> getDepositsArrayList(){
        return depositsArrayList;
    }
    public ArrayList<Transaction> getPaymentArrayList(){
        return paymentArrayList;
    }
    public void addDeposit(Transaction transaction){
        totalAmount += transaction.getAmount();
        depositsArrayList.add(transaction);
        addTransaction(transaction);
    }

    public void addPayment(Transaction transaction){
        double paymentAmount = transaction.getAmount();
        paymentAmount = -paymentAmount;
        transaction.setAmount(paymentAmount);
        totalAmount += paymentAmount;
        paymentArrayList.add(transaction);
        addTransaction(transaction);
    }

    public ArrayList<Transaction> getTransactionListByVendor(String vendor){
        ArrayList<Transaction>vendorList = new ArrayList<>();
        Transaction transaction;
        for (Transaction currentTransaction: ledgerArrayList){
            if (vendor.equalsIgnoreCase(currentTransaction.getVendor())){
                transaction = currentTransaction;
                vendorList.add(transaction);
            }
        }
        return vendorList;
    }

    public ArrayList<Transaction> getYearsToDateList(){
        ArrayList<Transaction>yearToDateList = new ArrayList<>();
        Transaction transaction;
        today = LocalDate.now();
        int year = today.getYear();
        formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String firstDateOfYear = String.format(year +"/"+"1"+"/"+"1");
        LocalDate dateOfYear = LocalDate.parse(firstDateOfYear,formatter);
        while(dateOfYear.isBefore(today)){
            transaction = dateTransactionHashMap.get(dateOfYear);
            if(!(transaction == null)) {
                yearToDateList.add(transaction);
            }dateOfYear = dateOfYear.plusDays(1);
        }
        return yearToDateList;
    }
    public ArrayList<Transaction> getPreviousYearList(){
        ArrayList<Transaction> preYearList = new ArrayList<>();
        Transaction transaction;
        today = LocalDate.now();
        LocalDate previousYear = today.minusYears(1);
        int lengthOfYear = previousYear.lengthOfYear();
        formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String firstDateOfYear = String.format(previousYear.getYear()+"/"+"1"+"/"+"1");
        LocalDate dateOfMonth = LocalDate.parse(firstDateOfYear,formatter);
        for(int dayOfMonth = 1; dayOfMonth <= lengthOfYear;dayOfMonth++){
            transaction = dateTransactionHashMap.get(dateOfMonth);
            if (transaction != null){
                preYearList.add(transaction);
            }dateOfMonth = dateOfMonth.plusDays(1);
        }
        return preYearList;
    }

    public ArrayList<Transaction> getMonthToDateList(){
        ArrayList<Transaction>monthToDateList = new ArrayList<>();
        Transaction transaction;
        today = LocalDate.now();
        int month = today.getMonthValue();
        formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String firstDateOfMonth = today.getYear()+"/"+month +"/"+"1";
        LocalDate datesOfMonth = LocalDate.parse(firstDateOfMonth,formatter);
        while(datesOfMonth.isBefore(today)){
            transaction = dateTransactionHashMap.get(datesOfMonth);
            if(!(transaction == null)) {
                monthToDateList.add(transaction);
            }datesOfMonth = datesOfMonth.plusDays(1);
        }
        return monthToDateList;
    }

    public ArrayList<Transaction> getPreviousMonthList(){
        ArrayList<Transaction> preMonthList = new ArrayList<>();
        Transaction transaction;
        today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        int lengthOfMonth = lastMonth.lengthOfMonth();
        formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String firstDayOfMonth = String.format(today.getYear() +"/"+lastMonth.getMonthValue()+"/"+"1");
        LocalDate dateOfMonth = LocalDate.parse(firstDayOfMonth,formatter);
        //String lastDayOfMonth = String.format(today.getYear() +"/"+lastMonth.getMonthValue()+"/"+lengthOfMonth);
        for(int currentDay = 1; currentDay <= lengthOfMonth; currentDay++){
            transaction = dateTransactionHashMap.get(dateOfMonth);
            if(transaction != null){
                preMonthList.add(transaction);
            }dateOfMonth = dateOfMonth.plusDays(1);
        }
        return preMonthList;
    }

    public ArrayList<Transaction> searchMonthToDateList(LocalDate preMonth){
        ArrayList<Transaction>monthToDateList = new ArrayList<>();
        today = LocalDate.now();
        while (preMonth.isBefore(today)){
            monthToDateList.add(dateTransactionHashMap.get(preMonth));
            preMonth = preMonth.plusDays(1);
        }
        return monthToDateList;
    }

    public ArrayList<Transaction> searchListByVendor(String vendor){
        ArrayList<Transaction> vendorArrayList = new ArrayList<>();
        for (Transaction transaction: ledgerArrayList){
            if (vendor.equalsIgnoreCase(transaction.getVendor())){
                vendorArrayList.add(transaction);
            }
        }
        return vendorArrayList;
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
                    if (transaction.getAmount() > 0){
                        depositsArrayList.add(transaction);
                    } else if (transaction.getAmount() < 0) {
                        paymentArrayList.add(transaction);
                    }
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
