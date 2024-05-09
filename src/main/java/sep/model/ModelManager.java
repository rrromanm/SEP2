package sep.model;


import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import sep.client.ClientImplementation;
import sep.client.ClientInterface;
import sep.model.validators.*;
import sep.shared.LibraryInterface;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

//TODO: NOTE TO ROMANS: I finished updateUsername etc. but you need to create getUsername etc.
// so we can retrieve values from db, and thus update them in ViewModel
public class ModelManager extends UnicastRemoteObject implements Model {
    private final LibraryInterface library;
    private final ClientInterface client;
    private String error;
    private RemotePropertyChangeSupport<Book> support;

    public ModelManager(LibraryInterface library) throws RemoteException {
        super();
        this.library = library;
        this.client = new ClientImplementation(library);
        this.error = "";
       this.support = new RemotePropertyChangeSupport<>();
    }
    @Override
    public void addPropertyChangeListener(RemotePropertyChangeListener<Book> listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(RemotePropertyChangeListener<Book> listener) {
        support.removePropertyChangeListener(listener);
    }
    @Override
    public synchronized void borrow(Book book, Patron patron) {
        try {
            book.borrow(book,patron);
            support.firePropertyChange("BorrowedBook",null,book);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override public synchronized void returnBook(Book book, Patron patron)
    {
        try {
            book.returnBook(book,patron);
            support.firePropertyChange("ReturnedBook",null,book);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Book> getAllBooks() throws RemoteException {
        return client.getAllBooks();
    }

    @Override
    public void createPatron(String username, String password, String first_name, String last_name, String email, String phone_number, int fees) throws RemoteException
    {
        try
        {

            UsernameValidator.validate(username);
            EmailValidator.validate(email);
            PasswordValidator.validate(password);
            PhoneValidator.validate(phone_number);
            NameValidator.validate(first_name);
            NameValidator.validate(last_name);

            this.client.createPatron(username,  password,  first_name,  last_name,  email,  phone_number,  fees);

        }
        catch (Exception e)
        {
            error = e.getMessage();
            throw new RemoteException(e.getMessage());
        }
    }


    public ArrayList<Book> filter(String genre,String state, String search) throws RemoteException {
        return client.filter(genre,state,search);
    }


    @Override
    public boolean login(String username, String password) throws RemoteException {
        try{

            return this.client.login(username, password);

        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }

    }
    public boolean loginAsAdmin(String username, String password) throws RemoteException{
        try{
            return this.client.loginAsAdmin(username, password);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }

    }

    @Override
    public void updateUsername(String oldUsername, String newUsername) throws RemoteException {
        try{
            client.updateUsername(oldUsername, newUsername);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void updateEmail(String oldEmail, String newEmail) throws RemoteException {
        try{
            client.updateEmail(oldEmail, newEmail);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void updatePhoneNumber(String oldPhoneNumber, String newPhoneNumber) throws RemoteException {
        try{
            client.updatePhoneNumber(oldPhoneNumber, newPhoneNumber);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void updateFirstName(String oldFirstName, String newFirstName) throws RemoteException {
        try{
            client.updateFirstName(oldFirstName, newFirstName);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void updateLastName(String oldLastName, String newLastName) throws RemoteException {
        try{
            client.updateLastName(oldLastName, newLastName);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    public String getError() {
        return error;
    }
}
