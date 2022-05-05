package me.coins.API;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import me.coins.Core.Main;
import me.coins.Utils.Chat;

public class Messages {

	public static Main plugin = Main.getPlugin(Main.class);
	public static File messages = new File(plugin.getDataFolder(), "Messages.yml");
	public static YamlConfiguration yaml = new YamlConfiguration();
	
	public static String getMessage(String message) {
		if(messages.exists() && yaml.getString("Messages." + message) != null) {
			return Chat.format(yaml.getString("Messages." + message));
		} else {
			if(message.equalsIgnoreCase("onEnable")) {
				return Chat.format(onEnable).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("onDisable")) {
				return Chat.format(onDisable).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equals("economyDisabled")) {
				return Chat.format(economyDisabled).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("useCommand")) {
				return Chat.format(useCommand).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("useNumbers")) {
				return Chat.format(useNumbers).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("notEnoughMoney")) {
				return Chat.format(notEnoughMoney).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("noPermission")) {
				return Chat.format(noPermission).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("getBalance")) {
				return Chat.format(getBalance).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("setBalance")) {
				return Chat.format(setBalance).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("addedMoney")) {
				return Chat.format(addedMoney).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("removedMoney")) {
				return Chat.format(removedMoney).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("paidMoney")) {
				return Chat.format(paidMoney).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("recievedMoney")) {
				return Chat.format(recievedMoney).replace("%prefix%", CoinsAPI.getPrefix());
			} else if(message.equalsIgnoreCase("setStarterMoney")) {
				return Chat.format(setStarterMoney).replace("%prefix%", CoinsAPI.getPrefix());
			} else {
				return Chat.format("&cMessage not found.");
			}
		}
	}
	
	// plugin messages
	public static String onEnable = "%prefix% &aEnabled plugin economy.";
	public static String onDisable = "%prefix% &cDisabled plugin economy.";
	public static String economyDisabled = "&cEconomy is currently disabled you can't use this action.";
	public static String useCommand = "&aUse&7: %command%&7.";
	public static String useNumbers = "&c%message% is not a number.";
	public static String notEnoughMoney = "&cYou don't have enough money.";
	public static String noPermission = "&cYou don't have permission for this command.";
	
	// money messages
	public static String getBalance = "&a%player%'s &7balance is &a%value% %balance%&7.";
	public static String setBalance = "&7Set &e%player%'s &7balance to &a%value% %balance%.";
	public static String addedMoney = "&7Added &a%value% %money% &7to &e%player%'s &7balance.";
	public static String removedMoney = "&7Removed &a%value% %money% &7from &e%player%'s &7balance.";
	public static String paidMoney = "&7You paid &e%player% &a%value% %money%&7.";
	public static String recievedMoney = "&7You recieved &a%value% %money% &7from &e%player% &7.";  
	public static String setStarterMoney = "&7You set the startermoney to &a%money%&7.";  
}
