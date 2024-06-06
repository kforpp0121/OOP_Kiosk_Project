import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JPanel {
    private JFrame jf;              // 전체 frame
    private StartMain main;            // StartMain 인스턴스 참조
    private JPanel panel;              // 전체 panel
    private Font font;

    Color orangeColor = new Color(238, 121, 3);
    Color green = new Color(70, 156, 118);

    public Start(JFrame jf, StartMain main) {
        this.jf = jf;    // 전체 frame
        this.main = main;      // StartMain 인스턴스
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
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0xD9D9D9));

        JLabel title = new JLabel("노인을 위한 도서 대출 시스템입니다", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        Font titleFont = new Font("Dialog", Font.BOLD, 25); // 폰트 설정
        title.setFont(titleFont);
        titlePanel.add(title);

        panel.add(titlePanel, BorderLayout.NORTH);  // 전체 panel의 상단에 추가
    }

    private void MainPanel() {
        JPanel panelMain = new JPanel(null);  // null 레이아웃 사용
        panelMain.setBackground(Color.WHITE);

        // 기존 라벨
        JLabel Label1 = new JLabel("기존");
        Label1.setBounds(100, 300, 400, 50);
        Label1.setFont(new Font("Dialog", Font.BOLD, 20));
        panelMain.add(Label1);

        // 처음 라벨
        JLabel Label2 = new JLabel("처음");
        Label2.setBounds(300, 300, 400, 50);
        Label2.setFont(new Font("Dialog", Font.BOLD, 20));
        panelMain.add(Label2);

        // 화살표 이미지 추가
        ImageIcon arrow = new ImageIcon("arrow.jpg");
        JLabel label = new JLabel();
        label.setIcon(arrow);
        label.setBounds(60, 350, 200, 150);
        panelMain.add(label);

        // 화살표 이미지 추가
        ImageIcon arrow2 = new ImageIcon("arrow.jpg");
        JLabel label2 = new JLabel();
        label2.setIcon(arrow2);
        label2.setBounds(260, 350, 200, 150);
        panelMain.add(label2);

        // 로그인 버튼
        JButton LoginButton = new JButton("로그인");
        LoginButton.setBounds(20, 500, 200, 150);
        LoginButton.setBackground(green);
        LoginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        LoginButton.setForeground(Color.WHITE);

        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인창 띄우기
                main.login();
                jf.dispose();
            }
        });
        panelMain.add(LoginButton);

        // 회원가입 버튼
        JButton SignupButton = new JButton("회원가입");
        SignupButton.setBackground(orangeColor);
        SignupButton.setBounds(230, 500, 200, 150);
        SignupButton.setFont(new Font("Dialog", Font.BOLD, 20));
        SignupButton.setForeground(Color.WHITE);

        SignupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원 가입 창을 띄우는 메서드 호출
                main.showSignUp();
            }
        });
        panelMain.add(SignupButton);

        panel.add(panelMain);
    }
}
