package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    @FXML private Text alertMessage;
    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private TableColumn<Worker, String> bannerIdColumn = new TableColumn<>(language.getProperty("BannerId"));
    private TableColumn<Worker, String> firstNameColumn = new TableColumn<>(language.getProperty("FirstName"));
    private TableColumn<Worker, String> lastNameColumn = new TableColumn<>(language.getProperty("LastName"));
    private TableColumn<Worker, String> contactPhoneColumn = new TableColumn<>(language.getProperty("ContactPhone"));
    private TableColumn<Worker, String> emailColumn = new TableColumn<>(language.getProperty("Email"));
    private TableColumn<Worker, String> credentialsColumn = new TableColumn<>(language.getProperty("Credentials"));
    private TableColumn<Worker, String> dateOfLatestCredentialsStatusColumn =
            new TableColumn<>(language.getProperty("DateOfLatestCredentialsStatus"));
    private TableColumn<Worker, String> dateOfHireColumn = new TableColumn<>(language.getProperty("DateOfHire"));
    private TableColumn<Worker, String> statusColumn = new TableColumn<>(language.getProperty("Status"));

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
    public void initialize(URL location, ResourceBundle resources) {

        searchChoice.setItems(itemsSearchChoiceArray());
        searchChoice.getSelectionModel().selectFirst();
        workerHeader.setText(language.getProperty("WorkerTransactions"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        try {
            setTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setTableView() throws IOException {

        bannerIdColumn.setMinWidth(100);
        firstNameColumn.setMinWidth(100);
        lastNameColumn.setMinWidth(100);
        contactPhoneColumn.setMinWidth(100);
        emailColumn.setMinWidth(100);
        credentialsColumn.setMinWidth(100);
        dateOfLatestCredentialsStatusColumn.setMinWidth(100);
        dateOfHireColumn.setMinWidth(100);
        statusColumn.setMinWidth(100);

        bannerIdColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BANNER_ID));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.FIRST_NAME));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.LAST_NAME));
        contactPhoneColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CONTACT_PHONE));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.EMAIL));
        credentialsColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CREDENTIALS));
        dateOfLatestCredentialsStatusColumn.setCellValueFactory
                (new PropertyValueFactory<>(DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS));
        dateOfHireColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.DATE_OF_HIRE));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.STATUS));

        tableView.getColumns().add(bannerIdColumn);
        tableView.getColumns().add(firstNameColumn);
        tableView.getColumns().add(lastNameColumn);
        tableView.getColumns().add(contactPhoneColumn);
        tableView.getColumns().add(emailColumn);
        tableView.getColumns().add(credentialsColumn);
        tableView.getColumns().add(dateOfLatestCredentialsStatusColumn);
        tableView.getColumns().add(dateOfHireColumn);
        tableView.getColumns().add(statusColumn);

        tableView.setRowFactory(tableView ->{

            final TableRow<Worker> row = new TableRow<>();

            row.setOnMouseClicked((event) -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Worker worker = row.getItem();
                    core.setModWorker(worker);
                    System.out.println(core.getUser().getCredentials());
                    if (core.getUser().getCredentials().equals("Administrator")) {
                        try {
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
                    } else alertMessage.setText(language.getProperty("invalidCredentials"));
                }
            });

            row.hoverProperty().addListener((observable) -> {
                final Worker worker = row.getItem();
                if (worker != null) {
                    Tooltip tp = new Tooltip("at row tool");
                    Tooltip.install(row, tp);
                    tp.setText(worker.toolTipToString());
                }
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
            primaryStage.setTitle(language.getProperty("addWorkerTitle"));
            primaryStage.setResizable(false);
            primaryStage.show();

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
                if (input.length() == 9 && isNumeric(input)) {
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
        if (input.equals("")) alertMessage.setText(language.getProperty("emptyField"));
        return null;
    }

    public int getType() {
        return 0;
    }
}
