package model;

import java.util.LinkedList;

//Represents all the Contacts that the user is indebted to
public class Debts extends Category {

    private int totalDebt;

    public Debts() {
        contactsList = new LinkedList<Contact>();
    }

    @Override
    public double totalFinances() {
        double totalAmount = 0.00;
        for (Contact c : contactsList) {
            totalAmount -= c.getTransAmount();
        }
        return totalAmount;
    }

}
