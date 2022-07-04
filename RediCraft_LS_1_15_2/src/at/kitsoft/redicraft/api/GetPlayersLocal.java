package at.kitsoft.redicraft.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import at.kitsoft.redicraft.mysql.lb.MySQL;


public class GetPlayersLocal {
	
	public static int getPlayers(String server, String type) {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_serverstats WHERE servername = ?");
			ps.setString(1, server);
			ResultSet rs = ps.executeQuery();
			rs.next();
			i = rs.getInt(type);
			rs.close();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); }
		return i;
	}

}
