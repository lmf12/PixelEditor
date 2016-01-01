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

import com.github.lmf12.UI.BaseWindow.ColorAreaListener;

public class DrawPanelFront extends JPanel {
	
	static public final int DRAW_TYPE_PAINT = 1;   //绘笔模式
	static public final int DRAW_TYPE_ERASER = 2;   //橡皮擦模式
	static public final int DRAW_TYPE_STRAW = 3;   //吸管模式

	private int locX, locY;   //本次的位置
	private BufferedImage mBufferedImage;
	private Graphics2D mGraphics2d;
	private boolean isBegin;   //表示是否开始绘画
	private boolean isDataDrawing;  //表示是否正在处于数据绘图
	private int cellSize;
	private int panelWidth, panelHeight;
	private int[][] drawData;
	private int drawType;   //绘图模式
	private ColorAreaListener mColorAreaListener;
	
	public DrawPanelFront(int cellSize, int pWidth, int pHeight) {
		
		this.setOpaque(false);   //设置背景透明
		this.isBegin = false;
		this.isDataDrawing = false;
		this.drawType = DRAW_TYPE_PAINT;
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
		
		//用于数据绘图
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
		//用于自由绘制
		if (isBegin) {
			if (mGraphics2d != null) {
				if (drawType == DRAW_TYPE_PAINT) {
					mGraphics2d.fillRect(locX - (locX % cellSize), locY - (locY % cellSize), cellSize, cellSize);
					g.drawImage(mBufferedImage, 0, 0, null);
				}
				else if (drawType == DRAW_TYPE_ERASER) {
					Color paintColor = mGraphics2d.getColor();
					mBufferedImage = clearOnePixel(mBufferedImage, panelWidth, panelHeight, locX / cellSize, locY / cellSize);
					mGraphics2d = mBufferedImage.createGraphics();   //缓存图变了，所以画笔也要变
					mGraphics2d.setColor(paintColor);
					g.drawImage(mBufferedImage, 0, 0, null);
				}
				else if (drawType == DRAW_TYPE_STRAW) {
					g.drawImage(mBufferedImage, 0, 0, null);
					
					if (locX >= 0 && locY >= 0 && locX < mBufferedImage.getWidth() && locY < mBufferedImage.getHeight()) {
						if (getElementColor(locX, locY).getAlpha() != 0) {
							mColorAreaListener.getColor(getElementColor(locX, locY));
						}
					}
				}
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
	
	/**
	 * 根据数据来绘制图片
	 * */
	public void createPicWithData(int[][] data) {
		
		if (data.length > panelHeight || data[0].length > panelWidth) {
			System.out.println("设置失败，数据尺寸过大");
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
			System.out.println("请先初始化绘画面板");
		}
	}
	
	/**
	 * 清除某一个像素点
	 * 因为没有直接的重绘方法，这里选择重新绘制一个BufferedImage
	 * */
	private BufferedImage clearOnePixel(BufferedImage bufferedImage, int width, int height, int x, int y) {
		
		BufferedImage targetImage = new BufferedImage(width*cellSize, height*cellSize, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = targetImage.createGraphics();
		
		if (width == 1) {
			return targetImage;
		}
		
		int width1 = width / 2;
		int width2 = width - width1;
		int height1 = height / 2;
		int height2 = height - height1;
		
		BufferedImage clearArea;
		
		if (x < width1 && y < height1) {
			clearArea = bufferedImage.getSubimage(0, 0, width1 * cellSize, height1 * cellSize);
			graphics.drawImage(clearOnePixel(clearArea, width1, height1, x, y), 0, 0, null);
			
			graphics.drawImage(bufferedImage.getSubimage(width1 * cellSize, 0, width2 * cellSize, height1 * cellSize), width1 * cellSize, 0, null);
			graphics.drawImage(bufferedImage.getSubimage(0, height1 * cellSize, width1 * cellSize, height2 * cellSize), 0, height1 * cellSize, null);
			graphics.drawImage(bufferedImage.getSubimage(width1 * cellSize, height1 * cellSize, width2 * cellSize, height2 * cellSize), width1 * cellSize, height1 * cellSize, null);
		}
		else if (x >= width1 && y < height1) {
			clearArea = bufferedImage.getSubimage(width1 * cellSize, 0, width2 * cellSize, height1 * cellSize);
			graphics.drawImage(clearOnePixel(clearArea, width2, height1, x-width1, y), width1 * cellSize, 0, null);
			
			graphics.drawImage(bufferedImage.getSubimage(0, 0, width1 * cellSize, height1 * cellSize), 0, 0, null);
			graphics.drawImage(bufferedImage.getSubimage(0, height1 * cellSize, width1 * cellSize, height2 * cellSize), 0, height1 * cellSize, null);
			graphics.drawImage(bufferedImage.getSubimage(width1 * cellSize, height1 * cellSize, width2 * cellSize, height2 * cellSize), width1 * cellSize, height1 * cellSize, null);
		}
		else if (x < width1 && y >= height1) {
			clearArea = bufferedImage.getSubimage(0, height1 * cellSize, width1 * cellSize, height2 * cellSize);
			graphics.drawImage(clearOnePixel(clearArea, width1, height2, x, y-height1), 0, height1 * cellSize, null);
			
			graphics.drawImage(bufferedImage.getSubimage(0, 0, width1 * cellSize, height1 * cellSize), 0, 0, null);
			graphics.drawImage(bufferedImage.getSubimage(width1 * cellSize, 0, width2 * cellSize, height1 * cellSize), width1 * cellSize, 0, null);
			graphics.drawImage(bufferedImage.getSubimage(width1 * cellSize, height1 * cellSize, width2 * cellSize, height2 * cellSize), width1 * cellSize, height1 * cellSize, null);		
		}
		else if (x >= width1 && y >= height1) {
			clearArea = bufferedImage.getSubimage(width1 * cellSize, height1 * cellSize, width2 * cellSize, height2 * cellSize);
			graphics.drawImage(clearOnePixel(clearArea, width2, height2, x-width1, y-height1), width1 * cellSize, height1 * cellSize, null);
			
			graphics.drawImage(bufferedImage.getSubimage(0, 0, width1 * cellSize, height1 * cellSize), 0, 0, null);
			graphics.drawImage(bufferedImage.getSubimage(width1 * cellSize, 0, width2 * cellSize, height1 * cellSize), width1 * cellSize, 0, null);
			graphics.drawImage(bufferedImage.getSubimage(0, height1 * cellSize, width1 * cellSize, height2 * cellSize), 0, height1 * cellSize, null);
		}
		
		return targetImage;
	}
	
	/**
	 * 获取某一个像素点的颜色值
	 * */
	public Color getElementColor(int x, int y) {
		
		Object data = mBufferedImage.getRaster().getDataElements(x, y, null);
        int red = mBufferedImage.getColorModel().getRed(data);  
        int blue = mBufferedImage.getColorModel().getBlue(data);  
        int green = mBufferedImage.getColorModel().getGreen(data);  
        int Alpha = mBufferedImage.getColorModel().getAlpha(data);

        return new Color(red, green, blue, Alpha);
	}
	
	/**
	 * 设置画板模式，绘画模式，橡皮擦模式等
	 * */
	public void setDrawType(int type) {
		
		drawType = type;
	}
	
	/**
	 * 设置颜色区域监听器
	 * */
	public void setColorAreaListener(ColorAreaListener colorAreaListener) {
		
		this.mColorAreaListener = colorAreaListener;
	}
}
