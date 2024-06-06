package StartLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class StartMain {

    private JFrame jf;
    Color orangeColor = new Color(238, 121, 3);
    Color green = new Color(70, 156, 118);

    public StartMain(String msg) {
        run();
    }

    public void run() {
        jf = new JFrame();
        jf.setTitle("시작화면");              // 창 제목
        jf.setSize(450, 700);  // 키오스크 화면 크기
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 실행 종료
        jf.setLocation(300, 10);      // (300, 10) 위치에 배치

        // 도서 대출 키오스크 GUI 생성
        Start start = new Start(jf, this);
        jf.add(start);
        start.setVisible(true);

        // GUI를 보이도록 설정
        jf.setVisible(true);
    }

    public void login() {
        JFrame jf2 = new JFrame("로그인창");
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setSize(450, 700);
        jf2.setLocation(300, 10);

        JPanel panel2 = new JPanel(new BorderLayout());
        jf2.add(panel2);

        JPanel formPanel = new JPanel(new GridLayout(3, 1));
        formPanel.setBackground(Color.WHITE);
        panel2.add(formPanel, BorderLayout.CENTER);

        JLabel title2 = new JLabel("로그인");
        title2.setFont(new Font("Dialog", Font.BOLD, 20));
        title2.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(title2);

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("아이디:   ")).setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField usernameField = new JTextField(30);
        usernameField.setPreferredSize(new Dimension(450, 50));
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        idPanel.add(usernameField);
        idPanel.setBackground(Color.WHITE);
        formPanel.add(idPanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("비밀번호:")).setFont(new Font("Dialog", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(30);
        passwordField.setPreferredSize(new Dimension(450, 50));
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(Color.WHITE);
        formPanel.add(passwordPanel);

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
                    JOptionPane.showMessageDialog(jf2, "로그인 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);
                    // MenuFirst menu = new MenuFirst();
                    // menu.setVisible(true);
                    jf2.dispose(); // 로그인 성공 후 창 닫기
                } else {
                    JOptionPane.showMessageDialog(jf2, "아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
                jf2.dispose();
            }
        });

        loginButtonPanel.add(loginButton);
        loginButtonPanel.setBackground(Color.WHITE);
        panel2.add(loginButtonPanel, BorderLayout.SOUTH);

        backPanel.add(backButton);
        backPanel.setBackground(Color.WHITE);
        panel2.add(backPanel, BorderLayout.NORTH);

        jf2.setVisible(true);
    }

    public void showSignUp() {
        // 프레임
        JFrame frame = new JFrame("회원 가입");
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel signupPanel = new JPanel(new GridLayout(13, 1, 5, 5));

        // 회원가입 라벨
        JLabel title3 = new JLabel("회원가입");
        title3.setFont(new Font("Dialog", Font.BOLD, 30));
        title3.setHorizontalAlignment(JLabel.CENTER);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title3);
        signupPanel.add(titlePanel);
        signupPanel.setBackground(Color.WHITE);
        titlePanel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("아이디:");
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(usernameLabel);
        usernamePanel.setBackground(Color.WHITE);
        signupPanel.add(usernamePanel);

        JPanel usernameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameFieldPanel.add(usernameField);
        usernameFieldPanel.setBackground(Color.WHITE);
        signupPanel.add(usernameFieldPanel);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.setBackground(Color.WHITE);
        signupPanel.add(passwordPanel);

        JPanel passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordFieldPanel.add(passwordField);
        passwordFieldPanel.setBackground(Color.WHITE);
        signupPanel.add(passwordFieldPanel);

        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        confirmPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.setBackground(Color.WHITE);
        signupPanel.add(confirmPasswordPanel);

        JPanel confirmPasswordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordFieldPanel.add(confirmPasswordField);
        confirmPasswordFieldPanel.setBackground(Color.WHITE);
        signupPanel.add(confirmPasswordFieldPanel);

        JLabel dobLabel = new JLabel("생년월일:");
        dobLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JComboBox<String> yearCombo = new JComboBox<>();
        for (int year = 1924; year <= 2024; year++) {
            yearCombo.addItem(Integer.toString(year));
        }
        yearCombo.setFont(new Font("Dialog", Font.PLAIN, 20));

        JComboBox<String> monthCombo = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            monthCombo.addItem(Integer.toString(month));
        }
        monthCombo.setFont(new Font("Dialog", Font.PLAIN, 20));

        JComboBox<String> dayCombo = new JComboBox<>();
        for (int day = 1; day <= 31; day++) {
            dayCombo.addItem(Integer.toString(day));
        }
        dayCombo.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dobLabel);
        dobPanel.setBackground(Color.WHITE);
        signupPanel.add(dobPanel);

        JPanel dobFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobFieldPanel.add(yearCombo);
        dobFieldPanel.add(monthCombo);
        dobFieldPanel.add(dayCombo);
        dobFieldPanel.setBackground(Color.WHITE);
        signupPanel.add(dobFieldPanel);

        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        namePanel.setBackground(Color.WHITE);
        signupPanel.add(namePanel);

        JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameFieldPanel.add(nameField);
        nameFieldPanel.setBackground(Color.WHITE);
        signupPanel.add(nameFieldPanel);

        JLabel phoneNumberLabel = new JLabel("전화번호:");
        phoneNumberLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField phoneNumberField = new JTextField(20);
        phoneNumberField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.setBackground(Color.WHITE);
        signupPanel.add(phoneNumberPanel);

        JPanel phoneNumberFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberFieldPanel.add(phoneNumberField);
        phoneNumberFieldPanel.setBackground(Color.WHITE);
        signupPanel.add(phoneNumberFieldPanel);

        JButton signupButton = new JButton("회원 가입");
        signupButton.setFont(new Font("Dialog", Font.BOLD, 20));
        signupButton.setForeground(Color.WHITE);
        signupButton.setPreferredSize(new Dimension(450, 50));
        signupButton.setBackground(orangeColor);

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
                if (isOkId(username, "userdata.csv")) {
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
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signupButton);

        frame.add(signupPanel);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
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

    public void showStartScreen() {
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new StartMain("시작 화면");
    }
}
