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
            library.setProperty("addStudentTitle", "Add Student Borrower");
            library.setProperty("modifyStudentTitle", "Modify Student Borrower");
            library.setProperty("addBookTitle", "Add Book");
            library.setProperty("modifyBook", "Modify Book");

            // Student Borrower Data Model/ Text
            library.setProperty("PromptBorrowerStatus", "Borrower Status:");
            library.setProperty("BorrowerStatus", "Borrower Status");
            library.setProperty("PromptDateOfLatestBorrowerStatus", "Date of Latest Borrower Status:");
            library.setProperty("DateOfLatestBorrowerStatus", "Date of Latest Borrower Status");
            library.setProperty("PromptDateOfRegistration", "Date of Registration:");
            library.setProperty("DateOfRegistration", "Date of Registration");

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
            library.setProperty("YearOfPublication", "Year of Publication");
            library.setProperty("isbn", "ISBN:");
            library.setProperty("ISBN", "ISBN");
            library.setProperty("bookCondition", "Book Condition:");
            library.setProperty("BookCondition", "Book Condition");
            library.setProperty("PromptSuggestedPrice", "Suggested Price:");
            library.setProperty("SuggestedPrice", "Suggested Price");
            library.setProperty("PromptNotes", "Notes:");
            library.setProperty("Notes", "Notes");
            library.setProperty("PromptStatus", "Status:");
            library.setProperty("Status", "Status");

            // Worker Data Models/Text
            library.setProperty("PromptBannerId", "Banner ID:");
            library.setProperty("BannerId", "Banner ID");
            library.setProperty("PromptPassword", "Password:");
            library.setProperty("Password", "Password");
            library.setProperty("PromptFirstName", "First Name:");
            library.setProperty("FirstName", "First Name");
            library.setProperty("PromptFirstName", "First Name:");
            library.setProperty("LastName", "Last Name");
            library.setProperty("PromptLastName", "Last Name:");
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
            library.setProperty("invalidBarcodeLength", "Barcode must be 5 digits long.");
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
            library.setProperty("GoodStanding", "Good Standing");
            library.setProperty("Delinquent", "Delinquent");

        } else if (language.equals("fr_FR")) {
            // Main View Buttons/ Text
            library.setProperty("bienvenue", "EOP Bibliotheque Systeme");
            library.setProperty("signIn", "Connectez-vous cidessous");
            library.setProperty("SignIn", "Se Connecter");
            library.setProperty("WorkerTransactions", "Operations des Travaileurs");
            library.setProperty("BookTransactions", "Transactions de Livres");
            library.setProperty("StudentTransactions", "Transactions Esudiantes");
            library.setProperty("CheckOutBook", "Consultez les Livres");
            library.setProperty("CheckInBook", "Verifier dans les Livres");
            library.setProperty("ListCheckedBooks", "Liste des Livres Verifies");

            // Window Titles
            library.setProperty("addWorker", "Ajounter un travailleur");
            library.setProperty("modifyWorker", "Modifier travailleur");
            library.setProperty("addStudentTitle", "Ajounter l'etudiant emprunteur");
            library.setProperty("modifyStudentTitle", "Modifier etudiant emprunteur");
            library.setProperty("addBookTitle", "Ajouter un livre");
            library.setProperty("modifyBook", "Modifier le livre");

            // Student Borrower Data Model/ Text
            library.setProperty("PromptBorrowerStatus", "Statut de l'emprunteur");
            library.setProperty("BorrowerStatus", "Statut de l'emprunteur");
            library.setProperty("PromptDateOfLatestBorrowerStatus", "Date du derier statut de l'emprunteur:");
            library.setProperty("DateOfLatestBorrowerStatus", "Date du derier statut de l'emprunteur");
            library.setProperty("PromptDateOfRegistration", "Date d'enregistrement:");
            library.setProperty("DateOfRegistration", "Date d'enregistremen");

            // Book Data Model/ Text
            library.setProperty("barcode", "Code a barre:");
            library.setProperty("Barcode", "Code a barre");
            library.setProperty("title", "Titre:");
            library.setProperty("Title", "Titre");
            library.setProperty("discipline", "Discipline:");
            library.setProperty("Discipline", "Discipline");
            library.setProperty("Author", "Auteur");
            library.setProperty("author1", "Auteur 1:");
            library.setProperty("Author1", "Auteur 1");
            library.setProperty("author2", "Auteur 2:");
            library.setProperty("Author2", "Auteur 2");
            library.setProperty("author3", "Auteur 3:");
            library.setProperty("Author3", "Auteur 3");
            library.setProperty("author4", "Auteur 4:");
            library.setProperty("Author4", "Auteur 4");
            library.setProperty("publisher", "Editeur:");
            library.setProperty("Publisher", "Editeur");
            library.setProperty("yearOfPublication", "Annee de publication:");
            library.setProperty("YearOfPublication", "Annee de publication");
            library.setProperty("isbn", "ISBN:");
            library.setProperty("ISBN", "ISBN");
            library.setProperty("bookCondition", "Etat du livre:");
            library.setProperty("BookCondition", "Etat du livre");
            library.setProperty("suggestedPrice", "Prix Suggere:");
            library.setProperty("SuggestedPrice", "Prix Suggere");
            library.setProperty("PromptNotes", "Remarques:");
            library.setProperty("Notes", "Remarques");
            library.setProperty("PromptStatus", "Statut:");
            library.setProperty("Status", "Statut");

            // Worker Data Models/Text
            library.setProperty("PromptBannerId", "Banner ID:");
            library.setProperty("BannerId", "Banner ID");
            library.setProperty("PromptPassword", "Mot de Passe:");
            library.setProperty("Password", "Mot de Passe");
            library.setProperty("PromptFirstName", "Prenom:");
            library.setProperty("FirstName", "Prenom");
            library.setProperty("PromptFirstName", "Prenom:");
            library.setProperty("LastName", "Nom de Famille");
            library.setProperty("PromptLastName", "Nom de Famille:");
            library.setProperty("PromptContactPhone", "Numero du Contact:");
            library.setProperty("ContactPhone", "Numero du Contact");
            library.setProperty("PromptEmail", "Email:");
            library.setProperty("Email", "Email");
            library.setProperty("PromptCredentials", "Lettres de Creance:");
            library.setProperty("Credentials", "Credentials");
            library.setProperty("PromptDateOfLatestCredentialsStatus", "Date du Dernier Statut d'accreditation:");
            library.setProperty("DateOfLatestCredentialsStatus", "Date du Dernier Statut d'accreditation");
            library.setProperty("PromptDateOfHire", "Date d'embuche:");
            library.setProperty("DateOfHire", "Date d'embuche");
            library.setProperty("PromptStatus", "Statut:");
            library.setProperty("Status", "Statut");

            // General Alerts
            library.setProperty("invalidDateFormat", "La date doit etre en format: mm-dd-yyyy");
            library.setProperty("completeFields", "Veuillez remplir tous les champs necessaires.");
            library.setProperty("emptyField", "Veuillez remplir le champ.");

            // Student Alerts
            library.setProperty("addStudentSuccess", "Etudiant a reussi a ajouter!");
            library.setProperty("addStudentFail", "N'a pas pu ajouter l'etudiant emprunteur.");
            library.setProperty("modifyStudentSuccess", "Emprunteur etudiant mis a jour avec succes!");
            library.setProperty("modifyStudentFail", "N'a pas reussi a modifier l'emprunteur etudiant.");
            library.setProperty("deleteStudentSuccess", "Etudiant emprunteur supprime.");
            library.setProperty("deleteStudentFail", "N'a pas reussi a supprimer l'emprunteur etudiant.");

            // Book Alerts
            library.setProperty("invalidBarcodeLength", "Le code a barres doit complorter 5 chiffres.");
            library.setProperty("existingBarcode", "Le code a barre existe deja: ");
            library.setProperty("yearFormat", "L'annee de publication doit etre en format: yyyy");
            library.setProperty("addBookSuccess", "Livre ajoute avec succes!");
            library.setProperty("addBookFail", "N'a pas pu ajouter de livre.");
            library.setProperty("modifyBookSuccess", "Livre mis a jour avec succes!");
            library.setProperty("modifyBookFail", "A echoue a mettre a jour le livre.");

            // Worker Alerts
            library.setProperty("invalidBannerIdFormat", "BannerID doit avoir des 9 chiffres seul.");
            library.setProperty("modifyWorkerSuccess", "Travailleur mis a jour avec succes!");
            library.setProperty("modifyWorkerFail", "N'a pas reussi a mettre a jour.");
            library.setProperty("existingBannerId", "BannerID existe deja dans le systeme.");
            library.setProperty("addWorkerSuccess", "Ouvrier ajoute avec succes!");
            library.setProperty("addWorkerFail", "N'a pas pu ajouter de travailleur.");

            // Buttons
            library.setProperty("Modify", "Modifier");
            library.setProperty("Add", "Ajouter");
            library.setProperty("Delete", "Effacer");
            library.setProperty("Good", "Bien");
            library.setProperty("Damaged", "Endommage");
            library.setProperty("Active", "Actif");
            library.setProperty("Inactive", "Inactif");
            library.setProperty("Search", "Chercher");
            library.setProperty("Administrator", "Administrateur");
            library.setProperty("Ordinary", "Ordinaire");
            library.setProperty("GoodStanding", "Bonne qualite");
            library.setProperty("Delinquent", "Delinquant");
        }

        return library;
    }
}
