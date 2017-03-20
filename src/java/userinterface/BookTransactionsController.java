package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.awt.print.Book;

/**
 * Created by kevph on 3/11/2017.
 */
public class BookTransactionsController extends SearchController {

    @FXML
    private Text alertMessage;

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                "Barcode",
                "Title",
                "Discipline",
                "Author",
                "Publisher",
                "Publication Year",
                "ISBN",
                "Suggested Price",
                "Status");
    }

    /**
     *
     */
    protected void setTableView(){
        TableColumn column;
        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<Book, String>(property));
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
    public static boolean deleteBook(ActionEvent actionEvent) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    protected ObservableList querySelector(){

        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "Barcode":
                break;

            case "Title":
                break;

            case "Discipline":
                break;

            case "Author":
                break;

            case "Publisher":
                break;

            case "Publication Year":
                break;

            case "ISBN":
                break;

            case "Suggested Price":
                break;

            case "Status":
                break;
        }
        searchField.clear();
        return null;
    }
}
