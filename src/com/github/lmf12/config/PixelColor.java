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
	private static final String[] RECOMMEND_LIST_PINK = {"#E91E63", "#880E4F", "#AD1457", "#C2185B", "#D81B60",
		"#E91E63", "#EC407A", "#F06292", "#F48FB1", "#F8BBD0", "#FCE4EC"};
	private static final String[] RECOMMEND_LIST_DEEP_PURPLE = {"#673AB7", "#311B92", "#4527A0", "#512DA8", "#5E35B1",
		"#673AB7", "#7E57C2", "#9575CD", "#B39DDB", "#D1C4E9", "#EDE7F6"};
	private static final String[] RECOMMEND_LIST_INDIGO = {"#3F51B5", "#1A237E", "#283593", "#303F9F", "#3949AB",
		"#3F51B5", "#5C6BC0", "#7986CB", "#9FA8DA", "#C5CAE9", "#E8EAF6"};
	private static final String[] RECOMMEND_LIST_LIGHT_BLUE = {"#03A9F4", "#01579B", "#0277BD", "#0288D1", "#039BE5",
		"#03A9F4", "#29B6F6", "#4FC3F7", "#81D4FA", "#B3E5FC", "#E1F5FE"};
	private static final String[] RECOMMEND_LIST_CYAN = {"#00BCD4", "#006064", "#00838F", "#0097A7", "#00ACC1",
		"#00BCD4", "#26C6DA", "#4DD0E1", "#80DEEA", "#B2EBF2", "#E0F7FA"};
	private static final String[] RECOMMEND_LIST_TEAL = {"#009688", "#004D40", "#00695C", "#00796B", "#00897B",
		"#009688", "#26A69A", "#4DB6AC", "#80CBC4", "#B2DFDB", "#E0F2F1"};
	private static final String[] RECOMMEND_LIST_LIGHT_GREEN = {"#8BC34A", "#33691E", "#558B2F", "#689F38", "#7CB342",
		"#8BC34A", "#9CCC65", "#AED581", "#C5E1A5", "#DCEDC8", "#F1F8E9"};
	private static final String[] RECOMMEND_LIST_LIME = {"#CDDC39", "#827717", "#9E9D24", "#AFB42B", "#C0CA33",
		"#CDDC39", "#D4E157", "#DCE775", "#E6EE9C", "#F0F4C3", "#F9FBE7"};
	private static final String[] RECOMMEND_LIST_YELLOW = {"#FFEB3B", "#F57F17", "#F9A825", "#FBC02D", "#FDD835",
		"#FFEB3B", "#FFEE58", "#FFF176", "#FFF59D", "#FFF9C4", "#FFFDE7"};
	private static final String[] RECOMMEND_LIST_ORANGE = {"#FF9800", "#E65100", "#EF6C00", "#F57C00", "#FB8C00",
		"#FF9800", "#FFA726", "#FFB74D", "#FFCC80", "#FFE0B2", "#FFF3E0"};
	private static final String[] RECOMMEND_LIST_DEEP_ORANGE = {"#FF5722", "#BF360C", "#D84315", "#E64A19", "#F4511E",
		"#FF5722", "#FF7043", "#FF8A65", "#FFAB91", "#FFCCBC", "#FBE9E7"};
	private static final String[] RECOMMEND_LIST_BROWN = {"#795548", "#3E2723", "#4E342E", "#5D4037", "#6D4C41",
		"#795548", "#8D6E63", "#A1887F", "#BCAAA4", "#D7CCC8", "#EFEBE9"};
	private static final String[] RECOMMEND_LIST_GREY = {"#9E9E9E", "#212121", "#424242", "#616161", "#757575",
		"#9E9E9E", "#BDBDBD", "#E0E0E0", "#EEEEEE", "#F5F5F5", "#FAFAFA"};
	private static final String[] RECOMMEND_LIST_BLUE_GREY = {"#607D8B", "#263238", "#37474F", "#455A64", "#546E7A",
		"#607D8B", "#78909C", "#90A4AE", "#B0BEC5", "#CFD8DC", "#ECEFF1"};
	private static final String[] RECOMMEND_LIST_BLACK = {"#000000", "#000000", "#000000", "#000000", "#000000",
		"#000000", "#000000", "#000000", "#000000", "#000000", "#000000"};
	private static final String[] RECOMMEND_LIST_WHITE = {"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF",
		"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"};
	
	public static final String[][] RECOMMEND_LISTS = {RECOMMEND_LIST_RED, RECOMMEND_LIST_PINK,
		RECOMMEND_LIST_PURPLE, RECOMMEND_LIST_DEEP_PURPLE, RECOMMEND_LIST_INDIGO, RECOMMEND_LIST_BLUE,
		RECOMMEND_LIST_LIGHT_BLUE, RECOMMEND_LIST_CYAN, RECOMMEND_LIST_TEAL, RECOMMEND_LIST_GREEN,
		RECOMMEND_LIST_LIGHT_GREEN, RECOMMEND_LIST_LIME, RECOMMEND_LIST_YELLOW,RECOMMEND_LIST_AMBER, 
		RECOMMEND_LIST_ORANGE, RECOMMEND_LIST_DEEP_ORANGE, RECOMMEND_LIST_BROWN, RECOMMEND_LIST_GREY,
		RECOMMEND_LIST_BLUE_GREY, RECOMMEND_LIST_BLACK, RECOMMEND_LIST_WHITE};
	
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
