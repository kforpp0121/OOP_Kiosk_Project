package LibraryManagerDisplay;

import CSVController.BookCSVController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class AddBookDialog extends JDialog{
    JPanel titlePanel, authorPanel, ISBNPanel, rvPanel;
    JLabel titleLabel, authorLabel, ISBNLabel, rvLabel;
    JTextField title, author, ISBN, rv;
    boolean isConfirmed = false;
    public AddBookDialog(Vector<Vector<String>> bookList, DefaultTableModel model) throws IOException, FontFormatException {
        Color green = new Color(0x00469C76);
        Color orange = new Color(0x00EE7930);

        // 폰트 불러오기
        File fontFile = new File("LibraryKiosk/font/NanumGothic.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        // 폰트 설정
        Font labelFont = customFont.deriveFont(Font.BOLD, 16);
        Font formFont = customFont.deriveFont(Font.PLAIN, 16);
        Font btnFont = customFont.deriveFont(Font.BOLD, 18);

        setTitle("도서 추가");
        JPanel panel = new JPanel();

        titlePanel=new JPanel();
        titleLabel = new JLabel("제목");
        titleLabel.setFont(labelFont);
        titleLabel.setPreferredSize(new Dimension(50, 20));
        title = new JTextField(20);
        title.setFont(formFont);
        titlePanel.add(titleLabel);
        titlePanel.add(title);

        authorPanel=new JPanel();
        authorLabel = new JLabel("작가");
        authorLabel.setFont(labelFont);
        authorLabel.setPreferredSize(new Dimension(50, 20));
        author = new JTextField(20);
        author.setFont(formFont);
        authorPanel.add(authorLabel);
        authorPanel.add(author);

        ISBNPanel=new JPanel();
        ISBNLabel = new JLabel("ISBN");
        ISBNLabel.setFont(labelFont);
        ISBNLabel.setPreferredSize(new Dimension(50, 20));
        ISBN = new JTextField(20);
        ISBN.setFont(formFont);
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
        yesButton.addActionListener(new addBookListener(bookList, model));
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

        setLocation(900, 300);
        setSize(400, 225);
    }

    public class addBookListener implements ActionListener {
        Vector<Vector<String>> bookList;
        DefaultTableModel model;
        public addBookListener(Vector<Vector<String>> bookList, DefaultTableModel model) {
            this.bookList = bookList;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(title.getText().isEmpty()||author.getText().isEmpty()||ISBN.getText().length()!=13 || !ISBN.getText().matches("[+-]?\\d*(\\.\\d+)?")){
                JOptionPane optionPane = new JOptionPane("입력한 도서 정보가 잘못되었습니다.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("입력 오류");
                dialog.setLocation(950, 300);
                dialog.setVisible(true);
                return;
            }
            // 완료 버튼 -> confirmDialog 띄움
            ConfirmTaskDialog confirmTaskDialog = null;
            try {
                confirmTaskDialog = new ConfirmTaskDialog("입력");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
            confirmTaskDialog.setVisible(true);

            // 대기 스레드 실행
            WaitingThread waitingThread = new WaitingThread(confirmTaskDialog);
            waitingThread.start();
        }

        private class WaitingThread extends Thread {
            private ConfirmTaskDialog confirmTaskDialog;

            public WaitingThread(ConfirmTaskDialog dialog) {
                this.confirmTaskDialog = dialog;
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
                    // bookList에 추가
                    Vector<String> newBook = new Vector<String>(){
                        {
                            add(title.getText());
                            add(author.getText());
                            add(ISBN.getText());
                            add("가능");
                            add("0");
                        }
                    };
                    bookList.add(newBook);
                    model.addRow(newBook);

                    BookCSVController bookCsvController = new BookCSVController();
                    BookInfo book = new BookInfo(title.getText(), author.getText(), ISBN.getText(), "가능", "0");
                    // 파일 write
                    bookCsvController.writeCSV(book);
                    // 파일 write 후에는 textfield 비워줌
                    title.setText("");
                    author.setText("");
                    ISBN.setText("");
                    dispose();

                    JOptionPane optionPane = new JOptionPane("도서 입력이 완료되었습니다.", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("도서 입력 완료");
                    dialog.setLocation(950, 300);
                    dialog.setVisible(true);
                }
            }
        }
    }
}
