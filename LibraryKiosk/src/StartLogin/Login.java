package StartLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JPanel {
    private JFrame frame;              // 전체 frame
    private JPanel panel;              // 전체 panel
    private Font font;

    private Color orangeColor = new Color(238, 121, 3);
    private Color green = new Color(70, 156, 118);

    public Login(JFrame frame) {
        this.frame = frame;    // 전체 frame
        setUIFont(); 
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
    	
    	Font loginFont = font.deriveFont(Font.BOLD, 20); // 나눔고딕, 크기 20
  
        JPanel panelMain = new JPanel(new GridLayout(3, 1));
        
        JLabel title = new JLabel("로그인", SwingConstants.CENTER);
        title.setFont(font.deriveFont(Font.BOLD, 30));
        
        
     // 뒤로 가기 버튼 패널
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("뒤로가기");
        backButton.setBackground(orangeColor);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(450, 70));
        backButton.setFont(font.deriveFont(Font.BOLD, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        backPanel.add(backButton);
        backPanel.setBackground(Color.WHITE);

        // ID 패널
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idLabel = new JLabel(" 아이디 : ");
        idLabel.setFont(loginFont);
        JTextField usernameField = new JTextField(17);
        usernameField.setPreferredSize(new Dimension(300, 50));
        usernameField.setFont(loginFont);
        idPanel.add(idLabel);
        idPanel.add(usernameField);
        idPanel.setBackground(Color.WHITE);

        // 비밀번호 패널
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("비밀번호:");
        passLabel.setFont(loginFont);
        JPasswordField passwordField = new JPasswordField(17);
        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setFont(loginFont);
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(Color.WHITE);

        // 로그인 버튼 패널
        JPanel loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("로그인");
        loginButton.setPreferredSize(new Dimension(450, 100));
        loginButton.setBackground(green);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(font.deriveFont(Font.BOLD, 30));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 기능 구현
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "로그인 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginButtonPanel.add(loginButton, BorderLayout.NORTH);
        loginButtonPanel.setBackground(Color.WHITE);
        
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(10, 10));
        JPanel emptyContainer = new JPanel(new BorderLayout());
        emptyContainer.add(emptyPanel, BorderLayout.CENTER);
        
        panel.add(backPanel,BorderLayout.NORTH);
        panelMain.add(title);
        panelMain.add(idPanel);
        panelMain.add(passwordPanel);
        panelMain.setBackground(Color.WHITE);

        panel.add(panelMain, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);
    }

    private boolean validLogin(String username, String password) {
        String csvFilePath = "userdata.csv";

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

    private void goBack() {
        setVisible(false);
        Start start = new Start(frame);
        start.setVisible(true);
        frame.add(start);
    }
    
    public void setUIFont() {
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
