package at.mlps.rc.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import at.mlps.rc.mysql.lb.MySQL;

public class Prefix {
	
	public static HashMap<String, String> prefix = new HashMap<>();
	
	public static void onLoad() {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_igprefix");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				prefix.put(rs.getString("type"), rs.getString("prefix"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static String prefix(String type) {
		String s = "";
		if(type.equalsIgnoreCase("main") || type.equalsIgnoreCase("prefix")) {
			s = prefix.get("main");
		}else if(type.equalsIgnoreCase("scoreboard")) {
			s = prefix.get("scoreboard");
		}else if(type.equalsIgnoreCase("pmsystem") || type.equalsIgnoreCase("pm")) {
			s = prefix.get("pmsys");
		}
		return s;
	}
}