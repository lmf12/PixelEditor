package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class LeftPanel extends JPanel {

	private DrawToolPanel mDrawToolPanel = null; 
	private ActionListener[] drawToolListeners = null;
	
	public LeftPanel() {
		
		this.setBackground(new Color(218, 217, 216));
	}
	
	/**
	 * 调用之前要先设置监听器
	 * */
	public void init() {
		
		mDrawToolPanel = new DrawToolPanel(drawToolListeners);
		this.add(mDrawToolPanel);
	}
	
	/**
	 * 设置工具监听器，必须在init之前调用
	 * */
	public void setDrawToolListeners(ActionListener[] als) {
		
		drawToolListeners = als;
	}
}
