package com.zsgs.librarymanagement.issuebook;

import java.util.Date;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;
import com.zsgs.librarymanagement.model.Book;
import com.zsgs.librarymanagement.model.IssueBook;
import com.zsgs.librarymanagement.model.User;

public class IssueBookModel {
    private IssueBookView issueBookView;

    public IssueBookModel(IssueBookView issueBookView) {
        this.issueBookView = issueBookView;
    }

    public void issueBook(int userId, int bookId) {
        IssueBook issueBook = new IssueBook(userId, bookId);
        Book book = LibraryDatabase.getInstance().getBookById(bookId);
        if (book == null) {
            issueBookView.showMessage("Book not found");
            return;
        }
        if (LibraryDatabase.getInstance().getUserById(userId) == null) {
            issueBookView.showMessage("User Not found");
            return;
        }
        LibraryDatabase.getInstance().addIssueBooks(issueBook);
        book.setAvailableCount(book.getAvailableCount() - 1);
        LibraryDatabase.getInstance().updateBook(book);
        serializeIssueBookList();
        issueBookView.showMessage("Book issued successfully");
    }

    private void serializeIssueBookList() {
        LibraryDatabase.getInstance().serializeIssueBookList(LibraryDatabase.getInstance().getAllIssueBooks());
        issueBookView.showMessage("Issue book list serialized successfully");
    }

    public void returnBook(int issueId) {
        IssueBook issueBook = LibraryDatabase.getInstance().getIssueBookById(issueId);
        issueBook.setReturnDate(new Date());
        Book book = LibraryDatabase.getInstance().getBookById(issueBook.getBookId());
        book.setAvailableCount(book.getAvailableCount() + 1);
        LibraryDatabase.getInstance().updateIssueBook(issueBook);
        int overdueDays = LibraryDatabase.getInstance().isBookOverdue(issueId);
        if (overdueDays > 0) {
            User user = LibraryDatabase.getInstance().getUserById(issueBook.getUserId());
            user.setFine(overdueDays * 10);
            LibraryDatabase.getInstance().updateUser(user);
        }
        issueBookView.showMessage("Book returned successfully");
        serializeIssueBookList();
    }

    public void showAllOverDues() {
        for (IssueBook issueBook : LibraryDatabase.getInstance().getAllIssueBooks()) {
            if (LibraryDatabase.getInstance().isBookOverdue(issueBook.getIssueId()) > 0) {
                issueBookView.showMessage("Issue Id: " + issueBook.getIssueId() + " User: "
                        + LibraryDatabase.getInstance().getUserById(issueBook.getUserId()) + " Book Id: "
                        + issueBook.getBookId() + " Overdue Days: "
                        + LibraryDatabase.getInstance().isBookOverdue(issueBook.getIssueId()) + " Overdue Fine: "
                        + LibraryDatabase.getInstance().isBookOverdue(issueBook.getIssueId()) * 10);
            }
        }
    }

    public void issueHistory() {
        for (IssueBook issueBook : LibraryDatabase.getInstance().getAllIssueBooks()) {
            if (issueBook.getReturnDate() != null) {
                issueBookView.showMessage("Issue Id: " + issueBook.getIssueId() + " User: "
                        + LibraryDatabase.getInstance().getUserById(issueBook.getUserId()) + " Book Id: "
                        + issueBook.getBookId() + " Issue Date: " + issueBook.getIssueDate() + " Return Date: "
                        + issueBook.getReturnDate());
            }

        }
    }

    public void issuedBooks() {
        if (LibraryDatabase.getInstance().getAllIssueBooks().isEmpty()) {
            issueBookView.showMessage("No books issued");
            return;
        }
        for (IssueBook issueBook : LibraryDatabase.getInstance().getAllIssueBooks()) {
            if (issueBook.getReturnDate() == null) {
                issueBookView.showMessage("Issue Id: " + issueBook.getIssueId() + " User: "
                        + LibraryDatabase.getInstance().getUserById(issueBook.getUserId()) + " Book Id: "
                        + issueBook.getBookId() + " Issue Date: " + issueBook.getIssueDate());
            }
        }
    }

    public void issueBooks(int userId) {
        if (LibraryDatabase.getInstance().getUserById(userId) == null) {
            issueBookView.showMessage("User Not found");
            return;
        }
        boolean hasBook = false;
        for (IssueBook issueBook : LibraryDatabase.getInstance().getAllIssueBooks()) {
            if (issueBook.getUserId() == userId) {
                hasBook = true;
                issueBookView.showMessage("Issue Id: " + issueBook.getIssueId() + " User: "
                        + LibraryDatabase.getInstance().getUserById(issueBook.getUserId()) + " Book Id: "
                        + issueBook.getBookId() + " Issue Date: " + issueBook.getIssueDate());
            }
        }
        if (!hasBook) {
            issueBookView.showMessage("No books issued to this user");
            return;
        }
    }

    public void showAllOverDues(int userId) {
        if (LibraryDatabase.getInstance().getUserById(userId) == null) {
            issueBookView.showMessage("User Not found");
            return;
        }
        boolean flag = false;
        for (IssueBook issueBook : LibraryDatabase.getInstance().getAllIssueBooks()) {
            if (LibraryDatabase.getInstance().isBookOverdue(issueBook.getIssueId()) > 0
                    && issueBook.getUserId() == userId) {
                flag = true;
                issueBookView.showMessage("Issue Id: " + issueBook.getIssueId() + " User: "
                        + LibraryDatabase.getInstance().getUserById(issueBook.getUserId()) + " Book Id: "
                        + issueBook.getBookId() + " Overdue Days: "
                        + LibraryDatabase.getInstance().isBookOverdue(issueBook.getIssueId()) + " Overdue Fine: "
                        + LibraryDatabase.getInstance().isBookOverdue(issueBook.getIssueId()) * 10);
            }
        }
        if (!flag) {
            issueBookView.showMessage("No books issued to this user");
        }
    }

}
