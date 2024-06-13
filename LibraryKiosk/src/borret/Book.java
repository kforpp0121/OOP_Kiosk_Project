//package borret;
//
//public class Book {
//    private String title;
//    private String author;
//    private String isbn;
//    private boolean isAvailable;
//    private boolean isReserved;
//    private String pictureUrl;
//
//    public Book(String title, String author, String isbn, boolean isAvailable, boolean isReserved, String pictureUrl) {
//        this.title = title;
//        this.author = author;
//        this.isbn = isbn;
//        this.isAvailable = isAvailable;
//        this.isReserved = isReserved;
//        this.pictureUrl = pictureUrl;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public boolean isAvailable() {
//        return isAvailable;
//    }
//
//    public void setAvailable(boolean isAvailable) {
//        this.isAvailable = isAvailable;
//    }
//
//    public boolean isReserved() {
//        return isReserved;
//    }
//
//    public void setReserved(boolean isReserved) {
//        this.isReserved = isReserved;
//    }
//
//    @Override
//    public String toString() {
//        return String.join(",", title, author, isbn, Boolean.toString(isAvailable), Boolean.toString(isReserved), pictureUrl);
//    }
//}