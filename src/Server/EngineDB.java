package Server;

import java.util.List;

public class EngineDB {

	  public static void main(String[] args) {
	        ServerDB b = new ServerDB();
	        b.insertServerStat(100,100);
	        b.insertServerStat(200,200);
	        b.insertServerStat(300,300);
	     
	        List<ServerStat> ServerStats = b.selectServerStats();
	        
	        System.out.println("Lista rekordow: ");
	        for(ServerStat c: ServerStats)
	            System.out.println(c);
	 
	        b.closeConnection();
	        
	  }
	
}
