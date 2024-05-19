import java.io.*;
import java.util.*;

public class CSVReaderAndWriter {
    private String filePath;

    public CSVReaderAndWriter(String filePath) {
        this.filePath = filePath;
    }

    public Vector<Vector<String>> readCSV() {
        Vector<Vector<String>> csvList = new Vector<Vector<String>>();
        BufferedReader br=null;
        String line = "";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            while ((line = br.readLine()) != null) {
                Vector<String> aLine = new Vector<>();
                String[] lineArr = csvSplit(line);
                aLine = new Vector<String>(Arrays.asList(lineArr));
                csvList.add(aLine);
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

    public void writeCSV(String title, String author, String ISBN) {
        File csv = new File(filePath);
        BufferedWriter bw = null; // 출력 스트림 생성
        try {
            bw = new BufferedWriter(new FileWriter(csv, true));
            // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다

            String aData = "";
            aData = title + "," + author + "," + ISBN;
            // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
            bw.write(aData);
            // 작성한 데이터를 파일에 넣는다
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
        //	System.out.println(result);
        resultStr=result.split("\\|,\\|");
        return resultStr;
    }

}