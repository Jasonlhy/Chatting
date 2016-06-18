package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JPanel panel = new ContactList();
		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.add(panel);
		frame.setVisible(true);
	}

}
