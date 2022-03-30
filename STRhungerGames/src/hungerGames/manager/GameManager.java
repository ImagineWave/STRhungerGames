package hungerGames.manager;

import hungerGames.listeners.BlockBreakListener;
import hungerGames.main.Main;

public class GameManager {
	private final Main plugin;
	private GameState gameState = GameState.LOBBY;
	private final PlayerManager playerManager;
	private final BlockBreakListener blockBreakListener;
	private long time;
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public BlockBreakListener getBlockBreakListener() {
		return blockBreakListener;
	}

	public long getTime() {
		return time;
	}
	
	public GameManager(Main plugin) {
		this.plugin = plugin;
		this.blockBreakListener = new BlockBreakListener(this);
		this.playerManager = new PlayerManager(this);
	}

	public void setGameState(GameState gamestate) {
		switch(gamestate) {
			case STARTING:{
				//save inventories all players
				//clear inventories
				//change gamemode
				//give control instruments to admins
				//teleport players
				//start countdown task
			}
			case ACTIVE:{
				//start countdown task and scoreboard
				//progressing game
			}
			case PREDM:{
				//remove walls
				//give vote tools for all
				//give vote control instruments to admins
				//FOR EACH PLTOT:
					//Teleport players to PLOT home
					//Save EACH player vote
			}
			case ENDING:{
				//change gamemode
				//teleport players to spawnpoint
				//clear inventories
				//give saved inventories to all players
				
			}
		}
	}
}
