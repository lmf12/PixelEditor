package com.github.lmf12.util;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Typeface {
	
	public static int[][] getTypeface(String c) {
		
		byte[] key = {(byte) 0x80,0x40,0x20,0x10,0x08,0x04,0x02,0x01};
		RandomAccessFile randomFile = null;
		int a[][] = new int[16][16];
		
		try {
			randomFile = new RandomAccessFile("c:/hzk16", "r");
	            
			byte[] word = c.getBytes("GB2312");
	            
			int offset = (94 * toUnsighed(toUnsighed(word[0]) - 0xa0 - 1) + (toUnsighed(word[1]) - 0xa0 - 1)) * 32;
			randomFile.seek(offset);
			byte[] bytes = new byte[32];
			randomFile.read(bytes);
	            
			for (int k = 0; k<16; k++) {
				for (int j = 0; j<2; j++) {
					for (int i = 0; i<8; i++) {
						int flag = bytes[k * 2 + j] & key[i];
						a[k][i+j*8] = flag != 0 ? 1 : 0;
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} 
				catch (IOException e1) {
				}
			}
		}
		return a;
	}
	
	private static int toUnsighed(int a) {
		
		if (a < 0) {
			a = 256 + a;
		}
		return a;
	}
}
