package sep.view;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import sep.jdbc.BookDatabaseImplementation;
import sep.model.Book;
import sep.model.Patron;
import sep.model.State;
import sep.viewmodel.AdminManageBooksViewModel;
import sep.viewmodel.AdminManageDonatedBooksViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AdminManageBooksViewController implements RemotePropertyChangeListener
{
   //BUTTONS
    @FXML
    private Button search;
    @FXML
    private Button back;
    @FXML
    private Button clear;
    @FXML
    private Button save;
    @FXML
    private Button add;
    @FXML
    private Button remove;

    //TABLE
    @FXML private TableView<Book> bookTableView;
    @FXML private TableColumn<Book,String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, Integer> yearColumn;
    @FXML private TableColumn<Book, Integer> isbnColumn;
    @FXML private TableColumn<Book, Integer> pageCountColumn;
    @FXML private TableColumn<Book, Integer> bookIdColumn;
    @FXML private TableColumn<Book, String> genreColumn;

    //TEXT FIELDS
    @FXML private TextField searchField;
    @FXML private TextField idField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private TextField yearField;
    @FXML private TextField pagesField;
    @FXML private ComboBox<String> genreField;

    private Region root;
    private ViewHandler viewHandler;
    private AdminManageBooksViewModel viewModel;
    private ReadOnlyObjectProperty<Book> selectedBook;

    //TODO: Implement publisher and other details about book into scene builder cuz someone forgot to do this
    //TODO: Implement edit, create book functionality
    public void init(ViewHandler viewHandler, AdminManageBooksViewModel viewModel, Region root)
            throws RemoteException, SQLException {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        initializeTableView();
        initializeGenreComboBox();
        this.viewModel.bindList(bookTableView.itemsProperty());
        this.selectedBook = bookTableView.getSelectionModel().selectedItemProperty();
        viewModel.loadBooks();

        viewModel.addPropertyChangeListener(evt -> {
            if ("bookList".equals(evt.getPropertyName())) {
                bookTableView.refresh();
                initializeTextFields();
            }
            if ("removeBook".equals(evt.getPropertyName())) {
                bookTableView.refresh();
                try {
                    viewModel.loadBooks();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            if("addBook".equals(evt.getPropertyName())) {
                bookTableView.refresh();
                try {
                    viewModel.loadBooks();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void initializeTableView(){
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("pageCount"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    @FXML
    public void onSelect(){
        initializeTextFields();
    }

    public void initializeGenreComboBox() throws SQLException
    {
        genreField.getItems().add("All");
        genreField.getItems().addAll(BookDatabaseImplementation.getInstance()
                .readGenres());
        genreField.getSelectionModel().selectFirst();
    }
    public void initializeTextFields() {

                titleField.setText(selectedBook.get().getTitle());
                authorField.setText(selectedBook.get().getAuthor());
                yearField.setText(String.valueOf(selectedBook.get().getYear()));
                isbnField.setText(String.valueOf(selectedBook.get().getIsbn()));
                pagesField.setText(String.valueOf(selectedBook.get().getPageCount()));
                genreField.getSelectionModel().select(selectedBook.get().getGenre());
                idField.setText(String.valueOf(selectedBook.get().getBookId()));


    }



    @FXML
    private void backButtonClicked() throws RemoteException
    {
        viewHandler.openView("adminMainView");
    }
    @FXML
    private void onSave()
    {
        //TODO logic to save detail for the books
    }
    @FXML
    private void onAdd()
    {
        try{

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onSearch(){
        try {
            viewModel.showFiltered("All","All",searchField.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onRemove() throws SQLException
    {
        try{
            viewModel.deleteBook(selectedBook.get().getBookId(),selectedBook.get().getTitle(), selectedBook.get().getAuthor(),String.valueOf(selectedBook.get().getYear()),selectedBook.get().getPublisher(),
                    String.valueOf(selectedBook.get().getIsbn()),String.valueOf(selectedBook.get().getPageCount()),selectedBook.get().getGenre());
            viewModel.loadBooks();
            bookTableView.refresh();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onClear()
    {
        searchField.clear();
        idField.clear();
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        yearField.clear();
        pagesField.clear();
        genreField.getSelectionModel().selectFirst();
    }

    public void reset()
    {
        searchField.clear();
        idField.clear();
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        yearField.clear();
        pagesField.clear();
        genreField.getSelectionModel().selectFirst();
    }

    public Region getRoot()
    {
        return root;
    }

    @Override
    public void propertyChange(RemotePropertyChangeEvent remotePropertyChangeEvent) throws RemoteException {

    }
}
