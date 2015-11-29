package com.github.lmf12.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	private JMenu fileMenu;
	
	private JMenuItem newMenuItem;
	private JMenuItem saveMenuItem;
	
	public MenuBar() {
		
		/**
		 * 文件菜单
		 * */
		fileMenu = new JMenu("文件");  
	    fileMenu.setMnemonic(KeyEvent.VK_F);  
	    this.add(fileMenu);  
	    
	    /**
	     * 文件菜单--新建
	     * */
	    newMenuItem = new JMenuItem("新建", KeyEvent.VK_N);  
	    fileMenu.add(newMenuItem);  
	    
	    /**
	     * 文件菜单--保存
	     * */
	    saveMenuItem = new JMenuItem("保存", KeyEvent.VK_N);  
	    fileMenu.add(saveMenuItem);  
	}
	
	/**
	 * 设置 文件菜单--保存 的监听器
	 * */
	public void setSaveMenuItemListeners(ActionListener al) {
		
		saveMenuItem.addActionListener(al);
	}
}
