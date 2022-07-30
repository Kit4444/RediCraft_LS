package at.kitsoft.redicraft.api;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Advents{
	
	private static File file;
	private YamlConfiguration cfg;
	
	static{
		file = new File("plugins/RCLS/advents.yml");
	}
	
	public Advents(){
		cfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public void setReward(Player p, int day){
		cfg.set("Advents." + p.getUniqueId().toString() + ".Day" + day, true);
		
		try{ cfg.save(file); }
		catch(IOException e){ e.printStackTrace(); }
	}
	
	public boolean hasRewardUsed(Player p, int day){
		return cfg.contains("Advents." + p.getUniqueId().toString() + ".Day" + day);
	}
	
	public boolean isAllowedDate(String date){
		String formDate = date + "/12";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		String sdff = sdf.format(new Date());
		
		return formDate.equalsIgnoreCase(sdff);
	}

}