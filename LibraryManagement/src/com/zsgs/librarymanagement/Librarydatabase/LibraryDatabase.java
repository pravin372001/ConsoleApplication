package com.zsgs.librarymanagement.Librarydatabase;

import com.google.gson.reflect.TypeToken;
import com.zsgs.librarymanagement.model.Admin;
import com.zsgs.librarymanagement.model.Book;
import com.zsgs.librarymanagement.model.Credential;
import com.zsgs.librarymanagement.model.IssueBook;
import com.zsgs.librarymanagement.model.Library;
import com.zsgs.librarymanagement.model.User;
import com.zsgs.librarymanagement.serialize.JsonSerialize;

import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.util.ArrayList;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;

    private Library library;
    private List<Book> bookList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<IssueBook> issueBooks = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public List<IssueBook> getIssueBooks() {
        return issueBooks;
    }

    public void setIssueBooks(List<IssueBook> issueBooks) {
        this.issueBooks = issueBooks;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    private Admin admin = new Admin(new Credential("zsgs", "Admin@123"));

    public Admin getAdmin() {
        return admin;
    }

    private LibraryDatabase() {

    }

    public static LibraryDatabase getInstance() {
        if (libraryDatabase == null) {
            libraryDatabase = new LibraryDatabase();
        }
        return libraryDatabase;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book) {
        this.bookList.add(book);
    }

    public void addNewUser(User user) {
        this.userList.add(user);
    }

    public List<User> getUsers() {
        return userList;
    }

    public boolean removeUserById(int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                userList.remove(user);
                return true;
            }
        }
        return false;
    }

    public User getUserById(int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void addIssueBooks(IssueBook issueBook) {
        this.issueBooks.add(issueBook);
    }

    public List<IssueBook> getAllIssueBooks() {
        return issueBooks;
    }

    public IssueBook getIssueBookById(int id) {
        for (IssueBook issueBook : issueBooks) {
            if (issueBook.getIssueId() == id) {
                return issueBook;
            }
        }
        return null;
    }

    public void updateIssueBook(IssueBook issueBook) {
        Iterator<IssueBook> iterator = issueBooks.iterator();
        while (iterator.hasNext()) {
            IssueBook currentIssueBook = iterator.next();
            if (currentIssueBook.getIssueId() == issueBook.getIssueId()) {
                iterator.remove();
                issueBooks.add(issueBook);
                return;
            }
        }
    }

    public void updateUser(User user) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getId() == user.getId()) {
                iterator.remove();
                userList.add(user);
                return;
            }
        }
    }

    public int isBookOverdue(int issueId) {
        IssueBook issueBook = getIssueBookById(issueId);
        Date returnDate = issueBook.getReturnDate() == null ? new Date() : issueBook.getReturnDate();
        int overdueDays = getOverdueDays(issueBook.getIssueDate(), returnDate);
        if (overdueDays >= IssueBook.getOverDueDays()) {
            return IssueBook.getOverDueDays() - overdueDays;
        }
        return -1;
    }

    private int getOverdueDays(Date issueDate, Date returnDate) {
        long dif = returnDate.getTime() - issueDate.getTime();
        return (int) (dif / (1000 * 60 * 60 * 24));
    }

    public void updateBook(Book book) {
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book currentBook = iterator.next();
            if (currentBook.getBookId() == book.getBookId()) {
                iterator.remove();
                bookList.add(book);
                return;
            }
        }
    }

    public Book getBookById(int bookId) {
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Serialization saving the state of the object into json file
    public void serializeBookList(List<Book> bookList) {
        JsonSerialize.getJsonSerialize().serialize(bookList, "src/com/zsgs/librarymanagement/data/bookList.json");
    }

    public void serializeUserList(List<User> userList) {
        JsonSerialize.getJsonSerialize().serialize(userList, "src/com/zsgs/librarymanagement/data/userList.json");
    }

    public void serializeIssueBookList(List<IssueBook> issueBooks) {
        JsonSerialize.getJsonSerialize().serialize(issueBooks,
                "src/com/zsgs/librarymanagement/data/issueBookList.json");
    }

    public void serializeLibrary(Library library) {
        JsonSerialize.getJsonSerialize().serialize(library, "src/com/zsgs/librarymanagement/data/library.json");
    }

    public void serializeAdmin(Admin admin) {
        JsonSerialize.getJsonSerialize().serialize(admin, "src/com/zsgs/librarymanagement/data/admin.json");
    }

    // Deserialization retrieving the state of the object from json file
    private void setAdmin() {
        this.admin = JsonSerialize.getJsonSerialize().deserialize("src/com/zsgs/librarymanagement/data/admin.json",
                Admin.class);
    }

    private void setIssueBooks() {
        List<IssueBook> issueBooks = JsonSerialize.getJsonSerialize()
                .deserialize("src/com/zsgs/librarymanagement/data/issueBookList.json",
                        new TypeToken<List<IssueBook>>() {
                        });
        this.issueBooks = issueBooks;
    }

    private void setUsers() {
        List<User> userList = JsonSerialize.getJsonSerialize()
                .deserialize("src/com/zsgs/librarymanagement/data/userList.json", new TypeToken<List<User>>() {
                });
        this.userList = userList;
    }

    private void setBookList() {
        List<Book> bookList = JsonSerialize.getJsonSerialize()
                .deserialize("src/com/zsgs/librarymanagement/data/bookList.json", new TypeToken<List<Book>>() {
                });
        this.bookList = bookList;
    }

    public void getDataFromDB() {
        setBookList();
        setUsers();
        setIssueBooks();
        setAdmin();
    }

}
