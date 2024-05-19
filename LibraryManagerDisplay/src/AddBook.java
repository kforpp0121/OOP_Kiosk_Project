import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook extends JPanel {
    public AddBook() {
        JButton addBook = new JButton("도서 추가");
        AddBookDialog addBookDialog = new AddBookDialog();
        addBook.addActionListener(e->{
            addBookDialog.setVisible(true);
        });
        add(addBook);
    }
}
