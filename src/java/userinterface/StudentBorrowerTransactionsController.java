package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.StudentBorrower;
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph & Ders on 3/11/2017.
 */
public class StudentBorrowerTransactionsController extends TransactionController {

    @FXML private Text alertMessage;
    Core core = Core.getInstance();
    Properties language = core.getLang();

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
                "Borrower Status",
                "Notes",
                "Status");
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
        if (core.getUser().getCredentials().equals("Ordinary"))
            modify.setDisable(true);
        setTableView();
    }

    /**
     *
     */
    protected void setTableView() {
        TableColumn column;
        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<StudentBorrower, String>(property));
            tableView.getColumns().add(column);

        }
    }

    /**
     *
     * @param actionEvent
     */
    public static void changeScene(ActionEvent actionEvent) {

    }

    public static boolean deleteStudentBorrower(ActionEvent actionEvent) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    protected ObservableList querySelector(){

        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "BannerId":
                break;

            case "FirstName":
                break;

            case "LastName":
                break;

            case "ContactPhone":
                break;

            case "Email":
                break;

            case "BorrowerStatus":
                break;

            case "Notes":
                break;

            case "Status":
                break;
        }
        searchField.clear();
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
