package menu;

import javax.swing.*;
import java.awt.*;

public class PieChart extends JPanel {
    private int loanedBooks;
    private int notLoanedBooks;
    private Color loanedColor;
    private Color notLoanedColor;

    public PieChart(int loanedBooks, int notLoanedBooks) {
        this.loanedBooks = loanedBooks;
        this.notLoanedBooks = notLoanedBooks;
        this.loanedColor = new Color(0xEE7930);
        this.notLoanedColor = new Color(0x469C76);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        int totalBooks = loanedBooks + notLoanedBooks;
        int loanedAngle = (int) ((double) loanedBooks / totalBooks * 360);
        int notLoanedAngle = 360 - loanedAngle;

        g.setColor(loanedColor);
        g.fillArc(50, 50, width - 100, height - 100, 0, loanedAngle);

        g.setColor(notLoanedColor);
        g.fillArc(50, 50, width - 100, height - 100, loanedAngle, notLoanedAngle);

        // 범주 그리기
        int legendX = width - 150;
        int legendY = 10;
        int legendWidth = 20;
        int legendHeight = 20;
        int spacing = 30;

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