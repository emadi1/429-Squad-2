package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.StudentBorrower;

/**
 * Created by kevph on 3/11/2017.
 */
public class StudentBorrowerTransactionsController extends SearchController {

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
                "Borrower Status",
                "Notes",
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

            case "BannerID":
                break;

            case "First Name":
                break;

            case "Last Name":
                break;

            case "Phone Number":
                break;

            case "Email":
                break;

            case "Borrower Status":
                break;

            case "Notes":
                break;

            case "Status":
                break;
        }
        searchField.clear();
        return null;
    }

}
