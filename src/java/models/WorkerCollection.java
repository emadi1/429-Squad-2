package models;

import java.util.Properties;
import java.util.Vector;

public class WorkerCollection extends EntityBase {
    private static final String myTableName = "Worker";
    private Vector<Worker> workers;
    private String updateStatusMessage = "";

    public WorkerCollection() {
        super(myTableName);
        Vector<Worker> workerCollection = new Vector<>();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                workers = new Vector();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties)allDataRetrieved.elementAt(index);
                    Worker worker = new Worker(data);
                    if (worker != null) {
                        addWorker(worker);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return workers;
    }

    public Vector findAllWorkers() {
        String query = "SELECT * FROM " + myTableName + " ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findBannerPassword(String bannerId) {
        String query = "SELECT BannerId, Password FROM " + myTableName + " WHERE (BannerId = " + bannerId + ")";
        return runQuery(query);
    }

    public Vector findWorkersByBannerId(String bannerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (BannerId = " + bannerId + ") ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findWorkersByFirstName(String firstName) {
        String query = "SELECT * FROM " + myTableName + " WHERE firstName LIKE '%" + firstName + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findWorkersByLastName(String lastName) {
        String query = "SELECT * FROM " + myTableName + " WHERE lastName LIKE '%" + lastName + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findWorkersByContactPhone(String contactPhone) {
        String query = "SELECT * FROM " + myTableName + " WHERE contactPhone LIKE '%" + contactPhone + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findWorkersByCredentials(String credentials) {
        String query = "SELECT * FROM " + myTableName + " WHERE credentials LIKE '%" + credentials + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findWorkerByCredentials(String status) {
        String query = "SELECT * FROM " + myTableName + " WHERE status LIKE '%" + status + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public void addWorker(Worker worker) {
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
}
