package at.mlps.rc.main;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import at.mlps.rc.mysql.lb.MySQL;

public class LanguageHandler {
	
	public static void loadConfig() {
		File file = new File("plugins/RCLS/language.yml");
		if(!file.exists()) { try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); } }
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		//cfg.addDefault("Language..", "&");
		//key = Language.<LangKey>.<cmd|event|misc>.<whatexactly>.state
		
		//english
		cfg.addDefault("Language.EN.noPerm", "&cInsufficent Permissions!");
		cfg.addDefault("Language.EN.notAvailable", "&cThis is currently not available.");
		cfg.addDefault("Language.EN.usage", "&7Usage: ");
		cfg.addDefault("Language.EN.cmd.login.success", "&7You are now logged in.");
		cfg.addDefault("Language.EN.cmd.logout.success", "&7You are now logged out.");
		cfg.addDefault("Language.EN.cmd.login.already", "&7You are already logged in.");
		cfg.addDefault("Language.EN.cmd.logout.already", "&7You are already logged out.");
		cfg.addDefault("Language.EN.cmd.tg.visible", "&7Your group is now visible.");
		cfg.addDefault("Language.EN.cmd.tg.invisible", "&7Your group is now invisible.");
		cfg.addDefault("Language.EN.cmd.build.activated", "&7Buildmode has been activated.");
		cfg.addDefault("Language.EN.cmd.build.deactivated", "&7Buildmode has been deactivated.");
		cfg.addDefault("Language.EN.cmd.afk.join", "&7You are now AFK.");
		cfg.addDefault("Language.EN.cmd.afk.leave", "&7You are not longer AFK.");
		cfg.addDefault("Language.EN.cmd.setspawn.mainspawn", "&7You have set the Lobby-Spawn.");
		cfg.addDefault("Language.EN.cmd.setspawn.dailyreward", "&7You have set the Daily-Reward Spawn.");
		cfg.addDefault("Language.EN.cmd.setid.idalreadyexists", "&7This ID is already in use.");
		cfg.addDefault("Language.EN.cmd.setid.updatesuccessfull", "%displayer &7has now ID &a%id");
		cfg.addDefault("Language.EN.cmd.setid.limitexceeded", "&7The ID should be between &a%minid &7and &a%maxid &7and needs to be &lunique&7.");
		cfg.addDefault("Language.EN.cmd.setid.playernotonline", "%displayer &7is not online");
		cfg.addDefault("Language.EN.cmd.setpf.playernotonline", "%displayer &7is not online");
		cfg.addDefault("Language.EN.cmd.setpf.playerreset", "%displayer &7's prefix has been resetted");
		cfg.addDefault("Language.EN.cmd.setpf.newprefix", "&7Prefix has been updated for %displayer&7. Prefix: &f%prefix");
		cfg.addDefault("Language.EN.cmd.setpf.prefixtoolong", "&7The prefix is only allowed for up to 16 Chars. Used: &c%length");
		cfg.addDefault("Language.EN.cmd.money.player.own", "&7You have &a%money &7Coins");
		cfg.addDefault("Language.EN.cmd.money.bank.own", "&7You have &a%money &7Coins on your bank");
		cfg.addDefault("Language.EN.cmd.money.player.other", "%displayer &7has &a%money &7Coins");
		cfg.addDefault("Language.EN.cmd.money.bank.other", "%displayer &7has &a%money &7Coins on his bank");
		cfg.addDefault("Language.EN.cmd.setmoney.playernotonline", "&7This player is not online");
		cfg.addDefault("Language.EN.cmd.setmoney.belowzero", "&7You can't set the money below 0 Coins");
		cfg.addDefault("Language.EN.cmd.setmoney.successfull", "&7You have set the money from %displayer &7 to &a%money &7Coins successfull.");
		cfg.addDefault("Language.EN.cmd.addmoney.playernotonline", "&7This player is not online");
		cfg.addDefault("Language.EN.cmd.addmoney.successfull", "&7You have added &a%money &7Coins to %displayer &7successfull.");
		cfg.addDefault("Language.EN.cmd.removemoney.playernotonline", "&7This player is not online");
		cfg.addDefault("Language.EN.cmd.removemoney.successfull", "&7You have removed &a%money &7Coins from %displayer");
		cfg.addDefault("Language.EN.cmd.removemoney.belowzero", "&7You can't set the money below 0 Coins.");
		cfg.addDefault("Language.EN.cmd.pay.playernotonline", "&7This player is not online");
		cfg.addDefault("Language.EN.cmd.pay.successfull", "&7You've paid %displayer &7successfully &a%money &7Coins");
		cfg.addDefault("Language.EN.cmd.pay.belowzero", "&7You can't pay more than you have.");
		cfg.addDefault("Language.EN.cmd.setbankmoney.playernotonline", "&7This player is not online.");
		cfg.addDefault("Language.EN.cmd.setbankmoney.belowzero", "&7You can't set the money below 0 Coins");
		cfg.addDefault("Language.EN.cmd.setbankmoney.successfull", "&7You have set the bankmoney from %displayer &7 to &a%money &7Coins successfull.");
		cfg.addDefault("Language.EN.cmd.bankdeposit.moreaspossible", "&7You can't deposit more as you have.");
		cfg.addDefault("Language.EN.cmd.bankdeposit.successfull", "&7You have deposed &a%money &7Coins to your bank.");
		cfg.addDefault("Language.EN.cmd.bankwithdraw.moreaspossible", "&7You can't deposit more as you have.");
		cfg.addDefault("Language.EN.cmd.bankwithdraw.successfull", "&7You have withdrawn &a%money &7Coins from your bank.");
		cfg.addDefault("Language.EN.event.afk.leave", "&7You are not longer AFK.");
		cfg.addDefault("Language.EN.event.build.cantdothat", "&cYou can't do that.");
		cfg.addDefault("Language.EN.event.wheatdestroy.cantdothat", "&cYou can't do that.");
		cfg.addDefault("Language.EN.event.extras.open.speedboost", "&7You have opened the Speedboost-Menu.");
		cfg.addDefault("Language.EN.event.extras.open.jumpboost", "&7You have opened the Jumpboost-Menu.");
		cfg.addDefault("Language.EN.event.extras.open.effects", "&7You have opened the Effects-Menu.");
		cfg.addDefault("Language.EN.event.extras.jumpboost.close", "&7You have closed the Jumpboost-Menu.");
		cfg.addDefault("Language.EN.event.extras.jumpboost.backextras", "&7You switched back to the Extras-Menu.");
		cfg.addDefault("Language.EN.event.extras.jumpboost.default", "&7Jumpboost has been resetted.");
		cfg.addDefault("Language.EN.event.extras.jumpboost.step1", "&7Jumpboost has been set to Step 1");
		cfg.addDefault("Language.EN.event.extras.jumpboost.step2", "&7Jumpboost has been set to Step 2");
		cfg.addDefault("Language.EN.event.extras.jumpboost.step3", "&7Jumpboost has been set to Step 3");
		cfg.addDefault("Language.EN.event.extras.jumpboost.step4", "&7Jumpboost has been set to Step 4");
		cfg.addDefault("Language.EN.event.extras.speedboost.close", "&7You have closed the Speedboost-Menu.");
		cfg.addDefault("Language.EN.event.extras.speedboost.backextras", "&7You have switched back to the Extras-Menu.");
		cfg.addDefault("Language.EN.event.extras.speedboost.default", "&7Speedboost has been resetted.");
		cfg.addDefault("Language.EN.event.extras.speedboost.step1", "&7Speedboost has been set to Step 1");
		cfg.addDefault("Language.EN.event.extras.speedboost.step2", "&7Speedboost has been set to Step 2");
		cfg.addDefault("Language.EN.event.extras.speedboost.step3", "&7Speedboost has been set to Step 3");
		cfg.addDefault("Language.EN.event.extras.speedboost.step4", "&7Speedboost has been set to Step 4");
		cfg.addDefault("Language.EN.event.extras.effects.Heart.selected", "&7Effect selected: &cHearts");
		cfg.addDefault("Language.EN.event.extras.effects.Heart.removed", "&7Effect removed: &cHearts");
		cfg.addDefault("Language.EN.event.extras.effects.Cloud.selected", "&7Effect selected: &fClouds");
		cfg.addDefault("Language.EN.event.extras.effects.Cloud.removed", "&7Effect removed: &fClouds");
		cfg.addDefault("Language.EN.event.extras.effects.Music.selected", "&7Effect selected: &6Musicnotes");
		cfg.addDefault("Language.EN.event.extras.effects.Music.removed", "&7Effect removed: &6Musicnotes");
		cfg.addDefault("Language.EN.event.extras.effects.Slime.selected", "&7Effect selected: &aSlime");
		cfg.addDefault("Language.EN.event.extras.effects.Slime.removed", "&7Effect removed: &aSlime");
		cfg.addDefault("Language.EN.event.extras.effects.Water.selected", "&7Effect selected: &1Waterdrops");
		cfg.addDefault("Language.EN.event.extras.effects.Water.removed", "&7Effect removed: &1Waterdrops");
		cfg.addDefault("Language.EN.event.extras.effects.Ender.selected", "&7Effect selected: &9Enderteleport");
		cfg.addDefault("Language.EN.event.extras.effects.Ender.removed", "&7Effect removed: &9Enderteleport");
		cfg.addDefault("Language.EN.event.extras.effects.Emerald.selected", "&7Effect selected: &aEmerald");
		cfg.addDefault("Language.EN.event.extras.effects.Emerald.removed", "&7Effect removed: &aEmerald");
		cfg.addDefault("Language.EN.event.extras.effects.Lava.selected", "&7Effect selected: &cLavadrops");
		cfg.addDefault("Language.EN.event.extras.effects.Lava.removed", "&7Effect removed: &cLavadrops");
		cfg.addDefault("Language.EN.event.extras.effects.Honey.selected", "&7Effect selected: &6Honeydrops");
		cfg.addDefault("Language.EN.event.extras.effects.Honey.removed", "&7Effect removed: &6Honeydrops");
		cfg.addDefault("Language.EN.event.extras.effects.Redstone.selected", "&7Effect selected: &6Multicolor-Dust");
		cfg.addDefault("Language.EN.event.extras.effects.Redstone.removed", "&7Effect removed: &6Multicolor-Dust");
		cfg.addDefault("Language.EN.event.extras.effects.Snow.selected", "&7Effect selected: &fSnowball");
		cfg.addDefault("Language.EN.event.extras.effects.Snow.removed", "&7Effect removed: &fSnowball");
		cfg.addDefault("Language.EN.event.navigator.sendPlayer.success", "&7You has been sent to server &a%server");
		cfg.addDefault("Language.EN.event.navigator.sendPlayer.locked", "&a%server &7is currently locked.");
		cfg.addDefault("Language.EN.event.navigator.sendPlayer.monitorinfo", "&eInfo: This server is currently monitored. Issues or instability may come up!");
		cfg.addDefault("Language.EN.event.navigator.sendPlayer.offline", "&a%server &cis currently offline.");
		cfg.addDefault("Language.EN.event.navigator.sendPlayer.failed", "&cFailed to send you to Server &a%server");
		cfg.addDefault("Language.EN.event.navigator.local.dailyreward", "&7You has been teleported to the &aDaily Rewards");
		cfg.addDefault("Language.EN.event.navigator.local.spawn", "&7You has been teleported back to the Lobbyspawn");
		cfg.addDefault("Language.EN.event.drop.cancel", "&cYou are unable to drop&6 %item");
		cfg.addDefault("Language.EN.event.move.cancel", "&cYou are unable to move&6 %item");
		cfg.addDefault("Language.EN.event.shopvillager.hurt", "&cYou can't hurt the villager.");
		cfg.addDefault("Language.EN.event.dailyrewards.claimed", "&7You claimed your daily reward.|&7Type: &a%type|&7Money: &a%money &7Coins");
		cfg.addDefault("Language.EN.event.dailyrewards.await", "&7You have to wait &a%time &7hours.");
		cfg.addDefault("Language.EN.restarter.time.200000", "&7The Server will be restarted in &a4 Hours");
		cfg.addDefault("Language.EN.restarter.time.210000", "&7The Server will be restarted in &a3 Hours");
		cfg.addDefault("Language.EN.restarter.time.220000", "&7The Server will be restarted in &a2 Hours");
		cfg.addDefault("Language.EN.restarter.time.223000", "&7The Server will be restarted in &a90 Minutes");
		cfg.addDefault("Language.EN.restarter.time.230000", "&7The Server will be restarted in &a60 Minutes");
		cfg.addDefault("Language.EN.restarter.time.233000", "&7The Server will be restarted in &a30 Minutes");
		cfg.addDefault("Language.EN.restarter.time.234500", "&7The Server will be restarted in &a15 Minutes");
		cfg.addDefault("Language.EN.restarter.time.235500", "&7The Server will be restarted in &a5 Minutes");
		cfg.addDefault("Language.EN.restarter.time.235600", "&7The Server will be restarted in &e4 Minutes");
		cfg.addDefault("Language.EN.restarter.time.235700", "&7The Server will be restarted in &e3 Minutes");
		cfg.addDefault("Language.EN.restarter.time.235800", "&7The Server will be restarted in &c2 Minutes");
		cfg.addDefault("Language.EN.restarter.time.235900", "&7The Server will be restarted in &c1 Minute");
		cfg.addDefault("Language.EN.restarter.time.235950", "&7The Server will be restarted in &410 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235951", "&7The Server will be restarted in &49 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235952", "&7The Server will be restarted in &48 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235953", "&7The Server will be restarted in &47 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235954", "&7The Server will be restarted in &46 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235955", "&7The Server will be restarted in &45 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235956", "&7The Server will be restarted in &44 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235957", "&7The Server will be restarted in &43 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235958", "&7The Server will be restarted in &42 Seconds");
		cfg.addDefault("Language.EN.restarter.time.235959", "&7The Server will be restarted in &41 Seconds");
		cfg.addDefault("Language.EN.scoreboard.sideboard.money", "&7Money:");
		cfg.addDefault("Language.EN.scoreboard.sideboard.rank", "&7Rank:");
		cfg.addDefault("Language.EN.scoreboard.sideboard.playerid", "&7PlayerID:");
		cfg.addDefault("Language.EN.scoreboard.sideboard.buildtime", "&7Buildtime:");
		cfg.addDefault("Language.EN.scoreboard.sideboard.block", "&7Block:");
		cfg.addDefault("Language.EN.scoreboard.playerlist.top", "&aRedi&cCraft|&aYour Minecraft Network");
		cfg.addDefault("Language.EN.scoreboard.playerlist.bottom", "&7Time: &a%time|&7Server: &a%servername &7/ &a%serverid");
		
		//german
		cfg.addDefault("Language.DE.noPerm", "&cUnzureichende Berechtigung!");
		cfg.addDefault("Language.DE.notAvailable", "&cDas ist aktuell nicht verfügbar.");
		cfg.addDefault("Language.DE.usage", "&7Verwende: ");
		cfg.addDefault("Language.DE.cmd.login.success", "&7Du bist nun eingeloggt.");
		cfg.addDefault("Language.DE.cmd.logout.success", "&7Du bist nun ausgeloggt.");
		cfg.addDefault("Language.DE.cmd.login.already", "&7Du bist bereits eingeloggt.");
		cfg.addDefault("Language.DE.cmd.logout.already", "&7Du bist bereits ausgeloggt.");
		cfg.addDefault("Language.DE.cmd.tg.visible", "&7Deine Gruppe ist nun sichtbar.");
		cfg.addDefault("Language.DE.cmd.tg.invisible", "&7Deine Gruppe ist nun nicht sichtbar.");
		cfg.addDefault("Language.DE.cmd.build.activated", "&7Buildmodus wurde aktiviert.");
		cfg.addDefault("Language.DE.cmd.build.deactivated", "&7Buildmodus wurde deaktiviert.");
		cfg.addDefault("Language.DE.cmd.afk.join", "&7Du bist nun AFK.");
		cfg.addDefault("Language.DE.cmd.afk.leave", "&7Du bist nicht mehr AFK.");
		cfg.addDefault("Language.DE.cmd.setspawn.mainspawn", "&7Du hast den Lobbyspawn gesetzt.");
		cfg.addDefault("Language.DE.cmd.setspawn.dailyreward", "&7Du hast den Spawn für die Tägliche Belohnung gesetzt.");
		cfg.addDefault("Language.DE.cmd.setid.idalreadyexists", "&7Diese ID existiert bereits.");
		cfg.addDefault("Language.DE.cmd.setid.updatesuccessfull", "%displayer &7hat nun ID &a%id &7.");
		cfg.addDefault("Language.DE.cmd.setid.limitexceeded", "&7Die ID sollte zwischen &a%minid &7und &a%maxid &7und &leinmalig &7sein.");
		cfg.addDefault("Language.DE.cmd.setid.playernotonline", "%displayer &7ist nicht online.");
		cfg.addDefault("Language.DE.cmd.setpf.playernotonline", "%displayer &7ist nicht online.");
		cfg.addDefault("Language.DE.cmd.setpf.playerreset", "%displayer &7's Prefix wurde zurückgesetzt.");
		cfg.addDefault("Language.DE.cmd.setpf.newprefix", "&7Prefix wurde geupdated.|&7Spieler: %displayer |&7Prefix:&f %prefix");
		cfg.addDefault("Language.DE.cmd.setpf.prefixtoolong", "&7Der Prefix darf nicht länger als &c%length &7/ &a16 &7Zeichen sein.");
		cfg.addDefault("Language.DE.cmd.money.player.own", "&7Du hast &a%money &7Coins");
		cfg.addDefault("Language.DE.cmd.money.bank.own", "&7Du hast &a%money &7Coins auf der Bank");
		cfg.addDefault("Language.DE.cmd.money.player.other", "%displayer &7hat &a%money &7Coins");
		cfg.addDefault("Language.DE.cmd.money.bank.other", "%displayer &7hat &a%money &7Coins auf der Bank");
		cfg.addDefault("Language.DE.cmd.setmoney.playernotonline", "&7Dieser Spieler ist nicht online.");
		cfg.addDefault("Language.DE.cmd.setmoney.belowzero", "&7Du kannst das Geld nicht in das negative setzen.");
		cfg.addDefault("Language.DE.cmd.setmoney.successfull", "&7Du hast %displayer &7auf &a%money &7Coins gesetzt.");
		cfg.addDefault("Language.DE.cmd.addmoney.playernotonline", "&7Dieser Spieler ist nicht online.");
		cfg.addDefault("Language.DE.cmd.addmoney.successfull", "&7Du hast %displayer &7auf &a%money &7Coins gesetzt.");
		cfg.addDefault("Language.DE.cmd.removemoney.playernotonline", "&7Dieser Spieler ist nicht online.");
		cfg.addDefault("Language.DE.cmd.removemoney.successfull", "&7Du hast %displayer &7auf &a%money &7Coins gesetzt.");
		cfg.addDefault("Language.DE.cmd.removemoney.belowzero", "&7Du kannst das Geld nicht in das negative setzen.");
		cfg.addDefault("Language.DE.cmd.pay.playernotonline", "&7Dieser Spieler ist nicht online.");
		cfg.addDefault("Language.DE.cmd.pay.successfull", "&7Du hast %displayer &a%money &7Coins gezahlt.");
		cfg.addDefault("Language.DE.cmd.pay.belowzero", "&7Du kannst nicht mehr bezahlen als du tatsächlich hast.");
		cfg.addDefault("Language.DE.cmd.setbankmoney.playernotonline", "&7Dieser Spieler ist nicht online.");
		cfg.addDefault("Language.DE.cmd.setbankmoney.belowzero", "&7Du kannst das Geld nicht in das negative setzen.");
		cfg.addDefault("Language.DE.cmd.setbankmoney.successfull", "&7Du hast %displayer &7's Bankaccount auf &a%money &7Coins gesetzt.");
		cfg.addDefault("Language.DE.cmd.bankdeposit.moreaspossible", "&7Du kannst nicht mehr auf deine Bank überweisen als du hast.");
		cfg.addDefault("Language.DE.cmd.bankdeposit.successfull", "&7Du hast &a%money &7Coins auf deine Bank überwiesen.");
		cfg.addDefault("Language.DE.cmd.bankwithdraw.moreaspossible", "&7Du kannst nicht mehr von deiner Bank abheben als du hast.");
		cfg.addDefault("Language.DE.cmd.bankwithdraw.successfull", "&7Du hast &a%money &7Coins von deiner Bank abgehoben.");
		cfg.addDefault("Language.DE.event.afk.leave", "&7Du bist nicht mehr AFK.");
		cfg.addDefault("Language.DE.event.build.cantdothat", "&cDu kannst das nicht.");
		cfg.addDefault("Language.DE.event.wheatdestroy.cantdothat", "&cYou can't do that.");
		cfg.addDefault("Language.DE.event.extras.open.speedboost", "&7Du hast das Speedboost-Menü geöffnet.");
		cfg.addDefault("Language.DE.event.extras.open.jumpboost", "&7Du hast das Jumpboost-Menü geöffnet.");
		cfg.addDefault("Language.DE.event.extras.open.effects", "&7Du hast das Effektemenü geöffnet.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.default", "&7Jumpboost wurde zurückgesetzt.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.step1", "&7Jumpbooster wurde auf Stufe 1 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.step2", "&7Jumpbooster wurde auf Stufe 2 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.step3", "&7Jumpbooster wurde auf Stufe 3 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.step4", "&7Jumpbooster wurde auf Stufe 4 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.close", "&7Du hast das Jumpboost-Menü geschlossen.");
		cfg.addDefault("Language.DE.event.extras.jumpboost.backextras", "&7Du bist zum Extras-Menü zurückgekehrt.");
		cfg.addDefault("Language.DE.event.extras.speedboost.default", "&7Speedbooster wurde zurückgesetzt.");
		cfg.addDefault("Language.DE.event.extras.speedboost.step1", "&7Speedbooster wurde auf Stufe 1 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.speedboost.step2", "&7Speedbooster wurde auf Stufe 2 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.speedboost.step3", "&7Speedbooster wurde auf Stufe 3 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.speedboost.step4", "&7Speedbooster wurde auf Stufe 4 gesetzt.");
		cfg.addDefault("Language.DE.event.extras.speedboost.close", "&7Du hast das Speedboost-Menü geschlossen.");
		cfg.addDefault("Language.DE.event.extras.speedboost.backextras", "&7Du bist zum Extras-Menü zurückgekehrt.");
		cfg.addDefault("Language.DE.event.extras.effects.Heart.selected", "&7Effekt ausgewählt: &cHearts");
		cfg.addDefault("Language.DE.event.extras.effects.Heart.removed", "&7Effekt entfernt: &cHearts");
		cfg.addDefault("Language.DE.event.extras.effects.Cloud.selected", "&7Effekt ausgewählt: &fClouds");
		cfg.addDefault("Language.DE.event.extras.effects.Cloud.removed", "&7Effekt entfernt: &fClouds");
		cfg.addDefault("Language.DE.event.extras.effects.Music.selected", "&7Effekt ausgewählt: &6Musicnotes");
		cfg.addDefault("Language.DE.event.extras.effects.Music.removed", "&7Effekt entfernt: &6Musicnotes");
		cfg.addDefault("Language.DE.event.extras.effects.Slime.selected", "&7Effekt ausgewählt: &aSlime");
		cfg.addDefault("Language.DE.event.extras.effects.Slime.removed", "&7Effekt entfernt: &aSlime");
		cfg.addDefault("Language.DE.event.extras.effects.Water.selected", "&7Effekt ausgewählt: &1Waterdrops");
		cfg.addDefault("Language.DE.event.extras.effects.Water.removed", "&7Effekt entfernt: &1Waterdrops");
		cfg.addDefault("Language.DE.event.extras.effects.Ender.selected", "&7Effekt ausgewählt: &9Enderteleport");
		cfg.addDefault("Language.DE.event.extras.effects.Ender.removed", "&7Effekt entfernt: &9Enderteleport");
		cfg.addDefault("Language.DE.event.extras.effects.Emerald.selected", "&7Effekt ausgewählt: &aEmerald");
		cfg.addDefault("Language.DE.event.extras.effects.Emerald.removed", "&7Effekt entfernt: &aEmerald");
		cfg.addDefault("Language.DE.event.extras.effects.Lava.selected", "&7Effekt ausgewählt: &cLavadrops");
		cfg.addDefault("Language.DE.event.extras.effects.Lava.removed", "&7Effekt entfernt: &cLavadrops");
		cfg.addDefault("Language.DE.event.extras.effects.Honey.selected", "&7Effekt ausgewählt: &6Honeydrops");
		cfg.addDefault("Language.DE.event.extras.effects.Honey.removed", "&7Effekt entfernt: &6Honeydrops");
		cfg.addDefault("Language.DE.event.extras.effects.Redstone.selected", "&7Effekt ausgewählt: &6Multicolor-Staub");
		cfg.addDefault("Language.DE.event.extras.effects.Redstone.removed", "&7Effekt entfernt: &6Multicolor-Staub");
		cfg.addDefault("Language.DE.event.extras.effects.Snow.selected", "&7Effekt ausgewählt: &fSnowball");
		cfg.addDefault("Language.DE.event.extras.effects.Snow.removed", "&7Effekt entfernt: &fSnowball");
		cfg.addDefault("Language.DE.event.navigator.sendPlayer.success", "&7Du wurdest zum Server &a%server &7gesendet.");
		cfg.addDefault("Language.DE.event.navigator.sendPlayer.locked", "&a%server &7ist zur zeit gesperrt.");
		cfg.addDefault("Language.DE.event.navigator.sendPlayer.monitorinfo", "&eInfo: Dieser Server wird derzeit beobachtet. Probleme oder instabilitäten könnten auftreten.");
		cfg.addDefault("Language.DE.event.navigator.sendPlayer.offline", "&a%server &7ist zur zeit offline.");
		cfg.addDefault("Language.DE.event.navigator.sendPlayer.failed", "&cEin fehler ist aufgetreten als du gesendet werden solltest.");
		cfg.addDefault("Language.DE.event.navigator.local.dailyreward", "&7Du wurdest zu den &aDaily Rewards &7teleportiert");
		cfg.addDefault("Language.DE.event.navigator.local.spawn", "&7Du wurdest zum Lobbyspawn zurückteleportiert.");
		cfg.addDefault("Language.DE.event.drop.cancel", "&cDu kannst das Item &6%item &cnicht droppen.");
		cfg.addDefault("Language.DE.event.move.cancel", "&cDu kannst das Item &6%item &cnicht verschieben.");
		cfg.addDefault("Language.DE.event.shopvillager.hurt", "&cDu kannst dem Villager nicht wehtun.");
		cfg.addDefault("Language.DE.event.dailyrewards.claimed", "&7Du hast deine Tägliche Belohnung abgeholt.|&7Typ: &a%type|&7Geld: &a%money &7Coins");
		cfg.addDefault("Language.DE.event.dailyrewards.await", "&7Du musst noch &a%time &7Stunden warten.");
		cfg.addDefault("Language.DE.restarter.time.200000", "&7Der Server wird neugestartet in &a4 Stunden");
		cfg.addDefault("Language.DE.restarter.time.210000", "&7Der Server wird neugestartet in &a3 Stunden");
		cfg.addDefault("Language.DE.restarter.time.220000", "&7Der Server wird neugestartet in &a2 Stunden");
		cfg.addDefault("Language.DE.restarter.time.223000", "&7Der Server wird neugestartet in &a90 Minuten");
		cfg.addDefault("Language.DE.restarter.time.230000", "&7Der Server wird neugestartet in &a60 Minuten");
		cfg.addDefault("Language.DE.restarter.time.233000", "&7Der Server wird neugestartet in &a30 Minuten");
		cfg.addDefault("Language.DE.restarter.time.234500", "&7Der Server wird neugestartet in &a15 Minuten");
		cfg.addDefault("Language.DE.restarter.time.235500", "&7Der Server wird neugestartet in &a5 Minuten");
		cfg.addDefault("Language.DE.restarter.time.235600", "&7Der Server wird neugestartet in &e4 Minuten");
		cfg.addDefault("Language.DE.restarter.time.235700", "&7Der Server wird neugestartet in &e3 Minuten");
		cfg.addDefault("Language.DE.restarter.time.235800", "&7Der Server wird neugestartet in &c2 Minuten");
		cfg.addDefault("Language.DE.restarter.time.235900", "&7Der Server wird neugestartet in &c1 Minute");
		cfg.addDefault("Language.DE.restarter.time.235950", "&7Der Server wird neugestartet in &410 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235951", "&7Der Server wird neugestartet in &49 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235952", "&7Der Server wird neugestartet in &48 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235953", "&7Der Server wird neugestartet in &47 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235954", "&7Der Server wird neugestartet in &46 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235955", "&7Der Server wird neugestartet in &45 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235956", "&7Der Server wird neugestartet in &44 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235957", "&7Der Server wird neugestartet in &43 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235958", "&7Der Server wird neugestartet in &42 Sekunden");
		cfg.addDefault("Language.DE.restarter.time.235959", "&7Der Server wird neugestartet in &41 Sekunde");
		cfg.addDefault("Language.DE.scoreboard.sideboard.money", "&7Geld:");
		cfg.addDefault("Language.DE.scoreboard.sideboard.rank", "&7Rang:");
		cfg.addDefault("Language.DE.scoreboard.sideboard.playerid", "&7SpielerID:");
		cfg.addDefault("Language.DE.scoreboard.sideboard.buildtime", "&7Bauzeit:");
		cfg.addDefault("Language.DE.scoreboard.sideboard.block", "&7Block:");
		cfg.addDefault("Language.DE.scoreboard.playerlist.top", "&aRedi&cCraft|&aDein Minecraft-Netzwerk");
		cfg.addDefault("Language.DE.scoreboard.playerlist.bottom", "&7Zeit: &a%time|&7Server: &a%servername &7/ &a%serverid");
		
		cfg.options().copyDefaults(true);
		try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void noPerm(Player p) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(Main.prefix() + retString("en-uk", "noPerm"));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(Main.prefix() + retString("de-de", "noPerm"));
		}
	}
	
	public static void notAvailable(Player p) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(Main.prefix() + retString("en-uk", "notAvailable"));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(Main.prefix() + retString("de-de", "notAvailable"));
		}
	}
	
	public static void sendMSGReady(Player p, String path) {
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			p.sendMessage(Main.prefix() + retString("en-uk", path));
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			p.sendMessage(Main.prefix() + retString("de-de", path));
		}
	}
	
	public static String returnStringReady(Player p, String path) {
		String s = "";
		if(retLang(p).equalsIgnoreCase("en-uk")) {
			s = retString("en-uk", path);
		}else if(retLang(p).equalsIgnoreCase("de-de")) {
			s = retString("de-de", path);
		}
		return s;
	}
	
	private static String retLang(Player p) {
		String langKey = "en-UK";
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_userstats WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString().replace("-", ""));
			ResultSet rs = ps.executeQuery();
			rs.next();
			langKey = rs.getString("language");
		}catch (SQLException e) { e.printStackTrace(); return null; }
		return langKey;
	}

	private static String retString(String lang, String path) {
		File file = new File("plugins/RCLS/language.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		String string = "";
		if(lang.equalsIgnoreCase("en-uk")) {
			if(cfg.contains("Language.EN." + path)) {
				string = cfg.getString("Language.EN." + path).replace("&", "§");
			}else {
				string = "§cThis path doesn't exists.";
			}
		}else if(lang.equalsIgnoreCase("de-de")) {
			if(cfg.contains("Language.DE." + path)) {
				string = cfg.getString("Language.DE." + path).replace("&", "§");
			}else {
				string = "§cDieser Pfad existiert nicht.";
			}
		}
		return string;
	}
}