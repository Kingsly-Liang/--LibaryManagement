package setButtonBG;

import java.awt.Color;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class setButtonBackground {

	//private static JButton jb;
	private static ImageIcon image;
	public setButtonBackground() {
		//this.jb=jb;
		//jb=new JButton();
	}
	public void setBtBackground(Object jb,String str) {
		//jb=new JButton(jbname);
		//((JButton) jb).setBounds(x, y, width, heigh);
		image = new ImageIcon(str);//添加按钮图片
        ((JButton) jb).setIcon(image);		
        //设置按钮背景透明
        Color c = new Color(0,0,255);//背影颜色随便设任意值,只起占位作用。
        ((JButton) jb).setBackground(c);
        ((JComponent) jb).setOpaque(false); //设置背景透明
        ((AbstractButton) jb).setBorderPainted(false);
	}
}
