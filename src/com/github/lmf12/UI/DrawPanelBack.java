package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanelBack extends JPanel {
	
	private int cellSize;
	private int panelWidth, panelHeight;
	
	public DrawPanelBack(int cellSize, int pWidth, int pHeight) {
		
		this.cellSize = cellSize;
		this.panelWidth = pWidth;
		this.panelHeight = pHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for (int i=0; i<panelHeight; ++i) {
			for (int j=0; j<panelWidth; ++j) {
				if (j == 0) {
					if (i%2 == 0) {
						g.setColor(Color.WHITE);
					}
					else {
						g.setColor(Color.GRAY);
					}
				}
				else {
					g.setColor(g.getColor() == Color.WHITE ? Color.GRAY : Color.WHITE);
				}
				g.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
			}
		}
	}
}
