package project;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class MainApp extends PApplet{
	
	private static final long serialVersionUID = 1L;
	private Client client;
	private int status;                             //0:Map;   1:Game;   2:Operating
	private int appWid = 900;
	private int appHei = 700;
	public Friend friend;
	private Operating operating;
	private boolean read;
	private boolean login = false;
	
	public void setup(){
		setSize(appWid, appHei);
		setLayout(null);
		
		friend = new Friend(this);
		operating = new Operating(this);
		read = true;
	}
	public boolean finRead(){
		return read;
	}
	
	public void draw(){
		if(status==0){
			friend.close();
			operating.close();
		}else if(status==1){
			operating.close();
			friend.draw();
		}else if(status==2){
			friend.close();
			operating.draw();
		}
	}
	public void setApp(int status){
		this.status = status;
	}
	public void setClient(Client c){
		client = c;
	}
	public void sentClient(Object o) throws IOException{
		client.sent(o);
	}
	public void setOpeInfo(User u){
		operating.setInfo(u);
	}
	public void setOpeInfo(String s){
		operating.setInfo(s);
	}
	public void login(boolean in) throws IOException{
		login = in;
		if(!in)
		    sentClient(new Info("out", ""));
		friend.initDraw();
	}
	public int getW(){
		return appWid;
	}
	public int getH(){
		return appHei;
	}
	public boolean getLogin(){
		return login;
	}
}
