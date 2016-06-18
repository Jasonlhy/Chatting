package project;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Operating extends Panel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private MainApp mainApp;
	private TextField acField;
	private TextField pwField;
	private Button enter;
	private Button create;
	private Button toCreate;
	private Button back;
	private Button logOut;
	private Choice typeChoice;
	private Label[] msg = new Label[3];
	private User user;
	private int w, h;
	private int jw = 200, jh = 30, wy = 30, jy = 70, bw = 150, bh = 30, by = 120, sw = 200, sy = 200;
	
	public Operating(MainApp main){
		mainApp = main;
		w = main.getW();
		h = main.getH();
		setup();
	}
    public void setInfo(User u){
		user = u;
		removeAll();
		Label l = new Label("You are "+user.getAccount());
		add(l);
		l.setBounds(330, 20, 100, 20);
		logOut = new Button("Log out");
		add(logOut);
		logOut.setBounds(310, 40, 100, 20);
		logOut.addActionListener(this);
		try {
			mainApp.login(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void setInfo(String s){
    	removeAll();
		Label l = new Label(s);
		add(l);
		l.setBounds((w-sw)/2, 120, sw, sy);
		initDraw();
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
	private void initDraw(){
		newText();
		enter = new Button("Enter");
		toCreate = new Button("Create a new account!");
		add(enter);
		add(toCreate);
		enter.setBounds((w/2-bw)/2, by, bw, bh);
		toCreate.setBounds(w/2+(w/2-bw)/2, by, bw, bh);
		enter.addActionListener(this);
		toCreate.addActionListener(this);
	}
	private void newText(){
		msg[0] = new Label("Account : ");
		msg[1] = new Label("Password : ");
		acField = new TextField(20);
		pwField = new TextField(20);
		pwField.setEchoChar('*');
		
		add(msg[0]);
		add(acField);
		add(msg[1]);
		add(pwField);
		
		msg[0].setBounds((w/2-jw)/2, wy, jw, jh);
		msg[1].setBounds(w/2+(w/2-jw)/2, wy, jw, jh);
		acField.setBounds((w/2-jw)/2, jy, jw, jh);
		pwField.setBounds(w/2+(w/2-jw)/2, jy, jw, jh);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource()==enter){
	    	enterEvent(0);
	    }else if(e.getSource()==toCreate){
	    	enterEvent(1);
		}else if(e.getSource()==logOut){
			removeAll();
			initDraw();
			try {
				mainApp.login(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	private void enterEvent(int k){
		if(!acField.getText().equals("")&&!pwField.getText().equals("")
		   &&!acField.getText().equals(null)&&!pwField.getText().equals(null)
		   &&!acField.getText().contains(" ")&&!pwField.getText().contains(" ")){
			
    		try {
				if(k==0){					//enter
					User u = new User(acField.getText(), pwField.getText());
					Info l = new Info("login", u);
					mainApp.sentClient(l);
				}else{						//create
					User u = new User(acField.getText(), pwField.getText());
					Info l = new Info("create", u);
					mainApp.sentClient(l);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else {
			setInfo("The text can't be or contain blank.");
		}
	}
}
