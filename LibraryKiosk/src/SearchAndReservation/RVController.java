package SearchAndReservation;

import LibraryManagerDisplay.BookInfo;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class RVController {
    String filePath = "LibraryKiosk/csv/rv_information.csv";
    Vector<Vector<String>> RVdata = new Vector<Vector<String>>();

    public RVController() {
        readRVCSV();
    }

    public void readRVCSV(){
        BufferedReader br=null;
        String line = "";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            br.readLine(); // 첫 번째 줄 스킵 (column 정보)
            while ((line = br.readLine()) != null) { // 파일 끝까지 읽기
                Vector<String> aLine = new Vector<>(); // 한 줄씩 읽어서 저장할 벡터
                String[] lineArr = csvSplit(line); // csvSplit() 메소드로 한 줄을 쉼표 단위로 나누어 배열에 저장
                aLine = new Vector<String>(Arrays.asList(lineArr)); // 배열을 벡터로 변환
                RVdata.add(aLine); // 한 줄씩 벡터에 저장
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
    }

    public void writeCSV(String userID, String ISBN, String date){
        File csv = new File(filePath);
        BufferedWriter bw = null; // 출력 스트림 생성
        try {
            // 파일이 없으면 새로 만들고, 있으면 기존 파일에 이어서 작성한다
            bw = new BufferedWriter(new FileWriter(csv, true));

            // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
            String aData = "";
            aData = aData.join(",", userID, ISBN, date);
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
