package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RightPanel extends JPanel {

	private ColorPickerPanel mColorPickerPanel = null; 
	
	public RightPanel() {
		
		mColorPickerPanel = new ColorPickerPanel();

		this.setBackground(Color.GREEN);
		this.add(mColorPickerPanel);
	}
	
	public void setGraphicsColorListener(BaseWindow.GraphicsColorListener linster) {
		
		mColorPickerPanel.setGraphicsColorListener(linster);
	}
	
	public ColorPickerPanel getColorPickerPanel() {
		
		return mColorPickerPanel;
	}
}
