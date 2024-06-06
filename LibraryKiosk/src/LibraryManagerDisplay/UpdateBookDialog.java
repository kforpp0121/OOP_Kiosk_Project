package LibraryManagerDisplay;

import CSVController.BookCSVController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UpdateBookDialog extends JDialog {
    JPanel titlePanel, authorPanel, ISBNPanel;
    JLabel titleLabel, authorLabel, ISBNLabel;
    JTextField title, author, ISBN;
    boolean isConfirmed = false;
    public UpdateBookDialog(Vector<Vector<String>> bookList, BookInfo book) {
        Color green = new Color(0x00469C76);
        Color orange = new Color(0x00EE7930);

        Font btnFont = new Font("맑은 고딕", Font.BOLD, 18);
        Font labelFont = new Font("맑은 고딕", Font.BOLD, 16);
        Font formFont = new Font("맑은 고딕", Font.PLAIN, 16);

        setTitle("도서 수정");
        JPanel panel = new JPanel();

        titlePanel=new JPanel();
        titleLabel = new JLabel("제목");
        titleLabel.setPreferredSize(new Dimension(50, 20));
        titleLabel.setFont(labelFont);
        title = new JTextField(20);
        title.setFont(formFont);
        title.setText(book.getTitle());
        titlePanel.add(titleLabel);
        titlePanel.add(title);

        authorPanel=new JPanel();
        authorLabel = new JLabel("작가");
        authorLabel.setPreferredSize(new Dimension(50, 20));
        authorLabel.setFont(labelFont);
        author = new JTextField(20);
        author.setFont(formFont);
        author.setText(book.getAuthor());
        authorPanel.add(authorLabel);
        authorPanel.add(author);

        ISBNPanel=new JPanel();
        ISBNLabel = new JLabel("ISBN");
        ISBNLabel.setPreferredSize(new Dimension(50, 20));
        ISBNLabel.setFont(labelFont);
        ISBN = new JTextField(20);
        ISBN.setFont(formFont);
        ISBN.setText(book.getISBN());
        ISBNPanel.add(ISBNLabel);
        ISBNPanel.add(ISBN);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton yesButton = new JButton("입력 완료");
        yesButton.setBackground(green);
        yesButton.setForeground(Color.WHITE);
        yesButton.setFont(btnFont);
        JButton noButton = new JButton("취소");
        noButton.setBackground(orange);
        noButton.setForeground(Color.WHITE);
        noButton.setFont(btnFont);
        yesButton.addActionListener(new UpdateBookListener(bookList, book));
        noButton.addActionListener(e -> {dispose();});
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(titlePanel);
        panel.add(authorPanel);
        panel.add(ISBNPanel);
        panel.add(buttonPanel);


        add(panel);

        setLocation(900, 300);
        setSize(400, 225);
    }

    public class UpdateBookListener implements ActionListener {
        BookInfo currentBook;
        Vector<Vector<String>> books;

        public UpdateBookListener(Vector<Vector<String>> books, BookInfo book) {
            this.books = books;
            this.currentBook = book;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(title.getText().isEmpty() // 제목, 작가, ISBN 입력 안했거나 ISBN이 13자리가 아니거나 ISBN이 숫자가 아닌 경우
                    || author.getText().isEmpty()
                    || ISBN.getText().length()!=13
                    || !ISBN.getText().matches("[+-]?\\d*(\\.\\d+)?")){
                JOptionPane.showMessageDialog(null, "입력한 도서 정보가 잘못되었습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // 완료 버튼 -> confirmTaskDialog 띄움
            ConfirmTaskDialog confirmTaskDialog = new ConfirmTaskDialog("수정");
            confirmTaskDialog.setVisible(true);

            // 대기 스레드 실행
            UpdateBookListener.WaitingThread waitingThread = new UpdateBookListener.WaitingThread(confirmTaskDialog);
            waitingThread.start();
        }

        private class WaitingThread extends Thread {
            private ConfirmTaskDialog confirmTaskDialog;

            public WaitingThread(ConfirmTaskDialog confirmTaskDialog) {
                this.confirmTaskDialog = confirmTaskDialog;
            }

            @Override
            public void run() {
                // dialog가 닫힐 때까지 기다림
                while (confirmTaskDialog.isVisible()) {
                    try {
                        Thread.sleep(100); // 다이얼로그가 닫힐 때까지 대기
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // dialog에서 완료 버튼 눌렀을 때만 수행
                if (confirmTaskDialog.isConfirmed()) {
                    BookInfo newBook = new BookInfo(title.getText(), author.getText(), ISBN.getText());
                    // CSV 파일 수정
                    BookCSVController csvCtrl = new BookCSVController();
                    csvCtrl.updateCSV(books, currentBook, newBook);
                    // dialog 닫기
                    dispose();
                    // 수정 완료
                    JOptionPane optionPane = new JOptionPane("도서 수정이 완료되었습니다.", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("도서 수정 완료");
                    dialog.setLocation(950, 300);
                    dialog.setVisible(true);                }
            }
        }
    }
}