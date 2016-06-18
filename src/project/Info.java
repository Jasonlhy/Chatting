package project;

import java.io.Serializable;
import java.util.ArrayList;

/*----------------------------
 stat
 0 = login;
 
 -----------------------------*/

public class Info implements Serializable{

	private static final long serialVersionUID = 1L;
	private String information = "";
	private String information2 = "";
	private String information3 = "";
	private String information4 = "";
	private String information5 = "";
	private User u;
	public ArrayList<User> us;
	public ArrayList<String> strs;
	
	public Info(String info, String info2){
		information = info;
		information2 = info2;
	}
	public Info(String info, String info2, String info3){
		information = info;
		information2 = info2;
		information3 = info3;
	}
	public Info(String info, String info2, String info3, String info4){
		information = info;
		information2 = info2;
		information3 = info3;
		information4 = info4;
	}
	public Info(String info, String info2, String info3, String info4, String info5){
		information = info;
		information2 = info2;
		information3 = info3;
		information4 = info4;
		information5 = info5;
	}
	public Info(String info, String u2, ArrayList<String> infos){
		information = info;
		information2 = u2;
		strs = infos;
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
	public String getInfo3(){
		return information3;
	}
	public String getInfo4(){
		return information4;
	}
	public String getInfo5(){
		return information5;
	}
	public User getUser(){
		return u;
	}
	public ArrayList<User> getUsers(){
		return us;
	}
	public ArrayList<String> getChat(){
		return strs;
	}
}
