package models;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class BookBarcodePrefixCollection extends EntityBase {
    private static final String myTableName = "BarcodePrefixValue";
    private Vector<BookBarcodePrefix> bookBarcodePrefixes;
    private String updateStatusMessage = "";

    public BookBarcodePrefixCollection() {
        super(myTableName);
        bookBarcodePrefixes = new Vector<>();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                bookBarcodePrefixes = new Vector<>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    BookBarcodePrefix bookBarcodePrefix = new BookBarcodePrefix(data);
                    if (bookBarcodePrefix != null) {
                        addBarcodePrefixValue(bookBarcodePrefix);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return bookBarcodePrefixes;
    }

    public Vector findAllBookBarcodePrefixValues() {
        String query = "SELECT * FROM " + myTableName + " ORDER BY PrefixValue ASC";
        return runQuery(query);
    }

    public Vector findBarcodePrefixValueByPrefix(String prefixValue) {
        String query = "SELECT * FROM " + myTableName + " WHERE (PrefixValue = " + prefixValue + ")";
        return runQuery(query);
    }

    public Vector findBarcodePrefixValueByDiscipline(String discipline) {
        String query = "SELECT * FROM " + myTableName + " WHERE (Discipline = " + discipline + ")";
        return runQuery(query);
    }

    public void addBarcodePrefixValue(BookBarcodePrefix bookBarcodePrefix) {
        bookBarcodePrefixes.add(bookBarcodePrefix);
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
