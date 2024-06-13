package BorrowReturn;

import SearchAndReservation.*;
import StartLogin.Start;
import StartLogin.UserInfo;
import menu.MenuFirst;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Vector;

public class Return extends JPanel{
    private JFrame frame;         // 전체 frame
    private UserInfo userinfo;         // 사용자 정보
    private JPanel panel;         // 전체 panel
    private JPanel panelMain;     // main panel
    private JTextField ISBN;
    private Font font;            // 나눔 고딕 폰트
    Vector<Vector<String>> bookData;

    public Return(JFrame frame, UserInfo userinfo) {
        this.frame = frame;
        this.userinfo = userinfo;
        bookData = new BookCSVReader().readCSV();
        setUIFont();           // 전체 font
        createUI();            // UI 생성
    }

    private void createUI() {
        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        ReserveLabel();   // label : 대출

        MainPanel();     // main panel

        InputISBNPanel(); // ISBN 입력

        ReturnButton(); // 반납 버튼

        BackPanel();     // 뒤로 가기

        panel.add(panelMain, BorderLayout.CENTER);   // 전체 panel에 main panel 추가
        add(panel, BorderLayout.CENTER);             // 기본 panel에 전체 panel 추가

        setVisible(true);
    }

    // 반납 label
    private void ReserveLabel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0xD9D9D9));

        JLabel title = new JLabel("반납", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        Font titleFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
        title.setFont(titleFont);
        titlePanel.add(title);

        panel.add(titlePanel, BorderLayout.NORTH);
    }

    // main panel
    private void MainPanel() {
        panelMain = new JPanel(new BorderLayout());
        panelMain.setBackground(Color.WHITE);
    }

    // ISBN 입력
    private void InputISBNPanel(){
        JPanel ISBNPanel = new JPanel();
        ISBNPanel.setBackground(new Color(255,255,255));

        JPanel msgPanel = new JPanel();
        msgPanel.setPreferredSize(new Dimension(400, 300));
        msgPanel.setBackground(new Color(255,255,255));

        JLabel msg1 = new JLabel("<html><br><br><br>도서의 ISBN을 입력해주세요.<br></html>", SwingConstants.CENTER);
        JLabel msg2 = new JLabel("<html><br>도서의 ISBN은 도서 뒷면의<br></html>", SwingConstants.CENTER);
        JLabel msg3 = new JLabel("<html>바코드 번호입니다.<br></html>", SwingConstants.CENTER);

        Font msgFont = font.deriveFont(Font.BOLD, 30); // 나눔고딕, 크기 20
        msg1.setFont(msgFont);
        msg2.setFont(msgFont);
        msg3.setFont(msgFont);

        msgPanel.add(msg1);
        msgPanel.add(msg2);
        msgPanel.add(msg3);

        JLabel ISBNLabel = new JLabel("ISBN");
        Font titleFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
        ISBNLabel.setFont(titleFont);

        ISBN = new JTextField();
        ISBN.setPreferredSize(new Dimension(300, 50));
        Font ISBNFont = font.deriveFont(Font.PLAIN, 20); // 나눔고딕, 크기 20
        ISBN.setFont(ISBNFont);

        ISBNPanel.add(msgPanel);
        ISBNPanel.add(ISBNLabel);
        ISBNPanel.add(ISBN);

        panelMain.add(ISBNPanel, BorderLayout.CENTER);
    }

    // 반납 버튼
    private void ReturnButton() {
        JPanel returnPanel = new JPanel(new BorderLayout());
        returnPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        returnPanel.setBackground(Color.WHITE);

        JButton returnButton = new JButton("반납하기");
        returnButton.setBorder(new Return.RoundedBorder(4, 2));
        returnButton.setPreferredSize(new Dimension(250, 150));

        Font borrowFont = font.deriveFont(Font.BOLD, 30); // 나눔고딕, 굵은체, 크기 30
        returnButton.setFont(borrowFont);
        returnButton.setBackground(new Color(0x469C76));   // 버튼의 배경색
        returnButton.setForeground(Color.WHITE);   // 버튼의 글자색

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> brInformationData = new BR_InformationCSVController().readCSV();
                if(ISBN.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "ISBN 번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(ISBN.getText().length()!=13){
                    JOptionPane.showMessageDialog(frame, "ISBN 번호는 13자리입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Optional<Vector<String>> selectedBook = bookData.stream()
                        .filter(book -> book.get(2).equals(ISBN.getText()))
                        .findFirst();
                String selectedBookISBN;
                if (selectedBook.isPresent()) {
                    selectedBookISBN = selectedBook.get().get(2);
                    // ...
                } else {
                    JOptionPane.showMessageDialog(frame, "해당 도서가 존재하지 않습니다.\nISBN 번호를 다시 확인해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Optional<Vector<String>> selectedBRInformation = brInformationData.stream()
                        .filter(brInformation -> brInformation.get(0).equals(selectedBookISBN) && brInformation.get(1).equals(userinfo.getUsername()))
                        .findFirst();
                if (!selectedBRInformation.isPresent()) {
                    JOptionPane.showMessageDialog(frame, "해당 도서를 대출한 기록이 없습니다.\nISBN 번호를 다시 확인해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    frame.getContentPane().removeAll();
                    ReturnFinish returnFinish = new ReturnFinish(frame, userinfo);
                    returnFinish.setVisible(true);
                    frame.add(returnFinish);
                    frame.revalidate();
                });
                new BR_InformationCSVController().deleteCSV(selectedBookISBN, userinfo.getUsername());
            }
        });
        returnPanel.add(returnButton, BorderLayout.CENTER);
        panelMain.add(returnPanel, BorderLayout.SOUTH);
    }

    // 뒤로 가기
    private void BackPanel() {
        JButton back = new JButton("뒤로 가기");  // 뒤로가기 버튼
        back.setBorder(new EmptyBorder(20, 0, 20, 0));
        Font backFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
        back.setFont(backFont);
        back.setBackground(new Color(0xEE7930));  // 버튼의 배경색
        back.setForeground(Color.WHITE);   // 버튼의 글자색

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        panel.add(back, BorderLayout.SOUTH); // 전체 panel의 하단에 뒤로가기 버튼 추가
    }
    private void goBack() {
        SwingUtilities.invokeLater(() -> {
            frame.getContentPane().removeAll();
            MenuFirst menuFirst = new MenuFirst(frame, userinfo);
            menuFirst.setVisible(true);
            frame.add(menuFirst);
            frame.revalidate();
        });
    }

    // 폰트 적용
    private void setUIFont() {
        // 나눔 고딕 폰트 파일 경로
        String fontPath = "LibraryKiosk/font/NanumGothic.ttf";

        // 폰트 파일로부터 폰트 객체 생성
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 12);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    class RoundedBorder extends AbstractBorder {
        private int radius;
        private int thickness;

        RoundedBorder(int radius, int thickness) {
            this.radius = radius;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = thickness;
            return insets;
        }
    }
}
