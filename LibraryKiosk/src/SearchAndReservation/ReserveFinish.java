package SearchAndReservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReserveFinish extends JFrame {
    private JPanel panel;         // 전체 panel
    private JFrame previousFrame; // 이전 화면을 저장
    private JProgressBar progressBar; // 타임바

    public ReserveFinish(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        createUI();
        startTimer(); // 타이머 시작
    }
    private void createUI() {
        setTitle("Reserve Finish");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // 화면 중앙에 컴포넌트 배치


        // 전체 panel
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        EndLabel();  // 완료 label

        TimeBar(30);   // 30초 타이머

        BackPanel(); // 뒤로 가기

        add(panel);
        setVisible(true);
    }

    // 완료 문구
    private void EndLabel() {
        JPanel end = new JPanel(new GridLayout(2, 1));
        JLabel end1 = new JLabel("<html><center>예약이<br>완료되었습니다</center></html>" , SwingConstants.CENTER);
        JLabel end2 = new JLabel("<html><center>이용해주셔서<br>감사합니다</center></html>" , SwingConstants.CENTER);

        Font end1Font = new Font("Dialog", Font.BOLD, 35); // 글꼴, 굵은체, 크기 20
        Font end2Font = new Font("Dialog", Font.PLAIN, 25); // 글꼴, 굵은체, 크기 20

        end1.setFont(end1Font);
        end2.setFont(end2Font);
        end.add(end1);
        end.add(end2);

        end1.setBorder(new EmptyBorder(100, 0, 0, 0));
        end2.setBorder(new EmptyBorder(0, 0, 150, 0));

        panel.add(end, BorderLayout.CENTER);
    }

    // Timer
    private void TimeBar(int sec) {
        progressBar = new JProgressBar();
        progressBar.setMaximum(sec); // 최대값을 30으로 설정 (30초)
        progressBar.setValue(sec); // 초기값을 30으로 설정
        panel.add(progressBar, BorderLayout.NORTH);
    }
    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                if (value > 0) {
                    progressBar.setValue(value - 1); // 타임바 값 1씩 감소
                } else {
                    // 타임바 값이 0이 되면 홈 화면으로 이동
                    goHome();
                }
            }
        });
        timer.start();
    }
    private void goHome() {
        // 홈 화면으로 이동하는 코드를 여기에 추가
    }

    // 뒤로 가기
    private void BackPanel() {
        JButton back = new JButton("뒤로 가기");  // 뒤로가기 버튼
        back.setBorder(new EmptyBorder(10, 0, 10, 0));
        Font backFont = new Font("Dialog", Font.BOLD, 20); // 글꼴, 굵은체, 크기 20
        back.setFont(backFont);
        back.setBackground(Color.ORANGE);  // 버튼의 배경색
        back.setForeground(Color.BLACK);   // 버튼의 글자색

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        panel.add(back, BorderLayout.SOUTH); // 전체 panel의 하단에 뒤로가기 버튼 추가
    }
    private void goBack() {
        previousFrame.setVisible(true); // 이전 화면을 보이도록 설정
        dispose(); // 현재 화면 닫기
    }
}
