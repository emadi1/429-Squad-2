package models;

import database.DBKey;
import utilities.Core;

import java.util.Enumeration;
import java.util.Properties;


/**
 * Created by id135 on 5/6/2017.
 */
public class RentedBook {

    private Properties language = Core.getInstance().getLang();
    private Properties persistentState;
    public RentedBook(Properties props) {
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements()) {
            String nextKey = (String) allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null) persistentState.setProperty(nextKey, nextValue);
        }
    }

    // Getters
    public Object getState(String key) {
        return persistentState.getProperty(key);
    }
    public String getBookId() {
        return persistentState.getProperty(DBKey.BOOK_ID);
    }
    public String getTitle() {
        return persistentState.getProperty(DBKey.TITLE);
    }
    public String getWorkerName() {
        return persistentState.getProperty(DBKey.WORKER_NAME);
    }
    public String getBorrowerId() {
        return persistentState.getProperty(DBKey.BORROWER_ID);
    }
    public String getBorrowerName() {
        return persistentState.getProperty(DBKey.BORROWER_NAME);
    }
    public String getCheckOutDate() {
        return persistentState.getProperty(DBKey.CHECK_OUT_DATE);
    }
    public String getDueDate() {
        return persistentState.getProperty(DBKey.DUE_DATE);
    }

    // Setters
    public void setState(String key, String value) {
        persistentState.setProperty(key, value);
    }
    public void setBookId(String id) {
        persistentState.setProperty(DBKey.BOOK_ID, id);
    }
    public void setTitle(String title) {
        persistentState.setProperty(DBKey.TITLE, title);
    }
    public void setWorkerName(String name) {
        persistentState.setProperty(DBKey.WORKER_NAME, name);
    }
    public void setBorrowerId(String id) {
        persistentState.setProperty(DBKey.BORROWER_ID, id);
    }
    public void setBorrowerName(String name) {
        persistentState.setProperty(DBKey.BORROWER_NAME, name);
    }
    public void setCheckOutDate(String date) {
        persistentState.setProperty(DBKey.CHECK_OUT_DATE, date);
    }
    public void setDueDate(String date) {
        persistentState.setProperty(DBKey.DUE_DATE, date);
    }

    public void formatData() {
        String date = persistentState.getProperty(DBKey.CHECK_OUT_DATE);
        String month = date.substring(0, 2);
        String day = date.substring(3, 5);
        String year = date.substring(6);
        if (Core.getInstance().getLanguage().equals("fr_FR"))
            persistentState.setProperty(DBKey.CHECK_OUT_DATE, day + '-' + month + '-' + year);
        else persistentState.setProperty(DBKey.CHECK_OUT_DATE, month + '-' + day + '-' + year);
        date = persistentState.getProperty(DBKey.DUE_DATE);
        month = date.substring(0, 2);
        day = date.substring(3, 5);
        year = date.substring(6);
        if (Core.getInstance().getLanguage().equals("fr_FR"))
            persistentState.setProperty(DBKey.DUE_DATE, day + '-' + month + '-' + year);
        else persistentState.setProperty(DBKey.DUE_DATE, day + '-' + month + '-' + year);
    }
    public String toString() {
        return  persistentState.getProperty(DBKey.ID) + "; " +
                persistentState.getProperty(DBKey.BOOK_ID) + "; " +
                persistentState.getProperty(DBKey.TITLE) + "; " +
                persistentState.getProperty(DBKey.BORROWER_ID) + "; " +
                persistentState.getProperty(DBKey.BORROWER_NAME) + "; " +
                persistentState.getProperty(DBKey.CHECK_OUT_DATE) + "; " +
                persistentState.getProperty(DBKey.DUE_DATE);
    }
    public String toolTipToString() {
        return  language.getProperty(DBKey.ID) + ": " + persistentState.getProperty(DBKey.ID) + "\n" +
                language.getProperty(DBKey.BOOK_ID) + ": " + persistentState.getProperty(DBKey.BOOK_ID) + "\n" +
                language.getProperty(DBKey.TITLE) + ": " + persistentState.getProperty(DBKey.TITLE) + "\n" +
                language.getProperty(DBKey.BORROWER_ID) + ": " + persistentState.getProperty(DBKey.BORROWER_ID) + "\n" +
                language.getProperty(DBKey.BORROWER_NAME) + ": " + persistentState.getProperty(DBKey.BORROWER_NAME) + "\n" +
                language.getProperty(DBKey.CHECK_OUT_DATE) + ": " + persistentState.getProperty(DBKey.CHECK_OUT_DATE) + "\n" +
                language.getProperty(DBKey.DUE_DATE) + ": " + persistentState.getProperty(DBKey.DUE_DATE);
    }
}
