package com.zsgs.librarymanagement.managebook;

import java.util.List;
import java.util.Scanner;

import com.zsgs.librarymanagement.model.Book;

public class ManageBookView {
    ManageBookModel manageBookModel;

    Scanner scanner = new Scanner(System.in);

    public ManageBookView() {
        manageBookModel = new ManageBookModel(this);
    }

    public void initAdd() {
        System.out.flush();
        scanner.nextLine();
        System.out.print("Enter the book name: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter the author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter the publication name: ");
        String publication = scanner.nextLine();
        System.out.print("Enter the edition: ");
        String edition = scanner.next();
        System.out.print("Enter the genre: ");
        String genre = scanner.next();
        System.out.print("Enter the available count: ");
        int availableCount = scanner.nextInt();
        System.out.print("Enter the volume: ");
        int volume = scanner.nextInt();
        Book book = new Book(bookName, author, publication, edition, genre, availableCount, volume);
        manageBookModel.addBook(book);
        
    }

    public void onSetupComplete() {
        System.out.println("\n\nSetup completed successfully....\n");

    }

    public void showAllBooks() {
        List<Book> bookList = manageBookModel.showAllBooks();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void getBookById() {
        System.out.print("Enter the book id: ");
        int bookId = scanner.nextInt();
        System.out.println(manageBookModel.getBookById(bookId));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void getBooksByName() {
        System.out.print("Enter the book name: ");
        String bookName = scanner.next();
        List<Book> bookList = manageBookModel.getBooksByName(bookName);
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void updateBookCount() {
        System.out.print("Enter the book id: ");
        int bookId1 = scanner.nextInt();
        System.out.print("Enter the new count to add: ");
        int availableCount = scanner.nextInt();
        int availableCount1 = manageBookModel.getBookById(bookId1).getAvailableCount() + availableCount;
        manageBookModel.updateBookCount(bookId1, availableCount1);
    }

    public void serializeBookList()
    {
        manageBookModel.serializeBookList();
    }
}
