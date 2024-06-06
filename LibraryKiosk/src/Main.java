import CSVController.BookCSVController;
import LibraryManagerDisplay.ManagerDisplay;
import SearchAndReservation.SearchOnly;
import StartLogin.Start;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {


        BookCSVController bookCSVController = new BookCSVController(); // bookList를 읽어오기 위한 객체
        Vector<Vector<String>> bookList = bookCSVController.readCSV(); // csv파일로부터 bookList를 읽어옴


        // 관리자 화면 생성
        new ManagerDisplay(bookList);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Search");              // 창 제목
                frame.setSize(450, 800);  // 키오스크 화면 크기
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 실행 종료
                frame.setLocation(250, 10);      // (300, 10) 위치에 배치

                // 도서 대출 키오스크 GUI 생성
                Start start = new Start(frame);
                frame.add(start);
                start.setVisible(true);

                // GUI를 보이도록 설정
                frame.setVisible(true);
            }
        });
    }
}
