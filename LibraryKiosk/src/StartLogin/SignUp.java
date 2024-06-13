package StartLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;

public class SignUp extends JPanel {
	private JFrame frame;              // 전체 frame          // StartMain 인스턴스 참조
    private JPanel panel;              // 전체 panel
    private Font font;

    private Color orangeColor = new Color(238, 121, 3);
    private Color green = new Color(70, 156, 118);

    public SignUp(JFrame frame) {
        this.frame = frame;    // 전체 frame
        setUIFont();
        createUI();
    }

    private void createUI() {

        setLayout(new BorderLayout());   // 기본 panel 설정

        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        StartLabel();   // 상단 레이블 설정
        MainPanel();    // 메인 패널 설정

        add(panel, BorderLayout.CENTER);  // 기본 panel에 전체 panel 추가
    }

    private void StartLabel() {
     // 회원가입 라벨
        
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
        panel.add(backPanel, BorderLayout.NORTH);
        
    }

    private void MainPanel() {
    	Font signupFont = font.deriveFont(Font.BOLD, 20);
    	JPanel empty = new JPanel();
    	empty.setBackground(Color.WHITE);  	
    	
    	
    	JPanel MainPanel = new JPanel(new GridLayout(14, 1));
    	
    	JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(0xD9D9D9));
    	
    	JLabel title = new JLabel("회원가입");
        title.setFont(font.deriveFont(Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setBackground(Color.WHITE);
        MainPanel.add(titlePanel);
        
        MainPanel.add(empty);
        
        
        JLabel usernameLabel = new JLabel("아이디:");
        usernameLabel.setFont(signupFont);
        JTextField usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(0, 30));
        usernameField.setFont(signupFont);


        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(usernameLabel);
        usernamePanel.setBackground(Color.WHITE);
        MainPanel.add(usernamePanel);

        JPanel usernameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameFieldPanel.add(usernameField);
        usernameFieldPanel.setBackground(Color.WHITE);
        MainPanel.add(usernameFieldPanel);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setFont(signupFont);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setFont(signupFont);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.setBackground(Color.WHITE);
        MainPanel.add(passwordPanel);

        JPanel passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordFieldPanel.add(passwordField);
        passwordFieldPanel.setBackground(Color.WHITE);
        passwordFieldPanel.setPreferredSize(new Dimension(150, 30));
        MainPanel.add(passwordFieldPanel);

        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        confirmPasswordLabel.setFont(signupFont);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(signupFont);

        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.setBackground(Color.WHITE);
        MainPanel.add(confirmPasswordPanel);

        JPanel confirmPasswordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordFieldPanel.add(confirmPasswordField);
        confirmPasswordFieldPanel.setBackground(Color.WHITE);
        MainPanel.add(confirmPasswordFieldPanel);

        JLabel dobLabel = new JLabel("생년월일:");
        dobLabel.setFont(signupFont);
        JComboBox<String> yearCombo = new JComboBox<>();
        for (int year = 1924; year <= 2024; year++) {
            yearCombo.addItem(Integer.toString(year));
        }
        yearCombo.setFont(signupFont);

        JComboBox<String> monthCombo = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            monthCombo.addItem(Integer.toString(month));
        }
        monthCombo.setFont(signupFont);

        JComboBox<String> dayCombo = new JComboBox<>();
        for (int day = 1; day <= 31; day++) {
            dayCombo.addItem(Integer.toString(day));
        }
        dayCombo.setFont(signupFont);

        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dobLabel);
        dobPanel.setBackground(Color.WHITE);
        MainPanel.add(dobPanel);

        JPanel dobFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobFieldPanel.add(yearCombo);
        dobFieldPanel.add(monthCombo);
        dobFieldPanel.add(dayCombo);
        dobFieldPanel.setBackground(Color.WHITE);
        MainPanel.add(dobFieldPanel);

        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setFont(signupFont);
        JTextField nameField = new JTextField(20);
        nameField.setFont(signupFont);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        namePanel.setBackground(Color.WHITE);
        MainPanel.add(namePanel);

        JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameFieldPanel.add(nameField);
        nameFieldPanel.setBackground(Color.WHITE);
        MainPanel.add(nameFieldPanel);

        JLabel phoneNumberLabel = new JLabel("전화번호:");
        phoneNumberLabel.setFont(signupFont);
        JTextField phoneNumberField = new JTextField(20);
        phoneNumberField.setFont(signupFont);
        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.setBackground(Color.WHITE);
        MainPanel.add(phoneNumberPanel);

        JPanel phoneNumberFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberFieldPanel.add(phoneNumberField);
        phoneNumberFieldPanel.setBackground(Color.WHITE);
        MainPanel.add(phoneNumberFieldPanel);

        JButton signupButton = new JButton("회원 가입");
        signupButton.setFont(font.deriveFont(Font.BOLD, 30));
        signupButton.setForeground(Color.WHITE);
        signupButton.setPreferredSize(new Dimension(450, 70));
        signupButton.setBackground(green);
        
        JButton IDCheck = new JButton("중복 확인");

        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String dob = yearCombo.getSelectedItem() + "-" + monthCombo.getSelectedItem() + "-" + dayCombo.getSelectedItem();
            String phoneNumber = phoneNumberField.getText();
            String name = nameField.getText();

            if (username.isEmpty() || password.isEmpty() || dob.isEmpty() || phoneNumber.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "정보를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username.length() < 8) {
                JOptionPane.showMessageDialog(frame, "8글자 이상 아이디를 설정해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (isOkId(username, "LibraryKiosk/csv/userdata.csv")) {
                    JOptionPane.showMessageDialog(frame, "중복된 아이디입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (IOException ex) {
                System.err.println("CSV 파일 읽기 오류");
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vector<String> userData = new Vector<>();
            userData.add(username);
            userData.add(password);
            userData.add(dob);
            userData.add(phoneNumber);
            userData.add(name);
            
            CSVWriter.writeUserData(userData);

            JOptionPane.showMessageDialog(frame, "회원 가입이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            
            goBack();
        });

        
        panel.add(signupButton, BorderLayout.SOUTH);
        MainPanel.setBackground(Color.WHITE);
        panel.add(MainPanel);
    }
    
    public static boolean isOkId(String username, String csvFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0 && values[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void goBack() {
        setVisible(false);
        Start start = new Start(frame);
        start.setVisible(true);
        frame.add(start);
    }
    
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
