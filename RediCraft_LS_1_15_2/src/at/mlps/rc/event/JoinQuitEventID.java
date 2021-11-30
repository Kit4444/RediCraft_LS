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
        		player.put("userrank", "Player");
        		player.put("userid", 0);
        		player.put("userprefix", "RESET");
        		player.put("userprefix_ncc", "RESET");
        		player.put("uuid_ut", p.getUniqueId().toString());
        		player.put("username", p.getName());
        		player.put("firstjoints", ts.getTime());
        		player.put("firstjoinstring", stime);
        		player.put("firstjoinip", p.getAddress().getHostString());
        		player.put("server", bukkit.getServerName());
        		player.put("loggedin", true);
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
        		PermissionUser po = PermissionsEx.getUser(p);
        		PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET userrank = ?, rankcolor = ?, online = ?, server = ?, lastjoints = ?, lastjoinstring = ?, lastloginip = ?, isstaff = ? WHERE uuid = ?");
        		if(po.inGroup("pman")) {
        			ps.setString(1, "Project Manager");
        			ps.setString(2, "#5555ff");
    			}else if(po.inGroup("cman")) {
    				ps.setString(1, "Community Manager");
        			ps.setString(2, "#00aa00");
    			}else if(po.inGroup("gmmman")) {
    				ps.setString(1, "Game Mod. Manager");
        			ps.setString(2, "#aa0000");
    			}else if(po.inGroup("dev")) {
    				ps.setString(1, "Developer");
        			ps.setString(2, "#aa00aa");
    			}else if(po.inGroup("hr")) {
    				ps.setString(1, "Human Resources");
        			ps.setString(2, "#ffaa00");
    			}else if(po.inGroup("cm")) {
    				ps.setString(1, "Community Moderator");
        			ps.setString(2, "#55ff55");
    			}else if(po.inGroup("ct")) {
    				ps.setString(1, "Content Team");
        			ps.setString(2, "#0000aa");
    			}else if(po.inGroup("st")) {
    				ps.setString(1, "Support Team");
        			ps.setString(2, "#ffff55");
    			}else if(po.inGroup("bd")) {
    				ps.setString(1, "Builder");
        			ps.setString(2, "#55ffff");
    			}else if(po.inGroup("gm")) {
    				ps.setString(1, "Game Moderator");
        			ps.setString(2, "#ff5555");
    			}else if(po.inGroup("aot")) {
    				ps.setString(1, "Add-On Team");
        			ps.setString(2, "#ff55ff");
    			}else if(po.inGroup("train")) {
    				ps.setString(1, "Trainee");
        			ps.setString(2, "#55ffff");
    			}else if(po.inGroup("rltm")) {
    				ps.setString(1, "Retired Legend");
        			ps.setString(2, "#00aaaa");
    			}else if(po.inGroup("rtm")) {
    				ps.setString(1, "Retired Team Member");
        			ps.setString(2, "#00aaaa");
    			}else if(po.inGroup("part")) {
    				ps.setString(1, "Partner");
        			ps.setString(2, "#00aa00");
    			}else if(po.inGroup("fs")) {
    				ps.setString(1, "Forum Supporter");
        			ps.setString(2, "#ff55ff");
    			}else if(po.inGroup("nb")) {
    				ps.setString(1, "Nitro Booster");
        			ps.setString(2, "#ff55ff");
    			}else if(po.inGroup("bt")) {
    				ps.setString(1, "Beta Tester");
        			ps.setString(2, "#ff55ff");
    			}else if(po.inGroup("friend")) {
    				ps.setString(1, "Friend");
        			ps.setString(2, "#aaaaaa");
    			}else if(po.inGroup("vip")) {
    				ps.setString(1, "VIP");
        			ps.setString(2, "#ffff55");
    			}else if(po.inGroup("default")) {
    				ps.setString(1, "Player");
        			ps.setString(2, "#ffffff");
    			}else {
    				ps.setString(1, "unknown Role");
    				ps.setString(1, "#7c4dff");
    			}
        		ps.setBoolean(3, true);
        		ps.setString(4, bukkit.getServerName());
        		ps.setLong(5, ts.getTime());
        		ps.setString(6, stime);
        		ps.setString(7, p.getAddress().getHostString());
        		if(p.hasPermission("mlps.isStaff")) {
        			ps.setBoolean(8, true);
        		}else {
        			ps.setBoolean(8, false);
        		}
        		ps.setString(9, uuid);
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
        PermissionUser po = PermissionsEx.getUser(p);
        try {
        	PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET userrank = ?, rankcolor = ?, lastjoints = ?, lastjoinstring = ?, lastloginip = ?, online = ? WHERE uuid = ?");
        	if(po.inGroup("pman")) {
    			ps.setString(1, "Project Manager");
    			ps.setString(2, "#5555ff");
			}else if(po.inGroup("cman")) {
				ps.setString(1, "Community Manager");
    			ps.setString(2, "#00aa00");
			}else if(po.inGroup("gmmman")) {
				ps.setString(1, "Game Mod. Manager");
    			ps.setString(2, "#aa0000");
			}else if(po.inGroup("dev")) {
				ps.setString(1, "Developer");
    			ps.setString(2, "#aa00aa");
			}else if(po.inGroup("hr")) {
				ps.setString(1, "Human Resources");
    			ps.setString(2, "#ffaa00");
			}else if(po.inGroup("cm")) {
				ps.setString(1, "Community Moderator");
    			ps.setString(2, "#55ff55");
			}else if(po.inGroup("ct")) {
				ps.setString(1, "Content Team");
    			ps.setString(2, "#0000aa");
			}else if(po.inGroup("st")) {
				ps.setString(1, "Support Team");
    			ps.setString(2, "#ffff55");
			}else if(po.inGroup("bd")) {
				ps.setString(1, "Builder");
    			ps.setString(2, "#55ffff");
			}else if(po.inGroup("gm")) {
				ps.setString(1, "Game Moderator");
    			ps.setString(2, "#ff5555");
			}else if(po.inGroup("aot")) {
				ps.setString(1, "Add-On Team");
    			ps.setString(2, "#ff55ff");
			}else if(po.inGroup("train")) {
				ps.setString(1, "Trainee");
    			ps.setString(2, "#55ffff");
			}else if(po.inGroup("rltm")) {
				ps.setString(1, "Retired Legend");
    			ps.setString(2, "#00aaaa");
			}else if(po.inGroup("rtm")) {
				ps.setString(1, "Retired Team Member");
    			ps.setString(2, "#00aaaa");
			}else if(po.inGroup("part")) {
				ps.setString(1, "Partner");
    			ps.setString(2, "#00aa00");
			}else if(po.inGroup("fs")) {
				ps.setString(1, "Forum Supporter");
    			ps.setString(2, "#ff55ff");
			}else if(po.inGroup("nb")) {
				ps.setString(1, "Nitro Booster");
    			ps.setString(2, "#ff55ff");
			}else if(po.inGroup("bt")) {
				ps.setString(1, "Beta Tester");
    			ps.setString(2, "#ff55ff");
			}else if(po.inGroup("friend")) {
				ps.setString(1, "Friend");
    			ps.setString(2, "#aaaaaa");
			}else if(po.inGroup("vip")) {
				ps.setString(1, "VIP");
    			ps.setString(2, "#ffff55");
			}else if(po.inGroup("default")) {
				ps.setString(1, "Player");
    			ps.setString(2, "#ffffff");
			}else {
				ps.setString(1, "unknown Role");
				ps.setString(1, "#7c4dff");
			}
        	ps.setLong(3, ts.getTime());
        	ps.setString(4, stime);
        	ps.setString(5, p.getAddress().getHostString());
        	ps.setBoolean(6, false);
        	ps.setString(7, uuid);
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