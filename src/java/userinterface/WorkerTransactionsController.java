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
                "BannerID",
                "First Name",
                "Last Name",
                "Phone Number",
                "Email",
                "Credentials",
                "Date of Hire",
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

            case "BannerID":
                String bannerId = searchField.getText();
                if (bannerId == null || bannerId.equals("") || !isNumeric(bannerId)) {
                    alertMessage.setText("Please enter a numeric BannerID in the search field");
                    searchField.clear();
                    break;
                } else {
                    WorkerCollection workerCollection = new WorkerCollection();
                    Vector workers = workerCollection.findWorkersByBannerId(bannerId);
                    searchField.clear();
                    return FXCollections.observableList(workers);
                }

            case "First Name":
                break;

            case "Last Name":
                break;

            case "Phone Number":
                break;

            case "Email":
                break;

            case "Credentials":
                break;

            case "Date of Hire":
                break;

            case "Status":
                break;
        }
        searchField.clear();
        return null;
    }
}
