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
import at.mlps.rc.api.Prefix;
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
		Inventory inv = Bukkit.createInventory(null, 9*5, effects);
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
		inv.setItem(35, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(36, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(37, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(38, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(39, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(40, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(41, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(42, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(43, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		inv.setItem(44, iapi.defItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "§9"));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
					inv.setItem(11, iapi.enchItem(Material.RED_DYE, 1, "§7» §cHearts", Enchantment.DURABILITY));
				}else {
					inv.setItem(11, iapi.defItem(Material.RED_DYE, 1, "§7» §cHearts"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
					inv.setItem(12, iapi.enchItem(Material.BONE_MEAL, 1, "§7» §fClouds", Enchantment.DURABILITY));
				}else {
					inv.setItem(12, iapi.defItem(Material.BONE_MEAL, 1, "§7» §fClouds"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Music")) {
					inv.setItem(13, iapi.enchItem(Material.NOTE_BLOCK, 1, "§7» §6Musicnotes", Enchantment.DURABILITY));
				}else {
					inv.setItem(13, iapi.defItem(Material.NOTE_BLOCK, 1, "§7» §6Musicnotes"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
					inv.setItem(14, iapi.enchItem(Material.SLIME_BALL, 1, "§7» §aSlime", Enchantment.DURABILITY));
				}else {
					inv.setItem(14, iapi.defItem(Material.SLIME_BALL, 1, "§7» §aSlime"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Water")) {
					inv.setItem(15, iapi.enchItem(Material.WATER_BUCKET, 1, "§7» §1Waterdrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(15, iapi.defItem(Material.WATER_BUCKET, 1, "§7» §1Waterdrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
					inv.setItem(20, iapi.enchItem(Material.ENDER_EYE, 1, "§7» §9Enderteleport", Enchantment.DURABILITY));
				}else {
					inv.setItem(20, iapi.defItem(Material.ENDER_EYE, 1, "§7» §9Enderteleport"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
					inv.setItem(24, iapi.enchItem(Material.EMERALD, 1, "§7» §aEmerald", Enchantment.DURABILITY));
				}else {
					inv.setItem(24, iapi.defItem(Material.EMERALD, 1, "§7» §aEmerald"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
					inv.setItem(10, iapi.enchItem(Material.LAVA_BUCKET, 1, "§7» §cLavadrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(10, iapi.defItem(Material.LAVA_BUCKET, 1, "§7» §cLavadrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
					inv.setItem(16, iapi.enchItem(Material.HONEY_BOTTLE, 1, "§7» §6Honeydrops", Enchantment.DURABILITY));
				}else {
					inv.setItem(16, iapi.defItem(Material.HONEY_BOTTLE, 1, "§7» §6Honeydrops"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
					inv.setItem(19, iapi.enchItem(Material.REDSTONE, 1, "§7» §cC§2o§6l§co§ar§9s", Enchantment.DURABILITY));
				}else {
					inv.setItem(19, iapi.defItem(Material.REDSTONE, 1, "§7» §cC§2o§6l§co§ar§9s"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
					inv.setItem(25, iapi.enchItem(Material.SNOWBALL, 1, "§7» §fSnowball", Enchantment.DURABILITY));
				}else {
					inv.setItem(25, iapi.defItem(Material.SNOWBALL, 1, "§7» §fSnowball"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".SoulFireflame")) {
					inv.setItem(21, iapi.enchItem(Material.SOUL_TORCH, 1, "§7» §bSoulfireflames", Enchantment.DURABILITY));
				}else {
					inv.setItem(21, iapi.defItem(Material.SOUL_TORCH, 1, "§7» §bSoulfireflames"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Ash")) {
					inv.setItem(23, iapi.enchItem(Material.BASALT, 1, "§7» §7Ash", Enchantment.DURABILITY));
				}else {
					inv.setItem(23, iapi.defItem(Material.BASALT, 1, "§7» §7Ash"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Souls")) {
					inv.setItem(29, iapi.enchItem(Material.SOUL_SAND, 1, "§7» §bSouls", Enchantment.DURABILITY));
				}else {
					inv.setItem(29, iapi.defItem(Material.SOUL_SAND, 1, "§7» §bSouls"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".Glow")) {
					inv.setItem(30, iapi.enchItem(Material.GLOWSTONE, 1, "§7» §eGlow", Enchantment.DURABILITY));
				}else {
					inv.setItem(30, iapi.defItem(Material.GLOWSTONE, 1, "§7» §eGlow"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".EndRod")) {
					inv.setItem(32, iapi.enchItem(Material.END_ROD, 1, "§7» §fEnd Rod Particles", Enchantment.DURABILITY));
				}else {
					inv.setItem(32, iapi.defItem(Material.END_ROD, 1, "§7» §fEnd Rod Particles"));
				}
				if(cfg.getBoolean("Effects." + uuid + ".ObsidianTears")) {
					inv.setItem(33, iapi.enchItem(Material.CRYING_OBSIDIAN, 1, "§7» §5Obsidian Tears", Enchantment.DURABILITY));
				}else {
					inv.setItem(33, iapi.defItem(Material.CRYING_OBSIDIAN, 1, "§7» §5Obsidian Tears"));
				}
				inv.setItem(31, iapi.defItem(Material.BARRIER, 1, "§cclose..."));
				//drip obsidian tear, end rod, glow, soul
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
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §cHearts")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Hearts")) {
					cfg.set("Effects." + uuid + ".Hearts", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§cHearts"));
				}else {
					cfg.set("Effects." + uuid + ".Hearts", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§cHearts"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §fClouds")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Clouds")) {
					cfg.set("Effects." + uuid + ".Clouds", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§fCouds"));
				}else {
					cfg.set("Effects." + uuid + ".Clouds", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§fClouds"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §6Musicnotes")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Music")) {
					cfg.set("Effects." + uuid + ".Music", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§6Musicnotes"));
				}else {
					cfg.set("Effects." + uuid + ".Music", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§6Musicnotes"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §aSlime")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Slime")) {
					cfg.set("Effects." + uuid + ".Slime", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§aSlime"));
				}else {
					cfg.set("Effects." + uuid + ".Slime", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§aSlime"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §1Waterdrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Water")) {
					cfg.set("Effects." + uuid + ".Water", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§1Waterdrops"));
				}else {
					cfg.set("Effects." + uuid + ".Water", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§1Waterdrops"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §9Enderteleport")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Ender")) {
					cfg.set("Effects." + uuid + ".Ender", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§9Enderteleport"));
				}else {
					cfg.set("Effects." + uuid + ".Ender", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§9Enderteleport"));
				}

				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §cclose...")) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §aEmerald")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Emerald")) {
					cfg.set("Effects." + uuid + ".Emerald", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§aEmerald"));
				}else {
					cfg.set("Effects." + uuid + ".Emerald", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§aEmerald"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §cLavadrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Lava")) {
					cfg.set("Effects." + uuid + ".Lava", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§cLavadrops"));
				}else {
					cfg.set("Effects." + uuid + ".Lava", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§cLavadrops"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §6Honeydrops")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Honey")) {
					cfg.set("Effects." + uuid + ".Honey", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§6Honeydrops"));
				}else {
					cfg.set("Effects." + uuid + ".Honey", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§6Honeydrops"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §cC§2o§6l§co§ar§9s")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Redstone")) {
					cfg.set("Effects." + uuid + ".Redstone", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§cC§2o§6l§co§ar§9s"));
				}else {
					cfg.set("Effects." + uuid + ".Redstone", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§cC§2o§6l§co§ar§9s"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §fSnowball")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Snow")) {
					cfg.set("Effects." + uuid + ".Snow", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§fSnowball"));
				}else {
					cfg.set("Effects." + uuid + ".Snow", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§fSnowball"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §bSoulfireflames")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".SoulFireflame")) {
					cfg.set("Effects." + uuid + ".SoulFireflame", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§bSoulfireflames"));
				}else {
					cfg.set("Effects." + uuid + ".SoulFireflame", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§bSoulfireflames"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §7Ash")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Ash")) {
					cfg.set("Effects." + uuid + ".Ash", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§7Ash"));
				}else {
					cfg.set("Effects." + uuid + ".Ash", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§7Ash"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §bSouls")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Souls")) {
					cfg.set("Effects." + uuid + ".Souls", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§bSouls"));
				}else {
					cfg.set("Effects." + uuid + ".Souls", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§bSouls"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §eGlow")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".Glow")) {
					cfg.set("Effects." + uuid + ".Glow", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§eGlow"));
				}else {
					cfg.set("Effects." + uuid + ".Glow", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§eGlow"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §fEnd Rod Particles")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".EndRod")) {
					cfg.set("Effects." + uuid + ".EndRod", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§fEnd Rod Particles"));
				}else {
					cfg.set("Effects." + uuid + ".EndRod", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§fEnd Rod Particles"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §5Obsidian Tears")) {
				e.setCancelled(true);
				if(cfg.getBoolean("Effects." + uuid + ".ObsidianTears")) {
					cfg.set("Effects." + uuid + ".ObsidianTears", false);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.removed").replace("%effect", "§5Obsidian Tears"));
				}else {
					cfg.set("Effects." + uuid + ".ObsidianTears", true);
					p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.extras.effects.selected").replace("%effect", "§5Obsidian Tears"));
				}
				cfg.save(file);
				p.closeInventory();
				openEffectsInv(p);
			}
		}
	}
}