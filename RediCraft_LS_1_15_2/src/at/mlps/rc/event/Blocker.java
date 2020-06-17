package at.mlps.rc.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class Blocker implements Listener{
	
	@EventHandler
	public void onDMG(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			if(e.getCause() == DamageCause.ENTITY_ATTACK) {
				e.setCancelled(true);
			}else if(e.getCause() == DamageCause.FALL) {
				e.setCancelled(true);
			}else if(e.getCause() == DamageCause.CONTACT){
				e.setCancelled(true);
			}else if(e.getCause() == DamageCause.LAVA) {
				e.setCancelled(true);
			}else if(e.getCause() == DamageCause.DROWNING) {
				e.setCancelled(true);
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
        String nomove = Main.prefix() + LanguageHandler.returnStringReady(p, "event.move.cancel").replace("%item", i);
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
        String nodrop = Main.prefix() + LanguageHandler.returnStringReady(p, "event.drop.cancel").replace("%item", i);
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
}