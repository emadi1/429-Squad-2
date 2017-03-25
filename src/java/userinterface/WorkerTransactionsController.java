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
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by kevph feat. Ders on 3/11/2017.
 */
public class WorkerTransactionsController extends SearchController {

    @FXML private Text alertMessage;

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                "BannerId",
                "FirstName",
                "LastName",
                "ContactPhone",
                "Email",
                "Credentials",
                "DateOfLatestCredentialsStatus",
                "DateOfHire",
                "Status");
    }


    protected void setTableView() {
        TableColumn column;
        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(92);
            column.setCellValueFactory(new PropertyValueFactory<Worker, String>(property));
            tableView.getColumns().add(column);
        }

        TableColumn<Worker, Boolean> actionCol = new TableColumn<>("Action");
        actionCol.setSortable(false);
        tableView.getColumns().add(actionCol);

        // Define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Worker, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Worker, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // Create a cell value factory with an add button for each row in the table.
        actionCol.setCellFactory(new Callback<TableColumn<Worker, Boolean>, TableCell<Worker, Boolean>>() {
            @Override
            public TableCell<Worker, Boolean> call(TableColumn<Worker, Boolean> workerBooleanTableColumn) {
                return new AddWorkerCell((Stage) tableView.getScene().getWindow(), tableView);
            }
        });

    }

    public void add(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addworkerview.fxml"));
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
    public void modify(ActionEvent actionEvent) throws IOException {
        try {
            Core core = Core.getInstance();
            Worker worker = (Worker)tableView.getItems().get(tableView.getFocusModel().getFocusedIndex());
            System.out.println(worker.toString());
            String bannerId = worker.getBannerId();
            core.setModWorker(worker);
            if (bannerId != null) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyworkerview.fxml"));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                primaryStage.setScene(scene);
                primaryStage.setTitle("Brockport Library System");
                primaryStage.setResizable(false);
                primaryStage.show();
            } else {
                alertMessage.setText("Please select worker to modify.");
            }
        } catch (IOException e) {
            System.out.println("Error");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyworkerview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Brockport Library System");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }

    protected ObservableList querySelector() {

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
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByBannerId(bannerId);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }

            case "FirstName":
                String firstName = searchField.getText();
                if (firstName == null || firstName.equals("")) {
                    alertMessage.setText("Please enter a name in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByFirstName(firstName);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "LastName":
                String lastName = searchField.getText();
                if (lastName == null || lastName.equals("")) {
                    alertMessage.setText("Please enter a name in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByLastName(lastName);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "ContactPhone":
                String contactPhone = searchField.getText();
                if (contactPhone == null || contactPhone.equals("")) {
                    alertMessage.setText("Please enter a phone number in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByContactPhone(contactPhone);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "Email":
                String email = searchField.getText();
                if (email == null || email.equals("")) {
                    alertMessage.setText("Please enter an email address in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByEmail(email);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "Credentials":
                String credentials = searchField.getText();
                if (credentials == null || credentials.equals("") ||
                        !(credentials.equals("Administrator") || credentials.equals("Ordinary"))) {
                    alertMessage.setText("Please enter either: 'Administrator'/'Ordinary' in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByCredentials(credentials);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "DateOfLatestCredentialsStatus":
                String dateOfLatestCredStatus = searchField.getText();
                break;

            case "Date of Hire":
                String dateOfHire = searchField.getText();
                break;

            case "Status":
                String status = searchField.getText();
                if (status == null || status.equals("") ||
                        !(status.equals("Active") || status.equals("Inactive"))) {
                    alertMessage.setText("Please enter either: 'Active'/'Inactive' in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkerByStatus(status);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;
        }

        searchField.clear();

        return null;
    }

    private class AddWorkerCell extends TableCell<Worker, Boolean> {

        final Button addButton = new Button("Modify");
        final StackPane paddedButton = new StackPane();


        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new worker can be added.
         */
        AddWorkerCell(final Stage stage, final TableView table) {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    table.getSelectionModel().select(getTableRow().getIndex());

                    Core core = Core.getInstance();
                    Worker w = (Worker) table.getSelectionModel().getSelectedItems().get(0);
                    core.setModWorker(w);

                    showModifyPersonDialog();
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

        private void showModifyPersonDialog() {

            try {

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyworkerview.fxml"));
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
    }
}
