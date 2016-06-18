/*
 * Created by JFormDesigner on Sat Jun 18 14:40:53 CST 2016
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J J
 */
public class ContactList extends JPanel {
	public ContactList() {
		contactsList.add("Item 1");
		contactsList.add("Item 2");
		
		initComponents();
	}
	
	private List<String> contactsList = new ArrayList<String>();
	

	public List<String> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<String> contactsList) {
		this.contactsList = contactsList;
	}

	private void searchUserActionPerformed(ActionEvent e) {
		// TODO add your code here
		System.out.println("search user\n");
	}

	private void setProfileActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void userSpaceActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void contactListValueChanged(ListSelectionEvent e) {
		JFrame chatRoom = new ChatRoom();
		chatRoom.setVisible(true);
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

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//---- menuItem2 ----
			menuItem2.setText("\u641c\u5c0b\u7528\u6236");
			menuItem2.addActionListener(e -> {
			searchUserActionPerformed(e);
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
		add(menuBar1, BorderLayout.NORTH);

		//======== scrollPane1 ========
		{

			//---- list1 ----
			list1.addListSelectionListener(e -> {
			contactListValueChanged(e);
		});
			scrollPane1.setViewportView(list1);
		}
		add(scrollPane1, BorderLayout.CENTER);

		//---- bindings ----
		bindingGroup = new BindingGroup();
		bindingGroup.addBinding(SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE,
			this, (BeanProperty) BeanProperty.create("contactsList"), list1));
		bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JMenuBar menuBar1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem1;
	private JScrollPane scrollPane1;
	private JList list1;
	private BindingGroup bindingGroup;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

}
