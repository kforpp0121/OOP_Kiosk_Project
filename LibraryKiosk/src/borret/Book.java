package borret;

// Book.java
public class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;
    boolean isReserved;
    String pictureUrl;

    public Book(String title, String author, String isbn, boolean isAvailable, boolean isReserved, String pictureUrl) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
        this.isReserved = isReserved;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return String.join(",", title, author, isbn, Boolean.toString(isAvailable), Boolean.toString(isReserved), pictureUrl);
    }
}