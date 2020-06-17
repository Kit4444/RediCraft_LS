package at.mlps.rc.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import at.mlps.rc.cmd.BuildClass;

public class ItemHandling implements Listener{
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(BuildClass.build.contains(p.getName())) {
			e.setCancelled(false);
		}else {
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Navigator.title)) {
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					e.setCancelled(true);
					Navigator.mainnavi(p);
				}
			}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ExtrasInv.mainTitle)) {
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					e.setCancelled(true);
					ExtrasInv.mainInv(p);
				}
			}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(LanguageInv.mainTitle)) {
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					e.setCancelled(true);
					LanguageInv.langInv(p);
				}
			}else if(e.getClickedBlock().getType() == Material.AIR || e.getClickedBlock().getType() == Material.FURNACE || e.getClickedBlock().getType() == Material.CHEST) {
				e.setCancelled(true);
			}else {
				e.setCancelled(false);
			}
		}
	}
}