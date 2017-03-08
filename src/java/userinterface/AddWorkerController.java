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

public class AddWorkerController implements Initializable {
    ArrayList<TextField> textFieldList;
    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    @FXML
    private TextField bannerId;
    @FXML
    private TextField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField credentials;
    @FXML
    private TextField dateOfLatestCredentialStatus;
    @FXML
    private TextField dateOfHire;
    @FXML
    private ComboBox<String> status;

    public AddWorkerController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(bannerId);
        textFieldList.add(password);
        textFieldList.add(firstName);
        textFieldList.add(lastName);
        textFieldList.add(phoneNumber);
        textFieldList.add(email);
        textFieldList.add(credentials);
        textFieldList.add(dateOfLatestCredentialStatus);
        textFieldList.add(dateOfHire);
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
