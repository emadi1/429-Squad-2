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
        Properties props = new Properties();
        BookCollection bookCollection = new BookCollection();
        alertMessage.setText("");
        for (TextField textField : textFieldList) {
            if (textField.getText().equals("") ) {
                alertMessage.setText("Please complete all fields");
                return;
            } else {
                props.put(textField.getId(), textField.getText());
            }
        }
        try {
            String prefix = props.getProperty("Barcode").substring(0, 2);
            BookBarcodePrefixCollection bookBarcodePrefixCollection = new BookBarcodePrefixCollection();
            BookBarcodePrefix bookBarcodePrefix = (BookBarcodePrefix) bookBarcodePrefixCollection.findBarcodePrefixValueByPrefix(prefix).get(0);
            String discipline = bookBarcodePrefix.getDiscipline();
            props.put("Discipline", discipline);
        } catch (Exception e) {
            alertMessage.setText("");
            props.put("Discipline", "None");
        }
        props.put(BookCondition.getId(), BookCondition.getSelectionModel().getSelectedItem());
        props.put(Status.getId(), Status.getSelectionModel().getSelectedItem());
        System.out.println(props);
        int count = bookCollection.findBooksByBarcode(props.getProperty("Barcode")).size();
        if (count == 0) {
            Properties tableName = new Properties();
            tableName.setProperty("TableName", "Book");
            SQLInsertStatement insertStatement = new SQLInsertStatement(tableName, props);

            Book newBook = new Book(props);
            System.out.println(newBook.toString());
            newBook.update();
            alertMessage.setText("Book has been submitted");
        } else {
            alertMessage.setText("Book with barcode: " + props.getProperty("Barcode") + " already exists");
        }
        for (TextField textField : textFieldList) {
            textField.clear();
        }
    }






















}
