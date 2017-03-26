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

    public Properties getLang() {
        Properties library = new Properties();
        if (language.equals("en_US")) {
            library.setProperty("welcome", "EOP Library System");
            library.setProperty("signIn", "Sign In Below");
            library.setProperty("WorkerTransactions", "Worker Transactions");
            library.setProperty("BookTransactions", "Book Transactions");
            library.setProperty("StudentTransactions", "Student Transactions");
            library.setProperty("addWorker", "Add Worker");
            library.setProperty("addStudent", "Add Student Borrower");
            library.setProperty("addBookTitle", "Add Book");
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
            library.setProperty("modifySuccess", "Worker updated successfully");
            library.setProperty("modifyFail", "Failed to update worker");
            library.setProperty("addWorkerSuccess", "Worker added successfully");
            library.setProperty("addWorkerFail", "Failed to add worker.");
            library.setProperty("completeFields", "Please fill out all necessary fields");
            library.setProperty("Modify", "Modify");
            library.setProperty("Add", "Add");
            library.setProperty("Delete", "Delete");
            library.setProperty("Good", "Good");
            library.setProperty("Damaged", "Damaged");
            library.setProperty("Active", "Active");
            library.setProperty("Inactive", "Inactive");
            library.setProperty("bannerId", "Banner ID:");
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
            library.setProperty("modifySuccess", "Worker updated successfully");
            library.setProperty("modifyFail", "Failed to update worker");
            library.setProperty("addWorkerSuccess", "Worker added successfully");
            library.setProperty("addWorkerFail", "Failed to add worker.");
            library.setProperty("completeFields", "Please fill out all necessary fields");
            library.setProperty("Search", "Search");
            library.setProperty("Modify", "Modify");
            library.setProperty("Add", "Add");
            library.setProperty("Delete", "Delete");
            library.setProperty("Administrator", "Administrator");
            library.setProperty("Ordinary", "Ordinary");
            library.setProperty("Active", "Active");
            library.setProperty("Inactive", "Inactive");
        } else if (language.equals("fr_FR")) {

        }
        return library;
    }
}
