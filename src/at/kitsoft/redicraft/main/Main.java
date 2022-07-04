package at.kitsoft.redicraft.main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import at.kitsoft.redicraft.api.GetBukkitInfo;
import at.kitsoft.redicraft.api.ItemsAPI;
import at.kitsoft.redicraft.api.Prefix;
import at.kitsoft.redicraft.event.ExtrasInv;
import at.kitsoft.redicraft.event.LanguageInv;
import at.kitsoft.redicraft.event.Navigator;
import at.kitsoft.redicraft.mysql.lpb.MySQL;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Manager manager = new Manager();
		manager.init();
		updateOnline(true);
	}
	
	/*public static String prefix() {
		return Prefix.prefix("main");
	}*/
	public static String mysqlprefix = "§eMySQL §7- ";
	public static String consolesend = Prefix.prefix("main") + "§7Please use this ingame.";
	public static Main instance;
	public static MySQL mysql;
	
	public void onDisable() {
		updateOnline(false);
		instance = null;
		try {
			mysql.disconnect();
			at.kitsoft.redicraft.mysql.lb.MySQL.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setPlayerBar(Player p) {
		p.getInventory().clear();
		ItemsAPI iapi = new ItemsAPI();
		p.getInventory().setItem(2, iapi.defItem(Material.DIAMOND, 1, ExtrasInv.mainTitle));
		p.getInventory().setItem(4, iapi.defItem(Material.COMPASS, 1, Navigator.title));
		p.getInventory().setItem(6, iapi.defItem(Material.BOOK, 1, LanguageInv.mainTitle));
	}
	
	private void updateOnline(boolean boo) {
		GetBukkitInfo bukkit = new GetBukkitInfo();
		try {
			PreparedStatement ps = at.kitsoft.redicraft.mysql.lb.MySQL.getConnection().prepareStatement("UPDATE redicore_serverstats SET online = ? WHERE servername = ?");
			ps.setBoolean(1, boo);
			ps.setString(2, bukkit.getServerName());
			ps.executeUpdate();
			ps.close();
			if(boo == true) {
				PreparedStatement ps1 = at.kitsoft.redicraft.mysql.lb.MySQL.getConnection().prepareStatement("UPDATE redicore_serverstats SET onlinesince = ? WHERE servername = ?");
				SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
				ps1.setString(1, time.format(new Date()));
				ps1.setString(2, "Lobby");
				ps1.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}