package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Writes (saves) ALL Contacts' data to a file
public class ContactsWriter {

    private PrintWriter pw;

    //Inspiration taken from the TellerApp
    //EFFECTS: instantiates a ContactsWriter object that will write data to file
    public ContactsWriter(File file) throws FileNotFoundException {
        pw = new PrintWriter(file);
    }

    //Inspiration taken from the TellerApp
    //MODIFIES: this
    //EFFECTS: writes saveableData to file
    public void write(SaveableData saveableData) {
        saveableData.save(pw);
    }

    //Inspiration taken from the TellerApp
    //MODIFIES: this
    //EFFECTS: closes the buffer stream for pw
    //NOTE: this Method MUST be called once finished writing data to the file
    public void close() {
        pw.close();
    }

}
