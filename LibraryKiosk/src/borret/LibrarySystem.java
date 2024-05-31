package borret;

public class LibrarySystem {
    private static BookManager bookManager;
    private static MemberManager memberManager;

    public static void main(String[] args) {
        BookLoader bookLoader = new BookLoader();
        MemberLoader memberLoader = new MemberLoader();

        bookManager = new BookManager(bookLoader);
        memberManager = new MemberManager(memberLoader);

        // ���� �Է�
        long isbnLong = 9780134685991L;
        String isbnString = Long.toString(isbnLong);

        bookManager.borrowBook(isbnString, "user123");
        bookManager.returnBook(isbnString, "user123");
    }
}