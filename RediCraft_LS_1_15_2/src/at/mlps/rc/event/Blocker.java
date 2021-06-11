package at.mlps.rc.event;


import java.util.LinkedList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import at.mlps.rc.api.Prefix;
import at.mlps.rc.cmd.BuildClass;
import at.mlps.rc.main.LanguageHandler;

public class Blocker implements Listener{
	
	@EventHandler
	public void onDMG(EntityDamageEvent damageEvent) {

		LinkedList<DamageCause> causes = new LinkedList<>();
		causes.add(DamageCause.ENTITY_ATTACK);
		causes.add(DamageCause.FALL);
		causes.add(DamageCause.CONTACT);
		causes.add(DamageCause.LAVA);
		causes.add(DamageCause.DROWNING);
		causes.add(DamageCause.FIRE);
		causes.add(DamageCause.WITHER);

		Entity entity = damageEvent.getEntity();
		if(entity instanceof Player || entity instanceof Fish){
			if(entity instanceof Player){
				if(causes.contains(damageEvent.getCause())){
					damageEvent.setCancelled(true);
				}
			}
			else{ damageEvent.setCancelled(true); }
		}
	}
	
	@EventHandler
	public void onItemFrameInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity target = e.getRightClicked();
		if(target.getType() == EntityType.ITEM_FRAME) {
			if(BuildClass.build.contains(p.getName())) {
				e.setCancelled(false);
			}else {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onItemFrameDamage(EntityDamageByEntityEvent e) {
		Entity issuer = e.getDamager();
		Entity damaged = e.getEntity();
		if(damaged.getType() == EntityType.ITEM_FRAME) {
			e.setCancelled(true);
			if(issuer.getType() == EntityType.PLAYER) {
				Player p = (Player) issuer;
				p.sendMessage(Prefix.prefix("main") + "§7You can't do that!");
			}
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        String i = e.getCurrentItem().getItemMeta().getDisplayName();
        String nomove = Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.move.cancel").replace("%item", i);
        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Navigator.title)) {
        	e.setCancelled(true);
            p.sendMessage(nomove);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ExtrasInv.mainTitle)) {
        	e.setCancelled(true);
            p.sendMessage(nomove);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(LanguageInv.mainTitle)) {
        	e.setCancelled(true);
            p.sendMessage(nomove);
        }
    }
	
    @EventHandler
    public void onDropItems(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        String i = e.getItemDrop().getItemStack().getItemMeta().getDisplayName();
        String nodrop = Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.drop.cancel").replace("%item", i);
        if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(Navigator.title)) {
            p.sendMessage(nodrop);
            e.setCancelled(true);
        }else if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ExtrasInv.mainTitle)) {
            e.setCancelled(true);
            p.sendMessage(nodrop);
        }else if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(LanguageInv.mainTitle)) {
            e.setCancelled(true);
            p.sendMessage(nodrop);
        }
    }
    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
    	Player p = e.getPlayer();
    	String message = e.getMessage();
    	if(message.equalsIgnoreCase("/pl") || message.equalsIgnoreCase("/plugins") || message.equalsIgnoreCase("/bukkit:pl") || message.equalsIgnoreCase("/bukkit:plugins")) {
    		if(p.hasPermission("*")) {
    			e.setCancelled(false);
    		}else {
    			e.setCancelled(true);
    			LanguageHandler.noPerm(p);
    		}
    	}else if(message.equalsIgnoreCase("/help") || message.equalsIgnoreCase("/?") || message.equalsIgnoreCase("/bukkit:?") || message.equalsIgnoreCase("/bukkit:help") || message.equalsIgnoreCase("/minecraft:help")) {
    		if(p.hasPermission("*")) {
    			e.setCancelled(false);
    		}else {
    			e.setCancelled(true);
    			LanguageHandler.sendMSGReady(p, "cmd.help");
    		}
    	}else if(message.equalsIgnoreCase("/icanhasbukkit") || message.equalsIgnoreCase("/bukkit:about") || message.equalsIgnoreCase("/about") || message.equalsIgnoreCase("/bukkit:ver") || message.equalsIgnoreCase("/bukkit:version")) {
    		if(p.hasPermission("*")) {
    			e.setCancelled(false);
    		}else {
    			e.setCancelled(true);
    			LanguageHandler.noPerm(p);
    		}
    	}
    }
}