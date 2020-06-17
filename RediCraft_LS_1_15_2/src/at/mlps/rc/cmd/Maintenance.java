package at.mlps.rc.cmd;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import at.mlps.rc.api.MojangAPI;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;

public class Maintenance implements CommandExecutor, Listener{
	
	File whitelist = new File("plugins/RCLS/whitelist.yml");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("userlist")) {
				if(args.length == 0) {
					p.sendMessage(Main.prefix() + "§7Usage: /userlist <add|remove|changereason|check|listall> <Name> <Message>");
				}else if(args.length >= 0) {
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
				    String sdate = date.format(new Date());
					if(p.hasPermission("mlps.whitelisting")) {
						if(args[0].equalsIgnoreCase("add")) {
							String name = args[1].toLowerCase();
							StringBuilder sb = new StringBuilder();
							for(int i = 2; i < args.length; i++) {
								sb.append(args[i]);
								sb.append(" ");
							}
							String msg = sb.toString();
							String uuid = MojangAPI.getUUIDfromName(name);
							setStatus(name, uuid, p.getName(), sdate, msg, System.currentTimeMillis(), true);
							p.sendMessage("§7========[§aAdduser§7]========");
							p.sendMessage("§7User: §a" + name + " §7/§a " + uuid);
							p.sendMessage("§7Reason: §a" + msg);
						}else if(args[0].equalsIgnoreCase("remove")) {
							String name = args[1].toLowerCase();
							StringBuilder sb = new StringBuilder();
							for(int i = 2; i < args.length; i++) {
								sb.append(args[i]);
								sb.append(" ");
							}
							String msg = sb.toString();
							String uuid = MojangAPI.getUUIDfromName(name);
							setStatus(name, uuid, p.getName(), sdate, msg, System.currentTimeMillis(), false);
							p.sendMessage("§7========[§cRemoveuser§7]========");
							p.sendMessage("§7User: §a" + name + " §7/§a " + uuid);
							p.sendMessage("§7Reason: §a" + msg);
						}else if(args[0].equalsIgnoreCase("check")) {
							String name = args[1].toLowerCase();
							String uuid = "";
							String msg = "§aDefault Reason.";
							String admin = "§cAlex";
							String datifo = "dd/MM/yy - HH:mm:ss";
							boolean boo = false;
							try {
								PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_whitelist WHERE name = ?");
								ps.setString(1, name);
								ResultSet rs = ps.executeQuery();
								rs.next();
								uuid = rs.getString("uuid");
								msg = rs.getString("reason");
								admin = rs.getString("admin");
								datifo = rs.getString("timest");
								boo = rs.getBoolean("allowed");
							}catch (SQLException e) {}
							p.sendMessage("§7========[§2Checkuser§7]========");
							p.sendMessage("§7User: §a" + name + " §7/§a " + uuid);
							p.sendMessage("§7Reason: §a" + msg);
							p.sendMessage("§7Admin: §a" + admin);
							p.sendMessage("§7Date & Time: §a" + datifo);
							if(boo == true) {
								p.sendMessage("§7Can Join: §ayes");
							}else {
								p.sendMessage("§7Can Join: §cno");
							}
						}else if(args[0].equalsIgnoreCase("changereason")) {
							String name = args[1].toLowerCase();
							StringBuilder sb = new StringBuilder();
							for(int i = 2; i < args.length; i++) {
								sb.append(args[i]);
								sb.append(" ");
							}
							String msg = sb.toString();
							String uuid = MojangAPI.getUUIDfromName(name);
							changeStatus(uuid, name, p.getName(), msg);
							p.sendMessage("§7========[§eChangereason§7]========");
							p.sendMessage("§7User: §a" + name + " §7/§a " + uuid);
							p.sendMessage("§7Reason: §a" + msg);
						}else if(args[0].equalsIgnoreCase("listall")) {
							try {
								PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_whitelist");
								ResultSet rs = ps.executeQuery();
								p.sendMessage("§7========[§9Listall§7]========");
								while(rs.next()) {
									p.sendMessage("§7ID: §2" + rs.getInt("id") + " §7| Player: §a" + rs.getString("name") + " §7| Whitelisted: §c" + rs.getBoolean("allowed"));
								}
							}catch (SQLException e) {
								p.sendMessage("§cError while performing this command.");
							}
						}else {
							p.sendMessage(Main.prefix() + "§7Usage: /userlist <add|remove|changereason|check|listall> <Name> <Message>");
						}
					}else {
						LanguageHandler.sendMSGReady(p, "noPermission");
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("maintenance")) {
				if(!whitelist.exists()) {
					try { whitelist.createNewFile(); }catch (IOException e) { e.printStackTrace(); }
				}
				if(args.length == 0) {
					p.sendMessage(Main.prefix() + "§7Usage: /maintenance <on|off|status> <(if on)Message>");
				}else if(args.length >= 1) {
					if(p.hasPermission("mlps.maintenance")) {
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(whitelist);
						StringBuilder sb = new StringBuilder();
						for(int i = 1; i < args.length; i++) {
							sb.append(args[i]);
							sb.append(" ");
						}
						String msg = sb.toString();
						if(args[0].equalsIgnoreCase("on")) {
							if(msg.isEmpty()) {
								p.sendMessage(Main.prefix() + "§cPlease give at least one Word for Reason.");
							}else {
								cfg.set("Whitelist.Maintenance.Message", msg);
								cfg.set("Whitelist.Maintenance.Boolean", true);
								cfg.set("Whitelist.Maintenance.activatedFrom", p.getName());
								try {
									cfg.save(whitelist);
									p.sendMessage(Main.prefix() + "§7You activated the maintenance - state.");
									p.sendMessage("§7Message: §9" + msg);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}else if(args[0].equalsIgnoreCase("off")) {
							cfg.set("Whitelist.Maintenance.Boolean", false);
							try {
								cfg.save(whitelist);
								p.sendMessage(Main.prefix() + "§7You deactivated the maintenance - state.");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else if(args[0].equalsIgnoreCase("status")) {
							String cfgmsg = cfg.getString("Whitelist.Maintenance.Message");
							boolean boo = cfg.getBoolean("Whitelist.Maintenance.Boolean");
							String admin = cfg.getString("Whitelist.Maintenance.deactivatedFrom", p.getName());
							if(boo == true) {
								p.sendMessage(Main.prefix() + "§7Status of Maintenance: §cactive");
							}else if(boo == false) {
								p.sendMessage(Main.prefix() + "§7Status of Maintenance: §ainactive");
							}
							p.sendMessage(Main.prefix() + "§7Last Maintenance-Message: §f" + cfgmsg);
							p.sendMessage(Main.prefix() + "§7Admin: " + admin);
						}
					}else {
						LanguageHandler.sendMSGReady(p, "noPermission");
					}
				}
			}
		}
		return true;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(whitelist);
		boolean boo = cfg.getBoolean("Whitelist.Maintenance.Boolean");
		String msg = cfg.getString("Whitelist.Maintenance.Message");
		String uname = cfg.getString("Whitelist.Maintenance.activatedFrom");
		if(boo == true) {
			if(p.hasPermission("mlps.isTeam") || getStatus(uuid) == true) {
				e.allow();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						e.getPlayer().sendMessage(Main.prefix() + "§7Bypassed the maintenance-mode!");
						p.sendMessage("§7Reason: §a" + returnReason(uuid));
					}
				}, 10);
			}else {
				e.disallow(Result.KICK_OTHER, "\n§aRedi§cCraft\n \n§2Hey " + e.getPlayer().getName() + ", thank you for joining the network,\n§2but currently we need time for server maintenance.\n§aAdmin: §r" + uname + "\n§aReason: §6" + msg + "\n \n§7If you believe this is a mistake,\n§7please report it to Maurice_Bailey.\n \n§aOur Public Beta Weekends are always between\n§6Friday 12:00 UTC to Sunday 22:00 UTC.\n§aJoin, select the right server and have fun!");
			}
		}else {
			e.allow();
		}
	}
	
	private void setStatus(String name, String uuid, String admin, String timest, String reason, long timets, boolean allowed) {
		try {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("uuid", uuid);
			if(Main.mysql.isInDatabase("redicore_whitelist", hm)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_whitelist SET name = ?, allowed = ?, reason = ?, timest = ?, timets = ?, admin = ? WHERE uuid = ?");
				ps.setString(1, name);
				ps.setBoolean(2, allowed);
				ps.setString(3, reason);
				ps.setString(4, timest);
				ps.setLong(5, timets);
				ps.setString(6, admin);
				ps.setString(7, uuid);
				ps.executeUpdate();
			}else {
				hm.put("name", name);
				hm.put("allowed", allowed);
				hm.put("reason", reason);
				hm.put("admin", admin);
				hm.put("timest", timest);
				hm.put("timets", timets);
				Main.mysql.insertInto("redicore_whitelist", hm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void changeStatus(String uuid, String name, String admin, String reason) {
		try {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("uuid", uuid);
			if(Main.mysql.isInDatabase("redicore_whitelist", hm)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_whitelist SET name = ?, reason = ?, admin = ? WHERE uuid = ?");
				ps.setString(1, name);
				ps.setString(2, reason);
				ps.setString(3, admin);
				ps.setString(4, uuid);
				ps.executeUpdate();
			}
		}catch (SQLException e) {}
	}
	
	private String returnReason(String uuid) {
		String s = "";
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("uuid", uuid);
		try {
			if(Main.mysql.isInDatabase("redicore_whitelist", hm)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_whitelist WHERE uuid = ?");
				ps.setString(1, uuid);
				ResultSet rs = ps.executeQuery();
				rs.next();
				s = rs.getString("reason");
			}
		}catch (SQLException e) {}
		return s;
	}
	
	private boolean getStatus(String uuid) {
		boolean boo = false;
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("uuid", uuid);
		try {
			if(Main.mysql.isInDatabase("redicore_whitelist", hm)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_whitelist WHERE uuid = ?");
				ps.setString(1, uuid);
				ResultSet rs = ps.executeQuery();
				rs.next();
				boo = rs.getBoolean("allowed");
			}
		}catch (SQLException e) {}
		return boo;
	}
}