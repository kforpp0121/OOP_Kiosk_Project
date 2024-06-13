package BorrowReturn;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class BookCSVReader {
    private String filePath;

    public BookCSVReader() {
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
