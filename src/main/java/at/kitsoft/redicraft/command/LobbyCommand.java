package at.kitsoft.redicraft.command;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import at.kitsoft.redicraft.api.Prefix;
import at.kitsoft.redicraft.main.LanguageHandler;
import at.kitsoft.redicraft.main.Main;

public class LobbyCommand implements CommandExecutor, Listener {
	
	// CMD -> /lobbyconf <setspawn|setdrew|heightlow|heightup>
	static File spawn = new File("plugins/RCLS/spawn.yml");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player) sender;
			if(args.length == 1) {
				Location loc = p.getLocation();
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(spawn);
				SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
			    
				if(args[0].equalsIgnoreCase("setspawn")) {
					cfg.set("Spawn.general.X", loc.getX());
					cfg.set("Spawn.general.Y", loc.getY());
					cfg.set("Spawn.general.Z", loc.getZ());
					cfg.set("Spawn.general.YAW", loc.getYaw());
					cfg.set("Spawn.general.PITCH", loc.getPitch());
					cfg.set("Spawn.general.WORLD", loc.getWorld().getName());
					cfg.set("Spawn.general.DATETIME", time.format(new Date()));
					cfg.set("Spawn.general.ADMIN", p.getName());
					try { 
						cfg.save(spawn);
						LanguageHandler.sendMSGReady(p, "cmd.setspawn.mainspawn");
					} catch (IOException e) { 
						e.printStackTrace(); 
					}
				}else if(args[0].equalsIgnoreCase("setdrew")) {
					cfg.set("Spawn.drew.X", loc.getX());
					cfg.set("Spawn.drew.Y", loc.getY());
					cfg.set("Spawn.drew.Z", loc.getZ());
					cfg.set("Spawn.drew.YAW", loc.getYaw());
					cfg.set("Spawn.drew.PITCH", loc.getPitch());
					cfg.set("Spawn.drew.WORLD", loc.getWorld().getName());
					cfg.set("Spawn.drew.DATETIME", time.format(new Date()));
					cfg.set("Spawn.drew.ADMIN", p.getName());
					try {
						cfg.save(spawn);
						LanguageHandler.sendMSGReady(p, "cmd.setspawn.dailyreward");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(args[0].equalsIgnoreCase("heightlow")) {
					cfg.set("Spawn.limit.low", loc.getBlockY());
					try {
						cfg.save(spawn);
						LanguageHandler.sendMSGReady(p, "cmd.setspawn.lowlimit");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(args[0].equalsIgnoreCase("heightup")) {
					cfg.set("Spawn.limit.high", loc.getBlockY());
					try {
						cfg.save(spawn);
						LanguageHandler.sendMSGReady(p, "cmd.setspawn.uplimit");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					p.sendMessage(Prefix.prefix("main") + "Usage: /lobbyconf <setspawn|setdrew|heightlow|heightup>");
				}
			}else {
				p.sendMessage(Prefix.prefix("main") + "Usage: /lobbyconf <setspawn|setdrew|heightlow|heightup>");
			}
		}
		return false;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(spawn);
		if(cfg.getString("Spawn.general.WORLD").equalsIgnoreCase(loc.getWorld().getName())) {
			if(!BuildCommand.build.contains(p.getName())) {
				if(loc.getBlockY() <= cfg.getInt("Spawn.limit.low")) {
					p.teleport(retLoc(cfg, "general"));
				}
				if(loc.getBlockY() >= cfg.getInt("Spawn.limit.high")) {
					p.teleport(retLoc(cfg, "general"));
				}
			}
		}
	}
	
	private Location retLoc(YamlConfiguration cfg, String type) {
		Location loc = new Location(Bukkit.getWorld(cfg.getString("Spawn." + type + ".WORLD")), cfg.getDouble("Spawn." + type + ".X"), cfg.getDouble("Spawn." + type + ".Y"), cfg.getDouble("Spawn." + type + ".Z"), (float)cfg.getDouble("Spawn." + type + ".YAW"), (float)cfg.getDouble("Spawn." + type + ".PITCH"));
		return loc;
	}

}