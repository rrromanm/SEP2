package sep.shared;

import dk.via.remote.observer.RemotePropertyChangeListener;
import sep.model.Book;
import sep.model.Event;
import sep.model.Patron;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The ConnectorInterface provides methods for interacting with the application's database and performing various operations related to books, events, and patrons.\
 *
 * @author Group 6 (Samuel, Kuba, Maciej, Romans)
 */
public interface ConnectorInterface extends Remote {
    ArrayList<Book> getAllBooks() throws RemoteException;
    ArrayList<Book> getBorrowedBooks(Patron patron) throws RemoteException;
    ArrayList<Book> getHistoryOfBooks(Patron patron) throws RemoteException;
    ArrayList<Book> getWishlistedBooks(Patron patron) throws RemoteException;
    ArrayList<Book> getDonatedBooks() throws RemoteException;
    int getAmountOfReadBooks(Patron patron) throws RemoteException;
    int getAmountOfBorrowedBooks(Patron patron) throws RemoteException;
    ArrayList<Book> filter(String genre,String state, String search) throws RemoteException;
    void createPatron(String username, String password, String first_name, String last_name, String email, String phone_number, int fees) throws RemoteException, SQLException;
    void createEvent(String title, String description, String eventDate) throws RemoteException;
    void deleteEvent(int id) throws RemoteException;
    void deletePatron(int id) throws RemoteException;
    void updateEvent(int id, String title, String description, String eventDate) throws SQLException, RemoteException;
    void createBook(String title, String author,String year, String publisher, String isbn, String pageCount, String genre) throws SQLException, RemoteException;
    void updateBook(int bookID, String title, String author, String year, String publisher, String isbn, String pageCount, String genre) throws SQLException, RemoteException;
    Patron login(String username, String password) throws RemoteException;
    boolean loginAsAdmin(String username, String password) throws RemoteException;
    void updateUsername(int userID, String newUsername) throws RemoteException;
    void updateEmail(int userID, String newEmail) throws RemoteException;
    void updatePhoneNumber(int userID, String newPhoneNumber) throws RemoteException;
    void updateFirstName(int userID, String newFirstName) throws RemoteException;
    void updateLastName(int userID, String newLastName) throws RemoteException;
    void updatePassword(int userID, String newPassword) throws RemoteException;
    void updateFees(int userID, int newFees) throws RemoteException;
    void borrowBooks(Book book, Patron patron) throws RemoteException, SQLException;
    void wishlistBook(Book book, Patron patron) throws RemoteException, SQLException;
    void extendBook(Book book, Patron patron) throws RemoteException, SQLException;
    boolean isWishlisted(Book book, Patron patron) throws RemoteException,SQLException;
    void returnBookToDatabase(Book book, Patron patron) throws RemoteException, SQLException;
    void deleteBook(int bookID,String title, String author, String year, String publisher, String isbn, String pageCount, String genre) throws RemoteException, SQLException;
    void deleteFromWishlist(Book book,Patron patron) throws RemoteException, SQLException;
    void addRemotePropertyChangeListener(RemotePropertyChangeListener<Patron> listener) throws RemoteException;
    Book donateBook(String title, String author, long isbn, int year, String publisher, int pageCount, String genre, Patron patron) throws RemoteException, SQLException;
    ArrayList<Event> getAllEvents() throws RemoteException;
    ArrayList<String> getEndingBooks(Patron patron) throws RemoteException;
    void approveDonatedBook(int id,String title, String author, long isbn, int year, String publisher, int pageCount, String genreId) throws SQLException, RemoteException;
    void rejectDonatedBook(int bookId) throws SQLException, RemoteException;
}
