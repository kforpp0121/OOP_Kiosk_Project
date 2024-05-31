package Start;

import CSVController.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class StartMain {
	
	private JPanel panel;
	private JFrame jf;
	Color orangeColor = new Color(238, 121, 3);
	Color green= new Color(70, 156, 118);
	
    public StartMain(String msg) {
        jf = new JFrame(msg);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(450, 700);
        jf.setLocation(300, 10); 

        panel = new JPanel();
        panel.setLayout(null);
        jf.add(panel); 
        Start();
        jf.setVisible(true);
    }
    
    public void Start() {
    	panel.setBackground(Color.WHITE);
    	
    	JLabel welcomeLabel = new JLabel("노인을 위한 도서 대출 시스템입니다");
        welcomeLabel.setBounds(55, 80, 400, 50);
        welcomeLabel.setFont(new Font("Dialog", Font.BOLD,20));
        
        //기존 라벨
        JLabel Label1 = new JLabel("기존");
        Label1.setBounds(100, 300, 400, 50);
        Label1.setFont(new Font("Dialog", Font.BOLD, 20));
        
        //처음 라벨
        JLabel Label2 = new JLabel("처음");
        Label2.setBounds(300, 300, 400, 50);
        Label2.setFont(new Font("Dialog", Font.BOLD, 20));
        
        panel.add(welcomeLabel);
        panel.add(Label1);
        panel.add(Label2);
        
        //화살표 이미지 추가
        ImageIcon arrow = new ImageIcon("arrow.jpg");
        JLabel label = new JLabel();
        label.setIcon(arrow);
        label.setBounds(60, 350, 200, 150);
        panel.add(label);
        
      //화살표 이미지 추가
        ImageIcon arrow2 = new ImageIcon("arrow.jpg");
        JLabel label2 = new JLabel();
        label2.setIcon(arrow2);
        label2.setBounds(260, 350, 200, 150);
        panel.add(label2);
    	
        //로그인 버튼
    	JButton LoginButton = new JButton("로그인");
    	LoginButton.setBounds(20, 500, 200, 150);
    	LoginButton.setBackground(green);
    	LoginButton.setFont(new Font ("Dialog", Font.BOLD, 20));
    	
    	LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인창 띄우기
                login();
                jf.dispose();  
            }
        });
        panel.add(LoginButton);
    	
        //회원가입 버튼
    	JButton SignupButton = new JButton("회원가입");
    	SignupButton.setBackground(orangeColor);
    	SignupButton.setBounds(230, 500, 200, 150);
    	SignupButton.setFont(new Font ("Dialog", Font.BOLD, 20));
    	
        SignupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원 가입 창을 띄우는 메서드 호출
                showSignUp();
            }
        });
        panel.add(SignupButton);
    }

    // 로그인 UI 구성 메서드
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
        title2.setFont(new Font("Dialog", Font.BOLD, 30));
        title2.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(title2);

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("아이디:   ")).setFont(new Font ("Dialog", Font.BOLD, 20));
        JTextField usernameField = new JTextField(30);
        usernameField.setPreferredSize(new Dimension(450, 50));
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        idPanel.add(usernameField);
        idPanel.setBackground(Color.WHITE); 
        formPanel.add(idPanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 비밀번호 입력 패널
        passwordPanel.add(new JLabel("비밀번호:")).setFont(new Font ("Dialog", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(30);
        passwordField.setPreferredSize(new Dimension(450, 50));
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordPanel.add(passwordField);
        passwordPanel.setBackground(Color.WHITE); 
        formPanel.add(passwordPanel);

        JPanel loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 로그인 버튼 패널
        JButton loginButton = new JButton("로그인");
        loginButton.setPreferredSize(new Dimension(450, 100));
        loginButton.setBackground(green);
        loginButton.setFont(new Font ("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 기능 구현
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validLogin(username, password)) {
                    JOptionPane.showMessageDialog(jf2, "로그인 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(jf2, "아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 로그인 버튼 패널
        JButton backButton = new JButton("뒤로가기");
        backButton.setBackground(orangeColor);
        backButton.setPreferredSize(new Dimension(450, 70));


        backButton.setFont(new Font ("Dialog", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Start();
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

        JPanel signupPanel = new JPanel(new GridLayout(13, 1, 5, 5)); // 14행 1열, 컴포넌트 간 간격 추가

        // 회원가입 라벨
        JLabel title3 = new JLabel("회원가입");
        title3.setFont(new Font("Dialog", Font.BOLD, 30));
        title3.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title3);
        signupPanel.add(titlePanel);

        // 아이디 입력 필드 추가
        JLabel usernameLabel = new JLabel("아이디:");
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(usernameLabel);
        signupPanel.add(usernamePanel);
        
        JPanel usernameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameFieldPanel.add(usernameField);
        signupPanel.add(usernameFieldPanel);

        // 비밀번호 입력 필드 추가
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        signupPanel.add(passwordPanel);
        
        JPanel passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordFieldPanel.add(passwordField);
        signupPanel.add(passwordFieldPanel);

        // 비밀번호 확인 입력 필드 추가
        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        confirmPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordPanel.add(confirmPasswordLabel);
        signupPanel.add(confirmPasswordPanel);
        
        JPanel confirmPasswordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordFieldPanel.add(confirmPasswordField);
        signupPanel.add(confirmPasswordFieldPanel);

        // 생년월일 입력 필드 추가 (JComboBox 사용)
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
        signupPanel.add(dobPanel);
        
        JPanel dobFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobFieldPanel.add(yearCombo);
        dobFieldPanel.add(monthCombo);
        dobFieldPanel.add(dayCombo);
        signupPanel.add(dobFieldPanel);

        // 이름 입력 필드 추가
        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        signupPanel.add(namePanel);
        
        JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameFieldPanel.add(nameField);
        signupPanel.add(nameFieldPanel);

        // 전화번호 입력 필드 추가
        JLabel phoneNumberLabel = new JLabel("전화번호:");
        phoneNumberLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        JTextField phoneNumberField = new JTextField(20);
        phoneNumberField.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JPanel phoneNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberPanel.add(phoneNumberLabel);
        signupPanel.add(phoneNumberPanel);
        
        JPanel phoneNumberFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneNumberFieldPanel.add(phoneNumberField);
        signupPanel.add(phoneNumberFieldPanel);

        // 회원 가입 버튼 추가
        JButton signupButton = new JButton("회원 가입");
        signupButton.setFont(new Font("Dialog", Font.BOLD, 20));
        signupButton.setPreferredSize(new Dimension(450, 50));
        signupButton.setBackground(orangeColor);
        
        signupButton.addActionListener(e -> {
            // 회원 가입 기능 구현
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String dob = yearCombo.getSelectedItem() + "-" + monthCombo.getSelectedItem() + "-" + dayCombo.getSelectedItem();
            String phoneNumber = phoneNumberField.getText();
            String name = nameField.getText();
            
          //빈칸 방지
            if (username.isEmpty() || password.isEmpty() || dob.isEmpty() || phoneNumber.isEmpty() || name.isEmpty()) {
            	JOptionPane.showMessageDialog(frame, "정보를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 아이디는 8자 이상
            if (username.length()<8) {
            	JOptionPane.showMessageDialog(frame, "8글자 이상 아이디를 설정해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //중복아이디 체크
            try {
                if (isOkId(username, "userdata.csv")) {
                    JOptionPane.showMessageDialog(frame, "중복된 아이디입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (IOException ex) {
                System.err.println("CSV 파일 읽기 오류");
            }

            // 비밀번호와 비밀번호 확인이 일치하는지 확인
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CSVWriter.writeUserData(username, password, dob, phoneNumber, name);

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
                    return true; // 아이디와 비밀번호가 일치하는 경우 true 반환
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // 아이디와 비밀번호가 일치하지 않는 경우 false 반환
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


    public static void main(String[] args) {
        new StartMain("시작 화면");
    }
}