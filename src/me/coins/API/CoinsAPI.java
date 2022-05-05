package me.coins.API;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import me.coins.Core.Main;
import me.coins.Utils.Chat;
import me.coins.Utils.NumberFormat;

public class CoinsAPI {

	
	private static Main plugin = Main.getPlugin(Main.class);
	private static File data = new File(plugin.getDataFolder() + "//database//Balance.yml");
	private static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(data);
	public static String PluginPrefix = plugin.getConfig().getString("Settings.prefix");
			
	public void getFiles() {
		if(!new File(plugin.getDataFolder(), "config.yml").exists()) {
			plugin.saveDefaultConfig();
			plugin.reloadConfig();
		}
		if(!data.exists()) {
			try {
				yaml.createSection("Players");
				yaml.save(data);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if(!Messages.messages.exists()) {
			File message = Messages.messages;
			YamlConfiguration yaml = Messages.yaml;
			try {
				yaml.set("Messages.onEnable", Messages.onEnable);
				yaml.set("Messages.onDisable", Messages.onDisable);
				yaml.set("Messages.economyDisabled", Messages.economyDisabled);
				yaml.set("Messages.useCommand", Messages.useCommand);
				yaml.set("Messages.useNumbers", Messages.useNumbers);
				yaml.set("Messages.noPermission", Messages.noPermission);
				yaml.set("Messages.notEnoughMoney", Messages.notEnoughMoney);
				yaml.set("Messages.getBalance", Messages.getBalance);
				yaml.set("Messages.setBalance", Messages.setBalance);
				yaml.set("Messages.addedMoney", Messages.addedMoney);
				yaml.set("Messages.removedMoney", Messages.removedMoney);
				yaml.set("Messages.recievedMoney", Messages.recievedMoney);
				yaml.set("Messages.paidMoney", Messages.paidMoney);
				yaml.save(message);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	
	}
	
	public static String getValue() {
		if(plugin.getConfig().getString("Settings.payment_unit") != null) {
			return plugin.getConfig().getString("Settings.payment_unit");
		} else {
			return "€";
		}
	}
	
	public static boolean isInDatabase(UUID uuid) {
		if(yaml.get("Players." + uuid + ".balance") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static double getBalance(String uuid) {
		if(yaml.get("Players." + uuid + ".balance") != null) {
			if(isInteger(yaml.getString("Players." + uuid + ".balance")) == true) {
		return yaml.getDouble("Players." + uuid + ".balance");
			} else {
				return 0.00;
			}
		} else {
			return 0.00;
		}
	}
	
	public static void setBalance(String uuid, double newbalance) {
		try {
			yaml.set("Players." + uuid + ".balance", newbalance);
			yaml.save(data);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void addMoney(String uuid, double newbalance) {
		try {
			yaml.set("Players." + uuid + ".balance", getBalance(uuid) + newbalance);
			yaml.save(data);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void removeMoney(String uuid, double newbalance) {
		try {
			yaml.set("Players." + uuid + ".balance", getBalance(uuid) - newbalance);
			yaml.save(data);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static double getStarterBalance() {
		if(plugin.getConfig().get("Settings.starter_money") != null) {
			if(isInteger(plugin.getConfig().getString("Settings.starter_money")) == true) {
			return plugin.getConfig().getDouble("Settings.startermoney");
			} else {
				return 200.0;
			}
		} else {
			return 200.00;
		}
	}
	
	public static void setStarterBalance(double newbalance) {
		Double balance = newbalance;
			plugin.getConfig().set("Settings.starter_money", balance);
			plugin.saveConfig();
			plugin.reloadConfig();
		
	}
	
	public static String getPrefix() {
		if(plugin.getConfig().getString("Settings.prefix") != null) {
			return Chat.format(PluginPrefix);
		} else {
			return Chat.format("&8(&aEconomy&8)");
		}
	}
	
	private static boolean isInteger(String number) {
		try {
			Double.parseDouble(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
}
