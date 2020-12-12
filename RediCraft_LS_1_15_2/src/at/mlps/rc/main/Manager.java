package at.mlps.rc.main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import at.mlps.rc.cmd.AFK_CMD;
import at.mlps.rc.cmd.BuildClass;
import at.mlps.rc.cmd.CMD_SetID_SetPf;
import at.mlps.rc.cmd.LobbyCMD;
import at.mlps.rc.cmd.LogSystem;
import at.mlps.rc.cmd.Maintenance;
import at.mlps.rc.cmd.MoneyAPI;
import at.mlps.rc.cmd.Pinfo;
import at.mlps.rc.cmd.TRS_Villager;
import at.mlps.rc.event.Advents_Handler;
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

public class Manager {
	
	public void init() {
		Main.instance.getCommand("build").setExecutor(new BuildClass());
		Main.instance.getCommand("login").setExecutor(new LogSystem());
		Main.instance.getCommand("logout").setExecutor(new LogSystem());
		Main.instance.getCommand("togglegroup").setExecutor(new LogSystem());
		Main.instance.getCommand("maintenance").setExecutor(new Maintenance());
		Main.instance.getCommand("userlist").setExecutor(new Maintenance());
		Main.instance.getCommand("afk").setExecutor(new AFK_CMD());
		Main.instance.getCommand("setid").setExecutor(new CMD_SetID_SetPf());
		Main.instance.getCommand("setpf").setExecutor(new CMD_SetID_SetPf());
		Main.instance.getCommand("money").setExecutor(new MoneyAPI());
		Main.instance.getCommand("setmoney").setExecutor(new MoneyAPI());
		Main.instance.getCommand("removemoney").setExecutor(new MoneyAPI());
		Main.instance.getCommand("addmoney").setExecutor(new MoneyAPI());
		Main.instance.getCommand("topmoney").setExecutor(new MoneyAPI());
		Main.instance.getCommand("setbankmoney").setExecutor(new MoneyAPI());
		Main.instance.getCommand("bankdeposit").setExecutor(new MoneyAPI());
		Main.instance.getCommand("bankwithdraw").setExecutor(new MoneyAPI());
		Main.instance.getCommand("pay").setExecutor(new MoneyAPI());
		Main.instance.getCommand("spawnvillager").setExecutor(new TRS_Villager());
		Main.instance.getCommand("pinfo").setExecutor(new Pinfo());
		Main.instance.getCommand("lobbyconf").setExecutor(new LobbyCMD());
		
		PluginManager pl = Bukkit.getPluginManager();
		pl.registerEvents(new ScoreboardClass(), Main.instance);
		pl.registerEvents(new JoinQuitEventID(), Main.instance);
		pl.registerEvents(new BuildClass(), Main.instance);
		pl.registerEvents(new Navigator(Main.instance), Main.instance);
		pl.registerEvents(new ItemHandling(), Main.instance);
		pl.registerEvents(new Maintenance(), Main.instance);
		pl.registerEvents(new ExtrasInv(), Main.instance);
		pl.registerEvents(new Blocker(), Main.instance);
		pl.registerEvents(new LanguageInv(), Main.instance);
		pl.registerEvents(new PlayerMove(), Main.instance);
		pl.registerEvents(new TRS_Inventory(), Main.instance);
		pl.registerEvents(new Serverupdater(), Main.instance);
		pl.registerEvents(new LobbyCMD(), Main.instance);
		pl.registerEvents(new Advents_Handler(), Main.instance);
		
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
		Main.mysql = new MySQL(host, port, db, user, pass);
		try {
			Main.mysql.connect();
		}catch (SQLException e) {}
		ScoreboardClass sb = new ScoreboardClass();
		sb.SBSched(0, 20);
		Serverupdater.runUpdaters(0, 100);
		LanguageHandler.loadConfig();
	}

}
