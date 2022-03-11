package hungerGames.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GamePlayer {
	private Player player;
	public GamePlayer(Player player) {
		this.player = player;
		this.points = 0;
		this.isSpectator = false;
		this.curentVote = 0;
		this.isMod = false;
		this.nonGameLoc = player.getLocation();
		this.nonGameInv = player.getInventory();
	}
	private Integer points;
	private boolean isSpectator;
	private Integer curentVote;
	private boolean isMod;
	private Location nonGameLoc;
	private Inventory nonGameInv;
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public boolean isSpectator() {
		return isSpectator;
	}
	public void setSpectator(boolean isSpectator) {
		this.isSpectator = isSpectator;
	}
	public Integer getCurentVote() {
		return curentVote;
	}
	public void setCurentVote(Integer curentVote) {
		this.curentVote = curentVote;
	}
	public boolean isMod() {
		return isMod;
	}
	public void setMod(boolean isMod) {
		this.isMod = isMod;
	}
	public Location getNonGameLoc() {
		return nonGameLoc;
	}
	public void setNonGameLoc(Location nonGameLoc) {
		this.nonGameLoc = nonGameLoc;
	}
	public Inventory getNonGameInv() {
		return nonGameInv;
	}
	public void setNonGameInv(Inventory nonGameInv) {
		this.nonGameInv = nonGameInv;
	}
}
