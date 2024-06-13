package borret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarySystemUI extends JFrame {
    private BookManager bookManager;
    private MemberManager memberManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField isbnField;
    private JTextField userIdField;
    private JTextArea outputArea;

    public LibrarySystemUI(BookManager bookManager, MemberManager memberManager) {
        this.bookManager = bookManager;
        this.memberManager = memberManager;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Library System");
        setLocation(300, 10);
        setSize(450, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        JPanel borrowReturnPanel = createBorrowReturnPanel();
        mainPanel.add(borrowReturnPanel, "BorrowReturn");

        // 추가 UI 패널
        JPanel otherPanel = new JPanel();
        otherPanel.add(new JLabel("Other UI"));
        mainPanel.add(otherPanel, "OtherUI");

        // 초기 화면 설정
        cardLayout.show(mainPanel, "BorrowReturn");

        setVisible(true);
    }

    private JPanel createBorrowReturnPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField();
        JLabel userIdLabel = new JLabel("아이디:");
        userIdField = new JTextField();
        JButton borrowButton = new JButton("대출");
        JButton returnButton = new JButton("반납");

        Font titleFont = new Font("Dialog", Font.BOLD, 25);
        isbnLabel.setFont(titleFont);
        userIdLabel.setFont(titleFont);
        borrowButton.setFont(titleFont);
        returnButton.setFont(titleFont);

        inputPanel.add(isbnLabel);
        inputPanel.add(isbnField);
        inputPanel.add(userIdLabel);
        inputPanel.add(userIdField);
        inputPanel.add(borrowButton);
        inputPanel.add(returnButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("뒤로가기");
        Font backFont = new Font("Dialog", Font.BOLD, 25);
        backButton.setFont(backFont);

        bottomPanel.add(backButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "OtherUI");
            }
        });

        return panel;
    }

    private void borrowBook() {
        String isbn = isbnField.getText();
        String userId = userIdField.getText();

        // 입력값 검증
        if (isbn.isEmpty() || userId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ISBN과 사용자 아이디를 모두 입력해주세요.");
            return;
        }

        // 회원 아이디 확인
        Member member = memberManager.getMemberById(userId);
        if (member == null) {
            JOptionPane.showMessageDialog(null, "해당 회원이 존재하지 않습니다.");
            return;
        }

        // 책 대출 처리
        bookManager.borrowBook(isbn, userId);

        // 대출 메시지 출력
        JOptionPane.showMessageDialog(null, "대출되었습니다.");

        // 필드 초기화
        isbnField.setText("");
        userIdField.setText("");
    }

    private void returnBook() {
        String isbn = isbnField.getText();
        String userId = userIdField.getText();

        // 입력값 검증
        if (isbn.isEmpty() || userId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ISBN과 사용자 아이디를 모두 입력해주세요.");
            return;
        }

        // 회원 아이디 확인
        Member member = memberManager.getMemberById(userId);
        if (member == null) {
            JOptionPane.showMessageDialog(null, "해당 회원이 존재하지 않습니다.");
            return;
        }

        // 책 반납 처리
        bookManager.returnBook(isbn, userId);

        // 반납 메시지 출력
        JOptionPane.showMessageDialog(null, "반납되었습니다.");

        // 필드 초기화
        isbnField.setText("");
        userIdField.setText("");
    }

    public static void main(String[] args) {
        // 파일 경로 설정
        String bookFilePath = "LibraryKiosk/library.csv";
        String memberFilePath = "LibraryKiosk/information.csv";

        // 데이터 로더 생성
        AAA bookLoader = new AAA(bookFilePath);
        BBB memberLoader = new BBB(memberFilePath);

        // 매니저 생성
        BookManager bookManager = new BookManager(bookLoader);
        MemberManager memberManager = new MemberManager(memberLoader);

        // UI 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibrarySystemUI(bookManager, memberManager);
            }
        });
    }
}