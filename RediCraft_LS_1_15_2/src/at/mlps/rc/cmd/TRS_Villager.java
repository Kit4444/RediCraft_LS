package at.mlps.rc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class TRS_Villager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player)sender;
			if(p.hasPermission("mlps.setVillager")) {
				Villager v = (Villager)p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
				v.setAgeLock(true);
				v.setAdult();
				v.setCustomNameVisible(true);
				v.setCustomName("§aDaily §cRewards");
				v.setProfession(Profession.LIBRARIAN);
			}else {
				LanguageHandler.noPerm(p);
			}
		}
		return true;
	}
}