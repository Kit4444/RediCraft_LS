package at.mlps.rc.event;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.mysql.lb.MySQL;

public class PlayerMove implements Listener{
	
	static File file = new File("plugins/RCLS/effectslist.yml");

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(isAFK(p)) {
			updateAFK(p, false);
			LanguageHandler.sendMSGReady(p, "event.afk.leave");
		}
		if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
			p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
			p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Music")) {
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
			p.getWorld().spawnParticle(Particle.SLIME, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Water")) {
			p.getWorld().spawnParticle(Particle.WATER_DROP, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
			p.getWorld().playEffect(p.getLocation().add(0.0, 0.0, 0.0), Effect.ENDER_SIGNAL, 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
			p.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
			p.getWorld().spawnParticle(Particle.DRIP_LAVA, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
			p.getWorld().spawnParticle(Particle.DRIPPING_HONEY, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
			Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(getRGB(), getRGB(), getRGB()), 2);
			p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1, dust);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
			p.getWorld().spawnParticle(Particle.SNOWBALL, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".SoulFireflame")) {
			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Ash")) {
			p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation().add(0, 1, 0), 1);
			p.getWorld().spawnParticle(Particle.ASH, p.getLocation().add(0, 1, 0), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Souls")) {
			p.getWorld().spawnParticle(Particle.SOUL, p.getLocation().add(0, 1, 0), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".Glow")) {
			p.getWorld().spawnParticle(Particle.GLOW, p.getLocation().add(0, 1, 0), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".EndRod")) {
			p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation().add(0, 1, 0), 1);
		}
		if(cfg.getBoolean("Effects." + uuid + ".ObsidianTears")) {
			p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation().add(0, 1, 0), 1);
		}
		//drip obsidian tear, end rod, glow, soul
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
	
	private static void updateAFK(Player p, boolean boo) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_userstats SET afk = ? WHERE uuid = ?");
			ps.setBoolean(1, boo);
			ps.setString(2, p.getUniqueId().toString().replace("-", ""));
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}
	
	int getRGB(){
		Random r = new Random();
		int number = r.nextInt(255);
		while(number < 0) {
			number = r.nextInt(255);
		}
		return number;
	}
}