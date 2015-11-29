package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements BaseWindow.GraphicsColorListener {

	private Color defaultBgColor = Color.WHITE;
	private int lastX, lastY;    //�ϴε�λ��
	private int locX, locY;   //���ε�λ��
	private BufferedImage mBufferedImage;
	private Graphics2D mGraphics2d;
	private boolean isBegin;   //��ʾ�Ƿ�ʼ�滭
	
	public DrawPanel() {
		
		this.setBackground(defaultBgColor);
		mBufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		mGraphics2d = mBufferedImage.createGraphics();
		mGraphics2d.setColor(Color.BLACK);
		isBegin = false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if (isBegin) {
			mGraphics2d.drawLine(lastX, lastY, locX, locY);
			g.drawImage(mBufferedImage, 0, 0, null);
		}
	}
	
	public void draw(int x, int y) {
		
		lastX = locX;
		lastY = locY;
		locX = x;
		locY = y;
		repaint();
	}
	
	public void startDraw(int x, int y) {
		
		isBegin = true;
		lastX = x;
		lastY = y;
		locX = x;
		locY = y;
	}
	
	public void savePicture() {
		
		try {
			ImageIO.write(mBufferedImage, "PNG", new File("frame.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void getColor(Color color) {
		// TODO Auto-generated method stub
		mGraphics2d.setColor(color);
	}
}
