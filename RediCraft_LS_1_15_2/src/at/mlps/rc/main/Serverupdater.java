package at.mlps.rc.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import at.mlps.rc.api.GetBukkitInfo;
import at.mlps.rc.mysql.lb.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.MinecraftServer;

public class Serverupdater implements Listener{
	
	static int rfm = 0;
	
	public static void runUpdaters(int delay, int period) {
		new BukkitRunnable() {
			@Override
			public void run() {
				updateServer();
				rfm++;
				if(rfm == 3) {
					rfm = 0;
					String pl = returnRadio1("https://api.laut.fm/station/redifm", "current_playlist", "name");
					String art = returnRadio1("https://api.laut.fm/station/redifm/current_song", "artist", "name");
					String tra = returnRadio("https://api.laut.fm/station/redifm/current_song", "title");
					String alb = returnRadio("https://api.laut.fm/station/redifm/current_song", "album");
					try {
						PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redifm_current SET track = ?, artist = ?, album = ?, playlist = ? WHERE id = ?");
						ps.setString(1, tra);
						ps.setString(2, art);
						ps.setString(3, alb);
						ps.setString(4, pl);
						ps.setInt(5, 1);
						ps.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.instance, delay, period);
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	public static void updateServer() {
		if(MySQL.isConnected()) {
			Runtime runtime = Runtime.getRuntime();
			long ramusage = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
			long ramtotal = runtime.totalMemory() / 1048576L;
			int players = Bukkit.getOnlinePlayers().size();
			int pmax = Bukkit.getMaxPlayers();
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			long timestamp = ts.getTime();
			SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
		    String stime = time.format(new Date());
		    StringBuilder sb = new StringBuilder("");
		    for(double tps : MinecraftServer.getServer().recentTps) {
		    	sb.append(format(tps));
		    }
		    String tps = sb.substring(0, sb.length() - 1);
		    int code1 = random(0, 5000);
			int code2 = random(5001, 10000);
			String gcode1 = code1 + "-" + code2;
		    try {
		    	Main.mysql.update("UPDATE useless_testtable SET toupdate = '" + gcode1 + "' WHERE type = 'lobby';");
		    	PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_serverstats SET ramusage = ?, serverid = ?, currPlayers = ?, maxPlayers = ?, lastupdateTS = ?, lastupdateST = ?, ramavailable = ?, version = ?, tps = ? WHERE servername = ?");
		    	ps.setInt(1, (int) ramusage);
				ps.setString(2, GetBukkitInfo.getServerId());
				ps.setInt(3, players);
				ps.setInt(4, pmax);
				ps.setInt(5, (int) timestamp);
				ps.setString(6, stime);
				ps.setInt(7, (int) ramtotal);
				ps.setString(8, "1.16.3");
				ps.setString(9, tps);
				ps.setString(10, GetBukkitInfo.getServerName());
				ps.executeUpdate();
				ps.close();
				
		    }catch (SQLException e) { e.printStackTrace(); Bukkit.getConsoleSender().sendMessage("§cCan't update DB-Stats."); }
		}else {
			Bukkit.getConsoleSender().sendMessage("§cDB is not connected.");
		}
		Serverrestarter();
	}
	
	private static void Serverrestarter() {
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	    String stime = time.format(new Date());
	    if(stime.equals("20:00:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.200000");
	    	}
	    }else if(stime.equals("21:00:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.210000");
	    	}
	    }else if(stime.equals("22:00:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.220000");
	    	}
	    }else if(stime.equals("22:30:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.223000");
	    	}
	    }else if(stime.equals("23:00:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.230000");
	    	}
	    }else if(stime.equals("23:30:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.233000");
	    	}
	    }else if(stime.equals("23:45:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.234500");
	    	}
	    }else if(stime.equals("23:55:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235500");
	    	}
	    }else if(stime.equals("23:56:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235600");
	    	}
	    }else if(stime.equals("23:57:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235700");
	    	}
	    }else if(stime.equals("23:58:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235800");
	    	}
	    }else if(stime.equals("23:59:00")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235900");
	    	}
	    }else if(stime.equals("23:59:50")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235950");
	    	}
	    }else if(stime.equals("23:59:51")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235951");
	    	}
	    }else if(stime.equals("23:59:52")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235952");
	    	}
	    }else if(stime.equals("23:59:53")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235953");
	    	}
	    }else if(stime.equals("23:59:54")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235954");
	    	}
	    }else if(stime.equals("23:59:55")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235955");
	    	}
	    }else if(stime.equals("23:59:56")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235956");
	    	}
	    }else if(stime.equals("23:59:57")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235957");
	    	}
	    }else if(stime.equals("23:59:58")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235958");
	    	}
	    }else if(stime.equals("23:59:59")) {
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    		LanguageHandler.sendMSGReady(all, "restarter.time.235959");
	    	}
	    }else if(stime.equals("00:00:01")) {
	    	Bukkit.shutdown();
	    }
	}
	
	private static String format(double tps) {
		return String.valueOf((tps > 18.0 ? ChatColor.GREEN : (tps > 16.0 ? ChatColor.YELLOW : ChatColor.RED)).toString()) + (tps > 20.0 ? "*" : "") + Math.min((double)Math.round(tps * 100.0) / 100.0, 20.0);
	}
	
	public static int getPlayTime(Player p) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT playtime FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("playtime");
		}catch (SQLException e) {
			return 0;
		}
	}
	
	public void setPlayTime(Player p, long playtime) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET playtime = ? WHERE uuid = ?");
			ps.setLong(1, playtime);
			ps.setString(2, p.getUniqueId().toString().replace("-", ""));
			ps.executeUpdate();
		}catch (SQLException e) {
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		File file = new File("plugins/RCLS/ptimecache.yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		long systime = (System.currentTimeMillis() / 1000);
		cfg.set(uuid, systime);
		try {
			cfg.save(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		File file = new File("plugins/RCLS/ptimecache.yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		long oldts = cfg.getLong(uuid);
		long newts = (System.currentTimeMillis() / 1000);
		long diffts = (newts - oldts);
		long newptime = (diffts + getPlayTime(p));
		setPlayTime(p, newptime);
	}
	
	static int random(int low, int max) {
		Random r = new Random();
		int number = r.nextInt(max);
		while(number < low) {
			number = r.nextInt(max);
		}
		return number;
	}
	
	private static String returnRadio(String uri, String node) {
		String s = "";
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(uri);
			URLConnection urlc = url.openConnection();
			BufferedReader bR = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			String line;
			while ((line = bR.readLine()) != null) {
				content.append(line + "\n");
			}
			bR.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String lortu = content.toString();
		JSONParser parser = new JSONParser();
		try {
			JSONObject jo = (JSONObject)parser.parse(lortu);
			if(jo.get(node) == null) {
				s = "-1";
			}else {
				s = (String) jo.get(node);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	private static String returnRadio1(String uri, String node, String subnode) {
		String s = "";
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(uri);
			URLConnection urlc = url.openConnection();
			BufferedReader bR = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			String line;
			while ((line = bR.readLine()) != null) {
				content.append(line + "\n");
			}
			bR.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		String lortu = content.toString();
		JSONParser parser = new JSONParser();
		try {
			JSONObject jo = (JSONObject)parser.parse(lortu);
			if(jo.get(node) == null) {
				s = "-1";
			}else {
				JSONObject sub = (JSONObject) jo.get(node);
				s = (String) sub.get(subnode);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}