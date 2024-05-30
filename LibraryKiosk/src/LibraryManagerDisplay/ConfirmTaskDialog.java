package LibraryManagerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmTaskDialog extends JDialog {
    private boolean isConfirmed;
    public ConfirmTaskDialog(String task) {
        setTitle("작업 확인");
        setConfirmed(false);

        Color green = new Color(0x00469C76);
        Color orange = new Color(0x00EE7930);

        JPanel confirmPanel=new JPanel();
        JLabel confirmLabel = new JLabel("도서 "+task+"을(를) 완료하시겠습니까?");
        confirmPanel.add(confirmLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton yesButton = new JButton("완료");
        yesButton.setBackground(green);
        JButton noButton = new JButton("취소");
        noButton.setBackground(orange);

        yesButton.addActionListener(e -> {
            setConfirmed(true);
            dispose();
        });

        noButton.addActionListener(e-> {dispose();});

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(confirmPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocation(650, 300);
        setSize(300, 150);
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
