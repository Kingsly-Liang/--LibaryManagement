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
	private static int count=0;   //������
	private static JButton login;//��½��ť
	private static JButton reset;//���ð�ť
	private static JFrame jf_1;//��½�Ŀ��
	private static JTextField cardid;//�û���
	private static JPasswordField cardpassword;//����
	private static JLabel jl_admin;
	private static JLabel jl_password;
	private static panel bgp;//�Զ��屳����
	private static ImageIcon image;//����ͼƬ
	private static Container container;//��������
	
	DAO dao;

	public AdminLogin (String str){//��ʼ����½����
		super("����Ա��½");
		this.setIconImage(new ImageIcon(".\\image\\login\\tubiao.jpg").getImage());
		Font font =new Font("����", Font.PLAIN, 20);//��������
		jf_1=new JFrame("���½");				

		jl_admin=new JLabel("����֤��:");
		jl_admin.setBounds(400, 170, 100, 40);
		jl_admin.setFont(font);		

		jl_password=new JLabel("����:");
		jl_password.setBounds(420, 240, 70, 40);
		jl_password.setFont(font);


		login=new JButton("��¼");         //���ĳ�loginButton
		login.setBounds(520, 290, 80, 40);
		login.setFont(font);			

		Color c = new Color(0,0,255);//��Ӱ��ɫ���������ֵ,ֻ��ռλ���á�
		login.setBackground(c);
		login.setOpaque(false); //���ñ���͸��
		login.setBorderPainted(false);

		reset=new JButton("����");
		reset.setBounds(620, 290, 80, 40);
		reset.setFont(font);

		reset.setBackground(c);
		reset.setOpaque(false); //���ñ���͸��
		reset.setBorderPainted(false);

		//�����ı���
		cardid=new JTextField();
		cardid.setBounds(500, 170, 250, 40);
		cardid.setFont(font);
		cardid.setOpaque(true);


		cardpassword=new JPasswordField();//���������
		cardpassword.setBounds(500, 240, 250, 40);
		cardpassword.setFont(font);
		cardpassword.setEchoChar('*');
		cardpassword.setOpaque(true);

		//�������κβ��ַ�ʽ��  
		container=this.getContentPane();
		this.setLayout(null);  



		//���»��Ʊ���ͼƬ  
		bgp=new panel((new ImageIcon(str)).getImage()); //������һ��Image����,        
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
			String password=String.valueOf(passwordtemp); //��char����ת��Ϊstring����

			if(dao.checkadmin(id, password))
			{
				//JOptionPane.showMessageDialog(null, "��¼�ɹ���");
				new MainUI();
				this.dispose();//���ٵ�ǰ����
			}
			else {
				JOptionPane.showMessageDialog(null, "��������˺Ż������д����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
				count++;
				if(count==3){
					JOptionPane.showMessageDialog(null, "�������3�Σ���","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}

		}else if(e.getSource()==reset){
			cardid.setText(null);
			cardpassword.setText(null);
		}
	}
	
}


