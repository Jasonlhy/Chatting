/*
 * Created by JFormDesigner on Sat Jun 18 16:07:52 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author J J
 */
public class LoginFrame extends JFrame {
	public LoginFrame() {
		initComponents();
	}

	private void loginButtonActionPerformed(ActionEvent e) {
		System.out.println("Login");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel2 = new JPanel();
		label1 = new JLabel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		label4 = new JLabel();
		textField1 = new JTextField();
		panel5 = new JPanel();
		label3 = new JLabel();
		textField2 = new JTextField();
		panel6 = new JPanel();
		panel7 = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		panel8 = new JPanel();
		label5 = new JLabel();

		//======== this ========
		setTitle("Chatting!!");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel2 ========
		{

			// JFormDesigner evaluation mark
			panel2.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel2.getBorder())); panel2.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel2.setLayout(new BorderLayout());

			//---- label1 ----
			label1.setText("\u767b\u5165");
			label1.setVerticalAlignment(SwingConstants.BOTTOM);
			label1.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.BOLD, 22));
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			label1.setPreferredSize(new Dimension(44, 66));
			label1.setMinimumSize(new Dimension(44, 66));
			panel2.add(label1, BorderLayout.NORTH);

			//======== panel3 ========
			{
				panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

				//======== panel4 ========
				{
					panel4.setLayout(new FlowLayout());

					//---- label4 ----
					label4.setText("\u540d\u7a31\uff1a");
					label4.setPreferredSize(new Dimension(55, 30));
					label4.setHorizontalAlignment(SwingConstants.RIGHT);
					panel4.add(label4);

					//---- textField1 ----
					textField1.setPreferredSize(new Dimension(100, 21));
					panel4.add(textField1);

					//======== panel5 ========
					{
						panel5.setLayout(new FlowLayout());

						//---- label3 ----
						label3.setText("\u5bc6\u78bc\uff1a");
						panel5.add(label3);

						//---- textField2 ----
						textField2.setPreferredSize(new Dimension(100, 21));
						panel5.add(textField2);
					}
					panel4.add(panel5);
				}
				panel3.add(panel4);

				//======== panel6 ========
				{
					panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

					//======== panel7 ========
					{
						panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));

						//---- button1 ----
						button1.setText("\u767b\u5165");
						button1.addActionListener(e -> loginButtonActionPerformed(e));
						panel7.add(button1);

						//---- button2 ----
						button2.setText("\u8a3b\u518a");
						panel7.add(button2);
					}
					panel6.add(panel7);

					//======== panel8 ========
					{
						panel8.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 5));

						//---- label5 ----
						label5.setText("text");
						label5.setOpaque(true);
						label5.setPreferredSize(new Dimension(200, 15));
						label5.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.PLAIN, 18));
						label5.setHorizontalAlignment(SwingConstants.CENTER);
						label5.setForeground(Color.red);
						panel8.add(label5);
					}
					panel6.add(panel8);
				}
				panel3.add(panel6);
			}
			panel2.add(panel3, BorderLayout.CENTER);
		}
		contentPane.add(panel2, BorderLayout.CENTER);
		setSize(400, 300);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel2;
	private JLabel label1;
	private JPanel panel3;
	private JPanel panel4;
	private JLabel label4;
	private JTextField textField1;
	private JPanel panel5;
	private JLabel label3;
	private JTextField textField2;
	private JPanel panel6;
	private JPanel panel7;
	private JButton button1;
	private JButton button2;
	private JPanel panel8;
	private JLabel label5;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
