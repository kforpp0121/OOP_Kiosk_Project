package SearchAndReservation;

import javax.swing.*;


public class SearchMain {
    public static void main(String[] args) {
        // CSV 파일 경로 설정
        String csvFilePath = "lib_test.csv";
        // lib_test
        // subset_lib

        // Swing GUI를 EDT(Thread)에서 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Search");              // 창 제목
                frame.setSize(450, 800);  // 키오스크 화면 크기
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 실행 종료
                frame.setLocation(300, 10);      // (300, 10) 위치에 배치

                // 도서 대출 키오스크 GUI 생성
                Search search = new Search(csvFilePath, frame);
                frame.add(search);
                search.setVisible(true);

                // GUI를 보이도록 설정
                frame.setVisible(true);
            }
        });
    }
}