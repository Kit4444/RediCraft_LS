package at.kitsoft.redicraft.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GetBukkitInfo {
	
	public String getServerName() {
		File file = new File("server.properties");
		Properties p = new Properties();
		String s = "";
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
			p.load(bis);
		}catch (Exception ex) {
			s = "null";
		}
		s = p.getProperty("server-name");
		return s;
	}
	
	public String getServerId() {
		File file = new File("server.properties");
		Properties p = new Properties();
		String s = "";
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
			p.load(bis);
		}catch (Exception ex) {
			s = "null";
		}
		s = p.getProperty("server-id");
		return s;
	}

}