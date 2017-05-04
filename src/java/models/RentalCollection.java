package models;

import database.DBKey;
import utilities.Core;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class RentalCollection extends EntityBase {
    private static final String myTableName = "Rental";
    private Vector<Rental> rentals;
    private String updateStatusMessage = "";
    private Properties language = Core.getInstance().getLang();

    public RentalCollection() {
        super(myTableName);
        Vector<Rental> rentalCollection = new Vector<>();
    }

    private Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                rentals = new Vector<>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    Rental rental = new Rental(data);
                    if (rental != null) {
                        if (Core.getInstance().getLanguage().equals("fr_FR")) rental.formatData();
                        addRental(rental);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return rentals;
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    private void addRental(Rental rental) {
        rentals.add(rental);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) mySchema = getSchemaInfo(tableName);
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage"))
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    // Queries
    public Vector findRentalsById(String id) {
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.ID +
                " = " + id + ") ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsByBorrowerId(String borrowerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.BORROWER_ID +
                " = " + borrowerId + ") ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsByBookId(String bookId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.BOOK_ID +
                " = " + bookId + ") ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsByCheckOutDate(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.CHECK_OUT_DATE +
                " LIKE '%" + date + "%' ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsByCheckOutWorkerId(String workerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.CHECK_OUT_WORKER_ID +
                " = " + workerId + ") ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsyDueDate(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.DUE_DATE +
                " LIKE '%" + date + "%' ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsByCheckInDate(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE " + DBKey.CHECK_IN_DATE +
                " LIKE '%" + date + "%' ORDER BY " + DBKey.ID + " ASC";
        return runQuery(query);
    }

    public Vector findRentalsByCheckInWorkerId(String workerId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (" + DBKey.CHECK_IN_WORKER_ID +
                " = " + workerId + ") ORDER BY " + DBKey.CHECK_IN_WORKER_ID + " ASC";
        return runQuery(query);
    }

    public Vector findAllCheckoutBooks() {
        String query = "SELECT  a.Id, CONCAT(b.FirstName, ' ', b.LastName) AS BorrowerName, d.Title, a.CheckOutDate, a.DueDate " +
                "FROM Rental a, StudentBorrower b, Book d " +
                "WHERE a.BookId = d.Barcode " +
                "AND b.BannerId = a.BorrowerId " +
                "AND a.CheckInDate IS NULL;";
        Vector allDataRetrieved = getSelectQueryResult(query);
        return allDataRetrieved;
    }
}
