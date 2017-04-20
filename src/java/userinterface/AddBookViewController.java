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

    private ArrayList<TextField> textFieldList;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set Text's text
        barcode.setText(lang.getProperty("PromptBarcode"));
        title.setText(lang.getProperty("PromptTitle"));
        author1.setText(lang.getProperty("PromptAuthor1"));
        author2.setText(lang.getProperty("PromptAuthor2"));
        author3.setText(lang.getProperty("PromptAuthor3"));
        author4.setText(lang.getProperty("PromptAuthor4"));
        publisher.setText(lang.getProperty("PromptPublisher"));
        yearOfPublication.setText(lang.getProperty("PromptYearOfPublication"));
        isbn.setText(lang.getProperty("PromptISBN"));
        bookCondition.setText(lang.getProperty("PromptBookCondition"));
        suggestedPrice.setText(lang.getProperty("PromptSuggestedPrice"));
        notes.setText(lang.getProperty("PromptNotes"));
        status.setText(lang.getProperty("PromptStatus"));
        submit.setText(lang.getProperty("Add"));

        // Set PromptText in text fields
        Barcode.setPromptText(lang.getProperty("Barcode"));
        Title.setPromptText(lang.getProperty("Title"));
        Author1.setPromptText(lang.getProperty("Author1"));
        Author2.setPromptText(lang.getProperty("Author2"));
        Author3.setPromptText(lang.getProperty("Author3"));
        Author4.setPromptText(lang.getProperty("Author4"));
        Publisher.setPromptText(lang.getProperty("Publisher"));
        YearOfPublication.setPromptText(lang.getProperty("YearOfPublication"));
        ISBN.setPromptText(lang.getProperty("ISBN"));
        SuggestedPrice.setPromptText(lang.getProperty("SuggestedPrice"));
        Notes.setPromptText(lang.getProperty("Notes"));
        Status.setItems(statusList);
        BookCondition.setItems(conditionList);

        // Add TextFields to ArrayList
        textFieldList = new ArrayList<>();
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
        textFieldList.add(Notes);
        Status.setItems(statusList);
        Status.setValue(lang.getProperty("Active"));
    }

    public void submit(ActionEvent actionEvent) {

        Properties properties = new Properties();
        int count = 0;
        try {
            count = bookCollection.findBooksByBarcode(properties.getProperty("Barcode")).size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Barcode.getText().length() != 5) {
            alertMessage.setText(lang.getProperty("invalidBarcodeLength"));
            return;
        }

        if (YearOfPublication.getText().length() != 4) {
            alertMessage.setText(lang.getProperty("yearFormat"));
            return;
        }

        if (Title.getText().length() > 64) {
            alertMessage.setText("Title length too large.");
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

        if (BookCondition.getValue().equals(lang.getProperty("Good"))) properties.put("BookCondition", "Good");
        else properties.put("BookCondition", "Damaged");

        if (Core.getInstance().getLanguage().equals("fr_FR")) {
            String price = SuggestedPrice.getText();
            price = price.replaceAll(",", ".");
            double euroToDollar = Double.parseDouble(price) / 0.94;
            String formattedPrice = "" + euroToDollar;
            properties.put(SuggestedPrice.getId(), formattedPrice);
        } else properties.put(SuggestedPrice.getId(), SuggestedPrice.getText());

        if (Status.getValue().equals(lang.getProperty("Active"))) properties.put("Status", "Active");
        else properties.put("Status", "Inactive");

        if (count == 0) {
            Book newBook = new Book(properties);
            newBook.insert();
            for (TextField t : textFieldList) { t.clear(); }
            BookCondition.setValue(lang.getProperty("Good"));
            Status.setValue(lang.getProperty("Active"));
            alertMessage.setText(lang.getProperty("addBookSuccess"));
        } else alertMessage.setText(lang.getProperty("existingBarcode") + properties.getProperty("Barcode"));
    }
}
