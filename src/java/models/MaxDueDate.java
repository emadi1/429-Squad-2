package models;

public class MaxDueDate {
    String currentMaxDueDate;

    // Constructors
    MaxDueDate() {
        this.currentMaxDueDate = "";
    }

    // Setters & Getters

    public String getCurrentMaxDueDate() { return currentMaxDueDate; }
    public void setCurrentMaxDueDate(String currentMaxDueDate) {
        this.currentMaxDueDate = currentMaxDueDate;
    }
}

