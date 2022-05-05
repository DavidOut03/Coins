package me.coins.Utils;

import java.text.DecimalFormat;

public class NumberFormat {

	public static double FormatNumbers(int message) {
		
			 DecimalFormat df = new DecimalFormat("#.##");
			 return  Double.parseDouble(df.format(message));
		
	}
	
	public static boolean isInteger(String message) {
		try {
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
}
