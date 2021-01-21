package at.mlps.rc.event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import at.mlps.rc.api.ItemsAPI;
import at.mlps.rc.api.Prefix;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;

public class LanguageInv implements Listener{
	
	public static String mainTitle = "§6Language";
	
	public static void langInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 1*9, mainTitle);
		ItemStack item = ItemsAPI.defItem(Material.ORANGE_STAINED_GLASS_PANE, 1, "§6");
		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(4, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if(retLang(p).equalsIgnoreCase("en-uk")) {
					inv.setItem(3, ItemsAPI.enchItem(Material.CYAN_CONCRETE, 1, "§bEnglish", Enchantment.LUCK));
					inv.setItem(5, ItemsAPI.defItem(Material.ORANGE_CONCRETE_POWDER, 1, "§6German"));
				}else if(retLang(p).equalsIgnoreCase("de-de")) {
					inv.setItem(3, ItemsAPI.defItem(Material.CYAN_CONCRETE_POWDER, 1, "§bEnglisch"));
					inv.setItem(5, ItemsAPI.enchItem(Material.ORANGE_CONCRETE, 1, "§6Deutsch", Enchantment.LUCK));
				}else {
					inv.setItem(3, ItemsAPI.defItem(Material.CYAN_CONCRETE_POWDER, 1, "§bEnglish"));
					inv.setItem(5, ItemsAPI.defItem(Material.ORANGE_CONCRETE_POWDER, 1, "§6German"));
				}
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getView().getTitle().equalsIgnoreCase(mainTitle)) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6")) {
				e.setCancelled(true);
			}if(retLang(p).equalsIgnoreCase("en-uk")) {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bEnglish")) {
					e.setCancelled(true);
					p.sendMessage(Prefix.prefix("main") + "§7You have english already selected.");
					langInv(p);
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6German")) {
					e.setCancelled(true);
					updateLang(p, "de-de");
					p.sendMessage(Prefix.prefix("main") + "§7Du hast nun die deutsche Sprache ausgewählt.");
					langInv(p);
				}
			}else if(retLang(p).equalsIgnoreCase("de-de")) {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bEnglisch")) {
					e.setCancelled(true);
					updateLang(p, "en-uk");
					p.sendMessage(Prefix.prefix("main") + "§7You have now selected the english language.");
					langInv(p);
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Deutsch")) {
					e.setCancelled(true);
					p.sendMessage(Prefix.prefix("main") + "§7Du hast Deutsch bereits ausgewählt.");
					langInv(p);
				}
			}else {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bEnglish")) {
					e.setCancelled(true);
					updateLang(p, "en-uk");
					p.sendMessage(Prefix.prefix("main") + "§7You have now selected the english language.");
					langInv(p);
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6German")) {
					e.setCancelled(true);
					updateLang(p, "de-de");
					p.sendMessage(Prefix.prefix("main") + "§7Du hast nun die deutsche Sprache ausgewählt.");
					langInv(p);
				}
			}
		}
	}
	
	private static String retLang(Player p) {
		String langKey = "en-UK";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			langKey = rs.getString("language");
		}catch (SQLException e) { e.printStackTrace(); return null; }
		return langKey;
	}
	
	private static void updateLang(Player p, String langKey) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET language = ? WHERE uuid = ?");
			ps.setString(1, langKey);
			ps.setString(2, p.getUniqueId().toString().replace("-", ""));
			ps.executeUpdate();
		}catch (SQLException e) { e.printStackTrace(); }
	}
}