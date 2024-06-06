package menu;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		JFrame main = new JFrame();
		main.setSize(450, 700);
		main.setLocation(300, 10);
		MenuFirst menu = new MenuFirst(main);
		main.add(menu);
		main.setVisible(true);
	}
}