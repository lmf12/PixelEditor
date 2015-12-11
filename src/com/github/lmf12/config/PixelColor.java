package com.github.lmf12.config;

import java.awt.Color;

public class PixelColor {
	
	private static final String[] RECOMMEND_LIST_RED = {"#F44336", "#B71C1C", "#C62828", "#D32F2F", "#E53935",
		"#F44336", "#EF5350", "#E57373", "#EF9A9A", "#FFCDD2", "#FFEBEE"};
	private static final String[] RECOMMEND_LIST_BLUE = {"#2196F3", "#0D47A1", "#1565C0", "#1976D2", "#1E88E5",
		"#2196F3", "#42A5F5", "#64B5F6", "#90CAF9", "#BBDEFB", "#E3F2FD"};
	private static final String[] RECOMMEND_LIST_PURPLE = {"#9C27B0", "#4A148C", "#6A1B9A", "#7B1FA2", "#8E24AA",
		"#9C27B0", "#AB47BC", "#BA68C8", "#CE93D8", "#E1BEE7", "#F3E5F5"};
	private static final String[] RECOMMEND_LIST_AMBER = {"#FFC107", "#FF6F00", "#FF8F00", "#FFA000", "#FFB300",
		"#FFC107", "#FFCA28", "#FFD54F", "#FFE082", "#FFECB3", "#FFF8E1"};
	private static final String[] RECOMMEND_LIST_GREEN = {"#4CAF50", "#1B5E20", "#2E7D32", "#388E3C", "#43A047",
		"#4CAF50", "#66BB6A", "#81C784", "#A5D6A7", "#C8E6C9", "#E8F5E9"};
	
	public static final String[][] RECOMMEND_LISTS = {RECOMMEND_LIST_RED, RECOMMEND_LIST_BLUE,
		RECOMMEND_LIST_PURPLE, RECOMMEND_LIST_AMBER, RECOMMEND_LIST_GREEN};
	
	public static Color turnToColor(String color) {
		
		int r = Integer.valueOf(color.substring(1, 3), 16);
		int g = Integer.valueOf(color.substring(3, 5), 16);
		int b = Integer.valueOf(color.substring(5, 7), 16);
		
		return new Color(r, g, b);
	}
	
	public static String turnToCode(Color color) {
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		String rString = Integer.toHexString(r).toUpperCase();
		String gString = Integer.toHexString(g).toUpperCase();
		String bString = Integer.toHexString(b).toUpperCase();
		
		if (rString.length() == 1) {
			rString = "0" + rString;
		}
		if (gString.length() == 1) {
			gString = "0" + gString;
		}
		if (bString.length() == 1) {
			bString = "0" + bString;
		}
		
		return "#" + rString + gString + bString;
	}
}
