package userinterface;

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
import models.Worker;
import models.WorkerCollection;

import java.io.IOException;
import java.util.Vector;

public class WorkerTransactionsController extends SearchController {

    @FXML
    private Text alertMessage;

    /**
     *
     * @return
     */
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

    /**
     *
     */
    protected void setTableView() {
        TableColumn column;
        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<Worker, String>(property));
            tableView.getColumns().add(column);
        }
    }

    /**
     *
     * @param actionEvent
     */
    public static void changeScene(ActionEvent actionEvent) {

    }

    /**
     *
     * @param actionEvent
     * @return
     */
    public static void delete(ActionEvent actionEvent) {

    }

    public static void modify(ActionEvent actionEvent) {

    }

    public void addWorker(ActionEvent actionEvent) {

        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("workerdatafieldview.fxml"));
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
                        (!credentials.equals("Administrator") || !credentials.equals("Ordinary"))) {
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
                        (!status.equals("Active") || !status.equals("Inactive"))) {
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
}
