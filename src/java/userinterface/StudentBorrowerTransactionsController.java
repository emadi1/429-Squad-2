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
        properties = dedicatedColumnHeaders();
        searchChoice.setItems(itemsSearchChoiceArray());
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
            column.setMinWidth(86);
            column.setCellValueFactory(new PropertyValueFactory<StudentBorrower, String>(property));
            tableView.getColumns().add(column);
        }

        TableColumn<StudentBorrower, Boolean> actionCol = new TableColumn<>("Action");
        actionCol.setSortable(false);
        tableView.getColumns().add(actionCol);

        // Define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StudentBorrower, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<StudentBorrower, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });


        // Create a cell value factory with an add button for each row in the table.
        actionCol.setCellFactory(new Callback<TableColumn<StudentBorrower, Boolean>, TableCell<StudentBorrower, Boolean>>() {
            @Override
            public TableCell<StudentBorrower, Boolean> call(TableColumn<StudentBorrower, Boolean> studentBooleanTableColumn) {
                return new AddModCell((Stage) tableView.getScene().getWindow(), tableView);
            }
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

    @Override
    public void modify(ActionEvent actionEvent) throws NullPointerException, IOException {
        try {
            StudentBorrower studentBorrower = (StudentBorrower) tableView.getSelectionModel().getSelectedItem();
            String bannerId = studentBorrower.getBannerId();
            core.setModStudentBorrower(studentBorrower);
            if (bannerId != null) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifystudentview.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                stage.setScene(scene);
                stage.setTitle(language.getProperty("modifyStudentTitle"));
                stage.setResizable(false);
                stage.show();
            } else {
                alertMessage.setText(language.getProperty("emptyField"));
            }
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

    @Override
    protected void showModifyDialog() {

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifystudentview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Brockport Library System");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected class AddModCell extends TableCell<StudentBorrower, Boolean> {

        final Button addButton = new Button("Modify");
        final StackPane paddedButton = new StackPane();


        /**
         * AddModCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new worker can be added.
         */
        AddModCell(final Stage stage, final TableView table) {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    table.getSelectionModel().select(getTableRow().getIndex());
                    Core core = Core.getInstance();
                    StudentBorrower sb = (StudentBorrower) table.getSelectionModel().getSelectedItems().get(0);
                    core.setModStudentBorrower(sb);
                    showModifyDialog();

                }
            });
        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
