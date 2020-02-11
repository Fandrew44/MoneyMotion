package model;

import java.util.LinkedList;

//Represents a generic category
public abstract class Category {
    public LinkedList<Contact> contactsList;

    //MODIFIES: this
    //EFFECTS: adds contact to contacts list
    public void addContact(Contact c) {
        contactsList.add(c);
    }

    //MODIFIES: this
    //EFFECTS if c is in contacts list remove it, else do nothing
    public void removeContact(Contact c) {
        if (contactsList.contains(c)) {
            contactsList.remove(c);
        }
    }

    //EFFECTS: returns the contacts list
    public LinkedList<Contact> getContactsList() {
        return contactsList;
    }

    //EFFECTS: produces true if a Contact with the same name is in contacts list, else false
    public boolean isContactInList(String name) {
        boolean result = false;

        for (Contact c : contactsList) {
            if (c.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }

    //EFFECTS: returns the size of the contacts list
    public int getContactsListSize() {
        return contactsList.size();
    }

    //EFFECTS: returns true if contacts list is empty, else false
    public boolean isEmpty() {
        if (contactsList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
