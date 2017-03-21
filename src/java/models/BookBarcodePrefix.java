package models;

public class                    BookBarcodePrefix {
    private String              prefixValue;
    private Book.Discipline     discipline;

    // Constructors
    BookBarcodePrefix() {
        this.prefixValue        = "";
        this.discipline         = Book.Discipline.NONE;
    }

    // Setters & Getters
    public void                 setPrefixValue(String prefixValue) { this.prefixValue = prefixValue; }
    public String               getPrefixValue() { return this.prefixValue; }
    public void                 setDiscipline(Book.Discipline discipline) { this.discipline = discipline; }
    public Book.Discipline      getDiscipline() { return this.discipline; }
}