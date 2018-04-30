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
	 * ����֮ǰҪ�����ü�����
	 * */
	public void init() {
		
		mDrawToolPanel = new DrawToolPanel(drawToolListeners);
		this.add(mDrawToolPanel);
	}
	
	/**
	 * ���ù��߼�������������init֮ǰ����
	 * */
	public void setDrawToolListeners(ActionListener[] als) {
		
		drawToolListeners = als;
	}
}
