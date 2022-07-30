package at.kitsoft.redicraft.command;

import at.kitsoft.redicraft.api.Prefix;
import at.kitsoft.redicraft.main.LanguageHandler;
import at.kitsoft.redicraft.mysql.lb.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DLStringsFromDBCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if(sender instanceof Player player){
			switch(args.length){
				case 0 -> player.sendMessage(
						Prefix.prefix("main") + LanguageHandler.returnStringReady(player, "usage")
						+ "/stringmanager <downloadEmpty|download|upload> [language]"
				);

				case 1 -> {
					final String arg = args[0].toLowerCase();
					if(arg.equals("downloadempty")){
						if(player.hasPermission("mlps.downloadStrings")){
							File strings = new File("plugins/RCLS/lang/emptystrings.yml");
							try{
								if(!strings.exists()){ strings.createNewFile(); }
								YamlConfiguration cfg = YamlConfiguration.loadConfiguration(strings);
								PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM redicraft_languagestrings");
								ResultSet rs = ps.executeQuery();
								ps.close();
								int i = 0;
								while(rs.next()){
									++i;
									cfg.set(rs.getString("lang_key"), rs.getString("English"));
								}
								player.sendMessage(Prefix.prefix("main") + "§7Downloaded " + i + " Strings as blank (EN).");
								cfg.save(strings);
							}
							catch(Exception e){ e.printStackTrace(); }
						}
						else{ LanguageHandler.noPerm(player); }

					}
					else if(arg.equals("download")){
						player.sendMessage(Prefix.prefix("main") + "Please provide the Language, which you want to download.");
						player.sendMessage("§aExample: §7/stringmanager download German");
					}
					else if(arg.equals("upload")){
						player.sendMessage(Prefix.prefix("main") + "Please provide the Language, which you want to upload.");
						player.sendMessage("§aExample: §7/stringmanager upload Dutch");
					}
				}

				case 2 -> {
					final String arg = args[0].toLowerCase();
					if(arg.equals("upload")){
						if(player.hasPermission("mlps.downloadStrings")){
							String langKey = args[1];
							// load file, for each new row, check if the path.to.string exists, if yes,
							// insert it in the correct column, if not, add to array, skip - print issues
							// afterwards. If a string exists for a language already, skip it, print it
							// afterwards
							File strings = new File("plugins/RCLS/lang/strings_" + langKey + ".yml");
							try{
								if(!strings.exists()){
									strings.createNewFile();
								}
								YamlConfiguration cfg = YamlConfiguration.loadConfiguration(strings);
								int i = 0;
								for (String s : cfg.getKeys(true)) {
									i++;
									PreparedStatement ps = MySQL.getConnection().prepareStatement(
											"UPDATE redicraft_languagestrings SET " + langKey + " = ? WHERE lang_key = ?");
									ps.setString(1, cfg.getString(s));
									ps.setString(2, s);
									ps.executeUpdate();
									ps.close();
								}
								player.sendMessage(Prefix.prefix("main") + "§7Uploaded " + i + " Strings for " + langKey);
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						else{ LanguageHandler.noPerm(player); }
					}
					if(arg.equals("download")){
						if(player.hasPermission("mlps.downloadStrings")){
							String langKey = args[1];
							File strings = new File("plugins/RCLS/lang/strings_" + langKey + ".yml");
							try{
								if(!strings.exists()){ strings.createNewFile(); }
								YamlConfiguration cfg = YamlConfiguration.loadConfiguration(strings);
								PreparedStatement ps = MySQL.getConnection()
										.prepareStatement("SELECT * FROM redicraft_languagestrings");
								ResultSet rs = ps.executeQuery();
								int i = 0;
								while (rs.next()) {
									i++;
									cfg.set(rs.getString("lang_key"), rs.getString(langKey));
								}
								cfg.save(strings);
								player.sendMessage(Prefix.prefix("main") + "§7Downloaded " + i + " Strings from " + langKey);
							}
							catch(Exception e){
								e.printStackTrace();
								player.sendMessage(Prefix.prefix("main") + "§cOh no! An Error appeared! §7Message: "
										+ e.getLocalizedMessage());
							}
						}
						else{ LanguageHandler.noPerm(player); }
					}

				}
			}
		}

		return true;
	}
}