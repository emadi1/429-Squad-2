package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/22/2017.
 */
public class WorkerDataFieldViewController implements Initializable{

    private ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    private ObservableList<String> credentialsList = FXCollections.observableArrayList("Administrator", "Ordinary");

    @FXML private TextField ContactPhone;
    @FXML private ComboBox<String> Status;
    @FXML private TextField Email;
    @FXML private Button submit;
    @FXML private TextField DateOfHire;
    @FXML private TextField FirstName;
    @FXML private TextField BannerId;
    @FXML private TextField LastName;
    @FXML private TextField DateOfLastCredentialStatus;
    @FXML private ComboBox<String> Credentials;
    @FXML private TextField Password;

    ArrayList<TextField> textFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(BannerId);
        textFieldList.add(Password);
        textFieldList.add(FirstName);
        textFieldList.add(LastName);
        textFieldList.add(ContactPhone);
        textFieldList.add(Email);
        Credentials.setValue("Ordinary");
        Credentials.setItems(credentialsList);
        textFieldList.add(DateOfLastCredentialStatus);
        textFieldList.add(DateOfHire);
        Status.setValue("Active");
        Status.setItems(statusList);
    }


    public void submit(ActionEvent event) {

    }

}






