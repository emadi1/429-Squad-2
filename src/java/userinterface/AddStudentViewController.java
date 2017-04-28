package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.StudentBorrower;
import models.StudentBorrowerCollection;
import utilities.Core;

import java.net.URL;
import java.util.*;

/**
 * Created by Ders on 3/27/2017.
 */
public class AddStudentViewController extends StudentBorrowerTransactionsController implements Initializable {

    private Properties language = Core.getInstance().getLang();
    private ArrayList<TextField> textFieldList;

    @FXML private Text bannerId;
    @FXML private Text firstName;
    @FXML private Text lastName;
    @FXML private Text contactPhone;
    @FXML private Text email;
    @FXML private Text dateOfLatestBorrowerStatus;
    @FXML private Text dateOfRegistration;
    @FXML private Text notes;
    @FXML private Text borrowerStatus;
    @FXML private Text status;
    @FXML private Text alertMessage;
    @FXML private Button submit;
    @FXML private ComboBox<String> Status;
    @FXML private ComboBox<String> BorrowerStatus;
    @FXML private TextField Email;
    @FXML private TextField ContactPhone, CountryCode;
    @FXML private TextField DateOfRegistration;
    @FXML private TextField FirstName;
    @FXML private TextField BannerId;
    @FXML private TextField LastName;
    @FXML private TextField Notes;
    @FXML private TextField DateOfLatestBorrowerStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> statusList = FXCollections.observableArrayList(language.getProperty("Active"), language.getProperty("Inactive"));
        ObservableList<String> standingList = FXCollections.observableArrayList(language.getProperty("GoodStanding"), language.getProperty("Delinquent"));

        // Set Text's text
        submit.setText(language.getProperty("Add"));
        bannerId.setText(language.getProperty("PromptBannerId"));
        firstName.setText(language.getProperty("PromptFirstName"));
        lastName.setText(language.getProperty("PromptLastName"));
        contactPhone.setText(language.getProperty("PromptContactPhone"));
        email.setText(language.getProperty("PromptEmail"));
        dateOfLatestBorrowerStatus.setText(language.getProperty("PromptDateOfLatestBorrowerStatus"));
        dateOfRegistration.setText(language.getProperty("PromptDateOfRegistration"));
        notes.setText(language.getProperty("PromptNotes"));
        borrowerStatus.setText(language.getProperty("PromptBorrowerStatus"));
        status.setText(language.getProperty("PromptStatus"));

        // Set PromptText in text fields
        BannerId.setPromptText(language.getProperty("BannerId"));
        FirstName.setPromptText(language.getProperty("FirstName"));
        LastName.setPromptText(language.getProperty("LastName"));
        ContactPhone.setPromptText(language.getProperty("ContactPhone"));
        Email.setPromptText(language.getProperty("Email"));
        DateOfLatestBorrowerStatus.setText(Core.generateDate());
        DateOfRegistration.setText(Core.generateDate());
        DateOfLatestBorrowerStatus.setDisable(true);
        DateOfRegistration.setDisable(true);
        Notes.setPromptText(language.getProperty("Notes"));
        CountryCode.setPromptText("Country");

        // Add TextFields to ArrayList
        textFieldList = new ArrayList<>();
        textFieldList.add(FirstName);
        textFieldList.add(LastName);
        textFieldList.add(ContactPhone);
        textFieldList.add(Email);
        BorrowerStatus.setValue(language.getProperty("GoodStanding"));
        BorrowerStatus.setItems(standingList);
        Status.setValue(language.getProperty("Active"));
        Status.setItems(statusList);
        Status.setDisable(true);
    }

    public void submit(ActionEvent event) {

        Properties prop = new Properties();
        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();

        String phoneNum = ContactPhone.getText();

        if (phoneNum.contains("-")) {
            phoneNum = phoneNum.replaceAll("-", "");
        }

        phoneNum = CountryCode.getText() + "-" + phoneNum;

        if (phoneNum.length() != 14 || phoneNum.charAt(3) != '-' ||
                !Core.isNumeric(phoneNum.substring(0, 3)) || !Core.isNumeric(phoneNum.substring(5))) {
            alertMessage.setText(language.getProperty("invalidPhoneFormat"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
            return;
        } //else prop.put(DBKey.CONTACT_PHONE, phoneNum);

        if (BannerId.getText().length() != 9) {
            alertMessage.setText(language.getProperty("invalidBannerIdLength"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
            return;
        }

        if (BannerId.getText().length() != 9 || !isNumeric(BannerId.getText())) {
            alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
            return;
        } else prop.put(BannerId.getId(), BannerId.getText());

        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText(language.getProperty("completeFields"));
                alertMessage.setFill(Paint.valueOf("dcc404"));
                return;
            } prop.put(textField.getId(), textField.getText());
        }

        prop.put(DBKey.CONTACT_PHONE, phoneNum);

        if (DateOfLatestBorrowerStatus.getText().length() != 10 || DateOfRegistration.getText().length() != 10 ||
                DateOfLatestBorrowerStatus.getText().charAt(2) != '-' || DateOfRegistration.getText().charAt(2) != '-' ||
                DateOfLatestBorrowerStatus.getText().charAt(5) != '-' || DateOfRegistration.getText().charAt(5) != '-') {
            alertMessage.setText(language.getProperty("invalidDateFormat"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
            return;
        } else {
            prop.put(DateOfLatestBorrowerStatus.getId(), DateOfLatestBorrowerStatus.getText());
            prop.put(DateOfRegistration.getId(), DateOfLatestBorrowerStatus.getText());
        }

        if (core.getLanguage().equals("fr_FR")) {
            String hireDay = DateOfRegistration.getText().substring(3, 5);
            String hireMonth = DateOfRegistration.getText().substring(0, 2);
            String hireYear = DateOfRegistration.getText().substring(6);
            String credDay = DateOfLatestBorrowerStatus.getText().substring(3, 5);
            String credMonth = DateOfLatestBorrowerStatus.getText().substring(0, 2);
            String credYear = DateOfLatestBorrowerStatus.getText().substring(6);
            prop.setProperty("DateOfRegistration", hireDay + "-" + hireMonth + "-" + hireYear);
            prop.setProperty("DateOfLatestBorrowerStatus", credDay + "-" + credMonth + "-" + credYear);
        }

        if (!Notes.getText().equals("")) prop.put(Notes.getId(), Notes.getText());

        if (studentBorrowerCollection.findStudentsByBannerId(prop.getProperty(DBKey.BANNER_ID)).size() == 0) {
            prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
            prop.put(BorrowerStatus.getId(), BorrowerStatus.getSelectionModel().getSelectedItem());
            StudentBorrower newStudentBorrower = new StudentBorrower(prop);
            newStudentBorrower.insert();
            alertMessage.setText(language.getProperty("addStudentSuccess"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
        } else {
            alertMessage.setText(language.getProperty("existingBannerId") + prop.getProperty(DBKey.BANNER_ID));
            alertMessage.setFill(Paint.valueOf("dcc404"));
        }

        for (TextField t : textFieldList) { t.clear(); }
        BannerId.clear();
        DateOfRegistration.clear();
        DateOfLatestBorrowerStatus.clear();
        Notes.clear();
        BorrowerStatus.setValue(language.getProperty("GoodStanding"));
        Status.setValue(language.getProperty("Active"));
    }
}
