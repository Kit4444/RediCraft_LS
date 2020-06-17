package at.mlps.rc.cmd;

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

import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class SpawnCMD implements CommandExecutor{
	
	static File spawn = new File("plugins/RCLS/spawn.yml");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player)sender;
			if(!spawn.exists()) {
				try {
					spawn.createNewFile();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
				if(args.length == 0) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + "§7/setspawn <spawn|dailyrew>");
				}else if(args.length == 1) {
					Location loc = p.getLocation();
					YamlConfiguration cfg = YamlConfiguration.loadConfiguration(spawn);
					SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
				    String stime = time.format(new Date());
					if(args[0].equalsIgnoreCase("spawn")) {
						cfg.set("Spawn.general.X", loc.getX());
						cfg.set("Spawn.general.Y", loc.getY());
						cfg.set("Spawn.general.Z", loc.getZ());
						cfg.set("Spawn.general.YAW", loc.getYaw());
						cfg.set("Spawn.general.PITCH", loc.getPitch());
						cfg.set("Spawn.general.WORLD", loc.getWorld().getName());
						cfg.set("Spawn.general.DATETIME", stime);
						cfg.set("Spawn.general.ADMIN", p.getName());
						try { 
							cfg.save(spawn);
							LanguageHandler.sendMSGReady(p, "cmd.setspawn.mainspawn");
						} catch (IOException e) { 
							e.printStackTrace(); 
						}
					}else if(args[0].equalsIgnoreCase("dailyrew")) {
						cfg.set("Spawn.drew.X", loc.getX());
						cfg.set("Spawn.drew.Y", loc.getY());
						cfg.set("Spawn.drew.Z", loc.getZ());
						cfg.set("Spawn.drew.YAW", loc.getYaw());
						cfg.set("Spawn.drew.PITCH", loc.getPitch());
						cfg.set("Spawn.drew.WORLD", loc.getWorld().getName());
						cfg.set("Spawn.drew.DATETIME", stime);
						cfg.set("Spawn.drew.ADMIN", p.getName());
						try {
							cfg.save(spawn);
							LanguageHandler.sendMSGReady(p, "cmd.setspawn.dailyreward");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		return false;
	}	
}