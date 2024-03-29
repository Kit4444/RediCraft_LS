package at.kitsoft.redicraft.event;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import at.kitsoft.redicraft.api.Advents;
import at.kitsoft.redicraft.api.ItemsAPI;
import at.kitsoft.redicraft.api.Prefix;
import at.kitsoft.redicraft.command.MoneyAPI;
import at.kitsoft.redicraft.main.LanguageHandler;
import at.kitsoft.redicraft.mysql.lb.MySQL;

public class AdventsHandler implements Listener{
	
	public static void setAdventInv(Player p) {
		ItemsAPI iapi = new ItemsAPI();
		Inventory inv = Bukkit.createInventory(null, 9*6, "§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr");
		Advents aapi = new Advents();
		Set<Integer> set = random1(0, 54, 24);
		List<Integer> list = new ArrayList<>();
		set.forEach(ra -> {
			list.add(ra);
		});
		int slot1 = list.get(0);
		int slot2 = list.get(1);
		int slot3 = list.get(2);
		int slot4 = list.get(3);
		int slot5 = list.get(4);
		int slot6 = list.get(5);
		int slot7 = list.get(6);
		int slot8 = list.get(7);
		int slot9 = list.get(8);
		int slot10 = list.get(9);
		int slot11 = list.get(10);
		int slot12 = list.get(11);
		int slot13 = list.get(12);
		int slot14 = list.get(13);
		int slot15 = list.get(14);
		int slot16 = list.get(15);
		int slot17 = list.get(16);
		int slot18 = list.get(17);
		int slot19 = list.get(18);
		int slot20 = list.get(19);
		int slot21 = list.get(20);
		int slot22 = list.get(21);
		int slot23 = list.get(22);
		int slot24 = list.get(23);
		
		if(aapi.isAllowedDate(1)) {
			if(aapi.hasRewardUsed(p, 1)) {
				inv.setItem(slot1, iapi.defItem(Material.MINECART, 1, "§cDay 1 §7- used"));
			}else {
				inv.setItem(slot1, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 1"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 1)) {
				inv.setItem(slot1, iapi.defItem(Material.MINECART, 1, "§cDay 1 §7- used"));
			}else {
				inv.setItem(slot1, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(2)) {
			if(aapi.hasRewardUsed(p, 2)) {
				inv.setItem(slot2, iapi.defItem(Material.MINECART, 1, "§cDay 2 §7- used"));
			}else {
				inv.setItem(slot2, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 2"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 2)) {
				inv.setItem(slot2, iapi.defItem(Material.MINECART, 1, "§cDay 2 §7- used"));
			}else {
				inv.setItem(slot2, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(3)) {
			if(aapi.hasRewardUsed(p, 3)) {
				inv.setItem(slot3, iapi.defItem(Material.MINECART, 1, "§cDay 3 §7- used"));
			}else {
				inv.setItem(slot3, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 3"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 3)) {
				inv.setItem(slot3, iapi.defItem(Material.MINECART, 1, "§cDay 3 §7- used"));
			}else {
				inv.setItem(slot3, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(4)) {
			if(aapi.hasRewardUsed(p, 4)) {
				inv.setItem(slot4, iapi.defItem(Material.MINECART, 1, "§cDay 4 §7- used"));
			}else {
				inv.setItem(slot4, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 4"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 4)) {
				inv.setItem(slot4, iapi.defItem(Material.MINECART, 1, "§cDay 4 §7- used"));
			}else {
				inv.setItem(slot4, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(5)) {
			if(aapi.hasRewardUsed(p, 5)) {
				inv.setItem(slot5, iapi.defItem(Material.MINECART, 1, "§cDay 5 §7- used"));
			}else {
				inv.setItem(slot5, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 5"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 5)) {
				inv.setItem(slot5, iapi.defItem(Material.MINECART, 1, "§cDay 5 §7- used"));
			}else {
				inv.setItem(slot5, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(6)) {
			if(aapi.hasRewardUsed(p, 6)) {
				inv.setItem(slot6, iapi.defItem(Material.MINECART, 1, "§cDay 6 §7- used"));
			}else {
				inv.setItem(slot6, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 6"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 6)) {
				inv.setItem(slot6, iapi.defItem(Material.MINECART, 1, "§cDay 6 §7- used"));
			}else {
				inv.setItem(slot6, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(7)) {
			if(aapi.hasRewardUsed(p, 7)) {
				inv.setItem(slot7, iapi.defItem(Material.MINECART, 1, "§cDay 7 §7- used"));
			}else {
				inv.setItem(slot7, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 7"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 7)) {
				inv.setItem(slot7, iapi.defItem(Material.MINECART, 1, "§cDay 7 §7- used"));
			}else {
				inv.setItem(slot7, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(8)) {
			if(aapi.hasRewardUsed(p, 8)) {
				inv.setItem(slot8, iapi.defItem(Material.MINECART, 1, "§cDay 8 §7- used"));
			}else {
				inv.setItem(slot8, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 8"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 8)) {
				inv.setItem(slot8, iapi.defItem(Material.MINECART, 1, "§cDay 8 §7- used"));
			}else {
				inv.setItem(slot8, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(9)) {
			if(aapi.hasRewardUsed(p, 9)) {
				inv.setItem(slot9, iapi.defItem(Material.MINECART, 1, "§cDay 9 §7- used"));
			}else {
				inv.setItem(slot9, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 9"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 9)) {
				inv.setItem(slot9, iapi.defItem(Material.MINECART, 1, "§cDay 9 §7- used"));
			}else {
				inv.setItem(slot9, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(10)) {
			if(aapi.hasRewardUsed(p, 10)) {
				inv.setItem(slot10, iapi.defItem(Material.MINECART, 1, "§cDay 10 §7- used"));
			}else {
				inv.setItem(slot10, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 10"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 10)) {
				inv.setItem(slot10, iapi.defItem(Material.MINECART, 1, "§cDay 10 §7- used"));
			}else {
				inv.setItem(slot10, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(11)) {
			if(aapi.hasRewardUsed(p, 11)) {
				inv.setItem(slot11, iapi.defItem(Material.MINECART, 1, "§cDay 11 §7- used"));
			}else {
				inv.setItem(slot11, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 11"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 11)) {
				inv.setItem(slot11, iapi.defItem(Material.MINECART, 1, "§cDay 11 §7- used"));
			}else {
				inv.setItem(slot11, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(12)) {
			if(aapi.hasRewardUsed(p, 12)) {
				inv.setItem(slot12, iapi.defItem(Material.MINECART, 1, "§cDay 12 §7- used"));
			}else {
				inv.setItem(slot12, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 12"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 12)) {
				inv.setItem(slot12, iapi.defItem(Material.MINECART, 1, "§cDay 12 §7- used"));
			}else {
				inv.setItem(slot12, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(13)) {
			if(aapi.hasRewardUsed(p, 13)) {
				inv.setItem(slot13, iapi.defItem(Material.MINECART, 1, "§cDay 13 §7- used"));
			}else {
				inv.setItem(slot13, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 13"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 13)) {
				inv.setItem(slot13, iapi.defItem(Material.MINECART, 1, "§cDay 13 §7- used"));
			}else {
				inv.setItem(slot13, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(14)) {
			if(aapi.hasRewardUsed(p, 14)) {
				inv.setItem(slot14, iapi.defItem(Material.MINECART, 1, "§cDay 14 §7- used"));
			}else {
				inv.setItem(slot14, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 14"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 14)) {
				inv.setItem(slot14, iapi.defItem(Material.MINECART, 1, "§cDay 14 §7- used"));
			}else {
				inv.setItem(slot14, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(15)) {
			if(aapi.hasRewardUsed(p, 15)) {
				inv.setItem(slot15, iapi.defItem(Material.MINECART, 1, "§cDay 15 §7- used"));
			}else {
				inv.setItem(slot15, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 15"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 15)) {
				inv.setItem(slot15, iapi.defItem(Material.MINECART, 1, "§cDay 15 §7- used"));
			}else {
				inv.setItem(slot15, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(16)) {
			if(aapi.hasRewardUsed(p, 16)) {
				inv.setItem(slot16, iapi.defItem(Material.MINECART, 1, "§cDay 16 §7- used"));
			}else {
				inv.setItem(slot16, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 16"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 16)) {
				inv.setItem(slot16, iapi.defItem(Material.MINECART, 1, "§cDay 16 §7- used"));
			}else {
				inv.setItem(slot16, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(17)) {
			if(aapi.hasRewardUsed(p, 17)) {
				inv.setItem(slot17, iapi.defItem(Material.MINECART, 1, "§cDay 17 §7- used"));
			}else {
				inv.setItem(slot17, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 17"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 17)) {
				inv.setItem(slot17, iapi.defItem(Material.MINECART, 1, "§cDay 17 §7- used"));
			}else {
				inv.setItem(slot17, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(18)) {
			if(aapi.hasRewardUsed(p, 18)) {
				inv.setItem(slot18, iapi.defItem(Material.MINECART, 1, "§cDay 18 §7- used"));
			}else {
				inv.setItem(slot18, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 18"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 18)) {
				inv.setItem(slot18, iapi.defItem(Material.MINECART, 1, "§cDay 18 §7- used"));
			}else {
				inv.setItem(slot18, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(19)) {
			if(aapi.hasRewardUsed(p, 19)) {
				inv.setItem(slot19, iapi.defItem(Material.MINECART, 1, "§cDay 19 §7- used"));
			}else {
				inv.setItem(slot19, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 19"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 19)) {
				inv.setItem(slot19, iapi.defItem(Material.MINECART, 1, "§cDay 19 §7- used"));
			}else {
				inv.setItem(slot19, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(20)) {
			if(aapi.hasRewardUsed(p, 20)) {
				inv.setItem(slot20, iapi.defItem(Material.MINECART, 1, "§cDay 20 §7- used"));
			}else {
				inv.setItem(slot20, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 20"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 20)) {
				inv.setItem(slot20, iapi.defItem(Material.MINECART, 1, "§cDay 20 §7- used"));
			}else {
				inv.setItem(slot20, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(21)) {
			if(aapi.hasRewardUsed(p, 21)) {
				inv.setItem(slot21, iapi.defItem(Material.MINECART, 1, "§cDay 21 §7- used"));
			}else {
				inv.setItem(slot21, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 21"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 21)) {
				inv.setItem(slot21, iapi.defItem(Material.MINECART, 1, "§cDay 21 §7- used"));
			}else {
				inv.setItem(slot21, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(22)) {
			if(aapi.hasRewardUsed(p, 22)) {
				inv.setItem(slot22, iapi.defItem(Material.MINECART, 1, "§cDay 22 §7- used"));
			}else {
				inv.setItem(slot22, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 22"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 22)) {
				inv.setItem(slot22, iapi.defItem(Material.MINECART, 1, "§cDay 22 §7- used"));
			}else {
				inv.setItem(slot22, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(23)) {
			if(aapi.hasRewardUsed(p, 23)) {
				inv.setItem(slot23, iapi.defItem(Material.MINECART, 1, "§cDay 23 §7- used"));
			}else {
				inv.setItem(slot23, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 23"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 23)) {
				inv.setItem(slot23, iapi.defItem(Material.MINECART, 1, "§cDay 23 §7- used"));
			}else {
				inv.setItem(slot23, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		if(aapi.isAllowedDate(24)) {
			if(aapi.hasRewardUsed(p, 24)) {
				inv.setItem(slot24, iapi.defItem(Material.MINECART, 1, "§cDay 24 §7- used"));
			}else {
				inv.setItem(slot24, iapi.defItem(Material.CHEST_MINECART, 1, "§cDay 24"));
			}
		}else {
			if(aapi.hasRewardUsed(p, 24)) {
				inv.setItem(slot24, iapi.defItem(Material.MINECART, 1, "§cDay 24 §7- used"));
			}else {
				inv.setItem(slot24, iapi.defItem(Material.HOPPER_MINECART, 1, "§cA §fD§ca§fy"));
			}
		}
		p.openInventory(inv);
		p.updateInventory();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getView().getTitle().equalsIgnoreCase("§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr")) {
			e.setCancelled(true);
			p.updateInventory();
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 1")) {
				randomGift(p, "1");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 2")) {
				randomGift(p, "2");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 3")) {
				randomGift(p, "3");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 4")) {
				randomGift(p, "4");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 5")) {
				randomGift(p, "5");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 6")) {
				randomGift(p, "6");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 7")) {
				randomGift(p, "7");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 8")) {
				randomGift(p, "8");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 9")) {
				randomGift(p, "9");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 10")) {
				randomGift(p, "10");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 11")) {
				randomGift(p, "11");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 12")) {
				randomGift(p, "12");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 13")) {
				randomGift(p, "13");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 14")) {
				randomGift(p, "14");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 15")) {
				randomGift(p, "15");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 16")) {
				randomGift(p, "16");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 17")) {
				randomGift(p, "17");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 18")) {
				randomGift(p, "18");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 19")) {
				randomGift(p, "19");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 20")) {
				randomGift(p, "20");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 21")) {
				randomGift(p, "21");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 22")) {
				randomGift(p, "22");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 23")) {
				randomGift(p, "23");
				p.closeInventory();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 24")) {
				randomGift(p, "24");
				p.closeInventory();
				//USED SECTION HERE - DO NOT MIX THIS UP!
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 1 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(1)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 2 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(2)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 3 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(3)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 4 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(4)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 5 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(5)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 6 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(6)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 7 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(7)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 8 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(8)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 9 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(9)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 10 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(10)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 11 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(11)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 12 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(12)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 13 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(13)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 14 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(14)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 15 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(15)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 16 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(16)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 17 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(17)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 18 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(18)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 19 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(19)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 20 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(20)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 21 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(21)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 22 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(22)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 23 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(23)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDay 24 §7- used")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.used").replace("%day", String.valueOf(24)));
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cA §fD§ca§fy")) {
				p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.hidden"));
			}
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		if(ent.getType() == EntityType.SNOWMAN) {
			Snowman v = (Snowman) ent;
			if(v.getCustomName().equalsIgnoreCase("§cA§fd§cv§fe§cn§ft §cC§fa§cl§fe§cn§fd§ca§fr")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM");
				String month = sdf.format(new Date());
				if(month.equalsIgnoreCase("12")) {
					setAdventInv(p);
				}else {
					p.sendMessage(Prefix.prefix("main") + "§cSorry, but it's not december yet!");
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		Entity dmg = e.getDamager();
		Entity tar = e.getEntity();
		if(tar.getType() == EntityType.SNOWMAN) {
			if(dmg.getType() == EntityType.PLAYER) {
				Player p = (Player) dmg;
				e.setCancelled(true);
				p.sendMessage(Prefix.prefix("main") + "Hey, why you want to hurt our poor snowman?");
			}else {
				e.setCancelled(true);
			}
		}
	}
	
	private void randomGift(Player p, String day) {
		int gift = random(1, 11);
		Advents advent = new Advents();
		advent.setReward(p, Integer.valueOf(day));
		switch(gift) {
		case 1: //Role VIP
			String giftCode3 = addGift(p, "1*:viprole");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "VIP Role, Gift Code: " + giftCode3).replace("%day", day));
			break;
		case 2: //Money (25k - 500k)
			int money = random(25000, 500000);
			MoneyAPI.addMoney(p.getUniqueId(), money);
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "Money: " + money + " Coins").replace("%day", day));
			break;
		case 3: //XP Boost 3d
			String giftCode = addGift(p, "3d:xpboost");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "3 Days Double XP on Survival Servers, Gift Code: " + giftCode).replace("%day", day));
			break;
		case 4: //XP Gift
			String giftCode1 = addGift(p, "1*:xperpts");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "Experience Points on Survival Servers, Gift Code: " + giftCode1).replace("%day", day));
			break;
		case 5: //enchanted Items
			String giftCode2 = addGift(p, "1*:enchItems");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "Enchanted Items on Survival Servers, Gift Code: " + giftCode2).replace("%day", day));
			break;
		case 6: //more plots
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add plots.plot.6");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "More plots to claim").replace("%day", day));
			break;
		case 7: //fly
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mlps.userfly");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "Fly allowance").replace("%day", day));
			break;
		case 8: //tempuse of /weather command
			String giftCode6 = addGift(p, "5t:weather");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "5 Uses of the /weather command, Gift Code: " + giftCode6).replace("%day", day));
			break;
		case 9: //tempuse of /time command
			String giftCode7 = addGift(p, "5t:time");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "5 Uses of the /time command, Gift Code: " + giftCode7).replace("%day", day));
			break;
		case 10: //win chat colors
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mlps.colorChat");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "Color Chat allowance").replace("%day", day));
			break;
		case 11: //5 uses of /heal command
			String giftCode9 = addGift(p, "5t:heal");
			p.sendMessage(Prefix.prefix("main") + LanguageHandler.returnStringReady(p, "event.advents.current").replace("%reward", "5 Uses of the /heal command, Gift Code: " + giftCode9).replace("%day", day));
			break;
		}
	}
	
	private String addGift(Player p, String giftkey) {
		String giftCode = RandomStringUtils.randomAlphanumeric(6);
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO redicore_gifts(uuid, giftkey, giftcode) VALUES (?, ?, ?)");
			ps.setString(1, p.getUniqueId().toString());
			ps.setString(2, giftkey);
			ps.setString(3, giftCode);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return giftCode;
	}

	private static int random(int low, int max) {
		Random r = new Random();
		int number = r.nextInt(max);
		while(number < low) {
			number = r.nextInt(max);
		}
		return number;
	}
	
	private static Set<Integer> random1(int low, int max, int size){
		Set<Integer> set = new Random().ints(low, max)
				.distinct()
				.limit(size)
				.boxed()
				.collect(Collectors.toSet());
		return set;
	}
}