package models;

import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;


public class Worker extends EntityBase {
    private static final String myTableName = "Worker";
    private String updateStatusMessage = "";
    protected Properties dependencies;

    /**
     * @param bannerId
     * @throws InvalidPrimaryKeyException
     */
    public Worker(String bannerId) throws InvalidPrimaryKeyException {
        super(myTableName);
        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (bannerId = " + bannerId + ")";
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
                while (allKeys.hasMoreElements() == true) {
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

    /**
     * @param props
     */
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

    /**
     * @param first
     * @param second
     * @return
     */
    public static int compare(Worker first, Worker second) {
        String firstWorker = (String) first.getState("bannerId");
        String secondWorker = (String) second.getState("bennerId");
        return firstWorker.compareTo(secondWorker);
    }

    /**
     *
     */
    private void setDependencies() {
        dependencies = new Properties();
        myRegistry.setDependencies(dependencies);
    }

    /**
     * @param key
     * @return
     */
    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true)
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    /**
     * @param key
     * @param value
     */
    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    /**
     * @param key
     * @param value
     */
    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    /**
     *
     */
    public void updateStateInDatabase() {
        try {
            if (persistentState.getProperty("BannerId") != null) {
                Properties whereClause = new Properties();
                whereClause.setProperty("BannerId", persistentState.getProperty("BannerId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Worker data for worker ID: " + persistentState.getProperty("BannerId")
                        + " installed successfully in database!";
            } else {
                Integer BannerId = insertPersistentState(mySchema, persistentState);
                persistentState.setProperty("BannerId", "" + BannerId.intValue());
                updateStatusMessage = "Worker data for new worker: " + persistentState.getProperty("BannerId")
                        + " installed successfully in database!";
            }
        } catch (Exception e) {
            System.out.println("Error installing data");
        }
    }

    /**
     * @return
     */
    public String getBannerId() {
        return persistentState.getProperty("BannerId");
    }

    /**
     * @return
     */
    public String getPassword() {
        return persistentState.getProperty("Password");
    }

    /**
     * @return
     */
    public String getFirstName() {
        return persistentState.getProperty("FirstName");
    }

    /**
     * @return
     */
    public String getLastName() {
        return persistentState.getProperty("LastName");
    }

    /**
     * @return
     */
    public String getContactPhone() {
        return persistentState.getProperty("ContactPhone");
    }

    /**
     * @return
     */
    public String getEmail() {
        return persistentState.getProperty("Email");
    }

    /**
     * @return
     */
    public String getCredentials() {
        return persistentState.getProperty("Credentials");
    }

    /**
     * @return
     */
    public String getDateOfLatestCredentialsStatus() {
        return persistentState.getProperty("DateOfLatestCredentialsStatus");
    }

    /**
     * @return
     */
    public String getDateOfHire() {
        return persistentState.getProperty("DateOfHire");
    }

    /**
     * @return
     */
    public String getStatus() {
        return persistentState.getProperty("Status");
    }

    /**
     *
     */
    public void update() {
        updateStateInDatabase();
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

    /**
     * @return
     */
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

    /**
     * @param tableName
     */
    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }
}
