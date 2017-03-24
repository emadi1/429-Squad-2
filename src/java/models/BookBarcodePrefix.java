package models;

import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class BookBarcodePrefix extends EntityBase {
    private static final String myTableName = "BookBarcodePrefix";
    private String updateStatusMessage = "";
    private Properties dependencies;

    public BookBarcodePrefix(String prefixValue) throws InvalidPrimaryKeyException {
        super(myTableName);
        String query = "SELECT * FROM " + myTableName + " WHERE (PrefixValue = " + prefixValue + ")";
        Vector<Properties> allDataRetreived = getSelectQueryResult(query);

        if (allDataRetreived != null) {
            int size = allDataRetreived.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple primary keys matching Prefix Value: "
                        + prefixValue + " found.");
            } else {
                Properties retreivedPreffixValueData = allDataRetreived.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retreivedPreffixValueData.propertyNames();
                while (allKeys.hasMoreElements()) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retreivedPreffixValueData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No Prefix Value matching key: " + prefixValue + " found.");
        }
    }

    public BookBarcodePrefix(Properties props) {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements()) {
            String nextKey = (String)allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    public int compare(BookBarcodePrefix first, BookBarcodePrefix second) {
        String firstValue = (String) first.getState("PrefixValue");
        String secondValue = (String) second.getState("PrefixValue");
        return firstValue.compareTo(secondValue);
    }

    public void setDependencies() {
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

    public void updateStateInDatabase() {
        try {
            if (persistentState.getProperty("PrefixValue") != null) {
                Properties whereClause = new Properties();
                whereClause.setProperty("PrefixValue", persistentState.getProperty("PrefixValue"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Data for PrefixValue: " + persistentState.getProperty("PrefixValue")
                        + " installed successfully in database!";
            } else {
                Integer PrefixValue = insertPersistentState(mySchema, persistentState);
                persistentState.setProperty("PrefixValue", "" + PrefixValue.intValue());
                updateStatusMessage = "Prefix Value data for barcode: " + persistentState.getProperty("PrefixValue")
                        + "installed successfully in database!";
            }
        } catch (SQLException e) {
            updateStatusMessage = "Error in installing Barcode Prefix Value";
        }
    }

    public void update() {
        updateStateInDatabase();
    }

    public String getPrefixValue() {
        return persistentState.getProperty("PrefixValue");
    }

    public String getDiscipline() {
        return persistentState.getProperty("Discipline");
    }

    public String toString() {
        return  persistentState.getProperty("PrefixValue") + "; " +
                persistentState.getProperty("Discipline");
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }






























}
