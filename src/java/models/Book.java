package models;
import exception.InvalidPrimaryKeyException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */

public class Book extends EntityBase {
    private static final String myTableName = "Book";
    private String updateStatusMessage = "";
    private Properties dependencies;

    public Book(String barcode) throws InvalidPrimaryKeyException {
        super(myTableName);
        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (Barcode = " + barcode + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple accounts matching ID: "
                        + barcode + " found.");
            } else {
                Properties retrievedBookData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedBookData.propertyNames();
                while (allKeys.hasMoreElements()) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedBookData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No book matching ID: " + barcode + " found.");
        }
    }

    public Book(Properties props) {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true) {
            String nextKey = (String)allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    public static int compare(Book first, Book second) {
        String firstBook = (String) first.getState("Barcode");
        String secondBook = (String) second.getState("Barcode");
        return firstBook.compareTo(secondBook);
    }

    private void setDependencies() {
        dependencies = new Properties();
        myRegistry.setDependencies(dependencies);
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage"))
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    private void insertStateInDatabase() {
        try {
            Integer Barcode = insertPersistentState(mySchema, persistentState);
            persistentState.setProperty("Barcode", "" + Barcode.intValue());
            updateStatusMessage = "Book data for book ID: " + persistentState.getProperty("Barcode")
                    + " installed successfully in database!";
        } catch (SQLException e) {
            System.out.println("Error installing data: " + e);
        }
    }

    private void updateStateInDatabase() {
        try {
            Properties whereClause = new Properties();
            whereClause.setProperty("Barcode", persistentState.getProperty("Barcode"));
            updatePersistentState(mySchema, persistentState, whereClause);
            updateStatusMessage = "Book data for book ID: " + persistentState.getProperty("Barcode")
                    + " installed successfully in database!";
        } catch (SQLException e) {
            System.out.println("Error installing data: " + e);
        }
    }

    public void update() {
        updateStateInDatabase();
    }

    public void insert() {
        insertStateInDatabase();
    }

    public String getBarcode() {
        return persistentState.getProperty("Barcode");
    }

    public String getTitle() {
        return persistentState.getProperty("Title");
    }

    public String getDiscipline() {
        return persistentState.getProperty("Discipline");
    }

    public String getAuthor1() {
        return persistentState.getProperty("Author1");
    }

    public String getAuthor2() {
        return persistentState.getProperty("Author2");
    }

    public String getAuthor3() {
        return persistentState.getProperty("Author3");
    }

    public String getAuthor4() {
        return persistentState.getProperty("Author4");
    }

    public String getPublisher() {
        return persistentState.getProperty("Publisher");
    }

    public String getYearOfPublication() {
        return persistentState.getProperty("YearOfPublication");
    }

    public String getISBN() {
        return persistentState.getProperty("ISBN");
    }

    public String getBookCondition() {
        return persistentState.getProperty("BookCondition");
    }

    public String getSuggestedPrice() {
        return persistentState.getProperty("SuggestedPrice");
    }

    public String getNotes() {
        return persistentState.getProperty("Notes");
    }

    public String getStatus() {
        return persistentState.getProperty("Status");
    }

    public void setTitle(String title) {
        persistentState.setProperty("Title", title);
    }

    public static String generateDiscipline(String prefix) {
        BookBarcodePrefixCollection bookBarcodePrefixCollection = new BookBarcodePrefixCollection();
        BookBarcodePrefix bookBarcodePrefix = (BookBarcodePrefix)bookBarcodePrefixCollection.findBarcodePrefixValueByPrefix(prefix).get(0);
        System.out.println(bookBarcodePrefix.getDiscipline());
        return bookBarcodePrefix.getDiscipline();
    }

    public void setDiscipline(String discipline) {
        persistentState.setProperty("Discipline", discipline);
    }

    public void setAuthor1(String author1) {
        persistentState.setProperty("Author1", author1);
    }

    public void setAuthor2(String author2) {
        persistentState.setProperty("Author2", author2);
    }

    public void setAuthor3(String author3) {
        persistentState.setProperty("Author3", author3);
    }

    public void setAuthor4(String author4) {
        persistentState.setProperty("Author4", author4);
    }

    public void setPublisher(String publisher) {
        persistentState.setProperty("Publisher", publisher);
    }

    public void setYearOfPublication(String yearOfPublication) {
        persistentState.setProperty("YearOfPublication", yearOfPublication);
    }

    public void setISBN(String isbn) {
        persistentState.setProperty("ISBN", isbn);
    }

    public void setBookCondition(String bookCondition) {
        persistentState.setProperty("BookCondition", bookCondition);
    }

    public void setSuggestedPrice(String suggestedPrice) {
        persistentState.setProperty("SuggestedPrice", suggestedPrice);
    }

    public void setNotes(String notes) {
        persistentState.setProperty("Notes", notes);
    }

    public void setStatus(String status) {
        persistentState.setProperty("Status", status);
    }

    public String toString() {
        return  persistentState.getProperty("Barcode") + "; " +
                persistentState.getProperty("Title") + "; " +
                persistentState.getProperty("Discipline") + "; " +
                persistentState.getProperty("Author1") + "; " +
                persistentState.getProperty("Author2") + "; " +
                persistentState.getProperty("Author3") + "; " +
                persistentState.getProperty("Author4") + "; " +
                persistentState.getProperty("Publisher") + "; " +
                persistentState.getProperty("YearOfPublication") + "; " +
                persistentState.getProperty("ISBN") + "; " +
                persistentState.getProperty("BookCondition") + "; " +
                persistentState.getProperty("SuggestedPrice") + "; " +
                persistentState.getProperty("Notes") + "; " +
                persistentState.getProperty("Status");
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }
}
