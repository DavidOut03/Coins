package me.coins.Commands;

import java.lang.reflect.Array;
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

public class Command_Economy implements CommandExecutor,TabCompleter {

	Main plugin = Main.getPlugin(Main.class);
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		
		if(plugin.getConfig().get("Settings.economy-enabled") != null) {
			if(plugin.getConfig().getBoolean("Settings.economy-enabled") == true) {
				if(sender.hasPermission("Coins.economy")) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("setstartermoney")) {
						if(isInteger(args[1]) == true) {
						Double money = Double.parseDouble(args[1]);
						sender.sendMessage(Messages.getMessage("setStarterMoney").replace("%money%", args[1]));
						CoinsAPI.setStarterBalance(money);
						} else {
							sender.sendMessage(Messages.getMessage("useNumbers").replace("%message%", args[1]));
						}
					} else if(args[0].equalsIgnoreCase("get")) {
						OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
						sender.sendMessage(Messages.getMessage("getBalance").replace("%player%", t.getName()).replace("%balance%", CoinsAPI.getBalance(t.getUniqueId().toString()) + "").replace("%value%", CoinsAPI.getValue()));
					} else {
						sender.sendMessage(Messages.getMessage("useCommand").replace("%command%", "/economy [get/setStarterMoney] [player/balance]"));
					}
				} else if(args.length == 3) {
					OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
					if(args[0].equalsIgnoreCase("set")) {
						if(isInteger(args[2]) == true) {
							Double newbalance = Double.parseDouble(args[2]);
							CoinsAPI.setBalance(t.getUniqueId().toString(), newbalance);
							sender.sendMessage(Messages.getMessage("setBalance").replace("%player%", t.getName()).replace("%balance%", Double.parseDouble(args[2]) + "").replace("%value%", CoinsAPI.getValue()));
						} else {
							sender.sendMessage(Messages.getMessage("useNumbers").replace("%message%", args[1]));
						}
					} else if(args[0].equalsIgnoreCase("add")) {
						if(isInteger(args[2]) == true) {
							Double money = Double.parseDouble(args[2]);
							CoinsAPI.addMoney(t.getUniqueId().toString(), money);
							sender.sendMessage(Messages.getMessage("addedMoney").replace("%money%", money.toString()).replace("%player%", t.getName()).replace("%value%", CoinsAPI.getValue()));
						} else {
							sender.sendMessage(Messages.getMessage("useNumbers").replace("%message%", args[1]));
						}
					} else if(args[0].equalsIgnoreCase("remove")) {
						if(isInteger(args[2]) == true) {
							Double money = Double.parseDouble(args[2]);
							CoinsAPI.removeMoney(t.getUniqueId().toString(), money);
							sender.sendMessage(Messages.getMessage("removedMoney").replace("%money%", money.toString()).replace("%player%", t.getName()).replace("%value%", CoinsAPI.getValue()));
						} else {
							sender.sendMessage(Messages.getMessage("useNumbers").replace("%message%", args[1]));
						}
					} else {
						sender.sendMessage(Messages.getMessage("useCommand").replace("%command%", "/economy [set/add/remove] [player] [balance]"));
					}
				} else {
	
					sender.sendMessage(Messages.getMessage("useCommand").replace("%command%", "/economy [set/add/get/setStarterMoney] [player] [balance]"));
				}
				} else {
					sender.sendMessage(Messages.getMessage("noPermission"));
				}
			} else {
				sender.sendMessage(Messages.getMessage("economyDisabled"));
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.hasPermission("Coins.economy")) {
			ArrayList<String> options = new ArrayList<>();
			ArrayList<String> alloptions = new ArrayList<>();
			
			if(args.length == 1) {
			alloptions.add("Set");
			alloptions.add("Add");
			alloptions.add("Remove");
			alloptions.add("Get");
			alloptions.add("setStarterMoney");
			
			for(String currentoption : alloptions) {
				if(currentoption.toLowerCase().startsWith(args[0].toLowerCase())) {
					options.add(currentoption);
				}
			}
			Collections.sort(options);
			return options;
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("Set")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
							options.add(p.getName());
						}
					}
					Collections.sort(options);
					return options;
				} else if(args[0].equalsIgnoreCase("Add")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
							options.add(p.getName());
						}
					}
					Collections.sort(options);
					return options;
				} else if( args[0].equalsIgnoreCase("Remove")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
							options.add(p.getName());
						}
					}
					Collections.sort(options);
					return options;
				} else if(args[0].equalsIgnoreCase("Get")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
							options.add(p.getName());
						}
					}
					Collections.sort(options);
					return options;
				}
			} else {
				return options;
			}
		}
		return null;
	}
	
	public static boolean isInteger(String message) {
		try {
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	// /economy set [player] [balance]
	// /economy add [player] [balance]
	// /economy get [player] 
	// /economy setStarterMoney [balance]
}
