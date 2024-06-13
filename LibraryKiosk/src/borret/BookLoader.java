package borret;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BookLoader {
    private final String BOOKS_FILE = "C:\\Java\\TermProject\\term2\\LibraryCSVfile.csv";

    public Map<String, Book> loadBooks() {
        Map<String, Book> books = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals("TITLE")) continue;  // Skip header
                String title = values[0];
                String author = values[1];
                String isbn = values[2];
                boolean available = Boolean.parseBoolean(values[3]);
                boolean reserved = Boolean.parseBoolean(values[4]);
                String pictureUrl = values[5];
                books.put(isbn, new Book(title, author, isbn, available, reserved, pictureUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void saveBooks(Map<String, Book> books) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(BOOKS_FILE))) {
            bw.write("TITLE,AUTHOR,ISBN,AVAILABLE,RESERVED,PICTURE\n");
            for (Book book : books.values()) {
                bw.write(book.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}