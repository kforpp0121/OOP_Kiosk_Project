package SearchAndReservation;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String reservation;
    private boolean reserved;  // 예약 여부.(예약 상태: true 예약 아닌 상태: false)
    private String coverImagePath; // 도서 표지 경로
    public Book(String title, String author, String isbn, String reservation, boolean reserved, String coverImagePath) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.reservation = reservation;
        this.reserved = false;  // 초기 상태. 예약 되지 않음
        this.coverImagePath = coverImagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return !reserved;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
        if (reserved) {
            reservation = "불가능";
        } else {
            reservation = "가능";
        }
    }

    @Override
    public String toString() {
        return "제목 : " + title + '\n' +
                "저자 : " + author + '\n' +
                "예약 : " + reservation + '\n' +
                "이미지 : " + coverImagePath;
    }
}
