package com.example.library_management_system.modles;

public class BookModel extends ResourcesModel {
    private String author;
    private String isbn;
    private int edition;

    public BookModel(int id, boolean availalbeState, String title,
                     String publisher, int totalCopies, int copiesLeft,
                     String author, String isbn, int edition) {
        super(id, availalbeState, title, publisher, totalCopies, copiesLeft);
        this.author = author;
        this.isbn = isbn;
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }


}
