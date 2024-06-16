package LibraryManagerDisplay;

import BorrowReturn.BR_InformationCSVController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class SearchResultDialog extends JDialog {
    JFrame frame;
    JTable bookTable;
    DefaultTableModel model;
    public SearchResultDialog(Vector<Vector<String>> bookList,JFrame frame) throws IOException, FontFormatException {
        this.frame = frame;
        setTitle("검색 결과");

        // 폰트 불러오기
        File fontFile = new File("LibraryKiosk/font/NanumGothic.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        // 폰트 설정
        Font labelFont = customFont.deriveFont(Font.BOLD, 30);
        Font tableFont = customFont.deriveFont(Font.PLAIN, 16);
        Font tableHeaderFont = customFont.deriveFont(Font.BOLD, 18);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel textPanel=new JPanel();
        JLabel textLabel = new JLabel("검색 결과");
        textLabel.setFont(labelFont);
        textPanel.add(textLabel);
        textPanel.setBackground(Color.WHITE);

        //JPanel bookTablePanel = new BookTable(bookList, frame);

        JPanel tablePanel = new JPanel(new BorderLayout());

        Vector<String> columnName =  new Vector<>(Arrays.asList("제목", "작가", "ISBN","대출 현항", "예약 현황","표지")); // 테이블의 열 이름

        model = new DefaultTableModel(columnName, 0) { // 테이블 내용 수정 불가하도록 설정
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        bookTable = new JTable(model); // 테이블 생성
        bookTable.setFont(tableFont);

        for(Vector<String> book : bookList){ // bookList의 내용을 테이블에 추가
            Vector<String> bookInfo =  new Vector<String>();
            bookInfo.add(book.get(0));
            bookInfo.add(book.get(1));
            bookInfo.add(book.get(2));
            long brCount;
            Vector<Vector<String>> brList = new BR_InformationCSVController().readCSV();
            brCount = brList.stream()
                    .filter(data -> data.get(0).equals(book.get(2)))
                    .count();
            bookInfo.add(Long.toString(brCount) + "권 대출 중");
            bookInfo.add(book.get(4));
            bookInfo.add(book.get(5));
            model.addRow(bookInfo);
        }

        // 열 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        bookTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        bookTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        bookTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        bookTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        // JTable의 헤더 가져오기
        JTableHeader header = bookTable.getTableHeader();

        // 헤더의 높이 설정
        header.setPreferredSize(new Dimension(header.getWidth(), 30)); // 높이를 40으로 설정

        // 헤더의 폰트 크기 변경
        header.setFont(tableHeaderFont);

        bookTable.getColumn("제목").setPreferredWidth(150); // 열 너비 설정
        bookTable.getColumn("작가").setPreferredWidth(50);
        bookTable.getColumn("ISBN").setPreferredWidth(100);
        bookTable.getColumn("대출 현항").setPreferredWidth(60);
        bookTable.getColumn("예약 현황").setPreferredWidth(60);
        bookTable.getColumn("표지").setPreferredWidth(80);

        bookTable.setRowHeight(30); // 행 높이 설정

        JScrollPane sp = new JScrollPane(bookTable); // 스크롤바 추가
        sp.setPreferredSize(new Dimension(700,550));

        tablePanel.add(sp, BorderLayout.CENTER);

        panel.add(textPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        //panel.add(bookTablePanel, BorderLayout.CENTER);

        add(panel);

        setSize(800,700);
        setLocationRelativeTo(frame);
    }
}
