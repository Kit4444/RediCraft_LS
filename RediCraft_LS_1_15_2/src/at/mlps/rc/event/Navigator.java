package at.mlps.rc.event;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import at.mlps.rc.api.ItemsAPI;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;

public class Navigator implements Listener{
	
	/*
	 * item = online
	 * blau = monitoring "§7- &9monitoring"
	 * gelb = offline "§7- §coffline"
	 * rot = gelockt "§7- §4locked"
	 */
	public static String title = "§aServer§cNavigator";
	static String dailyrew = "§aDaily Rewards";
	static String spawn = "§6Spawn";
	static String skyblock = "§a§fSky§2Block";
	static String creative = "§eCreative";
	static String survival = "§cSurvival";
	static String towny = "§6Towny";
	static String farmserver = "§5Farmserver";
	static String bauserver = "§bStaffserver";
	
	static String locked = " §7- §4locked";
	static String monitored = " §7- §9monitoring";
	static String offline = " §7- §eoffline";
	
	static File spawnfile = new File("plugins/RCLS/spawn.yml");
	
	//main servernavi
	public static void mainnavi(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, title);
		inv.setItem(0, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(1, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(3, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(4, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(5, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(6, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(7, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(8, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(9, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(11, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(12, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(14, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(15, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(17, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(18, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(19, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(21, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(22, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(23, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		inv.setItem(25, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		//inv w/ items
		inv.setItem(6, ItemsAPI.naviItem(Material.DIAMOND_PICKAXE, creative, "Creative"));
		inv.setItem(10, ItemsAPI.naviItem(Material.GRASS_BLOCK, skyblock, "SkyBlock"));
		inv.setItem(16, ItemsAPI.naviItem(Material.GOLDEN_SHOVEL, farmserver, "Farmserver"));
		inv.setItem(20, ItemsAPI.naviItem(Material.BRICKS, towny, "Towny"));
		inv.setItem(24, ItemsAPI.naviItem(Material.IRON_AXE, survival, "Survival"));
		if(p.hasPermission("mlps.isTeam")) {
			inv.setItem(26, ItemsAPI.naviItem(Material.WOODEN_AXE, bauserver, "Staffserver"));
		}else {
			inv.setItem(26, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		}
		inv.setItem(2, ItemsAPI.defItem(Material.EMERALD, 1, dailyrew)); //dailyrewards
		inv.setItem(13, ItemsAPI.defItem(Material.NETHER_STAR, 1, spawn)); //spawn
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(spawnfile);
		if(e.getView().getTitle().equalsIgnoreCase(title)) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(dailyrew)) {
				e.setCancelled(true);
				p.closeInventory();
				p.teleport(retLoc(cfg, "drew"));
				LanguageHandler.sendMSGReady(p, "event.navigator.local.dailyreward");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(spawn)) {
				e.setCancelled(true);
				p.closeInventory();
				p.teleport(retLoc(cfg, "general"));
				LanguageHandler.sendMSGReady(p, "event.navigator.local.spawn");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(creative)) {
				e.setCancelled(true);
				boolean lock = getData("Creative", "locked");
				if(lock) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Creative"));
				}else {
					boolean monitor = getData("Creative", "monitoring");
					boolean online = getData("Creative", "online");
					if(online) {
						if(monitor) {
							LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
							sendPlayer(p, "creative", creative);
						}else {
							sendPlayer(p, "creative", creative);
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Creative"));
					}
					
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(survival)) {
				e.setCancelled(true);
				boolean lock = getData("Survival", "locked");
				if(lock) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Survival"));
				}else {
					boolean monitor = getData("Survival", "monitoring");
					boolean online = getData("Survival", "online");
					if(online) {
						if(monitor) {
							LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
							sendPlayer(p, "survival", survival);
						}else {
							sendPlayer(p, "survival", survival);
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Survival"));
					}
					
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(skyblock)) {
				e.setCancelled(true);
				boolean lock = getData("SkyBlock", "locked");
				if(lock) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "SkyBlock"));
				}else {
					boolean monitor = getData("SkyBlock", "monitoring");
					boolean online = getData("SkyBlock", "online");
					if(online) {
						if(monitor) {
							LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
							sendPlayer(p, "skyblock", skyblock);
						}else {
							sendPlayer(p, "skyblock", skyblock);
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "SkyBlock"));
					}
					
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(farmserver)) {
				e.setCancelled(true);
				boolean lock = getData("Farmserver", "locked");
				if(lock) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Farmserver"));
				}else {
					boolean monitor = getData("Farmserver", "monitoring");
					boolean online = getData("Farmserver", "online");
					if(online) {
						if(monitor) {
							LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
							sendPlayer(p, "farmserver", farmserver);
						}else {
							sendPlayer(p, "farmserver", farmserver);
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Creative"));
					}
					
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(towny)) {
				e.setCancelled(true);
				boolean lock = getData("Towny", "locked");
				if(lock) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Towny"));
				}else {
					boolean monitor = getData("Towny", "monitoring");
					boolean online = getData("Towny", "online");
					if(online) {
						if(monitor) {
							LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
							sendPlayer(p, "towny", towny);
						}else {
							sendPlayer(p, "towny", towny);
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Towny"));
					}
					
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(bauserver)) {
				e.setCancelled(true);
				boolean lock = getData("Staffserver", "locked");
				if(lock) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Staffserver"));
				}else {
					boolean monitor = getData("Staffserver", "monitoring");
					boolean online = getData("Staffserver", "online");
					if(online) {
						if(monitor) {
							LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
							sendPlayer(p, "bauserver", bauserver);
						}else {
							sendPlayer(p, "bauserver", bauserver);
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Staffserver"));
					}
					
				}
			}else if(e.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
				e.setCancelled(true);
			}
		}
	}
	
	
	private static boolean getData(String server, String column) {
		boolean boo = false;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_serverstats WHERE servername = ?");
			ps.setString(1, server);
			ResultSet rs = ps.executeQuery();
			rs.next();
			boo = rs.getBoolean(column);
			ps.close();
			rs.close();
		}catch (SQLException e) { e.printStackTrace(); }
		return boo;
	}
	
	private static Main plugin;
	public Navigator(Main m) {
		plugin = m;
	}
	
	private static void sendPlayer(Player p, String server, String dserver) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.success").replace("%server", dserver));
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException e) {
			LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.failed");
		}
		p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}
	
	private Location retLoc(YamlConfiguration cfg, String type) {
		Location loc = new Location(Bukkit.getWorld(cfg.getString("Spawn." + type + ".WORLD")), cfg.getDouble("Spawn." + type + ".X"), cfg.getDouble("Spawn." + type + ".Y"), cfg.getDouble("Spawn." + type + ".Z"), (float)cfg.getDouble("Spawn." + type + ".YAW"), (float)cfg.getDouble("Spawn." + type + ".PITCH"));
		return loc;
	}
}