/*
 * Created by JFormDesigner on Sat Jun 18 15:35:09 CST 2016
 */

package ui;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author J J
 */
public class ChatRoom extends JFrame {
	public ChatRoom() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel1 = new JPanel();
		label1 = new JLabel();
		button1 = new JButton();
		panel2 = new JPanel();
		textArea1 = new JTextArea();
		scrollPane1 = new JScrollPane();

		//======== this ========
		setTitle("Chating!!");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new FormLayout(
				"15*(default, $lcgap), default",
				"fill:default"));

			//---- label1 ----
			label1.setText("Status....");
			panel1.add(label1, CC.xywh(1, 1, 14, 1));

			//---- button1 ----
			button1.setText("\u50b3\u9001\u5716\u7247");
			panel1.add(button1, CC.xy(27, 1));
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== panel2 ========
		{
			panel2.setMinimumSize(new Dimension(100, 1000));
			panel2.setLayout(new FormLayout(
				"default",
				"fill:default"));

			//---- textArea1 ----
			textArea1.setMinimumSize(new Dimension(350, 25));
			panel2.add(textArea1, CC.xy(1, 1));
		}
		contentPane.add(panel2, BorderLayout.SOUTH);
		contentPane.add(scrollPane1, BorderLayout.CENTER);
		setSize(460, 405);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel1;
	private JLabel label1;
	private JButton button1;
	private JPanel panel2;
	private JTextArea textArea1;
	private JScrollPane scrollPane1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
