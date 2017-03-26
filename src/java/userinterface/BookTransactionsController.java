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

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    @FXML private Text alertMessage;
    private String Barcode = language.getProperty("Barcode");
    private String Title = language.getProperty("Title");
    private String Discipline = language.getProperty("Discipline");
    private String Author1 = language.getProperty("Author1");
    private String Author2 = language.getProperty("Author2");
    private String Author3 = language.getProperty("Author3");
    private String Author4 = language.getProperty("Author4");
    private String Publisher = language.getProperty("Publisher");
    private String YearOfPublication = language.getProperty("YearOfPublication");
    private String ISBN = language.getProperty("ISBN");
    private String BookCondition = language.getProperty("BookCondition");
    private String SuggestedPrice = language.getProperty("SuggestedPrice");
    private String Notes = language.getProperty("Notes");
    private String Status = language.getProperty("Status");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        properties = itemsSearchChoiceArray();
        searchChoice.setItems(properties);
        searchChoice.getSelectionModel().selectFirst();
        bookHeader.setText(language.getProperty("WorkerTransactions"));
        modify.setText(language.getProperty("Modify"));
        add.setText(language.getProperty("Add"));
        search.setText(language.getProperty("Search"));
        if (core.getUser().getCredentials().equals("Ordinary")) {
            modify.setDisable(true);
        }
        setTableView();
    }

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                Barcode,
                Title,
                Discipline,
                Author1,
                Author2,
                Author3,
                Author4,
                Publisher,
                YearOfPublication,
                ISBN,
                BookCondition,
                SuggestedPrice,
                Notes,
                Status);
    }

    protected void setTableView(){
        TableColumn column;
        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(60);
            column.setCellValueFactory(new PropertyValueFactory<Book, String>(property));
            tableView.getColumns().add(column);
        }
    }

    public void add(ActionEvent actionEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addbookview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Add Book");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modify(ActionEvent actionEvent) throws IOException {
        try {
            Core core = Core.getInstance();
            Book book = (Book) tableView.getItems().get(tableView.getFocusModel().getFocusedIndex());
            System.out.println(book.toString());
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
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    protected ObservableList querySelector(){
        String input = searchField.getText();
        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "Barcode":
                if (input == null || input.equals("") || !isNumeric(input)) {
                    alertMessage.setText("Please enter a numeric barcode");
                    searchField.clear();
                } else if (input.length() <= 3) {
                    alertMessage.setText("Barcode must be at least 3 digits long");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByBarcode(input);
                    searchField.clear();
                    return FXCollections.observableArrayList(books);
                }
                break;

            case "Title":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter a title in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByTitle(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Discipline":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter a discipline in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByDiscipline(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Author1":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter an author in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByAuthor(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Author2":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter an author in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByAuthor(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Author3":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter an author in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByAuthor(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Author4":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter an author in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByAuthor(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Publisher":
                if (input == null || input.equals("")) {
                    alertMessage.setText("Please enter a publisher in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByPublisher(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "PublicationYear":
                if (input == null || input.equals("") || !isNumeric(input)) {
                    alertMessage.setText("Please enter a date in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByPublicationYear(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "ISBN":
                if (input == null || input.equals("") || !isNumeric(input)) {
                    alertMessage.setText("Please enter a ISBN number in search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByISBN(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;

            case "Status":
                if (input == null || input.equals("") ||
                        !(input.equals("Status") || input.equals("Inactive"))) {
                    alertMessage.setText("Please enter either: 'Active'/'Inactive' in the search field");
                    searchField.clear();
                } else {
                    BookCollection bookCollection = new BookCollection();
                    Vector books = bookCollection.findBooksByStatus(input);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
                break;
        }
        searchField.clear();
        return null;
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
