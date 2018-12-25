package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import menu.ReaderMenuActions;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import setButtonBG.setButtonBackground;
//import menu.MenuActions;


public class ReaderUI extends JFrame{

	private static JFrame jf;//
	private static JLabel jl;

	private static JButton change_password;  //修改密码
	private static JButton book_search;  //挂失借阅证
	private static JButton book_appointment;  //暂停借阅证

	private static JLabel jl_book;//存放图书管理的功能


	private static panel bgp;//自定义背景类
	Container con;//容器

	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

	public static void addIFrame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}

	//构造方法
	public ReaderUI(){
		//设置窗体
		super("图书馆管理系统");
		this.setIconImage(new ImageIcon(".\\image\\login\\tubiao.jpg").getImage());

		//设置按钮背景透明的对象
		setButtonBackground setbt=new setButtonBackground();

		//图书管理模块

		//常用功能图标界面
		jl_book=new JLabel();
		jl_book.setBounds(0, 0, 800, 80);
		// jl_book_manage.setVisible(false);
		//具体图标按钮		
		//购进图书按钮

		//修改密码按钮
		change_password=new JButton();
		change_password.setBounds(148, 7, 65, 50);		
		setbt.setBtBackground(change_password, ".\\image\\修改密码.png");
		jl_book.add(change_password);
		change_password.setToolTipText("修改密码");
		
		
		//查询图书按钮
		book_search=new JButton();
		book_search.setBounds(5, 7, 65, 50);		
		setbt.setBtBackground(book_search, ".\\image\\图书借阅.png");
		jl_book.add(book_search);
		book_search.setToolTipText("查询图书");
		
		
		//暂停借阅证按钮
		book_appointment=new JButton();
		book_appointment.setBounds(78, 7, 65, 50);		
		setbt.setBtBackground(book_appointment, ".\\image\\读者查询.png");
		jl_book.add(book_appointment);
		book_appointment.setToolTipText("图书预约");



		con=this.getContentPane();//创建一个容器  
		this.setLayout(null);  	  //不采用任何布局方式。      	       

		//重新绘制背景图片  
		bgp=new panel((new ImageIcon(".\\image\\admin_UI.png")).getImage()); //参数是一个Image对象,	        
		bgp.setBounds(0,0,1000,700);  
		bgp.setLayout(null);

		//添加背景面板进入容器
		con.add(bgp);  
		//添加控件入背景面板    	
		bgp.add(jl_book);



		// 创建菜单并添加
		JMenuBar menuBar = this.createMenu();
		this.setJMenuBar(menuBar);



		jl=new JLabel();
		bgp.add(jl);
		this.setSize(1000, 700);
		this.setVisible(true);
		this.getDefaultCloseOperation();
		this.setLocation(60,100);

		//点击相应功能按钮就出现相应的功能图标
		//图书管理按钮
		ActionListener button_book_manage=new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//点击按钮后相应的功能图标出现，其他功能图标消失
				jl_book.setVisible(true);
			}			
		};


	}

	// 创建菜单栏
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		//创建系统管理
		JMenu systemMenu= new JMenu("我的功能(K)");
		systemMenu.setMnemonic(KeyEvent.VK_S);
		systemMenu.add(ReaderMenuActions.book_search);
		systemMenu.add(ReaderMenuActions.book_appointment);
		systemMenu.add(ReaderMenuActions.change_password);
		

		menuBar.add(systemMenu);

		return menuBar;
	}


	public static void main(String[] args) {
		new ReaderUI();

	}
}
