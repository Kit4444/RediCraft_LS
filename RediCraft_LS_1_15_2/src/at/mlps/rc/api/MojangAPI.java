package at.mlps.rc.api;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class MojangAPI {
	
	public String getUUIDfromName(String name) {
		String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
		String uuid = "";
		try{
			String UUIDJson = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
			if(UUIDJson.isEmpty()) return "ERRORED";
			JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
			uuid =  UUIDObject.get("id").toString();
		}catch(IOException|ParseException e) {
			uuid = "ERRORED";
		}
		return uuid;
	}
	
	public String getNamefromUUID(String uuid) {
		String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
		String name = "";
		try{
			String nameJson = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
			JSONArray nameVal = (JSONArray) JSONValue.parseWithException(nameJson);
			String playerSlot = nameVal.get(nameVal.size()-1).toString();
			JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			name =  UUIDObject.get("name").toString();
		}catch(IOException|ParseException e) {
			name = "ERRORED";
		}
		return name;
	}

}