package project;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame{
	
	private int width = 300, height = 400;
	private static final long serialVersionUID = 1L;
	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	private JTextArea textArea;
	private static Database db;
	
	private static ArrayList<User> users;
	private static ArrayList<String> onlineUser;
	private static Hashtable<String, ConnectionThread> userToAddr = new Hashtable<>();
	
    public static void main(String[] args) throws IOException, SQLException {	
    	Server server = new Server(8000);
    	db = new Database("");
    	users = db.readUsers();
    	for(int i=0; i<users.size(); i++){
    		server.show(users.get(i).getAccount());
    		
    	}
    	onlineUser = new ArrayList<>();
		server.runForever();
	}
    
    public Server(int portNum) {

		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(this.textArea);
	    this.add(scrollPane);
	    
	    setTitle("Server");
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(width, height);
	    Toolkit t = Toolkit.getDefaultToolkit();
	    setLocation((int)t.getScreenSize().getWidth()-width-20, (int)t.getScreenSize().getHeight()-height-50);
	    setVisible(true);
	
	    try {
			this.serverSocket = new ServerSocket(portNum);
			show("Server starts listening on port "+portNum); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void runForever() {
		show("Server starts waiting for client.");
        while(true){
			try {
				Socket connectToClient = this.serverSocket.accept();
				show("Get connect from client "+ connectToClient.getInetAddress()+" : "+ connectToClient.getPort());
				ConnectionThread conThread = new ConnectionThread(connectToClient);
				connections.add(conThread);
				conThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	private void show(String s){
		textArea.append(s+"\n");
	}
	
	private void broadcast(Object o) throws IOException{
		for (ConnectionThread connection: this.connections) {
			connection.sendMessage(o);
		}
	}
	
	class ConnectionThread extends Thread{
		
		private Socket socket;
		private ObjectInputStream reader;
		private ObjectOutputStream write;
		private String name;
		
		public ConnectionThread(Socket s){
			this.socket = s;
			try{
				this.reader = new ObjectInputStream(socket.getInputStream());
				this.write = new ObjectOutputStream(socket.getOutputStream());
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		public void sendMessage(Object o) throws IOException {
			this.write.writeObject(o);
			this.write.reset();;
		}
		public void run(){
			/*
			try {
				
				
				//sendMessage(shops);
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			while(true){
				try {
					Object o = this.reader.readObject();
					
					if(o instanceof Info){
						Info l = (Info) o;
						String s = l.getInfo();
						String s2 = l.getInfo2();
						String s3 = l.getInfo3();
						String s4 = l.getInfo4();
						if(s.equals("login")){				//login
							User u2 = l.getUser();
							int index = User.getIndex(users, u2);
							
							if(index!=-1&&users.get(index).getPassword().equals(u2.getPassword())){        //successful
								addUser(u2.getAccount());
								show(name+" log in at "+ socket.getInetAddress()+" : "+ socket.getPort());
								sendMessage(new Info("login", u2));
								/*
								if(onlineUser.contains(u2.getAccount())){
									sendMessage(new Info("loginer", "The account has been logged in."));
								}else{
									addUser(u2.getAccount());
									show(name+" log in at "+ socket.getInetAddress()+" : "+ socket.getPort());
									sendMessage(new Info("login", u2));
								}*/
							}else if(index!=-1&&!users.get(index).getPassword().equals(u2.getPassword())){
								sendMessage(new Info("loginer", "WRONG password"));
							}else if(index==-1){
								sendMessage(new Info("loginer", "no THIS user"));
							}
						}else if(s.equals("create")){		//create account
							User u2 = l.getUser();
							int index = User.getIndex(users, u2);
							
							if(index != -1){
								sendMessage(new Info("loginer", "The user id has been USED."));
							}else{
								show("The user "+u2.getAccount()+" has been create.");
								show(u2.getAccount()+" log in.");
								
								db.addUser(u2);
								users.add(u2);
								addUser(u2.getAccount());
								sendMessage(new Info("login", u2));
							}
							
						}else if(s.equals("out")){			//logout
							show(name+" log out.");
							onlineUser.remove(name);
							userToAddr.remove(name);
							name = null;
						}else if(s.equals("friend")){
							sendMessage(new Info("friend", db.getFriendList(s2)));
						}else if(s.equals("searchid")){
							sendMessage(new Info("searchid", db.getSearchID(s2)));
						}else if(s.equals("request")){
							db.sentRequest(s2, s3);
							sendMessage(new Info("friend", db.getFriendList(s2)));
							if(onlineUser.contains(s3)){
								userToAddr.get(s3).sendMessage(new Info("friend", db.getFriendList(s3)));
							}
						}else if(s.equals("searchname")){
							ArrayList<User> us = db.readU("select * from user where name like '%"+s2+"%';");
							sendMessage(new Info("searchname", us));
						}else if(s.equals("setuser")){
							db.setUserFile(l.getUser());
							sendMessage(new Info("renewuser", l.getUser()));
						}
					}
				} catch(SocketException e){
					try {
						if(name!=null){
							show(name+" log out.");
							onlineUser.remove(name);
							userToAddr.remove(name);
						}
						show("Lost connect from client "+ socket.getInetAddress()+" : "+ socket.getPort());
						reader.close();
						write.close();
						connections.remove(this);
						break;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException | IOException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		private void addUser(String n){
			name = n;
			onlineUser.add(name);
			userToAddr.put(name, this);
		}
    }
	
}
