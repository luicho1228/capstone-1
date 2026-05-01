package com.plurasight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;
    private DateTimeFormatter formatter;
    private String transactionType;
    private String greenTextColor;
    private String redTextColor;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        greenTextColor = "\u001B[32m";
        redTextColor = "\u001B[31m";
    }

    public void setType(String transactionType){
        this.transactionType = transactionType;
    }

    public String getTransactionType(){
        return transactionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String toStringWriter(){
        return String.format("%s|%s|%s|%s|%.2f" , getDate().toString(),formatter.format(getTime()),getDescription(),getVendor(),getAmount());
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString(){
        String transactionString;
        if(amount < 0){
            transactionString = String.format(" %-12s | %-10s | %-25s | %-20s | "+redTextColor+"%10.2f" , getDate().toString(),formatter.format(getTime()),getDescription(),getVendor(),getAmount());
        }else {
            transactionString = String.format(" %-12s | %-10s | %-25s | %-20s | "+greenTextColor+"%10.2f" , getDate().toString(),formatter.format(getTime()),getDescription(),getVendor(),getAmount());

        }

        return transactionString;
    }
}
