package LibraryManagerDisplay;

import CSVController.BookCSVController;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class ManagerMain {
    public static void main(String[] args) throws IOException, FontFormatException {
        BookCSVController bookCSVController = new BookCSVController(); // bookList를 읽어오기 위한 객체
        Vector<Vector<String>> bookList = bookCSVController.readCSV(); // csv파일로부터 bookList를 읽어옴


        // 관리자 화면 생성
        new ManagerDisplay();
    }
}
