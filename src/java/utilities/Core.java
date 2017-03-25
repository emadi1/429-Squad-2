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
    private static Worker modWorker, addWorker, user;
    private static Book modBook, addBook;
    private static StudentBorrower modStudentBorrower, addStudentBorrower;
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
    public Book getAddBook() {
        return addBook;
    }
    public Worker getUser() {
        return user;
    }
    public Worker getModWorker() {
        return modWorker;
    }
    public Worker getAddWorker() {
        return addWorker;
    }
    public StudentBorrower getModStudentBorrower() {
        return modStudentBorrower;
    }
    public StudentBorrower getAddStudentBorrower() {
        return addStudentBorrower;
    }
    // Setters
    public void setModBook(Book book) {
        modBook = book;
    }
    public void setAddBook(Book book) {
        addBook = book;
    }
    public void setUser(Worker worker) {
        user = worker;
    }
    public void setModWorker(Worker worker) {
        modWorker = worker;
    }
    public void setAddWorker(Worker worker) {
        addWorker = worker;
    }
    public void setModStudentBorrower(StudentBorrower studentBorrower) {
        modStudentBorrower = studentBorrower;
    }
    public void setAddStudentBorrower(StudentBorrower studentBorrower) {
        addStudentBorrower = studentBorrower;
    }
    public void setLanguage(String lang) {
        language = lang;
    }

    // Language File
    public Properties getLanguageFile() {
        Properties languageProperties = new Properties();
        if (language.equals("fr_FR")) {
            // Main View
            languageProperties.setProperty("WorkerTransactions", "");
            languageProperties.setProperty("BookTransactions", "");
            languageProperties.setProperty("StudentTransactions", "");
            languageProperties.setProperty("CheckOutBook", "");
            languageProperties.setProperty("CheckInBook", "");
            languageProperties.setProperty("ListAllBooks", "");
            languageProperties.setProperty("Title", "");
            languageProperties.setProperty("Welcome", "");

            // Worker Views

            languageProperties.setProperty("Password", "Mot de passe");
            languageProperties.setProperty("Email", "Email");
            languageProperties.setProperty("Credentials", "Accréditation");
            languageProperties.setProperty("DateOfLatestCredentialsStatus", "Date du dernier statut d'accréditation");
            languageProperties.setProperty("DateOfHire", "Date d'embauche");

            // Student Borrower View
            languageProperties.setProperty("BorrowerStatus", "");
            languageProperties.setProperty("DateOfLatestBorrowerStatus", "");
            languageProperties.setProperty("DateOfRegistration", "");

            // Book View
            languageProperties.setProperty("Barcode", "");
            languageProperties.setProperty("Title", "");
            languageProperties.setProperty("Discipline", "");
            languageProperties.setProperty("Author1", "");
            languageProperties.setProperty("Author2", "");
            languageProperties.setProperty("Author3", "");
            languageProperties.setProperty("Author4", "");
            languageProperties.setProperty("Publisher", "");
            languageProperties.setProperty("YearOfPublication", "");
            languageProperties.setProperty("ISBN", "");
            languageProperties.setProperty("BookCondition", "");
            languageProperties.setProperty("SuggestedPrice", "");

            // Common
            languageProperties.setProperty("Notes", "");
            languageProperties.setProperty("Status", "Statut");
            languageProperties.setProperty("FirstName", "Prénom");
            languageProperties.setProperty("LastName", "Nom de famille");
            languageProperties.setProperty("ContactPhone", "Numéro de téléphone");
            languageProperties.setProperty("BannerID", "ID de bannière");

            // Buttons/ComboBox
            languageProperties.setProperty("Search", "");
            languageProperties.setProperty("Add", "");
            languageProperties.setProperty("Delete", "");
            languageProperties.setProperty("Modify", "");
            languageProperties.setProperty("Good", "");
            languageProperties.setProperty("Damaged", "");
            languageProperties.setProperty("Administrator", "Administrateur");
            languageProperties.setProperty("Ordinary", "Ordinaire");
            languageProperties.setProperty("Active", "Actif");
            languageProperties.setProperty("Inactive", "Inactif");
        }
        if (language.equals("en_US")) {
            // Main View
            languageProperties.setProperty("WorkerTransactions", "Worker Transactions");
            languageProperties.setProperty("BookTransactions", "Book Transactions");
            languageProperties.setProperty("StudentTransactions", "Student Transactions");
            languageProperties.setProperty("CheckOutBook", "Check Out Book");
            languageProperties.setProperty("CheckInBook", "Check In Book");
            languageProperties.setProperty("ListAllBooks", "List All Books");
            languageProperties.setProperty("Title", "Brockport Library System!");
            languageProperties.setProperty("Welcome", "Brockport Library System!");


            // Worker Views
            languageProperties.setProperty("Password", "Password:");
            languageProperties.setProperty("ContactPhone", "Contact Phone:");
            languageProperties.setProperty("Email", "Email:");
            languageProperties.setProperty("Credentials", "Credentials:");
            languageProperties.setProperty("DateOfLatestCredentialsStatus", "Date of Latest Credentials Status:");
            languageProperties.setProperty("DateOfHire", "Date of Hire:");

            // Student Borrower View
            languageProperties.setProperty("BorrowerStatus", "Borrower Status:");
            languageProperties.setProperty("DateOfLatestBorrowerStatus", "Date of Latest Borrower Status:");
            languageProperties.setProperty("DateOfRegistration", "Date of Registration:");

            // Book View
            languageProperties.setProperty("Barcode", "Barcode:");
            languageProperties.setProperty("Title", "Title:");
            languageProperties.setProperty("Discipline", "Discipline:");
            languageProperties.setProperty("Author1", "Author 1:");
            languageProperties.setProperty("Author2", "Author 2:");
            languageProperties.setProperty("Author3", "Author 3:");
            languageProperties.setProperty("Author4", "Author 4:");
            languageProperties.setProperty("Publisher", "Publisher:");
            languageProperties.setProperty("YearOfPublication", "Year of Publication:");
            languageProperties.setProperty("ISBN", "ISBN:");
            languageProperties.setProperty("BookCondition", "Book Condition:");
            languageProperties.setProperty("SuggestedPrice", "Suggested Price:");

            // Common
            languageProperties.setProperty("Notes", "Notes:");
            languageProperties.setProperty("Status", "Status:");
            languageProperties.setProperty("BannerID", "BannerID:");
            languageProperties.setProperty("FirstName", "First Name:");
            languageProperties.setProperty("LastName", "Last Name:");

            // Buttons/ComboBox
            languageProperties.setProperty("Search", "Search");
            languageProperties.setProperty("Modify", "Modify");
            languageProperties.setProperty("Delete", "Delete");
            languageProperties.setProperty("Add", "Add");
            languageProperties.setProperty("Active", "Active");
            languageProperties.setProperty("Inactive", "Inactive");
            languageProperties.setProperty("Administrator", "Administrator");
            languageProperties.setProperty("Ordinary", "Ordinary");
            languageProperties.setProperty("Good", "Good");
            languageProperties.setProperty("Damaged", "Damaged");
        }
        return languageProperties;
    }
}
