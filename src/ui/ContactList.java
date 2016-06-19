/*
 * Created by JFormDesigner on Sat Jun 18 14:40:53 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.*;
import org.jitsi.service.libjitsi.LibJitsi;

import finalproject.communication.ScreenReceiver;
import finalproject.communication.ScreenSender;
import project.Client;
import project.Info;
import project.ResponseCallback;
import project.SingleClient;
import project.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * The contact list main menu, also used as object who received the broadcast
 * message from server background thread
 * 
 * @author J J
 * 
 * 
 */
public class ContactList extends JFrame {

	// get activated contnet list window
	private static ContactList currentContentList;

	public static ContactList getCurrentContentList() {
		return currentContentList;
	}
	

	private User currentUser;
	
	/* Only one article frame */
	private ArticleFrame currentAricleFrame;
	
	/* Screen sharing */
	private ScreenSender screenSender;
	private ScreenReceiver screenReceiver;

	// used id , chatroom object
	// By default, the created chat room is hided only, will not release any memory
	private HashMap<String, ChatRoom> chatrooms = new HashMap<String, ChatRoom>();

	public ContactList(User user) {
		currentContentList = this;
		currentUser = user;

		loadFriendList();
		initComponents();
		setTitle("歡迎!! " + user.getAccount());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// logout itself
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "登出成功!!");
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
		});
	}

	public void loadFriendList() {
		// clear the list
		contactsList.clear();
		System.out.println("load contact list by contactlist: " + contactsList.size());
		SingleClient.sent(new Info("friend", currentUser.getAccount()), new ResponseCallback() {

			@Override
			public void successResponse(Object o) {
				List<User> friends = (List<User>) o;
				contactsList.addAll(friends);

				System.out.println("add fake contat list");
				list1.setModel(new ListModel<String>() {

					@Override
					public void addListDataListener(ListDataListener l) {

					}

					@Override
					public String getElementAt(int index) {
						// return contactsList.get(index);
						User user = contactsList.get(index);
						if(user.getKnow())
							return user.getAccount() + " - " + user.getStat();
						else
							return user.getAccount() + " (Stranger) - " + user.getStat();
					}

					@Override
					public int getSize() {
						return contactsList.size();
					}

					@Override
					public void removeListDataListener(ListDataListener l) {

					}

				});
				list1.repaint();
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
		SearchUserFrame frame = new SearchUserFrame(currentUser);
		frame.setVisible(true);
		this.addWindowListener(frame);
	}

	private void setProfileActionPerformed(ActionEvent e) {
		ProfileFrame frame = new ProfileFrame(currentUser);
		frame.setVisible(true);
		this.addWindowListener(frame);
	}

	private void userSpaceActionPerformed(ActionEvent e) {
		if (currentAricleFrame == null){
			ArticleFrame frame = new ArticleFrame(currentUser);
			frame.setVisible(true);
			currentAricleFrame = frame;
		} else {
			currentAricleFrame.requestFocus();
		}
		
		SingleClient.sent(new Info("article", currentUser.getAccount()));
	}
	
	/*
	 * When a contact is select in the list
	 */
	private void contactListValueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int index = e.getFirstIndex();
			String chatWithAccount = contactsList.get(index).getAccount();
			// exsitting chatroom
			if (chatrooms.containsKey(chatWithAccount)) {
				ChatRoom hisChatRoom = chatrooms.get(chatWithAccount);
				hisChatRoom.setVisible(true);
				hisChatRoom.requestFocus();
			} else {
			// not exisitting chatroom
				ChatRoom chatRoom = new ChatRoom(currentUser, contactsList.get(index));
				chatRoom.setVisible(true);
				chatrooms.put(chatWithAccount, chatRoom);
				this.addWindowListener(chatRoom); // tell it to dispose when this application is closed
				SingleClient.sent(new Info("chatlog", currentUser.getAccount(), contactsList.get(index).getAccount()));
			}
			list1.clearSelection();
		}
	}

	/**
	 * receive messages from username
	 *  
	 * @param username
	 * @param messages
	 */
	public void receivedMessage(String username, List<String> messages) {
		// exisiting chatroom
		if (chatrooms.containsKey(username)) {
			ChatRoom hisChatRoom = chatrooms.get(username);
			hisChatRoom.setVisible(true);
			hisChatRoom.requestFocus();
			hisChatRoom.loadChatRecords(messages);
		} else {
			// not exisiting chatoom
			// new create "push" message from server without exisiting chatroom
			Optional<User> chatWith = contactsList.parallelStream()
					.filter(contact -> contact.getAccount().equals(username)).findFirst();

			if (chatWith.isPresent()) {
				// new create
				ChatRoom chatRoom = new ChatRoom(currentUser, chatWith.get());
				chatRoom.setVisible(true);
				chatrooms.put(username, chatRoom);
				this.addWindowListener(chatRoom); // tell it to dispose when this application is closed
				SingleClient.sent(new Info("chatlog", currentUser.getAccount(), username));
			}
		}
	}

	public void receivedImage(String fromUsername, ImageIcon imageIcon) {
		JFrame frame = new ImagePreviewFrame(imageIcon, "由" + fromUsername + "得到圖片");
		frame.setVisible(true);
	}
	
	
	public void receivedArticle(List<String> articles){
		currentAricleFrame.loadArticles(articles);
	}
	
	/**
	 * Start as a sender for screen sharing
	 * 
	 * @param remoteAddress Receiver address
	 * @param remotePort 
	 * @param localPort 
	 */
	public void startSendScreen(String remoteAddress, String localPort, String remotePort){
		if (this.screenReceiver != null || this.screenSender != null){
			JOptionPane.showMessageDialog(null, "屏莫分享", "只可以有一個屏莫分享", JOptionPane.ERROR_MESSAGE);
			return;
		}
		

		LibJitsi.start();
		try {
			// Create a audio transmit object with the specified params.
			ScreenSender at = new ScreenSender(localPort, remoteAddress, remotePort);
			// Start the transmission
			String result = at.start();
		
			// result will be non-null if there was an error. The return
			// value is a String describing the possible error. Print it.
			if (result == null) {
				System.out.println("ScreeSender Start transmission for ..");
				this.screenSender = at;
			} else {
				System.out.println("Error : " + result);
			}
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			LibJitsi.stop();
		}
	}
	
	
	/**
	 * Start as a receiver for screen sharing
	 * 
	 * @param remoteAddress Sender address
	 * @param remotePort2 
	 * @param localPort2 
	 */
	public void startReceiveScreen(String remoteAddress, String localPort, String remotePort){
		if (this.screenReceiver != null || this.screenSender != null){
			JOptionPane.showMessageDialog(null, "屏莫分享", "只可以有一個屏莫分享", JOptionPane.ERROR_MESSAGE);
			return;
		}
		

		LibJitsi.start();
		
		try {
			ScreenReceiver avReceive = new ScreenReceiver(localPort, remoteAddress, remotePort);

			if (avReceive.initialize()) {
				avReceive.addSimpleListener("Video");
				avReceive.start();
				this.screenReceiver = avReceive;
				System.out.println("Start receving");
			} else {
				System.err.println("Failed to initialize the receiver sessions.");
			}
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			LibJitsi.stop();
		}
	}
	
	
	/**
	 * Stop the screen sharing session regardless of sender or receiver
	 * This method is not designed to notify the partner, please notify the partner to stop his/her socket
	 */
	public void stopSharing(){
		if (screenSender != null){
			screenSender.stop();
			screenSender = null;
			System.out.println("sender transmission ended.");
		}
		
		if (screenReceiver != null){
			screenReceiver.close();
			screenReceiver = null;
			System.out.println("sender transmission ended.");
		}
	}
	
	/**
	 * Transform the byte from received file to the byte to new/existing file
	 * 
	 * @param fromUsername
	 * @param receivedFile
	 * @param filename
	 */
	public void receivedFile(String fromUsername, File receivedFile, String filename) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(fromUsername+" 傳送  "+filename+" 給你...");
		chooser.setSelectedFile(new File(chooser.getCurrentDirectory(), filename)); // default to the same name as user b file

		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				System.out.println("selected File: ()" + chooser.getSelectedFile());
				File destFile = chooser.getSelectedFile();

				FileInputStream fileInputStream = new FileInputStream(receivedFile);
				FileChannel src = fileInputStream.getChannel();
				FileOutputStream fileOutputStream = new FileOutputStream(destFile);
				FileChannel dest = fileOutputStream.getChannel();
				dest.transferFrom(src, 0, src.size());
				
				fileInputStream.close();
				fileOutputStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private void list1ValueChanged(ListSelectionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
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
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JMenuBar menuBar1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem1;
	private JScrollPane scrollPane1;
	private JList list1;
	// JFormDesigner - End of variables declaration //GEN-END:variables

}
