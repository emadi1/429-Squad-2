package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.StudentBorrower;
import models.StudentBorrowerCollection;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by kevph & Ders on 3/11/2017.
 */
public class StudentBorrowerTransactionsController extends TransactionController {

    @FXML private Text alertMessage;
    Core core = Core.getInstance();
    private Properties language = Core.getInstance().getLang();

    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                language.getProperty("BannerId"),
                language.getProperty("FirstName"),
                language.getProperty("LastName"),
                language.getProperty("ContactPhone"),
                language.getProperty("Email"),
                language.getProperty("BorrowerStatus"),
                language.getProperty("DateOfLatestBorrowerStatus"),
                language.getProperty("DateOfRegistration"),
                language.getProperty("Status")
        );
    }
    @Override
    public ObservableList<String> dedicatedColumnHeaders() {
        return FXCollections.observableArrayList(
                DBKey.BANNER_ID,
                DBKey.FIRST_NAME,
                DBKey.LAST_NAME,
                DBKey.CONTACT_PHONE,
                DBKey.EMAIL,
                DBKey.BORROWER_STATUS,
                DBKey.DATE_OF_LATEST_BORROWER_STATUS,
                DBKey.DATE_OF_REGISTRATION,
                DBKey.NOTES,
                DBKey.STATUS
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        properties = itemsSearchChoiceArray();
        searchChoice.setItems(properties);
        searchChoice.getSelectionModel().selectFirst();
        studentHeader.setText(language.getProperty("StudentTransactions"));
        modify.setText(language.getProperty("Modify"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        if (core.getUser().getCredentials().equals("Ordinary")) modify.setDisable(true);
        setTableView();
    }

    protected void setTableView() {
        TableColumn column;
        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<StudentBorrower, String>(property));
            tableView.getColumns().add(column);
        }
    }

    public void add(ActionEvent actionEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addstudentborrowerview.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            stage.setScene(scene);
            stage.setTitle(language.getProperty("addStudentBorrowerTitle"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(ActionEvent actionEvent) throws IOException {
        try {
            StudentBorrower studentBorrower = (StudentBorrower) tableView.getSelectionModel().getSelectedItem();
            core.setModStudentBorrower(studentBorrower);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifystudentborrowerview.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            stage.setScene(scene);
            stage.setTitle(language.getProperty("modifyStudentBorrowerTitle"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ObservableList querySelector(){

        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();
        if (input != null || input.equals("")) {

            if (search.equals(language.getProperty("BannerId"))) {
                if (input.length() != 9 || !isNumeric(input))
                    alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
                else {
                    Vector students = studentBorrowerCollection.findStudentsByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
            }

            if (search.equals(language.getProperty("FirstName"))) {
                Vector students = studentBorrowerCollection.findStudentsByFirstName(input);
                searchField.clear();
                return FXCollections.observableList(students);
            }

            if (search.equals(language.getProperty("LastName"))) {
                Vector students = studentBorrowerCollection.findStudentsbyLastName(input);
                searchField.clear();
                return FXCollections.observableList(students);
            }

            if (search.equals(language.getProperty("ContactPhone"))) {
                Vector students = studentBorrowerCollection.findStudentsByContactPhone(input);
                searchField.clear();
                return FXCollections.observableList(students);
            }

            if (search.equals(language.getProperty("Email"))) {
                Vector students = studentBorrowerCollection.findStudentsByEmail(input);
                searchField.clear();
                return FXCollections.observableList(students);
            }

            if (search.equals(language.getProperty("BorrowerStatus"))) {
                Vector students = studentBorrowerCollection.findStudentsByBorrowererStatus(input);
                searchField.clear();
                return FXCollections.observableList(students);
            }

            if (search.equals(language.getProperty("DateOfLatestBorrowerStatus")) && search.length() == 10) {
                Vector students = studentBorrowerCollection.findStudentsByDateOfLatestBorrowerStatus(input);
                searchField.clear();
                return FXCollections.observableList(students);
            } else alertMessage.setText("invalidDateFormat");


        }
        return null;
    }

    @Override
    protected void showModifyDialog() {

    }

    @Override
    protected int getType() {
        return 0;
    }

}
