package utilities;

import common.PropertyFile;
import models.Book;
import models.StudentBorrower;
import models.Worker;

import java.util.Properties;

/**
 * Created by Anders and Kevin the Dynamic Duo on 3/24/2017.
 */
public class Core {

    // Attributes
    private static Core core = null;
    private static Worker modWorker, user;
    private static Book modBook;
    private static StudentBorrower modStudentBorrower;
    private static String language = "";

    //Private constructor
    private Core() {
    }
    public static Core getInstance() {
        if (core == null)
            core = new Core();
        return core;
    }
    // Getters
    public Book getModBook() {
        return modBook;
    }
    public Worker getUser() {
        return user;
    }
    public Worker getModWorker() {
        return modWorker;
    }
    public StudentBorrower getModStudentBorrower() {
        return modStudentBorrower;
    }
    public String getLanguage() {
        return language;
    }
    // Setters
    public void setModBook(Book book) {
        modBook = book;
    }
    public void setUser(Worker worker) {
        user = worker;
    }
    public void setModWorker(Worker worker) {
        modWorker = worker;
    }
    public void setModStudentBorrower(StudentBorrower studentBorrower) {
        modStudentBorrower = studentBorrower;
    }
    public void setLanguage(String lang) {
        language = lang;
    }

    public Properties getLang() {
        Properties library = new Properties();
        if (language.equals("en_US")) {

            // Main View Buttons/ Text
            library.setProperty("welcome", "EOP Library System");
            library.setProperty("signIn", "Sign In Below");
            library.setProperty("SignIn", "Sign In");
            library.setProperty("WorkerTransactions", "Worker Transactions");
            library.setProperty("BookTransactions", "Book Transactions");
            library.setProperty("StudentTransactions", "Student Transactions");
            library.setProperty("CheckOutBook", "Check Out Books");
            library.setProperty("CheckInBook", "Check In Books");
            library.setProperty("ListCheckedBooks", "List Checked Books");

            // Window Titles
            library.setProperty("addWorker", "Add Worker");
            library.setProperty("modifyWorker", "Modify Worker");
            library.setProperty("addStudent", "Add Student Borrower");
            library.setProperty("modifyStudentTitle", "Modify Student Borrower");
            library.setProperty("addBookTitle", "Add Book");
            library.setProperty("modifyBook", "Modify Book");

            // Student Borrower Data Model/ Text
            library.setProperty("borrowerStatus", "Borrower Status:");
            library.setProperty("BorrowerStatus", "BorrowerStatus");
            library.setProperty("dateOfLatestBorrowerStatus", "Date of Latest Borrower Status:");
            library.setProperty("DateOfLatestBorrowerStatus", "DateOfLatestBorrowerStatus");
            library.setProperty("dateOfRegistration", "Date of Registration:");
            library.setProperty("DateOfRegistration", "DateOfRegistration");

            // Book Data Model/ Text
            library.setProperty("barcode", "Barcode:");
            library.setProperty("Barcode", "Barcode");
            library.setProperty("title", "Title:");
            library.setProperty("Title", "Title");
            library.setProperty("discipline", "Discipline:");
            library.setProperty("Discipline", "Discipline");
            library.setProperty("Author", "Author");
            library.setProperty("author1", "Author 1:");
            library.setProperty("Author1", "Author 1");
            library.setProperty("author2", "Author 2:");
            library.setProperty("Author2", "Author 2");
            library.setProperty("author3", "Author 3:");
            library.setProperty("Author3", "Author 3");
            library.setProperty("author4", "Author 4:");
            library.setProperty("Author4", "Author 4");
            library.setProperty("publisher", "Publisher:");
            library.setProperty("Publisher", "Publisher");
            library.setProperty("yearOfPublication", "Year of Publication:");
            library.setProperty("YearOfPublication", "YearOfPublication");
            library.setProperty("isbn", "ISBN:");
            library.setProperty("ISBN", "ISBN");
            library.setProperty("bookCondition", "Book Condition:");
            library.setProperty("BookCondition", "BookCondition");
            library.setProperty("suggestedPrice", "Suggested Price:");
            library.setProperty("SuggestedPrice", "SuggestedPrice");
            library.setProperty("notes", "Notes:");
            library.setProperty("Notes", "Notes");
            library.setProperty("status", "Status:");
            library.setProperty("Status", "Status");

            // Worker Data Models/Text
            library.setProperty("PromptBannerId", "Banner ID:");
            library.setProperty("BannerId", "Banner ID");
            library.setProperty("PromptPassword", "Password:");
            library.setProperty("Password", "Password");
            library.setProperty("PromptFirstName", "First Name:");
            library.setProperty("FirstName", "First Name");
            library.setProperty("PromptLastName", "Last Name:");
            library.setProperty("LastName", "Last Name");
            library.setProperty("PromptContactPhone", "Contact Phone:");
            library.setProperty("ContactPhone", "Contact Phone");
            library.setProperty("PromptEmail", "Email:");
            library.setProperty("Email", "Email");
            library.setProperty("PromptCredentials", "Credentials:");
            library.setProperty("Credentials", "Credentials");
            library.setProperty("PromptDateOfLatestCredentialsStatus", "Date of Latest Credentials Status:");
            library.setProperty("DateOfLatestCredentialsStatus", "Date of Latest Credentials Status");
            library.setProperty("PromptDateOfHire", "Date of Hire:");
            library.setProperty("DateOfHire", "Date of Hire");
            library.setProperty("PromptStatus", "Status:");
            library.setProperty("Status", "Status");

            // General Alerts
            library.setProperty("invalidDateFormat", "Date must be in format: mm-dd-yyyy");
            library.setProperty("completeFields", "Please fill out all necessary fields.");
            library.setProperty("emptyField", "Please complete field.");

            // Student Alerts
            library.setProperty("addStudentSuccess", "Student Borrower successfully added!");
            library.setProperty("addStudentFail", "Failed to add student borrower.");
            library.setProperty("modifyStudentSuccess", "Student Borrower updated successfully!");
            library.setProperty("modifyStudentFail", "Failed to modify student borrower.");
            library.setProperty("deleteStudentSuccess", "Student Borrower deleted.");
            library.setProperty("deleteStudentFail", "Failed to delete student borrower.");

            // Book Alerts
            library.setProperty("invalidBarcodeLength", "Invalid Barcode length");
            library.setProperty("existingBarcode", "Barcode already exists: ");
            library.setProperty("yearFormat", "Publication year must be in format: yyyy");
            library.setProperty("addBookSuccess", "Book successfully added!");
            library.setProperty("addBookFail", "Failed to add book.");
            library.setProperty("modifyBookSuccess", "Book updated successfully!");
            library.setProperty("modifyBookFail", "Failed to update book.");

            // Worker Alerts
            library.setProperty("invalidBannerIdFormat", "BannerID must be 9 digits long.");
            library.setProperty("modifyWorkerSuccess", "Worker updated successfully!");
            library.setProperty("modifyWorkerFail", "Failed to update worker.");
            library.setProperty("existingBannerId", "BannerID already exists in system.");
            library.setProperty("addWorkerSuccess", "Worker added successfully!");
            library.setProperty("addWorkerFail", "Failed to add worker.");

            // Buttons
            library.setProperty("Modify", "Modify");
            library.setProperty("Add", "Add");
            library.setProperty("Delete", "Delete");
            library.setProperty("Good", "Good");
            library.setProperty("Damaged", "Damaged");
            library.setProperty("Active", "Active");
            library.setProperty("Inactive", "Inactive");
            library.setProperty("Search", "Search");
            library.setProperty("Administrator", "Administrator");
            library.setProperty("Ordinary", "Ordinary");

        } else if (language.equals("fr_FR")) {
            // Main View Buttons/ Text
            library.setProperty("welcome", "EOP Library System");
            library.setProperty("signIn", "Sign In Below");
            library.setProperty("SignIn", "Sign In");
            library.setProperty("WorkerTransactions", "Worker Transactions");
            library.setProperty("BookTransactions", "Book Transactions");
            library.setProperty("StudentTransactions", "Student Transactions");
            library.setProperty("CheckOutBook", "Check Out Books");
            library.setProperty("CheckInBook", "Check In Books");
            library.setProperty("ListCheckedBooks", "List Checked Books");

            // Window Titles
            library.setProperty("addWorkerTitle", "Add Worker");
            library.setProperty("modifyWorkerTitle", "Modify Worker");
            library.setProperty("addStudentBorrowerTitle", "Add Student Borrower");
            library.setProperty("modifyStudentBorrowerTitle", "Modify Student Borrower");
            library.setProperty("addBookTitle", "Add Book");
            library.setProperty("modifyBookTitle", "Modify Book");

            // Student Borrower Data Model/ Text
            library.setProperty("borrowerStatus", "Borrower Status:");
            library.setProperty("BorrowerStatus", "BorrowerStatus");
            library.setProperty("dateOfLatestBorrowerStatus", "Date of Latest Borrower Status:");
            library.setProperty("DateOfLatestBorrowerStatus", "DateOfLatestBorrowerStatus");
            library.setProperty("dateOfRegistration", "Date of Registration:");
            library.setProperty("DateOfRegistration", "DateOfRegistration");
            library.setProperty("addStudentTitle", "Add Student");
            library.setProperty("modifyStudentTitle", "Modify Student");

            // Book Data Model/ Text
            library.setProperty("barcode", "Barcode:");
            library.setProperty("Barcode", "Barcode");
            library.setProperty("title", "Title:");
            library.setProperty("Title", "Title");
            library.setProperty("discipline", "Discipline:");
            library.setProperty("Discipline", "Discipline");
            library.setProperty("Author", "Author");
            library.setProperty("author1", "Author 1:");
            library.setProperty("Author1", "Author1");
            library.setProperty("author2", "Author 2:");
            library.setProperty("Author2", "Author2");
            library.setProperty("author3", "Author 3:");
            library.setProperty("Author3", "Author3");
            library.setProperty("author4", "Author 4:");
            library.setProperty("Author4", "Author4");
            library.setProperty("publisher", "Publisher:");
            library.setProperty("Publisher", "Publisher");
            library.setProperty("yearOfPublication", "Year of Publication:");
            library.setProperty("YearOfPublication", "YearOfPublication");
            library.setProperty("isbn", "ISBN:");
            library.setProperty("ISBN", "ISBN");
            library.setProperty("bookCondition", "Book Condition:");
            library.setProperty("BookCondition", "BookCondition");
            library.setProperty("suggestedPrice", "Suggested Price:");
            library.setProperty("SuggestedPrice", "SuggestedPrice");
            library.setProperty("notes", "Notes:");
            library.setProperty("Notes", "Notes");
            library.setProperty("status", "Status:");
            library.setProperty("Status", "Status");

            // Worker Data Models/Text
            library.setProperty("bannerId", "ID de bannière");
            library.setProperty("BannerId", "BannerId");
            library.setProperty("password", "Password:");
            library.setProperty("Password", "Password");
            library.setProperty("firstName", "First Name:");
            library.setProperty("FirstName", "FirstName");
            library.setProperty("lastName", "Last Name:");
            library.setProperty("LastName", "LastName");
            library.setProperty("contactPhone", "Contact Phone:");
            library.setProperty("ContactPhone", "ContactPhone");
            library.setProperty("email", "Email:");
            library.setProperty("Email", "Email");
            library.setProperty("credentials", "Credentials:");
            library.setProperty("Credentials", "Credentials");
            library.setProperty("dateOfLatestCredentialsStatus", "Date of Latest Credentials Status:");
            library.setProperty("DateOfLatestCredentialsStatus", "DateOfLatestCredentialsStatus");
            library.setProperty("dateOfHire", "Date of Hire:");
            library.setProperty("DateOfHire", "DateOfHire");
            library.setProperty("status", "Status:");
            library.setProperty("Status", "Status");

            // General Alerts
            library.setProperty("invalidDateFormat", "Date must be in format: mm-dd-yyyy");
            library.setProperty("completeFields", "Please fill out all necessary fields.");
            library.setProperty("emptyField", "Please complete field.");

            // Student Alerts
            library.setProperty("addStudentSuccess", "Student Borrower successfully added!");
            library.setProperty("addStudentFail", "Failed to add student borrower.");
            library.setProperty("modifyStudentSuccess", "Student Borrower updated successfully!");
            library.setProperty("modifyStudentFail", "Failed to modify student borrower.");
            library.setProperty("deleteStudentSuccess", "Student Borrower deleted.");
            library.setProperty("deleteStudentFail", "Failed to delete student borrower.");

            // Book Alerts
            library.setProperty("invalidBarcodeLength", "Invalid Barcode length");
            library.setProperty("existingBarcode", "Barcode already exists: ");
            library.setProperty("yearFormat", "Publication year must be in format: yyyy");
            library.setProperty("addBookSuccess", "Book successfully added!");
            library.setProperty("addBookFail", "Failed to add book.");
            library.setProperty("modifyBookSuccess", "Book updated successfully!");
            library.setProperty("modifyBookFail", "Failed to update book.");

            // Worker Alerts
            library.setProperty("invalidBannerIdFormat", "BannerID must be 9 digits long.");
            library.setProperty("invalidContactPhoneFormat", "Invalid Contact Phone Format");
            library.setProperty("modifyWorkerSuccess", "Worker updated successfully!");
            library.setProperty("modifyWorkerFail", "Failed to update worker.");
            library.setProperty("existingBannerId", "BannerID already exists in system.");
            library.setProperty("addWorkerSuccess", "Worker added successfully!");
            library.setProperty("addWorkerFail", "Failed to add worker.");
            library.setProperty("invalidCredentials", "Please enter either Ordinary/Administrator in search field.");

            // Buttons
            library.setProperty("Modify", "Modify");
            library.setProperty("Add", "Add");
            library.setProperty("Delete", "Delete");
            library.setProperty("Good", "Good");
            library.setProperty("Damaged", "Damaged");
            library.setProperty("Active", "Active");
            library.setProperty("Inactive", "Inactive");
            library.setProperty("Search", "Search");
            library.setProperty("Administrator", "Administrator");
            library.setProperty("Ordinary", "Ordinary");
        }
        return library;
    }
}
