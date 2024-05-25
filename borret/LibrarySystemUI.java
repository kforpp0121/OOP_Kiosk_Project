import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class LibrarySystemUI extends JFrame {
    private BookManager bookManager;
    private MemberManager memberManager;
    
    private JTextField isbnField;
    private JTextField userIdField;
    private JTextArea outputArea;

    public LibrarySystemUI(BookManager bookManager, MemberManager memberManager) {
        this.bookManager = bookManager;
        this.memberManager = memberManager;
        initializeUI();
    }

	private Color color;
    private void initializeUI() {
        setTitle("Library System");
        setLocation(300, 10); // 창 위치 설정
        setSize(450, 800); // 창 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 상단 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField();
        JLabel userIdLabel = new JLabel("아이디:");
        userIdField = new JTextField();

		//대출버튼
        JButton borrowButton = new JButton("대출");
		color = Color.decode("#469C76");
		borrowButton.setBackground(color);

		//반납버튼
        JButton returnButton = new JButton("반납");
		color = Color.decode("#EE7930");
		returnButton.setBackground(color);

        // 상단 버튼 스타일 설정
        Font titleFont = new Font("Dialog", Font.BOLD, 50);//25->50
        isbnLabel.setFont(titleFont);
        userIdLabel.setFont(titleFont);
        borrowButton.setFont(titleFont);
        returnButton.setFont(titleFont);
        borrowButton.setBorder(new EmptyBorder(15, 0, 15, 0));
        returnButton.setBorder(new EmptyBorder(15, 0, 15, 0));

        inputPanel.add(isbnLabel);
        inputPanel.add(isbnField);
        inputPanel.add(userIdLabel);
        inputPanel.add(userIdField);
        inputPanel.add(borrowButton);
        inputPanel.add(returnButton);

        add(inputPanel, BorderLayout.NORTH);

        // 중앙 패널
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // 하단 패널
		//ImageIcon icon = new ImageIcon("C:\\Java\\TermProject\\term2\\returnbackimage.png");//뒤로가기 이미지
		
        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("뒤로가기");
		color = Color.decode("#737373");
		backButton.setBackground(color);

        // 하단 버튼 스타일 설정
        Font backFont = new Font("Dialog", Font.BOLD, 50);//20->50
        backButton.setFont(backFont);
        backButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // 버튼 이벤트 핸들러 등록
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
                outputArea.append("뒤로가기를 선택하셨습니다.\n");
            }
        });
    }

    private void borrowBook() {
        String isbn = isbnField.getText().trim();
        String userId = userIdField.getText().trim();

        if (isbn.isEmpty() || userId.isEmpty()) {
            outputArea.append("ISBN과 User ID를 모두 입력하세요.\n");
            return;
        }

        bookManager.borrowBook(isbn, userId);
        outputArea.append(userId + "님이 " + isbn + " 책을 대출했습니다.\n");
    }

    private void returnBook() {
        String isbn = isbnField.getText().trim();
        String userId = userIdField.getText().trim();

        if (isbn.isEmpty() || userId.isEmpty()) {
            outputArea.append("ISBN과 User ID를 모두 입력하세요.\n");
            return;
        }

        bookManager.returnBook(isbn, userId);
        outputArea.append(userId + "님이 " + isbn + " 책을 반납했습니다.\n");
    }

    public static void main(String[] args) {
        BookLoader bookLoader = new BookLoader();
        MemberLoader memberLoader = new MemberLoader();
        
        BookManager bookManager = new BookManager(bookLoader);
        MemberManager memberManager = new MemberManager(memberLoader);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LibrarySystemUI ui = new LibrarySystemUI(bookManager, memberManager);
                ui.setVisible(true);
            }
        });
    }
}