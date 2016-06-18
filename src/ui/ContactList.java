/*
 * Created by JFormDesigner on Sat Jun 18 14:40:53 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.*;

import project.Client;
import project.Info;
import project.ResponseCallback;
import project.SingleClient;
import project.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J J
 */
public class ContactList extends JFrame {
	
	// get activated contnet list window
	private static ContactList currentContentList;
	public static ContactList getCurrentContentList(){
		return currentContentList;
	}
	
	private User currentUser;
	
	public ContactList(User user){
		currentContentList = this;
		currentUser = user;
		
		loadFriendList();
		initComponents();
	}
	
	public void loadFriendList(){
		// clear the list
		contactsList.clear();
		
		SingleClient.sent(new Info("friend", currentUser.getAccount()), new ResponseCallback(){

			@Override
			public void successResponse(Object o) {
				List<User> friends = (List<User>)o;
				contactsList.addAll(friends);
				
				System.out.println("add fake contat list");
				list1.setModel(new ListModel<String>(){

					@Override
					public void addListDataListener(ListDataListener l) {
						
					}

					@Override
					public String getElementAt(int index) {
						// return contactsList.get(index);
						User user = contactsList.get(index);
						return  user.getAccount() + " - " + user.getStat();
					}

					@Override
					public int getSize() {
						return contactsList.size();
					}

					@Override
					public void removeListDataListener(ListDataListener l) {
						
					}
					
				});
				//list1.repaint();
			}

			@Override
			public void failedResponse(Object o) {
				// ignore
			}
			
		});
	
	}
	
	private List<User> contactsList = new ArrayList<User>();
	

	public List<User> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<User> contactsList) {
		this.contactsList = contactsList;
	}

	private void searchUserActionPerformed(ActionEvent e) {
		System.out.println("search user\n");
		JFrame frame = new SearchUserFrame(currentUser);
		frame.setVisible(true);
	}

	private void setProfileActionPerformed(ActionEvent e) {
		JFrame frame = new ProfileFrame(currentUser);
		frame.setVisible(true);
	}

	private void userSpaceActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void contactListValueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()){
			JFrame chatRoom = new ChatRoom();
			chatRoom.setVisible(true);
		}
	}

	private void list1ValueChanged(ListSelectionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		menuBar1 = new JMenuBar();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();
		menuItem1 = new JMenuItem();
		scrollPane1 = new JScrollPane();
		list1 = new JList();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//---- menuItem2 ----
			menuItem2.setText("\u641c\u5c0b\u7528\u6236");
			menuItem2.addActionListener(e -> {
			searchUserActionPerformed(e);
		});
			menuBar1.add(menuItem2);

			//---- menuItem3 ----
			menuItem3.setText("\u500b\u4eba\u8a2d\u5b9a");
			menuItem3.addActionListener(e -> setProfileActionPerformed(e));
			menuBar1.add(menuItem3);

			//---- menuItem1 ----
			menuItem1.setText("\u7a7a\u9593");
			menuItem1.addActionListener(e -> userSpaceActionPerformed(e));
			menuBar1.add(menuItem1);
		}
		contentPane.add(menuBar1, BorderLayout.NORTH);

		//======== scrollPane1 ========
		{

			//---- list1 ----
			list1.addListSelectionListener(e -> {
			contactListValueChanged(e);
			list1ValueChanged(e);
		});
			scrollPane1.setViewportView(list1);
		}
		contentPane.add(scrollPane1, BorderLayout.CENTER);
		setSize(500, 515);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JMenuBar menuBar1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem1;
	private JScrollPane scrollPane1;
	private JList<String> list1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

}
