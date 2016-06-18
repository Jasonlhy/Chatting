package project;

public class SingleClient {
	private static Client client;
	
	public static Client getClient(){
		if (client == null){
			client = new Client("127.0.0.1", 8000);
		}
		
		return client;
	}
	
	
}
