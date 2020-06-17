package at.mlps.rc.event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import at.mlps.rc.api.ItemsAPI;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;

public class ExtrasInv implements Listener{
	
	public static String mainTitle = "§dExtras";
	public static String speedboost = "§2Speedboost";
	public static String jumpboost = "§6Jumpboost";
	public static String effects = "§5Effects";
	
	public static void mainInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, mainTitle);
		ItemStack item = ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§3");
		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(3, item);
		inv.setItem(4, item);
		inv.setItem(5, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		inv.setItem(9, item);
		inv.setItem(10, item);
		inv.setItem(12, item);
		inv.setItem(14, item);
		inv.setItem(16, item);
		inv.setItem(17, item);
		inv.setItem(18, item);
		inv.setItem(19, item);
		inv.setItem(20, item);
		inv.setItem(21, item);
		inv.setItem(22, item);
		inv.setItem(23, item);
		inv.setItem(24, item);
		inv.setItem(25, item);
		inv.setItem(26, item);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				inv.setItem(11, ItemsAPI.potionItem(1, PotionType.SPEED, "§2Speedboost"));
				inv.setItem(13, ItemsAPI.potionItem(1, PotionType.JUMP, "§6Jumpboost"));
				inv.setItem(15, ItemsAPI.defItem(Material.LEATHER_BOOTS, 1, "§5Effects"));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}
	
	private static void speedboost(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3*9, speedboost);
		ItemStack item = ItemsAPI.defItem(Material.GREEN_STAINED_GLASS_PANE, 1, "§a");
		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(3, item);
		inv.setItem(4, item);
		inv.setItem(5, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		inv.setItem(10, item);
		inv.setItem(12, item);
		inv.setItem(14, item);
		inv.setItem(16, item);
		inv.setItem(18, item);
		inv.setItem(19, item);
		inv.setItem(20, item);
		inv.setItem(22, item);
		inv.setItem(24, item);
		inv.setItem(25, item);
		inv.setItem(26, item);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				inv.setItem(9, ItemsAPI.potionItem(1, PotionType.WATER, "§2Default"));
				inv.setItem(11, ItemsAPI.potionItem(1, PotionType.SPEED, "§aSpeed 1"));
				inv.setItem(13, ItemsAPI.potionItem(1, PotionType.SPEED, "§eSpeed 2"));
				inv.setItem(15, ItemsAPI.potionItem(1, PotionType.SPEED, "§cSpeed 3"));
				inv.setItem(17, ItemsAPI.potionItem(1, PotionType.SPEED, "§4Speed 4"));
				inv.setItem(21, ItemsAPI.defItem(Material.BARRIER, 1, "§cgo back"));
				inv.setItem(23, ItemsAPI.defItem(Material.BARRIER, 1, "§cclose"));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}

	private static void jumpboost(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3*9, jumpboost);
		ItemStack item = ItemsAPI.defItem(Material.GREEN_STAINED_GLASS_PANE, 1, "§a");
		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(3, item);
		inv.setItem(4, item);
		inv.setItem(5, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		inv.setItem(10, item);
		inv.setItem(12, item);
		inv.setItem(14, item);
		inv.setItem(16, item);
		inv.setItem(18, item);
		inv.setItem(19, item);
		inv.setItem(20, item);
		inv.setItem(22, item);
		inv.setItem(24, item);
		inv.setItem(25, item);
		inv.setItem(26, item);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				inv.setItem(9, ItemsAPI.potionItem(1, PotionType.WATER, "§2Default"));
				inv.setItem(11, ItemsAPI.potionItem(1, PotionType.JUMP, "§aJumpboost 1"));
				inv.setItem(13, ItemsAPI.potionItem(1, PotionType.JUMP, "§eJumpboost 2"));
				inv.setItem(15, ItemsAPI.potionItem(1, PotionType.JUMP, "§cJumpboost 3"));
				inv.setItem(17, ItemsAPI.potionItem(1, PotionType.JUMP, "§4Jumpboost 4"));
				inv.setItem(21, ItemsAPI.defItem(Material.BARRIER, 1, "§cgo back"));
				inv.setItem(23, ItemsAPI.defItem(Material.BARRIER, 1, "§cclose"));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}
	
	static File file = new File("plugins/RCLS/effectslist.yml");
	
	public static void openEffectsInv(Player p){
		Inventory inv = Bukkit.createInventory(null, 9*4, effects);
		String uuid = p.getUniqueId().toString().replace("-", "");
		if(!file.exists()) { try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); } }
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		inv.setItem(0, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(1, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(2, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(3, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(4, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(5, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(6, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(7, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(8, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(9, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(17, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(18, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(21, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(23, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(26, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(27, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(28, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(29, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(30, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(31, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(32, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(33, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(34, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(35, ItemsAPI.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
					inv.setItem(11, ItemsAPI.enchItem(Material.RED_DYE, 1, "§cHearts", Enchantment.DURABILITY));
				}else {
					inv.setItem(11, ItemsAPI.defItem(Material.RED_DYE, 1, "§cHearts"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
					inv.setItem(12, ItemsAPI.enchItem(Material.BONE_MEAL, 1, "§a§fClouds", Enchantment.DURABILITY));
				}else {
					inv.setItem(12, ItemsAPI.defItem(Material.BONE_MEAL, 1, "§a§fClouds"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Music")) {
					inv.setItem(13, ItemsAPI.enchItem(Material.NOTE_BLOCK, 1, "§6Musicnotes", Enchantment.DURABILITY));
				}else {
					inv.setItem(13, ItemsAPI.defItem(Material.NOTE_BLOCK, 1, "§6Musicnotes"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
					inv.setItem(14, ItemsAPI.enchItem(Material.SLIME_BALL, 1, "§aSlime", Enchantment.DURABILITY));
				}else {
					inv.setItem(14, ItemsAPI.defItem(Material.SLIME_BALL, 1, "§aSlime"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Water")) {
					inv.setItem(15, ItemsAPI.enchItem(Material.WATER_BUCKET, 1, "§1Waterdrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(15, ItemsAPI.defItem(Material.WATER_BUCKET, 1, "§1Waterdrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
					inv.setItem(20, ItemsAPI.enchItem(Material.ENDER_EYE, 1, "§9Enderteleport", Enchantment.DURABILITY));
				}else {
					inv.setItem(20, ItemsAPI.defItem(Material.ENDER_EYE, 1, "§9Enderteleport"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
					inv.setItem(24, ItemsAPI.enchItem(Material.EMERALD, 1, "§aEmerald", Enchantment.DURABILITY));
				}else {
					inv.setItem(24, ItemsAPI.defItem(Material.EMERALD, 1, "§aEmerald"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
					inv.setItem(10, ItemsAPI.enchItem(Material.LAVA_BUCKET, 1, "§cLavadrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(10, ItemsAPI.defItem(Material.LAVA_BUCKET, 1, "§cLavadrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
					inv.setItem(16, ItemsAPI.enchItem(Material.HONEY_BOTTLE, 1, "§6Honeydrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(16, ItemsAPI.defItem(Material.HONEY_BOTTLE, 1, "§6Honeydrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
					inv.setItem(19, ItemsAPI.enchItem(Material.REDSTONE, 1, "§cC§2o§6l§co§ar§9s", Enchantment.DURABILITY));
				}else {
					inv.setItem(19, ItemsAPI.defItem(Material.REDSTONE, 1, "§cC§2o§6l§co§ar§9s"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
					inv.setItem(25, ItemsAPI.enchItem(Material.SNOWBALL, 1, "§a§fSnowball", Enchantment.DURABILITY));
				}else {
					inv.setItem(25, ItemsAPI.defItem(Material.SNOWBALL, 1, "§a§fSnowball"));
				}
				inv.setItem(22, ItemsAPI.defItem(Material.BARRIER, 1, "§cclose..."));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public static void invClick(InventoryClickEvent e) throws IOException {
		Player p = (Player)e.getWhoClicked();
		if(e.getView().getTitle().equalsIgnoreCase(mainTitle)) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3")) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Speedboost")) {
				e.setCancelled(true);
				LanguageHandler.sendMSGReady(p, "event.extras.open.speedboost");
				speedboost(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Jumpboost")) {
				e.setCancelled(true);
				jumpboost(p);
				LanguageHandler.sendMSGReady(p, "event.extras.open.jumpboost");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Effects")) {
				e.setCancelled(true);
				openEffectsInv(p);
				LanguageHandler.sendMSGReady(p, "event.extras.open.effects");
			}
		}else if(e.getView().getTitle().equalsIgnoreCase(speedboost)) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Default")) {
				e.setCancelled(true);
				p.setWalkSpeed((float) 0.2);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.default");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSpeed 1")) {
				e.setCancelled(true);
				p.setWalkSpeed((float) 0.4);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step1");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eSpeed 2")) {
				e.setCancelled(true);
				p.setWalkSpeed((float) 0.6);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step2");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSpeed 3")) {
				e.setCancelled(true);
				p.setWalkSpeed((float) 0.8);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step3");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Speed 4")) {
				e.setCancelled(true);
				p.setWalkSpeed((float) 1.0);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step4");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cgo back")) {
				e.setCancelled(true);
				mainInv(p);
				LanguageHandler.sendMSGReady(p, "extras.speedboost.backextras");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cclose")) {
				e.setCancelled(true);
				p.closeInventory();
				LanguageHandler.sendMSGReady(p, "extras.speedboost.close");
			}
		}else if(e.getView().getTitle().equalsIgnoreCase(jumpboost)) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Default")) {
				e.setCancelled(true);
				p.removePotionEffect(PotionEffectType.JUMP);
				LanguageHandler.sendMSGReady(p, "event.extras.jumpboost.default");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aJumpboost 1")) {
				e.setCancelled(true);
				p.removePotionEffect(PotionEffectType.JUMP);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 2));
				LanguageHandler.sendMSGReady(p, "event.extras.jumpboost.step1");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eJumpboost 2")) {
				e.setCancelled(true);
				p.removePotionEffect(PotionEffectType.JUMP);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 4));
				LanguageHandler.sendMSGReady(p, "event.extras.jumpboost.step2");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cJumpboost 3")) {
				e.setCancelled(true);
				p.removePotionEffect(PotionEffectType.JUMP);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 6));
				LanguageHandler.sendMSGReady(p, "event.extras.jumpboost.step3");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Jumpboost 4")) {
				e.setCancelled(true);
				p.removePotionEffect(PotionEffectType.JUMP);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 8));
				LanguageHandler.sendMSGReady(p, "event.extras.jumpboost.step4");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cgo back")) {
				e.setCancelled(true);
				mainInv(p);
				LanguageHandler.sendMSGReady(p, "extras.jumpboost.backextras");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cclose")) {
				e.setCancelled(true);
				p.closeInventory();
				LanguageHandler.sendMSGReady(p, "extras.speedboost.close");
			}
		}else if(e.getView().getTitle().equalsIgnoreCase(effects)) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			String uuid = p.getUniqueId().toString().replace("-", "");
			e.setCancelled(true);
			if(e.getCurrentItem().getType() == Material.PURPLE_STAINED_GLASS_PANE) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cHearts")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
					cfg.set("Effects." + uuid + ".Hearts", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Heart.removed");
				}else {
					cfg.set("Effects." + uuid + ".Hearts", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Heart.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§fClouds")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
					cfg.set("Effects." + uuid + ".Clouds", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Cloud.removed");
				}else {
					cfg.set("Effects." + uuid + ".Clouds", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Cloud.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Musicnotes")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Music")) {
					cfg.set("Effects." + uuid + ".Music", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Music.removed");
				}else {
					cfg.set("Effects." + uuid + ".Music", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Music.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSlime")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
					cfg.set("Effects." + uuid + ".Slime", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Slime.removed");
				}else {
					cfg.set("Effects." + uuid + ".Slime", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Slime.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§1Waterdrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Water")) {
					cfg.set("Effects." + uuid + ".Water", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Water.removed");
				}else {
					cfg.set("Effects." + uuid + ".Water", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Water.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Enderteleport")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
					cfg.set("Effects." + uuid + ".Ender", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Ender.removed");
				}else {
					cfg.set("Effects." + uuid + ".Ender", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Ender.selected");
				}

				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cclose...")) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEmerald")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
					cfg.set("Effects." + uuid + ".Emerald", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Emerald.removed");
				}else {
					cfg.set("Effects." + uuid + ".Emerald", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Emerald.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cLavadrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
					cfg.set("Effects." + uuid + ".Lava", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Lava.removed");
				}else {
					cfg.set("Effects." + uuid + ".Lava", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Lava.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Honeydrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
					cfg.set("Effects." + uuid + ".Honey", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Honey.removed");
				}else {
					cfg.set("Effects." + uuid + ".Honey", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Honey.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cC§2o§6l§co§ar§9s")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
					cfg.set("Effects." + uuid + ".Redstone", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Redstone.removed");
				}else {
					cfg.set("Effects." + uuid + ".Redstone", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Redstone.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§fSnowball")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
					cfg.set("Effects." + uuid + ".Snow", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Snow.removed");
				}else {
					cfg.set("Effects." + uuid + ".Snow", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Snow.selected");
				}
				cfg.save(file);
				openEffectsInv(p);
			}
		}
	}
}