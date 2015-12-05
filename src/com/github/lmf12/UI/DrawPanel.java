package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.github.lmf12.config.PixelResource;

public class DrawPanel extends JPanel implements BaseWindow.GraphicsColorListener {
	
	private DrawPanelFront mDrawPanelFront = null;
	private DrawPanelBack mDrawPanelBack = null;
	private int cellSize = 20;   //单元格的尺寸，像素为单位，默认为20像素
	private int panelWidth = 16, panelHeight = 16;   //面板的长宽，表示有多少个单元格，默认为16×16
	
	public DrawPanel() {
	}
	
	/**
	 * 在新建面板后必须调用初始化函数，目的为了可以设置参数
	 * */
	public void init() {
		
		mDrawPanelBack = new DrawPanelBack(cellSize, panelWidth, panelHeight);
		mDrawPanelBack.setBounds(new Rectangle(0, 0, panelWidth * cellSize, panelHeight * cellSize));
		mDrawPanelFront = new DrawPanelFront(cellSize, panelWidth, panelHeight);
		mDrawPanelFront.setBounds(new Rectangle(0, 0, panelWidth * cellSize, panelHeight * cellSize));
		this.setLayout(null);
		this.add(mDrawPanelFront);
		this.add(mDrawPanelBack);
		
		/**
	     * 绘图面板的两个监听器
	     * */
		mDrawPanelFront.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseDragged(MouseEvent event) {
	
				mDrawPanelFront.draw(event.getX(), event.getY());
			}

			@Override
			public void mouseMoved(MouseEvent event) {
			}
		});
		mDrawPanelFront.addMouseListener(new MouseListener() {
			
	    	@Override
			public void mousePressed(MouseEvent event) {
				
	    		mDrawPanelFront.startDraw(event.getX(), event.getY());
			}
	    	
			@Override
			public void mouseReleased(MouseEvent event) {
			}
			
			@Override
			public void mouseExited(MouseEvent event) {
			}
			
			@Override
			public void mouseEntered(MouseEvent event) {
				
				
				Image IMAGE_HAND1 = new ImageIcon(PixelResource.ICON_PENCIL).getImage();

				Cursor CURSOR_PENCIL = Toolkit.getDefaultToolkit().createCustomCursor(IMAGE_HAND1,new Point(0, 31), "CURSOR_PENCIL");
				
				mDrawPanelFront.setCursor(CURSOR_PENCIL);
			}
			
			@Override
			public void mouseClicked(MouseEvent event) {
			}
		});
	}
	
	/**
	 * 设置单元格尺寸，默认为20像素
	 * */
	public void setCellSize(int size) {
		
		cellSize = size;
	}
	
	/**
	 * 设置面板长宽，默认为16×16
	 * */
	public void setPanelSize(int width, int height) {
		
		panelWidth = width;
		panelHeight = height;
	}
	
	public void savePicture() {
		
		mDrawPanelFront.savePicture();
	}

	@Override
	public void getColor(Color color) {

		if (mDrawPanelFront != null) {
			mDrawPanelFront.setGraphics2dColor(color);
		}
		else {
			System.out.println("请先初始化画板");
		}
	}
}
