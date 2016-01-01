package com.github.lmf12.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.github.lmf12.config.PixelResource;
import com.github.lmf12.util.Typeface;

/**
 * 基础窗口类，在这上面添加组件
 * */
public class BaseWindow extends JFrame {

	private MenuBar mMenuBar = null;
	private DrawPanel mDrawPanel = null;
	private RightPanel mRightPanel = null;
	private LeftPanel mLeftPanel = null;
	
	public BaseWindow() {
		
		mMenuBar = new MenuBar();
		this.setJMenuBar(mMenuBar);
		
		mDrawPanel = new DrawPanel();
		mDrawPanel.init();
		
		mRightPanel = new RightPanel();
		mRightPanel.setGraphicsColorListener(mDrawPanel);
		mDrawPanel.setColorAreaListener(mRightPanel.getColorPickerPanel());
		
		mLeftPanel = new LeftPanel();
		mLeftPanel.setDrawToolListeners(getDrawToolListener());
		mLeftPanel.init();
		
		JButton button = new JButton("Button 1 (PAGE_START)");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mDrawPanel.createPicWithData(Typeface.getTypeface("玩"));
			}
		});
		this.add(button, BorderLayout.PAGE_START);     
//	    button = new JButton("Button 2 (CENTER)");
//	    button.setPreferredSize(new Dimension(200, 100));
		
	    this.add(mDrawPanel, BorderLayout.CENTER);   
	    this.add(mLeftPanel, BorderLayout.LINE_START);   
	    
//	    button = new JButton("Long-Named Button 4 (PAGE_END)");
//	    this.add(button, BorderLayout.PAGE_END);      
	    this.add(mRightPanel, BorderLayout.LINE_END);
	    
	    /**
	     * 菜单栏的监听器
	     * */
	    mMenuBar.setSaveMenuItemListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mDrawPanel.savePicture();
			}
		});
	    
	    /**
	     * 最外层窗体监听窗口的变化
	     * */
	    this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				
				mDrawPanel.refreshDrawAreaLocation(mDrawPanel.getWidth(), mDrawPanel.getHeight());
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
	}
	
	/**
	 * 返回绘图工具的监听器
	 * */
	private ActionListener[] getDrawToolListener() {
		
		//铅笔监听器
		ActionListener pencilListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mDrawPanel.setDrawType(DrawPanelFront.DRAW_TYPE_PAINT, PixelResource.ICON_PENCIL);
			}
		};
		//橡皮擦监听器
		ActionListener eraserListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mDrawPanel.setDrawType(DrawPanelFront.DRAW_TYPE_ERASER, PixelResource.ICON_ERASER);
			}
		};
		//吸管监听器
		ActionListener strawListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mDrawPanel.setDrawType(DrawPanelFront.DRAW_TYPE_STRAW, PixelResource.ICON_STRAW);
			}
		};
		
		ActionListener[] als = {pencilListener, eraserListener, strawListener};
		
		return als;
	}
	
	/**
	 * 画笔颜色接口，用于让DrawPanel获取画笔颜色
	 * */
	public interface GraphicsColorListener {
		
		void getColor(Color color);
	}
	
	/**
	 * 颜色区域接口，用于让ColorPickerPanel更新颜色区域的颜色
	 * */
	public interface ColorAreaListener {
		
		void getColor(Color color);
	}
}
