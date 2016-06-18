/*
 * Created by JFormDesigner on Sat Jun 18 18:58:56 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author J J
 */
public class SearchUserFrame extends JFrame {
	public SearchUserFrame() {
		initComponents();
	}

	private void searchButtonActionPerformed(ActionEvent e) {
		String searchUserId = searchTextField.getText();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel1 = new JPanel();
		label1 = new JLabel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label2 = new JLabel();
		searchTextField = new JTextField();
		button1 = new JButton();
		scrollPane1 = new JScrollPane();
		resultList = new JList();

		//======== this ========
		setTitle("Chating!!");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		//======== panel1 ========
		{
			panel1.setPreferredSize(new Dimension(94, 10));

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));

			//---- label1 ----
			label1.setText("\u641c\u5c0b\u7528\u6236");
			label1.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.BOLD, 20));
			panel1.add(label1);
		}
		contentPane.add(panel1);

		//======== panel2 ========
		{
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

			//======== panel3 ========
			{
				panel3.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.BOLD, 12));
				panel3.setPreferredSize(new Dimension(313, 5));
				panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

				//---- label2 ----
				label2.setText("\u540d\u7a31: ");
				label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));
				panel3.add(label2);

				//---- searchTextField ----
				searchTextField.setMaximumSize(new Dimension(255, 255));
				searchTextField.setPreferredSize(new Dimension(200, 21));
				searchTextField.setMinimumSize(new Dimension(6, 10));
				panel3.add(searchTextField);

				//---- button1 ----
				button1.setText("\u641c\u5c0b");
				button1.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.BOLD, 12));
				button1.addActionListener(e -> searchButtonActionPerformed(e));
				panel3.add(button1);
			}
			panel2.add(panel3);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(resultList);
			}
			panel2.add(scrollPane1);
		}
		contentPane.add(panel2);
		setSize(440, 415);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel1;
	private JLabel label1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel label2;
	private JTextField searchTextField;
	private JButton button1;
	private JScrollPane scrollPane1;
	private JList resultList;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
