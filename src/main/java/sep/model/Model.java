package sep.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model {
    ArrayList<Book> getAllBooks() throws RemoteException;
}
