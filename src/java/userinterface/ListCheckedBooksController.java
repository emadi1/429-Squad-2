package userinterface;

import database.DBKey;
import database.Persistable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import utilities.Core;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by kevph on 3/7/2017.
 */

public class ListCheckedBooksController extends Persistable implements Initializable {

    @FXML private TableView tableView;
    private Properties language = Core.getInstance().getLang();
    private TableColumn<RentedBook ,String> id = new TableColumn<>(language.getProperty("Id"));
    private TableColumn<RentedBook ,String> bookId = new TableColumn<>(language.getProperty("BookId"));
    private TableColumn<RentedBook ,String> bookTitle = new TableColumn<>(language.getProperty("Title"));
    private TableColumn<RentedBook ,String> borrowerId = new TableColumn<>(language.getProperty("BorrowerId"));
    private TableColumn<RentedBook ,String> borrowerName = new TableColumn<>(language.getProperty("BorrowerName"));
    private TableColumn<RentedBook ,String> checkOutDate = new TableColumn<>(language.getProperty("CheckOutDate"));
    private TableColumn<RentedBook ,String> dueDate = new TableColumn<>(language.getProperty("DueDate"));

    private void setTableView() {
        id.setMinWidth(150);
        bookId.setMinWidth(150);
        bookTitle.setMinWidth(150);
        borrowerId.setMinWidth(150);
        borrowerName.setMinWidth(150);
        checkOutDate.setMinWidth(150);
        dueDate.setMinWidth(150);

        id.setCellValueFactory(new PropertyValueFactory<>(DBKey.ID));
        bookId.setCellValueFactory(new PropertyValueFactory<>(DBKey.BOOK_ID));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>(DBKey.TITLE));
        borrowerId.setCellValueFactory(new PropertyValueFactory<>(DBKey.BORROWER_ID));
        borrowerName.setCellValueFactory(new PropertyValueFactory<>(DBKey.BORROWER_NAME));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_DATE));
        dueDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.DUE_DATE));

        tableView.getColumns().add(id);
        tableView.getColumns().add(bookId);
        tableView.getColumns().add(bookTitle);
        tableView.getColumns().add(borrowerId);
        tableView.getColumns().add(borrowerName);
        tableView.getColumns().add(checkOutDate);
        tableView.getColumns().add(dueDate);

        RentedBookCollection rentedBookCollection = new RentedBookCollection();
        Vector<RentedBook> rentedBooks = rentedBookCollection.findAllCheckedOutBooks();
        tableView.setItems(FXCollections.observableList(rentedBookCollection.findAllCheckedOutBooks()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        tableView.refresh();
    }
}