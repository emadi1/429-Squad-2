package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/7/2017.
 */
public class AddStudentController implements Initializable {

    ArrayList<TextField> textFieldList;
    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    ObservableList<String> borrowerStatusList = FXCollections.observableArrayList("Good Standing", "Delinquent");
    @FXML
    private TextField bannerId;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField dateOfLatestBorrowerStatus;
    @FXML
    private TextField dateOfRegistration;
    @FXML
    private TextField notes;
    @FXML
    private ComboBox<String> borrowerStatus;
    @FXML
    private ComboBox<String> status;

    public AddStudentController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(bannerId);
        textFieldList.add(firstName);
        textFieldList.add(lastName);
        textFieldList.add(phoneNumber);
        textFieldList.add(email);
        textFieldList.add(dateOfLatestBorrowerStatus);
        textFieldList.add(dateOfRegistration);
        textFieldList.add(notes);
        borrowerStatus.setValue("Good Standing");
        borrowerStatus.setItems(borrowerStatusList);
        status.setValue("Active");
        status.setItems(statusList);
    }

    private static boolean isNumeric(String string) {
        try {
            double doub = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param actionEvent
     */
    public void submit(ActionEvent actionEvent) {

    }
}
