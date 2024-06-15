package LibraryManagerDisplay;

import CSVController.BookCSVController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class ManagerDisplay {
    public ManagerDisplay() throws IOException, FontFormatException {
        JFrame frame = new JFrame("관리자 화면"); // 프레임 생성
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫히면 프로그램 종료
        frame.setSize(800, 700); // 프레임 크기 설정
        frame.setLocation(700, 50); // 프레임 위치 설정

        BookCSVController bookCSVController = new BookCSVController(); // bookList를 읽어오기 위한 객체
        Vector<Vector<String>> bookList = bookCSVController.readCSV(); // csv파일로부터 bookList를 읽어옴

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JPanel searchBarPanel = new SearchBar(bookList,frame); // 검색창
        JPanel bookTablePanel = new BookTable(bookList,frame); // 도서 목록 테이블

        panel.add(searchBarPanel, BorderLayout.NORTH);
        panel.add(bookTablePanel, BorderLayout.CENTER);

        frame.add(panel);

        // 프레임을 보이도록 설정
        frame.setVisible(true);
    }
}
