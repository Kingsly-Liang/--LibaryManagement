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
		image = new ImageIcon(str);//��Ӱ�ťͼƬ
        ((JButton) jb).setIcon(image);		
        //���ð�ť����͸��
        Color c = new Color(0,0,255);//��Ӱ��ɫ���������ֵ,ֻ��ռλ���á�
        ((JButton) jb).setBackground(c);
        ((JComponent) jb).setOpaque(false); //���ñ���͸��
        ((AbstractButton) jb).setBorderPainted(false);
	}
}
