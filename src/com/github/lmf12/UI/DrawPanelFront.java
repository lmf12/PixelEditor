package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanelFront extends JPanel {

	private int locX, locY;   //本次的位置
	private BufferedImage mBufferedImage;
	private Graphics2D mGraphics2d;
	private boolean isBegin;   //表示是否开始绘画
	private int cellSize;
	private int panelWidth, panelHeight;
	
	public DrawPanelFront(int cellSize, int pWidth, int pHeight) {
		
		this.setOpaque(false);   //设置背景透明
		this.isBegin = false;
		this.panelWidth = pWidth;
		this.panelHeight = pHeight;
		this.cellSize = cellSize;
		this.mBufferedImage = new BufferedImage(panelWidth*cellSize, panelHeight*cellSize, BufferedImage.TYPE_INT_ARGB);
		this.mGraphics2d = mBufferedImage.createGraphics();
		this.mGraphics2d.setColor(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if (isBegin) {
			if (mGraphics2d != null) {
				mGraphics2d.fillRect(locX - (locX % cellSize), locY - (locY % cellSize), cellSize, cellSize);
				g.drawImage(mBufferedImage, 0, 0, null);
			}
			else {
				System.out.println("请先初始化绘画面板");
			}
		}
	}
	
	public void draw(int x, int y) {
		
		locX = x;
		locY = y;
		repaint();
	}
	
	public void startDraw(int x, int y) {
		
		isBegin = true;
		locX = x;
		locY = y;
		repaint();
	}
	
	public void savePicture() {
		
		try {
			ImageIO.write(mBufferedImage, "PNG", new File("frame.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void setGraphics2dColor(Color color) {
		
		if (mGraphics2d != null) {
			mGraphics2d.setColor(color);
		}
		else {
			System.out.println("请先初始化绘画面板");
		}
	}
}
