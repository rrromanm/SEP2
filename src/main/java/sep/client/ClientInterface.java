package sep.client;

import sep.model.Book;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface {
    ArrayList<Book> getAllBooks() throws RemoteException;
    void createPatron(String username, String password, String first_name, String last_name, String email, long phone_number) throws RemoteException;
    void login(String username, String password) throws RemoteException;
}
