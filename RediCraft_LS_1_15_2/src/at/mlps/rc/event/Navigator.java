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
		if(p.hasPermission("mlps.isTeam")) {
			if(getData("Staffserver", "locked")) {
				inv.setItem(26, ItemsAPI.defItem(Material.RED_STAINED_GLASS_PANE, 1, bauserver + locked));
			}else {
				if(getData("Staffserver", "monitoring")) {
					inv.setItem(26, ItemsAPI.defItem(Material.BLUE_STAINED_GLASS_PANE, 1, bauserver + monitored));
				}else {
					if(getData("Staffserver", "online")) {
						inv.setItem(26, ItemsAPI.onlineItem(Material.WOODEN_AXE, 1, bauserver, getPlayers("Staffserver"))); //bauserver
					}else {
						inv.setItem(26, ItemsAPI.defItem(Material.YELLOW_STAINED_GLASS_PANE, 1, bauserver + offline));
					}
				}
			}
		}else {
			inv.setItem(26, ItemsAPI.defItem(Material.BLACK_STAINED_GLASS_PANE, 1, "§0"));
		}
		if(getData("Creative", "locked")) {
			inv.setItem(6, ItemsAPI.defItem(Material.RED_STAINED_GLASS_PANE, 1, creative + locked));
		}else {
			if(getData("Creative", "monitoring")) {
				inv.setItem(6, ItemsAPI.defItem(Material.BLUE_STAINED_GLASS_PANE, 1, creative + monitored));
			}else {
				if(getData("Creative", "online")) {
					inv.setItem(6, ItemsAPI.onlineItem(Material.DIAMOND_PICKAXE, 1, creative, getPlayers("Creative"))); //creative
				}else {
					inv.setItem(6, ItemsAPI.defItem(Material.YELLOW_STAINED_GLASS_PANE, 1, creative + offline));
				}
			}
		}
		if(getData("SkyBlock", "locked")) {
			inv.setItem(10, ItemsAPI.defItem(Material.RED_STAINED_GLASS_PANE, 1, skyblock + locked));
		}else {
			if(getData("SkyBlock", "monitoring")) {
				inv.setItem(10, ItemsAPI.defItem(Material.BLUE_STAINED_GLASS_PANE, 1, skyblock + monitored));
			}else {
				if(getData("SkyBlock", "online")) {
					inv.setItem(10, ItemsAPI.onlineItem(Material.GRASS_BLOCK, 1, skyblock, getPlayers("SkyBlock"))); //skyblock
				}else {
					inv.setItem(10, ItemsAPI.defItem(Material.YELLOW_STAINED_GLASS_PANE, 1, skyblock + offline));
				}
			}
		}
		if(getData("Farmserver", "locked")) {
			inv.setItem(16, ItemsAPI.defItem(Material.RED_STAINED_GLASS_PANE, 1, farmserver + locked));
		}else {
			if(getData("Farmserver", "monitoring")) {
				inv.setItem(16, ItemsAPI.defItem(Material.BLUE_STAINED_GLASS_PANE, 1, farmserver + monitored));
			}else {
				if(getData("Farmserver", "online")) {
					inv.setItem(16, ItemsAPI.onlineItem(Material.GOLDEN_SHOVEL, 1, farmserver, getPlayers("Farmserver"))); //farmserver
				}else {
					inv.setItem(16, ItemsAPI.defItem(Material.YELLOW_STAINED_GLASS_PANE, 1, farmserver + offline));
				}
			}
		}
		if(getData("Towny", "locked")) {
			inv.setItem(20, ItemsAPI.defItem(Material.RED_STAINED_GLASS_PANE, 1, towny + locked));
		}else {
			if(getData("Towny", "monitoring")) {
				inv.setItem(20, ItemsAPI.defItem(Material.BLUE_STAINED_GLASS_PANE, 1, towny + monitored));
			}else {
				if(getData("Towny", "online")) {
					inv.setItem(20, ItemsAPI.onlineItem(Material.BRICKS, 1, towny, getPlayers("Towny"))); //towny
				}else {
					inv.setItem(20, ItemsAPI.defItem(Material.YELLOW_STAINED_GLASS_PANE, 1, towny + offline));
				}
			}
		}
		if(getData("Survival", "locked")) {
			inv.setItem(24, ItemsAPI.defItem(Material.RED_STAINED_GLASS_PANE, 1, survival + locked));
		}else {
			if(getData("Survival", "monitoring")) {
				inv.setItem(24, ItemsAPI.defItem(Material.BLUE_STAINED_GLASS_PANE, 1, survival + monitored));
			}else {
				if(getData("Survival", "online")) {
					inv.setItem(24, ItemsAPI.onlineItem(Material.IRON_AXE, 1, survival, getPlayers("Survival"))); //survival
				}else {
					inv.setItem(24, ItemsAPI.defItem(Material.YELLOW_STAINED_GLASS_PANE, 1, survival + offline));
				}
			}
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
				sendPlayer(p, "creative", creative);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(creative + locked)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Creative"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(creative + offline)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Creative"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(creative + monitored)) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
				sendPlayer(p, "creative", creative);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(survival)) {
				e.setCancelled(true);
				sendPlayer(p, "survival", survival);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(survival + locked)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Survival"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(survival + offline)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Survival"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(survival + monitored)) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
				sendPlayer(p, "survival", survival);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(skyblock)) {
				e.setCancelled(true);
				sendPlayer(p, "skyblock", skyblock);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(skyblock+ locked)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "SkyBlock"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(skyblock + offline)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "SkyBlock"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(skyblock + monitored)) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
				sendPlayer(p, "skyblock", skyblock);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(farmserver)) {
				e.setCancelled(true);
				sendPlayer(p, "farmserver", farmserver);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(farmserver + locked)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Farmserver"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(farmserver + offline)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Farmserver"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(farmserver + monitored)) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
				sendPlayer(p, "farmserver", farmserver);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(towny)) {
				e.setCancelled(true);
				sendPlayer(p, "towny", towny);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(towny + locked)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Towny"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(towny + offline)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Towny"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(towny + monitored)) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
				sendPlayer(p, "towny", towny);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(bauserver)) {
				e.setCancelled(true);
				sendPlayer(p, "bauserver", bauserver);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(bauserver + locked)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.locked").replace("%server", "Staffserver"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(bauserver + offline)) {
				e.setCancelled(true);
				p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "event.navigator.sendPlayer.offline").replace("%server", "Staffserver"));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(bauserver + monitored)) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.navigator.sendPlayer.monitorinfo");
				sendPlayer(p, "bauserver", bauserver);
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
	
	private static int getPlayers(String server) {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_serverstats WHERE servername = ?");
			ps.setString(1, server);
			ResultSet rs = ps.executeQuery();
			rs.next();
			i = rs.getInt("currPlayers");
			ps.close();
			rs.close();
		}catch (SQLException e) { e.printStackTrace(); }
		return i;
	}
	
	private Location retLoc(YamlConfiguration cfg, String type) {
		Location loc = new Location(Bukkit.getWorld(cfg.getString("Spawn." + type + ".WORLD")), cfg.getDouble("Spawn." + type + ".X"), cfg.getDouble("Spawn." + type + ".Y"), cfg.getDouble("Spawn." + type + ".Z"), (float)cfg.getDouble("Spawn." + type + ".YAW"), (float)cfg.getDouble("Spawn." + type + ".PITCH"));
		return loc;
	}
}