package Server;

import java.util.List;

public class ServerStatCalculator {
 
	public Double[] Stats = new Double[80];
	
	 public void Calculate()
	 {
		 ServerStat serverStat = new ServerStat(0,0,0);
		 ServerDB b = new ServerDB();
		 List<ServerStat> ServerStats = b.selectServerStats();
		 
		 for(int i=0; i<80; i++)
		 {	
				 Stats[i] = 0.0d;
		 }
		 
	   for(ServerStat c: ServerStats)
	   {
		   Stats[c.whereWasPlayer/10]=(Stats[c.whereWasPlayer/10]+c.whereHittedBall)/2;
	   }
	   
	   for(int i=0; i<80; i++)
		 {
			
				 System.out.println("[" + i + "]" + ":" + Stats[i]);
		 }
	   
	   b.closeConnection();
	 }
}
