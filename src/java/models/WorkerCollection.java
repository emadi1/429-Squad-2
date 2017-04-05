package models;
import database.DBKey;
import utilities.Core;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by Kevin and maybe Ders on 3/21/2017
 */
public class WorkerCollection extends EntityBase {
    private static final String myTableName = "Worker";
    private Vector<Worker> workers;
    private String updateStatusMessage = "";
    private Properties language;
    private Core core = Core.getInstance();

    public WorkerCollection() {
        super(myTableName);
        workers = new Vector<>();
        Vector<Worker> workerCollection = new Vector<>();
        language = Core.getInstance().getLang();
    }

    private Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                workers = new Vector();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    Worker worker = new Worker(data);
                    if (worker != null) {
                        if (core.getLanguage().equals("fr_FR")) {
                            worker.formatCredentialDate();
                            worker.formatDateOfHire();
                            worker.formatCredentials();
                            worker.formatStatus();
                        }
                        addWorker(worker);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return workers;
    }

    private void addWorker(Worker worker) {
        workers.add(worker);
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

    private String getColumnsName() {
        return    DBKey.BANNER_ID + " AS " + language.getProperty(DBKey.BANNER_ID) + " "
                + DBKey.PASSWORD + " AS " + language.getProperty(DBKey.PASSWORD) + " "
                + DBKey.FIRST_NAME + " AS " + language.getProperty(DBKey.FIRST_NAME) + " "
                + DBKey.LAST_NAME + " AS " + language.getProperty(DBKey.LAST_NAME) + " "
                + DBKey.CONTACT_PHONE + " AS " + language.getProperty(DBKey.CONTACT_PHONE) + " "
                + DBKey.EMAIL + " AS " + language.getProperty(DBKey.EMAIL) + " "
                + DBKey.CREDENTIALS + " AS " + language.getProperty(DBKey.CREDENTIALS) + " "
                + DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS + " AS " + language.getProperty(DBKey.DATE_OF_LATEST_CREDENTIALS_STATUS) + " "
                + DBKey.DATE_OF_HIRE + " AS " + language.getProperty(DBKey.DATE_OF_HIRE) + " "
                + DBKey.STATUS + " AS " + language.getProperty(DBKey.STATUS);
    }

    // SQL Statements
    public Vector findAllWorkers() {
        String query = "SELECT * FROM " + myTableName + " ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector signInWorker(String bannerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (BannerId = " + bannerId + ") ORDER BY BannerId ASC";
        return runQuery(query);
    }
    public Vector findWorkersByBannerId(String bannerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (BannerId = " + bannerId + ") ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByFirstName(String firstName) {
        String query = "SELECT * FROM " + myTableName + " WHERE FirstName LIKE '%" + firstName + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByLastName(String lastName) {
        String query = "SELECT * FROM " + myTableName + " WHERE LastName LIKE '%" + lastName + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByContactPhone(String contactPhone) {
        String query = "SELECT * FROM " + myTableName + " WHERE ContactPhone LIKE '%" + contactPhone + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByEmail(String email) {
        String query = "SELECT * FROM " + myTableName + " WHERE Email LIKE '%" + email + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByCredentials(String credentials) {
        String query = "SELECT * FROM " + myTableName + " WHERE credentials LIKE '%" + credentials + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByLatestCredentialsStatus(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE DateOfLatestCredentialsStatus LIKE '%" + date + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkersByDateOfHire(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE DateOfHire LIKE '%" + date + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public Vector findWorkerByStatus(String status) {
        String query = "SELECT * FROM " + myTableName + " WHERE Status LIKE '%" + status + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }
    public void deleteWorker(String bannerId) {
        String query = "DELETE * FROM " + myTableName + " WHERE (" + DBKey.BANNER_ID + " = " + bannerId + ");\n" +
                "SELECT * FROM " + myTableName + " WHERE (" + DBKey.BANNER_ID + " = " + bannerId + ");";
        runQuery(query);
    }
}
