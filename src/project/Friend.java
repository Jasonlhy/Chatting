package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Friend extends Panel implements ActionListener{
	
	private MainApp mainApp;
	private int w, h;
	private TextField search;
	private int ww = 200, wy = 50, jw = 200, jh = 30, jy = 100, bw = 70, bh = 30, by = 150;
	private Button sid, snick;
	private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panel);
	
	public Friend(MainApp main){
		mainApp = main;
		w = main.getW();
		h = main.getH();
		setup();
	}
	
	private void setup(){
		this.setSize(new Dimension(mainApp.getW(), mainApp.getH()));
		this.setLayout(null);
		mainApp.add(this);
		
		initDraw();
	}
	public void draw(){
		this.validate();
		this.setVisible(true);
	}
	public void close(){
		this.setVisible(false);
	}
	public void setSearch(ArrayList<User> us){
		scrollPane.setVisible(true);
		for(int i=0; i<us.size(); i++){
			panel.add(new JLabel(us.get(i).getAccount()));
			panel.add(new JLabel("!!!!"));
		}
	}
	public void initDraw(){
		removeAll();
		if(mainApp.getLogin()){
			setLayout(new GridLayout(0,1));
			
			JPanel p = new JPanel(), p2 = new JPanel();
			//p.setLayout(new BorderLayout());
			//p2.setLayout(new BorderLayout());
			
			Label searchT = new Label("Search : ");
			search = new TextField(30);
			sid = new Button("By ID");
			snick = new Button("By Name");
					
			p.add(searchT);
			p.add(search);
			p.add(sid);
			p.add(snick);
			add(p);
			add(scrollPane);
			//scrollPane.setVisible(false);
			
			/*
			searchT.setBounds((w-ww)/2, wy, ww, 30);
			search.setBounds((w-jw)/2, jy, jw, jh);
			sid.setBounds((w/2-bw)/2, by, bw, bh);
			snick.setBounds(w/2+(w/2-bw)/2, by, bw, bh);*/
			//scrollPane.setBounds(300, 200, 300, 100);
			//panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight()*2));
			
			sid.addActionListener(this);
			snick.addActionListener(this);
			
		}else{
			JLabel bg = new JLabel(new ImageIcon("img/logout.jpg"));
			add(bg);
			bg.setBounds(0, 0, w, h);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==sid){						//search by id
			if(!search.getText().equals("")){
				Info info = new Info("searchid", search.getText());
				try {
					mainApp.sentClient(info);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else if(e.getSource()==snick){				//search by name
			if(!search.getText().equals("")){
				Info info = new Info("searchname", search.getText());
				try {
					mainApp.sentClient(info);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
}
