package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ServerDB {

	public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:biblioteka.db";
 
    private Connection conn;
    private Statement stat;
    
    public ServerDB() {
        try {
            Class.forName(ServerDB.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
 
        createTables();
        
        
    }
    
    public boolean createTables()  {
        String createServerStat = "CREATE TABLE IF NOT EXISTS ServerStat (Id INTEGER PRIMARY KEY AUTOINCREMENT, whereWasPlayer int, whereHittedBall int)";
      
        try {
            stat.execute(createServerStat);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean insertServerStat(int whereWasPlayer, int whereHittedBall) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into ServerStat values (NULL, ?, ?);");
            prepStmt.setInt(1, whereWasPlayer);
            prepStmt.setInt(2, whereHittedBall);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu rekordu");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<ServerStat> selectServerStats() {
        List<ServerStat> ServerStats = new LinkedList<ServerStat>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM ServerStat");
            int whereWasPlayer;
            int whereHittedBall;
            int Id;
            while(result.next()) {
                Id = result.getInt("Id");
                whereWasPlayer = result.getInt("whereWasPlayer");
                whereHittedBall = result.getInt("whereHittedBall");
           
                ServerStats.add(new ServerStat(whereWasPlayer,whereHittedBall,Id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ServerStats;
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
    
    
    
}
