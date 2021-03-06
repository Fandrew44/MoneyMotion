package model;

import java.util.LinkedList;

//Represents all the Contacts that are neutral in their standing (no debts & no loans)
public class Neutral extends Category {

    public Neutral() {
        contactsList = new LinkedList<Contact>();
    }

    @Override
    public double totalFinances() {
        return 0.00;
    }


}
