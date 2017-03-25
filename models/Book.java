package models;

import exception.InvalidPrimaryKeyException;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */

/**
public class Book extends EntityBase {
    private static final String myTableName = "Book";
    private String updateStatusMessage = "";
    protected Properties dependencies;

    public Book(String barcode) throws InvalidPrimaryKeyException {
        super(myTableName);
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
                while (allKeys.hasMoreElements() == true) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedBookData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No book mathcing ID: " + barcode + " found.");
        }
    }

    public Book(Properties props) {
        super(myTableName);
        //setDependencies();
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
        if (key.equals("UpdateStatusMessage") == true) {
            return updateStatusMessage;
        }
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    public void updateStateInDatabase()

}
*/