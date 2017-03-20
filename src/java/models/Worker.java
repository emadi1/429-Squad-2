package models;


import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class Worker extends EntityBase {
    private static final String myTableName = "Worker";
    private String updateStatusMessage = "";
    protected Properties dependencies;

    public Worker(String bannerId) throws InvalidPrimaryKeyException {
        super(myTableName);
        //setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (bannerId = " + bannerId + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple accounts mathcing ID : "
                        + bannerId + " found.");
            } else {
                Properties retrievedWorkerData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedWorkerData.propertyNames();
                while (allKeys.hasMoreElements() == true) {
                    String nextKey = (String)allKeys.nextElement();
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
        //setDependencies();
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
        String firstWorker = (String) first.getState("bannerId");
        String secondWorker = (String) second.getState("bennerId");
        return firstWorker.compareTo(secondWorker);
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
                updateStatusMessage = "Worker data for worker number: " + persistentState.getProperty("bannerId")
                        + " installed successfully in database!";
            } else {
                Integer bannerId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("bannerId", "" + bannerId.intValue());
                updateStatusMessage = "Worker data for new worker: " + persistentState.getProperty("bannerId")
                        + " installed successfully in database!";
            }
        } catch (SQLException e) {
            updateStatusMessage = "Error in installing Worker data in database!";
        }
    }

    public void update() {
        updateStateInDatabase();
    }

    public String toString() {
        return  persistentState.getProperty("bannerId") + "; " +
                persistentState.getProperty("password") + "; " +
                persistentState.getProperty("firstName") + "; " +
                persistentState.getProperty("lastName") + "; " +
                persistentState.getProperty("contactPhone") + "; " +
                persistentState.getProperty("email") + "; " +
                persistentState.getProperty("credentials") + "; " +
                persistentState.getProperty("dateOfLatestCredentialsStatus") + "; " +
                persistentState.getProperty("dateOfHire") + "; " +
                persistentState.getProperty("status");
    }

    protected void initializeSchema(String tableName)
    {
        if(mySchema == null)
        {
            mySchema = getSchemaInfo(tableName);
        }
    }
}
