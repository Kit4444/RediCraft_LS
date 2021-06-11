package at.mlps.rc.event;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import at.mlps.rc.api.GetBukkitInfo;
import at.mlps.rc.api.Prefix;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class JoinQuitEventID implements Listener{
	
	static File spawn = new File("plugins/RCLS/spawn.yml");
	
	@EventHandler
	public void onSpawnJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		p.setGameMode(GameMode.SURVIVAL);
		Main.setPlayerBar(p);
		p.setFoodLevel(20);
		p.setHealth(20.0);
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(spawn);
		if(p.hasPlayedBefore()) {
			p.teleport(retLoc(cfg, "general"));
		}else {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {
					p.teleport(retLoc(cfg, "general"));
				}
			}, 10);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws SQLException {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		int id = random(1, 99999);
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        String stime = time.format(new Date());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        HashMap<String, Object> player = new HashMap<>();
        player.put("uuid", uuid);
        GetBukkitInfo bukkit = new GetBukkitInfo();
        try {
        	if(!Main.mysql.isInDatabase("redicore_userstats", player)) {
        		player.put("userrank", "default");
        		player.put("userid", 0);
        		player.put("userprefix", "RESET");
        		player.put("userprefix_ncc", "RESET");
        		player.put("uuid_ut", p.getUniqueId().toString());
        		player.put("username", p.getName());
        		player.put("firstjoints", ts.getTime());
        		player.put("firstjoinstring", stime);
        		player.put("firstjoinip", p.getAddress().getHostString());
        		player.put("server", bukkit.getServerName());
        		player.put("loggedin", false);
        		player.put("isstaff", false);
        		player.put("language", "en-UK");
        		player.put("scoreboard", 1);
        		Main.mysql.insertInto("redicore_userstats", player);
        		PreparedStatement idps = MySQL.getConnection().prepareStatement("SELECT userid FROM redicore_userstats WHERE userid = ?");
        		idps.setInt(1, id);
        		ResultSet idrs = idps.executeQuery();
        		if(!idrs.next()) {
        			insertID(id, uuid);
        		}
        	}else {
        		PermissionUser pu = PermissionsEx.getUser(p);
        		PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET userrank = ?, online = ?, server = ?, lastjoints = ?, lastjoinstring = ?, lastloginip = ?, isstaff = ? WHERE uuid = ?");
        		if(pu.inGroup("PMan")) {
	        		ps.setString(1, "Projectmanager");
	        	}else if(pu.inGroup("CMan")) {
	        		ps.setString(1, "Community Manager");
	        	}else if(pu.inGroup("AMan")) {
	        		ps.setString(1, "Game Moderation Manager");
	        	}else if(pu.inGroup("Developer")) {
	        		ps.setString(1, "Developer");
	        	}else if(pu.inGroup("Admin")) {
	        		ps.setString(1, "Game Moderator");
	        	}else if(pu.inGroup("Mod")) {
	        		ps.setString(1, "Moderator");
	        	}else if(pu.inGroup("Support")) {
	        		ps.setString(1, "Support");
	        	}else if(pu.inGroup("Translator")) {
	        		ps.setString(1, "Content");
	        	}else if(pu.inGroup("Builder")) {
	        		ps.setString(1, "Builder");
	        	}else if(pu.inGroup("RLTM")) {
	        		ps.setString(1, "Retired Legend Team Member");
	        	}else if(pu.inGroup("RTM")) {
	        		ps.setString(1, "Retired Team Member");
	        	}else if(pu.inGroup("Partner")) {
	        		ps.setString(1, "Partner");
	        	}else if(pu.inGroup("Beta")) {
	        		ps.setString(1, "Beta-Tester");
	        	}else if(pu.inGroup("Patron")) {
	        		ps.setString(1, "Patron");
	        	}else if(pu.inGroup("NitroBooster")) {
	        		ps.setString(1, "Nitrobooster");
	        	}else if(pu.inGroup("Friend")) {
	        		ps.setString(1, "Friend");
	        	}else {
	        		ps.setString(1, "Player");
	        	}
        		ps.setBoolean(2, true);
        		ps.setString(3, bukkit.getServerName());
        		ps.setLong(4, ts.getTime());
        		ps.setString(5, stime);
        		ps.setString(6, p.getAddress().getHostString());
        		if(p.hasPermission("mlps.isStaff")) {
        			ps.setBoolean(7, true);
        		}else {
        			ps.setBoolean(7, false);
        		}
        		ps.setString(8, uuid);
        		ps.executeUpdate();
        		ps.close();
        	}
        }catch (SQLException ex) { ex.printStackTrace(); }
        if(p.hasPermission("mlps.isSA")) {
        	String isVer = Main.instance.getDescription().getVersion();
            String shouldVer = retVersion();
            if(!isVer.equalsIgnoreCase(shouldVer)) {
            	p.sendMessage(Prefix.prefix("main") + "§cInfo, the Version you use is different to the DB.");
            	p.sendMessage("§aServerversion§7: " + isVer);
            	p.sendMessage("§cDB-Version§7: " + shouldVer);
            }
        }
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        String stime = time.format(new Date());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        PermissionUser pu = PermissionsEx.getUser(p);
        try {
        	PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET userrank = ?, lastjoints = ?, lastjoinstring = ?, lastloginip = ?, online = ? WHERE uuid = ?");
        	if(pu.inGroup("PMan")) {
        		ps.setString(1, "Projectmanager");
        	}else if(pu.inGroup("CMan")) {
        		ps.setString(1, "Community Manager");
        	}else if(pu.inGroup("AMan")) {
        		ps.setString(1, "Game Moderation Manager");
        	}else if(pu.inGroup("Developer")) {
        		ps.setString(1, "Developer");
        	}else if(pu.inGroup("Admin")) {
        		ps.setString(1, "Game Moderator");
        	}else if(pu.inGroup("Mod")) {
        		ps.setString(1, "Moderator");
        	}else if(pu.inGroup("Support")) {
        		ps.setString(1, "Support");
        	}else if(pu.inGroup("Translator")) {
        		ps.setString(1, "Content");
        	}else if(pu.inGroup("Builder")) {
        		ps.setString(1, "Builder");
        	}else if(pu.inGroup("RLTM")) {
        		ps.setString(1, "Retired Legend Team Member");
        	}else if(pu.inGroup("RTM")) {
        		ps.setString(1, "Retired Team Member");
        	}else if(pu.inGroup("Partner")) {
        		ps.setString(1, "Partner");
        	}else if(pu.inGroup("Beta")) {
        		ps.setString(1, "Beta-Tester");
        	}else if(pu.inGroup("Patron")) {
        		ps.setString(1, "Patron");
        	}else if(pu.inGroup("NitroBooster")) {
        		ps.setString(1, "Nitrobooster");
        	}else if(pu.inGroup("Friend")) {
        		ps.setString(1, "Friend");
        	}else {
        		ps.setString(1, "Player");
        	}
        	ps.setLong(2, ts.getTime());
        	ps.setString(3, stime);
        	ps.setString(4, p.getAddress().getHostString());
        	ps.setBoolean(5, false);
        	ps.setString(6, uuid);
        	ps.executeUpdate();
        	ps.close();
        }catch (SQLException ex) { ex.printStackTrace(); }
	}
	
	private void insertID(int id, String uuid) throws SQLException {
		PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET userid = ? WHERE uuid = ?");
		ps.setInt(1, id);
		ps.setString(2, uuid);
		ps.executeUpdate();
		ps.close();
	}
	
	private int random(int low, int max) {
		Random r = new Random();
		int number = r.nextInt(max);
		while(number < low) {
			number = r.nextInt(max);
		}
		return number;
	}

	private Location retLoc(YamlConfiguration cfg, String type) {
		Location loc = new Location(Bukkit.getWorld(cfg.getString("Spawn." + type + ".WORLD")), cfg.getDouble("Spawn." + type + ".X"), cfg.getDouble("Spawn." + type + ".Y"), cfg.getDouble("Spawn." + type + ".Z"), (float)cfg.getDouble("Spawn." + type + ".YAW"), (float)cfg.getDouble("Spawn." + type + ".PITCH"));
		return loc;
	}
	
	private String retVersion() {
		String s = "";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT version FROM redicore_versions WHERE type = ?");
			ps.setString(1, "game");
			ResultSet rs = ps.executeQuery();
			rs.next();
			s = rs.getString("version");
			rs.close();
			ps.close();
		}catch (SQLException ex) { ex.printStackTrace(); }
		return s;
	}
}