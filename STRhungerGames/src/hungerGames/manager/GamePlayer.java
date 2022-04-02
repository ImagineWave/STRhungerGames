package hungerGames.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GamePlayer {
	public GamePlayer(Player player) {
		this.player = player;
		this.points = 0;
		this.isSpectator = false;
		this.curentVote = 0;
		this.isMod = false;
		this.nonGameLoc = player.getLocation();
		this.items = player.getInventory().getContents();
		this.armor = player.getInventory().getArmorContents();
		this.leftHand =  player.getInventory().getItemInOffHand();
		this.exp = player.getLevel();
	}
	
	private Player player;
	private Integer points = 0;
	private boolean isSpectator = false;
	private Integer curentVote = 0;
	private boolean isMod = false;
	private Location nonGameLoc;
	private ItemStack[] items;
	private ItemStack[] armor;
	private ItemStack leftHand;
	private Integer exp;
	
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
	public ItemStack[] getItems() {
		return items;
	}
	public void setItems(ItemStack[] items) {
		this.items = items;
	}
	public ItemStack[] getArmor() {
		return armor;
	}
	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}
	public ItemStack getLeftHand() {
		return leftHand;
	}
	public void setLeftHand(ItemStack leftHand) {
		this.leftHand = leftHand;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
}
