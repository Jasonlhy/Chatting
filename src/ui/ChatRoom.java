/*
 * Created by JFormDesigner on Sat Jun 18 15:35:09 CST 2016
 */

package ui;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListDataListener;


import project.Info;
import project.SingleClient;
import project.User;

/**
 * @author J J
 */
public class ChatRoom extends JFrame {
	
	private User currentUser, toUser;
	public List<String> log = new ArrayList<String>();
	
	public ChatRoom(User user, User user2) {
		currentUser = user;
		toUser = user2;
		initComponents();
		setTitle("¥¿©M" + user2.getAccount() + "²á¤Ñ...");
		list1.setCellRenderer(new MyCellRenderer());
	}
	

	 class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
	     public Component getListCellRendererComponent(
	       JList<?> list,           // the list
	       Object value,            // value to display
	       int index,               // cell index
	       boolean isSelected,      // is the cell selected
	       boolean cellHasFocus)    // does the cell have focus
	     {
	         String s = value.toString();
	         setText(s);
	    
	       
	         setBackground(Color.YELLOW);
	         setForeground(Color.BLUE);
	         setFont(list.getFont());
	         setOpaque(true); // paint yellow pixel
	         
	        // System.out.println("called cell render");
	         return this;
	     }
	 }
	 
	private void sendTextButtonClick(ActionEvent e) {
		Date date = new Date();
		String inputText = inputTextArea.getText();
		SingleClient.sent(new Info("chat", currentUser.getAccount(), toUser.getAccount(), inputText, date.toString()));
	}

	public void loadChatRecords(List<String> messages){
		log.clear();
		log.addAll(messages);
		list1.setModel(new ListModel<String>(){

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
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel1 = new JPanel();
		label1 = new JLabel();
		hSpacer1 = new JPanel(null);
		button1 = new JButton();
		panel2 = new JPanel();
		inputTextArea = new JTextArea();
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

			//---- label1 ----
			label1.setText("Status....");
			label1.setPreferredSize(new Dimension(120, 15));
			panel1.add(label1);
			panel1.add(hSpacer1);

			//---- button1 ----
			button1.setText("\u50b3\u9001\u5716\u7247");
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
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel1;
	private JLabel label1;
	private JPanel hSpacer1;
	private JButton button1;
	private JPanel panel2;
	private JTextArea inputTextArea;
	private JButton button2;
	private JScrollPane scrollPane1;
	private JList list1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
