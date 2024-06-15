package LibraryManagerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ConfirmTaskDialog extends JDialog {
    private boolean isConfirmed;
    JFrame frame;
    public ConfirmTaskDialog(String task, JFrame frame) throws IOException, FontFormatException {
        this.frame = frame;

        setTitle("작업 확인");
        setConfirmed(false);

        Color green = new Color(0x00469C76);
        Color orange = new Color(0x00EE7930);

        // 폰트 불러오기
        File fontFile = new File("LibraryKiosk/font/NanumGothic.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        // 폰트 설정
        Font btnFont = customFont.deriveFont(Font.BOLD, 18);
        Font labelFont = customFont.deriveFont(Font.BOLD, 16);

        JPanel confirmPanel=new JPanel();
        JLabel confirmLabel = new JLabel("도서 "+task+"을(를) 완료하시겠습니까?");
        confirmLabel.setFont(labelFont);
        confirmPanel.add(confirmLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton yesButton = new JButton("완료");
        yesButton.setBackground(green);
        yesButton.setForeground(Color.WHITE);
        yesButton.setFont(btnFont);
        JButton noButton = new JButton("취소");
        noButton.setBackground(orange);
        noButton.setForeground(Color.WHITE);
        noButton.setFont(btnFont);

        yesButton.addActionListener(e -> {
            setConfirmed(true);
            dispose();
        });

        noButton.addActionListener(e-> {dispose();});

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(new JPanel(), BorderLayout.NORTH); // 빈 공간 추가 (위쪽 여백)
        add(confirmPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(frame);
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
