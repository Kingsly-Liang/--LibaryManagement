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
	        this.setOpaque(true);                    //设置控件不透明,若是false,那么就是透明
		}

		//Draw the background again,继承自Jpanle,是Swing控件需要继承实现的方法,而不是AWT中的Paint()
	    public void paintComponent(Graphics g)       //绘图类,详情可见博主的Java 下 java-Graphics 
	    {  
	    	if (im!=null) {
	        super.paintComponents(g);  
	        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  //绘制指定图像中当前可用的图像。图像的左上角位于该图形上下文坐标空间的 (x, y)。图像中的透明像素不影响该处已存在的像素
	         //  System.out.println("ok");
	    	}
	    	
	    }  
}

