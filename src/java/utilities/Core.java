package utilities;

import models.Book;
import models.StudentBorrower;
import models.Worker;

/**
 * Created by Anders and Kevin the Dynamic Duo on 3/24/2017.
 */
public class Core {

    // Attributes
    private static Core core = null;
    private static Worker modWorker, addWorker, user;
    private static Book modBook, addBook;
    private static StudentBorrower modStudentBorrower, addStudentBorrower;

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
}
