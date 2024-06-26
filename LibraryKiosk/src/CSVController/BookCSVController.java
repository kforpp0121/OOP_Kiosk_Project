package CSVController;

import LibraryManagerDisplay.BookInfo;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class BookCSVController {
    private String filePath;

    public BookCSVController() {
        this.filePath = "LibraryKiosk/csv/library.csv";
    }

    public Vector<Vector<String>> readCSV() {
        Vector<Vector<String>> csvList = new Vector<Vector<String>>();
        BufferedReader br=null;
        String line = "";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            br.readLine(); // 첫 번째 줄 스킵 (column 정보)
            while ((line = br.readLine()) != null) { // 파일 끝까지 읽기
                Vector<String> aLine = new Vector<>(); // 한 줄씩 읽어서 저장할 벡터
                String[] lineArr = csvSplit(line); // csvSplit() 메소드로 한 줄을 쉼표 단위로 나누어 배열에 저장
                aLine = new Vector<String>(Arrays.asList(lineArr)); // 배열을 벡터로 변환
                csvList.add(aLine); // 한 줄씩 벡터에 저장
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }

    public void writeCSV(BookInfo book) {
        File csv = new File(filePath);
        BufferedWriter bw = null; // 출력 스트림 생성
        try {
            // 파일이 없으면 새로 만들고, 있으면 기존 파일에 이어서 작성한다
            bw = new BufferedWriter(new FileWriter(csv, true));

            // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
            String aData = "";
            String title = "\""+book.getTitle()+"\"";
            String author = "\""+book.getAuthor()+"\"";
            String ISBN = "\""+book.getISBN()+"\"";
            String rv = "\""+book.getRv()+"\"";
            String bool = "\""+book.getBool()+"\"";
            String image = "\""+book.getCoverImagePath()+"\"";
            aData = aData.join(",", title, author, ISBN, rv, bool, image);
            // 작성한 데이터를 파일에 넣는다
            bw.write(aData);

            bw.newLine(); // 개행

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush(); // 남아있는 데이터까지 보내 준다
                    bw.close(); // 사용한 BufferedWriter를 닫아 준다
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCSV(Vector<Vector<String>> books, BookInfo selectedBook, BookInfo newBook) {
        for (Vector<String> book : books) { // books 벡터를 순회하며 선택된 책 정보와 일치하는 책 정보를 찾아 수정
            if (book.get(0).equals(selectedBook.getTitle()) &&
                    book.get(1).equals(selectedBook.getAuthor()) &&
                    book.get(2).equals(selectedBook.getISBN())) {
                book.set(0, newBook.getTitle());
                book.set(1, newBook.getAuthor());
                book.set(2, newBook.getISBN());
                book.set(3, newBook.getRv());
                book.set(4, newBook.getBool());
                book.set(5, newBook.getCoverImagePath());
            }
        }

        // 수정된 정보를 파일에 다시 쓰기
        writeUpdatedCSV(books);
    }

    public void deleteBook(Vector<Vector<String>> books, BookInfo selectedBook, DefaultTableModel model, int row) {
        // 선택된 책 정보와 일치하는 책 정보를 books 벡터에서 삭제
        books.removeIf(book -> book.get(0).equals(selectedBook.getTitle()) &&
                book.get(1).equals(selectedBook.getAuthor()) &&
                book.get(2).equals(selectedBook.getISBN()));
        // 수정된 정보를 파일에 다시 쓰기
        writeUpdatedCSV(books);
        // 테이블에서 선택된 행 삭제
        model.removeRow(row);
    }

    private void writeUpdatedCSV(Vector<Vector<String>> books) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, false));
            /*
            *  첫 번째 줄에 column 정보 쓰기
            * 읽어올 때 스킵하기 때문에 써주지 않으면 수정했을 때 도서 데이터가 한줄씩 삭제됨
            */
            bw.write("\"TITLE\",\"AUTHOR\",\"ISBN\",\"RV\",\"BOOL\",\"PICTURE\""); //
            bw.newLine();
            for (Vector<String> book : books) {
                String aData = "";
                String title = "\""+book.get(0)+"\"";
                String author = "\""+book.get(1)+"\"";
                String ISBN = "\""+book.get(2)+"\"";
                String rv = "\""+book.get(3)+"\"";
                String bool = "\""+book.get(4)+"\"";
                String image = "\""+book.get(5)+"\"";
                aData = aData.join(",", title, author, ISBN, rv, bool, image);
                bw.write(aData);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] csvSplit(String str){
        String[] resultStr=null;
        String result="";
        String[] a=str.split(",");
        int cnt=0;		String temp="";
        for(int i=0;i<a.length;i++){
            if(a[i].indexOf("\"")==0){
                if(a[i].lastIndexOf("\"")==a[i].length()-1){
                    result+=a[i].replaceAll("\"","");
                }else{
                    cnt++;
                    temp+=a[i].replaceAll("\"","");
                }
            }else if(a[i].lastIndexOf("\"")==a[i].length()-1){
                if(cnt>0){
                    result+=temp+","+a[i].replaceAll("\"","");
                    cnt=0;
                    temp="";
                }
            }else{
                if(cnt>0){
                    cnt++;
                    temp+=","+a[i].replaceAll("\"","");
                }else{
                    result+=a[i];
                }
            }
            if(i!=a.length-1 && cnt==0)result+="|,|";
        }

        resultStr=result.split("\\|,\\|");
        return resultStr;
    }

}