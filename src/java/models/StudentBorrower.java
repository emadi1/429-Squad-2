package models;
import database.DBKey;
import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Ders & kevph on 3/24/2017
 */
public class StudentBorrower extends EntityBase {
    private static final String myTableName = "StudentBorrower";
    private String updateStatusMessage = "";
    protected Properties dependencies;

    public StudentBorrower(String bannerId) throws InvalidPrimaryKeyException {
        super(myTableName);
        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (bannerId = " + bannerId + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple accounts matching ID: " +
                        bannerId + " found.");
            } else {
                Properties retrievedStudentBorrowerData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedStudentBorrowerData.propertyNames();
                while (allKeys.hasMoreElements() == true) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedStudentBorrowerData.getProperty(nextKey);
                    if (nextValue != null)
                        persistentState.setProperty(nextKey, nextValue);
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No Student Borrower matching ID: " + bannerId + " found.");
        }
    }

    public StudentBorrower(Properties props) {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true) {
            String nextKey = (String)allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null)
                persistentState.setProperty(nextKey, nextValue);
        }
    }

    public static int compare(StudentBorrower first, StudentBorrower second) {
        String firstStudentBorrower = (String)first.getState("bannerId");
        String secondStudentBorrower = (String)second.getState("bannerId");
        return firstStudentBorrower.compareTo(secondStudentBorrower);
    }

    private void setDependencies() {
        dependencies = new Properties();
        myRegistry.setDependencies(dependencies);
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true)
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    private void updateStateInDatabase() {
        try {
            Properties whereClause = new Properties();
            whereClause.setProperty("bannerId", persistentState.getProperty("bannerId"));
            updatePersistentState(mySchema, persistentState, whereClause);
            updateStatusMessage = "Student Borrower data for Student Borrower: " +
                    persistentState.getProperty("bannerId") + " installed successfully in database!";
        } catch (SQLException e) {
            updateStatusMessage = "Error updating Student Borrower data in database!";
        }
    }

    private void insertStateInDatabase() {
        try {
            Integer BannerId = insertPersistentState(mySchema, persistentState);
            persistentState.setProperty("BannerId", "" + BannerId.intValue());
            updateStatusMessage = "Student data for Student ID: " + persistentState.getProperty("BannerId")
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

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public String toString() {
        return  persistentState.getProperty("BannerId") + "; " +
                persistentState.getProperty("FirstName") + "; " +
                persistentState.getProperty("LastName") + "; " +
                persistentState.getProperty("ContactPhone") + "; " +
                persistentState.getProperty("BorrowerStatus") + "; " +
                persistentState.getProperty("DateOfLatestBorrowerStatus") + "; " +
                persistentState.getProperty("DateOfRegistration") + "; " +
                persistentState.getProperty("Notes") + "; " +
                persistentState.getProperty("Status");
    }

    // Getters
    public String getBannerId() {
        return persistentState.getProperty(DBKey.BANNER_ID);
    }
    public String getFirstName() {
        return persistentState.getProperty(DBKey.FIRST_NAME);
    }
    public String getLastName() {
        return persistentState.getProperty(DBKey.LAST_NAME);
    }
    public String getContactPhone() {
        return persistentState.getProperty(DBKey.CONTACT_PHONE);
    }
    public String getEmail() {
        return persistentState.getProperty(DBKey.EMAIL);
    }
    public String getBorrowerStatus() {
        return persistentState.getProperty(DBKey.BORROWER_STATUS);
    }
    public String getDateOfLatestBorrowerStatus() {
        return persistentState.getProperty(DBKey.DATE_OF_LATEST_BORROWER_STATUS);
    }
    public String getDateOfRegistration() {
        return persistentState.getProperty(DBKey.DATE_OF_REGISTRATION);
    }
    public String getNotes() {
        return persistentState.getProperty(DBKey.NOTES);
    }
    public String getStatus() {
        return persistentState.getProperty(DBKey.STATUS);
    }

    // Setters
    public void setFirstName(String name) {
        persistentState.setProperty(DBKey.FIRST_NAME, name);
    }
    public void setLastName(String name) {
        persistentState.setProperty(DBKey.LAST_NAME, name);
    }
    public void setContactPhone(String contactPhone) {
        persistentState.setProperty(DBKey.CONTACT_PHONE, contactPhone);
    }
    public void setEmail(String email) {
        persistentState.setProperty(DBKey.EMAIL, email);
    }
    public void setBorrowerStatus(String borrowerStatus) {
        persistentState.setProperty(DBKey.BORROWER_STATUS, borrowerStatus);
    }
    public void setDateOfLatestBorrowerStatus(String dateOfLatestBorrowerStatus) {
        persistentState.setProperty(DBKey.DATE_OF_LATEST_BORROWER_STATUS, dateOfLatestBorrowerStatus);
    }
    public void setDateOfRegistration(String dateOfRegistration) {
        persistentState.setProperty(DBKey.DATE_OF_REGISTRATION, dateOfRegistration);
    }
    public void setNotes(String notes) {
        persistentState.setProperty(DBKey.NOTES, notes);
    }
    public void setStatus(String status) {
        persistentState.setProperty(DBKey.STATUS, status);
    }
}
