package project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Client {
	
	private String desIPAddr;
	private int desPortNum;
	private Socket socket;
	private ObjectOutputStream writer;
	private MainApp mainApp;
	private User user;
	
	public Client(String IPAddress, int portNum) {
		this.desIPAddr = IPAddress;
		this.desPortNum = portNum;
		connect();
	}
	
	public void setMainApp(MainApp main){
		mainApp = main;
	}
	
	private void connect() {
		try {
			this.socket = new Socket(this.desIPAddr, this.desPortNum);
			this.writer = new ObjectOutputStream(this.socket.getOutputStream());
			ObjectInputStream r = new ObjectInputStream(this.socket.getInputStream());
			ClientThread con = new ClientThread(r);
			con.start();
		} catch (ConnectException e) {
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sent(Object o) throws IOException{
    	writer.writeObject(o);
    	writer.reset();
    }
    class ClientThread extends Thread{
		
        private ObjectInputStream reader;
		
		public ClientThread(ObjectInputStream r){
			this.reader = r;
		}
		public void run(){
			while(true){
				if(socket.isInputShutdown())
					System.exit(-1);
				try{			
					Object o = this.reader.readObject();
					if(o instanceof Info){
						Info l = (Info) o;
						String s1 = l.getInfo();
						String s2 = l.getInfo2();
						
						if(s1.equals("loginer"))
							mainApp.setOpeInfo(s2);
						else if(s1.equals("login"))
							mainApp.setOpeInfo(l.getUser());
						else if(s1.equals("searchid")){
							mainApp.friend.setSearch(l.getUsers());
						}else if(s1.equals("searchname")){
							
						}
					}
				} catch(SocketException e){
					try {
						reader.close();
						writer.close();
						System.exit(-1);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}catch(IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
    }
}
