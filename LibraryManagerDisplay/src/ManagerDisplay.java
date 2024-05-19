import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ManagerDisplay {
    CSVReaderAndWriter csvReader = new CSVReaderAndWriter("./subset_lib.csv");
    public ManagerDisplay() {
        JFrame frame = new JFrame("Manager Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Vector<Vector<String>> books = csvReader.readCSV();

        JPanel searchBarPanel = new SearchBar(books); // search -> lambda stream filter로?
        JPanel bookTablePanel = new BookTable(books);
        JPanel addBookPanel = new AddBook();

        frame.add(searchBarPanel, BorderLayout.NORTH);
        frame.add(bookTablePanel, BorderLayout.CENTER);
        frame.add(addBookPanel, BorderLayout.SOUTH);

        // 프레임을 보이도록 설정
        frame.setVisible(true);
    }
}
