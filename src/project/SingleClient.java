package project;

import java.io.IOException;

public class SingleClient {
	private static Client client;
	private static String serverAddress = "127.0.0.1";
	
	public static void setServerAddress(String addr){
		serverAddress = addr;
	}
	
	public static Client getClient(){
		if (client == null){
			client = new Client(serverAddress, 8000);
		}
		
		return client;
	}
	
	public static void sent(Object object, ResponseCallback cb){
		Client client = getClient();
		try {
			client.sent(object, cb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sent(Object object){
		Client client = getClient();
		try {
			client.sent(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
