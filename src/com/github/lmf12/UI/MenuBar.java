package com.github.lmf12.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	private JMenu fileMenu, functionMenu;
	
	private JMenuItem newMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem createWordItem;
	
	public MenuBar() {
		
		/**
		 * �ļ��˵�
		 * */
		fileMenu = new JMenu("�ļ�");  
	    fileMenu.setMnemonic(KeyEvent.VK_F);  
	    this.add(fileMenu);  
	 
	    //�ļ��˵�--�½�
	    newMenuItem = new JMenuItem("�½�", KeyEvent.VK_N);  
	    fileMenu.add(newMenuItem);  
	    
	    //�ļ��˵�--����
	    saveMenuItem = new JMenuItem("����", KeyEvent.VK_N);  
	    fileMenu.add(saveMenuItem);  
	    
	    /**
		 * ���ܲ˵�
		 * */
		functionMenu = new JMenu("����");  
		functionMenu.setMnemonic(KeyEvent.VK_F);  
	    this.add(functionMenu);  
	    
	    //���ܲ˵�--��������
	    createWordItem = new JMenuItem("��������", KeyEvent.VK_N);  
	    functionMenu.add(createWordItem);  
	}
	
	/**
	 * ���� �ļ��˵�--���� �ļ�����
	 * */
	public void setSaveMenuItemListeners(ActionListener al) {
		
		saveMenuItem.addActionListener(al);
	}
	
	/**
	 * ���ܲ˵�--�������� �ļ�����
	 * */
	public void setCreateWordItemListeners(ActionListener al) {
		
		createWordItem.addActionListener(al);
	}
}
