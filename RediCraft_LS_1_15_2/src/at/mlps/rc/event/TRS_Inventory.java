package at.mlps.rc.event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import at.mlps.rc.api.ItemsAPI;
import at.mlps.rc.api.TRS_API;
import at.mlps.rc.cmd.MoneyAPI;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class TRS_Inventory implements Listener{
	
	public static void trsinv(Player p) {
		Inventory inv = null;
		if(p.hasPermission("mlps.drew.premium")) {
			inv = Bukkit.createInventory(null, 5*9, "§aDaily §cRewards");
			if(TRS_API.isAllowed(p, "default.default")) {
				inv.setItem(11, ItemsAPI.defItem(Material.CHEST_MINECART, 1, "§aReward Fix"));
			}else {
				inv.setItem(11, ItemsAPI.l2Item(Material.MINECART, 1, "§aReward Fix", "§7Remaining Time", "§a" + time(p, "default.default")));
			}
			if(TRS_API.isAllowed(p, "default.random")) {
				inv.setItem(15, ItemsAPI.defItem(Material.CHEST_MINECART, 1, "§aReward Random"));
			}else {
				inv.setItem(15, ItemsAPI.l2Item(Material.MINECART, 1, "§aReward Random", "§7Remaining Time", "§a" + time(p, "default.random")));
			}
			if(TRS_API.isAllowed(p, "premium.default")) {
				inv.setItem(29, ItemsAPI.defItem(Material.CHEST_MINECART, 1, "§aReward Fix §ePremium"));
			}else {
				inv.setItem(29, ItemsAPI.l2Item(Material.MINECART, 1, "§aReward Fix §ePremium", "§7Remaining Time", "§a" + time(p, "premium.default")));
			}
			if(TRS_API.isAllowed(p, "premium.random")) {
				inv.setItem(33, ItemsAPI.defItem(Material.CHEST_MINECART, 1, "§aReward Random §ePremium"));
			}else {
				inv.setItem(33, ItemsAPI.l2Item(Material.MINECART, 1, "§aReward Random §ePremium", "§7Remaining Time", "§a" + time(p, "premium.random")));
			}
		}else {
			inv = Bukkit.createInventory(null, 3*9, "§aDaily §cRewards");
			if(TRS_API.isAllowed(p, "default.default")) {
				inv.setItem(11, ItemsAPI.defItem(Material.CHEST_MINECART, 1, "§aReward Fix"));
			}else {
				inv.setItem(11, ItemsAPI.l2Item(Material.MINECART, 1, "§aReward Fix", "§7Remaining Time", "§a" + time(p, "default.default")));
			}
			if(TRS_API.isAllowed(p, "default.random")) {
				inv.setItem(15, ItemsAPI.defItem(Material.CHEST_MINECART, 1, "§aReward Random"));
			}else {
				inv.setItem(15, ItemsAPI.l2Item(Material.MINECART, 1, "§aReward Random", "§7Remaining Time", "§a" + time(p, "default.random")));
			}
		}
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(e.getView().getTitle().equalsIgnoreCase("§aDaily §cRewards")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aReward Fix")) {
				e.setCancelled(true);
				if(TRS_API.isAllowed(p, "default.default")) {
					int money = 2500;
					TRS_API.setReward(p, "default.default");
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.claimed").replace("%type", "§aDefault Reward").replace("|", "\n").replace("%money", "2500"));
					MoneyAPI.addMoney(p.getUniqueId().toString(), money);
					trsinv(p);
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.await").replace("%time", time(p, "default.default")));
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aReward Fix §ePremium")) {
				e.setCancelled(true);
				if(TRS_API.isAllowed(p, "premium.default")) {
					int money = 5000;
					TRS_API.setReward(p, "premium.default");
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.claimed").replace("%type", "§aDefault Reward §ePremium").replace("|", "\n").replace("%money", "5000"));
					MoneyAPI.addMoney(p.getUniqueId().toString(), money);
					trsinv(p);
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.await").replace("%time", time(p, "premium.default")));
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aReward Random")) {
				e.setCancelled(true);
				if(TRS_API.isAllowed(p, "default.random")) {
					int money = random(0, 2500);
					TRS_API.setReward(p, "default.random");
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.claimed").replace("%type", "§aRandom Reward").replace("|", "\n").replace("%money", String.valueOf(money)));
					MoneyAPI.addMoney(p.getUniqueId().toString(), money);
					trsinv(p);
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.await").replace("%time", time(p, "default.random")));
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aReward Random §ePremium")) {
				e.setCancelled(true);
				if(TRS_API.isAllowed(p, "premium.random")) {
					int money = random(2500, 5000);
					TRS_API.setReward(p, "premium.random");
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.claimed").replace("%type", "§aRandom Reward §ePremium").replace("|", "\n").replace("%money", String.valueOf(money)));
					MoneyAPI.addMoney(p.getUniqueId().toString(), money);
					trsinv(p);
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.dailyrewards.await").replace("%time", time(p, "premium.random")));
				}
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Villager v = (Villager) e.getRightClicked();
		if(v.getCustomName().equalsIgnoreCase("§aDaily §cRewards")) {
			e.setCancelled(true);
			trsinv(p);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		Player p = (Player) e.getDamager();
		Villager v = (Villager) e.getEntity();
		if(v.getCustomName().equals("§aDaily §cRewards")) {
			e.setCancelled(true);
			LanguageHandler.sendMSGReady(p, "event.shopvillager.hurt");
		}else {
			
		}
	}
	
	private int random(int low, int max) {
		Random r = new Random();
		int number = r.nextInt(max);
		while(number < low) {
			number = r.nextInt(max);
		}
		return number;
	}
	
	private static String time(Player p, String path) {
		long current = System.currentTimeMillis();
		long ms = TRS_API.getTime(p, path);
		long diff = ms - current;
		return TRS_API.remainingTime(diff);
	}
}