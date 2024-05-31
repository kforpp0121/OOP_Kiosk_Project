package borret;// BookManager.java
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
            System.out.println("å�� ã�� �� �����ϴ�.");
            return;
        }
        Book book = books.get(isbn);
        if (!book.isAvailable) {
            System.out.println("å�� ���� ���Դϴ�.");
            return;
        }
        book.isAvailable = false;
        saveBooks();
        System.out.println(userId + "���� " + book.title + " å�� �����߽��ϴ�.");
    }

    public void returnBook(String isbn, String userId) {
        if (!books.containsKey(isbn)) {
            System.out.println("å�� ã�� �� �����ϴ�.");
            return;
        }
        Book book = books.get(isbn);
        if (book.isAvailable) {
            System.out.println("�� å�� ������� �ʾҽ��ϴ�.");
            return;
        }
        book.isAvailable = true;
        saveBooks();
        System.out.println(userId + "���� " + book.title + " å�� �ݳ��߽��ϴ�.");
    }
}