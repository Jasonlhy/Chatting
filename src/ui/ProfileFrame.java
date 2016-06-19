/*
 * Created by JFormDesigner on Sat Jun 18 20:45:43 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import project.Info;
import project.SingleClient;
import project.User;

/**
 * ProfileFramw, window listener listen to the main menu closed event
 * @author J J
 */
public class ProfileFrame extends JFrame implements WindowListener{
	private User currentUser;
	
	public ProfileFrame(User user) {
		currentUser = user;
		initComponents();
		
		statusTextField.setText(user.getStat());
		passwordField.setText(user.getPassword());
	}

	private void updateButtonActionPerformed(ActionEvent e) {
		if(!String.valueOf(passwordField.getPassword()).equals("")){
			currentUser.setStat(statusTextField.getText());
			currentUser.setPassword(String.valueOf(passwordField.getPassword()));
			SingleClient.sent(new Info("userfile", new User(currentUser.getAccount(), String.valueOf(passwordField.getPassword()), statusTextField.getText())));
			this.dispose();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel2 = new JPanel();
		panel5 = new JPanel();
		statusLabel = new JLabel();
		statusTextField = new JTextField();
		panel6 = new JPanel();
		label3 = new JLabel();
		panel7 = new JPanel();
		passwordField = new JPasswordField();
		panel8 = new JPanel();
		button1 = new JButton();
		panel3 = new JPanel();
		label1 = new JLabel();

		//======== this ========
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

			panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

			//======== panel5 ========
			{
				panel5.setLayout(new FlowLayout());

				//---- statusLabel ----
				statusLabel.setText("\u72c0\u614b\uff1a");
				panel5.add(statusLabel);

				//---- statusTextField ----
				statusTextField.setPreferredSize(new Dimension(120, 21));
				panel5.add(statusTextField);
			}
			panel2.add(panel5);

			//======== panel6 ========
			{
				panel6.setLayout(new FlowLayout());

				//---- label3 ----
				label3.setText("\u5bc6\u78bc");
				panel6.add(label3);

				//======== panel7 ========
				{
					panel7.setLayout(new FlowLayout());

					//---- passwordField ----
					passwordField.setPreferredSize(new Dimension(120, 21));
					panel7.add(passwordField);
				}
				panel6.add(panel7);
			}
			panel2.add(panel6);

			//======== panel8 ========
			{
				panel8.setLayout(new FlowLayout());

				//---- button1 ----
				button1.setText("\u66f4\u65b0");
				button1.addActionListener(e -> updateButtonActionPerformed(e));
				panel8.add(button1);
			}
			panel2.add(panel8);
		}
		contentPane.add(panel2, BorderLayout.CENTER);

		//======== panel3 ========
		{
			panel3.setLayout(new FlowLayout());

			//---- label1 ----
			label1.setText("\u500b\u4eba\u8a2d\u5b9a");
			label1.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.BOLD, 20));
			panel3.add(label1);
		}
		contentPane.add(panel3, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel2;
	private JPanel panel5;
	private JLabel statusLabel;
	private JTextField statusTextField;
	private JPanel panel6;
	private JLabel label3;
	private JPanel panel7;
	private JPasswordField passwordField;
	private JPanel panel8;
	private JButton button1;
	private JPanel panel3;
	private JLabel label1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		System.out.println("Main menu is closed!!!!");
		this.dispose();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
;
	}
}
