package models;

import database.Persistable;
import utilities.Core;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by id135 on 5/6/2017.
 */
public class RentedBookCollection extends Persistable {
    private Vector<RentedBook> rentedBooks;
    private Properties language;

    public RentedBookCollection() {
        Vector<RentedBook> rentedBookCollection = new Vector<>();
        language = Core.getInstance().getLang();
    }

    private Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                rentedBooks = new Vector<>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    RentedBook rentedBook = new RentedBook(data);
                    if (rentedBook != null) {
                        if (Core.getInstance().getLanguage().equals("fr_FR")) rentedBook.formatData();
                        addRentedBook(rentedBook);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return rentedBooks;
    }

    private void addRentedBook(RentedBook rentedBook) {
        rentedBooks.add(rentedBook);
    }

    public Vector findAllCheckedOutBooks() {
        String query = "SELECT Rental.Id, Rental.BorrowerId, " +
                "CONCAT(StudentBorrower.FirstName, ' ', StudentBorrower.LastName) AS BorrowerName, " +
                "Rental.BookId, Book.Title, Rental.CheckOutDate, Rental.DueDate FROM Rental, " +
                "StudentBorrower, Book WHERE (Rental.BookId = Book.Barcode AND Rental.BorrowerId = " +
                "StudentBorrower.BannerId AND Rental.CheckInDate IS NULL);";
        return runQuery(query);
    }
}
