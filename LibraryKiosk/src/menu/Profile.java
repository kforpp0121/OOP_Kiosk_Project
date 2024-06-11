package menu;

import StartLogin.UserInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Profile extends JPanel {
	
	private void createUI() {
        setLayout(new BorderLayout());   // 기본 panel 설정
        }
	
	public Profile(JFrame frame, UserInfo userinfo) {
		
		setSize(450, 700);
		createUI();
		
		Font backFont = new Font("Dialog", Font.BOLD, 25);
		
		JLabel profilestateL = new JLabel("내 정보");
		profilestateL.setOpaque(true);
		profilestateL.setBorder(new EmptyBorder(15, 20, 15, 0));
		profilestateL.setBackground(Color.decode("#D9D9D9"));
		profilestateL.setFont(backFont);
		
		JPanel basicP = new JPanel();
		basicP.setPreferredSize(new Dimension(450, 700));
		basicP.setBackground(Color.WHITE);
		
		JButton backwardB = new JButton("뒤로 가기");
		backwardB.setBorder(new EmptyBorder(20, 150, 20, 150));
		backwardB.setBackground(Color.decode("#EE7930"));
		backwardB.setForeground(Color.WHITE);
		backwardB.setFont(backFont);
		
        backwardB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		MenuFirst menu = new MenuFirst(frame, userinfo);
        		menu.setVisible(true);
                frame.add(menu);
        	}
        });
        
        JLabel profL = new JLabel();
        profL.setBackground(Color.decode("#469C76"));
        profL.setOpaque(true);
        profL.setHorizontalAlignment(SwingConstants.CENTER);
        profL.setVerticalAlignment(SwingConstants.CENTER);
        profL.setFont(backFont);
        profL.setForeground(Color.WHITE);
        profL.setPreferredSize(new Dimension(450, 150));
        basicP.add(profL);
        
        JLabel borrow = new JLabel();
        JLabel reserve = new JLabel();
        JLabel date = new JLabel();
        JLabel overdue = new JLabel();
        
        borrow.setOpaque(true);
        reserve.setOpaque(true);
        date.setOpaque(true);
        overdue.setOpaque(true);
        
        Color col = new Color(0xD9D9D9);
		borrow.setBackground(col);
		reserve.setBackground(col);
		date.setBackground(col);
		overdue.setBackground(col);
		
		borrow.setFont(backFont);
		reserve.setFont(backFont);
		date.setFont(backFont);
		overdue.setFont(backFont);
		
		EmptyBorder size = new EmptyBorder(25, 200, 25, 200);
        borrow.setBorder(size);
        reserve.setBorder(size);
        date.setBorder(size);
        overdue.setBorder(size);
        
        basicP.add(borrow);
        basicP.add(reserve);
        basicP.add(date);
        basicP.add(overdue);
        
        
        ProfileReader reader = new ProfileReader();
        
        if (userinfo.getPhoneNumber() != null && userinfo.getDob() != null) {
        	profL.setText("<html><body><center>아이디: " + userinfo.getUsername() +
        			"<br>생년월일: " + userinfo.getDob() +
        			"<br>전화번호: " + userinfo.getPhoneNumber() + "</center></body></html>");
        } else {
        	profL.setText("ERROR");
        }

        String idToLookup = userinfo.getUsername();
        
        BorrowReader boReader = new BorrowReader();
        String borrowcount = String.valueOf(boReader.countID(idToLookup));
        
        borrow.setText("대출 중인 도서  " + borrowcount + "권");
        
        
        ReserveReader rvReader = new ReserveReader();
        String reservecount = String.valueOf(rvReader.countID(idToLookup));
        
        reserve.setText("예약 중인 도서 " + reservecount + "권");
        
        String urgcount = String.valueOf(boReader.countBD(idToLookup));
        
        date.setText("반납일 임박 도서 " + urgcount + "권");
        
        String odcount = String.valueOf(boReader.countOD(idToLookup));
        
        overdue.setText("연체 도서 " + odcount + "권");
        
        
        setLayout(new BorderLayout());
		add(profilestateL, BorderLayout.NORTH);
		add(basicP, BorderLayout.CENTER);
		add(backwardB, BorderLayout.SOUTH);
        
	}

}
