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
	private static panel bgp;//�Զ��屳����
	private static ImageIcon image;//����ͼƬ
	public Begin() {
		super("ͼ�����ϵͳ��½");
		this.setIconImage(new ImageIcon(".\\image\\login\\tubiao.jpg").getImage());

		jf = new JFrame();

		jl=new JLabel("��ѡ���¼��ʽ");
		jl.setBounds(120, 200, 200, 50);//���ô�С
		jl.setFont(new Font("����", 1, 25));//��������ʹ�С
		//jl.setForeground(Color.WHITE);


		jb1=new JButton();
		jb1.setBounds(150, 260, 120, 50);
		image = new ImageIcon(".\\image\\login\\bt-����.png");//��Ӱ�ťͼƬ
		jb1.setIcon(image);		
		//���ð�ť����͸��
		Color c = new Color(0,0,255);//��Ӱ��ɫ���������ֵ,ֻ��ռλ���á�
		jb1.setBackground(c);
		jb1.setOpaque(false); //���ñ���͸��
		jb1.setBorderPainted(false);

		jb2=new JButton("�� �� Ա");
		jb2.setBounds(155, 330, 120, 50);
		image = new ImageIcon(".\\image\\login\\bt-����Ա.png");//��Ӱ�ťͼƬ
		jb2.setIcon(image);	
		jb2.setBackground(c);
		jb2.setOpaque(false); //���ñ���͸��
		jb2.setBorderPainted(false);


		container=this.getContentPane();//����һ������  
		this.setLayout(null);  	  //�������κβ��ַ�ʽ��      	       

		//���»��Ʊ���ͼƬ  
		bgp=new panel((new ImageIcon(".\\image\\login\\begin1.png")).getImage()); //������һ��Image����,	        
		bgp.setBounds(0,0,600,420);  
		bgp.setLayout(null);

		//��ӱ�������������
		container.add(bgp);  
		//��ӿؼ��뱳�����
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
