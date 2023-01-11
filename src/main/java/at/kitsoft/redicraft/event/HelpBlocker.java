package at.kitsoft.redicraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import at.kitsoft.redicraft.main.LanguageHandler;

public class HelpBlocker implements Listener{
	
	@EventHandler
	public void onHelp(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		if(msg.equalsIgnoreCase("/help") || msg.equalsIgnoreCase("/bukkit:help")) {
			if(p.hasPermission("mlps.canView.help")) {
				e.setCancelled(false);
			}else {
				e.setCancelled(true);
				LanguageHandler.notAvailable(p);
			}
		}
	}

}
