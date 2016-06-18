package project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


/*-----------------------------------------------------
login 			=> new Info("login", new User(...));
create account	=> new Info("create", new User(...));
logout			=> new Info("out", "");
friend list		=> new Info("friend", id);
search id		=> new Info("searchid", id);
set user file	=> new Info("setuser", new User(...));
-------------------------------------------------------*/

public class Client {
	
	private String desIPAddr;
	private int desPortNum;
	private Socket socket;
	private ObjectOutputStream writer;
	private User user;
	private ResponseCallback currentCallback;
	
	public Client(String IPAddress, int portNum) {
		this.desIPAddr = IPAddress;
		this.desPortNum = portNum;
		connect();
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
	
	public void sent(Object o, ResponseCallback cb) throws IOException{
    	writer.writeObject(o);
    	writer.reset();
    	currentCallback = cb;
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
						
						if(s1.equals("loginer")){					//login error message
							//s2 (error message)
							System.out.println(s2);
							System.out.println("l: " + l);
							currentCallback.failedResponse(l);
						}else if(s1.equals("login")){				//login successfully, with the user
							//l.getUser()
							System.out.println(l.getUser().getAccount());
							currentCallback.successResponse(l);
						}else if(s1.equals("searchid")){			//search user by id
							//l.getUsers()
							
						}else if(s1.equals("friend")){				//receive friend list
							//l.getUsers()
						}else if(s1.equals("renewuser")){			//renew user file
							//l.getUser()
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
