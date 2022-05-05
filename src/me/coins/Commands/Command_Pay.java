package me.coins.Commands;

import java.io.IOException;
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

public class Command_Pay implements CommandExecutor, TabCompleter {
	
	Main plugin = Main.getPlugin(Main.class);

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg2, String[] args) {
	
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
				options.clear();
				return options;
			}
		} else return null;
		} else return null;
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(plugin.getConfig().get("Settings.economy-enabled") != null) {
			if(plugin.getConfig().getBoolean("Settings.economy-enabled") == true) {
			if(args.length == 2) {
				Player t = Bukkit.getPlayer(args[0]);
				if(t != null) {
					if(isInteger(args[1]) == true) {
						Double money = Double.parseDouble(args[1]);
						if(sender instanceof Player) {
							Player p = (Player) sender;
							if(CoinsAPI.getBalance(p.getUniqueId().toString()) == money || CoinsAPI.getBalance(p.getUniqueId().toString()) >= money) {
					         CoinsAPI.addMoney(t.getUniqueId().toString(), money);
					         sender.sendMessage(Messages.getMessage("paidMoney").replace("%money%", money.toString()).replace("%player%", t.getName()).replace("%value%", CoinsAPI.getValue()));
							} else {
								sender.sendMessage(Messages.getMessage("notEnoughMoney"));
							}
						} else {
							 CoinsAPI.addMoney(t.getUniqueId().toString(), money);
						}
					} else {
						sender.sendMessage(Messages.getMessage("useNumbers").replace("%message%", args[1]));
					}
				} else {
					OfflinePlayer toff = Bukkit.getOfflinePlayer(args[1]);
					if(isInteger(args[1]) == true) {
						Double money = Double.parseDouble(args[1]);
						if(sender instanceof Player) {
							Player p = (Player) sender;
							if(CoinsAPI.getBalance(p.getUniqueId().toString()) == money || CoinsAPI.getBalance(p.getUniqueId().toString()) >= money) {
					         CoinsAPI.addMoney(toff.getUniqueId().toString(), money);
					         sender.sendMessage(Messages.getMessage("paidMoney").replace("%money%", money.toString()).replace("%player%", toff.getName()).replace("%value%", CoinsAPI.getValue()));
							} else {
								sender.sendMessage(Messages.getMessage("notEnoughMoney"));
							}
						} else {
							 CoinsAPI.addMoney(toff.getUniqueId().toString(), money);
						}
					} else {
						sender.sendMessage(Messages.getMessage("useNumbers").replace("%message%", args[1]));
					}
				}
			} else {
				sender.sendMessage(Messages.getMessage("useCommand").replace("%command%", "/pay [player] [amount]"));
			}
		
			} else {
				sender.sendMessage(Messages.getMessage("economyDisabled"));
			}
		} else {
			sender.sendMessage(Messages.getMessage("economyDisabled"));
		}
		return false;
	}
	
	public static boolean isInteger(String message) {
		try {
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
