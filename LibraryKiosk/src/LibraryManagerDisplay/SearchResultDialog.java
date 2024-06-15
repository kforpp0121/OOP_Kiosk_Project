package LibraryManagerDisplay;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class SearchResultDialog extends JDialog {
    JFrame frame;
    public SearchResultDialog(Vector<Vector<String>> bookList,JFrame frame) throws IOException, FontFormatException {
        this.frame = frame;
        setTitle("검색 결과");

        // 폰트 불러오기
        File fontFile = new File("LibraryKiosk/font/NanumGothic.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        // 폰트 설정
        Font labelFont = customFont.deriveFont(Font.BOLD, 30);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel textPanel=new JPanel();
        JLabel textLabel = new JLabel("검색 결과");
        textLabel.setFont(labelFont);
        textPanel.add(textLabel);
        textPanel.setBackground(Color.WHITE);

        JPanel bookTablePanel = new BookTable(bookList, frame);

        panel.add(textPanel, BorderLayout.NORTH);
        panel.add(bookTablePanel, BorderLayout.CENTER);

        add(panel);

        setSize(800,700);
        setLocationRelativeTo(frame);
    }
}
