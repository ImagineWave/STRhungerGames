package hungerGames.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	private GameManager gameManager;
	private List<GamePlayer> listOfPlayers = new ArrayList<>();
	public PlayerManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	public PlayerManager() {
		
	}
	public void addPlayer(Player p) {
		GamePlayer player = new GamePlayer(p);
		this.listOfPlayers.add(player);
	}
	public void removePlayer(Player p) {
		String playerName = p.getName();
		GamePlayer pToRemove = getGamePlayerByName(playerName);
		this.listOfPlayers.remove(pToRemove);
	}
	
	public void setModerator(Player p, boolean bol) {
		String playerName = p.getName();
		GamePlayer pToMod = getGamePlayerByName(playerName);
		pToMod.setMod(bol);
	}
	public void setSpectate(Player p, boolean bol) {
		String playerName = p.getName();
		GamePlayer pToSpec = getGamePlayerByName(playerName);
		pToSpec.setSpectator(bol);
	}
	public boolean containsPlayer(Player p) {
		String playerName = p.getName();
		GamePlayer containedPlayer = getGamePlayerByName(playerName);
		return listOfPlayers.contains(containedPlayer);
	}
	//ПОСТИМ КРИНЖ
	private GamePlayer getGamePlayerByName(String name) {
		for(GamePlayer player:this.listOfPlayers) {
			if(player.getPlayer().getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
}
