package userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import models.*;
import utilities.Core;
import database.DBKey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Book;
import utilities.Core;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.Properties;

/**
 * Created by kevph on 3/7/2017.
 */
public class ListCheckedBooksController extends TransactionController{
    @FXML
    private Text alertMessage;
    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private TableColumn<Rental, String> barcodeColumn = new TableColumn<>(language.getProperty("Id"));
    private TableColumn<Rental, String> borrowerColumn = new TableColumn<>(language.getProperty("BorrowerId"));
    private TableColumn<Rental, String> bookIdColumn = new TableColumn<>(language.getProperty("BookId"));
    private TableColumn<Rental, String> checkoutDateColumn = new TableColumn<>(language.getProperty("CheckoutDate"));
    private TableColumn<Rental, String> checkoutWorkerIdColumn = new TableColumn<>(language.getProperty("CheckoutWorkerId"));
    private TableColumn<Rental, String> dueDateColumn = new TableColumn<>(language.getProperty("DueDate"));
    private TableColumn<Rental, String> checkinDateColumn = new TableColumn<>(language.getProperty("CheckInDate"));
    private TableColumn<Rental, String> checkinWorkerIdColumn = new TableColumn<>(language.getProperty("CheckinWorkerId"));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
    }

    @Override
    protected void add(ActionEvent actionEvent) throws NullPointerException, IOException {

    }

    @Override
    protected ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                language.getProperty("Barcode"),
                language.getProperty("Borrower"),
                language.getProperty("Book ID"),
                language.getProperty("Checkout Date"),
                language.getProperty("Checkout Worker"),
                language.getProperty("Due Date"),
                language.getProperty("Checkin Date"),
                language.getProperty("Checkin Worker"));

    }

    @Override
    protected void setTableView() {
        barcodeColumn.setMinWidth(100);
        borrowerColumn.setMinWidth(100);
        bookIdColumn.setMinWidth(100);
        checkoutDateColumn.setMinWidth(100);
        checkoutWorkerIdColumn.setMinWidth(100);
        dueDateColumn.setMinWidth(100);
        checkinDateColumn.setMinWidth(100);
        checkinWorkerIdColumn.setMinWidth(100);


        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.ID));
        borrowerColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BORROWER_ID));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BOOK_ID));
        checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_DATE));
        checkoutWorkerIdColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_OUT_WORKER_ID));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.DUE_DATE));
        checkinDateColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_IN_DATE));
        checkinWorkerIdColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.CHECK_IN_WORKER_ID));


        tableView.getColumns().add(barcodeColumn);
        tableView.getColumns().add(borrowerColumn);
        tableView.getColumns().add(bookIdColumn);
        tableView.getColumns().add(checkoutDateColumn);
        tableView.getColumns().add(checkoutWorkerIdColumn);
        tableView.getColumns().add(dueDateColumn);
        tableView.getColumns().add(checkinDateColumn);
        tableView.getColumns().add(checkinWorkerIdColumn);


        tableView.setRowFactory(tableView -> {

            final TableRow<Book> row = new TableRow<>();
            return row;

        });

    }

    @Override
    protected ObservableList querySelector() {

        RentalCollection rentalCollection = new RentalCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();

        if (input != null || !input.equals("")) {

            if (search.equals(language.getProperty("Id"))) {
                if (input.length() == 5 && isNumeric(input)) {
                    Vector rentals = rentalCollection.findRentalsById(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(rentals);
                }
            }

            if (search.equals(language.getProperty("BorrowerId"))) {
                Vector rentals = rentalCollection.findRentalsByBorrowerId(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(rentals);
            }

            if (search.equals(language.getProperty("BookId"))) {
                Vector rentals = rentalCollection.findRentalsByBookId(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(rentals);
            }

            if (search.equals(language.getProperty("CheckoutDate"))) {
                Vector rentals = rentalCollection.findRentalsByCheckOutDate(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(rentals);
            }

            if (search.equals(language.getProperty("CheckoutWorkerId"))) {
                if (input.length() <= 4 && isNumeric(input)) {
                    Vector rentals = rentalCollection.findRentalsByCheckOutWorkerId(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(rentals);
                }
            }

            if (search.equals(language.getProperty("DueDate"))) {
                Vector rentals = rentalCollection.findRentalsByDueDate(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(rentals);
            }

            if (search.equals(language.getProperty("CheckInDate"))) {
                if (input.equals(language.getProperty("Good")) || input.equals(language.getProperty("Damaged"))) {
                    Vector rentals = rentalCollection.findRentalsByCheckInDate(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(rentals);
                }
            }

            if (search.equals(language.getProperty("CheckinWorkerId"))) {
                if (input.equals(language.getProperty("Active")) || input.equals(language.getProperty("Inactive"))) {
                    Vector rentals = rentalCollection.findRentalsByCheckInWorkerId(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(rentals);
                }
            }
        }
        if (input.equals("")) alertMessage.setText(language.getProperty("emptyField"));
        return null;
    }
}
