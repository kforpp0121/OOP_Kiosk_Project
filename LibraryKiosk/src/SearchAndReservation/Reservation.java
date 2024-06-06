import SearchAndReservation.Book;
import SearchAndReservation.BookImage;
import SearchAndReservation.ReserveFinish;
import SearchAndReservation.Search;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Reservation extends JPanel{
    private JFrame frame;         // 전체 frame
    private JPanel panel;         // 전체 panel
    private JPanel panelMain;     // main panel
    private Book book;            // 도서 정보
    private int height = 250;     // 결과 유닛 높이
    private int width = 100;      // 결과 유닛 너비
    private Font font;            // 나눔 고딕 폰트
    private String csvFilePath = "lib_test.csv";  // 도서 목록

    public Reservation(Book book, JFrame frame) {
        this.book = book;      // 도서 정보
        this.frame = frame;    // 전체 frame
        setUIFont();           // 전체 font
        createUI();            // UI 생성
    }
    private void createUI() {
        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        ReserveLabel();   // label : 예약

        MainPanel();     // main panel : 도서 정보와 예약 버튼을 담을 panel

        BookData();      // 도서 정보

        ReserveButton(); // 예약 버튼

        BackPanel();     // 뒤로 가기

        panel.add(panelMain, BorderLayout.CENTER);   // 전체 panel에 main panel 추가
        add(panel, BorderLayout.CENTER);             // 기본 panel에 전체 panel 추가

        setVisible(true);
    }

    // 예약 label
    private void ReserveLabel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0xD9D9D9));

        JLabel title = new JLabel("예약", SwingConstants.CENTER);
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

    // 예약 도서 정보
    private void BookData() {
        JPanel bookPanel = new JPanel();
        bookPanel.setBorder(new EmptyBorder(80, 10, 30, 10));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        bookPanel.setPreferredSize(new Dimension(width*4+15, height));

        // 도서 사진
        BookImage coverPanel = new BookImage(book.getCoverImagePath());
        coverPanel.setPreferredSize(new Dimension(width+30, height-10));
        coverPanel.setBackground(Color.WHITE);

        // 도서 정보
        JPanel infoPanel = new JPanel(new GridLayout(3, 1)); // 3개의 정보를 세로로 표시하기 위한 패널
        infoPanel.setPreferredSize(new Dimension(width*3-15, height-10));
        infoPanel.setBackground(new Color(0xD9D9D9));  // 16진수 색상 코드 사용
        JLabel titleLabel = new JLabel(" 제목 : " + book.getTitle());
        JLabel authorLabel = new JLabel(" 저자 : " + book.getAuthor());
        JLabel reservationLabel = new JLabel(" 예약 : " + (book.isAvailable() ? "가능" : "불가능"));

        Font infoFont = font.deriveFont(Font.PLAIN, 20); // 나눔고딕, 크기 20
        titleLabel.setFont(infoFont);
        authorLabel.setFont(infoFont);
        reservationLabel.setFont(infoFont);

        infoPanel.add(titleLabel);
        infoPanel.add(authorLabel);
        infoPanel.add(reservationLabel);

        bookPanel.add(coverPanel); // 도서 표지 결과 추가
        bookPanel.add(infoPanel); // 도서 정보 결과 추가
        panelMain.add(bookPanel, BorderLayout.CENTER);
    }

    // 예약 버튼
    private void ReserveButton() {
        JPanel reservePanel = new JPanel(new BorderLayout());
        reservePanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        reservePanel.setBackground(Color.WHITE);

        JButton reserveButton = new JButton("예약하기");
        reserveButton.setBorder(new RoundedBorder(4, 2));
        reserveButton.setPreferredSize(new Dimension(250, 150));

        Font reserveFont = font.deriveFont(Font.BOLD, 30); // 나눔고딕, 굵은체, 크기 30
        reserveButton.setFont(reserveFont);
        reserveButton.setBackground(new Color(0x469C76));   // 버튼의 배경색
        reserveButton.setForeground(Color.WHITE);   // 버튼의 글자색

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.setReserved(true);
                setVisible(false);
                ReserveFinish reserveFinish = new ReserveFinish(book, frame);
                reserveFinish.setVisible(true);
                frame.add(reserveFinish);
            }
        });
        reservePanel.add(reserveButton, BorderLayout.CENTER);
        panelMain.add(reservePanel, BorderLayout.SOUTH);
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
        book.setReserved(false);   // 예약 취소
        setVisible(false);
        Search search = new Search(csvFilePath, frame);
        search.setVisible(true);
        frame.add(search);
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
