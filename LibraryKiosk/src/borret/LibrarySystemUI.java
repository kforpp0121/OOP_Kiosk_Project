package borret;

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
        setLocation(300, 10); // â ��ġ ����
        setSize(450, 800); // â ũ�� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ��� �г�
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField();
        JLabel userIdLabel = new JLabel("���̵�:");
        userIdField = new JTextField();

		//�����ư
        JButton borrowButton = new JButton("����");
		color = Color.decode("#469C76");
		borrowButton.setBackground(color);

		//�ݳ���ư
        JButton returnButton = new JButton("�ݳ�");
		color = Color.decode("#EE7930");
		returnButton.setBackground(color);

        // ��� ��ư ��Ÿ�� ����
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

        // �߾� �г�
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // �ϴ� �г�
		//ImageIcon icon = new ImageIcon("C:\\Java\\TermProject\\term2\\returnbackimage.png");//�ڷΰ��� �̹���
		
        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("�ڷΰ���");
		color = Color.decode("#737373");
		backButton.setBackground(color);

        // �ϴ� ��ư ��Ÿ�� ����
        Font backFont = new Font("Dialog", Font.BOLD, 50);//20->50
        backButton.setFont(backFont);
        backButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // ��ư �̺�Ʈ �ڵ鷯 ���
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
                outputArea.append("�ڷΰ��⸦ �����ϼ̽��ϴ�.\n");
            }
        });
    }

    private void borrowBook() {
        String isbn = isbnField.getText().trim();
        String userId = userIdField.getText().trim();

        if (isbn.isEmpty() || userId.isEmpty()) {
            outputArea.append("ISBN�� User ID�� ��� �Է��ϼ���.\n");
            return;
        }

        bookManager.borrowBook(isbn, userId);
        outputArea.append(userId + "���� " + isbn + " å�� �����߽��ϴ�.\n");
    }

    private void returnBook() {
        String isbn = isbnField.getText().trim();
        String userId = userIdField.getText().trim();

        if (isbn.isEmpty() || userId.isEmpty()) {
            outputArea.append("ISBN�� User ID�� ��� �Է��ϼ���.\n");
            return;
        }

        bookManager.returnBook(isbn, userId);
        outputArea.append(userId + "���� " + isbn + " å�� �ݳ��߽��ϴ�.\n");
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