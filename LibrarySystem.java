import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LibrarySystem {
    // 책 정보 클래스
    static class Book {
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

    // 회원 정보 클래스
    static class Member {
        String name;
        String id;
        String password;
        String birth;
        String phoneNumber;

        public Member(String name, String id, String password, String birth, String phoneNumber) {
            this.name = name;
            this.id = id;
            this.password = password;
            this.birth = birth;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return String.join(",", name, id, password, birth, phoneNumber);
        }
    }

    private static Map<String, Book> books = new HashMap<>();
    private static Map<String, Member> members = new HashMap<>();
    private static final String BOOKS_FILE = "lib_test.csv";
    private static final String MEMBERS_FILE = "member.csv";

    public static void main(String[] args) {
        loadBooks(BOOKS_FILE);
        loadMembers(MEMBERS_FILE);

        // 예시 입력
        long isbnLong = 9780134685991L;
        String isbnString = Long.toString(isbnLong);

        borrowBook(isbnString, "user123");
        returnBook(isbnString, "user123");
    }

    private static void loadBooks(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals("TITLE")) continue;  // Header 건너뜀
                String title = values[0];
                String author = values[1];
                String isbn = values[2];
                boolean isAvailable = values[3].equalsIgnoreCase("true");
                boolean isReserved = values[4].equalsIgnoreCase("true");
                String pictureUrl = values[5];
                books.put(isbn, new Book(title, author, isbn, isAvailable, isReserved, pictureUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadMembers(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals("NM")) continue;  // Header 건너뜀
                String name = values[0];
                String id = values[1];
                String password = values[2];
                String birth = values[3];
                String phoneNumber = values[4];
                members.put(id, new Member(name, id, password, birth, phoneNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//책 대출
    private static void borrowBook(String isbn, String userId) {
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

	//책 반납
    private static void returnBook(String isbn, String userId) {
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

	//책 및 회원정보 저장
    private static void saveBooks() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(BOOKS_FILE))) {
            bw.write("TITLE,AUTHOR,ISBN,RV,BOOL,PICTURE\n");
            for (Book book : books.values()) {
                bw.write(book.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveMembers() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(MEMBERS_FILE))) {
            bw.write("NM,ID,PW,BIRTH,PN\n");
            for (Member member : members.values()) {
                bw.write(member.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
