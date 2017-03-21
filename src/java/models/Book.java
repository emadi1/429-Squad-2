package models;

public class            Book {
    public enum         Discipline {
        NONE,
        ENGLISH,
        MATHEMATICS,
        PHYSICS
    }

    public enum         Condition {
        GOOD,
        DAMAGED
    }

    public enum         Status {
        ACTIVE,
        INACTIVE
    }

    private String      barcode;
    private String      title;
    private Discipline  discipline;
    private String      authorOne;
    private String      authorTwo;
    private String      authorThree;
    private String      authorFour;
    private String      publisher;
    private Integer     yearOfPublication;
    private String      ISBN;
    private Condition   condition;
    private String      suggestedPrice;
    private String      notes;
    private Status      status;

    // Constructors
    Book() {
        this.barcode            = "";
        this.title              = "";
        this.discipline         = Discipline.NONE;
        this.authorOne          = "";
        this.authorTwo          = "";
        this.authorThree        = "";
        this.authorFour         = "";
        this.publisher          = "";
        this.yearOfPublication  = 1900;
        this.ISBN               = "";
        this.condition          = Condition.GOOD;
        this.suggestedPrice     = "";
        this.notes              = "";
        this.status             = Status.ACTIVE;
    }

    // Setters & Getters
    void                setBarcode(String barcode) { this.barcode = barcode; }
    String              getBarcode() { return this.barcode; }
    void                setTitle(String title) { this.title = title; }
    String              getTitle() { return this.title; }
    void                setDiscipline(Discipline discipline) { this.discipline = discipline; }
    Discipline          getDiscipline() { return this.discipline; }
    void                setAuthorOne(String authorOne) { this.authorOne = authorOne; }
    String              getAuthorOne() { return this.authorOne; }
    void                setAuthorTwo(String authorTwo) { this.authorTwo = authorTwo; }
    String              getAuthorTwo() { return this.authorTwo; }
    void                setAuthorThree(String authorThree) { this.authorThree = authorThree; }
    String              getAuthorThree() { return this.authorThree; }
    void                setAuthorFour(String authorFour) { this.authorFour = authorFour; }
    String              getAuthorFour() { return this.authorFour; }
    void                setPublisher(String publisher) { this.publisher = publisher; }
    String              getPublisher() { return this.publisher; }
    void                setYearOfPublication(Integer yearOfPublication) { this.yearOfPublication= yearOfPublication; }
    Integer             getYearOfPublication() { return this.yearOfPublication; }
    void                setISBN(String ISBN) { this.ISBN= ISBN; }
    String              getISBN() { return this.ISBN; }
    void                setCondition(Condition condition) { this.condition = condition; }
    Condition           getCondition() { return this.condition; }
    void                setSuggestedPrice(String suggestedPrice) { this.suggestedPrice = suggestedPrice; }
    String              getSuggestedPrice() { return this.suggestedPrice; }
    void                setNotes(String notes) { this.notes= notes; }
    String              getNotes() { return this.notes; }
    void                setStatus(Status status) { this.status = status; }
    Status              getStatus() { return this.status; }
}