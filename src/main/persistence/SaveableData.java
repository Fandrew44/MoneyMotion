package persistence;

import java.io.PrintWriter;

//Represents data that can be saved to file
public interface SaveableData {

    //Inspiration taken from the TellerApp
    //MODIFIES: pw
    //EFFECTS: writes the saveable data to pw
    public abstract void save(PrintWriter pw);
}
