package models;

import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
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

    public void updateStateInDatabase() {
        try {
            if (persistentState.getProperty("bannerId") != null) {
                Properties whereClause = new Properties();
                whereClause.setProperty("bannerId", persistentState.getProperty("bannerId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Student Borrower data for Student Borrower: " +
                        persistentState.getProperty("bannerId") + " installed successfully in database!";
            } else {
                Integer bannerId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("bannerId", "" + bannerId.intValue());
                updateStatusMessage = "Student Borrower data for new student: " + persistentState.getProperty("bannerId")
                        + " installed successfully in database!";
            }
        } catch (SQLException e) {
            updateStatusMessage = "Error in installing Student Borrower data in database!";
        }
    }

    public void update() {
        updateStateInDatabase();
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

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }
}
