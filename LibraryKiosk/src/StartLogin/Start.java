package StartLogin;

import SearchAndReservation.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Start extends JPanel {
    private JFrame frame;              // 전체 frame
    private StartMain main;            // StartMain 인스턴스 참조
    private JPanel panel;              // 전체 panel
    private Font font;

    Color orangeColor = new Color(238, 121, 3);
    Color green = new Color(70, 156, 118);

    public Start(JFrame frame) {
        this.frame = frame;    // 전체 frame
        this.main = main; // main 객체 초기화
        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(null);  // null 레이아웃 사용
        panel.setBackground(Color.WHITE);

        StartLabel();   // 상단 레이블 설정
        MainPanel();    // 메인 패널 설정

        add(panel, BorderLayout.CENTER);  // 기본 panel에 전체 panel 추가
    }

    private void StartLabel() {
        JPanel titlePanel = new JPanel(new BorderLayout()); // 레이아웃 수정
        titlePanel.setBackground(new Color(0xD9D9D9));

        JLabel title = new JLabel("환영합니다", SwingConstants.CENTER);
        Font titleFont = new Font("Dialog", Font.BOLD, 40); // 폰트 설정
        title.setFont(titleFont);
        titlePanel.add(title, BorderLayout.CENTER); // 레이아웃 수정
        titlePanel.setBackground(Color.WHITE);

        titlePanel.setBounds(0, 100, 450, 60); // 위치와 크기 설정
        panel.add(titlePanel);
    }

    private void MainPanel() {
        JPanel panelMain = new JPanel(null);  // null 레이아웃 사용
        panelMain.setBackground(Color.WHITE);

        // 기존 라벨
        JLabel Label1 = new JLabel("기존");
        Label1.setBounds(90, 200, 400, 50);
        Label1.setFont(new Font("Dialog", Font.BOLD, 30));
        panelMain.add(Label1);

        // 처음 라벨
        JLabel Label2 = new JLabel("처음");
        Label2.setBounds(300, 200, 400, 50);
        Label2.setFont(new Font("Dialog", Font.BOLD, 30));
        panelMain.add(Label2);

        // 화살표 이미지 추가
        ImageIcon arrow = new ImageIcon("LibraryKiosk/src/StartLogin/arrow.jpg");
        JLabel label = new JLabel();
        label.setIcon(arrow);
        label.setBounds(60, 250, 200, 150);
        panelMain.add(label);

        // 화살표 이미지 추가
        ImageIcon arrow2 = new ImageIcon("LibraryKiosk/src/StartLogin/arrow.jpg");
        JLabel label2 = new JLabel();
        label2.setIcon(arrow2);
        label2.setBounds(270, 250, 200, 150);
        panelMain.add(label2);

        // 로그인 버튼
        JButton LoginButton = new JButton("로그인");
        LoginButton.setBounds(20, 400, 200, 150);
        LoginButton.setBackground(green);
        LoginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        LoginButton.setForeground(Color.WHITE);

        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인창 띄우기
                setVisible(false);
                Login login = new Login(frame);
                login.setVisible(true);
                frame.add(login);
            }
        });
        panelMain.add(LoginButton);

        // 회원가입 버튼
        JButton SignupButton = new JButton("회원가입");
        SignupButton.setBackground(orangeColor);
        SignupButton.setBounds(230, 400, 200, 150);
        SignupButton.setFont(new Font("Dialog", Font.BOLD, 20));
        SignupButton.setForeground(Color.WHITE);

        SignupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SwingUtilities.invokeLater(new Runnable() {
            		public void run() {
            			JFrame frame = new JFrame();
            			frame.setTitle("회원가입");              // 창 제목
            			frame.setSize(450, 700);  // 키오스크 화면 크기
            			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 실행 종료
                        frame.setLocation(300, 10);      // (300, 10) 위치에 배치

                        Start start = new Start(frame);
                        SignUp signUp = new SignUp(frame);
                        frame.add(signUp);
                        signUp.setVisible(true);

                        // GUI를 보이도록 설정
                        frame.setVisible(true);
            		}
            	});
            }
        });
        panelMain.add(SignupButton);

        panelMain.setBounds(0, 60, 450, 640); // 위치와 크기 설정
        panel.add(panelMain);
    }
}
