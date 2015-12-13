package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanelFront extends JPanel {

	private int locX, locY;   //���ε�λ��
	private BufferedImage mBufferedImage;
	private Graphics2D mGraphics2d;
	private boolean isBegin;   //��ʾ�Ƿ�ʼ�滭
	private boolean isDataDrawing;  //��ʾ�Ƿ����ڴ������ݻ�ͼ
	private int cellSize;
	private int panelWidth, panelHeight;
	private int[][] drawData;
	
	public DrawPanelFront(int cellSize, int pWidth, int pHeight) {
		
		this.setOpaque(false);   //���ñ���͸��
		this.isBegin = false;
		this.isDataDrawing = false;
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
		
		//�������ݻ�ͼ
		if (isDataDrawing) {
			for (int i=0; i<drawData.length; ++i) {
				for (int j=0; j<drawData[0].length; ++j) {
					if (drawData[i][j] == 1) {
						mGraphics2d.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
					}
				}
			}
			g.drawImage(mBufferedImage, 0, 0, null);
			isDataDrawing = false;
			return;
		}
		//�������ɻ���
		if (isBegin) {
			if (mGraphics2d != null) {
				mGraphics2d.fillRect(locX - (locX % cellSize), locY - (locY % cellSize), cellSize, cellSize);
				g.drawImage(mBufferedImage, 0, 0, null);
			}
			else {
				System.out.println("���ȳ�ʼ���滭���");
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
	
	/**
	 * ��������������ͼƬ
	 * */
	public void createPicWithData(int[][] data) {
		
		if (data.length > panelHeight || data[0].length > panelWidth) {
			System.out.println("����ʧ�ܣ����ݳߴ����");
		}
		drawData = data;
		isDataDrawing = true;
		repaint();
	}

	public void setGraphics2dColor(Color color) {
		
		if (mGraphics2d != null) {
			mGraphics2d.setColor(color);
		}
		else {
			System.out.println("���ȳ�ʼ���滭���");
		}
	}
}
