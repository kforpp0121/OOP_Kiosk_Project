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
                // 도서 대출 키오스크 GUI 생성
                Search kiosk = new Search(csvFilePath);
                // GUI를 보이도록 설정
                kiosk.setVisible(true);
            }
        });
    }
}