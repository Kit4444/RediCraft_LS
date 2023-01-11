package at.kitsoft.redicraft.event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import at.kitsoft.redicraft.mysql.lb.MySQL;

public class AutoKickerMethods implements Listener{
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		long time = (System.currentTimeMillis() / 1000);
		ScoreboardCLS.afk_timer.put(p, time);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		long time = (System.currentTimeMillis() / 1000);
		ScoreboardCLS.afk_timer.put(p, time);
		ScoreboardCLS.autoAFK.put(p, dl_AFKSetting(p, "setAutoAFK"));
		ScoreboardCLS.staffKick.put(p, dl_AFKSetting(p, "enableAFKkick"));
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		ScoreboardCLS.afk_timer.remove(p);
		ScoreboardCLS.autoAFK.remove(p);
		ScoreboardCLS.staffKick.remove(p);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		long time = (System.currentTimeMillis() / 1000);
		ScoreboardCLS.afk_timer.put(p, time);
	}
	
	private boolean dl_AFKSetting(Player p, String col) {
		boolean boo = false;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT " + col + " FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			boo = rs.getBoolean(col);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}

}
