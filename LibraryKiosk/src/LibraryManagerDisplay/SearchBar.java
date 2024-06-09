package LibraryManagerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.stream.Collectors;

public class SearchBar extends JPanel{
    public SearchBar(Vector<Vector<String>> bookList) throws IOException, FontFormatException {

        Color green = new Color(0x00469C76);

        // 폰트 불러오기
        File fontFile = new File("LibraryKiosk/font/NanumGothic.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        // 폰트 설정
        Font btnFont = customFont.deriveFont(Font.BOLD, 18);
        Font formFont = customFont.deriveFont(Font.PLAIN, 16);

        this.setBackground(Color.WHITE);

        // 검색 창 및 버튼 생성
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(35);
        searchField.setFont(formFont);
        searchField.addKeyListener(new MyListener(searchField, bookList));
        JButton searchButton = new JButton("검색");
        searchButton.addActionListener(new MyListener(searchField, bookList));
        searchButton.setPreferredSize(new Dimension(130, 30));
        searchButton.setBackground(green);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(btnFont);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);


        // 검색창을 탭에 추가
        add(searchPanel);
    }
    private class MyListener implements ActionListener, KeyListener{
        Vector<Vector<String>> filteredBooks;
        JTextField text;
        Vector<Vector<String>> books;

        public MyListener(JTextField text, Vector<Vector<String>> books) {
            this.text = text;
            this.books = books;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = text.getText();
            if (!keyword.isEmpty()) { // 검색어가 비어있지 않을 때
                filteredBooks = books.stream()
                        .filter(book -> book != null && book.size() > 1 &&
                                book.get(0) != null && book.get(1) != null &&
                                (book.get(0).toLowerCase().contains(keyword.toLowerCase()) || book.get(1).toLowerCase().contains(keyword.toLowerCase())))
                        .collect(Collectors.toCollection(Vector::new));

                if (filteredBooks.isEmpty()) { // 검색 결과가 없을 때 -> 오류 메시지 출력
                    JOptionPane optionPane = new JOptionPane("찾으시는 책이 목록에 없습니다.", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("검색 결과 오류");
                    dialog.setLocation(950, 300);
                    dialog.setVisible(true);
                } else { // 검색 결과가 있을 때 -> 검색 결과 다이얼로그 띄움
                    SearchResultDialog searchResultDialog = null;
                    try {
                        searchResultDialog = new SearchResultDialog(filteredBooks);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                    searchResultDialog.setVisible(true);
                }
            } else { // 검색어가 비어있을 때 -> 오류 메시지 출력
                JOptionPane optionPane = new JOptionPane("찾으려는 책 제목이나 작가명을 입력해주세요.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("도서 입력 오류");
                dialog.setLocation(950, 300);
                dialog.setVisible(true);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                actionPerformed(null);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
