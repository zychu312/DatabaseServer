package Server;

public class ServerStat {

	int whereWasPlayer;
	int whereHittedBall;
	int Id;
	
	//static int counter = 0;
	
	ServerStat(int whereWasPlayer, int whereHittedBall, int Id)
	{
		this.whereHittedBall = whereHittedBall;
		this.whereWasPlayer = whereWasPlayer;
		this.Id = Id;
		//counter++;	
	}
	
	 public String toString() {
	        return "["+Id+"] - "+whereWasPlayer+" "+whereHittedBall+"";
	    }
	
}
