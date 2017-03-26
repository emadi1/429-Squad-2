package userinterface;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by kevph feat. Ders on 3/11/2017.
 */
public class WorkerTransactionsController extends TransactionController {

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    @FXML private Text alertMessage;
    private String BannerId = language.getProperty("BannerId");
    private String FirstName = language.getProperty("FirstName");
    private String LastName = language.getProperty("LastName");
    private String ContactPhone = language.getProperty("ContactPhone");
    private String Email = language.getProperty("Email");
    private String Credentials = language.getProperty("Credentials");
    private String DateOfLatestCredentialsStatus = language.getProperty("DateOfLatestCredentialsStatus");
    private String DateOfHire = language.getProperty("DateOfHire");
    private String Status = language.getProperty("Status");

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                BannerId,
                FirstName,
                LastName,
                ContactPhone,
                Email,
                Credentials,
                DateOfLatestCredentialsStatus,
                DateOfHire,
                Status);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        properties = itemsSearchChoiceArray();
        searchChoice.setItems(properties);
        searchChoice.getSelectionModel().selectFirst();
        workerHeader.setText(language.getProperty("WorkerTransactions"));
        modify.setText(language.getProperty("Modify"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        if (core.getUser().getCredentials().equals("Ordinary")) {
            modify.setDisable(true);
        }
        setTableView();
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
                return new AddModCell((Stage) tableView.getScene().getWindow(), tableView);
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
            Worker worker = (Worker)tableView.getSelectionModel().getSelectedItem();
            System.out.println(worker);
            Core core = Core.getInstance();
            core.setModWorker(worker);
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

    protected ObservableList querySelector() {
        String input = searchField.getText();
        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "BannerId":
                if (input == null || input.equals("")) {
                    alertMessage.setText(language.getProperty("InvalidEntry"));
                    searchField.clear();
                    break;
                } else if (input.length() != 9) {
                    alertMessage.setText(language.getProperty("InvalidEntry"));
                    searchField.clear();
                    break;
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }

            case "ID de bannière":
                if (input == null || input.equals("")) {
                    alertMessage.setText(language.getProperty("InvalidEntry"));
                    searchField.clear();
                    break;
                } else if (input.length() != 9) {
                    alertMessage.setText(language.getProperty("InvalidEntry"));
                    searchField.clear();
                    break;
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }

            case "FirstName":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter a name in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByFirstName(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "LastName":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter a name in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByLastName(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "ContactPhone":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter a phone number in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByContactPhone(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "Email":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter an email address in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByEmail(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;

            case "Credentials":
                if (input == null || input.equals("") ||
                        !(input.equals("Administrator") || input.equals("Ordinary"))) {
                    alertMessage.setText("Please enter either: 'Administrator'/'Ordinary' in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByCredentials(input);
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
                if (input == null || input.equals("") ||
                        !(input.equals("Active") || input.equals("Inactive"))) {
                    alertMessage.setText("Please enter either: 'Active'/'Inactive' in the search field");
                    searchField.clear();
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkerByStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }
                break;
        }

        searchField.clear();

        return null;
    }


    protected void showModifyDialog() {

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


    protected int getType() {
        return 0;
    }
}
