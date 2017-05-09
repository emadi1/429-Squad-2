package utilities;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Book;
import models.EntityBase;
import models.Rental;
import models.StudentBorrower;
import models.Worker;

import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Created by Anders and Kevin the Dynamic Duo on 3/24/2017.
 */
public class Core {

    // Attributes
    private static TableView tableView;
    private static Core core = null;
    private static Worker modWorker, user;
    private static Book modBook;
    private static Rental modRental;
    private static StudentBorrower modStudentBorrower;
    private static String language = "";
    private static Stage lastStage;
    private static int popupFlag;

    // Singleton pattern
    private Core() {
    }
    public static Core getInstance() {
        if (core == null)
            core = new Core();
        return core;
    }

    // Getters
    public TableView getTableView() {
        return tableView;
    }
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
    public Rental getModRental(){return modRental;}
    public String getLanguage() {
        return language;
    }
    public Stage getLastStage() {
        return lastStage;
    }

    public static int getPopupFlag() {
        return popupFlag;
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
            library.setProperty("CheckOutBook", "Check Out Book");
            library.setProperty("CheckInBook", "Check In Book");
            library.setProperty("ListCheckedBooks", "List Checked Books");

            // Window Titles
            library.setProperty("addWorkerTitle", "EOP Library System - Add Worker");
            library.setProperty("modifyWorkerTitle", "EOP Library System - Modify Worker");
            library.setProperty("addStudentTitle", "EOP Library System - Add Student Borrower");
            library.setProperty("modifyStudentTitle", "EOP Library System - Modify Student Borrower");
            library.setProperty("addBookTitle", "EOP Library System - Add Book");
            library.setProperty("modifyBookTitle", "EOP Library System - Modify Book");

            // Student Borrower Data Model/ Text
            library.setProperty("PromptBorrowerStatus", "Borrower Status:");
            library.setProperty("BorrowerStatus", "Borrower Status");
            library.setProperty("PromptDateOfLatestBorrowerStatus", "Date of Latest Borrower Status:");
            library.setProperty("DateOfLatestBorrowerStatus", "Date of Latest Borrower Status");
            library.setProperty("PromptDateOfRegistration", "Date of Registration:");
            library.setProperty("DateOfRegistration", "Date of Registration");
            library.setProperty("BorrowerName", "Borrower Name");

            // Book Data Model/ Text
            library.setProperty("PromptBarcode", "Barcode:");
            library.setProperty("Barcode", "Barcode");
            library.setProperty("PromptTitle", "Title:");
            library.setProperty("Title", "Title");
            library.setProperty("PromptDiscipline", "Discipline:");
            library.setProperty("Discipline", "Discipline");
            library.setProperty("Author", "Author");
            library.setProperty("PromptAuthor1", "Author 1:");
            library.setProperty("Author1", "Author 1");
            library.setProperty("PromptAuthor2", "Author 2:");
            library.setProperty("Author2", "Author 2");
            library.setProperty("PromptAuthor3", "Author 3:");
            library.setProperty("Author3", "Author 3");
            library.setProperty("PromptAuthor4", "Author 4:");
            library.setProperty("Author4", "Author 4");
            library.setProperty("PromptPublisher", "Publisher:");
            library.setProperty("Publisher", "Publisher");
            library.setProperty("PromptYearOfPublication", "Year of Publication:");
            library.setProperty("YearOfPublication", "Year of Publication");
            library.setProperty("PromptISBN", "ISBN:");
            library.setProperty("ISBN", "ISBN");
            library.setProperty("PromptBookCondition", "Book Condition:");
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

            // Rental Data Models/Text
            library.setProperty("Id", "ID");
            library.setProperty("BorrowerId", "Borrower ID");
            library.setProperty("BookId", "Book ID");
            library.setProperty("CheckOutDate", "Check Out Date");
            library.setProperty("CheckOutWorkerId", "Check Out Worker ID");
            library.setProperty("PromptCheckOutDate", "Check Out Date:");
            library.setProperty("DueDate", "Due Date");
            library.setProperty("CheckInDate", "Check In Date");
            library.setProperty("CheckInWorkerId", "Check In Worker ID");
            library.setProperty("WorkerName", "Worker Name");
            library.setProperty("CheckOutStatus", "Checkout Status:");
            library.setProperty("CheckInStatus", "Check-in Status:");
            library.setProperty("Successful", "Successful");

            // General Alerts
            library.setProperty("invalidDateFormat", "Date must be in format: mm-dd-yyyy");
            library.setProperty("completeFields", "Please fill out all necessary fields.");
            library.setProperty("emptyField", "Please complete field.");
            library.setProperty("dateFormat", "MM-DD-YYYY");
            library.setProperty("invalidPassword", "Invalid Password");
            library.setProperty("doubleClickModify", "- Double click to modify -");

            // Student Alerts
            library.setProperty("addStudentSuccess", "Student Borrower successfully added!");
            library.setProperty("addStudentFail", "Failed to add student borrower.");
            library.setProperty("modifyStudentSuccess", "Student Borrower updated successfully!");
            library.setProperty("modifyStudentFail", "Failed to modify student borrower.");
            library.setProperty("deleteStudentSuccess", "Student Borrower deleted.");
            library.setProperty("deleteStudentFail", "Failed to delete student borrower.");
            library.setProperty("noStudent", "No Student exists with that Banner ID");

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
            library.setProperty("invalidPassword", "Invalid Password.");
            library.setProperty("invalidCredentials", "Invalid Credentials");
            library.setProperty("invalidPhoneFormat", "Phone must be in format: '###-##########'");
            library.setProperty("noWorker", "No worker with that Banner ID");

            //Rental Alerts
            library.setProperty("invalidBorrowerStatus", "Borrower Status is 'Delinquent.'");
            library.setProperty("AdministratorOverride", "Administrator Override.");
            library.setProperty("BookNotCheckedOut", "Book with ID not currently checked out: ");
            library.setProperty("BookCheckedOut", "Book is currently checked out.");
            library.setProperty("NoBookWithId", "No book matching ID: ");
            library.setProperty("CheckOutSuccess", "successfully checked out by");
            library.setProperty("CheckInSuccess", "Book successfully checked in!");


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
            library.setProperty("Override", "Override?");
            library.setProperty("Submit", "Submit");
            library.setProperty("Verify", "Verify");

        } else if (language.equals("fr_FR")) {
            // Main View Buttons/ Text
            library.setProperty("welcome", "EOP Bibliotheque Systeme");
            library.setProperty("signIn", "Connectez-vous ci-dessous");
            library.setProperty("SignIn", "Se Connecter");
            library.setProperty("WorkerTransactions", "Operations des Travaileurs");
            library.setProperty("BookTransactions", "Transactions de Livres");
            library.setProperty("StudentTransactions", "Transactions Etudiantes");
            library.setProperty("CheckOutBook", "Consulter les Livres");
            library.setProperty("CheckInBook", "Verifier dans les Livres");
            library.setProperty("ListCheckedBooks", "Liste des Livres Verifiés");

            // Window Titles
            library.setProperty("addWorkerTitle", "EOP Bibliotheque Systeme - Ajouter un travailleur");
            library.setProperty("modifyWorkerTitle", "EOP Bibliotheque Systeme - Modifier travailleur");
            library.setProperty("addStudentTitle", "EOP Bibliotheque Systeme - Ajouter l'etudiant emprunteur");
            library.setProperty("modifyStudentTitle", "EOP Bibliotheque Systeme - Modifier etudiant emprunteur");
            library.setProperty("addBookTitle", "EOP Bibliotheque Systeme - Ajouter un livre");
            library.setProperty("modifyBookTitle", "EOP Bibliotheque Systeme - Modifier le livre");

            // Student Borrower Data Model/ Text
            library.setProperty("PromptBorrowerStatus", "Statut de l'emprunteur");
            library.setProperty("BorrowerStatus", "Statut de l'emprunteur");
            library.setProperty("PromptDateOfLatestBorrowerStatus", "Date du dernier statut de l'emprunteur:");
            library.setProperty("DateOfLatestBorrowerStatus", "Date du dernier statut de l'emprunteur");
            library.setProperty("PromptDateOfRegistration", "Date d'enregistrement:");
            library.setProperty("DateOfRegistration", "Date d'enregistrement");
            library.setProperty("BorrowerName", "Nom d'étudiant");
            library.setProperty("StudentBorrower", "Étudiant:");


            // Book Data Model/ Text
            library.setProperty("PromptBarcode", "Code barre:");
            library.setProperty("Barcode", "Code barre");
            library.setProperty("PromptTitle", "Titre:");
            library.setProperty("Title", "Titre");
            library.setProperty("PromptDiscipline", "Discipline:");
            library.setProperty("Discipline", "Discipline");
            library.setProperty("Author", "Auteur");
            library.setProperty("PromptAuthor1", "Auteur 1:");
            library.setProperty("Author1", "Auteur 1");
            library.setProperty("PromptAuthor2", "Auteur 2:");
            library.setProperty("Author2", "Auteur 2");
            library.setProperty("PromptAuthor3", "Auteur 3:");
            library.setProperty("Author3", "Auteur 3");
            library.setProperty("PromptAuthor4", "Auteur 4:");
            library.setProperty("Author4", "Auteur 4");
            library.setProperty("PromptPublisher", "Editeur:");
            library.setProperty("Publisher", "Editeur");
            library.setProperty("PromptYearOfPublication", "Année de publication:");
            library.setProperty("YearOfPublication", "Année de publication");
            library.setProperty("PromptISBN", "ISBN:");
            library.setProperty("ISBN", "ISBN");
            library.setProperty("PromptBookCondition", "Etat du livre:");
            library.setProperty("BookCondition", "Etat du livre");
            library.setProperty("PromptSuggestedPrice", "Prix Suggere:");
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
            library.setProperty("PromptFirstName", "Prénom:");
            library.setProperty("FirstName", "Prénom");
            library.setProperty("PromptFirstName", "Prénom:");
            library.setProperty("LastName", "Nom de Famille");
            library.setProperty("PromptLastName", "Nom de Famille:");
            library.setProperty("PromptContactPhone", "Numéro du Contact:");
            library.setProperty("ContactPhone", "Numéro du Contact");
            library.setProperty("PromptEmail", "Email:");
            library.setProperty("Email", "Email");
            library.setProperty("PromptCredentials", "Lettres de créance:");
            library.setProperty("Credentials", "Credentials");
            library.setProperty("PromptDateOfLatestCredentialsStatus", "Date du dernier statut d'accréditation:");
            library.setProperty("DateOfLatestCredentialsStatus", "Date du dernier statut d'accréditation");
            library.setProperty("PromptDateOfHire", "Date d'embauche:");
            library.setProperty("DateOfHire", "Date d'embauche");
            library.setProperty("PromptStatus", "Statut:");
            library.setProperty("Status", "Statut");

            // Rental Data Models/Text
            library.setProperty("Id", "ID");
            library.setProperty("BorrowerId", "identifiant de l'emprunteur"); //#######################################CHECK
            library.setProperty("BookId", "ID du livre"); //#######################################CHECK
            library.setProperty("CheckOutDate", "Date de paiement"); //#######################################CHECK
            library.setProperty("CheckOutWorkerId", "Identifiant de travail"); //#######################################CHECK
            library.setProperty("DueDate", "Date d'échéance");//#####################CHECK
            library.setProperty("CheckInDate", "Date d'arrivée");//#####################CHECK
            library.setProperty("CheckInWorkerId", "Vérifier l'identifiant du travailleur");//#######################CHECK
            library.setProperty("WorkerName", "Nom du travailleur");


            // General Alerts
            library.setProperty("invalidDateFormat", "La date doit être au format: DD-MM-YYYY");
            library.setProperty("completeFields", "Veuillez remplir tout les champs nécessaires.");
            library.setProperty("emptyField", "Veuillez remplir le champ.");
            library.setProperty("dateFormat", "jj-mm-aaaa");
            library.setProperty("invalidPassword", "Mot de passe incorrect.");
            library.setProperty("doubleClickModify", "- Double cliquer pour modifier -");

            // Student Alerts
            library.setProperty("addStudentSuccess", "Etudiant a été ajouté avec succès!");
            library.setProperty("addStudentFail", "L'ajout de l'étudiant a échoué.");
            library.setProperty("modifyStudentSuccess", "Etudiant a été mis à jour avec succès!");
            library.setProperty("modifyStudentFail", "La mise à jour de l'étudiant a échoué.");
            library.setProperty("deleteStudentSuccess", "Etudiant a été supprimé avec succès.");
            library.setProperty("deleteStudentFail", "La suppression de l'étudiant a échoué.");
            library.setProperty("noStudent", "");

            // Book Alerts
            library.setProperty("invalidBarcodeLength", "Le code barre doit avoir 5 chiffres.");
            library.setProperty("existingBarcode", "Le code barre existe déjà: ");
            library.setProperty("yearFormat", "L'annee de publication doit être au format: yyyy");
            library.setProperty("addBookSuccess", "Le livre a été ajouté avec succès!");
            library.setProperty("addBookFail", "L'ajout du livre a échoué.");
            library.setProperty("modifyBookSuccess", "Le livre a été mis à jour avec succès!");
            library.setProperty("modifyBookFail", "La mise à jour du livre a échoué.");

            // Worker Alerts
            library.setProperty("invalidBannerIdFormat", "BannerID doit avoir 9 chiffres uniquement.");
            library.setProperty("modifyWorkerSuccess", "Ouvrier mis a jour avec succès!");
            library.setProperty("modifyWorkerFail", "La mise à jour du ouvrier a échoué.");
            library.setProperty("existingBannerId", "Le BannerID existe déjà dans le systeme.");
            library.setProperty("addWorkerSuccess", "Ouvrier ajouté avec succès!");
            library.setProperty("addWorkerFail", "N'a pas pu ajouter l'ouvrier.");
            library.setProperty("invalidCredentials", "Les informations d'identification sont invalides.");
            library.setProperty("invalidPhoneFormat", "Format du téléphone invalide"); //#######################################CHECK
            library.setProperty("noWorker", "");

            // Rental Alerts
            library.setProperty("invalidBorrowerStatus", "Statut de l'emprunteur invalide"); //#######################################CHECK
            library.setProperty("AdministratorOverride", "L'annulation de l'administrateur"); //#######################################CHECK
            library.setProperty("BookNotCheckedOut", "Livre non sorti: "); //#######################################CHECK
            library.setProperty("BookCheckedOut", "Livre sorti"); // ############################################CHECK
            library.setProperty("NoBookWithId", "Aucun livre avec cet identifiant: "); //#######################################CHECK
            library.setProperty("CheckOutSuccess", "Vérifier le succès por"); //#######################################CHECK
            library.setProperty("CheckInSuccess", "Vérifier le succès"); //#######################################CHECK
            library.setProperty("CheckOutStatus", "Statut de caisse");
            library.setProperty("CheckInStatus", "Statut de verification");
            library.setProperty("Successful", "Réussi");

            // Buttons
            library.setProperty("Modify", "Modifier");
            library.setProperty("Add", "Ajouter");
            library.setProperty("Delete", "Effacer");
            library.setProperty("Good", "Bien");
            library.setProperty("Damaged", "Endommagé");
            library.setProperty("Active", "Actif");
            library.setProperty("Inactive", "Inactif");
            library.setProperty("Search", "Chercher");
            library.setProperty("Administrator", "Administrateur");
            library.setProperty("Ordinary", "Ordinaire");
            library.setProperty("GoodStanding", "Bonne qualité");
            library.setProperty("Delinquent", "Délinquant");
            library.setProperty("Submit", "Entrer");
            library.setProperty("Verify", "Vérifier");
            library.setProperty("Override", "Surcharger");
        }
        return library;
    }

    // Setters
    public void setTableView(TableView table) {
        tableView = table;
    }
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
    public void setModRental(Rental rental){modRental = rental;}
    public void setLanguage(String lang) {
        language = lang;
    }
    public void setStage(Stage stage) {
        lastStage = stage;
    }

    public static void setPopupFlag(int popupFlag) {
        Core.popupFlag = popupFlag;
    }

    // Generators/Formatting
    public static String generateDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        if (Integer.parseInt(day) < 10) day = '0' + day;
        if (Integer.parseInt(month) < 10) month = '0' + month;
        if (language.equals("fr_FR")) return day + '-' + month + '-' + year;
        else return month + '-' + day + '-' + year;
    }
    public static String generateEnglishDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        if (Integer.parseInt(day) < 10) day = '0' + day;
        if (Integer.parseInt(month) < 10) month = '0' + month;
        return month + '-' + day + '-' + year;
    }
    public static String formatDateToEnglish(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6);
        return month + '-' + day + '-' + year;
    }
    public static boolean isNumeric(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
