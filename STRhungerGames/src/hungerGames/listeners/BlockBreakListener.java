package hungerGames.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import hungerGames.manager.GameManager;
import hungerGames.manager.PlayerManager;

public class BlockBreakListener implements Listener {
	private GameManager gameManager;
	public BlockBreakListener(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	private Material[] allowedBlocks = new Material[] {
			Material.AZALEA_LEAVES,
			Material.ACACIA_LEAVES,
			Material.BIRCH_LEAVES,
			Material.OAK_LEAVES,
			Material.SPRUCE_LEAVES,
			Material.JUNGLE_LEAVES,
			Material.DARK_OAK_LEAVES,
			Material.FLOWERING_AZALEA_LEAVES};
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		Player p = e.getPlayer();
		gameManager.getPlayerManager().containsPlayer(p);
	}
}
