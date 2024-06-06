package StartLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JPanel {
    private JFrame frame;              // 전체 frame
    private StartMain main;            // StartMain 인스턴스 참조
    private JPanel panel;              // 전체 panel

    private Color orangeColor = new Color(238, 121, 3);
    private Color green = new Color(70, 156, 118);

    public Login(JFrame frame) {
        this.frame = frame;    // 전체 frame
        createUI();
    }

    private void createUI() {

        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        MainPanel();    // 메인 패널 설정

        add(panel, BorderLayout.CENTER);  // 기본 panel에 전체 panel 추가
    }


    private void MainPanel() {
        JPanel panelMain = new JPanel(new GridLayout(5, 1));
        
        JLabel title = new JLabel("로그인", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 30));
        
        
     // 뒤로 가기 버튼 패널
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("뒤로가기");
        backButton.setBackground(orangeColor);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(450, 70));
        backButton.setFont(new Font("Dialog", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStartScreen(); // 이전의 시작 화면을 보여줌
                frame.dispose();
            }
        });
        backPanel.add(backButton);
        backPanel.setBackground(Color.WHITE);

        // ID 패널
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idLabel = new JLabel("아이디:   ");
        idLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField usernameField = new JTextField(23);
        usernameField.setPreferredSize(new Dimension(300, 50));
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        idPanel.add(idLabel);
        idPanel.add(usernameField);
        idPanel.setBackground(Color.WHITE);

        // 비밀번호 패널
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("비밀번호:");
        passLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(30);
        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(Color.WHITE);

        // 로그인 버튼 패널
        JPanel loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("로그인");
        loginButton.setPreferredSize(new Dimension(450, 100));
        loginButton.setBackground(green);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 기능 구현
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "로그인 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);
                    // MenuFirst menu = new MenuFirst();
                    // menu.setVisible(true);
                    frame.dispose(); // 로그인 성공 후 창 닫기
                } else {
                    JOptionPane.showMessageDialog(frame, "아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginButtonPanel.add(loginButton);
        loginButtonPanel.setBackground(Color.WHITE);
        
        panelMain.add(backPanel);
        panelMain.add(title);
        panelMain.add(idPanel);
        panelMain.add(passwordPanel);
        panelMain.add(loginButtonPanel);
        panelMain.setBackground(Color.WHITE);

        panel.add(panelMain, BorderLayout.CENTER);
    }

    private boolean validLogin(String username, String password) {
        String csvFilePath = "LibraryKiosk/src/StartLogin/userdata.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                String savedUsername = userData[0];
                String savedPassword = userData[1];
                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void showStartScreen() {
    	frame.setVisible(true);
    }
}
