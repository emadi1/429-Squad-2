package models;

public class Rental {
    int     id;
    String  borrowedId;
    String  bookId;
    String  checkoutDate;
    String  checkoutWorkerId;
    String  dueDate;
    String  checkinDate;
    String  checkinWorkerId;

    // Constructors

    public void Rental() {
        this.id = 0;
        this.borrowedId = "";
        this.bookId = "";
        this.checkoutDate = "";
        this.checkoutWorkerId = "";
        this.dueDate = "";
        this.checkinDate = "";
        this.checkinWorkerId = "";
    }

    // Getters & Setters

    public int getId() {return id;}
    public String getBorrowedId() {return borrowedId;}
    public String getBookId() {return bookId;}
    public String getCheckoutDate() {return checkoutDate;}
    public String getCheckoutWorkerId() {return checkoutWorkerId;}
    public String getDueDate() {return dueDate;}
    public String getCheckinDate() {return checkinDate;}
    public String getCheckinWorkerId() {return checkinWorkerId;}
    public void setId(int id) {
        this.id = id;
    }
    public void setBorrowedId(String borrowedId) {
        this.borrowedId = borrowedId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    public void setCheckoutWorkerId(String checkoutWorkerId) {
        this.checkoutWorkerId = checkoutWorkerId;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }
    public void setCheckinWorkerId(String checkinWorkerId) {
        this.checkinWorkerId = checkinWorkerId;
    }
}