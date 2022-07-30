package at.kitsoft.redicraft.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BukkitInfo{
	
	private File file;
	
	public BukkitInfo(){
		file = new File("server.properties");
	}
	
	public String getServerId(){
		Properties p = new Properties();
		try{
			p.load(new FileInputStream(file));
		}
		catch(Exception ex){
			return "null";
		}
		return p.getProperty("server-id");
	}

}