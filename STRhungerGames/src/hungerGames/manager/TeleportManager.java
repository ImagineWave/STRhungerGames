package hungerGames.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import hungerGames.main.Main;

public class TeleportManager {
	private Main plugin;

	public TeleportManager(Main plugin) {
		this.plugin = plugin;
	}

	private void addOneLocCounters(String name) {
		File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		Integer count = f.getInt("amount." + name);
		count++;
		f.set("amount." + name, count);
	}

	public Integer getLocCounters(String name) {
		File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		Integer count = f.getInt("amount." + name);
		return count;
	}

	public void locToConfig(Location loc, String name) {
		File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		f.set("locations." + name + ".world", loc.getWorld().getName());
		f.set("locations." + name + ".x", loc.getBlockX());
		f.set("locations." + name + ".y", loc.getBlockY());
		f.set("locations." + name + ".z", loc.getBlockZ());
		try {
			f.save(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void removeLocFromConfig1(String name) {
		File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		f.set("locations." + name + ".world", null);
		f.set("locations." + name + ".x", null);
		f.set("locations." + name + ".y", null);
		f.set("locations." + name + ".z", null);
		try {
			f.save(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void removeLocFromConfig2(String name) {
		File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		f.set("locations." + name, null);
		try {
			f.save(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public Location getLocation (String name) {
		 File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		 FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		 Location loc = new Location(Bukkit.getServer().getWorld(f.getString("locations." + name + ".world")),
				f.getDouble("locations." + name + ".x")+0.5d,
				f.getDouble("locations." + name + ".y"),
				f.getDouble("locations." + name + ".z")+0.5d);
		return loc;
	}
	public void setLobbyLoc(Location loc) {
		locToConfig(loc, "lobby");
	}
	public Location getLobbyLoc() {
		return getLocation("lobby");
	}
	
	public void setSpectateSpawn(Location loc) {
		locToConfig(loc, "spectate");
	}
	public Location getSpectateSpawn() {
		return getLocation("spectate");
	}
	
	public void addStartingLoc(Location loc) {
		Integer count = getLocCounters("starting");
		locToConfig(loc, "starting" + count.toString());
		addOneLocCounters("starting");
	}

	public void addDeathmatchLoc(Location loc) {
		Integer count = getLocCounters("deathmatch");
		locToConfig(loc, "deathmatch" + count.toString());
		addOneLocCounters("deathmatch");
	}

	public List<Location> getListOfLocsByName(String name) {
		Integer count = getLocCounters(name);
		List<Location> list = new ArrayList<>();
		for(int i = 0; i<count; i++) {
			list.add(getLocation(name+count.toString()));
		}
		return list;
	}
	public void clearListOfLocsByName(String name) {
		File file = new File(plugin.getDataFolder() + File.separator + "Locations.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(file);
		Integer count = f.getInt("amount." + name);
		for(int i = 0; i<count; i++) {
			removeLocFromConfig1("deathmatch" + count.toString());
		}
		f.set("amount." + name, null);
	}
}
