package com.github.lmf12.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/** 
 * JTextField jTextField = new JTextField(); 
 * 使用时： 
 * LimitedDocument ld = new LimitedDocument(8);//参数为能输入的最大长度 
 * ld.setAllowChar("0123456789");//只能输入的字符 
 * jTextField.setDocument(ld);//运用到文本框中 
 *  
 * 不足：通过 复制粘贴 的方式可以写入英文字母 
 * @author WJ 
 *  
 */  
@SuppressWarnings("serial")  
public class LimitedDocument extends PlainDocument {  
  
    private int _maxLength  = -1;  
    private String _allowCharAsString = null;  
  
    public LimitedDocument(){
    	
        super();  
    }  
  
    public LimitedDocument(int maxLength){
    	
        super();  
        this._maxLength = maxLength;  
    }  
  
    public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException{
    	
        if(str == null) {  
            return;  
        }  
        if(_allowCharAsString != null && str.length() == 1) {
            if(_allowCharAsString.indexOf(str) == -1){  
                return;  
            }  
        }  
        char[] charVal = str.toCharArray();  
        String strOldValue = getText(0, getLength());  
        byte[] tmp = strOldValue.getBytes();  
        if(_maxLength != -1 && (tmp.length + charVal.length > _maxLength)){  
            return;  
        }  
        super.insertString(offset, str, attrSet);  
    }  
  
    public void setAllowChar(String str) {
    	
        _allowCharAsString = str;  
    }  
}  