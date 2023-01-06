package at.kitsoft.redicraft.api;

import org.bukkit.entity.Player;

import com.viaversion.viaversion.api.Via;

public class PlayerInfo {
	
	//340 is 1.12.2
	public static int getPlayerVersion(Player player) {
		return Via.getAPI().getPlayerVersion(player.getUniqueId());
	}
	
	static String translateVersion(int version) {
		String ver = "";
		switch(version) {
		case 340: ver = "1.12.2"; break;
		case 759: ver = "1.19"; break;
		case 760: ver = "1.19.2"; break;
		default: ver = "" + version; break;
		}
		return ver;
	}
	
}