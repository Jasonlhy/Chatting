package project;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;


public class Database {
	/*
	private String directory;
	private File file;
	private FileWriter fw;
	private BufferedReader br;
	private ArrayList<Shop> shops;*/
	
	private Connection con;
	
	public Database(String d) throws IOException, SQLException{
		
		SQLiteConfig config = new SQLiteConfig();  
		config.setSharedCache(true);
		config.enableRecursiveTriggers(true);
		SQLiteDataSource ds = new SQLiteDataSource(config); 
		ds.setUrl("jdbc:sqlite:"+d+"db");
		con = ds.getConnection();
		createTable();
	}
	public void createTable()throws SQLException{
		Statement stat = con.createStatement();
		
        String sql = "create table if not exists user (id string, pw string, stat string);";
        stat.executeUpdate(sql);
        sql = "create table if not exists friend (id string, fid string);";
        stat.executeUpdate(sql);
        //sql = "create table if not exists request (id string, fid string);";
        //stat.executeUpdate(sql);
    }
	public ArrayList<User> readUsers() throws SQLException{
		return readU("select * from user;", 0);
	}
	public ArrayList<User> readU(String sql, int k) throws SQLException{
		Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<User> us = new ArrayList<>();
        while(rs.next())
        {
        	User user;
        	if(k==0)
        		user = new User(rs.getString("id"), rs.getString("pw"), rs.getString("stat"));
        	else
        		user = new User(rs.getString("id"), "", rs.getString("stat"));
        	us.add(user);
        }return us;
	}
	public void addUser(User u)throws SQLException{
		Statement stat = con.createStatement();
		
        String sql = "insert into user values(\""+u.getAccount()+"\", \""+u.getPassword()+"\", '');";
        stat.executeUpdate(sql);
    }
	public ArrayList<User> getFriendList(String id) throws SQLException{
		return readU("select * from user where id in (select fid from friend where id=\""+id+"\");", 1);
	}
	public ArrayList<User> getSearchID(String id)  throws SQLException{
		return readU("select * from user where id like '%"+id+"%';", 0);
	}
	public void setUserFile(User u) throws SQLException{
		Statement stat = con.createStatement();
		String sql = "update user set pw='"+u.getPassword()+"', stat='"+u.getStat()+"' where id='"+u.getAccount()+"';";
		int count = stat.executeUpdate(sql);
		System.out.println("count: " + count);
	}
	public void sentRequest(String s1, String s2) throws SQLException{
		Statement stat = con.createStatement();
		String sql = "insert into friend values('"+s1+"', '"+s2+"');";
		stat.executeUpdate(sql);
		sql = "insert into friend values('"+s2+"', '"+s1+"');";
		stat.executeUpdate(sql);
	}
}
