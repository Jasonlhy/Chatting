package project;

import javax.swing.JFrame;
import ui.LoginFrame;

public class MainApp {
	public static void main(String[] args) {
		// System.out.println("arg length: " + args.length);
		if (args.length == 1)
			SingleClient.setServerAddress(args[0]);
		
		JFrame frame = new LoginFrame();
		frame.setVisible(true);
	}


}
