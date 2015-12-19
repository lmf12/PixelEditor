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

import com.github.lmf12.util.Typeface;

/**
 * ���������࣬��������������
 * */
public class BaseWindow extends JFrame {

	private MenuBar mMenuBar = null;
	private DrawPanel mDrawPanel = null;
	private RightPanel mRightPanel = null;
	
	public BaseWindow() {
		
		mMenuBar = new MenuBar();
		this.setJMenuBar(mMenuBar);
		
		mDrawPanel = new DrawPanel();
		mDrawPanel.init();
		
		mRightPanel = new RightPanel();
		
		mRightPanel.setGraphicsColorListener(mDrawPanel);
		
		JButton button = new JButton("Button 1 (PAGE_START)");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mDrawPanel.setDrawType(DrawPanelFront.DRAW_TYPE_ERASER);
			}
		});
		this.add(button, BorderLayout.PAGE_START);     
//	    button = new JButton("Button 2 (CENTER)");
//	    button.setPreferredSize(new Dimension(200, 100));
	    this.add(mDrawPanel, BorderLayout.CENTER);   
	    button = new JButton("Button 3 (LINE_START)");
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mDrawPanel.createPicWithData(Typeface.getTypeface("��"));
			}
		});
	    this.add(button, BorderLayout.LINE_START);     
//	    button = new JButton("Long-Named Button 4 (PAGE_END)");
//	    this.add(button, BorderLayout.PAGE_END);      
	    this.add(mRightPanel, BorderLayout.LINE_END);
	    
	    /**
	     * �˵����ļ�����
	     * */
	    mMenuBar.setSaveMenuItemListeners(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mDrawPanel.savePicture();
			}
		});
	    
	    /**
	     * ����㴰��������ڵı仯
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
	 * ������ɫ�ӿڣ�������DrawPanel��ȡ������ɫ
	 * */
	public interface GraphicsColorListener {
		
		void getColor(Color color);
	}
}
