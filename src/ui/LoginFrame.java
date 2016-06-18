/*
 * Created by JFormDesigner on Sat Jun 18 16:07:52 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import project.Client;
import project.Info;
import project.ResponseCallback;
import project.SingleClient;
import project.User;

/**
 * @author J J
 */
public class LoginFrame extends JFrame {
	public LoginFrame() {
		initComponents();
	}

	private void loginButtonActionPerformed(ActionEvent e) {
		System.out.println("Login");
		Client client = SingleClient.getClient();
		// client.sent( new Info("login", new User()));
		String username = usernameTextField.getText();
		String password = String.valueOf(passwordTextField.getPassword());
		
		try {
			LoginFrame ownForm = this;
			// client.sent( new Info("login", new User(username, password)));
			client.sent(new Info("login", new User(username, password)), new ResponseCallback(){

				@Override
				public void successResponse(Object o) {
					User user = ((Info)o).getUser();
					JFrame frame = new ContactList(user);
					frame.setVisible(true);
					ownForm.dispose();
				}

				@Override
				public void failedResponse(Object o) {
					System.out.println("l: " + o);
					Info info = (Info) o;
					messageLabel.setText(info.getInfo2());
				}
				
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel2 = new JPanel();
		label1 = new JLabel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		label4 = new JLabel();
		usernameTextField = new JTextField();
		panel5 = new JPanel();
		label3 = new JLabel();
		passwordTextField = new JPasswordField();
		panel6 = new JPanel();
		panel7 = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		panel8 = new JPanel();
		messageLabel = new JLabel();

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

					//---- usernameTextField ----
					usernameTextField.setPreferredSize(new Dimension(100, 21));
					panel4.add(usernameTextField);

					//======== panel5 ========
					{
						panel5.setLayout(new FlowLayout());

						//---- label3 ----
						label3.setText("\u5bc6\u78bc\uff1a");
						panel5.add(label3);

						//---- passwordTextField ----
						passwordTextField.setMinimumSize(new Dimension(100, 21));
						passwordTextField.setPreferredSize(new Dimension(100, 21));
						panel5.add(passwordTextField);
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

						//---- messageLabel ----
						messageLabel.setText("text");
						messageLabel.setOpaque(true);
						messageLabel.setPreferredSize(new Dimension(200, 15));
						messageLabel.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.PLAIN, 18));
						messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
						messageLabel.setForeground(Color.red);
						panel8.add(messageLabel);
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
	private JTextField usernameTextField;
	private JPanel panel5;
	private JLabel label3;
	private JPasswordField passwordTextField;
	private JPanel panel6;
	private JPanel panel7;
	private JButton button1;
	private JButton button2;
	private JPanel panel8;
	private JLabel messageLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
