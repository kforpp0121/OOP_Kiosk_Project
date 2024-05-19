import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDialog extends JDialog {
    private boolean isConfirmed;
    public ConfirmDialog(String task) {
        setTitle("Confirm Task Book");
        setConfirmed(false);

        JPanel confirmPanel=new JPanel();
        JLabel confirmLabel = new JLabel("도서 "+task+"을(를) 완료하시겠습니까?");
        confirmPanel.add(confirmLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton yesButton = new JButton("완료");
        JButton noButton = new JButton("취소");

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setConfirmed(true);
                setVisible(false);
            }
        });

        noButton.addActionListener(e-> {setVisible(false);});

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(confirmPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 150);
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
