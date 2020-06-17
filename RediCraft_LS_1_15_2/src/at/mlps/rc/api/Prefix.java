package at.mlps.rc.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import at.mlps.rc.mysql.lb.MySQL;

public class Prefix {

	public static String prefix(String type) {
		String s = "";
		if(MySQL.isConnected()) {
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_igprefix WHERE type = ?");
				if(type.equalsIgnoreCase("main")) {
					ps.setString(1, "main");
					ResultSet rs = ps.executeQuery();
					rs.next();
					s = rs.getString("prefix");
					rs.close();
					ps.close();
				}else if(type.equalsIgnoreCase("scoreboard")) {
					ps.setString(1, "scoreboard");
					ResultSet rs = ps.executeQuery();
					rs.next();
					s = rs.getString("prefix");
					rs.close();
					ps.close();
				}else if(type.equalsIgnoreCase("pmsystem")) {
					ps.setString(1, "pmsys");
					ResultSet rs = ps.executeQuery();
					rs.next();
					s = rs.getString("prefix");
					rs.close();
					ps.close();
				}else {
					ps.setString(1, type);
					ResultSet rs = ps.executeQuery();
					rs.next();
					s = rs.getString("prefix");
					rs.close();
					ps.close();
				}
			}catch (SQLException e) { e.printStackTrace(); }
		}else {
			if(type.equalsIgnoreCase("prefix")) {
				s = "prefix1";
			}else if(type.equalsIgnoreCase("scoreboard")) {
				s = "prefix2";
			}else if(type.equalsIgnoreCase("pmsystem")) {
				s = "prefix3";
			}else {
				s = "prefix4";
			}
		}
		return s;
	}
}
