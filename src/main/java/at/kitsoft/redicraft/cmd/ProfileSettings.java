package at.kitsoft.redicraft.cmd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import at.kitsoft.redicraft.api.APIs;
import at.kitsoft.redicraft.api.ActionLogger;
import at.kitsoft.redicraft.main.Main;
import at.kitsoft.redicraft.mysql.lb.MySQL;

public class ProfileSettings implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			
		}else {
			Player p = (Player)sender;
			APIs api = new APIs();
			openSettingsInv(p);
			p.sendMessage(api.prefix("default") + "§7The Profile Settings have been opened.");
			ActionLogger.log(api.getServerName(), p, "Player used profilesettings command.");
		}
		return true;
	}
	
	private static void openSettingsInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, "§6" + p.getName() + "'s Settings");
		APIs api = new APIs();
		if(getSetting(p, "disablePMs")) {
			inv.setItem(0, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cPrivate Messages"));
		}else {
			inv.setItem(0, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aPrivate Messages"));
		}
		if(getSetting(p, "disableTPAR")) {
			inv.setItem(1, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cTeleport Requests"));
		}else {
			inv.setItem(1, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aTeleport Requests"));
		}
		if(!getSetting(p, "setAutoAFK")) {
			inv.setItem(2, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cSet Auto-AFK"));
		}else {
			inv.setItem(2, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aSet Auto-AFK"));
		}
		inv.setItem(4, api.defItem(Material.BOOK, 1, "§aLanguage"));
		if(getSetting(p, "showIDdsc")) {
			inv.setItem(9, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aShow ID Discord"));
		}else {
			inv.setItem(9, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cShow ID Discord"));
		}
		if(getSetting(p, "showRoledsc")) {
			inv.setItem(10, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aShow Role Discord"));
		}else {
			inv.setItem(10, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cShow Role Discord"));
		}
		if(getSetting(p, "showNickdsc")) {
			inv.setItem(11, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aShow Nick Discord"));
		}else {
			inv.setItem(11, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cShow Nick Discord"));
		}
		inv.setItem(13, api.defItem(Material.OAK_SIGN, 1, "§aScoreboard"));
		if(p.hasPermission("mlps.canBan")) {
			if(getSetting(p, "loggedin")) {
				inv.setItem(18, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aLogout"));
			}else {
				inv.setItem(18, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cLogin"));
			}
			if(getSetting(p, "vanishCountinTab")) {
				inv.setItem(22, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aShow Vanish"));
			}else {
				inv.setItem(22, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cShow Vanish"));
			}
			if(getSetting(p, "vanish")) {
				inv.setItem(23, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aVanish"));
			}else {
				inv.setItem(23, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cVanish"));
			}
		}
		if(p.hasPermission("mlps.isTeam") || p.hasPermission("mlps.isStaff")) {
			if(getSetting(p, "enableAFKkick")) {
				inv.setItem(24, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§aAFK Kick"));
			}else {
				inv.setItem(24, api.defItem(Material.GREEN_CONCRETE_POWDER, 1, "§aAFK Kick"));
			}
		}
		p.openInventory(inv);
		p.updateInventory();
	}
	
	private static void openSettingsSBInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*2, "§6" + p.getName() + "'s Scoreboardsettings");
		APIs api = new APIs();
		if(Main.serverlist.contains(api.getServerName())) {
			inv.setItem(1, api.defItem(Material.BARRIER, 1, "§cOff"));
			inv.setItem(3, api.defItem(Material.EMERALD, 1, "§aDefault"));
			inv.setItem(5, api.defItem(Material.DIAMOND_PICKAXE, 1, "§bJobs"));
			inv.setItem(7, api.defItem(Material.JUKEBOX, 1, "§6RediFM"));
			inv.setItem(10, api.skullItem(1, "§3Players", p.getName()));
			inv.setItem(12, api.defItem(Material.MAP, 1, "§5Location"));
			if(p.hasPermission("mlps.canBan")) {
				inv.setItem(14, api.defItem(Material.DIAMOND_SWORD, 1, "§cAdmins"));
			}else {
				inv.setItem(14, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cHidden"));
			}
			if(p.hasPermission("mlps.isSA")) {
				inv.setItem(16, api.defItem(Material.SHIELD, 1, "§dData"));
			}else {
				inv.setItem(16, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cHidden"));
			}
		}else {
			inv.setItem(1, api.defItem(Material.BARRIER, 1, "§cOff"));
			inv.setItem(3, api.defItem(Material.EMERALD, 1, "§aDefault"));
			inv.setItem(5, api.defItem(Material.JUKEBOX, 1, "§6RediFM"));
			inv.setItem(7, api.skullItem(1, "§3Players", p.getName()));
			inv.setItem(11, api.defItem(Material.MAP, 1, "§5Location"));
			if(p.hasPermission("mlps.canBan")) {
				inv.setItem(13, api.defItem(Material.DIAMOND_SWORD, 1, "§cAdmins"));
			}else {
				inv.setItem(13, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cHidden"));
			}
			if(p.hasPermission("mlps.isSA")) {
				inv.setItem(15, api.defItem(Material.SHIELD, 1, "§dData"));
			}else {
				inv.setItem(15, api.defItem(Material.RED_CONCRETE_POWDER, 1, "§cHidden"));
			}
		}
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		APIs api = new APIs();
		if(e.getView().getTitle().equalsIgnoreCase("§6" + p.getName() + "'s Settings")) {
			e.setCancelled(true);
			switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
			case "§aPrivate Messages": updateSetting(p, "disablePMs", true); api.sendMSGReady(p, "profilesettings.general.pm.enable"); openSettingsInv(p); break;
			case "§cPrivate Messages": updateSetting(p, "disablePMs", false); api.sendMSGReady(p, "profilesettings.general.pm.disable"); openSettingsInv(p); break;
			case "§aTeleport Requests": updateSetting(p, "disableTPAR", true); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.tpr.enable"); break;
			case "§cTeleport Requests": updateSetting(p, "disableTPAR", false); api.sendMSGReady(p, "profilesettings.general.tpr.disable"); openSettingsInv(p); break;
			case "§cShow ID Discord": updateSetting(p, "showIDdsc", true); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.discord_id.enable"); break;
			case "§aShow ID Discord": updateSetting(p, "showIDdsc", false); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.discord_id.disable"); break;
			case "§cShow Nick Discord": updateSetting(p, "showNickdsc", true); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.discord_nick.enable"); break;
			case "§aShow Nick Discord": updateSetting(p, "showNickdsc", false); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.discord_nick.disable"); break;
			case "§cShow Role Discord": updateSetting(p, "showRoledsc", true); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.discord_role.enable"); break;
			case "§aShow Role Discord": updateSetting(p, "showRoledsc", false); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.discord_role.disable"); break;
			case "§cLogin": updateSetting(p, "loggedin", true); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.login"); break;
			case "§aLogout": updateSetting(p, "loggedin", false); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.logout"); break;
			case "§cSet Auto-AFK": updateSetting(p, "setAutoAFK", true); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.autoafk.enable"); break;
			case "§aSet Auto-AFK": updateSetting(p, "setAutoAFK", false); openSettingsInv(p); api.sendMSGReady(p, "profilesettings.general.autoafk.disable"); break;
			case "§aScoreboard": p.closeInventory(); openSettingsSBInv(p);
			}
		}else if(e.getView().getTitle().equalsIgnoreCase("§6" + p.getName() + "'s Scoreboardsettings")) {
			e.setCancelled(true);
			switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
			case "§cOff": updateSB(p, 0); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.off"); break;
			case "§aDefault": updateSB(p, 1); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.default"); break;
			case "§6RediFM": updateSB(p, 5); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.redifm"); break;
			case "§3Players": updateSB(p, 6); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.players"); break;
			case "§5Location": updateSB(p, 7); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.location"); break;
			case "§bJobs": updateSB(p, 2); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.jobs"); break;
			case "§cAdmins": updateSB(p, 3); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.admin"); break;
			case "§dData": updateSB(p, 4); openSettingsSBInv(p); api.sendMSGReady(p, "profilesettings.scoreboard.data"); break;
			case "§cHidden": api.sendMSGReady(p, "profilesettings.scoreboard.hidden"); break;
			}
		}
	}
	
	static boolean getSetting(Player p, String setting) {
		boolean boo = false;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			boo = rs.getBoolean(setting);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	static void updateSetting(Player p, String setting, boolean newState) {
		APIs api = new APIs();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET " + setting + " = ? WHERE uuid = ?");
			ps.setBoolean(1, newState);
			ps.setString(2, p.getUniqueId().toString().replace("-", ""));
			ps.executeUpdate();
			ActionLogger.log(api.getServerName(), p, "Player updated in profilesettings " + setting + " to " + newState);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateSB(Player p, int id) {
		APIs api = new APIs();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET scoreboard = ? WHERE uuid = ?");
			ps.setInt(1, id);
			ps.setString(2, p.getUniqueId().toString().replace("-", ""));
			ps.executeUpdate();
			ps.close();
			ActionLogger.log(api.getServerName(), p, "Player updated in profilesettings/scoreboard to " + id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}