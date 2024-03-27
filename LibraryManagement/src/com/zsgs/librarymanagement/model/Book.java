package com.zsgs.librarymanagement.model;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;

public class Book {
    private String bookName;
    private int bookId;
    private String author;
    private String publication;
    private String edition;
    private String genre;
    private int availableCount;
    private int volume;

    public Book(String bookName, String author, String publication, String edition, String genre,
            int availableCount, int volume) {
        this.bookName = bookName;
        this.bookId = LibraryDatabase.getInstance().getBookList().size() + 1;
        this.author = author;
        this.publication = publication;
        this.edition = edition;
        this.genre = genre;
        this.availableCount = availableCount;
        this.volume = volume;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getgenre() {
        return genre;
    }

    public void setgenre(String genre) {
        this.genre = genre;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String toString() {
        return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", publication="
                + publication + ", edition=" + edition + ", genre=" + genre + ", availableCount=" + availableCount
                + ", volume=" + volume + "]";
    }

}
