/*
 * Created by JFormDesigner on Sat Jun 18 15:35:09 CST 2016
 */

package ui;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListDataListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import project.Info;
import project.SingleClient;
import project.User;
import say.swing.JFontChooser;

/**
 * @author J J
 */
public class ChatRoom extends JFrame implements WindowListener {

	private User currentUser, toUser;
	public List<String> log = new ArrayList<String>();
	private Color currentColor;
	private Color currentBackground;
	private Font currentFont;
	private boolean isSharing = false;

	public ChatRoom(User user, User user2) {
		currentUser = user;
		toUser = user2;
		initComponents();
		setTitle("正和" + user2.getAccount() + "聊天...");

		statusLabel.setText(user2.getStat());
		currentColor = list1.getForeground();
		currentFont = list1.getFont();
		currentBackground = list1.getBackground();
		list1.setCellRenderer(new MyCellRenderer());
	}

	class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
		public Component getListCellRendererComponent(JList<?> list, // the list
				Object value, // value to display
				int index, // cell index
				boolean isSelected, // is the cell selected
				boolean cellHasFocus) // does the cell have focus
		{
			String s = value.toString();
			setText(s);

			setBackground(currentBackground);
			setForeground(currentColor);
			setFont(currentFont);
			setOpaque(true); // paint yellow pixel

			// System.out.println("called cell render");
			return this;
		}
	}

	private void sendTextButtonClick(ActionEvent e) {
		Date date = new Date();
		String inputText = inputTextArea.getText();
		SingleClient.sent(new Info("chat", currentUser.getAccount(), toUser.getAccount(), inputText, date.toString()));
		inputTextArea.setText("");
	}

	public void loadChatRecords(List<String> messages) {
		log.clear();
		log.addAll(messages);
		list1.setModel(new ListModel<String>() {

			@Override
			public void addListDataListener(ListDataListener l) {

			}

			@Override
			public String getElementAt(int index) {
				// return contactsList.get(index);
				return log.get(index);
			}

			@Override
			public int getSize() {
				return log.size();
			}

			@Override
			public void removeListDataListener(ListDataListener l) {

			}

		});
	}

	private void fontcolorlabelMouseClicked(MouseEvent e) {
		Color chosenColor = JColorChooser.showDialog(null, "Choose a color", fontcolorlabel.getForeground());
		System.out.println("HIHIHI");
		currentColor = chosenColor;
		fontcolorlabel.setForeground(currentColor);
		list1.repaint();
	}

	private void backgroundColorLabelMouseClicked(MouseEvent e) {
		Color chosenColor = JColorChooser.showDialog(null, "Choose a color", backgroundColorLabel.getBackground());
		currentBackground = chosenColor;
		backgroundColorLabel.setBackground(chosenColor);
		list1.repaint();
	}

	private void fontLabelMouseClicked(MouseEvent e) {
		JFontChooser fontChooser = new JFontChooser();
		fontChooser.setSelectedFont(currentFont);
		int option = fontChooser.showDialog(null);
		Font chosenFont = fontChooser.getSelectedFont();
		if (option == JFontChooser.OK_OPTION && chosenFont != null) {
			currentFont = chosenFont;
		}
		list1.repaint();
	}

	private void sendImageActionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			try {
				System.out.println("full path: " + file.getAbsolutePath());
				String fileType = Files.probeContentType(file.toPath());
				
				// is it image
				if (fileType.startsWith("image")){
					Image image = ImageIO.read(file);
					System.out.println("filetype: " + fileType);
					ImageIcon imageIcon = new ImageIcon(image);
					SingleClient.sent(new Info("image", toUser.getAccount(), imageIcon));
				} else {
					JOptionPane.showMessageDialog(null, "你沒有選取圖片");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void sendFileActionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			try {
				FileInputStream fileStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = null;
				try {
					objectInputStream = new ObjectInputStream(fileStream);
					SingleClient.sent(new Info("file", toUser.getAccount(), chooser.getSelectedFile().getName(), objectInputStream));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void screenSharingActionPerformed(ActionEvent e) {
		if (!isSharing){
			SingleClient.sent(new Info("screen", toUser.getAccount()));
			isSharing = true;
			button4.setText("停止");
		} else {
			ContactList.getCurrentContentList().stopSharing();
			isSharing = false;
			button4.setText("屏幕分享");
		}
		
		// ContactList.getCurrentContentList().startSendScreen("127.0.0.1");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel1 = new JPanel();
		statusLabel = new JLabel();
		hSpacer1 = new JPanel(null);
		button4 = new JButton();
		button3 = new JButton();
		button1 = new JButton();
		panel2 = new JPanel();
		inputTextArea = new JTextArea();
		panel3 = new JPanel();
		fontLabel = new JLabel();
		fontcolorlabel = new JLabel();
		backgroundColorLabel = new JLabel();
		button2 = new JButton();
		scrollPane1 = new JScrollPane();
		list1 = new JList();

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

			panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

			//---- statusLabel ----
			statusLabel.setText("Status....");
			statusLabel.setPreferredSize(new Dimension(120, 15));
			panel1.add(statusLabel);
			panel1.add(hSpacer1);

			//---- button4 ----
			button4.setText("\u5c4f\u5e55\u5206\u4eab");
			button4.addActionListener(e -> screenSharingActionPerformed(e));
			panel1.add(button4);

			//---- button3 ----
			button3.setText("\u50b3\u9001\u6a94\u6848");
			button3.addActionListener(e -> sendFileActionPerformed(e));
			panel1.add(button3);

			//---- button1 ----
			button1.setText("\u50b3\u9001\u5716\u7247");
			button1.addActionListener(e -> sendImageActionPerformed(e));
			panel1.add(button1);
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== panel2 ========
		{
			panel2.setMinimumSize(new Dimension(100, 1000));
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

			//---- inputTextArea ----
			inputTextArea.setMinimumSize(new Dimension(350, 25));
			inputTextArea.setPreferredSize(new Dimension(200, 25));
			panel2.add(inputTextArea);

			//======== panel3 ========
			{
				panel3.setLayout(new FlowLayout());

				//---- fontLabel ----
				fontLabel.setText("T");
				fontLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						fontLabelMouseClicked(e);
					}
				});
				panel3.add(fontLabel);

				//---- fontcolorlabel ----
				fontcolorlabel.setText("A");
				fontcolorlabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						fontcolorlabelMouseClicked(e);
					}
				});
				panel3.add(fontcolorlabel);

				//---- backgroundColorLabel ----
				backgroundColorLabel.setText("       ");
				backgroundColorLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				backgroundColorLabel.setOpaque(true);
				backgroundColorLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						backgroundColorLabelMouseClicked(e);
					}
				});
				panel3.add(backgroundColorLabel);
			}
			panel2.add(panel3);

			//---- button2 ----
			button2.setText("\u767c\u9001");
			button2.addActionListener(e -> sendTextButtonClick(e));
			panel2.add(button2);
		}
		contentPane.add(panel2, BorderLayout.SOUTH);

		//======== scrollPane1 ========
		{
			scrollPane1.setRequestFocusEnabled(false);
			scrollPane1.setViewportView(list1);
		}
		contentPane.add(scrollPane1, BorderLayout.CENTER);
		setSize(495, 405);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel1;
	private JLabel statusLabel;
	private JPanel hSpacer1;
	private JButton button4;
	private JButton button3;
	private JButton button1;
	private JPanel panel2;
	private JTextArea inputTextArea;
	private JPanel panel3;
	private JLabel fontLabel;
	private JLabel fontcolorlabel;
	private JLabel backgroundColorLabel;
	private JButton button2;
	private JScrollPane scrollPane1;
	private JList list1;
	// JFormDesigner - End of variables declaration //GEN-END:variables

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
		
	}
}
