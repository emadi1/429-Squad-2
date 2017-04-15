package models;

import database.DBKey;
import exception.InvalidPrimaryKeyException;
import utilities.Core;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class Rental extends EntityBase {
    private Properties language = Core.getInstance().getLang();
    private static final String myTableName = "Rental";
    private String updateStatusMessage = "";
    private Properties dependencies;

    public Rental(String id) throws InvalidPrimaryKeyException {
        super(myTableName);
        //setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.ID + " = " + id + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple rentals matching ID: "
                        + id + " found.");
            } else {
                Properties retrievedRentalData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedRentalData.propertyNames();
                while (allKeys.hasMoreElements()) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedRentalData.getProperty(nextKey);
                    if (nextValue != null)
                        persistentState.setProperty(nextKey, nextValue);
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No rental matching ID: " + id + " found.");
        }
    }

    public Rental(Properties props) {
        super(myTableName);
        //setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements()) {
            String nextKey = (String)allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null)
                persistentState.setProperty(nextKey, nextValue);
        }
    }

    public static int compare(Rental first, Rental second) {
        String firstRental = (String) first.getState(DBKey.ID);
        String secondRental = (String) second.getState(DBKey.ID);
        return firstRental.compareTo(secondRental);
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

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    private void insertStateInDatabase() {
        try {
            Integer Id = insertAutoIncrementalPersistentState(mySchema, persistentState);

            persistentState.getProperty("Id", "" + Id.intValue());
            updateStatusMessage = "Rental data for Rental ID: " + persistentState.getProperty("Id")
                    + " installed successfully in database!";
        } catch (SQLException e) {
            System.out.println("Error installing data: " + e);
        }
    }
    private void updateStateInDatabase() {
        try {
            Properties whereClause = new Properties();
            whereClause.setProperty("Id", persistentState.getProperty("Id"));
            updatePersistentState(mySchema, persistentState, whereClause);
            updateStatusMessage = "Rental data for rental ID: " + persistentState.getProperty("Id")
                    + " installed successfully in database!";
        } catch (SQLException e) {
            System.out.println("Error installing data: " + e);
        }
    }
    public void insert() {
        insertStateInDatabase();
    }
    public void update() {
        updateStateInDatabase();
    }
    public static String generateDueDate() {
        return null;
    } // FINISH THIS

    // Getters
    public String getId() {
        return persistentState.getProperty(DBKey.ID);
    }
    public String getBorrowerId() {
        return persistentState.getProperty(DBKey.BORROWER_ID);
    }
    public String getBookId() {
        return persistentState.getProperty(DBKey.BOOK_ID);
    }
    public String getCheckOutDate() {
        return persistentState.getProperty(DBKey.CHECK_OUT_DATE);
    }
    public String getCheckOutWorkerId() {
        return persistentState.getProperty(DBKey.CHECK_OUT_WORKER_ID);
    }
    public String getDueDate() {
        return persistentState.getProperty(DBKey.DUE_DATE);
    }
    public String getCheckInDate() {
        return persistentState.getProperty(DBKey.CHECK_IN_DATE);
    }
    public String getCheckInWorkerId() {
        return persistentState.getProperty(DBKey.CHECK_IN_WORKER_ID);
    }

    // Setters
    public void setId(String Id) {
        persistentState.setProperty(DBKey.ID, Id);
    }
    public void setBorrowerId(String borrowerId) {
        persistentState.setProperty(DBKey.BORROWER_ID, borrowerId);
    }
    public void setBookId(String bookId) {
        persistentState.setProperty(DBKey.BOOK_ID, bookId);
    }
    public void setCheckOutDate(String date) {
        persistentState.setProperty(DBKey.CHECK_OUT_DATE, date);
    }
    public void setCheckOutWorkerId(String workerId) {
        persistentState.setProperty(DBKey.CHECK_OUT_WORKER_ID, workerId);
    }
    public void setDueDate(String date) {
        persistentState.setProperty(DBKey.DUE_DATE, date);
    }
    public void setCheckInDate(String date) {
        persistentState.setProperty(DBKey.CHECK_IN_DATE, date);
    }
    public void setCheckInWorkerId(String workerId) {
        persistentState.setProperty(DBKey.CHECK_IN_WORKER_ID, workerId);
    }

    public String toString() {
        return  persistentState.getProperty(DBKey.ID) + "; " +
                persistentState.getProperty(DBKey.BORROWER_ID) + "; " +
                persistentState.getProperty(DBKey.BOOK_ID) + "; " +
                persistentState.getProperty(DBKey.CHECK_OUT_DATE) + "; " +
                persistentState.getProperty(DBKey.CHECK_OUT_WORKER_ID) + "; " +
                persistentState.getProperty(DBKey.DUE_DATE) + "; " +
                persistentState.getProperty(DBKey.CHECK_IN_DATE) + "; " +
                persistentState.getProperty(DBKey.CHECK_IN_WORKER_ID);
    }
}
