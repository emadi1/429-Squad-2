package models;
import database.DBKey;
import exception.InvalidPrimaryKeyException;
import utilities.Core;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 *  Created by kevph & Ders on 3/20/2017
 */
public class Worker extends EntityBase {
    private static final String myTableName = "Worker";
    private String updateStatusMessage = "";
    private Properties dependencies;
    private Core core = Core.getInstance();
    private Properties language = core.getLang();

    public Worker(String bannerId) throws InvalidPrimaryKeyException {
        super(myTableName);
        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (BannerId = " + bannerId + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple accounts matching ID : "
                        + bannerId + " found.");
            } else {
                Properties retrievedWorkerData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedWorkerData.propertyNames();
                while (allKeys.hasMoreElements()) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedWorkerData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No Worker matching ID: " + bannerId + " found.");
        }
    }

    public Worker(Properties props) {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true) {
            String nextKey = (String) allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    public static int compare(Worker first, Worker second) {
        String firstWorker = (String) first.getState("BannerId");
        String secondWorker = (String) second.getState("BennerId");
        return firstWorker.compareTo(secondWorker);
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

    // Data formatting
    public void formatData() {
        String date = persistentState.getProperty(DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS);
        String month = date.substring(0, 2);
        String day = date.substring(3, 5);
        String year = date.substring(6);
        persistentState.setProperty(DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS, day + "-" + month + "-" + year);
        date = persistentState.getProperty(DBKey.DATE_OF_HIRE);
        month = date.substring(0, 2);
        day = date.substring(3, 5);
        year = date.substring(6);
        persistentState.setProperty(DBKey.DATE_OF_HIRE, day + "-" + month + "-" + year);
        if (persistentState.getProperty(DBKey.CREDENTIALS).equals("Administrator"))
            persistentState.setProperty(DBKey.CREDENTIALS, language.getProperty("Administrator"));
        else persistentState.setProperty(DBKey.CREDENTIALS, language.getProperty("Ordinary"));
        if (persistentState.getProperty(DBKey.STATUS).equals("Active"))
            persistentState.setProperty(DBKey.STATUS, language.getProperty("Active"));
        else persistentState.setProperty(DBKey.STATUS, language.getProperty("Inactive"));
    }

    // SQL Insert/Update
    private void insertStateInDatabase() {
        try {
            Integer BannerId = insertPersistentState(mySchema, persistentState);
            persistentState.setProperty("BannerId", "" + BannerId.intValue());
            updateStatusMessage = "Worker data for worker ID: " + persistentState.getProperty("BannerId")
                    + " installed successfully in database!";
        } catch (SQLException e) {
            System.out.println("Error installing data: " + e);
        }
    }
    private void updateStateInDatabase() {
        try {
            Properties whereClause = new Properties();
            whereClause.setProperty("BannerId", persistentState.getProperty("BannerId"));
            updatePersistentState(mySchema, persistentState, whereClause);
            updateStatusMessage = "Worker data for worker ID: " + persistentState.getProperty("BannerId")
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

    // To String
    public String toString() {
        return  persistentState.getProperty("BannerId") + "; " +
                persistentState.getProperty("Password") + "; " +
                persistentState.getProperty("FirstName") + "; " +
                persistentState.getProperty("LastName") + "; " +
                persistentState.getProperty("ContactPhone") + "; " +
                persistentState.getProperty("Email") + "; " +
                persistentState.getProperty("Credentials") + "; " +
                persistentState.getProperty("DateOfLatestCredentialsStatus") + "; " +
                persistentState.getProperty("DateOfHire") + "; " +
                persistentState.getProperty("Status");
    }
    public String toolTipToString() {

        return  language.getProperty("BannerId") + ": " + persistentState.getProperty("BannerId") + "\n" +
                language.getProperty("FirstName") + ": " + persistentState.getProperty("FirstName") + "\n" +
                language.getProperty("LastName") + ": " + persistentState.getProperty("LastName") + "\n" +
                language.getProperty("ContactPhone") + ": " + persistentState.getProperty("ContactPhone") + "\n" +
                language.getProperty("Email") + ": " + persistentState.getProperty("Email") + "\n" +
                language.getProperty("Credentials") + ": " + persistentState.getProperty("Credentials") + "\n" +
                language.getProperty("DateOfLatestCredentialsStatus") + ": " + persistentState.getProperty("DateOfLatestCredentialsStatus") + "\n" +
                language.getProperty("DateOfHire") + ": " + persistentState.getProperty("DateOfHire") + "\n" +
                language.getProperty("Status") + ": " + persistentState.getProperty("Status") + "\n\n" +
                language.getProperty("doubleClickModify");
    }

    // Getters
    public String getBannerId() {
        return persistentState.getProperty(DBKey.BANNER_ID);
    }
    public String getPassword() {
        return persistentState.getProperty(DBKey.PASSWORD);
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
    public String getCredentials() {
        return persistentState.getProperty(DBKey.CREDENTIALS);
    }
    public String getDateOfLatestCredentialsStatus() {return persistentState.getProperty(DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS);}
    public String getDateOfHire() {
        return persistentState.getProperty(DBKey.DATE_OF_HIRE);
    }
    public String getStatus() {
        return persistentState.getProperty(DBKey.STATUS);
    }

    // Setters
    public void setPassword(String password) {
        persistentState.setProperty(DBKey.PASSWORD, password);
    }
    public void setFirstName(String firstName) {
        persistentState.setProperty(DBKey.FIRST_NAME, firstName);
    }
    public void setLastName(String lastName) {
        persistentState.setProperty(DBKey.LAST_NAME, lastName);
    }
    public void setContactPhone(String contactPhone) {
        persistentState.setProperty(DBKey.CONTACT_PHONE, contactPhone);
    }
    public void setEmail(String email) {
        persistentState.setProperty(DBKey.EMAIL, email);
    }
    public void setCredentials(String credentials) {
        persistentState.setProperty(DBKey.CREDENTIALS, credentials);
    }
    public void setDateOfLatestCredentialsStatus(String dateOfLatestCredentialsStatus) {
        persistentState.setProperty(DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS, dateOfLatestCredentialsStatus);
    }
    public void setDateOfHire(String dateOfHire) {
        persistentState.setProperty(DBKey.DATE_OF_HIRE, dateOfHire);
    }
    public void setStatus(String status) {
        persistentState.setProperty(DBKey.STATUS, status);
    }
}
