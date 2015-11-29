package com.github.lmf12.util;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingConsole {
	
	public static void createWindow(final JFrame f, final String title, final int width, 
			final int height, final int x, final int y) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				f.setTitle(title);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setLocation(x, y);
				f.setVisible(true);
			}
		});
	}
}
