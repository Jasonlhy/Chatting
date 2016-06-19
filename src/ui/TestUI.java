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
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(chooser.getCurrentDirectory(), "filename")); // default to the same name as user b file

		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				System.out.println("selected File: ()" + chooser.getSelectedFile());
				File destFile = chooser.getSelectedFile();

				/*
				FileInputStream fileInputStream = new FileInputStream(receivedFile);
				FileChannel src = fileInputStream.getChannel();
				FileOutputStream fileOutputStream = new FileOutputStream(destFile);
				FileChannel dest = fileOutputStream.getChannel();
				dest.transferFrom(src, 0, src.size());
				
				fileInputStream.close();
				fileOutputStream.close(); */
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
