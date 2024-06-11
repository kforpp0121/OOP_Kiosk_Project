package menu;

import StartLogin.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BorrowState extends JPanel {
	
	private void createUI() {
        setLayout(new BorderLayout());
        }
	
	public BorrowState (JFrame frame, UserInfo userinfo) {
		
		setSize(450, 700);
		createUI();
		
		Font backFont = new Font("Dialog", Font.BOLD, 25);
		
		JLabel borrowstateL = new JLabel("대출 현황");
		borrowstateL.setOpaque(true);
		borrowstateL.setBorder(new EmptyBorder(15, 20, 15, 0));
		borrowstateL.setBackground(Color.decode("#D9D9D9"));
		borrowstateL.setFont(backFont);
		
		JPanel basicP = new JPanel();
		basicP.setPreferredSize(new Dimension(540, 710));
		basicP.setBackground(Color.WHITE);
		
		JPanel detailP = new JPanel();
		detailP.setPreferredSize(new Dimension(540, 710));
		detailP.setBackground(Color.WHITE);
		
		JLabel titleB = new JLabel();
		JLabel brdateL = new JLabel();
        JLabel rtdateL = new JLabel();
        JLabel oddateL = new JLabel();
        JLabel overdueL = new JLabel();
        
        Color col = new Color(0xD9D9D9);
        brdateL.setBackground(col);
        rtdateL.setBackground(col);
        oddateL.setBackground(col);
        overdueL.setBackground(col);
        
    	brdateL.setOpaque(true);
    	rtdateL.setOpaque(true);
    	oddateL.setOpaque(true);
    	overdueL.setOpaque(true);
    	
    	brdateL.setFont(backFont);
    	rtdateL.setFont(backFont);
    	oddateL.setFont(backFont);
    	overdueL.setFont(backFont);
    	
    	EmptyBorder size = new EmptyBorder(25, 200, 25, 200);
    	brdateL.setBorder(size);
    	rtdateL.setBorder(size);
    	oddateL.setBorder(size);
    	overdueL.setBorder(size);
    	
		JButton backward1B = new JButton("뒤로 가기");
		backward1B.setBorder(new EmptyBorder(20, 150, 20, 150));
		backward1B.setBackground(Color.decode("#EE7930"));
		backward1B.setForeground(Color.WHITE);
		backward1B.setFont(backFont);
		
		backward1B.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		remove(borrowstateL);
        		remove(basicP);
        		remove(backward1B);
        		MenuFirst menu = new MenuFirst(frame, userinfo);
        		add(menu);
        		revalidate();
        		repaint();
        	}
        });
		
		
		JButton backward2B = new JButton("뒤로 가기");
		backward2B.setBorder(new EmptyBorder(20, 150, 20, 150));
		backward2B.setBackground(Color.decode("#EE7930"));
		backward2B.setForeground(Color.WHITE);
		backward2B.setFont(backFont);
		
        backward2B.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		remove(detailP);
    			remove(backward2B);
    			add(basicP, BorderLayout.CENTER);
    			add(backward1B, BorderLayout.SOUTH);
    			revalidate();
        		repaint();
        	}
        });
        
        JLabel titleL = new JLabel("대출 도서 목록");
        titleL.setBackground(Color.decode("#737373"));
        titleL.setOpaque(true);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setVerticalAlignment(SwingConstants.CENTER);
        titleL.setFont(backFont);
        titleL.setForeground(Color.WHITE);
        titleL.setPreferredSize(new Dimension(500, 100));
        basicP.add(titleL);
        
        BorrowReader brReader = new BorrowReader();
        BookDTReader dtReader = new BookDTReader();
        String idToLookup = "noonsong";
        List<Map<String, String>> getdata = brReader.getDataById(idToLookup);
        
        if (getdata != null) {
        	int a = getdata.size();
        	List<String> isbns = new ArrayList<>();
        	
        	for(Map<String, String> bookInfo : getdata) {
        		isbns.add(bookInfo.get("BR_ISBN"));
        	}
        	
        	JButton[] buttons = new JButton[a];
        	for(int i = 0 ; i < a ; i++) {
        		String isbn = isbns.get(i);
        		buttons[i] = new JButton(dtReader.getTitleByISBN(isbn));
        		buttons[i].setBackground(Color.decode("#469C76"));
        		buttons[i].setForeground(Color.WHITE);
        		buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
        		buttons[i].setVerticalAlignment(SwingConstants.CENTER);
        		buttons[i].setFont(backFont);
        		buttons[i].setPreferredSize(new Dimension(540, 100));
        		basicP.add(buttons[i]);
        		
        		buttons[i].addActionListener(e -> {
        			remove(basicP);
        			remove(backward1B);
        			add(detailP, BorderLayout.CENTER);
        			add(backward2B, BorderLayout.SOUTH);
        			
        			titleB.setText(dtReader.getTitleByISBN(isbn));
        			titleB.setBackground(Color.decode("#469C76"));
        	        titleB.setOpaque(true);
        	        titleB.setHorizontalAlignment(SwingConstants.CENTER);
        	        titleB.setVerticalAlignment(SwingConstants.CENTER);
        	        titleB.setFont(backFont);
        	        titleB.setForeground(Color.WHITE);
        	        titleB.setPreferredSize(new Dimension(500, 100));
        	        detailP.add(titleB);

        	        String brdate = null;
        	        String rtdate = null;
        	        String oddate = null;
        	        String overdue = null;
        	      
        	        brdate = brReader.getDatebyISBN(isbn);
        	        	
        	        DateCalculator datecal = new DateCalculator();
        	        	
        	        rtdate = datecal.OverdueDate(brdate).toString();
        	        	
        	        long remaindays = datecal.RemainDays(brdate);
        	        	
        	        if(remaindays < 0) {
        	        		
        	        	String num = String.valueOf(Math.abs(remaindays));
        	        	overdueL.setForeground(Color.decode("#EE7930"));
        	        	oddate = "반납일로부터 " + num + "일 지났습니다.";
        	        	overdue = "연체입니다.";
        	        		
        	        } else {
        	        		
        	        	if(remaindays <= 2) {
        	        		overdueL.setForeground(Color.decode("#EE7930"));
        	        	}
        	        	else {
        	        		overdueL.setForeground(null);
        	        	}
        	        	oddate = "반납 예정일까지 " + remaindays + "일";
        	        	overdue = "기한 내에 반납해주세요.";
        	        		
        	        }
        	        
                	brdateL.setText("대출일 " + brdate);
                	rtdateL.setText("반납 예정일 " + rtdate);
                	oddateL.setText(oddate);
                	overdueL.setText(overdue);
                	
                	detailP.add(brdateL);
                	detailP.add(rtdateL);
                	detailP.add(oddateL);
                	detailP.add(overdueL);
                	
                	revalidate();
            		repaint();
        			
        		});
        	}
        	
        } else {
        	JLabel label = new JLabel("빌린 도서가 없습니다.");
        	label.setBackground(Color.decode("#D9D9D9"));
        	label.setOpaque(true);
        	label.setHorizontalAlignment(SwingConstants.CENTER);
        	label.setVerticalAlignment(SwingConstants.CENTER);
        	label.setFont(backFont);
        	label.setPreferredSize(new Dimension(450, 450));
        	basicP.add(label);
        	
        }
        
		setLayout(new BorderLayout());
		add(borrowstateL, BorderLayout.NORTH);
		add(basicP, BorderLayout.CENTER);
		add(backward1B, BorderLayout.SOUTH);
	}
	
}
