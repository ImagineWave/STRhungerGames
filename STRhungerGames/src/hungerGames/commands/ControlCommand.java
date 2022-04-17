package hungerGames.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import hungerGames.manager.GameManager;
import hungerGames.manager.GameState;
import net.md_5.bungee.api.ChatColor;

public class ControlCommand implements CommandExecutor {
	
	private GameManager gameManager;
	public ControlCommand (GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("str.hungergames.control")) {
			sender.sendMessage(ChatColor.RED+"У вас нет прав");
			return true;
		}
		typeSelector(sender, args);
		return true;
	}

	private void typeSelector(CommandSender sender, String[] args) {
		switch (args[0]) {
		case ("player"): {
			playerSubcommands(sender, args);
			break;
		}
		case ("gamestate"): {
			gamestateSubcommand(sender,args);
			break;
		}
		case ("settings"): {
			settingsSubcommand(sender,args);
			break;
		}
		default: {
			sender.sendMessage(ChatColor.RED+"Использование /hgc player/gamestate/settings");
			break;
		}
		}
	}

	private void playerSubcommands(CommandSender sender, String[] args) {
		if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(args[2]))){
			sender.sendMessage(ChatColor.RED+"Игрок "+args[2]+" не в сети");
			return;
		}
		if(args.length<3) {
			sender.sendMessage(ChatColor.RED+"Использование /hgc player add/remove <player>");
			sender.sendMessage(ChatColor.RED+"Использование /hgc player setmod/setspec <player> true/false");
			return;
		}
		if(args.length>4) {
			sender.sendMessage(ChatColor.RED+"Использование /hgc player add/remove <player>");
			sender.sendMessage(ChatColor.RED+"Использование /hgc player setmod/setspec <player> true/false");
			return;
		}
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
			sender.sendMessage(ChatColor.RED+"Использование /hgc player add/remove <player>");
			sender.sendMessage(ChatColor.RED+"Использование /hgc player setmod/setspec <player> true/false");
			break;
		}
		}
	}
	
	private void gamestateSubcommand(CommandSender sender,String[] args) {
		switch (args[1]) {
		case("start"):{
			if( gameManager.canChangeGameSate(GameState.STARTING, sender)) {
				gameManager.setGameState(GameState.STARTING);
			}
			break;
		}
		case("end"):{
			if( gameManager.canChangeGameSate(GameState.ENDING, sender)) {
				gameManager.setGameState(GameState.ENDING);
			}
			break;
		}
		case("deathmatch"):{
			if( gameManager.canChangeGameSate(GameState.DEATHMATCH, sender)) {
				gameManager.setGameState(GameState.DEATHMATCH);
			}
			break;
		}
		case("pause"):{
			if( gameManager.canChangeGameSate(GameState.PAUSED, sender)) {
				gameManager.setGameState(GameState.PAUSED);
			}
			break;
		}
		case("resume"):{
			if( gameManager.canChangeGameSate(GameState.ACTIVE, sender)) {
				gameManager.setGameState(GameState.ACTIVE);
			}
			break;
		}
		default: {
			sender.sendMessage(ChatColor.RED+"Использование /hgc gamestate start/end/deathmatch/pause/resume");
			break;
		}
		}
	}
	
	private void settingsSubcommand(CommandSender sender, String[] args) {
		switch (args[1]) {
		case("gametime"):{
			Integer time = 0;
			try {
				time = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException e1) {
				e1.printStackTrace();
			}
			gameManager.setActiveTimeSec(time);
			break;
		}
		case("dmtime"):{
			Integer time = 0;
			try {
				time = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException e1) {
				e1.printStackTrace();
			}
			gameManager.setDeathmatchTimeSec(time);
			break;
		}
		case("glowingtime"):{
			Integer time = 0;
			try {
				time = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException e1) {
				e1.printStackTrace();
			}
			gameManager.setTimeBeforeGlowing(time);
			break;
		}
		case("addpos"):{
			switch(args[2]) {
			case("deathmatch"):{
				Player p = (Player) sender;
				gameManager.getTeleportManager().addDeathmatchLoc(p.getLocation());
				sender.sendMessage(ChatColor.GREEN+"Добавлена позиция в лист "+ChatColor.AQUA+ args[2]);
				break;
			}
			case("start"):{
				Player p = (Player) sender;
				gameManager.getTeleportManager().addStartingLoc(p.getLocation());
				sender.sendMessage(ChatColor.GREEN+"Добавлена позиция в лист "+ChatColor.AQUA+ args[2]);
				break;
			}
			default: {
				sender.sendMessage(ChatColor.RED+"Использование /hgc settings addpos start/deathmatch");
				break;
			}
			}
			break;
		}
		case("setpos"):{
			switch(args[2]) {
			case("spectate"):{
				Player p = (Player) sender;
				gameManager.getTeleportManager().setSpectateSpawn(p.getLocation());
				sender.sendMessage(ChatColor.GREEN+"Установлена позиция "+ChatColor.AQUA+ args[2]);
				break;
			}
			case("lobby"):{
				Player p = (Player) sender;
				gameManager.getTeleportManager().setSpectateSpawn(p.getLocation());
				sender.sendMessage(ChatColor.GREEN+"Установлена позиция "+ChatColor.AQUA+ args[2]);
				break;
			}
			default: {
				sender.sendMessage(ChatColor.RED+"Использование /hgc settings setpos spectate/lobby");
				break;
			}
			}
			break;
		}
		case("poslist"):{
			switch(args[2]) {
			case("deathmatch"):{
				//DUPLICATIVE SHIT GTFO DRY
				List<Location> list = gameManager.getTeleportManager().getListOfLocsByName(args[2]);
				Integer counter = 0;
				sender.sendMessage(ChatColor.GOLD+args[2]+ChatColor.AQUA+" locations list");
				for(Location loc:list) {
					Integer x = loc.getBlockX();
					Integer y = loc.getBlockY();
					Integer z = loc.getBlockZ();
					sender.sendMessage(ChatColor.AQUA+ counter.toString() +". X="+x.toString()+" Y="+y.toString()+" Z="+z.toString());
					counter++;
				}
				break;
			}
			case("start"):{
				//DUPLICATIVE SHIT GTFO DRY
				List<Location> list = gameManager.getTeleportManager().getListOfLocsByName(args[2]);
				Integer counter = 0;
				sender.sendMessage(ChatColor.GOLD+args[2]+ChatColor.AQUA+" locations list");
				for(Location loc:list) {
					Integer x = loc.getBlockX();
					Integer y = loc.getBlockY();
					Integer z = loc.getBlockZ();
					sender.sendMessage(ChatColor.AQUA+ counter.toString() +". X="+x.toString()+" Y="+y.toString()+" Z="+z.toString());
					counter++;
				}
				break;
			}
			default: {
				sender.sendMessage(ChatColor.RED+"Использование /hgc settings poslist start/deathmatch");
				break;
			}
			}
			break;
		}
		case("clearlist"):{
			switch(args[2]) {
			case("start"):{
				gameManager.getTeleportManager().clearListOfLocsByName(args[2]);
				sender.sendMessage(ChatColor.GREEN+"Очищен лист "+ChatColor.AQUA+ args[2]);
				break;
			}
			case("deathmatch"):{
				gameManager.getTeleportManager().clearListOfLocsByName(args[2]);
				sender.sendMessage(ChatColor.GREEN+"Очищен лист "+ChatColor.AQUA+ args[2]);
				break;
			}
			default: {
				sender.sendMessage(ChatColor.RED+"Использование /hgc settings clearlist start/deathmatch");
				break;
			}
			}
			break;
		}
		default: {
			sender.sendMessage(ChatColor.RED+"Использование /hgc settings gametime/dmtime/glowingtime/addpos/setpos/poslist/clearlist");
			break;
		}
		}
	}
}
