/*
 * Created by JFormDesigner on Sun Jun 19 13:46:34 CST 2016
 */

package ui;

import java.awt.*;
import javax.swing.*;

/**
 * @author J J
 */
public class ImagePreviewFrame extends JFrame {
	
	public ImagePreviewFrame(ImageIcon imageIcon, String title){
		this(imageIcon, title, null);
	}
	
	public ImagePreviewFrame(ImageIcon imageIcon, String title, JComponent parent) {
		initComponents();
		setTitle(title);
		int width = imageIcon.getIconWidth();
		int height = imageIcon.getIconHeight();
		Dimension dimension = new Dimension(width, height);
		
		label1.setIcon(imageIcon);
		label1.setSize(dimension);
		this.setSize(dimension);
		setLocationRelativeTo(parent);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		label1 = new JLabel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(label1);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JLabel label1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
