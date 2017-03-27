package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/22/2017.
 */
public class AddWorkerViewController implements Initializable {

    @FXML private Text bannerId;
    @FXML private Text password;
    @FXML private Text firstName;
    @FXML private Text lastName;
    @FXML private Text contactPhone;
    @FXML private Text email;
    @FXML private Text credentials;
    @FXML private Text dateOfLatestCredentialsStatus;
    @FXML private Text dateOfHire;
    @FXML private Text status;
    @FXML private TextField BannerId;
    @FXML private TextField Password;
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private TextField ContactPhone;
    @FXML private TextField Email;
    @FXML private ComboBox<String> Credentials;
    @FXML private TextField DateOfLatestCredentialsStatus;
    @FXML private TextField DateOfHire;
    @FXML private ComboBox<String> Status;
    @FXML private Button submit;
    @FXML private Text alertMessage;
    Core core = Core.getInstance();
    Properties lang = core.getLang();
    ArrayList<TextField> textFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> statusList = FXCollections.observableArrayList(lang.getProperty("Active"), lang.getProperty("Inactive"));
        ObservableList<String> credentialsList = FXCollections.observableArrayList(lang.getProperty("Administrator"), lang.getProperty("Ordinary"));
        submit.setText(lang.getProperty("Add"));
        bannerId.setText(lang.getProperty("bannerId"));
        password.setText(lang.getProperty("password"));
        firstName.setText(lang.getProperty("firstName"));
        lastName.setText(lang.getProperty("lastName"));
        contactPhone.setText(lang.getProperty("contactPhone"));
        email.setText(lang.getProperty("email"));
        credentials.setText(lang.getProperty("credentials"));
        dateOfLatestCredentialsStatus.setText(lang.getProperty("dateOfLatestCredentialsStatus"));
        dateOfHire.setText(lang.getProperty("dateOfHire"));
        status.setText(lang.getProperty("status"));
        textFieldList = new ArrayList<>();
        textFieldList.add(BannerId);
        textFieldList.add(Password);
        textFieldList.add(FirstName);
        textFieldList.add(LastName);
        textFieldList.add(ContactPhone);
        textFieldList.add(Email);
        Credentials.setValue(lang.getProperty("Ordinary"));
        Credentials.setItems(credentialsList);
        textFieldList.add(DateOfLatestCredentialsStatus);
        textFieldList.add(DateOfHire);
        Status.setValue(lang.getProperty("Active"));
        Status.setItems(statusList);
    }


    public void submit(ActionEvent event) {

        Properties prop = new Properties();
        WorkerCollection workerCollection = new WorkerCollection();
        int count = workerCollection.findWorkersByBannerId(prop.getProperty("BannerId")).size();

        if (BannerId.getText().length() != 9) {
            alertMessage.setText(lang.getProperty("invalidBannerIdLength"));
            return;
        }

        if (DateOfHire.getText().length() != 10 || DateOfLatestCredentialsStatus.getText().length() != 10
                || DateOfHire.getText().charAt(5) != '-' || DateOfHire.getText().charAt(7) != '-'
                || DateOfLatestCredentialsStatus.getText().charAt(4) != '-'
                || DateOfLatestCredentialsStatus.getText().charAt(7) != '-') {
            alertMessage.setText(lang.getProperty("invalidDateFormat"));
            return;
        }

        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText(lang.getProperty("completeFields"));
                return;
            } else prop.put(textField.getId(), textField.getText());
        }

        if (core.getLanguage().equals("fr_FR")) {
            String hireDay = DateOfHire.getText().substring(3, 5);
            String hireMonth = DateOfHire.getText().substring(0, 2);
            String hireYear = DateOfHire.getText().substring(6);
            String credDay = DateOfLatestCredentialsStatus.getText().substring(3, 5);
            String credMonth = DateOfLatestCredentialsStatus.getText().substring(0, 2);
            String credYear = DateOfLatestCredentialsStatus.getText().substring(6);
            prop.setProperty("DateOfHire", hireDay + "-" + hireMonth + "-" + hireYear);
            prop.setProperty("DateOfLatestCredentialsStatus", credDay + "-" + credMonth + "-" + credYear);
        }

        if (count == 0) {
            prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
            prop.put(Credentials.getId(), Credentials.getSelectionModel().getSelectedItem());
            Worker newWorker = new Worker(prop);
            newWorker.insert();
            alertMessage.setText(lang.getProperty("addWorkerSuccess"));
        } else alertMessage.setText(lang.getProperty("existingBannerId") + prop.getProperty("BannerID"));

        for (TextField t : textFieldList) { t.clear(); }
        Credentials.setValue(lang.getProperty("Ordinary"));
        Status.setValue(lang.getProperty("Active"));
    }
}