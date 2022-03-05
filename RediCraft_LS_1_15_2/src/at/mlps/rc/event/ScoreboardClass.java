package at.mlps.rc.event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import at.mlps.rc.api.ChatFont;
import at.mlps.rc.api.GetBukkitInfo;
import at.mlps.rc.api.GetPlayersLocal;
import at.mlps.rc.api.Prefix;
import at.mlps.rc.cmd.BuildClass;
import at.mlps.rc.cmd.MoneyAPI;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;
import at.mlps.rc.main.Serverupdater;
import at.mlps.rc.mysql.lb.MySQL;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ScoreboardClass implements Listener{
	
	public static HashMap<String, Long> buildtime = new HashMap<>();
	private static HashMap<String, String> tabHM = new HashMap<>();
	private static HashMap<String, String> chatHM = new HashMap<>();
	private static HashMap<String, String> roleHM = new HashMap<>();
	private static int sbSide = 0;
	
	public void setScoreboard(Player p) throws SQLException {
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sb.registerNewObjective("aaa", "dummy", "Infoboard");
		PermissionUser po = PermissionsEx.getUser(p);
		int puser = GetPlayersLocal.getPlayers("BungeeCord", "currPlayers");
		int pusermax = GetPlayersLocal.getPlayers("BungeeCord", "maxPlayers");
		int puserperc = (puser*100/pusermax);
		String sbpr = Prefix.prefix("scoreboard");
		String pr = "";
		
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		if(puserperc <= 20) {
			pr = sbpr + " » §a" + puser + " §7/§9 " + pusermax;
		}else if(puserperc >= 21 && puserperc <= 40) {
			pr = sbpr + " » §2" + puser + " §7/§9 " + pusermax;
		}else if(puserperc >= 41 && puserperc <= 60) {
			pr = sbpr + " » §e" + puser + " §7/§9 " + pusermax;
		}else if(puserperc >= 61 && puserperc <= 80) {
			pr = sbpr + " » §6" + puser + " §7/§9 " + pusermax;
		}else if(puserperc >= 81 && puserperc <= 95) {
			pr = sbpr + " » §c" + puser + " §7/§9 " + pusermax;
		}else if(puserperc >= 96) {
			pr = sbpr + " » §4" + puser + " §7/§9 " + pusermax;
		}
		if(BuildClass.build.contains(p.getName())) {
			ItemStack is = p.getItemInUse();
			String itemid = is.getType().toString();
			o.setDisplayName("§aBuild§cStats");
			o.getScore("§7Buildtime:").setScore(4);
			o.getScore("§7» §a" + getBuildTime(p.getName())).setScore(3);
			o.getScore("§0").setScore(2);
			o.getScore(LanguageHandler.returnStringReady(p, "scoreboard.sideboard.block")).setScore(1);
			o.getScore("§7» §a" + itemid).setScore(0);
		}else {
			if(sbSide >= 0 && sbSide <= 4) {
				o.setDisplayName(pr);
				o.getScore(LanguageHandler.returnStringReady(p, "scoreboard.sideboard.money")).setScore(8);
				o.getScore("§7» §a" + MoneyAPI.getBankMoney(p.getUniqueId()) + " §7Bank").setScore(7);
				o.getScore("§7» §a" + MoneyAPI.getMoney(p.getUniqueId()) + " §7Cash").setScore(6);
				o.getScore("§f").setScore(5);
				o.getScore(LanguageHandler.returnStringReady(p, "scoreboard.sideboard.rank")).setScore(4);
				o.getScore(retGroup(po)).setScore(3);
				o.getScore("§9").setScore(2);
				o.getScore(LanguageHandler.returnStringReady(p, "scoreboard.sideboard.playerid")).setScore(1);
				o.getScore("§7»  §7" + igid(p) + " §f" + igpre(p)).setScore(0);
			}else if(sbSide >= 5 && sbSide <= 9) {
				o.setDisplayName("§aPlayers:");
				o.getScore("§7Proxy: §a" + getPlayers("BungeeCord", "currPlayers")).setScore(6);
				o.getScore("§a").setScore(5);
				o.getScore("§6Gameslobby§7: §a" + getPlayers("Gameslobby", "currPlayers")).setScore(4);
				o.getScore("§eCreative§7: §a" + getPlayers("Creative", "currPlayers")).setScore(3);
				o.getScore("§cSurvival§7: §a" + getPlayers("Survival", "currPlayers")).setScore(2);
				o.getScore("§dFarmserver§7: §a" + getPlayers("Farmserver", "currPlayers")).setScore(1);
				o.getScore("§7Sky§2Block§7: §a" + getPlayers("SkyBlock", "currPlayers")).setScore(0);
			}
		}
		p.setScoreboard(sb);
		
		Team pman = getTeam(sb, "pman", ChatColor.GRAY);
		Team sman = getTeam(sb, "sman", ChatColor.GRAY);
		Team gmmman = getTeam(sb, "gmmman", ChatColor.GRAY);
		Team dev = getTeam(sb, "dev", ChatColor.GRAY);
		Team gman = getTeam(sb, "gman", ChatColor.GRAY);
		Team sda = getTeam(sb, "sda", ChatColor.GRAY);
		Team cm = getTeam(sb, "cm", ChatColor.GRAY);
		Team ct = getTeam(sb, "ct", ChatColor.GRAY);
		Team st = getTeam(sb, "st", ChatColor.GRAY);
		Team bd = getTeam(sb, "bd", ChatColor.GRAY);
		Team gm = getTeam(sb, "gm", ChatColor.GRAY);
		Team aot = getTeam(sb, "aot", ChatColor.GRAY);
		Team train = getTeam(sb, "train", ChatColor.GRAY);
		Team rltm = getTeam(sb, "rltm", ChatColor.GRAY);
		Team rtm = getTeam(sb, "rtm", ChatColor.GRAY);
		Team part = getTeam(sb, "part", ChatColor.GRAY);
		Team fs = getTeam(sb, "fs", ChatColor.GRAY);
		Team nb = getTeam(sb, "nb", ChatColor.GRAY);
		Team bt = getTeam(sb, "bt", ChatColor.GRAY);
		Team friend = getTeam(sb, "friend", ChatColor.GRAY);
		Team vip = getTeam(sb, "vip", ChatColor.GRAY);
		Team player = getTeam(sb, "default", ChatColor.GRAY);
		Team afk = getTeam(sb, "safk", ChatColor.BLUE);
		
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			PermissionUser pp = PermissionsEx.getUser(all);
			HashMap<String, Object> hm = new HashMap<>();
	    	hm.put("uuid", all.getUniqueId().toString().replace("-", ""));
	    	ResultSet rs = Main.mysql.select("redicore_userstats", hm);
	    	rs.next();
	    	if(rs.getString("nickname").equalsIgnoreCase("none")) {
	    		all.setCustomName(all.getName());
	    	}else {
	    		all.setCustomName(rs.getString("nickname"));
	    	}
	    	String prefix = "";
	    	if(!rs.getString("userprefix").equalsIgnoreCase("RESET")) {
	    		prefix = rs.getString("userprefix");
	    	}
	    	if(isAFK(all)) {
	    		afk.addEntry(all.getName());
				all.setPlayerListName(retPrefix("safk", "prefix_tab") + all.getCustomName() + " §7| ID§7: §a" + rs.getInt("userid") + " §f" + prefix);
	    	}else {
	    		if(!rs.getBoolean("loggedin")) {
	    			player.addEntry(all.getName());
					all.setDisplayName(retPrefix("default", "prefix_chat") + all.getCustomName());
					all.setPlayerListName(retPrefix("default", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
	    		}else {
	    			if(pp.inGroup("pman")) {
		    			pman.addEntry(all.getName());
						all.setDisplayName(retPrefix("pman", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("pman", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("sman")) {
	    				sman.addEntry(all.getName());
	    				all.setDisplayName(retPrefix("sman", "prefix_chat") + all.getName());
	    				all.setPlayerListName(retPrefix("sman", "prefix_tab") + all.getName() + " §7| ID: §a" + igid(all) + " §f" + prefix);
	    			}else if(pp.inGroup("dev")) {
						dev.addEntry(all.getName());
						all.setDisplayName(retPrefix("dev", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("dev", "prefix_tab") + all.getCustomName() + " §7| ID§7: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("gman")) {
	    				gman.addEntry(all.getName());
	    				all.setDisplayName(retPrefix("gman", "prefix_chat") + all.getName());
	    				all.setPlayerListName(retPrefix("gman", "prefix_tab") + all.getName() + " §7| ID: §a" + igid(all) + " §f" + prefix);
	    			}else if(pp.inGroup("sda")) {
	    				sda.addEntry(all.getName());
	    				all.setDisplayName(retPrefix("sda", "prefix_chat") + all.getName());
	    				all.setPlayerListName(retPrefix("sda", "prefix_tab") + all.getName() + " §7| ID: §a" + igid(all) + " §f" + prefix);
	    			}else if(pp.inGroup("gmmman")) {
						gmmman.addEntry(all.getName());
						all.setDisplayName(retPrefix("gmmman", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("gmmman", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("gm")) {
						gm.addEntry(all.getName());
						all.setDisplayName(retPrefix("gm", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("gm", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("cm")) {
						cm.addEntry(all.getName());
						all.setDisplayName(retPrefix("cm", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("cm", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("ct")) {
						ct.addEntry(all.getName());
						all.setDisplayName(retPrefix("ct", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("ct", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("st")) {
						st.addEntry(all.getName());
						all.setDisplayName(retPrefix("st", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("st", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("bd")) {
						bd.addEntry(all.getName());
						all.setDisplayName(retPrefix("bd", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("bd", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("aot")) {
						aot.addEntry(all.getName());
						all.setDisplayName(retPrefix("aot", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("aot", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("train")) {
						train.addEntry(all.getName());
						all.setDisplayName(retPrefix("train", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("train", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("rltm")) {
						rltm.addEntry(all.getName());
						all.setDisplayName(retPrefix("rltm", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("rltm", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("rtm")) {
						rtm.addEntry(all.getName());
						all.setDisplayName(retPrefix("rtm", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("rtm", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("part")) {
						part.addEntry(all.getName());
						all.setDisplayName(retPrefix("part", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("part", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("bt")) {
						bt.addEntry(all.getName());
						all.setDisplayName(retPrefix("bt", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("bt", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("fs")) {
						fs.addEntry(all.getName());
						all.setDisplayName(retPrefix("fs", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("fs", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("nb")) {
						nb.addEntry(all.getName());
						all.setDisplayName(retPrefix("nb", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("nb", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("friend")) {
						friend.addEntry(all.getName());
						all.setDisplayName(retPrefix("friend", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("friend", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("vip")) {
						vip.addEntry(all.getName());
						all.setDisplayName(retPrefix("vip", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("vip", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else if(pp.inGroup("default")) {
						player.addEntry(all.getName());
						all.setDisplayName(retPrefix("default", "prefix_chat") + all.getCustomName());
						all.setPlayerListName(retPrefix("default", "prefix_tab") + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}else {
						player.addEntry(all.getName());
						all.setDisplayName("§cunknown Role " + all.getCustomName());
						all.setPlayerListName("§cunknown Role " + all.getCustomName() + " §7| ID: §a" + rs.getInt("userid") + " §f" + prefix);
					}
	    		}
	    	}
			rs.close();
		}
	}
	
	public Team getTeam(Scoreboard sb, String role, ChatColor cc) {
		Team team = sb.registerNewTeam(retPrefix(role, "team"));
		team.setPrefix(retPrefix(role, "prefix_tab"));
		team.setColor(cc);
		return team;
	}
	
	private String retGroup(PermissionUser po) {
		String group = "";
		if(po.inGroup("pman")) {
			group = "§7» §aProject Manager";
		}else if(po.inGroup("sman")) {
			group = "§7» §aStaff Manager";
		}else if(po.inGroup("gmmman")) {
			group = "§7» §aGame Mod. Manager";
		}else if(po.inGroup("dev")) {
			group = "§7» §aDeveloper";
		}else if(po.inGroup("gman")) {
			group = "§7» §aGeneral Manager";
		}else if(po.inGroup("sda")) {
			group = "§7» §aService & Data Analyst";
		}else if(po.inGroup("cm")) {
			group = "§7» §aCommunity Moderator";
		}else if(po.inGroup("ct")) {
			group = "§7» §aContent Team";
		}else if(po.inGroup("st")) {
			group = "§7» §aSupport Team";
		}else if(po.inGroup("bd")) {
			group = "§7» §aBuilder";
		}else if(po.inGroup("gm")) {
			group = "§7» §aGame Moderator";
		}else if(po.inGroup("aot")) {
			group = "§7» §aAdd-On Team";
		}else if(po.inGroup("train")) {
			group = "§7» §aTrainee";
		}else if(po.inGroup("rltm")) {
			group = "§7» §aRetired Legend";
		}else if(po.inGroup("rtm")) {
			group = "§7» §aRetired Team Member";
		}else if(po.inGroup("part")) {
			group = "§7» §aPartner";
		}else if(po.inGroup("fs")) {
			group = "§7» §aForum Supporter";
		}else if(po.inGroup("nb")) {
			group = "§7» §aNitro Booster";
		}else if(po.inGroup("bt")) {
			group = "§7» §aBeta Tester";
		}else if(po.inGroup("friend")) {
			group = "§7» §aFriend";
		}else if(po.inGroup("vip")) {
			group = "§7» §aVIP";
		}else if(po.inGroup("default")) {
			group = "§7» §aPlayer";
		}else {
			group = "§7» §cunknown Role";
		}
		return group;
	}
	
	private String igid(Player p) {
		String s = "";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replaceAll("-", ""));
		    ResultSet rs = ps.executeQuery();
		    rs.next();
		    s = rs.getString("userid");
		    rs.close();
		    ps.close();
		}catch (SQLException e) { }
		return s;
	}
	
	private int getPlayers(String servers, String col) {
		int players = 0;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT " + col + " FROM redicore_serverstats WHERE servername = ?");
			ps.setString(1, servers);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				players = rs.getInt(col);
			}else {
				players = -1;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}
	
	private static String igpre(Player p) throws SQLException {
		PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
		ps.setString(1, p.getUniqueId().toString().replaceAll("-", ""));
		ResultSet rs = ps.executeQuery();
		rs.next();
		String pre = rs.getString("userprefix");
		String prefix = "";
		if(pre.equalsIgnoreCase("RESET")) {
			prefix = "";
		}else {
			prefix = pre;
		}
		rs.close();
		ps.close();
		return prefix;
	}
	
	private static boolean isAFK(Player p) {
		boolean boo = false;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			boo = rs.getBoolean("afk");
			rs.close();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); }
		return boo;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage().replace("%", "%%");
		if(p.hasPermission("mlps.colorchat")) {
			String msg1 = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', msg);
			msg1 = ChatFont.translate(msg);
			e.setFormat(p.getDisplayName() + " §7(§9" + igid(p) + "§7): " + msg1);
		}else {
			e.setFormat(p.getDisplayName() + " §7(§9" + igid(p) + "§7): " + msg);
		}
	}
	
	private String retPrefix(String rank, String type) {
		String prefix = "";
		if(type.equalsIgnoreCase("prefix_chat")) {
			prefix = chatHM.get(rank);
		}else if(type.equalsIgnoreCase("prefix_tab")) {
			prefix = tabHM.get(rank);
		}else if(type.equalsIgnoreCase("team")) {
			prefix = roleHM.get(rank);
		}
		String ret = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', prefix);
		ret = ChatFont.translate(ret);
		return ret;
	}
	
	public void downloadStrings() {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_ranks");
			ResultSet rs = ps.executeQuery();
			tabHM.clear();
			chatHM.clear();
			roleHM.clear();
			while(rs.next()) {
				tabHM.put(rs.getString("rank"), rs.getString("prefix_tab"));
				chatHM.put(rs.getString("rank"), rs.getString("prefix_chat"));
				roleHM.put(rs.getString("rank"), rs.getString("team"));
			}
		}catch (SQLException e) {
		}
	}
	
	String getBuildTime(String player) {
		if(buildtime.containsKey(player)) {
			long btime = buildtime.get(player);
			long current = System.currentTimeMillis() / 1000;
			long tst = (current - btime);
			long seconds = tst;
			long minutes = 0;
			long hours = 0;
			while(seconds > 60) {
				seconds -= 60;
				minutes++;
			}
			while(minutes > 60) {
				minutes -= 60;
				hours++;
			}
			String hr = "";
			String min = "";
			if(hours < 10) {
				hr = "0" + hours;
			}else {
				hr = "" + hours;
			}
			if(minutes < 10) {
				min = "0" + minutes;
			}else {
				min = "" + minutes;
			}
			return hr + ":" + min;
		}else {
			return "Errored";
		}
	}
	
	int pause = 0;
	int timer = 599;
	int sideboard = 0;
	
	public void SBSched(int delay, int sbsched) {
		new BukkitRunnable() {
			@Override
			public void run() {
				SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
				String stime = time.format(new Date());
				Serverupdater su = new Serverupdater();
				su.Serverrestarter();
				sideboard++;
				if(sideboard == 4) {
					sideboard = 0;
					sbSide++;
					if(sbSide == 10) {
						sbSide = 0;
					}
					for(Player all : Bukkit.getOnlinePlayers()) {
						try {
							setScoreboard(all);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				pause++;
				timer++;
				if(timer == 600) {
					timer = 0;
					try {
						PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_ranks");
						ResultSet rs = ps.executeQuery();
						tabHM.clear();
						chatHM.clear();
						while(rs.next()) {
							tabHM.put(rs.getString("rank"), rs.getString("prefix_tab"));
							chatHM.put(rs.getString("rank"), rs.getString("prefix_chat"));
						}
						tabHM.put("TEST", "VALUE");
						chatHM.put("TEST", "VALUE");
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(pause == 5) {
					pause = 0;
					su.updateServer();
					su.updateWorlds();
					System.gc();
				}
				GetBukkitInfo bukkit = new GetBukkitInfo();
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.setPlayerListHeaderFooter(LanguageHandler.returnStringReady(all, "scoreboard.playerlist.top").replace("|", "\n"), LanguageHandler.returnStringReady(all, "scoreboard.playerlist.bottom").replace("|", "\n").replace("%time", stime).replace("%servername", bukkit.getServerName()).replace("%serverid", bukkit.getServerId()));
				}
			}
		}.runTaskTimer(Main.instance, delay, sbsched);
	}
}