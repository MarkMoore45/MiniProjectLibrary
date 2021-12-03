package LibrarySYS;

/**
 * An instantiable class which defines a Book.
 * @author Mark Moore
 */

public class Book {

    private int BookID;
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private int pages;
    private char status;
    private static int count = 1;

    /** * Book 6-argument constructor. Calls the 6 mutators and the incrementCount() method to
     * initialise the attributes of a Book object with some user-supplied values.
     * The Book ID is set internally using the value of the count attribute, to ensure unique Book ID values.
     * Status is set internally to 'A' as all books are available when they are created.
     * @param ISBN the ISBN of the book
     * @param title the title of the book
     * @param author the author of the book
     * @param genre the genre of the book
     * @param pages the number of pages in the book
     * @param status the status of the book
     */

    public Book(String ISBN, String title, String author, String genre, int pages, char status) {
        setBookID(count);
        setISBN(ISBN);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setPages(pages);
        setStatus(status);
    }

    /**
     * Method to get the ID of a Book object
     * @return an int value specifying the ID of a Book object
     */

    public int getBookID() {
        return BookID;
    }

    /**
     * Method to set the ID of a Book object, where count increments to
     * generate a unique ID for each Book Object.
     * @param BookID the ID number of the Book
     */

    public void setBookID(int BookID) {
        this.BookID = count++;
    }

    /**
     * Method to get the ISBN of a Book object
     * @return a String value specifying the ISBN of a Book object
     */

    public String getISBN() {
        return ISBN;
    }

    /**
     * Method to set the ID of a Book object
     * @param ISBN the ISBN of a Book Object
     */

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Method to get the title of a Book object
     * @return a String value specifying the title of a Book object
     */

    public String getTitle() {
        return title;
    }

    /**
     * Method to set the title of a Book object
     * @param title the title of a Book Object
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to get the author of a Book object
     * @return a String value specifying the author of a Book object
     */

    public String getAuthor() {
        return author;
    }

    /**
     * Method to set the author of a Book object
     * @param author the author of a Book Object
     */

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Method to get the genre of a Book object
     * @return a String value specifying the genre of a Book object
     */

    public String getGenre() {
        return genre;
    }

    /**
     * Method to set the genre of a Book object
     * @param genre the genre of a Book Object
     */

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Method to get the pages of a Book object
     * @return an int value specifying the number of pages in a Book object
     */

    public int getPages() {
        return pages;
    }

    /**
     * Method to set the pages of a Book object
     * @param pages the number of pages in a Book Object
     */

    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Method to get the status of a Book object
     * @return a char value specifying the status of a Book object
     */

    public char getStatus() {
        return status;
    }

    /**
     * Method to set the status of a Book object
     * @param status the status of a Book Object
     */

    public void setStatus(char status) {
        this.status = 'A';
    }

    /**
     * Method to get the state of a Book object
     * @return a String value specifying the state of a Book object
     */

    @Override
    public String toString() {
        return "\nBookID: " + getBookID() +
                "\nISBN: " + getISBN() +
                "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nGenre: " + getGenre() +
                "\nPages: " + getPages() +
                "\nStatus: " + getStatus();
    }
}


