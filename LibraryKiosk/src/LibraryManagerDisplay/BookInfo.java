package LibraryManagerDisplay;

public class BookInfo {
    private String title;
    private String author;
    private String ISBN;
    private String rv;
    private String bool;
    private String coverImagePath;

    public BookInfo(String title, String author, String ISBN, String rv, String bool, String coverImagePath) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.rv = rv;
        this.bool = bool;
        this.coverImagePath = coverImagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getRv() {
        return rv;
    }

    public void setRv(String rv) {
        this.rv = rv;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }
}
