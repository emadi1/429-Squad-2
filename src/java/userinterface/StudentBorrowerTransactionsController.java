package userinterface;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.StudentBorrower;
import models.StudentBorrowerCollection;
import utilities.Core;
import java.io.IOException;
import java.util.Vector;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph & Ders but mostly Ders but not really on 3/11/2017.
 */
public class StudentBorrowerTransactionsController extends TransactionController {

    @FXML private Text alertMessage;
    Core core = Core.getInstance();
    private Properties language = Core.getInstance().getLang();
    private TableColumn<StudentBorrower, String> bannerIdColumn = new TableColumn<>(language.getProperty("BannerId"));
    private TableColumn<StudentBorrower, String> firstNameColumn = new TableColumn<>(language.getProperty("FirstName"));
    private TableColumn<StudentBorrower, String> lastNameColumn = new TableColumn<>(language.getProperty("LastName"));
    private TableColumn<StudentBorrower, String> contactPhoneColumn =
            new TableColumn<>(language.getProperty("ContactPhone"));
    private TableColumn<StudentBorrower, String> emailColumn = new TableColumn<>(language.getProperty("Email"));
    private TableColumn<StudentBorrower, String> borrowerStatusColumn = new
            TableColumn<>(language.getProperty("BorrowerStatus"));
    private TableColumn<StudentBorrower, String> dateOfLatestBorrowerStatusColumn =
            new TableColumn<>(language.getProperty("DateOfLatestBorrowerStatus"));
    private TableColumn<StudentBorrower, String> dateOfRegistrationColumn = new
            TableColumn<>(language.getProperty("DateOfRegistration"));
    private TableColumn<StudentBorrower, String> statusColumn = new TableColumn<>(language.getProperty("Status"));

    @Override
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
    public void initialize(URL location, ResourceBundle resources) {

        searchChoice.setItems(itemsSearchChoiceArray());
        searchChoice.getSelectionModel().selectFirst();
        studentHeader.setText(language.getProperty("StudentTransactions"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        try {
            setTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setTableView() throws IOException{

        bannerIdColumn.setMinWidth(100);
        firstNameColumn.setMinWidth(100);
        lastNameColumn.setMinWidth(100);
        contactPhoneColumn.setMinWidth(100);
        emailColumn.setMinWidth(100);
        borrowerStatusColumn.setMinWidth(100);
        dateOfLatestBorrowerStatusColumn.setMinWidth(100);
        dateOfRegistrationColumn.setMinWidth(100);
        statusColumn.setMinWidth(100);

        bannerIdColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BANNER_ID));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.FIRST_NAME));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.LAST_NAME));
        contactPhoneColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CONTACT_PHONE));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.EMAIL));
        borrowerStatusColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BORROWER_STATUS));
        dateOfLatestBorrowerStatusColumn.setCellValueFactory
                (new PropertyValueFactory<>(DBKey.DATE_OF_LATEST_BORROWER_STATUS));
        dateOfRegistrationColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.DATE_OF_REGISTRATION));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.STATUS));

        tableView.getColumns().add(bannerIdColumn);
        tableView.getColumns().add(firstNameColumn);
        tableView.getColumns().add(lastNameColumn);
        tableView.getColumns().add(contactPhoneColumn);
        tableView.getColumns().add(emailColumn);
        tableView.getColumns().add(borrowerStatusColumn);
        tableView.getColumns().add(dateOfLatestBorrowerStatusColumn);
        tableView.getColumns().add(dateOfRegistrationColumn);
        tableView.getColumns().add(statusColumn);

        tableView.setRowFactory(tableView ->{

            final TableRow<StudentBorrower> row = new TableRow<>();

            row.setOnMouseClicked((event) -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    StudentBorrower studentBorrower = row.getItem();
                    core.setModStudentBorrower(studentBorrower);
                    if (core.getUser().getCredentials().equals(language.getProperty("Administrator"))) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifystudentview.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                            stage.setScene(scene);
                            stage.setTitle(language.getProperty("modifyWorkerTitle"));
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException | NullPointerException exception) {
                            exception.printStackTrace();
                        }
                    } else alertMessage.setText(language.getProperty("invalidCredentials"));
                }
            });

            row.hoverProperty().addListener((observable) -> {
                final StudentBorrower studentBorrower = row.getItem();
                if (studentBorrower != null) {
                    Tooltip tp = new Tooltip("at row tool");
                    Tooltip.install(row, tp);
                    tp.setText(studentBorrower.toolTipToString());
                }
            });
            return row;
        });
    }

    @Override
    public void add(ActionEvent actionEvent) throws NullPointerException, IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addstudentview.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            stage.setScene(scene);
            stage.setTitle(language.getProperty("addStudentTitle"));
            stage.setResizable(false);
            stage.show();
        } catch (NullPointerException|IOException exception) {
            exception.printStackTrace();
        }
    }


    protected ObservableList querySelector(){

        StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();

        if (input != null || !input.equals("")) {

            if (search.equals(language.getProperty("BannerId"))) {
                if (input.length() == 9 && isNumeric(input)) {
                    Vector students = studentBorrowerCollection.findStudentsByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(students);
                } else alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
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
                if (input.equals(language.getProperty("Delinquent")) || input.equals(language.getProperty("GoodStanding"))) {
                    Vector students = studentBorrowerCollection.findStudentsByBorrowererStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(students);
                } else alertMessage.setText("invalidBorrowerStatus");
            }

            if (search.equals(language.getProperty("DateOfLatestBorrowerStatus"))) {
                if (input.length() == 10 && input.charAt(2) == '-' && input.charAt(5) == '-') {
                    Vector students = studentBorrowerCollection.findStudentsByDateOfLatestBorrowerStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(students);
                } else alertMessage.setText("invalidDateFormat");
            }

            if (search.equals(language.getProperty("DateOfRegistration"))) {
                if (input.length() == 10 && input.charAt(2) == '-' && input.charAt(5) == '-') {
                    Vector students = studentBorrowerCollection.findStudentsByDateOfRegistration(input);
                    searchField.clear();
                    return FXCollections.observableList(students);
                } else alertMessage.setText("invalidDateFormat");
            }

            if (search.equals(language.getProperty("Status"))) {
                if (input.equals(language.getProperty("Active")) || input.equals(language.getProperty("Inactive"))) {
                    Vector students = studentBorrowerCollection.findStudentsByStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
            }
        } else if (input.equals("")) {
            alertMessage.setText("emptyFields");
            return null;
        }
        return null;
    }
}
