package hungerGames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hungerGames.manager.GameManager;
import net.md_5.bungee.api.ChatColor;

public class ControlCommand implements CommandExecutor {
	
	private GameManager gameManager;
	
	public ControlCommand (GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		return true;
	}

	private void typeSelector(CommandSender sender, String[] args) {
		switch (args[0]) {
		case ("player"): {

			break;
		}
		case ("gamestate"): {

			break;
		}
		case ("settings"): {

			break;
		}
		default: {

		}
		}
	}

	private void playerSubcommands(CommandSender sender, String[] args) {
		Player t = (Bukkit.getPlayerExact(args[2]));
		switch (args[1]) {
		case ("add"): {
			gameManager.getPlayerManager().addPlayer(t);
			sender.sendMessage(ChatColor.GREEN+"Игрок "+t.getName()+" добавлен в игру");
			break;
		}
		case ("remove"): {
			gameManager.getPlayerManager().removePlayer(t);
			sender.sendMessage(ChatColor.GREEN+"Игрок "+t.getName()+" удален из игры");
			break;
		}
		case ("setmod"): {
			if(args[3].equalsIgnoreCase("true")){
				gameManager.getPlayerManager().setModerator(t, true);
				sender.sendMessage(ChatColor.GREEN+"Игрок "+t.getName()+" теперь модератор мини-игры");
				return;
			}
			if(args[3].equalsIgnoreCase("false")){
				gameManager.getPlayerManager().setModerator(t, false);
				sender.sendMessage(ChatColor.GREEN+"Игрок "+t.getName()+" больше не модератор мини-игры");
				return;
			}
			break;
		}
		case ("setspec"): {
			if(args[3].equalsIgnoreCase("true")){
				gameManager.getPlayerManager().setSpectate(t, true);
				sender.sendMessage(ChatColor.GREEN+"Игрок "+t.getName()+" теперь зритель");
				return;
			}
			if(args[3].equalsIgnoreCase("false")){
				gameManager.getPlayerManager().setSpectate(t, false);
				sender.sendMessage(ChatColor.GREEN+"Игрок "+t.getName()+" больше не зритель");
				return;
			}
			break;
		}
		default: {

		}
		}
	}
}