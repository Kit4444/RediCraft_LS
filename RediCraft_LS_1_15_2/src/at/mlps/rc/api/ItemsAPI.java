package at.mlps.rc.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import at.mlps.rc.mysql.lb.MySQL;

public class ItemsAPI {
	
	public static ItemStack defItem(Material mat, int avg, String dpname) {
		ItemStack is = new ItemStack(mat, avg);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dpname);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack potionItem(int avg, PotionType effect, String dpname) {
		ItemStack item = new ItemStack(Material.POTION, avg);
		PotionMeta potion = (PotionMeta) item.getItemMeta();
		potion.setBasePotionData(new PotionData(effect, false, false));
		potion.setDisplayName(dpname);
		item.setItemMeta(potion);
		return item;
	}
	
	public static ItemStack enchItem(Material mat, int avg, String dpname, Enchantment ench) {
		ItemStack item = new ItemStack(mat, avg);
		ItemMeta mitem = item.getItemMeta();
		mitem.setDisplayName(dpname);
		mitem.addEnchant(ench, 1, true);
		item.setItemMeta(mitem);
		return item;
	}
	
	public static ItemStack l2Item(Material mat, int avg, String dpname, String lore1, String lore2) {
	    ArrayList<String> lore = new ArrayList<String>();
	    ItemStack item = new ItemStack(mat, avg);
	    ItemMeta mitem = item.getItemMeta();
	    lore.add(lore1);
	    lore.add(lore2);
	    mitem.setLore(lore);
	    mitem.setDisplayName(dpname);
	    item.setItemMeta(mitem);
	    return item;
	  }
	
	@Deprecated
	public static ItemStack onlineItem(Material mat, int avg, String dpname, int online) {
		ArrayList<String> lore = new ArrayList<>();
		ItemStack item = new ItemStack(mat, avg);
		ItemMeta mitem = item.getItemMeta();
		lore.add("§aOnline§7: " + online);
		mitem.setLore(lore);
		mitem.setDisplayName(dpname);
		item.setItemMeta(mitem);
		return item;
	}
	
	public static ItemStack naviItem(Material mat, String dpname, String servername) {
		ArrayList<String> lore = new ArrayList<>();
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta mitem = item.getItemMeta();
		boolean online = getData(servername, "online");
		boolean locked = getData(servername, "locked");
		boolean monitor = getData(servername, "monitoring");
		if(online){
			lore.add("§7Online: §ayes");
			lore.add("§7Online: §a" + getPlayers(servername) + " §7Players");
		}else {
			lore.add("§7Online: §cno");
		}
		if(locked) {
			lore.add("§7Locked: §cyes");
		}
		if(monitor) {
			lore.add("§7Monitoring: §cyes");
		}
		mitem.setLore(lore);
		mitem.setDisplayName(dpname);
		item.setItemMeta(mitem);
		return item;
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
}