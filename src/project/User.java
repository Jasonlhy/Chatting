package project;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String account = "";
	private String password = "";
	private String stat = "";
	private boolean know = true;
	//private ArrayList<User> freind;
	
	public User(String id, String pw){
		//String[] info = str.split(" ");
		account = id;
		password = pw;
		//freind = new ArrayList<>();
	}
	

	public User(String id, String pw, String st){
		account = id;
		password = pw;
		stat = st;
	}
	public User(String id, String pw, String st, boolean b){
		account = id;
		password = pw;
		stat = st;
		know = b;
	}
	public String getAccount(){
		return account;
	}
	public String getPassword(){
		return password;
	}
	public String getStat(){
		return stat;
	}
	public void setPassword(String p){
		password = p;
	}
	public void setStat(String s){
		stat = s;
	}
	public boolean getKnow(){
		return know;
	}
	public static int getIndex(ArrayList<User> us, User u){
		for(int i=0; i<us.size(); i++){
			if(us.get(i).getAccount().equals(u.getAccount())){
				return i;
			}
		}return -1;
	}
	public static int getIndex(ArrayList<User> us, String u){
		for(int i=0; i<us.size(); i++){
			if(us.get(i).getAccount().equals(u)){
				return i;
			}
		}return -1;
	}
}
