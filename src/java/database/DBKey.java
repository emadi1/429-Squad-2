package database;

/**
 * Created by kevph on 3/27/2017.
 */
public class DBKey {
    // Shared
    public static final String BANNER_ID = "BannerId";
    public static final String PASSWORD = "Password";
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String CONTACT_PHONE = "ContactPhone";
    public static final String EMAIL = "Email";
    public static final String STATUS = "Status";
    public static final String NOTES = "Notes";
    public static final String DISCIPLINE = "Discipline";

    // Worker
    public static final String CREDENTIALS = "Credentials";
    public static final String DATE_OF_LATEST_CREDENTIALS_STATUS = "DateOfLatestCredentialsStatus";
    public static final String DATE_OF_HIRE = "DateOfHire";

    // StudentBorrower
    public static final String BORROWER_STATUS = "BorrowerStatus";
    public static final String DATE_OF_LATEST_BORROWER_STATUS = "DateOfLatestBorrowerStatus";
    public static final String DATE_OF_REGISTRATION = "DateOfRegistration";

    // Book
    public static final String BARCODE = "Barcode";
    public static final String TITLE = "Title";
    public static final String AUTHOR_1 = "Author1";
    public static final String AUTHOR_2 = "Author2";
    public static final String AUTHOR_3 = "Author3";
    public static final String AUTHOR_4 = "Author4";
    public static final String PUBLISHER = "Publisher";
    public static final String YEAR_OF_PUBLICATION = "YearOfPublication";
    public static final String ISBN = "ISBN";
    public static final String BOOK_CONDITION = "BookCondition";
    public static final String SUGGESTED_PRICE = "SuggestedPrice";

    // BookBarcodePrefix
    public static final String PREFIX_VALUE = "PrefixValue";

    // Rental
    public static final String ID = "Id";
    public static final String BORROWER_ID = "BorrowerId";
    public static final String BOOK_ID = "BookId";
    public static final String CHECK_OUT_DATE = "CheckoutDate";
    public static final String CHECK_OUT_WORKER_ID = "CheckoutWorkerId";
    public static final String DUE_DATE = "DueDate";
    public static final String CHECK_IN_DATE = "CheckinDate";
    public static final String CHECK_IN_WORKER_ID = "CheckinWorkerId";

    // MaxDueDate
    public static final String CURRENT_MAX_DUE_DATE = "CurrentMaxDueDate";

}
