package userinterface;

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
import utilities.Core;
import java.net.URL;;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/27/2017.
 */
public class ModifyStudentViewController implements Initializable {

    private Core core = Core.getInstance();
    Properties language = core.getLang();
    private ObservableList<String> statusList = FXCollections.observableArrayList(language.getProperty("Active"), language.getProperty("Inactive"));
    private ObservableList<String> borrowerStatusList = FXCollections.observableArrayList(language.getProperty("GoodStanding"), language.getProperty("Delinquent"));

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
    @FXML private TextField ContactPhone, CountryCode;
    @FXML private ComboBox<String> Status;
    @FXML private TextField Email;
    @FXML private Button submit;
    @FXML private TextField DateOfRegistration;
    @FXML private TextField FirstName;
    @FXML private TextField BannerId;
    @FXML private TextField LastName;
    @FXML private TextField DateOfLatestBorrowerStatus;
    @FXML private ComboBox<String> BorrowerStatus;
    @FXML private TextField Notes;
    @FXML private Text alertMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BannerId.setText(core.getModStudentBorrower().getBannerId());
        BannerId.setDisable(true);
        Notes.setText(core.getModStudentBorrower().getNotes());
        FirstName.setText(core.getModStudentBorrower().getFirstName());
        LastName.setText(core.getModStudentBorrower().getLastName());
//        ContactPhone.setText(core.getModStudentBorrower().getContactPhone());
        ContactPhone.setText(core.getModStudentBorrower().getContactPhone().substring(4));
        CountryCode.setText(core.getModStudentBorrower().getContactPhone().substring(0,3));

        Email.setText(core.getModStudentBorrower().getEmail());
        BorrowerStatus.setValue(core.getModStudentBorrower().getBorrowerStatus());
        DateOfLatestBorrowerStatus.setText(core.getModStudentBorrower().getDateOfLatestBorrowerStatus());
        DateOfRegistration.setText(core.getModStudentBorrower().getDateOfRegistration());
        Status.setValue(core.getModStudentBorrower().getStatus());
        BorrowerStatus.setItems(borrowerStatusList);
        Status.setItems(statusList);
        submit.setText(language.getProperty("Modify"));
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
    }

    public void submit(ActionEvent actionEvent) {
        alertMessage.setText("");
        StudentBorrower studentBorrower = core.getModStudentBorrower();
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
        } else studentBorrower.setContactPhone(phoneNum);

        studentBorrower.setNotes(Notes.getText());
        studentBorrower.setFirstName(FirstName.getText());
        studentBorrower.setLastName(LastName.getText());
        studentBorrower.setContactPhone(phoneNum);
        studentBorrower.setEmail(Email.getText());
        studentBorrower.setBorrowerStatus(BorrowerStatus.getValue());
        studentBorrower.setDateOfLatestBorrowerStatus(DateOfLatestBorrowerStatus.getText());
        studentBorrower.setDateOfRegistration(DateOfRegistration.getText());
        studentBorrower.setStatus(Status.getValue());
        studentBorrower.update();
        System.out.println(studentBorrower.toString());
        alertMessage.setText("Student Borrower successfully updated");
    }
}
