package com.zsgs.librarymanagement.managebook;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;
import com.zsgs.librarymanagement.model.Book;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ManageBookModel {
    private ManageBookView manageBookView;

    public ManageBookModel(ManageBookView manageBookView) {
        this.manageBookView = manageBookView;
    }

    public void addBook(Book book) {
        LibraryDatabase.getInstance().addBook(book);
        manageBookView.showMessage("Successfully book added");
        serializeBookList();
    }

    public List<Book> showAllBooks() {
        List<Book> bookList = LibraryDatabase.getInstance().getBookList();
        if (bookList.isEmpty()) {
            manageBookView.showMessage("No books found");
        }
        return bookList;
    }

    public Book getBookById(int bookId) {
        List<Book> bookList = LibraryDatabase.getInstance().getBookList();
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooksByName(String bookName) {
        List<Book> bookList = LibraryDatabase.getInstance().getBookList();
        List<Book> bookListByName = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getBookName().contains(bookName)) {
                bookListByName.add(book);
            }
        }
        if (bookListByName.isEmpty()) {
            manageBookView.showMessage("Books not found");
        }
        return bookListByName;
    }

    public void updateBookCount(int bookId1, int availableCount) {
        List<Book> bookList = LibraryDatabase.getInstance().getBookList();
        Iterator<Book> iterator = bookList.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId1) {
                book.setAvailableCount(availableCount);
                LibraryDatabase.getInstance().updateBook(book);
                manageBookView.showMessage("Book count updated");
                break;
            }
        }
        serializeBookList();
    }

    public void serializeBookList() {
        LibraryDatabase.getInstance().serializeBookList(LibraryDatabase.getInstance().getBookList());
        manageBookView.showMessage("Book list serialized");
    }

}
