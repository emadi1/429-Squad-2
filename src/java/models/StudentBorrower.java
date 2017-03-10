package models;

public class                StudentBorrower {
    public enum             BorrowerStatus {
        DELINQUENT,
        GOOD_STANDING
    }

    private String          bannerId;
    private String          firstName;
    private String          lastName;
    private String          contactPhone;
    private String          email;
    private BorrowerStatus  borrowerStatus;
    private String          dateOfLatestBorrowerStatus;
    private String          dateOfRegistration;
    private String          notes;
    private Book.Status     status;

    // Constructors

    StudentBorrower() {
        this.bannerId                   = "";
        this.firstName                  = "";
        this.lastName                   = "";
        this.contactPhone               = "";
        this.email                      = "";
        this.borrowerStatus             = BorrowerStatus.GOOD_STANDING;
        this.dateOfLatestBorrowerStatus = "";
        this.dateOfRegistration         = "";
        this.notes                      = "";
        this.status                     = Book.Status.ACTIVE;
    }

    // Setters & Getters

    public void setBannerId(String bannerId) { this.bannerId = bannerId; }
    public String getBannerId() { return this.bannerId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getFirstName() { return this.firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLastName() { return this.lastName; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactPhone() { return this.contactPhone; }
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }
    public void setBorrowerStatus(BorrowerStatus borrowerStatus) { this.borrowerStatus = borrowerStatus; }
    public BorrowerStatus getBorrowerStatus() { return borrowerStatus; }
    public void setDateOfLatestBorrowerStatus(String dateOfLatestBorrowerStatus) { this.dateOfLatestBorrowerStatus = dateOfLatestBorrowerStatus; }
    public String getDateOfLatestBorrowerStatus() { return dateOfLatestBorrowerStatus; }
    public void setDateOfRegistration(String dateOfRegistration) { this.dateOfRegistration = dateOfRegistration; }
    public String getDateOfRegistration() { return dateOfRegistration; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getNotes() { return notes; }
    public void setStatus(Book.Status status) { this.status = status; }
    public Book.Status getStatus() { return status; }
}