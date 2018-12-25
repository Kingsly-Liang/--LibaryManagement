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

	private static JButton change_password;  //�޸�����
	private static JButton book_search;  //��ʧ����֤
	private static JButton book_appointment;  //��ͣ����֤

	private static JLabel jl_book;//���ͼ�����Ĺ���


	private static panel bgp;//�Զ��屳����
	Container con;//����

	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

	public static void addIFrame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}

	//���췽��
	public ReaderUI(){
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

		//�޸����밴ť
		change_password=new JButton();
		change_password.setBounds(148, 7, 65, 50);		
		setbt.setBtBackground(change_password, ".\\image\\�޸�����.png");
		jl_book.add(change_password);
		change_password.setToolTipText("�޸�����");
		
		
		//��ѯͼ�鰴ť
		book_search=new JButton();
		book_search.setBounds(5, 7, 65, 50);		
		setbt.setBtBackground(book_search, ".\\image\\ͼ�����.png");
		jl_book.add(book_search);
		book_search.setToolTipText("��ѯͼ��");
		
		
		//��ͣ����֤��ť
		book_appointment=new JButton();
		book_appointment.setBounds(78, 7, 65, 50);		
		setbt.setBtBackground(book_appointment, ".\\image\\���߲�ѯ.png");
		jl_book.add(book_appointment);
		book_appointment.setToolTipText("ͼ��ԤԼ");



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
		this.setLocation(60,100);

		//�����Ӧ���ܰ�ť�ͳ�����Ӧ�Ĺ���ͼ��
		//ͼ�����ť
		ActionListener button_book_manage=new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�����ť����Ӧ�Ĺ���ͼ����֣���������ͼ����ʧ
				jl_book.setVisible(true);
			}			
		};


	}

	// �����˵���
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		//����ϵͳ����
		JMenu systemMenu= new JMenu("�ҵĹ���(K)");
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
