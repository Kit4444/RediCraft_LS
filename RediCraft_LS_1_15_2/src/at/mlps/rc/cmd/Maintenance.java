package at.mlps.rc.cmd;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import at.mlps.rc.api.Prefix;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;

public class Maintenance implements CommandExecutor, Listener {

	File whitelist = new File("plugins/RCLS/whitelist.yml");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		} else {
			Player p = (Player) sender;
			MojangAPI mapi = new MojangAPI();
			if (cmd.getName().equalsIgnoreCase("whitelist")) {
				if (args.length == 0) {
					p.sendMessage(Prefix.prefix("main") + "§7Usage: /whitelist <add|remove> <Name>");
				} else if (args.length == 2) {
					if (p.hasPermission("mlps.whitelist")) {
						String uuid = mapi.getUUIDfromName(args[1]);
						if (args[0].equalsIgnoreCase("add")) {
							if (uuid.equalsIgnoreCase("errored")) {
								p.sendMessage("§7========[§aAdduser§7]========");
								p.sendMessage("§7User: §a" + args[1]);
								p.sendMessage("§7Reason: §cPlayer couldn't be found. Please check the name again.");
							} else {
								HashMap<String, Object> hm = new HashMap<>();
								hm.put("uuid", uuid);
								try {
									if (Main.mysql.isInDatabase("redicore_whitelist", hm)) {
										PreparedStatement ps = MySQL.getConnection().prepareStatement(
												"UPDATE redicore_whitelist SET allowed = ? WHERE uuid = ?");
										ps.setBoolean(1, true);
										ps.setString(2, uuid);
										ps.executeUpdate();
										p.sendMessage("§7========[§bWhitelist§7]========");
										p.sendMessage("§7User: §a" + args[1] + " §7/§a " + uuid);
										p.sendMessage("§7Player is now allowed to join.");
									} else {
										hm.put("allowed", true);
										Main.mysql.insertInto("redicore_userwhitelist", hm);
										p.sendMessage("§7========[§bWhitelist§7]========");
										p.sendMessage("§7User: §a" + args[1] + " §7/§a " + uuid);
										p.sendMessage("§7Player has been added and is now able to join.");
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							HashMap<String, Object> hm = new HashMap<>();
							hm.put("uuid", uuid);
							try {
								if (Main.mysql.isInDatabase("redicore_userwhitelist", hm)) {
									PreparedStatement ps = MySQL.getConnection().prepareStatement(
											"UPDATE redicore_whitelist SET enabled = ? WHERE uuid = ?");
									ps.setBoolean(1, false);
									ps.setString(2, uuid);
									ps.executeUpdate();
									p.sendMessage("§7========[§bWhitelist§7]========");
									p.sendMessage("§7User: §a" + args[1] + " §7/§a " + uuid);
									p.sendMessage("§7Player is no longer allowed to join.");
									ps.close();
								} else {
									hm.put("enabled", false);
									Main.mysql.insertInto("redicore_userwhitelist", hm);
									p.sendMessage("§7========[§bWhitelist§7]========");
									p.sendMessage("§7User: §a" + args[1] + " §7/§a " + uuid);
									p.sendMessage("§7Player has been added but is §cnot §7able to join.");
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							p.sendMessage(Prefix.prefix("main") + "§7Usage: /whitelist <add|remove> <Name>");
						}
					}
				} else {
					p.sendMessage(Prefix.prefix("main") + "§7Usage: /whitelist <add|remove> <Name>");
				}
			} else if (cmd.getName().equalsIgnoreCase("maintenance")) {
				if (!whitelist.exists()) {
					try {
						whitelist.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (args.length == 0) {
					p.sendMessage(Prefix.prefix("main") + "§7Usage: /maintenance <on|off|status>");
				} else if (args.length == 1) {
					if (p.hasPermission("mlps.maintenance")) {
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(whitelist);
						if (args[0].equalsIgnoreCase("on")) {
							cfg.set("Whitelist.Maintenance", true);
							try {
								cfg.save(whitelist);
								p.sendMessage(Prefix.prefix("main") + "§cYou activated the maintenance.");
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (args[0].equalsIgnoreCase("off")) {
							cfg.set("Whitelist.Maintenance", false);
							try {
								cfg.save(whitelist);
								p.sendMessage(Prefix.prefix("main") + "§aYou deactivated the maintenance.");
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (args[0].equalsIgnoreCase("status")) {
							boolean boo = cfg.getBoolean("Whitelist.Maintenance");
							if (boo == true) {
								p.sendMessage(Prefix.prefix("main") + "§7Status of Maintenance: §cactive");
							} else if (boo == false) {
								p.sendMessage(Prefix.prefix("main") + "§7Status of Maintenance: §ainactive");
							}
						}
					} else {
						LanguageHandler.noPerm(p);
					}
				}
			}
		}
		return true;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent e) {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(whitelist);
		if (cfg.getBoolean("Whitelist.Maintenance")) {
			// when maintenance is activated
			Player p = e.getPlayer();
			String uuid = p.getUniqueId().toString().replace("-", "");
			boolean allowed = false;
			try {
				PreparedStatement ps = MySQL.getConnection()
						.prepareStatement("SELECT allowed FROM redicore_whitelist WHERE uuid = ?");
				ps.setString(1, uuid);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					allowed = rs.getBoolean("allowed");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (allowed) {
				e.allow();
			} else {
				e.disallow(Result.KICK_WHITELIST,
						"§aRedi§cCraft\n \n§7Currently, the Server is in maintenance mode. \n§7We hope, we can release it as quick as possible again. \n \n§aWe're sorry for the inconvenience.");
			}
		} else {
			e.allow();
		}
	}

	/*
	 * @EventHandler(priority=EventPriority.HIGHEST)
	 * public void onLogin(PlayerLoginEvent e) {
	 * Player p = e.getPlayer();
	 * String uuid = p.getUniqueId().toString().replace("-", "");
	 * try {
	 * HashMap<String, Object> hm = new HashMap<>();
	 * hm.put("uuid", p.getUniqueId().toString().replace("-", ""));
	 * if(Main.mysql.isInDatabase("redicore_userwhitelist", hm)) {
	 * PreparedStatement ps = MySQL.getConnection().
	 * prepareStatement("SELECT * FROM redicore_userwhitelist WHERE uuid = ?");
	 * ps.setString(1, p.getUniqueId().toString().replace("-", ""));
	 * ResultSet rs = ps.executeQuery();
	 * rs.next();
	 * if(rs.getBoolean("enabled")) {
	 * e.allow();
	 * }else {
	 * if(p.hasPermission("mlps.isTeam") || getStatus(uuid) == true) {
	 * e.allow();
	 * }else {
	 * e.disallow(Result.KICK_OTHER, "\n§aRedi§cCraft\n \n§7Hey " +
	 * e.getPlayer().getName() +
	 * ",\n§7thank you for joining our server,\n§7but it seems you are not allowed to join our server.\n \nIf you want to play on our server, you have to fill out our Google Forms.\nURL: https://forms.gle/2mvyoJ8DGqeBP2fH7 \n \nYou want to know more about it?\nJoin our Discord Server now: https://discord.gg/sHDF9WR"
	 * );
	 * }
	 * }
	 * }else {
	 * if(p.hasPermission("mlps.isTeam") || getStatus(uuid) == true) {
	 * e.allow();
	 * }else {
	 * e.disallow(Result.KICK_OTHER, "\n§aRedi§cCraft\n \n§7Hey " +
	 * e.getPlayer().getName() +
	 * ",\n§7thank you for joining our server,\n§7but it seems you are not allowed to join our server.\n \nIf you want to play on our server, you have to fill out our Google Forms.\nURL: https://forms.gle/2mvyoJ8DGqeBP2fH7 \n \nYou want to know more about it?\nJoin our Discord Server now: https://discord.gg/sHDF9WR"
	 * );
	 * }
	 * }
	 * } catch (SQLException e1) {
	 * e1.printStackTrace();
	 * }
	 * }
	 */
}