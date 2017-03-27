package userinterface;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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

/**
 * Created by kevph & Ders but mostly Ders on 3/11/2017.
 */
public class StudentBorrowerTransactionsController extends TransactionController {

    @FXML private Text alertMessage;


    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                "BannerId",
                "FirstName",
                "LastName",
                "ContactPhone",
                "Email",
                "BorrowerStatus",
                "DateOfLatestBorrowerStatus",
                "DateOfRegistration",
                "Notes",
                "Status");
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


    public static void changeScene(ActionEvent actionEvent) {

    }

    public static boolean deleteStudentBorrower(ActionEvent actionEvent) {
        return false;
    }


    public void add(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addstudentview.fxml"));
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


    @Override
    protected ObservableList querySelector(){

        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "BannerId":
                String bannerId = searchField.getText();
                if (bannerId == null || bannerId.equals("")) {
                    alertMessage.setText("Please enter a numeric BannerID in the search field");
                    searchField.clear();
                    break;
                } else if (bannerId.length() != 9) {
                    alertMessage.setText("BannerID must be 9 numbers long");
                    searchField.clear();
                    break;
                } else {
                    StudentBorrowerCollection studentCollection = new StudentBorrowerCollection();
                    Vector students = studentCollection.findStudentBorrowersByBannerId(bannerId);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }

            case "FirstName":
                String firstName = searchField.getText();
                if (firstName == null || firstName.equals("")) {
                    alertMessage.setText("Please enter a name in the search field");
                    searchField.clear();
                } else {
                    StudentBorrowerCollection studentCollection = new StudentBorrowerCollection();
                    Vector students = studentCollection.findStudentBorrowersByFirstName(firstName);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
                break;
            case "LastName":
                String lastName = searchField.getText();
                if (lastName == null || lastName.equals("")) {
                    alertMessage.setText("Please enter a name in the search field");
                    searchField.clear();
                } else {
                    StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
                    Vector students = studentBorrowerCollection.findStudentBorrowersByLastName(lastName);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
                break;

            case "ContactPhone":
                String contactPhone = searchField.getText();
                if (contactPhone == null || contactPhone.equals("")) {
                    alertMessage.setText("Please enter a phone number in the search field");
                    searchField.clear();
                } else {
                    StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
                    Vector students = studentBorrowerCollection.findStudentBorrowersByContactPhone(contactPhone);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
                break;

            case "Email":
                String email = searchField.getText();
                if (email == null || email.equals("")) {
                    alertMessage.setText("Please enter an email address in the search field");
                    searchField.clear();
                } else {
                    StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
                    Vector students = studentBorrowerCollection.findStudentBorrowersByEmail(email);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
                break;

            case "BorrowerStatus":
                String borrowerStatus = searchField.getText();
                if (borrowerStatus == null || borrowerStatus.equals("") ||
                        !(borrowerStatus.equals("Good") || borrowerStatus.equals("Delinquent"))) {
                    alertMessage.setText("Please enter either: 'Good'/'Delinquent' in the search field");
                    searchField.clear();
                } else {
                    StudentBorrowerCollection studentCollection = new StudentBorrowerCollection();
                    Vector students = studentCollection.findStudentBorrowersByCredentials(borrowerStatus);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
                break;

            case "Notes":
                String notes = searchField.getText();
//                if (notes == null || notes.equals("")) {
//                    alertMessage.setText("Please desired notes in the search field");
//                    searchField.clear();
//                } else {
//                    StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
//                    Vector students = studentBorrowerCollection.findStudentBorrowersByNotes(notes);
//                    searchField.clear();
//                    return FXCollections.observableList(students);
//                }
                break;

            case "Status":
                String status = searchField.getText();
                if (status == null || status.equals("") ||
                        !(status.equals("Active") || status.equals("Inactive"))) {
                    alertMessage.setText("Please enter either: 'Active'/'Inactive' in the search field");
                    searchField.clear();
                } else {
                    StudentBorrowerCollection studentBorrowerCollection = new StudentBorrowerCollection();
                    Vector students = studentBorrowerCollection.findStudentBorrowersByStatus(status);
                    searchField.clear();
                    return FXCollections.observableList(students);
                }
                break;
        }
        searchField.clear();
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
