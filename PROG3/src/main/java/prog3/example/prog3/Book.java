package prog3.example.prog3;

import java.util.Date;

public class Book {
    private int id;
    private String bookName;
    private int pageNumbers;
    private String releaseDate;

    public Book(int id, String bookName, int pageNumbers, String releaseDate) {
        this.id = id;
        this.bookName = bookName;
        this.pageNumbers = pageNumbers;
        this.releaseDate = releaseDate;
    }

    public Book() {

    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    // Additional methods

    public String getTitle() {
        return getBookName();
    }

    public String getAuthor() {
        // Assuming there is an Author class with a relevant attribute
        return "Author Name"; // Replace with actual logic to get the author
    }

    public int getPublicationYear() {
        if (getReleaseDate() != null && getReleaseDate().length() >= 4) {

            return Integer.parseInt(getReleaseDate().substring(0, 4));
        } else {

            return -1;
        }
    }

}
