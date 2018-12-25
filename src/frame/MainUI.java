package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import setButtonBG.setButtonBackground;
import menu.MenuActions;

public class MainUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame jf;//
	private static JLabel jl;
	
	private static JButton out_book;  //图书出库
	private static JButton buy_book;  //图书购进
	private static JButton modify_book; //图书信息修改
	
	private static JButton borrow_book;//图书借阅
	private static JButton query_reader;//读者查询
	private static JButton query_borrowing;  //借阅查询
	private static JButton query_borrowing_histy;  //借阅历史查询
	
	private static JButton addUser;  //添加用户
	private static JButton change_password;  //修改密码
	private static JButton CardTypeModify;  //借阅证状态修改
	private static JButton payFine;//交纳罚金
	private static JButton addOperator;//交纳罚金
	
	private static JLabel jl_book;//存放图书管理的功能
   
	
	private static panel bgp;//自定义背景类
	Container con;//容器
	
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public static void addIFrame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}
	
	//构造方法
	MainUI(){
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
		 buy_book=new JButton();
		 buy_book.setBounds(5, 7, 65, 50);			
         setbt.setBtBackground(buy_book, ".\\image\\新书入库.png");//设置按钮背景图片
         buy_book.setToolTipText("新书入库");
		 jl_book.add(buy_book);
		 //图书出库按钮
		 out_book=new JButton();
		 out_book.setBounds(78, 7, 65, 50);		
         setbt.setBtBackground(out_book, ".\\image\\图书出库.png");
         out_book.setToolTipText("图书出库");
		 jl_book.add(out_book);
		
		 //图书信息修改
		 modify_book=new JButton();
		 modify_book.setBounds(148, 7, 65, 50);		
         setbt.setBtBackground(modify_book, ".\\image\\图书信息修改.png");
         modify_book.setToolTipText("图书信息修改");
		 jl_book.add(modify_book);
		
	//图书流通按钮		
		
		//图书借阅按钮
		 borrow_book=new JButton();
		 borrow_book.setBounds(218, 7, 65, 50);			
        setbt.setBtBackground(borrow_book, ".\\image\\图书借阅.png");//设置按钮背景图片
		 jl_book.add(borrow_book);
		 borrow_book.setToolTipText("图书借阅");		 
		
	//统计查询模块
		

		//读者查询按钮
		query_reader=new JButton();
		query_reader.setBounds(288, 7, 65, 50);			
        setbt.setBtBackground(query_reader, ".\\image\\读者查询.png");//设置按钮背景图片
        jl_book.add(query_reader);
        query_reader.setToolTipText("读者查询");
		 //借阅查询按钮
		 query_borrowing=new JButton();
		 query_borrowing.setBounds(358, 7, 65, 50);		
        setbt.setBtBackground(query_borrowing, ".\\image\\借阅查询.png");
        jl_book.add(query_borrowing);
        query_borrowing.setToolTipText("借阅查询");
		 //借阅历史查询
        query_borrowing_histy=new JButton();
        query_borrowing_histy.setBounds(428,7, 65, 50);		
        setbt.setBtBackground(query_borrowing_histy, ".\\image\\借阅历史查询.png");
        jl_book.add(query_borrowing_histy);
        query_borrowing_histy.setToolTipText("借阅历史查询");
		
	//系统管理模块
	  

       //添加用户按钮
        addUser=new JButton();
        addUser.setBounds(498, 7, 65, 50);			
        setbt.setBtBackground(addUser, ".\\image\\添加用户.png");//设置按钮背景图片
        jl_book.add(addUser);
        addUser.setToolTipText("添加用户");    
      	//修改密码按钮
              change_password=new JButton();
              change_password.setBounds(568, 7, 65, 50);		
              setbt.setBtBackground(change_password, ".\\image\\修改密码.png");
              jl_book.add(change_password);
              change_password.setToolTipText("修改密码");    	
      	//暂停借阅证按钮
              CardTypeModify=new JButton();
              CardTypeModify.setBounds(638, 7, 65, 50);		
              setbt.setBtBackground(CardTypeModify, ".\\image\\暂停借阅证.png");
              jl_book.add(CardTypeModify);
              CardTypeModify.setToolTipText("借阅证状态修改");
                      
       //交纳罚款按钮
              payFine=new JButton();
              payFine.setBounds(708, 7, 65, 50);		
              setbt.setBtBackground(payFine, ".\\image\\交纳罚款.png");
              jl_book.add(payFine);
              payFine.setToolTipText("交纳罚款 ");
        
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
		this.setLocation(200,200);
		
        //按钮事件
		//添加图书事件
		       buy_book.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						new BookAddFrame();
					}
				});
		//图书出库事件
		       out_book.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BookDeleteFrame();
							}
						});
		//图书信息修改事件
		       modify_book.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BookModifyFrame();
							}
						});
		//图书借阅事件
		       borrow_book.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BookBorrowFrame();
							}
						});
		//读者查询事件
		       query_reader.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new ReaderQueryFrame();
							}
						});
		//借阅查询事件
		       query_borrowing.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BorrowingQueryFrame();
							}
						});
		//借阅历史查询事件
		       query_borrowing_histy.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BorrowHistoryQueryFrame();
							}
						});
		//添加用户事件
		       addUser.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new ReaderAddFrame();
							}
						});
		//修改密码事件
		       change_password.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new ReaderChangePasswdFrame();
							}
						});
		//借阅证状态修改事件
		       CardTypeModify.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new CardTypeFrame().setSize(600, 450);	
							}
						});
				
	}
	
	// 创建菜单栏
		private JMenuBar createMenu() {
			JMenuBar menuBar = new JMenuBar();
			
			//创建图书管理
			JMenu bookMenu = new JMenu("图书管理(B)");
			bookMenu.setMnemonic(KeyEvent.VK_B);
			bookMenu.add(MenuActions.BOOKTYPE_add);
			bookMenu.add(MenuActions.BOOK_add);
			bookMenu.add(MenuActions.BOOK_out);
			bookMenu.add(MenuActions.BOOK_modify);
			bookMenu.add(MenuActions.BOOK_setNumber);
			
			//创建图书流通管理
			JMenu transferMenu = new JMenu("图书流通管理(R)");
			transferMenu.setMnemonic(KeyEvent.VK_R);
			transferMenu.add(MenuActions.BOOK_borrow);
			transferMenu.add(MenuActions.BOOK_return);
			transferMenu.add(MenuActions.BOOK_transfer);
			transferMenu.add(MenuActions.BOOK_borrow_return);
			
			//创建统计查询管理
			JMenu queryMenu = new JMenu("统计查询管理(S)");
			queryMenu.setMnemonic(KeyEvent.VK_S);
			queryMenu.add(MenuActions.query_reader);
			queryMenu.add(MenuActions.query_borrowing);
			queryMenu.add(MenuActions.query_borrowing_histy);
			queryMenu.add(MenuActions.query_fine_histy);
			queryMenu.add(MenuActions.query_password);
			queryMenu.add(MenuActions.statistics_reader);
			queryMenu.add(MenuActions.statistics_borrow);
			
			//创建系统管理
			JMenu systemMenu= new JMenu("系统管理(K)");
			systemMenu.setMnemonic(KeyEvent.VK_S);
			systemMenu.add(MenuActions.addUser);
			systemMenu.add(MenuActions.addOperator);
			systemMenu.add(MenuActions.change_password);
			systemMenu.add(MenuActions.changeCard);
			systemMenu.add(MenuActions.payFine);
			systemMenu.add(MenuActions.reFreshFineTable);
			
			menuBar.add(bookMenu);
			menuBar.add(transferMenu);
			menuBar.add(queryMenu);
			menuBar.add(systemMenu);
			
			return menuBar;
		}

		
	public static void main(String[] args) {
		new MainUI();
			}
}
