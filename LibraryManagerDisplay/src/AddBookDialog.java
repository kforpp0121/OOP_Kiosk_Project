import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookDialog extends JDialog{
    JPanel titlePanel, authorPanel, ISBNPanel;
    JLabel titleLabel, authorLabel, ISBNLabel;
    JTextField title, author, ISBN;
    boolean isConfirmed = false;
    public AddBookDialog() {
        setTitle("Add Book");
        JPanel panel = new JPanel();

        titlePanel=new JPanel();
        titleLabel = new JLabel("도서 제목");
        title = new JTextField(30);
        titlePanel.add(titleLabel);
        titlePanel.add(title);

        authorPanel=new JPanel();
        authorLabel = new JLabel("작가");
        author = new JTextField(30);
        authorPanel.add(authorLabel);
        authorPanel.add(author);

        ISBNPanel=new JPanel();
        ISBNLabel = new JLabel("ISBN");
        ISBN = new JTextField(30);
        ISBNPanel.add(ISBNLabel);
        ISBNPanel.add(ISBN);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton yesButton = new JButton("입력 완료");
        JButton noButton = new JButton("취소");
        yesButton.addActionListener(new MyListener());
        noButton.addActionListener(e -> {
            title.setText("");
            author.setText("");
            ISBN.setText("");
            setVisible(false);});
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(titlePanel);
        panel.add(authorPanel);
        panel.add(ISBNPanel);
        panel.add(buttonPanel);


        add(panel);

        setSize(400, 200);
    }

    public class MyListener implements ActionListener {
        // ConfirmAddBook 클래스로 isConfirmed 값 어떻게 넘겨주고 받아올지?
        @Override
        public void actionPerformed(ActionEvent e) {
            // 완료 버튼 -> confirmDialog 띄움
            ConfirmDialog confirmDialog = new ConfirmDialog("입력");
            confirmDialog.setVisible(true);

            // 대기 스레드 실행
            WaitingThread waitingThread = new WaitingThread(confirmDialog);
            waitingThread.start();
        }

        private class WaitingThread extends Thread {
            private ConfirmDialog confirmDialog;

            public WaitingThread(ConfirmDialog dialog) {
                this.confirmDialog = dialog;
            }

            @Override
            public void run() {
                // dialog가 닫힐 때까지 기다림
                while (confirmDialog.isVisible()) {
                    try {
                        Thread.sleep(100); // 다이얼로그가 닫힐 때까지 대기
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // dialog에서 완료 버튼 눌렀을 때만 수행
                if (confirmDialog.isConfirmed()) {
                    CSVReaderAndWriter srw = new CSVReaderAndWriter("./test_lib.csv");
                    srw.writeCSV(title.getText(), author.getText(), ISBN.getText());
                    // 파일 write 후에는 textfield 비워줌
                    title.setText("");
                    author.setText("");
                    ISBN.setText("");
                    setVisible(false);
                    CompleteDialog complete = new CompleteDialog("입력");
                    complete.setVisible(true);
                }
            }
        }
    }
}
