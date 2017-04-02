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
import javafx.scene.control.TableRow;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Book;
import models.BookCollection;
import models.Worker;
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
    private TableColumn<Book, String> barcodeColumn = new TableColumn<>(language.getProperty("Barcode"));
    private TableColumn<Book, String> titleColumn = new TableColumn<>(language.getProperty("Title"));
    private TableColumn<Book, String> disciplineColumn = new TableColumn<>(language.getProperty("Discipline"));
    private TableColumn<Book, String> author1Column = new TableColumn<>(language.getProperty("Author1"));
    private TableColumn<Book, String> author2Column = new TableColumn<>(language.getProperty("Author2"));
    private TableColumn<Book, String> author3Column = new TableColumn<>(language.getProperty("Author3"));
    private TableColumn<Book, String> author4Column = new TableColumn<>(language.getProperty("Author4"));
    private TableColumn<Book, String> publisherColumn = new TableColumn<>(language.getProperty("Publisher"));
    private TableColumn<Book, String> yearOfPublicationColumn =
            new TableColumn<>(language.getProperty("YearOfPublication"));
    private TableColumn<Book, String> ISBNColumn = new TableColumn<>(language.getProperty("ISBN"));
    private TableColumn<Book, String> bookConditionColumn = new TableColumn<>(language.getProperty("BookCondition"));
    private TableColumn<Book, String> statusColumn = new TableColumn<>(language.getProperty("Status"));

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                language.getProperty("Barcode"),
                language.getProperty("Title"),
                language.getProperty("Discipline"),
                language.getProperty("Author"),
                language.getProperty("Publisher"),
                language.getProperty("YearOfPublication"),
                language.getProperty("ISBN"),
                language.getProperty("BookCondition"),
                language.getProperty("Status"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchChoice.setItems(itemsSearchChoiceArray());
        searchChoice.getSelectionModel().selectFirst();
        bookHeader.setText(language.getProperty("BookTransactions"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        setTableView();
    }


    protected void setTableView(){

        barcodeColumn.setMinWidth(100);
        titleColumn.setMinWidth(100);
        disciplineColumn.setMinWidth(100);
        author1Column.setMinWidth(100);
        author2Column.setMinWidth(100);
        author3Column.setMinWidth(100);
        author4Column.setMinWidth(100);
        publisherColumn.setMinWidth(100);
        yearOfPublicationColumn.setMinWidth(100);
        ISBNColumn.setMinWidth(100);
        bookConditionColumn.setMinWidth(100);
        statusColumn.setMinWidth(100);

        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BARCODE));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.TITLE));
        disciplineColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.DISCIPLINE));
        author1Column.setCellValueFactory(new PropertyValueFactory<>(DBKey.AUTHOR_1));
        author2Column.setCellValueFactory(new PropertyValueFactory<>(DBKey.AUTHOR_2));
        author3Column.setCellValueFactory(new PropertyValueFactory<>(DBKey.AUTHOR_3));
        author4Column.setCellValueFactory(new PropertyValueFactory<>(DBKey.AUTHOR_4));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.PUBLISHER));
        yearOfPublicationColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.YEAR_OF_PUBLICATION));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.ISBN));
        bookConditionColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.BOOK_CONDITION));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>(DBKey.STATUS));

        tableView.getColumns().add(barcodeColumn);
        tableView.getColumns().add(titleColumn);
        tableView.getColumns().add(disciplineColumn);
        tableView.getColumns().add(author1Column);
        tableView.getColumns().add(author2Column);
        tableView.getColumns().add(author3Column);
        tableView.getColumns().add(author4Column);
        tableView.getColumns().add(publisherColumn);
        tableView.getColumns().add(yearOfPublicationColumn);
        tableView.getColumns().add(ISBNColumn);
        tableView.getColumns().add(bookConditionColumn);
        tableView.getColumns().add(statusColumn);

        tableView.setRowFactory(tableView ->{

            final TableRow<Book> row = new TableRow<>();

            row.setOnMouseClicked((event) -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Book book = row.getItem();
                    core.setModBook(book);
                    if (core.getUser().getCredentials().equals("Administrator")) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifybookview.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                            stage.setScene(scene);
                            stage.setTitle(language.getProperty("modifyBookTitle"));
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException | NullPointerException exception) {
                            exception.printStackTrace();
                        }
                    }
                    else alertMessage.setText(language.getProperty("invalidCredentials"));
                }
            });

            row.hoverProperty().addListener((observable) -> {
                final Book book = row.getItem();
                if (book != null) {
                    Tooltip tp = new Tooltip("at row tool");
                    Tooltip.install(row, tp);
                    tp.setText(book.toolTipToString());
                }
            });
            return row;
        });
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

    @Override
    protected ObservableList querySelector() {

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
        }
        if (input.equals("")) alertMessage.setText(language.getProperty("emptyField"));
        return null;
    }

    public int getType() {
        return 0;
    }
}
