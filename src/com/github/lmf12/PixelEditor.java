package com.github.lmf12;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.github.lmf12.UI.BaseWindow;
import com.github.lmf12.util.SwingConsole;

public class PixelEditor {
	
	/**
	 * 全局参数
	 * */
	public static final int PARAMETER_WIDTH = 1000;
	public static final int PARAMETER_HEIGHT = 800;
	public static final String PARAMETER_TITLE = "测试";
	
	public static void main(String[] args) {
		
	    Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();   //获取屏幕大小
		SwingConsole.createWindow(new BaseWindow(), PARAMETER_TITLE, PARAMETER_WIDTH, PARAMETER_HEIGHT,
				(screenSize.width - PARAMETER_WIDTH) / 2, (screenSize.height - PARAMETER_HEIGHT) / 2);
	}
}
