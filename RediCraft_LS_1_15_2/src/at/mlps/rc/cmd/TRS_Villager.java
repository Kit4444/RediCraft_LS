package at.mlps.rc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import at.mlps.rc.api.Prefix;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class TRS_Villager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player)sender;
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("dailyrewards")) {
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
				}else if(args[0].equalsIgnoreCase("advents")) {
					if(p.hasPermission("mlps.setVillager")) {
						Snowman v = (Snowman)p.getWorld().spawnEntity(p.getLocation(), EntityType.SNOWMAN);
						v.setCustomNameVisible(true);
						v.setCustomName("§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr");
						v.setAI(true);
						v.setGlowing(true);
						v.setDerp(true);
						v.setGliding(true);
					}else {
						LanguageHandler.noPerm(p);
					}
				}
			}else {
				p.sendMessage(Prefix.prefix("main") + "Usage: /setvillager <dailyrewards|advents>");
			}
		}
		return true;
	}
}