package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

//Controller for the Loans scene
public class ControllerLoans implements Initializable {

    private SceneManager sceneManager;
    private TableManager tableManager;
    private ButtonManager buttonManager;

    private Contact selectedContact;

    @FXML
    private TableView<Contact> tableView;

    //The search text field
    @FXML
    private TextField filteredField;

    @FXML
    private TableColumn<Contact, String> names;

    @FXML
    private TableColumn<Contact, String> transAmounts;

    @FXML
    private TableColumn<Contact, String> description;

    @FXML
    private Category contactDetails;

    @FXML
    private Button details;

    @FXML
    private Button category;

    //Observable list to store data
    private final ObservableList<Contact> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneManager = new SceneManager();
        tableManager = new TableManager();
        buttonManager = new ButtonManager();
        buttonManager.hoverEffect(category);
        buttonManager.hoverEffect(details);

        //TODO: DELETE THIS DUPLICATED IMPLEMENTATION
//        //Associating the data model property value to the column ??????????????????????????
//        names.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
//        transAmounts.setCellValueFactory(new PropertyValueFactory<Contact, String>("transAmount"));
//        description.setCellValueFactory(new PropertyValueFactory<Contact, String>("description"));
//
//
//        contactDetails = DataState.getState().getLoans();
//        LinkedList<Contact> loansList = contactDetails.getContactsList();
//
//        for (Contact c : loansList) {
//            dataList.add(c);
//        }
        tableManager.initializeTable(names, transAmounts, description, "l", dataList);

        //Wrap the ObservableList in a FilteredList (while initially displaying ALL the contacts)
        tableManager.createSearchBar(dataList, filteredField, tableView);
    }

    @FXML
    //EFFECTS: Changes the scene to the menu scene
    public void menu(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes the scene to the categories scene
    public void categories(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.categoriesScene, actionEvent);
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Displays the selected row and updates selectedContact
    public void selectRow() {
        selectedContact = tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Changes the scene to the contact details scene of contact
    public void contactDetails(ActionEvent actionEvent) {
        try {
            sceneManager.assignAction(actionEvent, selectedContact);
        } catch (Exception e) {
            return;
        }
    }
}
