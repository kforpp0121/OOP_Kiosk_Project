package SearchAndReservation;

import SearchAndReservation.Book;
import SearchAndReservation.BookDatabase;
import SearchAndReservation.BookImage;
import SearchAndReservation.Reservation;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Search extends JPanel {
    private JFrame frame;              // 전체 frame
    private JPanel panel;              // 전체 panel
    private JTextField searchField;    // 검색창
    private JPanel resultPanel;        // 결과창
    private BookDatabase bookDatabase; // 도서 데이터
    private int result_num;            // 검색 결과 수
    private int height = 180;          // 결과 유닛 높이
    private int width = 100;           // 결과 유닛 너비
    private Font font;                 // 나눔 고딕 폰트

    public Search(String csvFilePath, JFrame frame) {
        bookDatabase = new BookDatabase(csvFilePath);  // 도서 csv 파일의 경로를 통해 데이터베이스를 받는다.
        this.frame = frame;    // 전체 frame
        setUIFont();           // 전체 font
        createUI();            // UI 생성
    }

    private void createUI() {
        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        SearchLabel(); // label : 검색

        MainPanel();   // main panel : 검색창과 결과창을 담을 panel

        BackPanel();   // 뒤로 가기

        add(panel, BorderLayout.CENTER);  // 기본 panel에 전체 panel 추가
    }

    // 검색 label
    private void SearchLabel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0xD9D9D9));

        JLabel title = new JLabel("검색", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        Font titleFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
        title.setFont(titleFont);
        titlePanel.add(title);

        panel.add(titlePanel, BorderLayout.NORTH);  // 전체 panel의 상단에 추가
    }

    // main panel
    private void MainPanel() {
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.setBackground(Color.WHITE);

        // 검색창
        searchField = new JTextField(15);        // 검색창 길이
        searchField.setBorder(new RoundedBorder(10, 4)); // 검색창 디자인
        searchField.setPreferredSize(new Dimension(100, 40));
        Font searchFieldFont = font.deriveFont(Font.PLAIN, 20); // 나눔고딕, 굵은체, 크기 25
        searchField.setFont(searchFieldFont); // 검색창의 글꼴 적용

        JButton searchButton = new JButton("검색");  // 검색 버튼
        searchButton.setBorder(new RoundedBorder(0, 2)); // 검색 버튼 디자인
        Font searchFont = font.deriveFont(Font.PLAIN, 20); // 나눔고딕, 크기 20
        searchButton.setFont(searchFont);                       // 글꼴 적용
        searchButton.setBackground(new Color(0xD9D9D9));   // 버튼의 배경색
        searchButton.setForeground(Color.BLACK);               // 버튼의 글자색
        searchButton.setPreferredSize(new Dimension(50, 40));

        searchButton.addActionListener(new ActionListener() {  // 버튼 event
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks();  // 검색 버튼을 누르면 search 시작
            }
        });

        JPanel searchPanel = new JPanel(); // 검색창 panel
        searchPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        searchPanel.setBackground(Color.WHITE);

        ImageIcon originalIcon = new ImageIcon(Search.class.getResource("SearchAndReservation/search_symbol.png"));  // 돋보기 아이콘
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(45, 30, Image.SCALE_SMOOTH); // 원하는 크기로 조절
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel searchLabel = new JLabel(scaledIcon);

        searchPanel.add(searchLabel);  // 돋보기 아이콘
        searchPanel.add(searchField);  // 검색 입력창
        searchPanel.add(searchButton); // 검색 버튼

        panelMain.add(searchPanel, BorderLayout.NORTH);  // main panel에 검색창 추가


        // 결과창
        resultPanel = new JPanel();  // 결과창 panel
        resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        resultPanel.setBackground(Color.WHITE);

        JScrollPane resultAll = new JScrollPane(resultPanel);  // Scroll
        resultAll.setPreferredSize(new Dimension(400, 400));
        resultAll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 스크롤 단위 조절을 위해 고정
        resultAll.getVerticalScrollBar().setUnitIncrement(10);  // 스크롤 시 단위

        panelMain.add(resultAll, BorderLayout.CENTER);  // main panel에 검색창 추가

        panel.add(panelMain, BorderLayout.CENTER);   // 전체 panel에 main panel 추가
    }

    // 뒤로가기
    private void BackPanel() {
        JButton back = new JButton("뒤로 가기");    // 뒤로가기 버튼
        back.setBorder(new EmptyBorder(20, 0, 20, 0));
        Font backFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
        back.setFont(backFont);
        back.setBackground(new Color(0xEE7930));   // 버튼의 배경색
        back.setForeground(Color.WHITE);               // 버튼의 글자색

        back.addActionListener(new ActionListener() {  // 뒤로가기 event
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        panel.add(back, BorderLayout.SOUTH);           // 전체 panel의 하단에 뒤로가기 버튼 추가
    }
    private void goBack() {

    }

    // 도서 검색
    private void searchBooks() {
        String query = searchField.getText().trim();  // 검색창에 입력한 문자열. 공백 제거.

        // 검색 입력이 없는 경우
        if (query.isEmpty()) {
            NeedInput needInput = new NeedInput(this);
            needInput.setVisible(true);
            return;
        }

        List<Book> results = bookDatabase.searchBooks(query);  // 데이터베이스에서 문자열에 해당하는 내용을 results에 저장

        // 검색 결과가 없는 경우
        if (results == null || results.isEmpty()) {
            NotfoundBook notFoundBook = new NotfoundBook(this);
            notFoundBook.setVisible(true);
            return;
        }

        resultPanel.removeAll(); // 이전 결과를 제거
        result_num = 0;          // 결과 개수 변수

        for (Book book : results) {  // 입력한 문자열과 일치하는 내용을 찾기 위해 for문 순환
            result_num += 1;     // 일치하는 내용을 찾을 때마다 결과 개수 +1

            // 결과 panel
            // 도서 사진, 도서 정보, 예약 버튼
            JPanel bookPanel = new JPanel();
            bookPanel.setBackground(Color.WHITE);
            bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            bookPanel.setPreferredSize(new Dimension(width*4+30, height));

            // 도서 사진
            BookImage coverPanel = new BookImage(book.getCoverImagePath());
            coverPanel.setPreferredSize(new Dimension(width+10, height-10));
            coverPanel.setBackground(Color.WHITE);

            // 도서 정보
            JPanel infoPanel = new JPanel(new GridLayout(3, 1)); // 3개의 정보를 세로로 표시하기 위한 패널
            infoPanel.setPreferredSize(new Dimension(width*2, height-10));
            infoPanel.setBackground(new Color(0xD9D9D9));  // 16진수 색상 코드 사용
            JLabel titleLabel = new JLabel(" 제목 : " + book.getTitle());
            JLabel authorLabel = new JLabel(" 저자 : " + book.getAuthor());
            JLabel reservationLabel = new JLabel(" 예약 : " + (book.isAvailable() ? "가능" : "불가능"));

            Font infoFont = font.deriveFont(Font.PLAIN, 15); // 나눔고딕, 크기 15
            titleLabel.setFont(infoFont);
            authorLabel.setFont(infoFont);
            reservationLabel.setFont(infoFont);

            infoPanel.add(titleLabel);
            infoPanel.add(authorLabel);
            infoPanel.add(reservationLabel);

            // 예약 panel과 예약 button
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.setPreferredSize(new Dimension(width+20, height-10));
            buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

            JButton reserveButton = new JButton("예약");
            Font reserveFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
            reserveButton.setFont(reserveFont);
            reserveButton.setBackground(new Color(0x469C76));   // 버튼의 배경색
            reserveButton.setForeground(Color.WHITE);   // 버튼의 글자색

            reserveButton.setEnabled(book.isAvailable()); // 예약이 가능하면 활성화
            reserveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (book.isAvailable()) {
                        setVisible(false);
                        Reservation reservation = new Reservation(book, frame);
                        reservation.setVisible(true);
                        frame.add(reservation);
                    } else {
                        JOptionPane.showMessageDialog(
                                Search.this,
                                "예약이 불가능합니다.",
                                "예약 실패",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });
            buttonPanel.add(reserveButton, BorderLayout.CENTER); // 버튼 panel에 button 추가

            bookPanel.add(coverPanel); // 도서 표지 결과 추가
            bookPanel.add(infoPanel); // 도서 정보 결과 추가
            bookPanel.add(buttonPanel); // 버튼 패널을 오른쪽에 추가

            resultPanel.add(bookPanel); // 결과 panel에 추가
        }
        resultPanel.setPreferredSize(new Dimension(300, result_num*height));
        resultPanel.revalidate(); // UI 업데이트
        resultPanel.repaint();    // UI 업데이트
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

    // 검색 하고자 하는 도서가 없을 때
    class NotfoundBook extends JDialog {
        public NotfoundBook(JPanel parent) {
            setTitle("Can Not Found Book");
            setLayout(new BorderLayout());

            JPanel nullPanel = new JPanel();

            JPanel textPanel = new JPanel();
            JLabel text = new JLabel("찾으시는 책이 목록에 없습니다.");
            Font textFont = font.deriveFont(Font.PLAIN, 15); // 나눔고딕, 크기 15
            text.setFont(textFont);
            textPanel.add(text);

            JPanel buttonPanel = new JPanel();
            JButton button = new JButton("확인");
            button.addActionListener(e->{setVisible(false);});
            buttonPanel.add(button);

            add(nullPanel, BorderLayout.NORTH);
            add(textPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(300,150);
            setLocationRelativeTo(parent);
        }
    }

    // 입력이 없을 때
    class NeedInput extends JDialog {
        public NeedInput(JPanel parent) {
            setTitle("Please Enter Book Information");
            setLayout(new BorderLayout());

            JPanel nullPanel = new JPanel();

            JPanel textPanel = new JPanel();
            JLabel text = new JLabel("찾으려는 책 제목이나 작가명을 입력해주세요.");
            Font textFont = font.deriveFont(Font.PLAIN, 15); // 나눔고딕, 크기 15
            text.setFont(textFont);
            textPanel.add(text);

            JPanel buttonPanel = new JPanel();
            JButton button = new JButton("확인");
            button.addActionListener(e->{setVisible(false);});
            buttonPanel.add(button);

            add(nullPanel, BorderLayout.NORTH);
            add(textPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(300,150);
            setLocationRelativeTo(parent);
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