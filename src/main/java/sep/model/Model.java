package sep.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface defining the methods for the library management model.
 */
public interface Model {
    ArrayList<Book> getAllBooks() throws RemoteException;
    ArrayList<Book> getBorrowedBooks(Patron patron) throws RemoteException;
    ArrayList<Book> getHistoryOfBooks(Patron patron) throws RemoteException;
    ArrayList<Book> getWishlistedBooks(Patron patron) throws RemoteException;
    ArrayList<Book> getDonatedBooks() throws RemoteException;
    ArrayList<Event> getAllEvents() throws RemoteException;
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
    void deleteBook(int bookID,String title, String author, String year, String publisher, String isbn, String pageCount, String genre) throws RemoteException, SQLException;
    void createBook(String title, String author,String year, String publisher, String isbn, String pageCount, String genre) throws SQLException, RemoteException;
    void updateBook(int bookID, String title, String author, String year, String publisher, String isbn, String pageCount, String genre) throws SQLException, RemoteException;
    int getAmountOfReadBooks(Patron patron) throws RemoteException;
    int getAmountOfBorrowedBooks(Patron patron) throws RemoteException;
    void createPatron(String username, String password, String first_name, String last_name, String email, String phone_number, int fees) throws RemoteException;
    void createEvent(String title, String description, String date) throws RemoteException;
    void deleteEvent(int id) throws RemoteException;
    void updateEvent(int id, String title, String description, String eventDate) throws RemoteException;
    void deletePatron(int id) throws RemoteException;
    Patron login(String username, String password) throws RemoteException;
    boolean loginAsAdmin(String username, String password) throws RemoteException;
    void updateUsername(int userID, String newUsername) throws RemoteException;
    void updateEmail(int userID, String newEmail) throws RemoteException;
    void updatePhoneNumber(int userID, String newPhoneNumber) throws RemoteException;
    void updateFirstName(int userID, String newFirstName) throws RemoteException;
    void updateLastName(int userID, String newLastName) throws RemoteException;
    void updatePassword(int userID, String newPassowrd) throws RemoteException;
    void updateFees(int userID, int newFees) throws RemoteException;
    ArrayList<Book> filter(String genre, String state, String search) throws RemoteException;
    void borrowBooks(Book book, Patron patron) throws IOException, SQLException;
    void wishlistBook(Book book, Patron patron) throws IOException, SQLException;
    void extendBook(Book book, Patron patron) throws RemoteException, SQLException;
    boolean isWishlisted(Book book, Patron patron) throws RemoteException, SQLException;
    void approveDonatedBook(int id, String title, String author, long isbn, int year, String publisher, int pageCount, String genreId) throws SQLException, RemoteException;
    void rejectDonatedBook(int bookId) throws SQLException, RemoteException;
    void returnBookToDatabase(Book book, Patron patron) throws IOException, SQLException;
    void donateBook(String title, String author, long isbn, int year, String publisher, int pageCount, String genre, Patron patron) throws SQLException, IOException;
    void deleteFromWishlist(Book book, Patron patron) throws IOException, SQLException;
    ArrayList<String> getEndingBooks(Patron patron) throws RemoteException;
}

