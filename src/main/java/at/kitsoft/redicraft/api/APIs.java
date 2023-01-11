package at.kitsoft.redicraft.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import at.kitsoft.redicraft.mysql.lb.MySQL;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class APIs {
	
	public static HashMap<String, String> langCache_DE = new HashMap<>();
	public static HashMap<String, String> langCache_EN = new HashMap<>();
	
	public void loadConfig() {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicraft_languagestrings");
			ResultSet rs = ps.executeQuery();
			if(!langCache_DE.isEmpty()) {
				langCache_DE.clear();
			}
			if(!langCache_EN.isEmpty()) {
				langCache_EN.clear();
			}
			while(rs.next()) {
				langCache_DE.put(rs.getString("lang_key"), rs.getString("German"));
				langCache_EN.put(rs.getString("lang_key"), rs.getString("English"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void noPerm(Player p) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(prefix("main") + retString("en-uk", "noPerm"));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(prefix("main") + retString("de-de", "noPerm"));
		}
	}
	
	public void notAvailable(Player p) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(prefix("main") + retString("en-uk", "notAvailable"));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(prefix("main") + retString("de-de", "notAvailable"));
		}
	}
	
	public void sendMSGReady(Player p, String path) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(prefix("main") + retString("en-uk", path));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(prefix("main") + retString("de-de", path));
		}
	}
	
	public String returnStringReady(Player p, String path) {
		String s = "";
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			s = retString("en-uk", path);
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			s = retString("de-de", path);
		}
		return s;
	}
	
	private String retLang(Player p) {
		String langKey = "en-UK";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			langKey = rs.getString("language");
		}catch (SQLException e) { e.printStackTrace(); }
		return langKey;
	}

	private String retString(String lang, String path) {
		
		String string = "";
		if(lang.equalsIgnoreCase("en-uk")) {
			if(langCache_EN.containsKey(path)) {
				string = langCache_EN.get(path).replace("&", "§");
			}else {
				string = "§cThis path doesn't exists.";
			}
		}else if(lang.equalsIgnoreCase("de-de")) {
			if(langCache_DE.containsKey(path)) {
				string = langCache_DE.get(path).replace("&", "§");
			}else {
				string = "§cDieser Pfad existiert nicht.";
			}
		}
		return string;
	}
	
	public String getServerName() {
		File file = new File("server.properties");
		Properties p = new Properties();
		String s = "";
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
			p.load(bis);
		}catch (Exception ex) {
			s = "null";
		}
		s = p.getProperty("server-name");
		return s;
	}
	
	public String getServerId() {
		File file = new File("server.properties");
		Properties p = new Properties();
		String s = "";
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
			p.load(bis);
		}catch (Exception ex) {
			s = "null";
		}
		s = p.getProperty("server-id");
		return s;
	}
	
	public int getPlayers(String server, String type) {
		int i = 0;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_serverstats WHERE servername = ?");
			ps.setString(1, server);
			ResultSet rs = ps.executeQuery();
			rs.next();
			i = rs.getInt(type);
		}catch (SQLException e) { e.printStackTrace(); }
		return i;
	}
	
	public String getUUIDfromName(String name) {
		String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
		String uuid = "";
		try {
			@SuppressWarnings("deprecation")
			String UUIDJson = IOUtils.toString(new URL(url));
			if(UUIDJson.isEmpty()) return "ERRORED";
			JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
			uuid =  UUIDObject.get("id").toString();
		}catch(Exception e) {
			uuid = "ERRORED";
		}
		return uuid;
	}
	
	public String getNamefromUUID(String uuid) {
		String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
		String name = "";
		try {
			@SuppressWarnings("deprecation")
			String nameJson = IOUtils.toString(new URL(url));
			JSONArray nameVal = (JSONArray) JSONValue.parseWithException(nameJson);
			String playerSlot = nameVal.get(nameVal.size()-1).toString();
			JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			name =  UUIDObject.get("name").toString();
		}catch(Exception e) {
			name = "ERRORED";
		}
		return name;
	}
	
	public static HashMap<String, String> prefix = new HashMap<>();
	
	public void onLoad() {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_igprefix");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				prefix.put(rs.getString("type"), rs.getString("prefix"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String prefix(String type) {
		String s = "";
		if(type.equalsIgnoreCase("main") || type.equalsIgnoreCase("prefix")) {
			s = prefix.get("main");
		}else if(type.equalsIgnoreCase("scoreboard")) {
			s = prefix.get("scoreboard");
		}else if(type.equalsIgnoreCase("pmsystem") || type.equalsIgnoreCase("pm")) {
			s = prefix.get("pmsys");
		}else if(type.equalsIgnoreCase("system")) {
			s = prefix.get("system");
		}
		return s;
	}
	
	public ItemStack defItem(Material mat, int avg, String dpname) {
		ItemStack is = new ItemStack(mat, avg);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(dpname);
		is.setItemMeta(im);
		return is;
	}
	
	public ItemStack potionItem(int avg, PotionType effect, String dpname) {
		ItemStack item = new ItemStack(Material.POTION, avg);
		PotionMeta potion = (PotionMeta) item.getItemMeta();
		potion.setBasePotionData(new PotionData(effect, false, false));
		potion.setDisplayName(dpname);
		item.setItemMeta(potion);
		return item;
	}
	
	public ItemStack enchItem(Material mat, int avg, String dpname, Enchantment ench) {
		ItemStack item = new ItemStack(mat, avg);
		ItemMeta mitem = item.getItemMeta();
		mitem.setDisplayName(dpname);
		mitem.addEnchant(ench, 1, true);
		item.setItemMeta(mitem);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack skullItem(int avg, String dpname, String skullowner) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, avg);
		SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
		skullmeta.setOwner(skullowner);
		skullmeta.setDisplayName(dpname);
		skull.setItemMeta(skullmeta);
		return skull;
	}
	
	public ItemStack bannerTest() {
		ItemStack is = new ItemStack(Material.GRAY_BANNER, 1);
		BannerMeta bm = (BannerMeta) is.getItemMeta();
		List<Pattern> patterns = new ArrayList<>();
		patterns.add(new Pattern(DyeColor.RED, PatternType.BRICKS));
		patterns.add(new Pattern(DyeColor.YELLOW, PatternType.STRIPE_DOWNLEFT));
		bm.setPatterns(patterns);
		bm.setDisplayName("§cTest");
		is.setItemMeta(bm);
		return is;
	}
	
	public ItemStack l2Item(Material mat, int avg, String dpname, String lore1, String lore2) {
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
	public ItemStack onlineItem(Material mat, int avg, String dpname, int online) {
		ArrayList<String> lore = new ArrayList<>();
		ItemStack item = new ItemStack(mat, avg);
		ItemMeta mitem = item.getItemMeta();
		lore.add("§aOnline§7: " + online);
		mitem.setLore(lore);
		mitem.setDisplayName(dpname);
		item.setItemMeta(mitem);
		return item;
	}
	
	public ItemStack naviItem(Material mat, String dpname, String servername) {
		ArrayList<String> lore = new ArrayList<>();
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta mitem = item.getItemMeta();
		boolean online = getData(servername, "online");
		boolean locked = getData(servername, "locked");
		boolean monitor = getData(servername, "monitoring");
		String entrylevel = getEntrylevel(servername);
		if(entrylevel.equalsIgnoreCase(ServerEntryLevel.ALL.toString())) {
			lore.add("§7Server Entrylevel:");
			lore.add("§aEveryone");
			lore.add("§f");
		}else if(entrylevel.equalsIgnoreCase(ServerEntryLevel.STAFF.toString())) {
			lore.add("§7Server Entrylevel:");
			lore.add("§eStaff");
			lore.add("§f");
		}else if(entrylevel.equalsIgnoreCase(ServerEntryLevel.ALPHA.toString())) {
			lore.add("§7Server Entrylevel:");
			lore.add("§cAlphatester");
			lore.add("§f");
		}else if(entrylevel.equalsIgnoreCase(ServerEntryLevel.BETA.toString())) {
			lore.add("§7Server Entrylevel:");
			lore.add("§dBetatester");
			lore.add("§f");
		}
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
	
	public ItemStack wt_item(Material mat, String dpname, String bukkitWorld) {
		ArrayList<String> lore = new ArrayList<>();
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta mitem = item.getItemMeta();
		lore.add("§7Current User on");
		lore.add("§a" + dpname + "§7: §a" + Bukkit.getWorld(bukkitWorld).getPlayers().size());
		mitem.setLore(lore);
		mitem.setDisplayName(dpname);
		item.setItemMeta(mitem);
		return item;
	}
	
	private boolean getData(String server, String column) {
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
	
	private String getEntrylevel(String server) {
		String level = "";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_serverstats WHERE servername = ?");
			ps.setString(1, server);
			ResultSet rs = ps.executeQuery();
			rs.next();
			level = rs.getString("entrylevel");
			ps.close();
			rs.close();
		}catch (SQLException e) { e.printStackTrace(); }
		return level;
	}
	
	private int getPlayers(String server) {
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
	
	public void sendHotbarMessage(Player p, String message) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
	}
	
	public int getPlayerPing(Player p) {
		int ping = -1;
		ping = p.getPing();
		return ping;
	}
}