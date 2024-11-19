package com.example.library_management_system.modles;

import java.util.Date;

/**
 * Represents a book resource in the library management system.
 * This class extends the ResourcesModel to include additional properties such as author, ISBN, and edition.
 */
public class BookModel extends ResourcesModel {

    private String author;
    private String isbn;
    private int edition;

    /**
     * Constructs a BookModel object with the specified values.
     * This constructor is typically used when a new book is created without providing an ID or timestamps.
     *
     * @param availableState the availability state of the book (true if available, false otherwise)
     * @param title the title of the book
     * @param publisher the publisher of the book
     * @param totalCopies the total number of copies of the book
     * @param copiesLeft the number of copies available
     * @param author the author of the book
     * @param isbn the ISBN of the book
     * @param edition the edition number of the book
     */
    public BookModel(boolean availableState, String title,
                     String publisher, int totalCopies, int copiesLeft,
                     String author, String isbn, int edition) {
        this(0, availableState, title, publisher, totalCopies, copiesLeft,
                author, isbn, edition, new Date(), new Date());
    }

    /**
     * Constructs a BookModel object with the specified values including the ID and timestamps.
     *
     * @param id the unique identifier of the book
     * @param availableState the availability state of the book (true if available, false otherwise)
     * @param title the title of the book
     * @param publisher the publisher of the book
     * @param totalCopies the total number of copies of the book
     * @param copiesLeft the number of copies available
     * @param author the author of the book
     * @param isbn the ISBN of the book
     * @param edition the edition number of the book
     * @param createdAt the date and time when the book was created
     * @param updatedAt the date and time when the book was last updated
     */
    public BookModel(int id, boolean availableState, String title,
                     String publisher, int totalCopies, int copiesLeft,
                     String author, String isbn, int edition, Date createdAt, Date updatedAt) {
        super(id, availableState, title, publisher, totalCopies, copiesLeft, createdAt, updatedAt);
        this.author = author;
        this.isbn = isbn;
        this.edition = edition;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the new ISBN to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns the edition number of the book.
     *
     * @return the edition number of the book
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Sets the edition number of the book.
     *
     * @param edition the new edition number to set
     */
    public void setEdition(int edition) {
        this.edition = edition;
    }

    /**
     * Returns a string representation of the book object.
     * This includes the properties from the parent class (ResourcesModel) as well as the author, ISBN, and edition.
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        return super.toString() + ", author = " + author
                + ", isbn = " + isbn
                + ", edition = " + edition + " }\n";
    }
}
