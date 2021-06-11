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
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 9*3, mainTitle);
		ItemStack item = iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§3");
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
				inv.setItem(11, iapi.potionItem(1, PotionType.SPEED, speedboost));
				inv.setItem(13, iapi.potionItem(1, PotionType.JUMP, jumpboost));
				inv.setItem(15, iapi.defItem(Material.LEATHER_BOOTS, 1, effects));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}
	
	private static void speedboost(Player p) {
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 3*9, speedboost);
		ItemStack item = iapi.defItem(Material.GREEN_STAINED_GLASS_PANE, 1, "§a");
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
				inv.setItem(9, iapi.potionItem(1, PotionType.WATER, "§2Default"));
				inv.setItem(11, iapi.potionItem(1, PotionType.SPEED, "§aSpeed 1"));
				inv.setItem(13, iapi.potionItem(1, PotionType.SPEED, "§eSpeed 2"));
				inv.setItem(15, iapi.potionItem(1, PotionType.SPEED, "§cSpeed 3"));
				inv.setItem(17, iapi.potionItem(1, PotionType.SPEED, "§4Speed 4"));
				inv.setItem(21, iapi.defItem(Material.BARRIER, 1, "§cgo back"));
				inv.setItem(23, iapi.defItem(Material.BARRIER, 1, "§cclose"));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}

	private static void jumpboost(Player p) {
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 3*9, jumpboost);
		ItemStack item = iapi.defItem(Material.GREEN_STAINED_GLASS_PANE, 1, "§a");
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
				inv.setItem(9, iapi.potionItem(1, PotionType.WATER, "§2Default"));
				inv.setItem(11, iapi.potionItem(1, PotionType.JUMP, "§aJumpboost 1"));
				inv.setItem(13, iapi.potionItem(1, PotionType.JUMP, "§eJumpboost 2"));
				inv.setItem(15, iapi.potionItem(1, PotionType.JUMP, "§cJumpboost 3"));
				inv.setItem(17, iapi.potionItem(1, PotionType.JUMP, "§4Jumpboost 4"));
				inv.setItem(21, iapi.defItem(Material.BARRIER, 1, "§cgo back"));
				inv.setItem(23, iapi.defItem(Material.BARRIER, 1, "§cclose"));
			}
		}, 5);
		p.openInventory(inv);
		p.updateInventory();
	}
	
	public static void openEffectsInv(Player p){
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 9*4, effects);
		String uuid = p.getUniqueId().toString().replace("-", "");
		File file = new File("plugins/RCLS/effectslist.yml");
		if(!file.exists()) { try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); } }
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		inv.setItem(0, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(1, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(2, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(3, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(4, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(5, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(6, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(7, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(8, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(9, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(17, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(18, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(26, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(27, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(28, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(29, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(30, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(31, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(32, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(33, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(34, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(35, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
					inv.setItem(11, iapi.enchItem(Material.RED_DYE, 1, "§7§ §cHearts", Enchantment.DURABILITY));
				}else {
					inv.setItem(11, iapi.defItem(Material.RED_DYE, 1, "§7§ §cHearts"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
					inv.setItem(12, iapi.enchItem(Material.BONE_MEAL, 1, "§7§ §fClouds", Enchantment.DURABILITY));
				}else {
					inv.setItem(12, iapi.defItem(Material.BONE_MEAL, 1, "§7§ §fClouds"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Music")) {
					inv.setItem(13, iapi.enchItem(Material.NOTE_BLOCK, 1, "§7§ §6Musicnotes", Enchantment.DURABILITY));
				}else {
					inv.setItem(13, iapi.defItem(Material.NOTE_BLOCK, 1, "§7§ §6Musicnotes"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
					inv.setItem(14, iapi.enchItem(Material.SLIME_BALL, 1, "§7§ §aSlime", Enchantment.DURABILITY));
				}else {
					inv.setItem(14, iapi.defItem(Material.SLIME_BALL, 1, "§7§ §aSlime"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Water")) {
					inv.setItem(15, iapi.enchItem(Material.WATER_BUCKET, 1, "§7§ §1Waterdrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(15, iapi.defItem(Material.WATER_BUCKET, 1, "§7§ §1Waterdrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
					inv.setItem(20, iapi.enchItem(Material.ENDER_EYE, 1, "§7§ §9Enderteleport", Enchantment.DURABILITY));
				}else {
					inv.setItem(20, iapi.defItem(Material.ENDER_EYE, 1, "§7§ §9Enderteleport"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
					inv.setItem(24, iapi.enchItem(Material.EMERALD, 1, "§7§ §aEmerald", Enchantment.DURABILITY));
				}else {
					inv.setItem(24, iapi.defItem(Material.EMERALD, 1, "§7§ §aEmerald"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
					inv.setItem(10, iapi.enchItem(Material.LAVA_BUCKET, 1, "§7§ §cLavadrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(10, iapi.defItem(Material.LAVA_BUCKET, 1, "§7§ §cLavadrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
					inv.setItem(16, iapi.enchItem(Material.HONEY_BOTTLE, 1, "§7§ §6Honeydrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(16, iapi.defItem(Material.HONEY_BOTTLE, 1, "§7§ §6Honeydrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
					inv.setItem(19, iapi.enchItem(Material.REDSTONE, 1, "§7§ §cC§2o§6l§co§ar§9s", Enchantment.DURABILITY));
				}else {
					inv.setItem(19, iapi.defItem(Material.REDSTONE, 1, "§7§ §cC§2o§6l§co§ar§9s"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
					inv.setItem(25, iapi.enchItem(Material.SNOWBALL, 1, "§7§ §fSnowball", Enchantment.DURABILITY));
				}else {
					inv.setItem(25, iapi.defItem(Material.SNOWBALL, 1, "§7§ §fSnowball"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".SoulFireflame")) {
					inv.setItem(21, iapi.enchItem(Material.SOUL_TORCH, 1, "§7§ §bSoulfireflames", Enchantment.DURABILITY));
				}else {
					inv.setItem(21, iapi.defItem(Material.SOUL_TORCH, 1, "§7§ §bSoulfireflames"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Ash")) {
					inv.setItem(23, iapi.enchItem(Material.BASALT, 1, "§7§ §7Ash", Enchantment.DURABILITY));
				}else {
					inv.setItem(23, iapi.defItem(Material.BASALT, 1, "§7§ §7Ash"));
				}
				inv.setItem(22, iapi.defItem(Material.BARRIER, 1, "§cclose..."));
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
				p.closeInventory();
				speedboost(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Jumpboost")) {
				e.setCancelled(true);
				p.closeInventory();
				jumpboost(p);
				LanguageHandler.sendMSGReady(p, "event.extras.open.jumpboost");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Effects")) {
				e.setCancelled(true);
				p.closeInventory();
				openEffectsInv(p);
				LanguageHandler.sendMSGReady(p, "event.extras.open.effects");
			}
		}else if(e.getView().getTitle().equalsIgnoreCase(speedboost)) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Default")) {
				e.setCancelled(true);
				//p.setWalkSpeed((float) 0.2);
				p.removePotionEffect(PotionEffectType.SPEED);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.default");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSpeed 1")) {
				e.setCancelled(true);
				//p.setWalkSpeed((float) 0.4);
				p.removePotionEffect(PotionEffectType.SPEED);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 4));
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step1");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eSpeed 2")) {
				e.setCancelled(true);
				//p.setWalkSpeed((float) 0.6);
				p.removePotionEffect(PotionEffectType.SPEED);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 6));
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step2");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSpeed 3")) {
				e.setCancelled(true);
				//p.setWalkSpeed((float) 0.8);
				p.removePotionEffect(PotionEffectType.SPEED);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 8));
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step3");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Speed 4")) {
				e.setCancelled(true);
				//p.setWalkSpeed((float) 1.0);
				p.removePotionEffect(PotionEffectType.SPEED);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 10));
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.step4");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cgo back")) {
				e.setCancelled(true);
				mainInv(p);
				LanguageHandler.sendMSGReady(p, "event.extras.speedboost.backextras");
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
				LanguageHandler.sendMSGReady(p, "event.extras.jumpboost.backextras");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cclose")) {
				e.setCancelled(true);
				p.closeInventory();
				LanguageHandler.sendMSGReady(p, "extras.speedboost.close");
			}
		}else if(e.getView().getTitle().equalsIgnoreCase(effects)) {
			File file = new File("plugins/RCLS/effectslist.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			String uuid = p.getUniqueId().toString().replace("-", "");
			e.setCancelled(true);
			if(e.getCurrentItem().getType() == Material.PURPLE_STAINED_GLASS_PANE) {
				e.setCancelled(true);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §cHearts")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
					cfg.set("Effects." + uuid + ".Hearts", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Heart.removed");
				}else {
					cfg.set("Effects." + uuid + ".Hearts", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Heart.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7§ §fClouds")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
					cfg.set("Effects." + uuid + ".Clouds", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Cloud.removed");
				}else {
					cfg.set("Effects." + uuid + ".Clouds", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Cloud.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §6Musicnotes")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Music")) {
					cfg.set("Effects." + uuid + ".Music", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Music.removed");
				}else {
					cfg.set("Effects." + uuid + ".Music", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Music.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §aSlime")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
					cfg.set("Effects." + uuid + ".Slime", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Slime.removed");
				}else {
					cfg.set("Effects." + uuid + ".Slime", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Slime.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §1Waterdrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Water")) {
					cfg.set("Effects." + uuid + ".Water", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Water.removed");
				}else {
					cfg.set("Effects." + uuid + ".Water", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Water.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §9Enderteleport")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
					cfg.set("Effects." + uuid + ".Ender", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Ender.removed");
				}else {
					cfg.set("Effects." + uuid + ".Ender", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Ender.selected");
				}

				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §cclose...")) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §aEmerald")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
					cfg.set("Effects." + uuid + ".Emerald", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Emerald.removed");
				}else {
					cfg.set("Effects." + uuid + ".Emerald", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Emerald.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §cLavadrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
					cfg.set("Effects." + uuid + ".Lava", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Lava.removed");
				}else {
					cfg.set("Effects." + uuid + ".Lava", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Lava.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §6Honeydrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
					cfg.set("Effects." + uuid + ".Honey", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Honey.removed");
				}else {
					cfg.set("Effects." + uuid + ".Honey", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Honey.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §cC§2o§6l§co§ar§9s")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
					cfg.set("Effects." + uuid + ".Redstone", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Redstone.removed");
				}else {
					cfg.set("Effects." + uuid + ".Redstone", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Redstone.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §fSnowball")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
					cfg.set("Effects." + uuid + ".Snow", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Snow.removed");
				}else {
					cfg.set("Effects." + uuid + ".Snow", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Snow.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §bSoulfireflames")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".SoulFireflame")) {
					cfg.set("Effects." + uuid + ".SoulFireflame", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Soulfire.removed");
				}else {
					cfg.set("Effects." + uuid + ".SoulFireflame", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Soulfire.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§ §7Ash")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Ash")) {
					cfg.set("Effects." + uuid + ".Ash", false);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Ash.removed");
				}else {
					cfg.set("Effects." + uuid + ".Ash", true);
					LanguageHandler.sendMSGReady(p, "event.extras.effects.Ash.selected");
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}
		}
	}
}