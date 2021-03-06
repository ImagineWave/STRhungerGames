package hungerGames.manager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hungerGames.listeners.BlockBreakListener;
import hungerGames.main.Main;
import net.md_5.bungee.api.ChatColor;

public class GameManager {
	private final Main plugin;
	private GameState gameState = GameState.LOBBY;
	private final PlayerManager playerManager;
	private final BlockBreakListener blockBreakListener;
	private final TeleportManager teleportManager;
	private int time;
	private int activeTimeSec;
	private int timeBeforeGlowing;
	private int deathmatchTimeSec;
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	public BlockBreakListener getBlockBreakListener() {
		return blockBreakListener;
	}
	public TeleportManager getTeleportManager() {
		return teleportManager;
	}
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getActiveTimeSec() {
		return activeTimeSec;
	}
	public void setActiveTimeSec(int activeTimeSec) {
		this.activeTimeSec = activeTimeSec;
	}
	public int getTimeBeforeGlowing() {
		return timeBeforeGlowing;
	}
	public void setTimeBeforeGlowing(int timeBeforeGlowing) {
		this.timeBeforeGlowing = timeBeforeGlowing;
	}
	public int getDeathmatchTimeSec() {
		return deathmatchTimeSec;
	}
	public void setDeathmatchTimeSec(int deathmatchTimeSec) {
		this.deathmatchTimeSec = deathmatchTimeSec;
	}
	
	
	public GameManager(Main plugin) {
		this.plugin = plugin;
		this.blockBreakListener = new BlockBreakListener(this);
		this.playerManager = new PlayerManager(this);
		this.teleportManager = new TeleportManager(plugin);
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
				break;
			}
			case ACTIVE:{
				//start countdown task and scoreboard
				//progressing game
				break;
			}
			case PAUSED:{
				//freeze all players
				//stop countdown task and scoreboard
				break;
			}
			case DEATHMATCH:{
				//TELEPORT players to list of locs
				//TELEPORT spectators to spect loc
				//start countdown task
				break;
			}
			case ENDING:{
				//change gamemode
				//clear inventories
				//teleport players to saved locs
				//give saved inventories to all players
				//clear players list
				//set gamestate to lobby
				break;
			}
		}
	}
	public boolean canChangeGameSate(GameState gamestate, CommandSender sender) {
		switch(gamestate) {
		case LOBBY:{
			sender.sendMessage(ChatColor.DARK_RED+"???????????? ?????????????? ???????????????? ???????????? ???? LOBBY. ?????? ?????????? ?????????????????? ?????????????? ????????");
			return false;
		}
		case STARTING:{
			if(this.gameState.equals(GameState.LOBBY)) {
				return true;
			}
			break;
		}
		case ACTIVE:{
			if(this.gameState.equals(GameState.PAUSED)) {
				return true;
			}
			break;
		}
		case PAUSED:{
			if(this.gameState.equals(GameState.ACTIVE)) {
				return true;
			}
			break;
		}
		case DEATHMATCH:{
			if(this.gameState.equals(GameState.ACTIVE)) {
				return true;
			}
			break;
		}
		case ENDING:{
			if(this.gameState.equals(GameState.LOBBY)) {
				sender.sendMessage(ChatColor.DARK_RED+"???????? ?????? ???? ????????????");
				return false;
			}
			break;
		}
		default :{
			sender.sendMessage(ChatColor.DARK_RED+"???????????? ?????????? ?????????????? ???????????? ?? "+ChatColor.GOLD+" "+this.gameState.toString()+ChatColor.DARK_RED+" ???? "+ChatColor.GOLD+gamestate.toString());
			return false;
		}
		}
		return false;
	}
}
