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
import utilities.Core;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/26/2017.
 */
public class ModifyBookViewController implements Initializable {

    Core core = Core.getInstance();
    Properties language = core.getLang();
    private ObservableList<String> statusList = FXCollections.observableArrayList(language.getProperty("Active", language.getProperty("Inactive")));
    private ObservableList<String> bookConditionList = FXCollections.observableArrayList(language.getProperty("Good"), language.getProperty("Damagaed"));

    @FXML private Button submit;
    @FXML private Text barcode, title, discipline, author1;
    @FXML private Text author2, author3, author4, publisher;
    @FXML private Text yearOfPublication, isbn, bookCondition;
    @FXML private Text suggestedPrice, notes, status, alertMessage;
    @FXML private TextField Barcode, Title, Discipline, Author1, Author2, Author3, Author4;
    @FXML private TextField Publisher, YearOfPublication, ISBN, SuggestedPrice, Notes;
    @FXML private ComboBox<String> BookCondition, Status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setText(language.getProperty("Modify"));
        barcode.setText(language.getProperty("barcode"));
        title.setText(language.getProperty("title"));
        discipline.setText(language.getProperty("discipline"));
        author1.setText(language.getProperty("author1"));
        author2.setText(language.getProperty("author2"));
        author3.setText(language.getProperty("author3"));
        author4.setText(language.getProperty("author4"));
        publisher.setText(language.getProperty("publisher"));
        yearOfPublication.setText(language.getProperty("yearOfPublication"));
        isbn.setText(language.getProperty("isbn"));
        bookCondition.setText(language.getProperty("bookCondition"));
        suggestedPrice.setText(language.getProperty("suggestedPrice"));
        notes.setText(language.getProperty("notes"));
        status.setText(language.getProperty("status"));

        Barcode.setText(core.getModBook().getBarcode());
        Barcode.setDisable(true);
        Title.setText(core.getModBook().getTitle());
        Discipline.setText(core.getModBook().getDiscipline());
        Discipline.setDisable(true);
        Author1.setText(core.getModBook().getAuthor1());
        Author2.setText(core.getModBook().getAuthor2());
        Author3.setText(core.getModBook().getAuthor3());
        Author4.setText(core.getModBook().getAuthor4());
        Publisher.setText(core.getModBook().getPublisher());
        YearOfPublication.setText(core.getModBook().getYearOfPublication());
        ISBN.setText(core.getModBook().getISBN());
        BookCondition.setValue(core.getModBook().getBookCondition());
        BookCondition.setItems(bookConditionList);
        SuggestedPrice.setText(core.getModBook().getSuggestedPrice());
        Notes.setText(core.getModBook().getNotes());
        Status.setValue(core.getModBook().getStatus());
        Status.setItems(statusList);
    }

    public void submit(ActionEvent actionEvent) {
        alertMessage.setText("");
        Book book = core.getModBook();
        book.setTitle(Title.getText());
        try {
            book.setDiscipline(Book.generateDiscipline(book.getBarcode().substring(0, 2)));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        book.setAuthor1(Author1.getText());
        book.setAuthor2(Author2.getText());
        book.setAuthor3(Author3.getText());
        book.setAuthor4(Author4.getText());
        book.setPublisher(Publisher.getText());
        book.setYearOfPublication(YearOfPublication.getText());
        book.setISBN(ISBN.getText());
        book.setBookCondition(BookCondition.getValue());
        book.setSuggestedPrice(SuggestedPrice.getText());
        book.setNotes(Notes.getText());
        book.setStatus(Status.getValue());
        book.update();
        alertMessage.setText(language.getProperty("modifyBookSuccess"));
    }
}
