package LibraryManagerDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SearchResultDialog extends JDialog {
    public SearchResultDialog(Vector<Vector<String>> bookList) {
        setTitle("검색 결과");

        JPanel panel = new JPanel(new BorderLayout());

        JPanel textPanel=new JPanel();
        JLabel textLabel = new JLabel("검색 결과");
        textPanel.add(textLabel);
        textPanel.setBackground(Color.WHITE);

        JPanel bookTablePanel = new BookTable(bookList);

        panel.add(textPanel, BorderLayout.NORTH);
        panel.add(bookTablePanel, BorderLayout.CENTER);

        add(panel);

        setLocation(400, 100);
        setSize(800,600);
    }
}
