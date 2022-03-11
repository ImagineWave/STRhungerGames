package hungerGames.manager;

import hungerGames.main.Main;

public class GameManager {
	private Main plugin;
	private GameState gameState = GameState.LOBBY;
	private PlayerManager playerManager;
	private long time;
	
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
			case VOTING:{
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
