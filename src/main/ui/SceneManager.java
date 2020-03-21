package ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Category;
import model.Contact;

import java.io.IOException;
import java.net.URL;

//Manages general operations applicable to most, if not all, scenes
public class SceneManager {

    //Inspiration taken from StackOverflow
    protected static final String IDLE_BUTTON = "-fx-background-color: transparent;";

    //Inspiration taken from StackOverflow
    protected static final String HOVERED_BUTTON = "-fx-background-color: -fx-shadow-highlight-color, "
                + "-fx-outer-border, -fx-inner-border, -fx-body-color;";

    protected final String startScene = "StartUI.fxml";
    protected final String menuScene = "MenuUI.fxml";
    protected final String quitScene = "QuitUI.fxml";
    protected final String createContactScene = "CreateContactUI.fxml";
    protected final String categoriesScene = "CategoriesUI.fxml";
    protected final String savedContactsPopupScene = "contactsSavedPopupUI.fxml";
    protected final String debtsScene = "DebtsUI.fxml";
    protected final String loansScene = "LoansUI.fxml";
    protected final String neutralScene = "NeutralUI.fxml";

    @FXML
    //MODIFIES: this
    //EFFECTS: Changes the scene to the scene specified by file and accepts data about contacts from caller
    public void updateScene(String file, ActionEvent actionEvent) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource(file));
        Scene scene = new Scene(menuParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        URL styleSheetFile = getClass().getResource("MainStylesheet.css");
        String styleSheet = String.valueOf(styleSheetFile);
        scene.getStylesheets().add(styleSheet);
        window.setScene(scene);
        window.show();

    }

    //EFFECTS: exits the program
    public void quitProgram() {
        Platform.exit();
    }


    @FXML
    //EFFECTS: Adds a hover effect to the button
    public void hoverEffect(Button b) {
        b.setStyle(IDLE_BUTTON);
        b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON));
        b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON));
    }

    @FXML
    //MODIFIES: this
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
    //EFFECTS: Changes the scene to contact details scene with contact c's details
    public void updateSceneContactDetails(ActionEvent actionEvent, Contact c) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ContactDetailsUI.fxml"));
        Parent contactDetails = loader.load();

        Scene contactDetailsScene = new Scene(contactDetails);
        ControllerContactDetails controller = loader.getController();
        controller.init(c);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(contactDetailsScene);
        window.show();
    }

    @FXML
    //MODIFIES: this
    //EFFECTS: Assigns an action to the button
    public void assignAction(ActionEvent actionEvent, Contact c) throws IOException {
        updateSceneContactDetails(actionEvent, c);
    }
}
