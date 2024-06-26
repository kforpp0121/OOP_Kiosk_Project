package BorrowReturn;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

public class BR_InformationCSVController {
    Vector<Vector<String>> informationData;
    private String filePath;
    public BR_InformationCSVController() {
        this.filePath = "LibraryKiosk/csv/br_information.csv";
        informationData = readCSV();
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

    public void writeCSV(String selectedBookISBN, String userID, LocalDate now) {
        File csv = new File(filePath);
        BufferedWriter bw = null; // 출력 스트림 생성
        try {
            // 파일이 없으면 새로 만들고, 있으면 기존 파일에 이어서 작성한다
            bw = new BufferedWriter(new FileWriter(csv, true));

            // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
            String aData = "";
            aData = aData.join(",", selectedBookISBN, userID, now.toString());
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


    public void deleteCSV(String selectedBookISBN, String userID) {
        // 선택된 책 정보와 일치하는 책 정보를 books 벡터에서 삭제
        informationData.removeIf(data -> data.get(0).equals(selectedBookISBN) &&
                data.get(1).equals(userID));
        // 수정된 정보를 파일에 다시 쓰기
        writeUpdatedCSV(informationData);
    }

    private void writeUpdatedCSV(Vector<Vector<String>> br_data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, false));
            /*
             *  첫 번째 줄에 column 정보 쓰기
             * 읽어올 때 스킵하기 때문에 써주지 않으면 수정했을 때 도서 데이터가 한줄씩 삭제됨
             */
            bw.write("BR_ISBN,BR_ID,BR_DT");
            bw.newLine();
            for (Vector<String> data : br_data) {
                String aData = "";
                aData = aData.join(",", data.get(0), data.get(1), data.get(2));
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
