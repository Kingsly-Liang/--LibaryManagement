package frame;
import java.awt.*;
import javax.swing.*;
 
class panel extends JPanel
{
	
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image im;     
		public panel(Image im) {
			this.im=im;  
	        this.setOpaque(true);                    //���ÿؼ���͸��,����false,��ô����͸��
		}

		//Draw the background again,�̳���Jpanle,��Swing�ؼ���Ҫ�̳�ʵ�ֵķ���,������AWT�е�Paint()
	    public void paintComponent(Graphics g)       //��ͼ��,����ɼ�������Java �� java-Graphics 
	    {  
	    	if (im!=null) {
	        super.paintComponents(g);  
	        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  //����ָ��ͼ���е�ǰ���õ�ͼ��ͼ������Ͻ�λ�ڸ�ͼ������������ռ�� (x, y)��ͼ���е�͸�����ز�Ӱ��ô��Ѵ��ڵ�����
	         //  System.out.println("ok");
	    	}
	    	
	    }  
}

