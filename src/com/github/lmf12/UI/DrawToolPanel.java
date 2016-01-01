package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.lmf12.config.PixelColor;
import com.github.lmf12.config.PixelResource;

public class DrawToolPanel extends JPanel {
	
	private static final int DRAW_TOOL_PANEL_WIDTH = 200;
	private static final int DRAW_TOOL_PANEL_HEIGHT = 200;
	
	private String[] drawToolList = {PixelResource.ICON_PENCIL, PixelResource.ICON_ERASER, PixelResource.ICON_STRAW,
									PixelResource.ICON_KETTLE};   //绘画工具列表
	
	public DrawToolPanel(ActionListener[] als) {
		
		this.setPreferredSize(new Dimension(DRAW_TOOL_PANEL_WIDTH, DRAW_TOOL_PANEL_HEIGHT));
		addDrawTools(als);
	}
	
	/**
	 * 添加绘画工具	
	 * */
	private void addDrawTools(ActionListener[] als) {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		for (int i = 0; i < drawToolList.length; i++) {
			JButton button = new JButton();
			button.setBackground(Color.ORANGE);
			ImageIcon icon = new ImageIcon(drawToolList[i]);
	        button.setIcon(icon);
	        button.addActionListener(als[i]);
	    	c.fill = GridBagConstraints.HORIZONTAL;
	    	c.gridx = i % 2;
	    	c.gridy = i / 2;
			this.add(button, c);
	    }
	}
}
