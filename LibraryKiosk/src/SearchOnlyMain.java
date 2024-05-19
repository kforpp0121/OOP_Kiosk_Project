import javax.swing.*;

public class SearchOnlyMain {
    public static void main(String[] args) {
        // CSV 파일 경로 설정
        String csvFilePath = "lib_test.csv";

        // Swing GUI를 EDT(Thread)에서 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 도서 대출 키오스크 GUI 생성
                SearchOnly kiosk = new SearchOnly(csvFilePath);
                // GUI를 보이도록 설정
                kiosk.setVisible(true);
            }
        });
    }
}