package model;

import java.time.LocalDate;
import java.time.Month;

//Represents a single contact
public class Contact {

    private String name;
    private String description;
    private double transAmount;
    private LocalDate date;
    private String transType;
    private String status;

    public Contact(String n, String description, double transAmount, int year, int month, int day, String transType) {
        this.name = n;
        this.description = description;
        this.transAmount = transAmount;
        date = LocalDate.of(year, month, day);
        this.transType = transType;
        status = " ";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public String getTransType() {
        return transType;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTransAmount(double transAmount) {
        this.transAmount = transAmount;
    }

    public void setDate(int year, int month, int day) {
        date = LocalDate.of(year, month, day);
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: increases the TransAmount by amount
    public void incTransAmount(double amount) {
        transAmount += amount;
    }

    //REQUIRES: amount >= 0 and amount <= transAmount
    //MODIFIES: this
    //EFFECTS: decreases the TransAmount by amount
    public void decTransAmount(double amount) {
        transAmount -= amount;
    }

    //REQUIRES: newCategory string length == 1
    //MODIFIES: this
    //EFFECTS: Changes the Transaction Type to neutral, debts, or loans depending on newCategory
    public void updateTransType(String newCategory) {
        switch (newCategory) {
            case "n":
                transType = "n";
                break;
            case "d":
                transType = "d";
                break;
            case "l":
                transType = "l";
                break;
        }
    }



}
