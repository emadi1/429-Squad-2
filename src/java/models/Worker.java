package models;
import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class Worker extends EntityBase {
    private static final String myTableName = "Worker";
    private String updateStatusMessage = "";
    private Properties dependencies;

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

    public String getBannerId() {
        return persistentState.getProperty("BannerId");
    }

    public String getPassword() {
        return persistentState.getProperty("Password");
    }

    public String getFirstName() {
        return persistentState.getProperty("FirstName");
    }

    public String getLastName() {
        return persistentState.getProperty("LastName");
    }

    public String getContactPhone() {
        return persistentState.getProperty("ContactPhone");
    }

    public String getEmail() {
        return persistentState.getProperty("Email");
    }

    public String getCredentials() {
        return persistentState.getProperty("Credentials");
    }

    public String getDateOfLatestCredentialsStatus() {
        return persistentState.getProperty("DateOfLatestCredentialsStatus");
    }

    public String getDateOfHire() {
        return persistentState.getProperty("DateOfHire");
    }

    public String getStatus() {
        return persistentState.getProperty("Status");
    }

    public void setPassword(String password) {
        persistentState.setProperty("Password", password);
    }

    public void setFirstName(String firstName) {
        persistentState.setProperty("FirstName", firstName);
    }

    public void setLastName(String lastName) {
        persistentState.setProperty("LastName", lastName);
    }

    public void setContactPhone(String contactPhone) {
        persistentState.setProperty("ContactPhone", contactPhone);
    }

    public void setEmail(String email) {
        persistentState.setProperty("Email", email);
    }

    public void setCredentials(String credentials) {
        persistentState.setProperty("Credentials", credentials);
    }

    public void setDateOfLatestCredentialsStatus(String dateOfLatestCredentialsStatus) {
        persistentState.setProperty("DateOfLatestCredentialsStatus", dateOfLatestCredentialsStatus);
    }

    public void setDateOfHire(String dateOfHire) {
        persistentState.setProperty("DateOfHire", dateOfHire);
    }

    public void setStatus(String status) {
        persistentState.setProperty("Status", status);
    }

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

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }
}
