package com.github.lmf12.UI;

import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.github.lmf12.util.LimitedDocument;

/**
 * 颜色滑动条，放置在颜色面板中
 * */
public class ColorSliderBar extends JPanel {
	
	private static final int Label_WIDTH = 20;   //文本标签宽度
	private static final int TEXT_FIELD_WIDTH = 30;   //输入框宽度
	
	private JSlider mSlider = null;
	private JLabel mLabel = null;
	private JTextField mTextField = null;
	
	public ColorSliderBar() {
		
		mSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		
		mLabel = new JLabel("d");   //默认文本
		mLabel.setFont(new java.awt.Font("Dialog", 0, 15));
		
		mTextField = new JTextField();
		LimitedDocument ld = new LimitedDocument(3);    //限制输入数字和字数
		ld.setAllowChar("0123456789");
		mTextField.setDocument(ld);
		mTextField.setText("0");
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(mLabel);
		this.add(mSlider);
		this.add(mTextField);
	}
	
	/**
	 * 设置标签文本
	 * */
	public void setLabelText(String text) {
		
		mLabel.setText(text);
	}
	
	/**
	 * 为输入框添加监听器
	 * */
	public void setTextFieldListener(DocumentListener documentListener, FocusListener focusListener) {
		
		mTextField.getDocument().addDocumentListener(documentListener);
		mTextField.addFocusListener(focusListener);
	}
	
	/**
	 * 为滑块添加监听器
	 * */
	public void setSilderListender(ChangeListener changeListener, FocusListener focusListener) {
		
		mSlider.addChangeListener(changeListener);
		mSlider.addFocusListener(focusListener);
	}
	
	/**
	 * 获取输入框文本
	 * */
	public String getTextFieldString() {
		
		return mTextField.getText();
	}
	
	/**
	 * 设置输入框文本
	 * */
	public void setTextFieldString(String text) {
		
		mTextField.setText(text);
	}
	
	/**
	 * 设置滑块位置
	 * */
	public void setSliderValue(int value) {
		
		mSlider.setValue(value);
	}
	
	/**
	 * 获取滑块位置
	 * */
	public int getSliderValue() {
		
	 	return mSlider.getValue();
	}
	
	/**
	 * 重写设置大小的方法，使得父容器大小调整时，子容器对应调整
	 * */
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		
		super.setPreferredSize(preferredSize);
		
		mSlider.setPreferredSize(new Dimension(
				preferredSize.width - Label_WIDTH - TEXT_FIELD_WIDTH,preferredSize.height));
		mLabel.setPreferredSize(new Dimension(Label_WIDTH ,preferredSize.height));
		mTextField.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH ,preferredSize.height));
	}
}
