package at.kitsoft.redicraft.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import at.kitsoft.redicraft.mysql.lb.MySQL;

public class Prefix {
	
	public static HashMap<String, String> prefix = new HashMap<>();
	
	public static void onLoad() {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_igprefix");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				prefix.put(rs.getString("type"), rs.getString("prefix"));
			}
			rs.close();
			ps.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public static String prefix(String type){
		return switch(type.toLowerCase()){
			case "main", "prefix" -> prefix.get("main");
			case "scoreboard" -> prefix.get("scoreboard");
			case "pmsystem", "pm" -> prefix.get("pmsys");
			default -> throw new IllegalArgumentException("Unexpected value: " + type.toLowerCase()); 
		};
	}
}