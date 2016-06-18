package project;

import java.io.Serializable;
import java.util.ArrayList;

/*----------------------------
 stat
 0 = login;
 
 -----------------------------*/

public class Info implements Serializable{

	private static final long serialVersionUID = 1L;
	private String information;
	private String information2;
	private User u;
	public ArrayList<User> us;
	
	public Info(String info, String info2){
		information = info;
		information2 = info2;
	}
	public Info(String info, User u2){
		information = info;
		u = u2;
	}
	public Info(String info, ArrayList<User> u2){
		information = info;
		us = u2;
	}
	public String getInfo(){
		return information;
	}
	public String getInfo2(){
		return information2;
	}
	public User getUser(){
		return u;
	}
	public ArrayList<User> getUsers(){
		return us;
	}
}
