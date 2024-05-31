import java.io.*;
import java.util.*;

public class BookDTReader {

    public String getTitleByISBN(String isbn) {
        String title = null;
        List<Map<String, String>> allBooks = getUserBooks();
        
        for (Map<String, String> bookInfo : allBooks) {
            if (bookInfo.get("ISBN").equals(isbn)) {
                title = bookInfo.get("TITLE");
                break;
            }
        }
        
        return title;
    }
    
    private List<Map<String, String>> getUserBooks() {
        List<Map<String, String>> allBooks = new ArrayList<>();
        File csv = new File("lib_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String[] headers = br.readLine().split(","); // 첫 번째 줄은 열 이름

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> bookInfo = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    bookInfo.put(headers[i], values[i]);
                }
                allBooks.add(bookInfo);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allBooks;
    }
}
