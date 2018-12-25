package frame;
import java.awt.*;
import java.awt.event.*;import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;

import frame.AdminLogin;
import frame.ReaderLogin;

@SuppressWarnings({ "unused", "serial" })
public class Begin extends JFrame implements ActionListener{

	private static JLabel jl;
	private static JLabel jlab;
	private static JButton jb1;
	private static JButton jb2;
	private static JFrame jf;
	private static Container container;
	private static panel bgp;//自定义背景类
	private static ImageIcon image;//创建图片
	public Begin() {
		super("图书管理系统登陆");
		this.setIconImage(new ImageIcon(".\\image\\login\\tubiao.jpg").getImage());

		jf = new JFrame();

		jl=new JLabel("请选择登录方式");
		jl.setBounds(120, 200, 200, 50);//设置大小
		jl.setFont(new Font("黑体", 1, 25));//设置字体和大小
		//jl.setForeground(Color.WHITE);


		jb1=new JButton();
		jb1.setBounds(150, 260, 120, 50);
		image = new ImageIcon(".\\image\\login\\bt-读者.png");//添加按钮图片
		jb1.setIcon(image);		
		//设置按钮背景透明
		Color c = new Color(0,0,255);//背影颜色随便设任意值,只起占位作用。
		jb1.setBackground(c);
		jb1.setOpaque(false); //设置背景透明
		jb1.setBorderPainted(false);

		jb2=new JButton("管 理 员");
		jb2.setBounds(155, 330, 120, 50);
		image = new ImageIcon(".\\image\\login\\bt-管理员.png");//添加按钮图片
		jb2.setIcon(image);	
		jb2.setBackground(c);
		jb2.setOpaque(false); //设置背景透明
		jb2.setBorderPainted(false);


		container=this.getContentPane();//创建一个容器  
		this.setLayout(null);  	  //不采用任何布局方式。      	       

		//重新绘制背景图片  
		bgp=new panel((new ImageIcon(".\\image\\login\\begin1.png")).getImage()); //参数是一个Image对象,	        
		bgp.setBounds(0,0,600,420);  
		bgp.setLayout(null);

		//添加背景面板进入容器
		container.add(bgp);  
		//添加控件入背景面板
		bgp.add(jb1); 
		bgp.add(jb2);
		bgp.add(jl);


		this.setSize(620,470);  
		this.setLocation(400,300);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setVisible(true);  


		jb1.addActionListener(this);
		jb2.addActionListener(this);	

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			this.dispose();
			System.out.println(e.getSource());
			ReaderLogin login=new ReaderLogin(".\\image\\login\\bgd1.png");
			//window.frame.setVisible(true);
			
		}else if(e.getSource()==jb2){
			AdminLogin login2=new AdminLogin(".\\image\\login\\bga1.png");
			this.dispose();
			}
	}

	public static void main(String[] args) {
		new Begin();
	}

}
