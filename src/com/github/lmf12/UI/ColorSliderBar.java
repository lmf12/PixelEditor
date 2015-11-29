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
 * ��ɫ����������������ɫ�����
 * */
public class ColorSliderBar extends JPanel {
	
	private static final int Label_WIDTH = 20;   //�ı���ǩ���
	private static final int TEXT_FIELD_WIDTH = 30;   //�������
	
	private JSlider mSlider = null;
	private JLabel mLabel = null;
	private JTextField mTextField = null;
	
	public ColorSliderBar() {
		
		mSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		
		mLabel = new JLabel("d");   //Ĭ���ı�
		mLabel.setFont(new java.awt.Font("Dialog", 0, 15));
		
		mTextField = new JTextField();
		LimitedDocument ld = new LimitedDocument(3);    //�����������ֺ�����
		ld.setAllowChar("0123456789");
		mTextField.setDocument(ld);
		mTextField.setText("0");
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(mLabel);
		this.add(mSlider);
		this.add(mTextField);
	}
	
	/**
	 * ���ñ�ǩ�ı�
	 * */
	public void setLabelText(String text) {
		
		mLabel.setText(text);
	}
	
	/**
	 * Ϊ�������Ӽ�����
	 * */
	public void setTextFieldListener(DocumentListener documentListener, FocusListener focusListener) {
		
		mTextField.getDocument().addDocumentListener(documentListener);
		mTextField.addFocusListener(focusListener);
	}
	
	/**
	 * Ϊ������Ӽ�����
	 * */
	public void setSilderListender(ChangeListener changeListener, FocusListener focusListener) {
		
		mSlider.addChangeListener(changeListener);
		mSlider.addFocusListener(focusListener);
	}
	
	/**
	 * ��ȡ������ı�
	 * */
	public String getTextFieldString() {
		
		return mTextField.getText();
	}
	
	/**
	 * ����������ı�
	 * */
	public void setTextFieldString(String text) {
		
		mTextField.setText(text);
	}
	
	/**
	 * ���û���λ��
	 * */
	public void setSliderValue(int value) {
		
		mSlider.setValue(value);
	}
	
	/**
	 * ��ȡ����λ��
	 * */
	public int getSliderValue() {
		
	 	return mSlider.getValue();
	}
	
	/**
	 * ��д���ô�С�ķ�����ʹ�ø�������С����ʱ����������Ӧ����
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
