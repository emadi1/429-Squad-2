package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.StudentBorrower;
import models.Worker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/27/2017.
 */
public class AddStudentViewController implements Initializable {

    private ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    private ObservableList<String> standingList = FXCollections.observableArrayList("Good", "Delinquent");

    @FXML private TextField ContactPhone;
    @FXML private ComboBox<String> Status;
    @FXML private TextField Email;
    @FXML private Button submit;
    @FXML private TextField DateOfRegistration;
    @FXML private TextField FirstName;
    @FXML private TextField BannerId;
    @FXML private TextField LastName;
    @FXML private TextField Notes;
    @FXML private ComboBox<String> BorrowerStatus;
    @FXML private TextField DateOfLatestBorrowerStatus;
    @FXML private Text alertMessage;

    ArrayList<TextField> textFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(BannerId);
        textFieldList.add(DateOfLatestBorrowerStatus);
        textFieldList.add(FirstName);
        textFieldList.add(LastName);
        textFieldList.add(ContactPhone);
        textFieldList.add(Email);
        BorrowerStatus.setValue("Good");
        BorrowerStatus.setItems(standingList);
        textFieldList.add(Notes);
        textFieldList.add(DateOfRegistration);
        Status.setValue("Active");
        Status.setItems(statusList);
    }


    public void submit(ActionEvent event) {

        Properties prop = new Properties();

        alertMessage.setText("");

        // TODO field checks
        for (TextField textField : textFieldList) {

            if (textField.getText().equals("")) {
                alertMessage.setText("Please complete all fields");
                return;
            } else {
                prop.put(textField.getId(), textField.getText());
            }
        }
        prop.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
        prop.put(BorrowerStatus.getId(), BorrowerStatus.getSelectionModel().getSelectedItem());
        //WorkerCollection workerCollection = new WorkerCollection();
        //Worker worker = (Worker) workerCollection.findWorkersByBannerId(textFieldList.get(0).getText()).elementAt(0);
        //if (!worker.getBannerId().equals(prop.getProperty("BannerId"))) {
        StudentBorrower newStudent = new StudentBorrower(prop);
        newStudent.update();
        alertMessage.setText("Student has been submitted");
        //} else {
        // alertMessage.setText("BannerID already exists in database.");
        //}
        for (TextField t : textFieldList) { t.clear(); }
    }
}
