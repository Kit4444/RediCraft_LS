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
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;

public class LanguageInv implements Listener{
	
	public static String mainTitle = "§6Language";
	
	public static void langInv(Player p) {
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 1*9, mainTitle);
		ItemStack item = iapi.defItem(Material.ORANGE_STAINED_GLASS_PANE, 1, "§6");
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
					inv.setItem(1, iapi.defItem(Material.RED_CONCRETE_POWDER, 1, "§6German §f- §cAustria"));
					inv.setItem(3, iapi.enchItem(Material.CYAN_CONCRETE, 1, "§bEnglish", Enchantment.LUCK));
					inv.setItem(5, iapi.defItem(Material.ORANGE_CONCRETE_POWDER, 1, "§6German §f- §cHigh German"));
					inv.setItem(7, iapi.defItem(Material.BLUE_CONCRETE_POWDER, 1, "§9Dutch"));
				}else if(retLang(p).equalsIgnoreCase("de-de")) {
					inv.setItem(1, iapi.defItem(Material.RED_CONCRETE_POWDER, 1, "§6Deutsch §f- §cÖsterreich"));
					inv.setItem(3, iapi.defItem(Material.CYAN_CONCRETE_POWDER, 1, "§bEnglisch"));
					inv.setItem(5, iapi.enchItem(Material.ORANGE_CONCRETE, 1, "§6Deutsch §f- §cHochdeutsch", Enchantment.LUCK));
					inv.setItem(7, iapi.defItem(Material.BLUE_CONCRETE_POWDER, 1, "§9Niederländisch"));
				}else if(retLang(p).equalsIgnoreCase("nl-nl")) {
					inv.setItem(1, iapi.defItem(Material.RED_CONCRETE_POWDER, 1, "§6Duits §f- §cOostenrijk"));
					inv.setItem(3, iapi.defItem(Material.CYAN_CONCRETE_POWDER, 1, "§bEngels"));
					inv.setItem(5, iapi.defItem(Material.ORANGE_CONCRETE, 1, "§6Duits §f- §cHoogduits"));
					inv.setItem(7, iapi.enchItem(Material.BLUE_CONCRETE_POWDER, 1, "§9Nederlands", Enchantment.LUCK));
				}else if(retLang(p).equalsIgnoreCase("de-at")) {
					inv.setItem(1, iapi.enchItem(Material.RED_CONCRETE_POWDER, 1, "§6Deutsch §f- §cÖsterreichisch", Enchantment.LUCK));
					inv.setItem(3, iapi.defItem(Material.CYAN_CONCRETE_POWDER, 1, "§bEnglisch"));
					inv.setItem(5, iapi.defItem(Material.ORANGE_CONCRETE, 1, "§6Deutsch §f- §cHochdeutsch"));
					inv.setItem(7, iapi.defItem(Material.BLUE_CONCRETE_POWDER, 1, "§9Niederländisch"));
				}else {
					inv.setItem(1, iapi.defItem(Material.RED_CONCRETE_POWDER, 1, "§6German §f- §cAustria"));
					inv.setItem(3, iapi.defItem(Material.CYAN_CONCRETE_POWDER, 1, "§bEnglish"));
					inv.setItem(5, iapi.defItem(Material.ORANGE_CONCRETE_POWDER, 1, "§6German §f- §cHigh German"));
					inv.setItem(7, iapi.defItem(Material.BLUE_CONCRETE_POWDER, 1, "§9Dutch"));
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
				switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§6German §f- §cAustria": e.setCancelled(true); updateLang(p, "de-at"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§9Dutch": e.setCancelled(true); updateLang(p, "nl-nl"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§6German §f- §cHigh German": e.setCancelled(true); updateLang(p, "de-de"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§bEnglish": e.setCancelled(true); LanguageHandler.sendMSGReady(p, "event.language.alrSel"); break;
				}
			}else if(retLang(p).equalsIgnoreCase("de-de")) {
				switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§6Deutsch §f- §cÖsterreich": e.setCancelled(true); updateLang(p, "de-at"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§bEnglisch": e.setCancelled(true); updateLang(p, "en-uk"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§9Niederländisch": e.setCancelled(true); updateLang(p, "nl-nl"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§6Deutsch §f- §cHochdeutsch": e.setCancelled(true); LanguageHandler.sendMSGReady(p, "event.language.alrSel"); break;
				}
			}else if(retLang(p).equalsIgnoreCase("nl-nl")) {
				switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§6Duits §f- §cOostenrijk": e.setCancelled(true); updateLang(p, "de-at"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§bEngels": e.setCancelled(true); updateLang(p, "en-uk"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§6Duits §f- §cHoogduits": e.setCancelled(true); updateLang(p, "de-de"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§9Nederlands": e.setCancelled(true); LanguageHandler.sendMSGReady(p, "event.language.alrSel"); break;
				}
			}else if(retLang(p).equalsIgnoreCase("de-at")) {
				switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§9Niederländisch": e.setCancelled(true); updateLang(p, "nl-nl"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§bEnglisch": e.setCancelled(true); updateLang(p, "en-uk"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§6Deutsch §f- §cHochdeutsch": e.setCancelled(true); updateLang(p, "de-de"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§6Deutsch §f- §cÖsterreichisch": e.setCancelled(true); LanguageHandler.sendMSGReady(p, "event.language.alrSel"); break;
				}
			}else {
				switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§6German §f- §cAustria": e.setCancelled(true); updateLang(p, "de-at"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§bEnglish": e.setCancelled(true); updateLang(p, "en-uk"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§6German §f- §cHigh German": e.setCancelled(true); updateLang(p, "de-de"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
				case "§9Dutch": e.setCancelled(true); updateLang(p, "nl-nl"); LanguageHandler.sendMSGReady(p, "event.language.switch"); langInv(p); break;
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
			rs.next();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); return null; }
		return langKey;
	}
	
	private static void updateLang(Player p, String langKey) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET language = ? WHERE uuid = ?");
			ps.setString(1, langKey);
			ps.setString(2, p.getUniqueId().toString().replace("-", ""));
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}
}