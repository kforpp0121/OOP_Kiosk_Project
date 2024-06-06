package StartLogin;

import javax.swing.*;

import SearchAndReservation.Search;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class StartMain {

    private JFrame frame;
    Color orangeColor = new Color(238, 121, 3);
    Color green = new Color(70, 156, 118);

    public StartMain(String msg) {
    }

    public void showStartScreen() {
    	frame.setVisible(true);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			JFrame frame = new JFrame();
    			frame.setTitle("시작화면");              // 창 제목
    			frame.setSize(450, 700);  // 키오스크 화면 크기
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 실행 종료
                frame.setLocation(300, 10);      // (300, 10) 위치에 배치

                Start start = new Start(frame);
                frame.add(start);
                start.setVisible(true);

                // GUI를 보이도록 설정
                frame.setVisible(true);
    		}
    	});
    }
}
