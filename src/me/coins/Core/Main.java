package me.coins.Core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.coins.Commands.Command_Pay;
import me.coins.Commands.Command_Balance;
import me.coins.Commands.Command_Economy;
import me.coins.API.CoinsAPI;
import me.coins.API.Messages;
import me.coins.Utils.Chat;

public class Main extends JavaPlugin {
	
	
	
	@Override
	public void onEnable() {
		CoinsAPI coins = new CoinsAPI();
		coins.getFiles();
		registerCommands();
		registerTabCompleters();
		Bukkit.getConsoleSender().sendMessage(Messages.getMessage("onEnable").replace("%prefix%", CoinsAPI.getPrefix()));
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Messages.getMessage("onDisable").replace("%prefix%", CoinsAPI.getPrefix()));
	}
	
	public void registerCommands() {
		getCommand("economy").setExecutor(new Command_Economy());
		getCommand("pay").setExecutor(new Command_Pay());
		getCommand("balance").setExecutor(new Command_Balance());
	}
	
	public void registerTabCompleters() {
		getCommand("pay").setTabCompleter(new Command_Pay());
		getCommand("economy").setTabCompleter(new Command_Economy());
		getCommand("balance").setTabCompleter(new Command_Balance());
	}
}
