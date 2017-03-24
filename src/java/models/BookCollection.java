package models;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class BookCollection extends EntityBase {
    private static final String myTableName = "Book";
    private Vector<Book> books;
    private String updateStatusMessage = "";

    public BookCollection() {
        super(myTableName);
        Vector<Book> bookCollection = new Vector<>();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                books = new Vector<>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    Book book = new Book(data);
                    if (book != null) {
                        addBook(book);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return books;
    }

    public Vector findAllBooks() {
        String query = "SELECT * FROM " + myTableName + " ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByBarcode(String barcode) {
        String query = "SELECT * FROM " + myTableName + " WHERE (Barcode = " + barcode + ") ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByTitle(String title) {
        String query = "SELECT * FROM " + myTableName + " WHERE Title LIKE '%" + title + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByDiscipline(String discipline) {
        String query = "SELECT * FROM " + myTableName + " WHERE Discipline LIKE '%" + discipline + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByAuthor1(String author1) {
        String query = "SELECT * FROM " + myTableName + " WHERE Author1 LIKE '%" + author1 + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByAuthor2(String author2) {
        String query = "SELECT * FROM " + myTableName + " WHERE Author2 LIKE '%" + author2 + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByAuthor3(String author3) {
        String query = "SELECT * FROM " + myTableName + " WHERE Author3 LIKE '%" + author3 + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByAuthor4(String author4) {
        String query = "SELECT * FROM " + myTableName + " WHERE Author4 LIKE '%" + author4 + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByYearOfPublication(String year) {
        String query = "SELECT * FROM " + myTableName + " WHERE YearOfPublication = " + year + " ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByISBN(String isbn) {
        String query = "SELECT * FROM " + myTableName + " WHERE ISBN = " + isbn + " ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByCondition(String condition) {
        String query = "SELECT * FROM " + myTableName + " WHERE Condition LIKE '%" + condition + "ORDER BY Title ASC";
        return runQuery(query);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true)
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

}
