package at.kitsoft.redicraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import at.kitsoft.redicraft.api.APIs;

public class Blocker implements Listener{
	
	@EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
    	Player p = e.getPlayer();
    	APIs api = new APIs();
    	String message = e.getMessage();
    	if(message.equalsIgnoreCase("/pl") || message.equalsIgnoreCase("/plugins") || message.equalsIgnoreCase("/bukkit:pl") || message.equalsIgnoreCase("/bukkit:plugins")) {
    		if(p.hasPermission("*")) {
    			e.setCancelled(false);
    		}else {
    			e.setCancelled(true);
    			api.noPerm(p);
    		}
    	}else if(message.equalsIgnoreCase("/help") || message.equalsIgnoreCase("/?") || message.equalsIgnoreCase("/bukkit:?") || message.equalsIgnoreCase("/bukkit:help") || message.equalsIgnoreCase("/minecraft:help")) {
    		if(p.hasPermission("*")) {
    			e.setCancelled(false);
    		}else {
    			e.setCancelled(true);
    			api.sendMSGReady(p, "cmd.help");
    		}
    	}else if(message.equalsIgnoreCase("/icanhasbukkit") || message.equalsIgnoreCase("/bukkit:about") || message.equalsIgnoreCase("/about") || message.equalsIgnoreCase("/bukkit:ver") || message.equalsIgnoreCase("/bukkit:version")) {
    		if(p.hasPermission("*")) {
    			e.setCancelled(false);
    		}else {
    			e.setCancelled(true);
    			api.noPerm(p);
    		}
    	}
    }
}