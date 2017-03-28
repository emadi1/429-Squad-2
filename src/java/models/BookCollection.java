package models;

import database.DBKey;
import utilities.Core;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class BookCollection extends EntityBase {
    private static final String myTableName = "Book";
    private Vector<Book> books;
    private String updateStatusMessage = "";
    private Properties language;

    public BookCollection() {
        super(myTableName);
        Vector<Book> bookCollection = new Vector<>();
        language = Core.getInstance().getLang();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                books = new Vector();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties)allDataRetrieved.elementAt(index);
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
        String query = "SELECT * FROM " + myTableName + " ORDER BY Title";
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

    public Vector findBooksByAuthor(String author) {
        String query = "SELECT * FROM " + myTableName + " WHERE Author1 LIKE '%" + author + "%' OR Author2 LIKE '%"
                + author + "%' OR Author3 LIKE '%" + author + "%' OR Author4 LIKE '%" + author + "%' ORDER BY Title ASC";
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

    public Vector findBooksByPublisher(String publisher) {
        String query = "SELECT * FROM " + myTableName + " WHERE Publisher LIKE '%" + publisher + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByPublicationYear(String year) {
        String query = "SELECT * FROM " + myTableName + " WHERE (PublicationYear = " + year + ") ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByISBN(String isbn) {
        String query = "SELECT * FROM " + myTableName + " WHERE ISBN LIKE '%" + isbn + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByBookCondition(String condition) {
        String query = "SELECT * FROM " + myTableName + " WHERE Condition LIKE '%" + condition + "%' ORDER BY Title ASC";
        return runQuery(query);
    }

    public Vector findBooksByStatus(String status) {
        String query = "SELECT * FROM " + myTableName + " WHERE Status LIKE '%" + status + "%' ORDER BY Status ASC";
        return runQuery(query);
    }

    private String getColumnsName() {
        return    DBKey.BARCODE + " AS " + language.getProperty(DBKey.BANNER_ID) + " "
                + DBKey.TITLE + " AS " + language.getProperty(DBKey.TITLE) + " "
                + DBKey.DISCIPLINE + " AS " + language.getProperty(DBKey.DISCIPLINE) + " "
                + DBKey.AUTHOR_1 + " AS " + language.getProperty(DBKey.AUTHOR_1) + " "
                + DBKey.AUTHOR_2 + " AS " + language.getProperty(DBKey.AUTHOR_2) + " "
                + DBKey.AUTHOR_3 + " AS " + language.getProperty(DBKey.AUTHOR_3) + " "
                + DBKey.AUTHOR_4 + " AS " + language.getProperty(DBKey.AUTHOR_4) + " "
                + DBKey.PUBLISHER + " AS " + language.getProperty(DBKey.PUBLISHER) + " "
                + DBKey.YEAR_OF_PUBLICATION + " AS " + language.getProperty(DBKey.YEAR_OF_PUBLICATION) + " "
                + DBKey.ISBN + " AS " + language.getProperty(DBKey.ISBN) + " "
                + DBKey.CONDITION + " AS " + language.getProperty(DBKey.CONDITION) + " "
                + DBKey.SUGGESTED_PRICE + " AS " + language.getProperty(DBKey.SUGGESTED_PRICE) + " "
                + DBKey.NOTES + " AS " + language.getProperty(DBKey.NOTES) + " "
                + DBKey.STATUS + " AS " + language.getProperty(DBKey.STATUS);
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
        if (key.equals("UpdateStatusMessage") == true) {
            return updateStatusMessage;
        }
        return persistentState.getProperty(key);
    }
}
