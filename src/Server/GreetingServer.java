package Server;
import java.net.*;
import java.util.List;
import java.io.*;

public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   ServerStatCalculator serverStatCalculator = new ServerStatCalculator();
   
   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(20000);
      serverStatCalculator.Calculate();
   }

   public void run()
   {
	   ServerStat serverStat = new ServerStat(0,0,0);
	   ServerDB b = new ServerDB();
	   
   while(true)
   {
         try
         {
            Socket server = serverSocket.accept();
            DataInputStream in = new DataInputStream(server.getInputStream());
            serverStat.whereWasPlayer = in.readInt();
            serverStat.whereHittedBall = in.readInt();
            System.out.println(serverStat.whereWasPlayer);
            System.out.println(serverStat.whereHittedBall);
                      
	        b.insertServerStat(serverStat.whereWasPlayer,serverStat.whereHittedBall);
	        
	        DataOutputStream out = new DataOutputStream(server.getOutputStream());
	        
	        for(int i=0; i<80; i++)
	        {
	        	out.writeDouble(serverStatCalculator.Stats[i]);
	        }
	        
            server.close();
            
         }
         catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;  
         }
         catch(IOException e)
         {
            e.printStackTrace();
            break;
         }  
         
      }
   List<ServerStat> ServerStats = b.selectServerStats();
   System.out.println("Lista rekordow: ");
   for(ServerStat c: ServerStats)
   System.out.println(c);
   b.closeConnection();
   
   }
   public static void main(String [] args)
   {
      int port = 3555;
      try
      {
         Thread t = new GreetingServer(port);
         t.start();
         
      }catch(IOException e)
      {
         e.printStackTrace();
      }
      
     
   }
}