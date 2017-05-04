package userinterface;

import database.DBKey;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class ListCheckedBooksController implements Initializable {
    @FXML
    private TableView tableView;
    private Properties language = Core.getInstance().getLang();

    private TableColumn<Book, String> bookTitle = new TableColumn<>(language.getProperty("Title"));
    private TableColumn<StudentBorrower, String> borrowerName = new TableColumn<>(language.getProperty("BorrowerName"));
    private TableColumn<Rental, String> dueDate = new TableColumn<>(language.getProperty("DueDate"));
    private TableColumn<Rental, String> checkOutDate = new TableColumn<>(language.getProperty("CheckOutDate"));
    private TableColumn<Rental, String> id = new TableColumn<>(language.getProperty("Id"));

    public void setTableView() {
        bookTitle.setMinWidth(150);
        borrowerName.setMinWidth(150);
        dueDate.setMinWidth(150);
        checkOutDate.setMinWidth(150);
        id.setMinWidth(150);

        bookTitle.setCellValueFactory(new PropertyValueFactory<>(DBKey.TITLE));
        borrowerName.setCellValueFactory(new PropertyValueFactory<>(DBKey.BORRWER_NAME));
        dueDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.DUE_DATE));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_DATE));
        id.setCellValueFactory(new PropertyValueFactory<>(DBKey.ID));

        tableView.getColumns().add(id);
        tableView.getColumns().add(borrowerName);
        tableView.getColumns().add(bookTitle);
        tableView.getColumns().add(checkOutDate);
        tableView.getColumns().add(dueDate);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RentalCollection rentalCollection = new RentalCollection();
        Vector checkoutBook = rentalCollection.findAllCheckoutBooks();
        System.out.println(checkoutBook);

        setTableView();
        tableView.setItems(FXCollections.observableList(rentalCollection.findAllCheckoutBooks()));
        tableView.refresh();
    }
}