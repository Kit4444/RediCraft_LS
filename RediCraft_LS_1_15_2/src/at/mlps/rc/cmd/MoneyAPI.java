package at.mlps.rc.cmd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.mlps.rc.api.MojangAPI;
import at.mlps.rc.main.LanguageHandler;
import at.mlps.rc.main.Main;
import at.mlps.rc.mysql.lb.MySQL;

public class MoneyAPI implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Main.consolesend);
		}else {
			Player p = (Player)sender;
			String uuid = p.getUniqueId().toString();
			if(cmd.getName().equalsIgnoreCase("money")) {
				if(args.length == 0) {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.money.player.own").replace("%money", String.valueOf(getMoney(uuid))));
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.money.bank.own").replace("%money", String.valueOf(getBankMoney(uuid))));
				}else if(args.length == 1) {
					String uuid2 = Bukkit.getPlayerExact(args[0]).getUniqueId().toString();
					Player p2 = Bukkit.getPlayerExact(args[0]);
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.money.player.other").replace("%money", String.valueOf(getMoney(uuid2)).replace("%displayer", p2.getDisplayName())));
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.money.bank.other").replace("%money", String.valueOf(getBankMoney(uuid2)).replace("%displayer", p2.getDisplayName())));
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/money [Player]");
				}
			}else if(cmd.getName().equalsIgnoreCase("setmoney")) {
				if(!p.hasPermission("mlps.setmoney")) {
					LanguageHandler.noPerm(p);
				}else {
					if(args.length == 0) {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/setmoney <Player> <Money>");
					}else if(args.length >= 1 && args.length <= 2) {
						Player p2 = Bukkit.getPlayerExact(args[0]);
						if(p2 == null) {
							LanguageHandler.sendMSGReady(p, "cmd.setmoney.playernotonline");
						}else {
							String uuid2 = p2.getUniqueId().toString();
							int money = Integer.parseInt(args[1]);
							if(money < 0) {
								LanguageHandler.sendMSGReady(p, "cmd.setmoney.belowzero");
							}else {
								setMoney(uuid2, money);
								p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.setmoney.successfull").replace("%money", args[1]).replace("%displayer", p2.getDisplayName()));
								//LanguageHandler.sendMSGReady(p, "cmd.setmoney.successfull");
							}
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/setmoney <Player> <Money>");
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("addmoney")) {
				if(!p.hasPermission("mlps.addmoney")) {
					LanguageHandler.noPerm(p);
				}else {
					if(args.length >= 1 && args.length <= 2) {
						Player p2 = Bukkit.getPlayerExact(args[0]);
						if(p2 == null) {
							LanguageHandler.sendMSGReady(p, "cmd.addmoney.playernotonline");
						}else {
							String uuid2 = p2.getUniqueId().toString();
							int money = Integer.parseInt(args[1]);
							int oldmoney = getMoney(uuid2);
							setMoney(uuid2, (money + oldmoney));
							p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.addmoney.successfull").replace("%money", String.valueOf(money)).replace("%displayer", p2.getDisplayName()));
							//LanguageHandler.sendMSGReady(p, "cmd.addmoney.successfull");
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/addmoney <Player> <Money>");
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("removemoney")) {
				if(!p.hasPermission("mlps.removemoney")) {
					LanguageHandler.noPerm(p);
				}else {
					if(args.length >= 1 && args.length <= 2) {
						Player p2 = Bukkit.getPlayerExact(args[0]);
						if(p2 == null) {
							LanguageHandler.sendMSGReady(p, "cmd.removemoney.playernotonline");
						}else {
							String uuid2 = p2.getUniqueId().toString();
							int money = Integer.parseInt(args[1]);
							int oldmoney = getMoney(uuid2);
							if(money <= oldmoney) {
								setMoney(uuid2, (oldmoney - money));
								p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.removemoney.successfull").replace("%money", String.valueOf(money)).replace("%displayer", p2.getDisplayName()));
								//LanguageHandler.sendMSGReady(p, "cmd.removemoney.successfull");
							}else {
								LanguageHandler.sendMSGReady(p, "cmd.removemoney.belowzero");
							}
						}
					}else {
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/removemoney <Player> <Money>");
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("pay")) {
				if(args.length >= 1 && args.length <= 2) {
					Player p2 = Bukkit.getPlayerExact(args[0]);
					if(p2 == null) {
						LanguageHandler.sendMSGReady(p, "cmd.pay.playernotonline");
					}else {
						String uuid2 = p2.getUniqueId().toString();
						int money = Integer.parseInt(args[1]);
						int oldmoney2 = getMoney(uuid2);
						int oldmoney1 = getMoney(uuid);
						if(money <= oldmoney1) {
							setMoney(uuid2, (oldmoney2 + money));
							setMoney(uuid, (oldmoney1 - money));
							p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.pay.successfull").replace("%displayer", p2.getDisplayName()).replace("%money", args[1]));
							//LanguageHandler.sendMSGReady(p, "cmd.pay.successfull");
						}else {
							LanguageHandler.sendMSGReady(p, "cmd.pay.belowzero");
						}
					}
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/pay <Player> <Money>");
				}
			}else if(cmd.getName().equalsIgnoreCase("topmoney")) {
				try {
					PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_money ORDER BY money DESC LIMIT 10");
					ResultSet rs = ps.executeQuery();
					int i = 0;
					while(rs.next()) {
						i++;
						p.sendMessage("�7Place �a" + i + " �7| User: �a" + MojangAPI.getNamefromUUID(rs.getString("uuid_ut")) + " �7| Balance: �a" + rs.getInt("money") + " �7Coins");
					}
				}catch (SQLException e) {
				}
			}else if(cmd.getName().equalsIgnoreCase("setbankmoney")) {
				if(args.length >= 1 && args.length <= 2) {
					Player p2 = Bukkit.getPlayerExact(args[0]);
					if(p2 == null) {
						LanguageHandler.sendMSGReady(p, "cmd.setbankmoney.playernotonline");
					}else {
						String uuid2 = p2.getUniqueId().toString();
						int money = Integer.parseInt(args[1]);
						if(money <= 0) {
							LanguageHandler.sendMSGReady(p, "cmd.setbankmoney.belowzero");
						}else {
							setBankMoney(uuid2, money);
							p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.setbankmoney.successfull").replace("%money", args[1]).replace("%displayer", p2.getDisplayName()));
						}
					}
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/setbankmoney <Player> <Money>");
				}
			}else if(cmd.getName().equalsIgnoreCase("bankdeposit")) { //add to bankaccount
				if(args.length == 1) {
					int money = Integer.parseInt(args[0]);
					int currMoney = getMoney(p.getUniqueId().toString());
					int bankcurrMoney = getBankMoney(p.getUniqueId().toString());
					if(money <= currMoney) {
						setBankMoney(p.getUniqueId().toString(), (bankcurrMoney + money));
						setMoney(p.getUniqueId().toString(), (currMoney - money));
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.bankdeposit.successfull").replace("%money", args[0]));
					}else {
						LanguageHandler.sendMSGReady(p, "cmd.bankdeposit.moreaspossible");
					}
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/bankdeposit <Money>");
				}
			}else if(cmd.getName().equalsIgnoreCase("bankwithdraw")) { //remove from bankaccount
				if(args.length == 1) {
					int money = Integer.parseInt(args[0]);
					int currMoney = getBankMoney(p.getUniqueId().toString());
					int usercurrMoney = getMoney(p.getUniqueId().toString());
					if(money <= currMoney) {
						setBankMoney(p.getUniqueId().toString(), (currMoney - money));
						setMoney(p.getUniqueId().toString(), (usercurrMoney + money));
						p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "cmd.bankwithdraw.successfull").replace("%money", args[0]));
					}else {
						LanguageHandler.sendMSGReady(p, "cmd.bankwithdraw.moreaspossible");
					}
				}else {
					p.sendMessage(Main.prefix() + LanguageHandler.returnStringReady(p, "usage") + " �7/bankwithdraw <Money>");
				}
			}
		}
		return false;
	}
	
	public static int getBankMoney(String uuid) {
		int i = 0;
		try {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("uuid_ut", uuid);
			if(Main.mysql.isInDatabase("redicore_money", hm)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_money WHERE uuid_ut = ?");
				ps.setString(1, uuid);
				ResultSet rs = ps.executeQuery();
				rs.next();
				i = rs.getInt("bankmoney");
				rs.close();
				ps.closeOnCompletion();
			}else {
				i = 0;
			}
		}catch (SQLException e) {
		}
		return i;
	}
	
	public static int getMoney(String uuid) {
		//return AccountData.getAccountBalance(uuid, "default");
		int i = 0;
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicore_money WHERE uuid_ut = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			i = rs.getInt("money");
		}catch (SQLException e) {}
		return i;
	}
	
	public static void addMoney(String uuid, int moneytoadd) {
		int currentMoney = getMoney(uuid);
		setMoney(uuid, (currentMoney + moneytoadd));
	}
	
	public static void setBankMoney(String uuid, int money) {
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_money SET bankmoney = ? WHERE uuid_ut = ?");
			ps.setInt(1, money);
			ps.setString(2, uuid);
			ps.executeUpdate();
			ps.closeOnCompletion();
		}catch (SQLException e) {
		}
	}
	
	public static void setMoney(String uuid, int money) {
		//AccountData.setAccountBalance(uuid, "default", money);
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE redicore_money SET money = ? WHERE uuid_ut = ?");
			ps.setInt(1, money);
			ps.setString(2, uuid);
			ps.executeUpdate();
			ps.closeOnCompletion();
		}catch (SQLException e) {
		}
	}
	
	public static boolean hasAccount(String uuid) {
		boolean boo = false;
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("uuid_ut", uuid);
		try {
			if(Main.mysql.isInDatabase("redicore_money", hm)) {
				boo = true;
			}else {
				boo = false;
			}
		}catch (SQLException e) {
			boo = false;
		}
		return boo;
	}
	
	public static boolean hasenoughmoney(int usermoney, int needmoney) {
		boolean boo = false;
		if(needmoney <= usermoney) {
			boo = true;
		}else {
			boo = false;
		}
		return boo;
	}
	
	public static boolean createAccount(String uuid_ut, String uuid_t) {
		boolean boo = false;
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("uuid_ut", uuid_ut);
		try {
			if(!Main.mysql.isInDatabase("redicore_money", hm)) {
				hm.put("money", 1000);
				hm.put("bankmoney", 500);
				Main.mysql.insertInto("redicore_money", hm);
				boo = true;
			}
		} catch (SQLException e) {
			boo = false;
			e.printStackTrace();
		}
		return boo;
	}
}