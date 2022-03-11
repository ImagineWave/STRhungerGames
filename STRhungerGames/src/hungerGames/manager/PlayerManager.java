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
	public void addPlayer(Player p) {
		GamePlayer player = new GamePlayer(p);
		this.listOfPlayers.add(player);
	}
	public void remove(Player p) {
		for(GamePlayer player:listOfPlayers) {
			if(player.getPlayer().equals(p)) {
				this.listOfPlayers.remove(player);
			}
		}
	}
	public void setMod(Player p) {
		
	}
	//ПОСТИМ КРИНЖ
	public GamePlayer getGamePlayerByName(String name) {
		for(GamePlayer player:this.listOfPlayers) {
			if(player.getPlayer().getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
}
