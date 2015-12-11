package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.github.lmf12.config.PixelColor;

/**
 * 取色面板，放置于右面板中
 * */
public class ColorPickerPanel extends JPanel {

	private static final int COLOR_PANEL_WIDTH = 250;
	private static final int COLOR_PANEL_HEIGHT = 250;
	private static final int COLOR_PANEL_X_BORDER = 40;   //取色面板的横向边距
	private static final int COLOR_SLIDER_BAR_HEIGHT = 20;   //滑动条的高度
	
	private ColorSliderBar redSlider = null;
	private ColorSliderBar greenSlider = null;
	private ColorSliderBar blueSlider = null;
	private JPanel colorArea = null;
	private JPanel changePanel = null;
	
	private BaseWindow.GraphicsColorListener mGraphicsColorListener = null;
	
	private int operateType;   //操作类型，0表示操作滑块，1表示操作文本框
	
	public ColorPickerPanel() {
		
		this.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH, COLOR_PANEL_HEIGHT));
		
		addRecommendColor();
		addColorArea();
		addSliderBars();
	}
	
	/**
	 * 添加推荐颜色
	 * */
	private void addRecommendColor() {
		
		int rectWidth = 20;
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		for (int i = 0; i < PixelColor.RECOMMEND_LISTS.length; i++) {
	    	ColorRect colorRect = new ColorRect(rectWidth, PixelColor.turnToColor(PixelColor.RECOMMEND_LISTS[i][0]),
	    			new ColorRect.ColorRectLinster() {
						
						@Override
						public void getColor(Color color) {
							updateChangePanel(color);
							updateColorSliderLoc(color);
							updateColorArea();
						}
					});
	    	c.fill = GridBagConstraints.HORIZONTAL;
	    	c.gridx = i;
	    	c.gridy = 0;
			mainPanel.add(colorRect, c);
	    }
		
		changePanel = new JPanel();
		changePanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		
		for (int i = 0; i < PixelColor.RECOMMEND_LISTS[0].length-1; i++) {
	    	ColorRect colorRect = new ColorRect(rectWidth, PixelColor.turnToColor(PixelColor.RECOMMEND_LISTS[0][i+1]),
	    			new ColorRect.ColorRectLinster() {
						
						@Override
						public void getColor(Color color) {
							updateColorSliderLoc(color);
							updateColorArea();
						}
					});
	    	c1.fill = GridBagConstraints.HORIZONTAL;
	    	c1.gridx = i;
	    	c1.gridy = 0;
	    	changePanel.add(colorRect, c1);
	    }
		
		this.add(mainPanel);
		this.add(changePanel);
	}
	
	/**
	 * 添加显色面板
	 * */
	private void addColorArea() {
		
		//显色面板
		colorArea = new JPanel();
		colorArea.setPreferredSize(new Dimension(100, 100));
		colorArea.setBackground(new Color(0, 0, 0));
		
		this.add(colorArea);
	}
	
	/**
	 * 添加滑动条
	 * */
	private void addSliderBars() {
		
		//红色滑动条
		redSlider = new ColorSliderBar();
		redSlider.setLabelText("R");
		redSlider.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH - COLOR_PANEL_X_BORDER, 
				COLOR_SLIDER_BAR_HEIGHT));
		bindLinstersToSliderBar(redSlider);
		
		//绿色滑动条
		greenSlider = new ColorSliderBar();
		greenSlider.setLabelText("G");
		greenSlider.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH - COLOR_PANEL_X_BORDER, 
				COLOR_SLIDER_BAR_HEIGHT));
		bindLinstersToSliderBar(greenSlider);
		
		//蓝色滑动条
		blueSlider = new ColorSliderBar();
		blueSlider.setLabelText("B");
		blueSlider.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH - COLOR_PANEL_X_BORDER, 
				COLOR_SLIDER_BAR_HEIGHT));
		bindLinstersToSliderBar(blueSlider);
		
		this.add(redSlider);
		this.add(greenSlider);
		this.add(blueSlider);
	}
	
	/**
	 * 为颜色滑动条绑定多个监听器
	 * */
	private void bindLinstersToSliderBar(final ColorSliderBar colorSliderBar) {
		
		DocumentListener mDocumentListener = new DocumentListener() {   
			
			@Override
			public void removeUpdate(DocumentEvent event) {

				if (operateType == 1) {
					int value = "".equals(colorSliderBar.getTextFieldString()) ? 
							0 : Integer.parseInt(colorSliderBar.getTextFieldString());
					colorSliderBar.setSliderValue(value);
					updateColorArea();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent event) {
				
				if (operateType == 1) {
					colorSliderBar.setSliderValue(Integer.parseInt(colorSliderBar.getTextFieldString()));
					updateColorArea();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent event) {
			}
		};
		
		FocusListener textFieldFocusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				operateType = 0;
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				operateType = 1;
			}
		};
		
		ChangeListener mChangeListener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent event) {
				
				if (operateType == 0) {
					int value = ((JSlider)event.getSource()).getValue();
					colorSliderBar.setTextFieldString(""+value);
					updateColorArea();
				}
			}
		};
		
		FocusListener silderFocusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				operateType = 1;
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				operateType = 0;
			}
		};
		
		colorSliderBar.setTextFieldListener(mDocumentListener, textFieldFocusListener);
		colorSliderBar.setSilderListender(mChangeListener, silderFocusListener);
	}
	
	/**
	 * 设置颜色获取监听器
	 * */
	public void setGraphicsColorListener(BaseWindow.GraphicsColorListener linster) {
		
		this.mGraphicsColorListener = linster;
	}
	
	/**
	 * 更新颜色区域背景颜色
	 * */
	private void updateColorArea() {
		
		Color mColor = new Color(redSlider.getSliderValue(), greenSlider.getSliderValue(),
				blueSlider.getSliderValue());
		colorArea.setBackground(mColor);
		mGraphicsColorListener.getColor(mColor);
	}
	
	/**
	 * 更新滑动条的位置
	 * */
	private void updateColorSliderLoc(Color color) {
		
		redSlider.setSliderValue(color.getRed());
		greenSlider.setSliderValue(color.getGreen());
		blueSlider.setSliderValue(color.getBlue());
	}
	
	/**
	 * 更新渐变颜色条
	 * */
	private void updateChangePanel(Color color) {
		
		changePanel.removeAll();
		
		int colorNum = 0;
		String colorCode = PixelColor.turnToCode(color);
		for (int i = 0; i < PixelColor.RECOMMEND_LISTS.length; i++) {
			if (colorCode.equals(PixelColor.RECOMMEND_LISTS[i][0])) {
				colorNum = i;
				break;
			}
		}
		int rectWidth = 20;
		changePanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		
		for (int i = 0; i < PixelColor.RECOMMEND_LISTS[0].length-1; i++) {
	    	ColorRect colorRect = new ColorRect(rectWidth, PixelColor.turnToColor(PixelColor.RECOMMEND_LISTS[colorNum][i+1]),
	    			new ColorRect.ColorRectLinster() {
						
						@Override
						public void getColor(Color color) {
							updateColorSliderLoc(color);
							updateColorArea();
						}
					});
	    	c1.fill = GridBagConstraints.HORIZONTAL;
	    	c1.gridx = i;
	    	c1.gridy = 0;
	    	changePanel.add(colorRect, c1);
	    }
		changePanel.updateUI();
	}
}
