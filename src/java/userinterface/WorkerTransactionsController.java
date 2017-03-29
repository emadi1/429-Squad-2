package userinterface;

import database.DBKey;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
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

    @FXML
    private Text alertMessage;
    private Core core = Core.getInstance();
    private Properties language = core.getLang();

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                language.getProperty("BannerId"),
                language.getProperty("FirstName"),
                language.getProperty("LastName"),
                language.getProperty("ContactPhone"),
                language.getProperty("Email"),
                language.getProperty("Credentials"),
                language.getProperty("DateOfLatestCredentialsStatus"),
                language.getProperty("DateOfHire"),
                language.getProperty("Status")
        );
    }

    @Override
    protected ObservableList<String> dedicatedColumnHeaders() {
        return FXCollections.observableArrayList(
                DBKey.BANNER_ID,
                DBKey.FIRST_NAME,
                DBKey.LAST_NAME,
                DBKey.CONTACT_PHONE,
                DBKey.EMAIL,
                DBKey.CREDENTIALS,
                DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS,
                DBKey.DATE_OF_HIRE,
                DBKey.STATUS
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchChoice.setItems(itemsSearchChoiceArray());
        searchChoice.getSelectionModel().selectFirst();
        workerHeader.setText(language.getProperty("WorkerTransactions"));
        modify.setText(language.getProperty("Modify"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        if (core.getUser().getCredentials().equals("Ordinary")) modify.setDisable(true);
        setTableView();
    }

    protected void setTableView() {
        TableColumn column;
        for (String property : dedicatedColumnHeaders()) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<Worker, String>(property));
            tableView.getColumns().add(column);
        }

        tableView.setRowFactory(tableView ->{

            final TableRow<Worker> row = new TableRow<>();

            row.hoverProperty().addListener((observable) -> {
                final Worker worker = row.getItem();
                Tooltip tp = new Tooltip("at row tool");
                tp.install(row, tp);
                tp.setText(worker.toolTipToString());
            });
                return row;
        });
    }

    @Override
    public void add(ActionEvent actionEvent) throws NullPointerException, IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addworkerview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Brockport Library System");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void modify(ActionEvent actionEvent) throws NullPointerException, LoadException, IOException {
        try {
            Worker worker = (Worker) tableView.getSelectionModel().getSelectedItem();
            core.setModWorker(worker);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyworkerview.fxml"));
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
    }


    protected ObservableList querySelector() {

        WorkerCollection workerCollection = new WorkerCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();

        if (input != null || !input.equals("")) {

            if (search.equals(language.getProperty("BannerId"))) {
                if (input.length() == 9 || isNumeric(input)) {
                    Vector workers = workerCollection.findWorkersByBannerId(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText(language.getProperty("invalidBannerIdFormat"));
            }

            if (search.equals(language.getProperty("FirstName"))) {
                Vector workers = workerCollection.findWorkersByFirstName(input);
                searchField.clear();
                return FXCollections.observableList(workers);
            }

            if (search.equals(language.getProperty("LastName"))) {
                Vector workers = workerCollection.findWorkersByLastName(input);
                searchField.clear();
                return FXCollections.observableList(workers);
            }

            if (search.equals(language.getProperty("ContactPhone"))) {
                Vector workers = workerCollection.findWorkersByContactPhone(input);
                searchField.clear();
                return FXCollections.observableList(workers);
            }

            if (search.equals(language.getProperty("Email"))) {
                Vector workers = workerCollection.findWorkersByEmail(input);
                searchField.clear();
                return FXCollections.observableList(workers);
            }

            if (search.equals(language.getProperty("Credentials"))) {
                if (input.equals(language.getProperty("Administrator")) || input.equals(language.getProperty("Ordinary"))) {
                    Vector workers = workerCollection.findWorkersByCredentials(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText("invalidCredentials");
            }

            if (search.equals(language.getProperty("DateOfLatestCredentialsStatus"))) {
                if (input.length() == 10 && input.charAt(2) == '-' && input.charAt(5) == '-') {
                    Vector workers = workerCollection.findWorkersByLatestCredentialsStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText("invalidDateFormat");
            }

            if (search.equals(language.getProperty("DateOfHire"))) {
                if (input.length() == 10 && input.charAt(2) == '-' && input.charAt(5) == '-') {
                    Vector workers = workerCollection.findWorkersByDateOfHire(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText("invalidDateFormat");
            }

            if (search.equals(language.getProperty("Status"))) {
                if (input.equals(language.getProperty("Active")) || input.equals(language.getProperty("Inactive"))) {
                    Vector workers = workerCollection.findWorkerByStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                } else alertMessage.setText(language.getProperty("invalidStatus"));
            }
        }
        if (input.equals("")) {
            alertMessage.setText("emptyField");
            return null;
        }
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
}
