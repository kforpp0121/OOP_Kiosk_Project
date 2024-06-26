package menu;

import BorrowReturn.Borrow;
import BorrowReturn.Return;
import SearchAndReservation.SearchOnly;
import SearchAndReservation.Search;
import StartLogin.Start;
import StartLogin.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuFirst extends JPanel {
	private JPanel menupage1P;
	private JPanel menupage2P;
	private JPanel southpanel1;
	private JPanel southpanel2;
	private String csvFilePath = "LibraryKiosk/csv/library.csv";
	private Font font;
	private JFrame frame;

	
	public MenuFirst(JFrame frame, UserInfo userinfo) {

		this.frame = frame;
		setUIFont();
		
		setSize(450, 800);
		setLayout(new BorderLayout());   // 기본 panel 설정

		Font backFont = font.deriveFont(Font.BOLD, 25);
		
		JLabel menustateL = new JLabel("메뉴");
		menustateL.setOpaque(true);
		menustateL.setBorder(new EmptyBorder(15, 20, 15, 0));
		menustateL.setBackground(Color.decode("#D9D9D9"));
		menustateL.setFont(backFont);
		
		menupage1P = new JPanel();
		menupage1P.setBorder(new EmptyBorder(15, 0, 15, 0));
		menupage1P.setBackground(Color.WHITE);
		
		menupage2P = new JPanel();
		menupage2P.setBorder(new EmptyBorder(15, 0, 15, 0));
		menupage2P.setBackground(Color.WHITE);
		
		southpanel1 = new JPanel();
		southpanel1.setBorder(new EmptyBorder(20, 0, 70, 0));
		southpanel1.setBackground(Color.WHITE);
		
		southpanel2 = new JPanel();
		southpanel2.setBorder(new EmptyBorder(20, 0, 70, 0));
		southpanel2.setBackground(Color.WHITE);
		
		JButton backward1B = new JButton("시작 화면으로");
		backward1B.setBorder(new EmptyBorder(20, 150, 20, 150));
		backward1B.setBackground(Color.decode("#EE7930"));
		backward1B.setForeground(Color.WHITE);
		backward1B.setFont(backFont);
		backward1B.addActionListener(new BacktoStart());
		
		JButton backward2B = new JButton("시작 화면으로");
		backward2B.setBorder(new EmptyBorder(20, 150, 20, 150));
		backward2B.setBackground(Color.decode("#EE7930"));
		backward2B.setForeground(Color.WHITE);
		backward2B.setFont(backFont);
		backward2B.addActionListener(new BacktoStart());
		
		//메뉴 button 설정
		JButton button1 = new JButton("대출");
		JButton button2 = new JButton("반납");
		JButton button3 = new JButton("검색");
		JButton button4 = new JButton("예약");
		JButton button5 = new JButton("<html><body><center>대출<br>현황</center></body></html>");
		JButton button6 = new JButton("<html><body><center>내<br>정보</center></body></html>");
		
		//메뉴 button 색상 설정
		Color buttonC = new Color(0x469C76);
		button1.setBackground(buttonC);
		button2.setBackground(buttonC);
		button3.setBackground(buttonC);
		button4.setBackground(buttonC);
		button5.setBackground(buttonC);
		button6.setBackground(buttonC);
		
		//메뉴 button 글자 크기 
		Font buttonFont = font.deriveFont(Font.BOLD, 30);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);
        button4.setFont(buttonFont);
        button5.setFont(buttonFont);
        button6.setFont(buttonFont);
        
        //메뉴 button 글자 색상 설정
        button1.setForeground(Color.WHITE);
        button2.setForeground(Color.WHITE);
        button3.setForeground(Color.WHITE);
        button4.setForeground(Color.WHITE);
        button5.setForeground(Color.WHITE);
        button6.setForeground(Color.WHITE);
        
        //메뉴 button 글자 가운데정렬
        button1.setHorizontalAlignment(SwingConstants.CENTER);
        button1.setVerticalAlignment(SwingConstants.CENTER);
        button2.setHorizontalAlignment(SwingConstants.CENTER);
        button2.setVerticalAlignment(SwingConstants.CENTER);
        button3.setHorizontalAlignment(SwingConstants.CENTER);
        button3.setVerticalAlignment(SwingConstants.CENTER);
        button4.setHorizontalAlignment(SwingConstants.CENTER);
        button4.setVerticalAlignment(SwingConstants.CENTER);
        button5.setHorizontalAlignment(SwingConstants.CENTER);
        button5.setVerticalAlignment(SwingConstants.CENTER);
        button6.setHorizontalAlignment(SwingConstants.CENTER);
        button6.setVerticalAlignment(SwingConstants.CENTER);
        
        Dimension buttonSize = new Dimension(120, 550);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        button6.setPreferredSize(buttonSize);
        
        menupage1P.add(button1);
        menupage1P.add(button2);
        menupage1P.add(button3);
        
        menupage2P.add(button4);
        menupage2P.add(button5);
        menupage2P.add(button6);

		// 대출
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// UI 작업을 최소화하여 스윙 이벤트 디스패치 스레드를 차단하지 않도록 함
				SwingUtilities.invokeLater(() -> {
					frame.getContentPane().removeAll();
					Borrow borrow = new Borrow(frame, userinfo);
					frame.add(borrow);
					frame.revalidate(); // 변경 사항을 적용하기 위해 프레임을 다시 그림
				});
			}
		});

		// 반납
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					frame.getContentPane().removeAll();
					Return returnBook = new Return(frame, userinfo);
					returnBook.setVisible(true);
					frame.add(returnBook);
					frame.revalidate();
				});
			}
		});

		// 검색
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					frame.getContentPane().removeAll();
					SearchOnly searchOnly = new SearchOnly(csvFilePath, frame);
					searchOnly.setVisible(true);
					frame.add(searchOnly);
					frame.revalidate();
				});
			}
		});

		// 예약
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					frame.getContentPane().removeAll();
					Search search = new Search(csvFilePath, frame, userinfo);
					search.setVisible(true);
					frame.add(search);
					frame.revalidate();
				});
			}
		});

		// 대출 현황
        button5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					frame.getContentPane().removeAll();
					BorrowState br = new BorrowState(frame, userinfo);
					br.setVisible(true);
					frame.add(br);
					frame.revalidate();
				});
        	}
        });

		// 내 정보
        button6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					frame.getContentPane().removeAll();
					Profile pf = new Profile(frame, userinfo);
					pf.setVisible(true);
					frame.add(pf);
					frame.revalidate();
				});
        	}
        });
        
        //이동 button 설정
        JButton nextpage = new JButton("다른 메뉴를 찾으시나요?");
        JButton previouspage = new JButton("다른 메뉴를 찾으시나요?");
        
        nextpage.setBackground(Color.WHITE);
        previouspage.setBackground(Color.WHITE);
        
        nextpage.setFont(backFont);
        previouspage.setFont(backFont);
        
        nextpage.setForeground(Color.decode("#469C76"));
        previouspage.setForeground(Color.decode("#469C76"));
        
        nextpage.setBorder(new EmptyBorder(20, 0, 20, 0));
        previouspage.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        southpanel1.add(nextpage, BorderLayout.NORTH);
        southpanel1.add(backward1B, BorderLayout.SOUTH);
        
        southpanel2.add(previouspage, BorderLayout.NORTH);
        southpanel2.add(backward2B, BorderLayout.SOUTH);
        
        nextpage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		remove(menupage1P);
        		remove(southpanel1);
        		add(menupage2P, BorderLayout.CENTER);
        		add(southpanel2, BorderLayout.SOUTH);
        		revalidate();
        		repaint();
        	}
        });
        
        previouspage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		remove(menupage2P);
        		remove(southpanel2);
        		add(menupage1P, BorderLayout.CENTER);
        		add(southpanel1, BorderLayout.SOUTH);
        		revalidate();
        		repaint();
        	}
        });
        
        add(menustateL, BorderLayout.NORTH);
        add(menupage1P, BorderLayout.CENTER);
        add(southpanel1, BorderLayout.SOUTH);

	}

	private void setUIFont() {
		// 나눔 고딕 폰트 파일 경로
		String fontPath = "LibraryKiosk/font/NanumGothic.ttf";

		// 폰트 파일로부터 폰트 객체 생성
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 12);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	private class BacktoStart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(() -> {
				frame.getContentPane().removeAll();
				Start start = new Start(frame);
				start.setVisible(true);
				frame.add(start);
				frame.revalidate();
			});
		}
	}

}
