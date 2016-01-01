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

import com.github.lmf12.UI.BaseWindow.ColorAreaListener;
import com.github.lmf12.config.PixelResource;

public class DrawPanel extends JPanel implements BaseWindow.GraphicsColorListener {
	
	private DrawPanelFront mDrawPanelFront = null;
	private DrawPanelBack mDrawPanelBack = null;
	private int cellSize = 20;   //��Ԫ��ĳߴ磬����Ϊ��λ��Ĭ��Ϊ20����
	private int panelWidth = 16, panelHeight = 16;   //���ĳ�����ʾ�ж��ٸ���Ԫ��Ĭ��Ϊ16��16
	private String icon = PixelResource.ICON_PENCIL;
	
	public DrawPanel() {
	}
	
	/**
	 * ���½����������ó�ʼ��������Ŀ��Ϊ�˿������ò���
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
	     * ��ͼ��������������
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
				
				Image IMAGE_HAND1 = new ImageIcon(icon).getImage();
				Cursor CURSOR_PENCIL = Toolkit.getDefaultToolkit().createCustomCursor(IMAGE_HAND1,new Point(0, 31), "CURSOR_PENCIL");
				mDrawPanelFront.setCursor(CURSOR_PENCIL);
			}
			
			@Override
			public void mouseClicked(MouseEvent event) {
			}
		});
	}
	
	/**
	 * ���õ�Ԫ��ߴ磬Ĭ��Ϊ20����
	 * */
	public void setCellSize(int size) {
		
		cellSize = size;
	}
	
	/**
	 * ������峤��Ĭ��Ϊ16��16
	 * */
	public void setPanelSize(int width, int height) {
		
		panelWidth = width;
		panelHeight = height;
	}
	
	public void savePicture() {
		
		mDrawPanelFront.savePicture();
	}
	
	public void createPicWithData(int[][] data) {
		
		mDrawPanelFront.createPicWithData(data);
	}
	
	
	/**
	 * ˢ�»�������λ�ã�ʹ�������ʾ
	 * */
	public void refreshDrawAreaLocation(int winWidth, int winHeight) {
		
		mDrawPanelBack.setBounds(new Rectangle((winWidth - panelWidth * cellSize) / 2, 
				(winHeight - panelHeight * cellSize) / 2, panelWidth * cellSize, panelHeight * cellSize));
		mDrawPanelFront.setBounds(new Rectangle((winWidth - panelWidth * cellSize) / 2, 
				(winHeight - panelHeight * cellSize) / 2, panelWidth * cellSize, panelHeight * cellSize));
	}

	@Override
	public void getColor(Color color) {

		if (mDrawPanelFront != null) {
			mDrawPanelFront.setGraphics2dColor(color);
		}
		else {
			System.out.println("���ȳ�ʼ������");
		}
	}
	
	/**
	 * ���û���ģʽ���滭ģʽ����Ƥ��ģʽ��
	 * */
	public void setDrawType(int type, String icon) {
		
		mDrawPanelFront.setDrawType(type);
		this.icon = icon;
	}
	
	/**
	 * ������ɫ���������
	 * */
	public void setColorAreaListener(ColorAreaListener colorAreaListener) {
		
		mDrawPanelFront.setColorAreaListener(colorAreaListener);
	}
}
