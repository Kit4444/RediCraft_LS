package at.mlps.rc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import at.mlps.rc.api.Advents_API;
import at.mlps.rc.api.ItemsAPI;
import at.mlps.rc.api.Prefix;
import at.mlps.rc.main.Main;

public class Advents_Handler implements Listener{
	
	static int task = 0;
	static int days = 1;
	
	public static void setAdventInv(Player p) {
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 9*6, "§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr");
		Advents_API aapi = new Advents_API();
		List<Integer> slotsUsed = new ArrayList<>();
		HashMap<Integer, Integer> daySlot = new HashMap<>();
		Bukkit.getConsoleSender().sendMessage("DEBUG 1");
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if(days >= 25) {
					Bukkit.getScheduler().cancelTask(task);
				}else {
					int slot = random(1, 24);
					if(slotsUsed.contains(slot)) {
						slot = random(1, 24);	
					}
					slotsUsed.add(slot);
					daySlot.put(days, slot);
					days++;
				}
			}
		}, 0, 1);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				daySlot.forEach((key, value) -> Bukkit.getConsoleSender().sendMessage("Day: " + key + ", Slot: " + value));
			}
		}, 30);
		
		
		/*if(aapi.isAllowedDate("01")) {
			if(aapi.hasRewardUsed(p, 1)) {
				inv.setItem(slot1, iapi.defItem(Material.MINECART, 1, "§cDay 1"));
			}else {
				inv.setItem(slot1, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 1"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 1)) {
				inv.setItem(slot1, iapi.defItem(Material.MINECART, 1, "§cDay 1 §7- used"));
			}else {
				inv.setItem(slot1, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("02")) {
			if(aapi.hasRewardUsed(p, 2)) {
				inv.setItem(slot2, iapi.defItem(Material.MINECART, 1, "§cDay 2"));
			}else {
				inv.setItem(slot2, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 2"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 2)) {
				inv.setItem(slot2, iapi.defItem(Material.MINECART, 1, "§cDay 2 §7- used"));
			}else {
				inv.setItem(slot2, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("03")) {
			if(aapi.hasRewardUsed(p, 3)) {
				inv.setItem(slot3, iapi.defItem(Material.MINECART, 1, "§cDay 3"));
			}else {
				inv.setItem(slot3, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 3"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 3)) {
				inv.setItem(slot3, iapi.defItem(Material.MINECART, 1, "§cDay 3 §7- used"));
			}else {
				inv.setItem(slot3, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("04")) {
			if(aapi.hasRewardUsed(p, 4)) {
				inv.setItem(slot4, iapi.defItem(Material.MINECART, 1, "§cDay 4"));
			}else {
				inv.setItem(slot4, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 4"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 4)) {
				inv.setItem(slot4, iapi.defItem(Material.MINECART, 1, "§cDay 4 §7- used"));
			}else {
				inv.setItem(slot4, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("05")) {
			if(aapi.hasRewardUsed(p, 5)) {
				inv.setItem(slot5, iapi.defItem(Material.MINECART, 1, "§cDay 5"));
			}else {
				inv.setItem(slot5, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 5"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 5)) {
				inv.setItem(slot5, iapi.defItem(Material.MINECART, 1, "§cDay 5 §7- used"));
			}else {
				inv.setItem(slot5, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("06")) {
			if(aapi.hasRewardUsed(p, 6)) {
				inv.setItem(slot6, iapi.defItem(Material.MINECART, 1, "§cDay 6"));
			}else {
				inv.setItem(slot6, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 6"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 6)) {
				inv.setItem(slot6, iapi.defItem(Material.MINECART, 1, "§cDay 6 §7- used"));
			}else {
				inv.setItem(slot6, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("07")) {
			if(aapi.hasRewardUsed(p, 7)) {
				inv.setItem(slot7, iapi.defItem(Material.MINECART, 1, "§cDay 7"));
			}else {
				inv.setItem(slot7, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 7"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 7)) {
				inv.setItem(slot7, iapi.defItem(Material.MINECART, 1, "§cDay 7 §7- used"));
			}else {
				inv.setItem(slot7, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("08")) {
			if(aapi.hasRewardUsed(p, 8)) {
				inv.setItem(slot8, iapi.defItem(Material.MINECART, 1, "§cDay 8"));
			}else {
				inv.setItem(slot8, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 8"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 8)) {
				inv.setItem(slot8, iapi.defItem(Material.MINECART, 1, "§cDay 8 §7- used"));
			}else {
				inv.setItem(slot8, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("09")) {
			if(aapi.hasRewardUsed(p, 9)) {
				inv.setItem(slot9, iapi.defItem(Material.MINECART, 1, "§cDay 9"));
			}else {
				inv.setItem(slot9, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 9"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 9)) {
				inv.setItem(slot9, iapi.defItem(Material.MINECART, 1, "§cDay 9 §7- used"));
			}else {
				inv.setItem(slot9, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("10")) {
			if(aapi.hasRewardUsed(p, 10)) {
				inv.setItem(slot10, iapi.defItem(Material.MINECART, 1, "§cDay 10"));
			}else {
				inv.setItem(slot10, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 10"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 10)) {
				inv.setItem(slot10, iapi.defItem(Material.MINECART, 1, "§cDay 10 §7- used"));
			}else {
				inv.setItem(slot10, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("11")) {
			if(aapi.hasRewardUsed(p, 11)) {
				inv.setItem(slot11, iapi.defItem(Material.MINECART, 1, "§cDay 11"));
			}else {
				inv.setItem(slot11, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 11"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 11)) {
				inv.setItem(slot11, iapi.defItem(Material.MINECART, 1, "§cDay 11 §7- used"));
			}else {
				inv.setItem(slot11, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("12")) {
			if(aapi.hasRewardUsed(p, 12)) {
				inv.setItem(slot12, iapi.defItem(Material.MINECART, 1, "§cDay 12"));
			}else {
				inv.setItem(slot12, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 12"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 12)) {
				inv.setItem(slot12, iapi.defItem(Material.MINECART, 1, "§cDay 12 §7- used"));
			}else {
				inv.setItem(slot12, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("13")) {
			if(aapi.hasRewardUsed(p, 13)) {
				inv.setItem(slot13, iapi.defItem(Material.MINECART, 1, "§cDay 103"));
			}else {
				inv.setItem(slot13, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 13"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 13)) {
				inv.setItem(slot13, iapi.defItem(Material.MINECART, 1, "§cDay 13 §7- used"));
			}else {
				inv.setItem(slot13, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("14")) {
			if(aapi.hasRewardUsed(p, 14)) {
				inv.setItem(slot14, iapi.defItem(Material.MINECART, 1, "§cDay 14"));
			}else {
				inv.setItem(slot14, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 14"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 14)) {
				inv.setItem(slot14, iapi.defItem(Material.MINECART, 1, "§cDay 14 §7- used"));
			}else {
				inv.setItem(slot14, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("15")) {
			if(aapi.hasRewardUsed(p, 15)) {
				inv.setItem(slot15, iapi.defItem(Material.MINECART, 1, "§cDay 15"));
			}else {
				inv.setItem(slot15, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 15"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 15)) {
				inv.setItem(slot15, iapi.defItem(Material.MINECART, 1, "§cDay 15 §7- used"));
			}else {
				inv.setItem(slot15, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("16")) {
			if(aapi.hasRewardUsed(p, 16)) {
				inv.setItem(slot16, iapi.defItem(Material.MINECART, 1, "§cDay 16"));
			}else {
				inv.setItem(slot16, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 16"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 16)) {
				inv.setItem(slot16, iapi.defItem(Material.MINECART, 1, "§cDay 16 §7- used"));
			}else {
				inv.setItem(slot16, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("17")) {
			if(aapi.hasRewardUsed(p, 17)) {
				inv.setItem(slot17, iapi.defItem(Material.MINECART, 1, "§cDay 17"));
			}else {
				inv.setItem(slot17, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 17"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 17)) {
				inv.setItem(slot17, iapi.defItem(Material.MINECART, 1, "§cDay 17 §7- used"));
			}else {
				inv.setItem(slot17, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("18")) {
			if(aapi.hasRewardUsed(p, 18)) {
				inv.setItem(slot18, iapi.defItem(Material.MINECART, 1, "§cDay 18"));
			}else {
				inv.setItem(slot18, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 18"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 18)) {
				inv.setItem(slot18, iapi.defItem(Material.MINECART, 1, "§cDay 18 §7- used"));
			}else {
				inv.setItem(slot18, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("19")) {
			if(aapi.hasRewardUsed(p, 19)) {
				inv.setItem(slot19, iapi.defItem(Material.MINECART, 1, "§cDay 19"));
			}else {
				inv.setItem(slot19, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 19"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 19)) {
				inv.setItem(slot19, iapi.defItem(Material.MINECART, 1, "§cDay 19 §7- used"));
			}else {
				inv.setItem(slot19, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("20")) {
			if(aapi.hasRewardUsed(p, 20)) {
				inv.setItem(slot20, iapi.defItem(Material.MINECART, 1, "§cDay 20"));
			}else {
				inv.setItem(slot20, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 20"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 20)) {
				inv.setItem(slot20, iapi.defItem(Material.MINECART, 1, "§cDay 20 §7- used"));
			}else {
				inv.setItem(slot20, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("21")) {
			if(aapi.hasRewardUsed(p, 21)) {
				inv.setItem(slot21, iapi.defItem(Material.MINECART, 1, "§cDay 21"));
			}else {
				inv.setItem(slot21, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 21"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 21)) {
				inv.setItem(slot21, iapi.defItem(Material.MINECART, 1, "§cDay 21 §7- used"));
			}else {
				inv.setItem(slot21, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("22")) {
			if(aapi.hasRewardUsed(p, 22)) {
				inv.setItem(slot22, iapi.defItem(Material.MINECART, 1, "§cDay 22"));
			}else {
				inv.setItem(slot22, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 22"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 22)) {
				inv.setItem(slot22, iapi.defItem(Material.MINECART, 1, "§cDay 22 §7- used"));
			}else {
				inv.setItem(slot22, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("23")) {
			if(aapi.hasRewardUsed(p, 23)) {
				inv.setItem(slot23, iapi.defItem(Material.MINECART, 1, "§cDay 23"));
			}else {
				inv.setItem(slot23, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 23"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 23)) {
				inv.setItem(slot23, iapi.defItem(Material.MINECART, 1, "§cDay 23 §7- used"));
			}else {
				inv.setItem(slot23, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate("24")) {
			if(aapi.hasRewardUsed(p, 24)) {
				inv.setItem(slot24, iapi.defItem(Material.MINECART, 1, "§cDay 24"));
			}else {
				inv.setItem(slot24, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 24"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 24)) {
				inv.setItem(slot24, iapi.defItem(Material.MINECART, 1, "§cDay 24 §7- used"));
			}else {
				inv.setItem(slot24, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		//Player p = (Player) e.getWhoClicked();
		if(e.getView().getTitle().equalsIgnoreCase("§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay ")) {
				
			}
		}
		*/
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		if(ent.getType() == EntityType.SNOWMAN) {
			Snowman v = (Snowman) ent;
			if(v.getCustomName().equalsIgnoreCase("§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr")) {
				setAdventInv(p);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		Entity dmg = e.getDamager();
		Entity tar = e.getEntity();
		if(tar.getType() == EntityType.SNOWMAN) {
			if(dmg.getType() == EntityType.PLAYER) {
				Player p = (Player) dmg;
				e.setCancelled(true);
				p.sendMessage(Prefix.prefix("main") + "Hey, why you want to hurt our poor snowman?");
			}else {
				e.setCancelled(true);
			}
		}
	}

	private static int random(int low, int max) {
		Random r = new Random();
		int number = r.nextInt(max);
		while(number < low) {
			number = r.nextInt(max);
		}
		return number;
	}
}