package userinterface;

import database.SQLInsertStatement;
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
import models.BookBarcodePrefix;
import models.BookBarcodePrefixCollection;
import models.BookCollection;
import utilities.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;


/**
 * Created by kevph on 3/25/2017.
 */
public class AddBookViewController implements Initializable {

    private ObservableList<String> conditionList = FXCollections.observableArrayList("Good", "Damaged");
    private ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    Core core = Core.getInstance();
    Properties lang = core.getLang();
    Properties props = new Properties();
    BookCollection bookCollection = new BookCollection();
    BookBarcodePrefixCollection bookBarcodePrefixCollection = new BookBarcodePrefixCollection();

    @FXML private Text barcode;
    @FXML private Text title;
    @FXML private Text author1;
    @FXML private Text author2;
    @FXML private Text author3;
    @FXML private Text author4;
    @FXML private Text publisher;
    @FXML private Text yearOfPublication;
    @FXML private Text isbn;
    @FXML private Text bookCondition;
    @FXML private Text suggestedPrice;
    @FXML private Text notes;
    @FXML private Text status;
    @FXML private Text alertMessage;
    @FXML private TextField Barcode;
    @FXML private TextField Title;
    @FXML private TextField Author1;
    @FXML private TextField Author2;
    @FXML private TextField Author3;
    @FXML private TextField Author4;
    @FXML private TextField Publisher;
    @FXML private TextField YearOfPublication;
    @FXML private TextField ISBN;
    @FXML private ComboBox<String> BookCondition;
    @FXML private TextField SuggestedPrice;
    @FXML private TextField Notes;
    @FXML private ComboBox<String> Status;
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
        BookCondition.setValue("Good");
        textFieldList.add(SuggestedPrice);
        textFieldList.add(Notes);
        Status.setItems(statusList);
        Status.setValue("Status");
    }

    public void submit(ActionEvent actionEvent) {

        if (Barcode.getText().length() != 5) {
            alertMessage.setText("Invalid Barcode Length");
            return;
        }

        props.put(BookCondition.getId(), BookCondition.getSelectionModel().getSelectedItem());
        props.put(Status.getId(), Status.getSelectionModel().getSelectedItem());

        if (Barcode.getText().equals("") || Title.getText().equals("")
                || Author1.getText().equals("") || Publisher.getText().equals("")
                || YearOfPublication.getText().equals("") || ISBN.getText().equals("")
                || SuggestedPrice.getText().equals("")) {
            alertMessage.setText("Please fill out all necessary fields");
            return;
        }

        try {
            props.put("Discipline", bookBarcodePrefixCollection.generateDiscipline(Barcode.getText()));
        } catch (Exception e) {
            alertMessage.setText("");
            props.put("Discipline", "None");
        }

        int count = bookCollection.findBooksByBarcode(props.getProperty("Barcode")).size();
        if (count == 0) {
            Book newBook = new Book(props);
            System.out.println(newBook.toString());
            newBook.insert();
            alertMessage.setText("Book has been submitted");
        } else {
            alertMessage.setText("Book with barcode: " + props.getProperty("Barcode") + " already exists");
        }
        for (TextField textField : textFieldList) {
            textField.clear();
        }
    }






















}
