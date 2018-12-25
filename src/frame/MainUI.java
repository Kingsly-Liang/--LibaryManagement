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
	
	private static JButton out_book;  //ͼ�����
	private static JButton buy_book;  //ͼ�鹺��
	private static JButton modify_book; //ͼ����Ϣ�޸�
	
	private static JButton borrow_book;//ͼ�����
	private static JButton query_reader;//���߲�ѯ
	private static JButton query_borrowing;  //���Ĳ�ѯ
	private static JButton query_borrowing_histy;  //������ʷ��ѯ
	
	private static JButton addUser;  //����û�
	private static JButton change_password;  //�޸�����
	private static JButton CardTypeModify;  //����֤״̬�޸�
	private static JButton payFine;//���ɷ���
	private static JButton addOperator;//���ɷ���
	
	private static JLabel jl_book;//���ͼ�����Ĺ���
   
	
	private static panel bgp;//�Զ��屳����
	Container con;//����
	
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public static void addIFrame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}
	
	//���췽��
	MainUI(){
		//���ô���
		super("ͼ��ݹ���ϵͳ");
		this.setIconImage(new ImageIcon(".\\image\\login\\tubiao.jpg").getImage());
		
		//���ð�ť����͸���Ķ���
		setButtonBackground setbt=new setButtonBackground();
		
	//ͼ�����ģ��

		//���ù���ͼ�����
		jl_book=new JLabel();
		jl_book.setBounds(0, 0, 800, 80);
	   // jl_book_manage.setVisible(false);
		//����ͼ�갴ť		
		//����ͼ�鰴ť
		 buy_book=new JButton();
		 buy_book.setBounds(5, 7, 65, 50);			
         setbt.setBtBackground(buy_book, ".\\image\\�������.png");//���ð�ť����ͼƬ
         buy_book.setToolTipText("�������");
		 jl_book.add(buy_book);
		 //ͼ����ⰴť
		 out_book=new JButton();
		 out_book.setBounds(78, 7, 65, 50);		
         setbt.setBtBackground(out_book, ".\\image\\ͼ�����.png");
         out_book.setToolTipText("ͼ�����");
		 jl_book.add(out_book);
		
		 //ͼ����Ϣ�޸�
		 modify_book=new JButton();
		 modify_book.setBounds(148, 7, 65, 50);		
         setbt.setBtBackground(modify_book, ".\\image\\ͼ����Ϣ�޸�.png");
         modify_book.setToolTipText("ͼ����Ϣ�޸�");
		 jl_book.add(modify_book);
		
	//ͼ����ͨ��ť		
		
		//ͼ����İ�ť
		 borrow_book=new JButton();
		 borrow_book.setBounds(218, 7, 65, 50);			
        setbt.setBtBackground(borrow_book, ".\\image\\ͼ�����.png");//���ð�ť����ͼƬ
		 jl_book.add(borrow_book);
		 borrow_book.setToolTipText("ͼ�����");		 
		
	//ͳ�Ʋ�ѯģ��
		

		//���߲�ѯ��ť
		query_reader=new JButton();
		query_reader.setBounds(288, 7, 65, 50);			
        setbt.setBtBackground(query_reader, ".\\image\\���߲�ѯ.png");//���ð�ť����ͼƬ
        jl_book.add(query_reader);
        query_reader.setToolTipText("���߲�ѯ");
		 //���Ĳ�ѯ��ť
		 query_borrowing=new JButton();
		 query_borrowing.setBounds(358, 7, 65, 50);		
        setbt.setBtBackground(query_borrowing, ".\\image\\���Ĳ�ѯ.png");
        jl_book.add(query_borrowing);
        query_borrowing.setToolTipText("���Ĳ�ѯ");
		 //������ʷ��ѯ
        query_borrowing_histy=new JButton();
        query_borrowing_histy.setBounds(428,7, 65, 50);		
        setbt.setBtBackground(query_borrowing_histy, ".\\image\\������ʷ��ѯ.png");
        jl_book.add(query_borrowing_histy);
        query_borrowing_histy.setToolTipText("������ʷ��ѯ");
		
	//ϵͳ����ģ��
	  

       //����û���ť
        addUser=new JButton();
        addUser.setBounds(498, 7, 65, 50);			
        setbt.setBtBackground(addUser, ".\\image\\����û�.png");//���ð�ť����ͼƬ
        jl_book.add(addUser);
        addUser.setToolTipText("����û�");    
      	//�޸����밴ť
              change_password=new JButton();
              change_password.setBounds(568, 7, 65, 50);		
              setbt.setBtBackground(change_password, ".\\image\\�޸�����.png");
              jl_book.add(change_password);
              change_password.setToolTipText("�޸�����");    	
      	//��ͣ����֤��ť
              CardTypeModify=new JButton();
              CardTypeModify.setBounds(638, 7, 65, 50);		
              setbt.setBtBackground(CardTypeModify, ".\\image\\��ͣ����֤.png");
              jl_book.add(CardTypeModify);
              CardTypeModify.setToolTipText("����֤״̬�޸�");
                      
       //���ɷ��ť
              payFine=new JButton();
              payFine.setBounds(708, 7, 65, 50);		
              setbt.setBtBackground(payFine, ".\\image\\���ɷ���.png");
              jl_book.add(payFine);
              payFine.setToolTipText("���ɷ��� ");
        
              con=this.getContentPane();//����һ������  
     	     this.setLayout(null);  	  //�������κβ��ַ�ʽ��      	       

     	        //���»��Ʊ���ͼƬ  
     	     bgp=new panel((new ImageIcon(".\\image\\admin_UI.png")).getImage()); //������һ��Image����,	        
     	     bgp.setBounds(0,0,1000,700);  
     	     bgp.setLayout(null);
     	        
     	     //��ӱ�������������
     	     con.add(bgp);  
     	     //��ӿؼ��뱳�����    	
     	     bgp.add(jl_book);
     	
     	
     	
     // �����˵������
     		JMenuBar menuBar = this.createMenu();
     		this.setJMenuBar(menuBar);
     		
     	
		
     	jl=new JLabel();
     	bgp.add(jl);
     	this.setSize(1000, 700);
		this.setVisible(true);
		this.getDefaultCloseOperation();
		this.setLocation(200,200);
		
        //��ť�¼�
		//���ͼ���¼�
		       buy_book.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						new BookAddFrame();
					}
				});
		//ͼ������¼�
		       out_book.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BookDeleteFrame();
							}
						});
		//ͼ����Ϣ�޸��¼�
		       modify_book.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BookModifyFrame();
							}
						});
		//ͼ������¼�
		       borrow_book.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BookBorrowFrame();
							}
						});
		//���߲�ѯ�¼�
		       query_reader.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new ReaderQueryFrame();
							}
						});
		//���Ĳ�ѯ�¼�
		       query_borrowing.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BorrowingQueryFrame();
							}
						});
		//������ʷ��ѯ�¼�
		       query_borrowing_histy.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new BorrowHistoryQueryFrame();
							}
						});
		//����û��¼�
		       addUser.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new ReaderAddFrame();
							}
						});
		//�޸������¼�
		       change_password.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new ReaderChangePasswdFrame();
							}
						});
		//����֤״̬�޸��¼�
		       CardTypeModify.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								new CardTypeFrame().setSize(600, 450);	
							}
						});
				
	}
	
	// �����˵���
		private JMenuBar createMenu() {
			JMenuBar menuBar = new JMenuBar();
			
			//����ͼ�����
			JMenu bookMenu = new JMenu("ͼ�����(B)");
			bookMenu.setMnemonic(KeyEvent.VK_B);
			bookMenu.add(MenuActions.BOOKTYPE_add);
			bookMenu.add(MenuActions.BOOK_add);
			bookMenu.add(MenuActions.BOOK_out);
			bookMenu.add(MenuActions.BOOK_modify);
			bookMenu.add(MenuActions.BOOK_setNumber);
			
			//����ͼ����ͨ����
			JMenu transferMenu = new JMenu("ͼ����ͨ����(R)");
			transferMenu.setMnemonic(KeyEvent.VK_R);
			transferMenu.add(MenuActions.BOOK_borrow);
			transferMenu.add(MenuActions.BOOK_return);
			transferMenu.add(MenuActions.BOOK_transfer);
			transferMenu.add(MenuActions.BOOK_borrow_return);
			
			//����ͳ�Ʋ�ѯ����
			JMenu queryMenu = new JMenu("ͳ�Ʋ�ѯ����(S)");
			queryMenu.setMnemonic(KeyEvent.VK_S);
			queryMenu.add(MenuActions.query_reader);
			queryMenu.add(MenuActions.query_borrowing);
			queryMenu.add(MenuActions.query_borrowing_histy);
			queryMenu.add(MenuActions.query_fine_histy);
			queryMenu.add(MenuActions.query_password);
			queryMenu.add(MenuActions.statistics_reader);
			queryMenu.add(MenuActions.statistics_borrow);
			
			//����ϵͳ����
			JMenu systemMenu= new JMenu("ϵͳ����(K)");
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
