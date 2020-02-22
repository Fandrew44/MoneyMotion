package persistence;


import model.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Reads data about Contacts from a file
public class ContactsReader {

    public static final String DELIMITER = ",";

    //EFFECTS: returns the list of saved contacts from file, throws FileNotFoundException if
    //         file cannot be found
    public static ArrayList<Contact> readContacts(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        while (scanner.hasNextLine()) {
            contacts.add(createContact(separateProperties(scanner.nextLine())));
        }
        return contacts;
    }

    //Inspiration taken from the TellerApp
    //EFFECTS: Separates a line of the properties of a Contact and returns them
    public static ArrayList<String> separateProperties(String line) {
        String[] properties = line.split(DELIMITER);
        return new ArrayList<String>(Arrays.asList(properties));
    }

    //Inspiration taken from the TellerApp
    //EFFECTS: Creates and returns a contact with the given properties
    public static Contact createContact(ArrayList<String> properties) {
        Contact c;
        String name = properties.get(0);
        String description = properties.get(1);
        double transAmount = Double.parseDouble(properties.get(2));
        int year = Integer.parseInt(properties.get(3));
        int month = Integer.parseInt(properties.get(4));
        int day = Integer.parseInt(properties.get(5));
        String transType = properties.get(6);

        return new Contact(name, description, transAmount, year, month, day, transType);
    }

}
