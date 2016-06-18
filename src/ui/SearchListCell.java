/*
 * Created by JFormDesigner on Sat Jun 18 19:36:33 CST 2016
 */

package ui;

import java.awt.*;
import javax.swing.*;

import project.User;

/**
 * @author J J
 */
public class SearchListCell extends JPanel {
	private User user;
	
	public SearchListCell(User user){
		initComponents();
		this.user = user;
		this.label1.setText(user.getAccount());
	}
	
	public SearchListCell() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - J J
		label1 = new JLabel();
		hSpacer1 = new JPanel(null);
		addButton = new JButton();

		//======== this ========
		setPreferredSize(new Dimension(66, 23));
		setMinimumSize(new Dimension(66, 23));

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//---- label1 ----
		label1.setText("name");
		add(label1);
		add(hSpacer1);

		//---- addButton ----
		addButton.setText("+");
		add(addButton);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - J J
	private JLabel label1;
	private JPanel hSpacer1;
	private JButton addButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
