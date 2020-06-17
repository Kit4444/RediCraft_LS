package at.mlps.rc.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class BuildClass implements CommandExecutor, Listener{
	
	public static ArrayList<String> build = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("Use this only ingame!");
		}else {
			Player p = (Player)sender;
			if(p.hasPermission("mlps.canBuild")) {
				if(build.contains(p.getName())) {
					build.remove(p.getName());
					LanguageHandler.sendMSGReady(p, "cmd.build.deactivated");
					Main.setPlayerBar(p);
					p.setGameMode(GameMode.SURVIVAL);
				}else {
					build.add(p.getName());
					LanguageHandler.sendMSGReady(p, "cmd.build.activated");
					p.getInventory().clear();
					p.setGameMode(GameMode.CREATIVE);
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(build.contains(p.getName())) {
			e.setCancelled(false);
		}else {
			e.setCancelled(true);
			LanguageHandler.sendMSGReady(p, "event.build.cantdothat");
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(build.contains(p.getName())) {
			e.setCancelled(false);
		}else {
			e.setCancelled(true);
			LanguageHandler.sendMSGReady(p, "event.build.cantdothat");
		}
	}
	
	@EventHandler
	public void onInteractwithWeed(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.PHYSICAL) {
			e.setCancelled(true);
			LanguageHandler.sendMSGReady(p, "event.wheatdestroy.cantdothat");
		}
	}
}