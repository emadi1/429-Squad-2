package models;

import database.DBKey;
import utilities.Core;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by Ders on 3/24/2017
 */
public class StudentBorrowerCollection extends EntityBase {
    private static final String myTableName = "StudentBorrower";
    private Vector<StudentBorrower> students;
    private String updateStatusMessage = "";
    private Properties language;

    public StudentBorrowerCollection() {
        super(myTableName);
        Vector<StudentBorrower> studentBorrowerCollection = new Vector<>();
        language = Core.getInstance().getLang();
    }

    private Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                students = new Vector();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    StudentBorrower studentBorrower = new StudentBorrower(data);
                    if (studentBorrower != null) {
                        addStudent(studentBorrower);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return students;
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    public void initializeSchema(String tableName) {
        if (mySchema == null) mySchema = getSchemaInfo(tableName);
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true) return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    private void addStudent(StudentBorrower studentBorrower) {
        students.add(studentBorrower);
    }

    // SQL Statements
    public Vector findAllStudents() {
        String query = "SELECT * FROM " + myTableName + " ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByBannerId(String bannerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.BANNER_ID +  " = " + bannerId + ") ORDER " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByFirstName(String name) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.FIRST_NAME + " LIKE '%"
                + name + "%' ORDER BY "  + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsbyLastName(String name) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.LAST_NAME + " LIKE '%"
                + name + "%' ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByContactPhone(String contactPhone) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.CONTACT_PHONE + " LIKE '%"
                + contactPhone + "%' ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByEmail(String email) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.EMAIL + " LIKE '%"
                + email + "%' ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByBorrowererStatus(String borrowerStatus) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.BORROWER_STATUS
                + " LIKE '%" + borrowerStatus + "%' ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByDateOfLatestBorrowerStatus(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.DATE_OF_LATEST_BORROWER_STATUS
                + " LIKE '%" + date + " ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByDateOfRegistration(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.DATE_OF_REGISTRATION
                + " LIKE '%" + date + " ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
    public Vector findStudentsByStatus(String status) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.STATUS
                + " LIKE '%" + status + " ORDER BY " + DBKey.LAST_NAME + " ASC";
        return runQuery(query);
    }
}