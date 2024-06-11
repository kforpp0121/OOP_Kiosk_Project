package SearchAndReservation;

import StartLogin.Login;
import menu.MenuFirst;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ReserveFinish extends JPanel {
    private JFrame frame;             // 전체 frame
    private JPanel panel;             // 전체 panel
    private JProgressBar progressBar; // 타임바
    private Font font;                // 나눔 고딕 폰트
    private Book book;            // 도서 정보

    public ReserveFinish(Book book, JFrame frame) {
        this.book = book;      // 도서 정보
        this.frame = frame;    // 전체 frame
        setUIFont();           // 전체 font
        createUI();            // UI 생성
        startTimer();          // 타이머 시작
    }
    private void createUI() {
        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        EndLabel();         // 완료 label

        TimeBar(30);   // 30초 타이머

        BackPanel();        // 뒤로 가기

        add(panel, BorderLayout.CENTER); // 기본 panel에 전체 panel 추가
        setVisible(true);
    }

    // 완료 문구
    private void EndLabel() {
        JPanel end = new JPanel(new GridLayout(2, 1));
        end.setBackground(Color.WHITE);

        JLabel end1 = new JLabel("<html><center>예약이<br>완료되었습니다</center></html>" , SwingConstants.CENTER);
        JLabel end2 = new JLabel("<html><center>이용해주셔서<br>감사합니다</center></html>" , SwingConstants.CENTER);

        Font end1Font = font.deriveFont(Font.BOLD, 50); // 나눔고딕, 굵은체, 크기 50
        Font end2Font = font.deriveFont(Font.PLAIN, 40); // 나눔고딕, 크기 40

        end1.setFont(end1Font);
        end2.setFont(end2Font);
        end.add(end1);
        end.add(end2);

        end1.setBorder(new EmptyBorder(100, 0, 0, 0));
        end2.setBorder(new EmptyBorder(0, 0, 150, 0));

        panel.add(end, BorderLayout.CENTER);
    }

    // Timer
    private void TimeBar(int sec) {
        progressBar = new JProgressBar();
        progressBar.setMaximum(sec); // 최대값을 30으로 설정 (30초)
        progressBar.setValue(sec); // 초기값을 30으로 설정
        panel.add(progressBar, BorderLayout.NORTH);
    }
    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                if (value > 0) {
                    progressBar.setValue(value - 1); // 타임바 값 1씩 감소
                } else {
                    // 타임바 값이 0이 되면 홈 화면으로 이동
                    goHome();
                }
            }
        });
        timer.start();
    }

    // 홈으로
    private void goHome() {
        // 홈 화면으로 이동하는 코드
        book.setReserved(false);
        setVisible(false);
        MenuFirst menuFirst = new MenuFirst(frame, Login.userInfo);
        menuFirst.setVisible(true);
        frame.add(menuFirst);
    }

    // 메뉴로
    private void BackPanel() {
        JButton back = new JButton("메뉴로 돌아가기");  // 메뉴로 이동
        back.setBorder(new EmptyBorder(20, 0, 20, 0));
        Font backFont = font.deriveFont(Font.BOLD, 25); // 나눔고딕, 굵은체, 크기 25
        back.setFont(backFont);
        back.setBackground(new Color(0xEE7930));  // 버튼의 배경색
        back.setForeground(Color.WHITE);   // 버튼의 글자색

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goHome();
            }
        });

        panel.add(back, BorderLayout.SOUTH); // 전체 panel의 하단에 뒤로가기 버튼 추가
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
}
