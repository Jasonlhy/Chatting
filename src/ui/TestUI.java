package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import project.User;

public class TestUI {

	public static void main(String[] args) {
		ArticleFrame frame = new ArticleFrame(null);
		frame.setVisible(true);
	}

}
