/*
 * Created by JFormDesigner on Sun Jun 19 17:10:12 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListDataListener;


import project.Info;
import project.SingleClient;
import project.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author J J
 */
public class ArticleFrame extends JFrame {
	private List<String> articles = new ArrayList<String>();
	private User currentUser;
	
	public ArticleFrame(User currentUser) {
		initComponents();
		setTitle("ªÅ¶¡");
		this.currentUser = currentUser;
	//	loadArticles();
	}

	public void loadArticles(List<String> arts){
		articles.clear();
		articles.addAll(arts);
		System.out.println("load article with size: " + articles.size());
		
		list1.setModel(new ListModel(){

			@Override
			public void addListDataListener(ListDataListener l) {
				
			}

			@Override
			public Object getElementAt(int index) {
				return articles.get(index);
			}

			@Override
			public int getSize() {
				return articles.size();
			}

			@Override
			public void removeListDataListener(ListDataListener l) {
				
			}
			
		});
	}

	private void sendArticleActionPerformed(ActionEvent e) {
		Date date = new Date();
		String sendContent = textArea1.getText();
		SingleClient.sent(new Info("write", currentUser.getAccount(), sendContent, date.toString()));
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		panel2 = new JPanel();
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();
		button1 = new JButton();
		scrollPane2 = new JScrollPane();
		list1 = new JList();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel2 ========
		{
			panel2.setOpaque(false);
			panel2.setPreferredSize(new Dimension(63, 80));

			// JFormDesigner evaluation mark
			panel2.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel2.getBorder())); panel2.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel2.setLayout(new FlowLayout());

			//======== scrollPane1 ========
			{

				//---- textArea1 ----
				textArea1.setPreferredSize(new Dimension(500, 50));
				scrollPane1.setViewportView(textArea1);
			}
			panel2.add(scrollPane1);

			//---- button1 ----
			button1.setText("\u767c\u6587");
			button1.setPreferredSize(new Dimension(70, 50));
			button1.setMinimumSize(new Dimension(57, 23));
			button1.setFont(new Font("\u65b0\u7d30\u660e\u9ad4", Font.BOLD, 16));
			button1.addActionListener(e -> sendArticleActionPerformed(e));
			panel2.add(button1);
		}
		contentPane.add(panel2, BorderLayout.NORTH);

		//======== scrollPane2 ========
		{

			//---- list1 ----
			list1.setOpaque(false);
			list1.setPreferredSize(new Dimension(31, 50));
			scrollPane2.setViewportView(list1);
		}
		contentPane.add(scrollPane2, BorderLayout.CENTER);
		setSize(645, 595);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JPanel panel2;
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	private JButton button1;
	private JScrollPane scrollPane2;
	private JList list1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
