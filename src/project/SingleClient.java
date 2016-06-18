package project;

public class SingleClient {
	private static Client client;
	
	public static Client getClient(){
		if (client == null){
			client = new Client("140.114.86.105", 8000);
		}
		
		return client;
	}
	
	
}
