package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.User;

public class TestUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// JPanel panel = new LoginFrame();
		JFrame frame = new ProfileFrame(new User("Jason", "1234"));
		// frame.setSize(1000, 1000);
	//	frame.add(panel);
		frame.setVisible(true);
	}

}
