package at.kitsoft.redicraft.mysql.lb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import at.kitsoft.redicraft.main.Main;

public class MySQL {
	
	static Connection con;
	
	public static void connect(String host, String port, String db, String user, String pw) {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true", user, pw);
				Bukkit.getConsoleSender().sendMessage(Main.mysqlprefix + "§aConnected successfully.");
			}catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(Main.mysqlprefix + "§cCouldn't connect to DB-Server. Check credentials.");
			}
		}
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				Bukkit.getConsoleSender().sendMessage(Main.mysqlprefix + "§aDisconnected successfully.");
			}catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(Main.mysqlprefix + "§cCouldn't disconnect from DB-Server.");
			}
		}
	}
	
	public static boolean isConnected() {
		return (con != null);
	}
	
	public static Connection getConnection() {
		return con;
	}

}
