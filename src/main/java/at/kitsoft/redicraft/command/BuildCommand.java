package at.kitsoft.redicraft.command;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerJoinEvent;

import at.kitsoft.redicraft.event.ScoreboardClass;
import at.kitsoft.redicraft.main.LanguageHandler;
import at.kitsoft.redicraft.main.Main;

public class BuildCommand implements CommandExecutor, Listener{

	public static ArrayList<String> build = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player player){
			if(player.hasPermission("mlps.canBuild")){
				if(build.contains(player.getName())){
					build.remove(player.getName());
					LanguageHandler.sendMSGReady(player, "cmd.build.deactivated");
					Main.setPlayerBar(player);
					player.setGameMode(GameMode.SURVIVAL);
					if(ScoreboardClass.buildtime.containsKey(player.getName())){
						ScoreboardClass.buildtime.remove(player.getName());
					}
				}
				else{
					build.add(player.getName());
					LanguageHandler.sendMSGReady(player, "cmd.build.activated");
					player.getInventory().clear();
					player.setGameMode(GameMode.CREATIVE);
					long time = (System.currentTimeMillis());
					ScoreboardClass.buildtime.put(player.getName(), time);

				}
			}
		}
		else{
			Bukkit.getConsoleSender().sendMessage("Use this only ingame!");
		}
		return true;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(build.contains(p.getName())){
			e.setCancelled(false);
		}
		else{
			e.setCancelled(true);
			LanguageHandler.sendMSGReady(p, "event.build.cantdothat");
		}
	}

	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if (build.contains(p.getName())){
			e.setCancelled(false);
		}
		else{
			e.setCancelled(true);
			LanguageHandler.sendMSGReady(p, "event.build.cantdothat");
		}
	}

	@EventHandler
	public void onInteractwithWeed(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.PHYSICAL) {
			if (p.getLocation().getBlock().getType() != Material.BIG_DRIPLEAF) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.wheatdestroy.cantdothat");
			}
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(build.contains(e.getPlayer().getName())) {
			build.remove(e.getPlayer().getName());
		}
	}
}