package menu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PieChart extends JFrame {
    private PieChartPanel chartpanel;
    private Font font;

    public PieChart(JPanel parent) {
        setTitle("대출량 비교");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setUIFont();

        chartpanel = new PieChartPanel(10, 5);

        add(chartpanel, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(parent);
    }

    class PieChartPanel extends JPanel {
        private int loanedBooks;
        private int notLoanedBooks;
        private Color loanedColor;
        private Color notLoanedColor;

        public PieChartPanel(int loanedBooks, int notLoanedBooks) {
            this.loanedBooks = loanedBooks;
            this.notLoanedBooks = notLoanedBooks;
            this.loanedColor = new Color(0xEE7930); // RGB 색상
            this.notLoanedColor = new Color(0x469C76); // RGB 색상
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();

            int totalBooks = loanedBooks + notLoanedBooks;
            int loanedAngle = (int) ((double) loanedBooks / totalBooks * 360);
            int notLoanedAngle = 360 - loanedAngle;

            g.setColor(loanedColor);
            g.fillArc(50, 60, width - 100, height - 100, 0, loanedAngle);

            g.setColor(notLoanedColor);
            g.fillArc(50, 60, width - 100, height - 100, loanedAngle, notLoanedAngle);

            // 범주 그리기
            int legendX = width - 100;
            int legendY = 10;
            int legendWidth = 20;
            int legendHeight = 20;
            int spacing = 30;

            Font chartFont = font.deriveFont(Font.PLAIN, 20); // 나눔고딕, 크기 20
            g.setFont(chartFont);

            // 대출 도서 범주
            g.setColor(loanedColor);
            g.fillRect(legendX, legendY, legendWidth, legendHeight);
            g.setColor(Color.BLACK);
            g.drawString("이번달", legendX + legendWidth + 10, legendY + legendHeight - 5);

            // 미대출 도서 범주
            g.setColor(notLoanedColor);
            legendY += spacing;
            g.fillRect(legendX, legendY, legendWidth, legendHeight);
            g.setColor(Color.BLACK);
            g.drawString("지난달", legendX + legendWidth + 10, legendY + legendHeight - 5);
        }
    }

    // 폰트 적용
    private void setUIFont() {
        // 나눔 고딕 폰트 파일 경로
        String fontPath = "LibraryKiosk/font/NanumGothic.ttf";

        // 폰트 파일로부터 폰트 객체 생성
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 12);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}