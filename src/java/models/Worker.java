package models;

public class Worker {

    public enum Credentials {
        ADMIN, ORDINARY
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

    String      bannerId;
    String      password;
    String      firstName;
    String      lastName;
    String      phoneContact;
    String      email;
    Credentials credentials;
    String      dateOfLatestCredentialsStatus;
    String      dateOfHire;
    Status      status;

    public void Worker() {
        this.bannerId = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.phoneContact = "";
        this.email = "";
        this.credentials = Credentials.ORDINARY;
        this.dateOfLatestCredentialsStatus = "";
        this.dateOfHire = "";
        this.status = Status.ACTIVE;
    }

    public String getBannerId() {return bannerId;}

    public String getPassword() {return password;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getPhoneContact() {return phoneContact;}

    public String getEmail() {return email;}

    public Credentials getCredentials() {return credentials;}

    public String getDateOfLatestCredentialsStatus() {return dateOfLatestCredentialsStatus;}

    public String getDateOfHire() {return dateOfHire;}

    public Status getStatus() {return status;}

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setDateOfLatestCredentialsStatus(String dateOfLatestCredentialsStatus) {
        this.dateOfLatestCredentialsStatus = dateOfLatestCredentialsStatus;
    }

    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}