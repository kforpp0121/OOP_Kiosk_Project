package LibraryManagerDisplay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

public class BookTable extends JPanel{
    DefaultTableModel model;
    JTable bookTable;
    Vector<Vector<String>> bookList;
    public BookTable(Vector<Vector<String>> bookList) {
        Color green = new Color(0x00469C76);
        Font btnFont = new Font("맑은 고딕", Font.BOLD, 18);
        Font tableFont = new Font("맑은 고딕", Font.PLAIN, 16);
        Font tableHeaderFont = new Font("맑은 고딕", Font.BOLD, 18);

        this.setBackground(Color.WHITE);

        this.bookList = bookList; // bookList를 받아옴
        JPanel tablePanel = new JPanel(new BorderLayout());

        Vector<String> columnName =  new Vector<>(Arrays.asList("제목", "작가", "ISBN")); // 테이블의 열 이름

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

        bookTable.getColumn("제목").setPreferredWidth(250); // 열 너비 설정
        bookTable.getColumn("작가").setPreferredWidth(250);
        bookTable.getColumn("ISBN").setPreferredWidth(150);
        bookTable.setRowHeight(30); // 행 높이 설정

        JScrollPane sp = new JScrollPane(bookTable); // 스크롤바 추가
        sp.setPreferredSize(new Dimension(650,550));

        tablePanel.add(sp, BorderLayout.CENTER);

        JPanel addBookPanel = new JPanel();
        addBookPanel.setBackground(Color.WHITE);
        JButton addBook = new JButton("도서 추가");
        addBook.setPreferredSize(new Dimension(140, 30));
        addBook.setBackground(green);
        addBook.setForeground(Color.WHITE);
        addBook.setFont(btnFont);
        addBook.addActionListener(e->{
            new AddBookDialog(bookList, model).setVisible(true);
        });
        addBookPanel.add(addBook);

        tablePanel.add(addBookPanel, BorderLayout.SOUTH);

        add(tablePanel);
    }

    public class DoubleClickedListener implements MouseListener { // 더블클릭 이벤트 리스너
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==bookTable) { // 테이블에서 더블클릭 시
                if (e.getClickCount() == 2) {
                    int row = bookTable.getSelectedRow(); // 선택된 행의 인덱스를 가져옴

                    if (row == -1) return; // 선택된 행이 없으면 리턴
                    // 선택된 행의 제목, 작가, ISBN을 가져옴
                    String title = (String) model.getValueAt(row, 0);
                    String author = (String) model.getValueAt(row, 1);
                    String ISBN = (String) model.getValueAt(row, 2);

                    BookInfo selectedBook = new BookInfo(title, author, ISBN); // 선택된 책 정보를 BookInfo 객체로 생성

                    SelectTaskDialog selectTaskDialog = new SelectTaskDialog(bookList, selectedBook, model, row); // 선택된 책 정보를 가지고 SelectTaskDialog 생성
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
}
