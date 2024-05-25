// BookManager.java
import java.util.HashMap;
import java.util.Map;

public class BookManager {
    private Map<String, Book> books = new HashMap<>();
    private BookLoader bookLoader;

    public BookManager(BookLoader bookLoader) {
        this.bookLoader = bookLoader;
        this.books = bookLoader.loadBooks();
    }

    public void saveBooks() {
        bookLoader.saveBooks(books);
    }

    public void borrowBook(String isbn, String userId) {
        if (!books.containsKey(isbn)) {
            System.out.println("책을 찾을 수 없습니다.");
            return;
        }
        Book book = books.get(isbn);
        if (!book.isAvailable) {
            System.out.println("책이 대출 중입니다.");
            return;
        }
        book.isAvailable = false;
        saveBooks();
        System.out.println(userId + "님이 " + book.title + " 책을 대출했습니다.");
    }

    public void returnBook(String isbn, String userId) {
        if (!books.containsKey(isbn)) {
            System.out.println("책을 찾을 수 없습니다.");
            return;
        }
        Book book = books.get(isbn);
        if (book.isAvailable) {
            System.out.println("이 책은 대출되지 않았습니다.");
            return;
        }
        book.isAvailable = true;
        saveBooks();
        System.out.println(userId + "님이 " + book.title + " 책을 반납했습니다.");
    }
}