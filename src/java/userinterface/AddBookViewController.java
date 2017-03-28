package userinterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Book;
import models.BookBarcodePrefixCollection;
import models.BookCollection;
import utilities.Core;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import static models.Book.generateDiscipline;


/**
 * Created by kevph on 3/25/2017.
 */
public class AddBookViewController implements Initializable {

    private Properties lang = Core.getInstance().getLang();
    private BookCollection bookCollection = new BookCollection();
    private ObservableList<String> conditionList = FXCollections.observableArrayList(lang.getProperty("Good"), lang.getProperty("Damaged"));
    private ObservableList<String> statusList = FXCollections.observableArrayList(lang.getProperty("Active"), lang.getProperty("Inactive"));

    @FXML private Text barcode, title, author1, author2, author3, author4, publisher, yearOfPublication;
    @FXML private Text isbn, bookCondition, suggestedPrice, notes, status, alertMessage;
    @FXML private TextField Barcode, Title, Author1, Author2, Author3, Author4, Publisher;
    @FXML private TextField YearOfPublication, ISBN, SuggestedPrice, Notes;
    @FXML private ComboBox<String> BookCondition, Status;
    @FXML private Button submit;

    ArrayList<TextField> textFieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        barcode.setText(lang.getProperty("barcode"));
        title.setText(lang.getProperty("title"));
        author1.setText(lang.getProperty("author1"));
        author2.setText(lang.getProperty("author2"));
        author3.setText(lang.getProperty("author3"));
        author4.setText(lang.getProperty("author4"));
        publisher.setText(lang.getProperty("publisher"));
        yearOfPublication.setText(lang.getProperty("yearOfPublication"));
        isbn.setText(lang.getProperty("isbn"));
        bookCondition.setText(lang.getProperty("bookCondition"));
        suggestedPrice.setText(lang.getProperty("suggestedPrice"));
        notes.setText(lang.getProperty("notes"));
        status.setText(lang.getProperty("status"));
        submit.setText(lang.getProperty("Add"));

        textFieldList.add(Barcode);
        textFieldList.add(Title);
        textFieldList.add(Author1);
        textFieldList.add(Author2);
        textFieldList.add(Author3);
        textFieldList.add(Author4);
        textFieldList.add(Publisher);
        textFieldList.add(YearOfPublication);
        textFieldList.add(ISBN);
        BookCondition.setItems(conditionList);
        BookCondition.setValue(lang.getProperty("Good"));
        textFieldList.add(SuggestedPrice);
        textFieldList.add(Notes);
        Status.setItems(statusList);
        Status.setValue(lang.getProperty("Active"));
    }

    public void submit(ActionEvent actionEvent) {

        Properties properties = new Properties();
        int count = bookCollection.findBooksByBarcode(properties.getProperty("Barcode")).size();

        if (Barcode.getText().length() != 5) {
            alertMessage.setText(lang.getProperty("invalidBarcodeLength"));
            return;
        }

        if (YearOfPublication.getText().length() != 4) {
            alertMessage.setText(lang.getProperty("yearFormat"));
            return;
        }

        if (Title.getText().equals("") || Author1.getText().equals("") || Publisher.getText().equals("")
                || YearOfPublication.getText().equals("") || ISBN.getText().equals("")
                || SuggestedPrice.getText().equals("")) {
            alertMessage.setText(lang.getProperty("completeFields"));
            return;
        } else {
            properties.put(BookCondition.getId(), BookCondition.getSelectionModel().getSelectedItem());
            properties.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
            properties.put("Discipline", generateDiscipline(Barcode.getText().substring(0, 2)));
            for (TextField textField : textFieldList)
                properties.put(textField.getId(), textField.getText());
        }

        if (count == 0) {
            Book newBook = new Book(properties);
            newBook.insert();
            alertMessage.setText(lang.getProperty("addBookSuccess"));
        } else {
            alertMessage.setText(lang.getProperty("existingBarcode") + properties.getProperty("Barcode"));
        }

        for (TextField t : textFieldList) { t.clear(); }
        BookCondition.setValue(lang.getProperty("Good"));
        Status.setValue(lang.getProperty("Active"));
    }
}
