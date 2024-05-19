import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTaskDialog extends JDialog {
    public SelectTaskDialog(BookInfo book) {
        setTitle("Update Or Delete");

        JPanel confirmPanel=new JPanel();
        JLabel confirmLabel = new JLabel("원하시는 업무를 선택해주세요.");
        confirmPanel.add(confirmLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton updateButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");

        updateButton.addActionListener(e -> {
            UpdateBookDialog ubd = new UpdateBookDialog(book);
            ubd.setVisible(true);
            setVisible(false);
        });

        deleteButton.addActionListener(new MyListener());

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(confirmPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 150);
    }

    public class MyListener implements ActionListener {
        // ConfirmAddBook 클래스로 isConfirmed 값 어떻게 넘겨주고 받아올지?
        @Override
        public void actionPerformed(ActionEvent e) {
            // 완료 버튼 -> dialog 띄움
            ConfirmDialog dialog = new ConfirmDialog("삭제");
            dialog.setVisible(true);

            // 대기 스레드 실행
            SelectTaskDialog.MyListener.WaitingThread waitingThread = new SelectTaskDialog.MyListener.WaitingThread(dialog);
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
                    //CSVReaderAndWriter srw = new CSVReaderAndWriter("./test_lib.csv");
                    //srw.writeCSV(title.getText(), author.getText(), ISBN.getText());
                    // 파일 write 후에는 textfield 비워줌
                    //title.setText("");
                    //author.setText("");
                    //ISBN.setText("");
                    setVisible(false);
                    CompleteDialog complete = new CompleteDialog("삭제");
                    complete.setVisible(true);
                }
            }
        }
    }
}
