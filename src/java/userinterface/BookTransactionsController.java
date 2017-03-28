package userinterface;

import database.DBKey;
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
import models.Book;
import models.BookCollection;
import utilities.Core;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by kevph on 3/11/2017.
 */
public class BookTransactionsController extends TransactionController {

    @FXML private Text alertMessage;
    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private String Barcode = language.getProperty("Barcode");
    private String Title = language.getProperty("Title");
    private String Discipline = language.getProperty("Discipline");
    private String Author = language.getProperty("Author");
    private String Publisher = language.getProperty("Publisher");
    private String YearOfPublication = language.getProperty("YearOfPublication");
    private String ISBN = language.getProperty("ISBN");
    private String BookCondition = language.getProperty("BookCondition");
    private String SuggestedPrice = language.getProperty("SuggestedPrice");
    private String Notes = language.getProperty("Notes");
    private String Status = language.getProperty("Status");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchChoice.setItems(itemsSearchChoiceArray());
        searchChoice.getSelectionModel().selectFirst();
        bookHeader.setText(language.getProperty("BookTransactions"));
        modify.setText(language.getProperty("Modify"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        if (core.getUser().getCredentials().equals("Ordinary")) modify.setDisable(true);
        setTableView();
    }

    @Override
    protected ObservableList<String> dedicatedColumnHeaders() {
        return FXCollections.observableArrayList(
                DBKey.BARCODE,
                DBKey.TITLE,
                DBKey.DISCIPLINE,
                DBKey.AUTHOR_1,
                DBKey.AUTHOR_2,
                DBKey.AUTHOR_3,
                DBKey.AUTHOR_4,
                DBKey.PUBLISHER,
                DBKey.YEAR_OF_PUBLICATION,
                DBKey.ISBN,
                DBKey.CONDITION,
                DBKey.SUGGESTED_PRICE,
                DBKey.NOTES,
                DBKey.STATUS
        );
    }

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                Barcode,
                Title,
                Discipline,
                Author,
                Publisher,
                YearOfPublication,
                ISBN,
                BookCondition,
                Status);
    }

    protected void setTableView(){
        TableColumn column;
        for (String property : dedicatedColumnHeaders()) {
            column = new TableColumn(property);
            column.setMinWidth(60);
            column.setCellValueFactory(new PropertyValueFactory<Book, String>(property));
            tableView.getColumns().add(column);
        }
    }

    public void add(ActionEvent actionEvent) throws NullPointerException, IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addbookview.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            stage.setScene(scene);
            stage.setTitle(language.getProperty("addBookTitle"));
            stage.setResizable(false);
            stage.show();
        } catch (NullPointerException|IOException exception) {
            exception.printStackTrace();
        }
    }

    public void modify(ActionEvent actionEvent) throws NullPointerException, IOException {
        try {
            Book book = (Book) tableView.getItems().get(tableView.getFocusModel().getFocusedIndex());
            String barcode = book.getBarcode();
            core.setModBook(book);
            if (barcode != null) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifybookview.fxml"));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                primaryStage.setScene(scene);
                primaryStage.setTitle("Modify Book");
                primaryStage.setResizable(false);
                primaryStage.show();
            } else {
                alertMessage.setText("Please select a book to modify");
            }
        } catch (NullPointerException|IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected ObservableList querySelector(){
        BookCollection bookCollection = new BookCollection();
        String input = searchField.getText();
        String search = searchChoice.getSelectionModel().getSelectedItem();

        if (input != null || !input.equals("")) {

            if (search.equals(language.getProperty("Barcode"))) {
                if (input.length() == 5 && isNumeric(input)) {
                    Vector books = bookCollection.findBooksByBarcode(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(books);
                } else alertMessage.setText(language.getProperty("invalidBarcodeLength"));
            }

            if (search.equals(language.getProperty("Title"))) {
                Vector books = bookCollection.findBooksByTitle(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(books);
            }

            if (search.equals(language.getProperty("Discipline"))) {
                Vector books = bookCollection.findBooksByDiscipline(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(books);
            }

            if (search.equals(language.getProperty("Author"))) {
                Vector books = bookCollection.findBooksByAuthor(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(books);
            }

            if (search.equals(language.getProperty("YearOfPublication"))) {
                if (input.length() <= 4 && isNumeric(input)) {
                    Vector books = bookCollection.findBooksByPublicationYear(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(books);
                } else alertMessage.setText(language.getProperty("yearFormat"));
            }

            if (search.equals(language.getProperty("ISBN"))) {
                Vector books = bookCollection.findBooksByISBN(input);
                searchField.clear();
                alertMessage.setText("");
                return FXCollections.observableList(books);
            }

            if (search.equals(language.getProperty("BookCondition"))) {
                if (input.equals(language.getProperty("Good")) || input.equals(language.getProperty("Damaged"))) {
                    Vector books = bookCollection.findBooksByBookCondition(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(books);
                } else alertMessage.setText(language.getProperty("invalidCondition"));
            }

            if (search.equals(language.getProperty("Status"))) {
                if (input.equals(language.getProperty("Active")) || input.equals(language.getProperty("Inactive"))) {
                    Vector books = bookCollection.findBooksByStatus(input);
                    searchField.clear();
                    alertMessage.setText("");
                    return FXCollections.observableList(books);
                } else alertMessage.setText(language.getProperty("invalidStatus"));
            }
        } return null;
    }

    public int getType() {
        return 0;
    }

    public void showModifyDialog() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifybookview.fxml"));
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
