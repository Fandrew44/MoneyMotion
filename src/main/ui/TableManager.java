package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;
import model.Contact;

import java.util.LinkedList;

//Manages general operations related to tables in SceneManager
public class TableManager {

    private Category contactDetails;

    private ObservableList<Contact> dataList = FXCollections.observableArrayList();

    @FXML
    //EFFECTS: Creates a search bar for the table in the scene
    public void createSearchBar(ObservableList dataList, TextField filteredField, TableView<Contact> tableView) {
        FilteredList<Contact> filteredData = new FilteredList<>(dataList, b -> true);

        //Set the filter predicate whenever the filter changes ??????????????????
        filteredField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(contact -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Automatically changes the user input in the text field to lower case for ease of comparison
                String lowerCaseFilter = newValue.toLowerCase();

                //Compares the specified property of the Contact (in this case the name) to
                // the user input in the text field
                if (contact.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        //Wrap the filtered list in a sorted list
        SortedList<Contact> sortedData = new SortedList<Contact>(filteredData);

        //Bind the SortedList comparator to the TableView comparator ?????????????????
        //              else sorting the TableView would have NO EFFECT ??????????????
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        //Add the sorted (and filtered) data to the table
        tableView.setItems(sortedData);
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Initializes the table for the specified category
    public void initializeTable(TableColumn c1, TableColumn c2, TableColumn c3, String type, ObservableList list) {
        dataList = list;

        c1.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<Contact, String>("transAmount"));
        c3.setCellValueFactory(new PropertyValueFactory<Contact, String>("description"));

        assignData(type);
        LinkedList<Contact> contactsList = contactDetails.getContactsList();

        dataList.addAll(contactsList);

    }


    //MODIFIES: this
    //EFFECTS: Assigns the corresponding data to the table depending on type
    public void assignData(String type) {
        if (type.equals("d")) {
            contactDetails = DataState.getState().getDebts();
        } else if (type.equals("l")) {
            contactDetails = DataState.getState().getLoans();
        } else {
            contactDetails = DataState.getState().getNeutral();
        }
    }
}
