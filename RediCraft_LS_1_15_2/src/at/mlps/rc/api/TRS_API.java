package at.mlps.rc.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TRS_API {
	
	static File file = new File("plugins/RCLS/rewards.yml");
	
	public static String remainingTime(long ms) {
		long seconds = ms / 1000;
		long minutes = 0;
		while(seconds > 60) {
			seconds -= 60;
			minutes++;
		}
		long hours = 0;
		while(minutes > 60) {
			minutes -= 60;
			hours++;
		}
		return hours + ":" + minutes + ":" + seconds;
	}
	
	public static long getTime(Player p, String path) {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg.getLong(p.getUniqueId().toString() + ".millis." + path);
	}
	
	public static void setReward(Player p, String path) {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		long onedaytime = System.currentTimeMillis() + 86400000;
		cfg.set(p.getUniqueId().toString() + ".millis." + path, onedaytime);
		try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
	}
	
	public static boolean isAllowed(Player p, String path) {
		long current = System.currentTimeMillis();
		long ms = getTime(p, path);
		return (current >= ms);
	}
}