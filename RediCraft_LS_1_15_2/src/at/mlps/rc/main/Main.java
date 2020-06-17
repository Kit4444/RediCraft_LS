package at.mlps.rc.main;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import at.mlps.rc.api.GetBukkitInfo;
import at.mlps.rc.api.ItemsAPI;
import at.mlps.rc.api.Prefix;
import at.mlps.rc.cmd.AFK_CMD;
import at.mlps.rc.cmd.BuildClass;
import at.mlps.rc.cmd.CMD_SetID_SetPf;
import at.mlps.rc.cmd.LogSystem;
import at.mlps.rc.cmd.Maintenance;
import at.mlps.rc.cmd.MoneyAPI;
import at.mlps.rc.cmd.SpawnCMD;
import at.mlps.rc.cmd.TRS_Villager;
import at.mlps.rc.event.Blocker;
import at.mlps.rc.event.ExtrasInv;
import at.mlps.rc.event.ItemHandling;
import at.mlps.rc.event.JoinQuitEventID;
import at.mlps.rc.event.LanguageInv;
import at.mlps.rc.event.Navigator;
import at.mlps.rc.event.PlayerMove;
import at.mlps.rc.event.ScoreboardClass;
import at.mlps.rc.event.TRS_Inventory;
import at.mlps.rc.mysql.lpb.MySQL;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		registermisc();
		registerCMD();
		registerEvents();
		updateOnline(true);
	}
	
	public static String prefix() {
		return Prefix.prefix("main");
	}
	public static String mysqlprefix = "§eMySQL §7- ";
	public static String consolesend = prefix() + "§7Please use this ingame.";
	public static Main instance;
	public static MySQL mysql;
	
	public void onDisable() {
		updateOnline(false);
		instance = null;
		try {
			mysql.disconnect();
			at.mlps.rc.mysql.lb.MySQL.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void registerEvents() {
		PluginManager pl = Bukkit.getPluginManager();
		pl.registerEvents(new ScoreboardClass(), this);
		pl.registerEvents(new JoinQuitEventID(), this);
		pl.registerEvents(new BuildClass(), this);
		pl.registerEvents(new Navigator(this), this);
		pl.registerEvents(new ItemHandling(), this);
		pl.registerEvents(new Maintenance(), this);
		pl.registerEvents(new ExtrasInv(), this);
		pl.registerEvents(new Blocker(), this);
		pl.registerEvents(new LanguageInv(), this);
		pl.registerEvents(new PlayerMove(), this);
		pl.registerEvents(new TRS_Inventory(), this);
	}
	
	public void registerCMD() {
		getCommand("build").setExecutor(new BuildClass());
		getCommand("login").setExecutor(new LogSystem());
		getCommand("logout").setExecutor(new LogSystem());
		getCommand("togglegroup").setExecutor(new LogSystem());
		getCommand("maintenance").setExecutor(new Maintenance());
		getCommand("userlist").setExecutor(new Maintenance());
		getCommand("afk").setExecutor(new AFK_CMD());
		getCommand("setspawn").setExecutor(new SpawnCMD());
		getCommand("setid").setExecutor(new CMD_SetID_SetPf());
		getCommand("setpf").setExecutor(new CMD_SetID_SetPf());
		getCommand("money").setExecutor(new MoneyAPI());
		getCommand("setmoney").setExecutor(new MoneyAPI());
		getCommand("removemoney").setExecutor(new MoneyAPI());
		getCommand("addmoney").setExecutor(new MoneyAPI());
		getCommand("topmoney").setExecutor(new MoneyAPI());
		getCommand("setbankmoney").setExecutor(new MoneyAPI());
		getCommand("bankdeposit").setExecutor(new MoneyAPI());
		getCommand("bankwithdraw").setExecutor(new MoneyAPI());
		getCommand("pay").setExecutor(new MoneyAPI());
		getCommand("spawnvillager").setExecutor(new TRS_Villager());
	}
	
	public static void setPlayerBar(Player p) {
		p.getInventory().clear();
		p.getInventory().setItem(2, ItemsAPI.defItem(Material.DIAMOND, 1, ExtrasInv.mainTitle));
		p.getInventory().setItem(4, ItemsAPI.defItem(Material.COMPASS, 1, Navigator.title));
		p.getInventory().setItem(6, ItemsAPI.defItem(Material.BOOK, 1, LanguageInv.mainTitle));
	}
	
	public void registermisc() {
		File config = new File("plugins/RCLS/config.yml");
		File file = new File("plugins/RCLS");
		File trs = new File("plugins/RCLS/rewards.yml");
		if(!file.exists()) {
			file.mkdir();
		}
		if(!trs.exists()) {
			try {
				trs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(config);
		cfg.addDefault("MySQL.Host", "localhost");
		cfg.addDefault("MySQL.Port", 3306);
		cfg.addDefault("MySQL.Database", "database");
		cfg.addDefault("MySQL.Username", "username");
		cfg.addDefault("MySQL.Password", "password");
		cfg.options().copyDefaults(true);
		try {
			cfg.save(config);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String host = cfg.getString("MySQL.Host");
		int port = cfg.getInt("MySQL.Port");
		String db = cfg.getString("MySQL.Database");
		String user = cfg.getString("MySQL.Username");
		String pass = cfg.getString("MySQL.Password");
		at.mlps.rc.mysql.lb.MySQL.connect(host, String.valueOf(port), db, user, pass);
		mysql = new MySQL(host, port, db, user, pass);
		try {
			mysql.connect();
		}catch (SQLException e) {}
		ScoreboardClass sb = new ScoreboardClass();
		sb.SBSched(0, 20);
		LanguageHandler.loadConfig();
	}
	
	private void updateOnline(boolean boo) {
		try {
			PreparedStatement ps = at.mlps.rc.mysql.lb.MySQL.getConnection().prepareStatement("UPDATE redicore_serverstats SET online = ? WHERE servername = ?");
			ps.setBoolean(1, boo);
			ps.setString(2, GetBukkitInfo.getServerName());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}