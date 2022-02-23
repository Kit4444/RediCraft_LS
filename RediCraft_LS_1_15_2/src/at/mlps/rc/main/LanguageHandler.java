package at.mlps.rc.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.entity.Player;

import at.mlps.rc.api.Prefix;
import at.mlps.rc.mysql.lb.MySQL;

public class LanguageHandler {
	
	public static HashMap<String, String> langCache_DE = new HashMap<>();
	public static HashMap<String, String> langCache_EN = new HashMap<>();
	public static HashMap<String, String> langCache_NL = new HashMap<>();
	public static HashMap<String, String> langCache_AT = new HashMap<>();
	
	public static void loadConfig() {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicraft_languagestrings");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				langCache_DE.put(rs.getString("lang_key"), rs.getString("German"));
				langCache_EN.put(rs.getString("lang_key"), rs.getString("English"));
				langCache_NL.put(rs.getString("lang_key"), rs.getString("Dutch"));
				langCache_AT.put(rs.getString("lang_key"), rs.getString("Austrian"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void noPerm(Player p) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(Prefix.prefix("main") + retString("en-uk", "noPerm"));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(Prefix.prefix("main") + retString("de-de", "noPerm"));
		}else if(retLang(p).equalsIgnoreCase("nl-nl")) {
			p.sendMessage(Prefix.prefix("main") + retString("nl-nl", "noPerm"));
		}else if(retLang(p).equalsIgnoreCase("de-at")) {
			p.sendMessage(Prefix.prefix("main") + retString("de-at", "noPerm"));
		}
	}
	
	public static void notAvailable(Player p) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(Prefix.prefix("main") + retString("en-uk", "notAvailable"));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(Prefix.prefix("main") + retString("de-de", "notAvailable"));
		}else if(retLang(p).equalsIgnoreCase("nl-nl")) {
			p.sendMessage(Prefix.prefix("main") + retString("nl-nl", "notAvailable"));
		}else if(retLang(p).equalsIgnoreCase("de-at")) {
			p.sendMessage(Prefix.prefix("main") + retString("de-at", "notAvailable"));
		}
	}
	
	public static void sendMSGReady(Player p, String path) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(Prefix.prefix("main") + retString("en-uk", path));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(Prefix.prefix("main") + retString("de-de", path));
		}else if(retLang(p).equalsIgnoreCase("nl-nl")) {
			p.sendMessage(Prefix.prefix("main") + retString("nl-nl", path));
		}else if(retLang(p).equalsIgnoreCase("de-at")) {
			p.sendMessage(Prefix.prefix("main") + retString("de-at", path));
		}
	}
	
	public static String returnStringReady(Player p, String path) {
		String s = "";
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			s = retString("en-uk", path);
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			s = retString("de-de", path);
		}else if(retLang(p).equalsIgnoreCase("nl-nl")) {
			s = retString("nl-nl", path);
		}else if(retLang(p).equalsIgnoreCase("de-at")) {
			s = retString("de-at", path);
		}
		return s;
	}
	
	private static String retLang(Player p) {
		String langKey = "en-UK";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT language FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			langKey = rs.getString("language");
		}catch (SQLException e) { e.printStackTrace(); return null; }
		return langKey;
	}

	private static String retString(String lang, String path) {
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
		}else if(lang.equalsIgnoreCase("nl-nl")) {
			if(langCache_NL.containsKey(path)) {
				string = langCache_NL.get(path).replace("&", "§");
			}else {
				string = "§cDit pad bestaat niet.";
			}
		}else if(lang.equalsIgnoreCase("de-at")) {
			if(langCache_AT.containsKey(path)) {
				string = langCache_AT.get(path).replace("&", "§");
			}else {
				string = "§cDea Pfad existiat ned!";
			}
		}
		return string;
	}
}