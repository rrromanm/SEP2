package sep.jdbc;

import sep.model.Book;
import sep.model.Patron;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interface representing defining methods for managing a book information in the database.
 * @author Group 6 (Samuel, Kuba, Maciej, Romans)
 */
public interface BookDatabaseInterface
{
  void createBook(String title, String author,String year, String publisher, String isbn, String pageCount, String genre) throws SQLException;
  void deleteBook(int bookID, String title, String author, String year, String publisher, String isbn, String pageCount, String genre) throws SQLException;
  void updateBook(int bookID,String title, String author, String year, String publisher,String isbn, String pageCount, String genre) throws SQLException;
  ArrayList<Book> filter(String state, String genres, String search) throws SQLException;
  ArrayList<Book> readBooks() throws SQLException;
  ArrayList<String> readGenres() throws SQLException;
  ArrayList<Book> readDonatedBooks() throws SQLException;
  void borrowBook(Book book, Patron patron) throws SQLException;
  void returnBookToDatabase(Book book, Patron patron) throws SQLException;
  ArrayList<Book> readBorrowedBook(Patron patron) throws SQLException;
  ArrayList<Book> readHistoryOfBooks(Patron patron) throws SQLException;
  int readAmountOfBooksRead(Patron patron) throws SQLException;
  void wishlistBook(Book book, Patron patron) throws SQLException;
  boolean isWishlisted(Book book,Patron patron) throws SQLException;
  ArrayList<Book> readWishlistedBooks(Patron patron) throws SQLException;
  void deleteFromWishlist(Book book,Patron patron) throws SQLException;
  int readAmountOfBorrowedBooks(Patron patron) throws SQLException;
  int getGenreId(String genreName) throws SQLException;
  Book donateBook(String title, String author, long isbn, int year, String publisher, int pageCount, String genre, Patron patron) throws SQLException;
  ArrayList<String> checkEndingBooks(Patron patron) throws SQLException;
  void extendBook(Book book, Patron patron) throws SQLException;
}
