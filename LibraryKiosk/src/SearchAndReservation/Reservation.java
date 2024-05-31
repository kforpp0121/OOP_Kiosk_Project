package SearchAndReservation;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reservation extends JFrame {
    private JPanel panel;         // 전체 panel
    private JPanel panelMain;     // main panel
    private Book book;
    private int height = 200;
    private JFrame previousFrame; // 이전 화면을 저장

    public Reservation(Book book, JFrame previousFrame) {
        this.previousFrame = previousFrame;
        this.book = book;
        createUI();
    }
    private void createUI() {
        setTitle("Reservation");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // 화면 중앙에 컴포넌트 배치


        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        ReserveLabel();   // label : 예약

        MainPanel();     // main panel : 도서 정보와 예약 버튼을 담을 panel

        BookData();      // 도서 정보

        ReserveButton(); // 예약 버튼

        BackPanel();     // 뒤로 가기

        panel.add(panelMain, BorderLayout.CENTER);
        add(panel);

        setVisible(true);
    }

    // 예약 label
    private void ReserveLabel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.GRAY);

        JLabel title = new JLabel("예약", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        Font titleFont = new Font("Dialog", Font.BOLD, 25); // 글꼴, 굵은체, 크기 20
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
        bookPanel.setBorder(new EmptyBorder(50, 10, 30, 10));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        bookPanel.setPreferredSize(new Dimension(400, height));

        // 도서 사진
        BookImage coverPanel = new BookImage(book.getCoverImagePath());
        coverPanel.setPreferredSize(new Dimension(100, height-10));
        coverPanel.setBackground(Color.WHITE);

        // 도서 정보
        JPanel infoPanel = new JPanel(new GridLayout(3, 1)); // 3개의 정보를 세로로 표시하기 위한 패널
        infoPanel.setPreferredSize(new Dimension(250, height-10));
        JLabel titleLabel = new JLabel("제목: " + book.getTitle());
        JLabel authorLabel = new JLabel("저자: " + book.getAuthor());
        JLabel reservationLabel = new JLabel("예약: " + (book.isAvailable() ? "가능" : "불가능"));

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

        Font reserveFont = new Font("Dialog", Font.BOLD, 30); // 글꼴, 굵은체, 크기 17
        reserveButton.setFont(reserveFont);
        reserveButton.setBackground(Color.GREEN);   // 버튼의 배경색
        reserveButton.setForeground(Color.BLACK);   // 버튼의 글자색

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.setReserved(true);
                dispose();
                new ReserveFinish(Reservation.this);
            }
        });
        reservePanel.add(reserveButton, BorderLayout.CENTER);
        panelMain.add(reservePanel, BorderLayout.SOUTH);
    }

    // 뒤로 가기
    private void BackPanel() {
        JButton back = new JButton("뒤로 가기");  // 뒤로가기 버튼
        back.setBorder(new EmptyBorder(10, 0, 10, 0));
        Font backFont = new Font("Dialog", Font.BOLD, 20); // 글꼴, 굵은체, 크기 20
        back.setFont(backFont);
        back.setBackground(Color.ORANGE);  // 버튼의 배경색
        back.setForeground(Color.BLACK);   // 버튼의 글자색

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        panel.add(back, BorderLayout.SOUTH); // 전체 panel의 하단에 뒤로가기 버튼 추가
    }
    private void goBack() {
        previousFrame.setVisible(true); // 이전 화면을 보이도록 설정
        dispose(); // 현재 화면 닫기
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
