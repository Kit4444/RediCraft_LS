package at.kitsoft.redicraft.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.kitsoft.redicraft.main.LanguageHandler;
import at.kitsoft.redicraft.main.Main;
import at.kitsoft.redicraft.mysql.lb.MySQL;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class LogSystem implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player)sender;
			String uuid = p.getUniqueId().toString().replace("-", "");
			/*
			 * Groups for Log-In /-Out: gmmman, gm, hr, cman, pman
			 */
			if(cmd.getName().equalsIgnoreCase("login")) {
				if(p.hasPermission("mlps.canBan")) {
					if(retStatus(uuid)) {
						LanguageHandler.sendMSGReady(p, "cmd.login.already");
					}else {
						LanguageHandler.sendMSGReady(p, "cmd.login.success");
						updateStatus(uuid, true);
					}
				}else {
					LanguageHandler.noPerm(p);
				}
			}else if(cmd.getName().equalsIgnoreCase("logout")) {
				if(p.hasPermission("mlps.canBan")) {
					if(retStatus(uuid)) {
						LanguageHandler.sendMSGReady(p, "cmd.logout.success");
						updateStatus(uuid, false);
					}else {
						LanguageHandler.sendMSGReady(p, "cmd.logout.already");
					}
				}else {
					LanguageHandler.noPerm(p);
				}
			}else if(cmd.getName().equalsIgnoreCase("tg") || cmd.getName().equalsIgnoreCase("togglegroup")) {
				PermissionUser po = PermissionsEx.getUser(p);
				/*
				 * Groups for TG: st, bd, rltm, rtm, aot, part, cm, fs, ct, bt, nb, friend, train
				 */
				if(po.inGroup("st") || po.inGroup("bd") || po.inGroup("rltm") || po.inGroup("rtm") || po.inGroup("aot") || po.inGroup("part") || po.inGroup("cm") || po.inGroup("fs") || po.inGroup("ct") || po.inGroup("bt") || po.inGroup("nb") || po.inGroup("friend") || po.inGroup("train")) {
					if(retStatus(uuid)) {
						updateStatus(uuid, false);
						LanguageHandler.sendMSGReady(p, "cmd.tg.invisible");
					}else {
						updateStatus(uuid, true);
						LanguageHandler.sendMSGReady(p, "cmd.tg.visible");
					}
				}else {
					LanguageHandler.noPerm(p);
				}
			}
		}
		return false;
	}
	
	private boolean retStatus(String uuid) {
		boolean boo = true;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			boo = rs.getBoolean("loggedin");
			rs.close();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); }
		return boo;
	}
	
	private void updateStatus(String uuid, boolean boo) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET loggedin = ? WHERE uuid = ?");
			ps.setBoolean(1, boo);
			ps.setString(2, uuid);
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}

}