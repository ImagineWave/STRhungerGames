package hungerGames.main;

import org.bukkit.plugin.java.JavaPlugin;

import hungerGames.listeners.BlockBreakListener;
import hungerGames.manager.GameManager;

public class Main extends JavaPlugin{
	
	private GameManager gameManager;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager), this);
	}
	@Override
	public void onDisable() {
		
	}

}
