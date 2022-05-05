package me.coins.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.coins.API.CoinsAPI;
import me.coins.API.Messages;
import me.coins.Core.Main;
import me.coins.Utils.NumberFormat;

public class Command_Balance implements CommandExecutor, TabCompleter {

	Main plugin = Main.getPlugin(Main.class);
	
	@Override
	public List<String> onTabComplete(CommandSender se, Command arg1, String arg2, String[] args) {
		
		if(plugin.getConfig().get("Settings.economy-enabled") != null) {
			if(plugin.getConfig().getBoolean("Settings.economy-enabled") == true) {
				ArrayList<String> options = new ArrayList<>();
				if(args.length == 1) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
							options.add(p.getName());
						}
					}
					Collections.sort(options);
					return options;
				} else {
					return options;
				}
			}
			}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(plugin.getConfig().get("Settings.economy-enabled") != null) {
			if(plugin.getConfig().getBoolean("Settings.economy-enabled") == true) {
				if(args.length == 1) {
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
				Double balance = CoinsAPI.getBalance(p.getUniqueId().toString());
				sender.sendMessage(Messages.getMessage("getBalance").replace("%player%", p.getName()).replace("%balance%", balance.toString()).replace("%value%", CoinsAPI.getValue()));
				} else {
					sender.sendMessage(Messages.getMessage("useCommand").replace("%command%", "/balance [player]"));
				}
			} else {
				sender.sendMessage(Messages.getMessage("economyDisabled"));
			}
		}
		return false;
	}

}
