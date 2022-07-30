package at.kitsoft.redicraft.command;

import at.kitsoft.redicraft.main.LanguageHandler;
import at.kitsoft.redicraft.main.Main;
import at.kitsoft.redicraft.mysql.lb.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogSystem implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player player){
			final String uid = player.getUniqueId().toString();

			/*
			 * Groups for Log-In /-Out: gmmman, gm, hr, cman, pman
			 */
			final String cmdName = cmd.getName().toLowerCase();

			if(cmdName.equals("login")){
				if(player.hasPermission("mlps.canBan")){
					if(retStatus(uid)){
						LanguageHandler.sendMSGReady(player, "cmd.login.already");
					}
					else{
						LanguageHandler.sendMSGReady(player, "cmd.login.success");
						updateStatus(uid, true);
					}
				}
				else{
					LanguageHandler.noPerm(player);
				}
			}
			else if(cmdName.equals("logout")){
				if(player.hasPermission("mlps.canBan")){
					if(retStatus(uid)){
						LanguageHandler.sendMSGReady(player, "cmd.logout.success");
						updateStatus(uid, false);
					}
					else{
						LanguageHandler.sendMSGReady(player, "cmd.logout.already");
					}
				}
				else{
					LanguageHandler.noPerm(player);
				}
			}
			else if(cmdName.equals("tg") || cmdName.equals("togglegroup")){
				PermissionUser po = PermissionsEx.getUser(player);

				// Groups for TG: st, bd, rltm, rtm, aot, part, cm, fs, ct, bt, nb, friend, train

				final String[] groups = {
						"st", "bd", "rltm", "rtm", "aot", "part", "cm", "fs", "ct", "bt", "nb", "friend", "train"
				};

				for(String group:groups){
					if(po.inGroup(group)){
						if(retStatus(uid)){
							updateStatus(uid, false);
							LanguageHandler.sendMSGReady(player, "cmd.tg.invisible");
						}
						else{
							updateStatus(uid, true);
							LanguageHandler.sendMSGReady(player, "cmd.tg.visible");
						}
					}
					else{ LanguageHandler.noPerm(player); }

					break;
				}

			}
		}
		else{
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}
		return true;
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