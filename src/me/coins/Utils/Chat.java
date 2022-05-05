package me.coins.Utils;

import net.md_5.bungee.api.ChatColor;

public class Chat {

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
