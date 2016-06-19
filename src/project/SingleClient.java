package project;

import java.io.IOException;

public class SingleClient {
	private static Client client;
	
	public static Client getClient(){
		if (client == null){
			client = new Client("140.114.86.105", 8000);
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
