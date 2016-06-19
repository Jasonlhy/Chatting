package ui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import project.User;

public class TestUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// JPanel panel = new LoginFrame();
		JFrame frame;
		try {
			frame = new ImagePreviewFrame(new ImageIcon(ImageIO.read(new File("C:/Users/Public/Pictures/Sample Pictures/Koala.jpg"))), "GOD");
			// frame.setSize(1000, 1000);
			//	frame.add(panel);
				frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
