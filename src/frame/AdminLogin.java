package frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DAO;



public class AdminLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count=0;   //计数器
	private static JButton login;//登陆按钮
	private static JButton reset;//重置按钮
	private static JFrame jf_1;//登陆的框架
	private static JTextField cardid;//用户名
	private static JPasswordField cardpassword;//密码
	private static JLabel jl_admin;
	private static JLabel jl_password;
	private static panel bgp;//自定义背景类
	private static ImageIcon image;//创建图片
	private static Container container;//创建容器
	
	DAO dao;

	public AdminLogin (String str){//初始化登陆界面
		super("管理员登陆");
		this.setIconImage(new ImageIcon(".\\image\\login\\tubiao.jpg").getImage());
		Font font =new Font("黑体", Font.PLAIN, 20);//设置字体
		jf_1=new JFrame("请登陆");				

		jl_admin=new JLabel("借阅证号:");
		jl_admin.setBounds(400, 170, 100, 40);
		jl_admin.setFont(font);		

		jl_password=new JLabel("密码:");
		jl_password.setBounds(420, 240, 70, 40);
		jl_password.setFont(font);


		login=new JButton("登录");         //更改成loginButton
		login.setBounds(520, 290, 80, 40);
		login.setFont(font);			

		Color c = new Color(0,0,255);//背影颜色随便设任意值,只起占位作用。
		login.setBackground(c);
		login.setOpaque(false); //设置背景透明
		login.setBorderPainted(false);

		reset=new JButton("重置");
		reset.setBounds(620, 290, 80, 40);
		reset.setFont(font);

		reset.setBackground(c);
		reset.setOpaque(false); //设置背景透明
		reset.setBorderPainted(false);

		//加入文本框
		cardid=new JTextField();
		cardid.setBounds(500, 170, 250, 40);
		cardid.setFont(font);
		cardid.setOpaque(true);


		cardpassword=new JPasswordField();//密码输入框
		cardpassword.setBounds(500, 240, 250, 40);
		cardpassword.setFont(font);
		cardpassword.setEchoChar('*');
		cardpassword.setOpaque(true);

		//不采用任何布局方式。  
		container=this.getContentPane();
		this.setLayout(null);  



		//重新绘制背景图片  
		bgp=new panel((new ImageIcon(str)).getImage()); //参数是一个Image对象,        
		bgp.setBounds(0,0,790,400); 
		bgp.setLayout(null);
		container.add(bgp);                                 

		container.add(bgp);
		bgp.add(cardid);
		bgp.add(cardpassword);

		bgp.add(jl_admin);
		bgp.add(jl_password);
		bgp.add(login);
		bgp.add(reset);


		this.setSize(800,430);  
		this.setLocation(400,300);  
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		this.setVisible(true);

		login.addActionListener(this);
		reset.addActionListener(this);		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login){
			dao = new DAO();
			String id=cardid.getText();
			char[] passwordtemp=cardpassword.getPassword();
			String password=String.valueOf(passwordtemp); //将char数组转化为string类型

			if(dao.checkadmin(id, password))
			{
				//JOptionPane.showMessageDialog(null, "登录成功！");
				new MainUI();
				this.dispose();//销毁当前界面
			}
			else {
				JOptionPane.showMessageDialog(null, "你输入的账号或密码有错误，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);
				count++;
				if(count==3){
					JOptionPane.showMessageDialog(null, "输入错误3次！！","系统提示",JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}

		}else if(e.getSource()==reset){
			cardid.setText(null);
			cardpassword.setText(null);
		}
	}
	
}


