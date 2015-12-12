package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.jws.Oneway;
import javax.swing.JPanel;

public class ColorRect extends JPanel {
	
	private Color color;
	private ColorRectLinster colorRectLinster;
	private String colorCode;
	
	public ColorRect(int width, final Color color, final String colorCode, final ColorRectLinster linster) {
		
		this.color = color;
		this.colorCode = colorCode;
		this.setBackground(color);
		this.setPreferredSize(new Dimension(width, width));
		this.colorRectLinster = linster;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				linster.getColor(color, colorCode);
			}
		});
	}
	
	public interface ColorRectLinster {
		
		void getColor(Color color, String colorCode);
	}
}
