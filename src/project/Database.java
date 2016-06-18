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
		
        String sql = "create table if not exists user (id string, pw string);";
        stat.executeUpdate(sql);
        sql = "create table if not exists friend (id string, fid string);";
        stat.executeUpdate(sql);
        sql = "create table if not exists request (id string, fid string);";
        stat.executeUpdate(sql);
    }
	public ArrayList<User> readUsers() throws SQLException{
		return readU("select * from user;");
	}
	public ArrayList<User> readU(String sql) throws SQLException{
		Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<User> us = new ArrayList<>();
        while(rs.next())
        {
        	User user = new User(rs.getString("id"), rs.getString("pw"));
        	us.add(user);
        }return us;
	}
	public void addUser(User u)throws SQLException{
		Statement stat = con.createStatement();
		
        String sql = "insert into user values(\""+u.getAccount()+"\", \""+u.getPassword()+"\");";
        stat.executeUpdate(sql);
    }
	
}
