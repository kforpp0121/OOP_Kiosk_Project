package borret;

import java.util.Vector;
import javax.swing.JOptionPane;

public class BookManager {
    private Vector<Book> books;
    private AAA bookLoader;

    public BookManager(AAA bookLoader) {
        this.bookLoader = bookLoader;
        this.books = bookLoader.getBooks();
    }

    public void saveBooks() {
        // bookLoader.saveBooks(books); // 필요에 따라 구현
    }

    public void borrowBook(String isbn, String userId) {
        Book book = bookLoader.getBookByISBN(isbn);
        if (book == null) {
            JOptionPane.showMessageDialog(null, "책을 찾을 수 없습니다.");
            return;
        }
        if (!book.isAvailable()) {
            JOptionPane.showMessageDialog(null, "이미 대출되었습니다.");
            return;
        }
        book.setAvailable(false);
        saveBooks();
        JOptionPane.showMessageDialog(null, userId + "님이 " + book.getTitle() + " 책을 대출했습니다.");
    }

    public void returnBook(String isbn, String userId) {
        Book book = bookLoader.getBookByISBN(isbn);
        if (book == null) {
            JOptionPane.showMessageDialog(null, "책을 찾을 수 없습니다.");
            return;
        }
        if (book.isAvailable()) {
            JOptionPane.showMessageDialog(null, "대출되지 않은 책입니다.");
            return;
        }
        book.setAvailable(true);
        saveBooks();
        JOptionPane.showMessageDialog(null, userId + "님이 " + book.getTitle() + " 책을 반납했습니다.");
    }
}