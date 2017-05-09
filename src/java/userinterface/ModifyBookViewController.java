package userinterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.Book;
import utilities.Core;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kevph on 3/26/2017.
 */
public class ModifyBookViewController implements Initializable {

    private Core core = Core.getInstance();
    private Properties language = core.getLang();
    private ObservableList<String> statusList =
            FXCollections.observableArrayList(language.getProperty("Active", language.getProperty("Inactive")));
    private ObservableList<String> bookConditionList =
            FXCollections.observableArrayList(language.getProperty("Good"), language.getProperty("Damaged"));

    @FXML private Text barcode, title, discipline, author1, author2, author3, author4, publisher;
    @FXML private Text yearOfPublication, isbn, bookCondition, suggestedPrice, notes, status, alertMessage;
    @FXML private TextField Barcode, Title, Discipline, Author1, Author2, Author3, Author4;
    @FXML private TextField Publisher, YearOfPublication, ISBN, SuggestedPrice, Notes;
    @FXML private ComboBox<String> BookCondition, Status;
    @FXML private Button submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setText(language.getProperty("Modify"));
        barcode.setText(language.getProperty("PromptBarcode"));
        title.setText(language.getProperty("PromptTitle"));
        discipline.setText(language.getProperty("PromptDiscipline"));
        author1.setText(language.getProperty("PromptAuthor1"));
        author2.setText(language.getProperty("PromptAuthor2"));
        author3.setText(language.getProperty("PromptAuthor3"));
        author4.setText(language.getProperty("PromptAuthor4"));
        publisher.setText(language.getProperty("PromptPublisher"));
        yearOfPublication.setText(language.getProperty("PromptYearOfPublication"));
        isbn.setText(language.getProperty("PromptISBN"));
        bookCondition.setText(language.getProperty("PromptBookCondition"));
        suggestedPrice.setText(language.getProperty("PromptSuggestedPrice"));
        notes.setText(language.getProperty("PromptNotes"));
        status.setText(language.getProperty("PromptStatus"));

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
        try {
            alertMessage.setText("");
            Book book = core.getModBook();
            book.setTitle(Title.getText());
            try {
                book.setDiscipline(Book.generateDiscipline(book.getBarcode().substring(0, 2)));
            } catch (Exception e) {
                book.setDiscipline("None");
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
            if (core.getLanguage().equals("fr_FR")) {
                String price = SuggestedPrice.getText().substring(2, SuggestedPrice.getText().length());
                price = price.replaceAll(",", ".");
                price = price.replaceAll(" ", ",");
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                String usPrice = df.format(Double.parseDouble(price) / 0.94);

                book.setSuggestedPrice(usPrice);
            } else book.setSuggestedPrice(SuggestedPrice.getText().substring(2, SuggestedPrice.getText().length()));
            book.setNotes(Notes.getText());
            if (BookCondition.getValue().equals(language.getProperty("Good"))) book.setBookCondition("Good");
            else book.setBookCondition("Damaged");
            if (Status.getValue().equals(language.getProperty("Active"))) book.setStatus("Active");
            else book.setStatus("Inactive");
            book.update();
            alertMessage.setText(language.getProperty("modifyBookSuccess"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
            core.getTableView().refresh();
        } catch (Exception e) {
            alertMessage.setText(language.getProperty("modifyBookFail"));
            alertMessage.setFill(Paint.valueOf("dcc404"));
        }
    }
}
