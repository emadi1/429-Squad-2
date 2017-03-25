package models;

        import java.util.Properties;
        import java.util.Vector;

public class StudentBorrowerCollection  extends EntityBase {
    private static final String myTableName = "Student";
    private Vector<StudentBorrower> students;
    private String updateStatusMessage = "";

    public StudentBorrowerCollection() {
        super(myTableName);
        Vector<StudentBorrower> StudentBorrowerCollection = new Vector<>();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                students = new Vector();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties)allDataRetrieved.elementAt(index);
                    StudentBorrower student = new StudentBorrower(data);
                    if (student != null) {
                        addStudentBorrower(student);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return students;
    }

    public Vector findAllStudentBorrowers() {
        String query = "SELECT * FROM " + myTableName + " ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByBannerId(String bannerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (BannerId = " + bannerId + ") ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByFirstName(String firstName) {
        String query = "SELECT * FROM " + myTableName + " WHERE firstName LIKE '%" + firstName + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByLastName(String lastName) {
        String query = "SELECT * FROM " + myTableName + " WHERE lastName LIKE '%" + lastName + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByContactPhone(String contactPhone) {
        String query = "SELECT * FROM " + myTableName + " WHERE contactPhone LIKE '%" + contactPhone + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByEmail(String email) {
        String query = "SELECT * FROM " + myTableName + " WHERE Email LIKE '%" + email + "%' ORDER BY LastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByCredentials(String credentials) {
        String query = "SELECT * FROM " + myTableName + " WHERE credentials LIKE '%" + credentials + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public Vector findStudentBorrowersByStatus(String status) {
        String query = "SELECT * FROM " + myTableName + " WHERE status LIKE '%" + status + "%' ORDER BY lastName ASC";
        return runQuery(query);
    }

    public void deleteStudentBorrowers(String bannerId) {
        String query = "DELETE * FROM " + myTableName + " WHERE (BannerId = " + bannerId + ")";
        runQuery(query);
    }

    public void addStudentBorrower(StudentBorrower student) {
        students.add(student);
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