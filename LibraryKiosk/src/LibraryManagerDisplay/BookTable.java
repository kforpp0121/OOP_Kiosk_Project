package LibraryManagerDisplay;

import BorrowReturn.BR_InformationCSVController;
import CSVController.BookCSVController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class BookTable extends JPanel{
    DefaultTableModel model;
    JTable bookTable;
    Vector<Vector<String>> bookList;
    public BookTable(Vector<Vector<String>> bookList) throws IOException, FontFormatException {
        Color green = new Color(0x00469C76);

        // 폰트 불러오기
        File fontFile = new File("LibraryKiosk/font/NanumGothic.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        // 폰트 설정
        Font btnFont = customFont.deriveFont(Font.BOLD, 18);
        Font tableFont = customFont.deriveFont(Font.PLAIN, 16);
        Font tableHeaderFont = customFont.deriveFont(Font.BOLD, 18);

        this.setBackground(Color.WHITE);

        this.bookList = bookList; // bookList를 받아옴
        JPanel tablePanel = new JPanel(new BorderLayout());

        Vector<String> columnName =  new Vector<>(Arrays.asList("제목", "작가", "ISBN","예약 가능 여부", "예약 현황","표지")); // 테이블의 열 이름

        model = new DefaultTableModel(columnName, 0) { // 테이블 내용 수정 불가하도록 설정
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        bookTable = new JTable(model); // 테이블 생성
        bookTable.setFont(tableFont);

        for(Vector<String> book : bookList){ // bookList의 내용을 테이블에 추가
            model.addRow(book);
        }

        bookTable.addMouseListener(new DoubleClickedListener()); // 더블클릭 이벤트 추가

        // JTable의 헤더 가져오기
        JTableHeader header = bookTable.getTableHeader();

        // 헤더의 높이 설정
        header.setPreferredSize(new Dimension(header.getWidth(), 30)); // 높이를 40으로 설정

        // 헤더의 폰트 크기 변경
        header.setFont(tableHeaderFont);

        bookTable.getColumn("제목").setPreferredWidth(150); // 열 너비 설정
        bookTable.getColumn("작가").setPreferredWidth(50);
        bookTable.getColumn("ISBN").setPreferredWidth(100);
        bookTable.getColumn("예약 가능 여부").setPreferredWidth(100);
        bookTable.getColumn("예약 현황").setPreferredWidth(50);
        bookTable.getColumn("표지").setPreferredWidth(50);

        bookTable.setRowHeight(30); // 행 높이 설정

        JScrollPane sp = new JScrollPane(bookTable); // 스크롤바 추가
        sp.setPreferredSize(new Dimension(700,550));

        tablePanel.add(sp, BorderLayout.CENTER);

        JPanel refreshPanel = new JPanel();
        JButton refreshButton = new JButton("새로고침");
        refreshButton.setBackground(new Color(0x00EE7930));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(btnFont);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookCSVController bookCSVController = new BookCSVController();
                Vector<Vector<String>> newBookList = bookCSVController.readCSV();
                updateBookList(newBookList);
            }
        });

        refreshPanel.setBackground(Color.WHITE);

        JPanel addBookPanel = new JPanel();
        addBookPanel.setBackground(Color.WHITE);
        JButton addBook = new JButton("도서 추가");
        addBook.setPreferredSize(new Dimension(140, 30));
        addBook.setBackground(green);
        addBook.setForeground(Color.WHITE);
        addBook.setFont(btnFont);
        addBook.addActionListener(e->{
            try {
                new AddBookDialog(bookList, model).setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });
        addBookPanel.add(addBook);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(refreshButton);
        buttonPanel.add(addBookPanel);
        buttonPanel.setBackground(Color.WHITE);

        /*tablePanel.add(addBookPanel, BorderLayout.SOUTH);*/
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        add(tablePanel);
    }

    public class DoubleClickedListener implements MouseListener { // 더블클릭 이벤트 리스너
        @Override
        public void mouseClicked(MouseEvent e) {
            Vector<Vector<String>> brInformation = new BR_InformationCSVController().readCSV();
            if(e.getSource()==bookTable) { // 테이블에서 더블클릭 시
                if (e.getClickCount() == 2) {
                    int row = bookTable.getSelectedRow(); // 선택된 행의 인덱스를 가져옴

                    if (row == -1) return; // 선택된 행이 없으면 리턴
                    // 선택된 행의 제목, 작가, ISBN을 가져옴
                    String title = (String) model.getValueAt(row, 0);
                    String author = (String) model.getValueAt(row, 1);
                    String ISBN = (String) model.getValueAt(row, 2);
                    String rv = (String) model.getValueAt(row, 3);
                    String bool = (String) model.getValueAt(row, 4);
                    String image = (String) model.getValueAt(row, 5);

                    boolean isDuplicate = brInformation.stream()
                            .anyMatch(data -> data.get(0).equals(ISBN)); // brInformation에서 동일한 ISBN을 가진 항목이 있는지 확인

                    if (isDuplicate) {
                        JOptionPane optionPane = new JOptionPane("현재 대출 중인 도서의 정보는\n변경할 수 없습니다.", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("오류");
                        dialog.setLocation(950, 300);
                        dialog.setVisible(true);
                        return;
                    }

                    BookInfo selectedBook = new BookInfo(title, author, ISBN, rv, bool,image); // 선택된 책 정보를 BookInfo 객체로 생성

                    SelectTaskDialog selectTaskDialog = null; // 선택된 책 정보를 가지고 SelectTaskDialog 생성
                    try {
                        selectTaskDialog = new SelectTaskDialog(bookList, selectedBook, model, row);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                    selectTaskDialog.setVisible(true);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public void updateBookList(Vector<Vector<String>> newBookList) {
        this.bookList = newBookList;
        model.setRowCount(0);
        for(Vector<String> book : bookList){
            model.addRow(book);
        }
    }
}
