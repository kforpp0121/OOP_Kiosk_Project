package menu;

import java.io.*;
import java.util.*;

public class BorrowReader {
	
	DateCalculator datecal = new DateCalculator();
	
	public String getDatebyISBN(String isbn) {
		
		String getDate = null;
		for (Map<String, String> bookInfo : AllList()) {
			if(bookInfo.get("BR_ISBN").equals(isbn)) {
				getDate = bookInfo.get("BR_DT");
			}
		}
		
		return getDate;
	}
    
	public List<Map<String, String>> getDataById(String id) {
        List<Map<String, String>> userBooks = new ArrayList<>();
        List<Map<String, String>> alllist = AllList();
        for (Map<String, String> bookInfo : alllist) {
            if (bookInfo.get("BR_ID").equals(id)) {
                userBooks.add(bookInfo);
            }
        }
        return userBooks.isEmpty() ? null : userBooks;
    }
	
    public int countID(String id) {
        int count = 0;
        File csv = new File("br_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(id)) {
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public int countBD(String id) {
        int count = 0;
        File csv = new File("br_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(id)) {
                	
                	long remaindays = datecal.RemainDays(values[2]);
    	        	
    	        	if (0 <= remaindays && remaindays <= 2) {
    	        		count++;
    	        	}
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return count;
    }
    
    public int countOD(String id) {
        int count = 0;
        File csv = new File("br_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(id)) {
                	
                	long remaindays = datecal.RemainDays(values[2]);
    	        	
    	        	if (remaindays < 0) {
    	        		count++;
    	        	}
                }
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
        return count;
    }
    
    private List<Map<String, String>> AllList() {
        List<Map<String, String>> allBooks = new ArrayList<>();
        File csv = new File("br_test.csv");
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