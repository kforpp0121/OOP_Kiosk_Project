package StartLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import SearchAndReservation.Search;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Start extends JPanel {
    private JFrame frame;              // 전체 frame
    private JPanel panel;              // 전체 panel
    private Font font;

    Color orangeColor = new Color(238, 121, 3);
    Color green = new Color(70, 156, 118);

    public Start(JFrame frame) {
        this.frame = frame;    // 전체 frame
        setUIFont();
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
        Font startFont = font.deriveFont(Font.BOLD, 30);

        // 기존 라벨
        JLabel Label1 = new JLabel("기존");
        Label1.setBounds(80, 200, 400, 50);
        Label1.setFont(startFont);
        panelMain.add(Label1);

        // 처음 라벨
        JLabel Label2 = new JLabel("처음");
        Label2.setBounds(295, 200, 400, 50);
        Label2.setFont(startFont);
        panelMain.add(Label2);

        // 화살표 이미지 추가
        ImageIcon arrow = new ImageIcon("arrow.jpg");
        JLabel label = new JLabel();
        label.setIcon(arrow);
        label.setBounds(50, 270, 200, 150);
        panelMain.add(label);

        // 화살표 이미지 추가
        ImageIcon arrow2 = new ImageIcon("arrow.jpg");
        JLabel label2 = new JLabel();
        label2.setIcon(arrow2);
        label2.setBounds(265, 270, 200, 150);
        panelMain.add(label2);

        // 로그인 버튼
        JButton LoginButton = new JButton("로그인");
        LoginButton.setBounds(10, 430, 200, 150);
        LoginButton.setBackground(green);
        LoginButton.setFont(startFont);
        LoginButton.setForeground(Color.WHITE);

        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        SignupButton.setBounds(225, 430, 200, 150);
        SignupButton.setFont(startFont);
        SignupButton.setForeground(Color.WHITE);

        SignupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                SignUp signUp = new SignUp(frame);
                signUp.setVisible(true);
                frame.add(signUp);
            }
        });
        panelMain.add(SignupButton);

        panelMain.setBounds(0, 60, 450, 640); // 위치와 크기 설정
        panel.add(panelMain);
    }
    
    private void goBack() {
        setVisible(false);
        Start start = new Start(frame);
        start.setVisible(true);
        frame.add(start);
    }
    
    private void setUIFont() {
        // 나눔 고딕 폰트 파일 경로
        String fontPath = "NanumGothic.ttf";

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
