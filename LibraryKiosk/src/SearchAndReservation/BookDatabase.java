package SearchAndReservation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import CSVController.CSVReader;

public class BookDatabase {
    private Vector<Book> books;

    public BookDatabase(String csvFilePath) {
        books = new Vector<>();
        loadBooksFromCSV(csvFilePath);
        System.out.println("총 도서 수: " + (books.size()-1));
    }

    private void loadBooksFromCSV(String csvFilePath) {
        try  {
            Vector<String[]> records = CSVReader.readCSV(csvFilePath);
            for (String[] line : records) {
                // 제목, 저자, ISBN, 예약, 예약 상태, 이미지 경로 여섯 가지 정보 추출
                if (line.length < 6) continue; // 잘못된 형식의 라인 건너뜀
                String title = line[0];          // 제목
                String author = line[1];         // 저자
                String isbn = line[2];           // ISBN
                String reservation = line[3];    // 예약 가능 여부
                boolean reserved = line[4].equals("\"0\"")?false:true;    // 예약 상태, "0"이면 false, "1"이면 true
                String coverImagePath = line[5];
                books.add(new Book(title, author, isbn, reservation, reserved, coverImagePath));
            }
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (IOException e) {
            // IOException이 발생하면
            // 예외 처리
            e.printStackTrace();
        }
    }

    public Vector<Book> searchBooks(String query) { // 검색어를 매개변수로 받음
        Vector<Book> result = new Vector<>();    // 결과 리스트
        for (Book book : books) {  // books 리스트에 있는 모든 도서 순회
            // 대소문자 구분하지 않기 위해 소문자로 변환
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public void updateCSV(Book selectedBook, String csvFilePath) {
        /*도서 예약 상태를 true로 변경해줌, false로 변경하는 기능은 없음(아마 setReserved(!isReserved)로 구현 가능할듯?)*/
        for (Book book : books) { // books 벡터를 순회하며 선택된 책 정보와 일치하는 책 정보를 찾아 수정
            if (book.getTitle().equals(selectedBook.getTitle()) &&
                    book.getAuthor().equals(selectedBook.getAuthor()) &&
                    book.getIsbn().equals(selectedBook.getIsbn())) {
                book.setReserved(true); // 예약 상태를 true로 변경
            }
        }

        // 수정된 정보를 파일에 다시 쓰기
        writeUpdatedCSV(csvFilePath);
    }

    public void writeUpdatedCSV(String csvFilePath) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFilePath, false));
            /*
             *  첫 번째 줄에 column 정보 쓰기
             * 읽어올 때 스킵하기 때문에 써주지 않으면 수정했을 때 도서 데이터가 한줄씩 삭제됨
             */
            bw.write("\"TITLE\",\"AUTHOR\",\"ISBN\",\"RV\",\"BOOL\",\"PICTURE\"");
            bw.newLine();
            for (Book book : books) {
                String aData = "";
                String title = book.getTitle();
                String author = book.getAuthor();
                String ISBN = book.getIsbn();
                String rv = book.getReservation();
                String bool = (book.isReserved()?"\"1\"":"\"0\""); // 예약 상태가 true이면 "1", false이면 "0"
                String image = book.getCoverImagePath();
                aData = aData.join(",", title, author, ISBN, rv, bool, image);
                bw.write(aData);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

