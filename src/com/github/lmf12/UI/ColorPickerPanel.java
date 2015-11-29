package com.github.lmf12.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	
	private BaseWindow.GraphicsColorListener mGraphicsColorListener = null;
	
	private int operateType;   //操作类型，0表示操作滑块，1表示操作文本框
	
	public ColorPickerPanel() {
		
		this.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH, COLOR_PANEL_HEIGHT));
		
		colorArea = new JPanel();
		colorArea.setPreferredSize(new Dimension(150, 150));
		colorArea.setBackground(new Color(0, 0, 0));
		
		redSlider = new ColorSliderBar();
		redSlider.setLabelText("R");
		redSlider.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH - COLOR_PANEL_X_BORDER, 
				COLOR_SLIDER_BAR_HEIGHT));
		bindLinstersToSliderBar(redSlider);
		
		greenSlider = new ColorSliderBar();
		greenSlider.setLabelText("G");
		greenSlider.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH - COLOR_PANEL_X_BORDER, 
				COLOR_SLIDER_BAR_HEIGHT));
		bindLinstersToSliderBar(greenSlider);
		
		blueSlider = new ColorSliderBar();
		blueSlider.setLabelText("B");
		blueSlider.setPreferredSize(new Dimension(COLOR_PANEL_WIDTH - COLOR_PANEL_X_BORDER, 
				COLOR_SLIDER_BAR_HEIGHT));
		bindLinstersToSliderBar(blueSlider);
		
		this.add(colorArea);
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
}
