//package CSVController;// BookLoader.java
//import borret.Book;
//
//import java.io.*;
//import java.nio.file.*;
//import java.util.*;
//
//public class BookLoader {
//    private final String BOOKS_FILE = "C:\\Java\\TermProject\\term2\\LibraryCSVfile.csv";
//
//    public Map<String, Book> loadBooks() {
//        Map<String, Book> books = new HashMap<>();
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(BOOKS_FILE))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                if (values[0].equals("TITLE")) continue;  // Header �ǳʶ�
//                String title = values[0];
//                String author = values[1];
//                String isbn = values[2];
//                boolean isAvailable = values[3].equalsIgnoreCase("true");
//                boolean isReserved = values[4].equalsIgnoreCase("true");
//                String pictureUrl = values[5];
//                books.put(isbn, new Book(title, author, isbn, isAvailable, isReserved, pictureUrl));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
//
//    public void saveBooks(Map<String, Book> books) {
//        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(BOOKS_FILE))) {
//            bw.write("TITLE,AUTHOR,ISBN,RV,BOOL,PICTURE\n");
//            for (Book book : books.values()) {
//                bw.write(book.toString() + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}