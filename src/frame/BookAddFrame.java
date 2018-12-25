//�������

package frame;

import java.awt.*;
import javax.swing.*;

import dao.DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BookAddFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame jf;
	private JLabel[] jlbookinfo;  //��ʾ��Ҫ��д��ͼ����Ϣ
	private JTextField[] jtbookwrite;  //��дͼ����Ϣ
	private JButton send;  //�ύ
	private JButton reset;  //����
	private Container con;
	private Choice booktype;
	private DAO dao;
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;
	
	public BookAddFrame() {
		super();
		jf=new JFrame();
		con=this.getContentPane();
		this.setLayout(null);//�����ò���
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		con.add(jl);
		
		//��ʾ��Ϣ
		String[] bookinfo={"ͼ�����","ͼ����:","ͼ������:","ͼ������:","������:","�� �� ��:","����ʱ��:","�۸�:","�������:","�ɽ�����:"};
		
		//ʵ��������
		jlbookinfo=new JLabel[10];
		jtbookwrite=new JTextField[10];
		
		//��ȡ�Ѿ����ڵ�ͼ�����
		booktype=new Choice();
				String sql="select typeName from booktype";
				dao=new DAO();
				ResultSet rs=dao.getRS(sql);		
				try {
					while(rs.next()) {
						
						booktype.add(rs.getString("typeName"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				booktype.setBounds(110, 60, 80, 30);
				
				add(booktype);
		String[] type = {"null"};

		
		for(int i=0;i<bookinfo.length;i++)
		{
			int labelx;  //ÿ����ǩ��x����
			int textX; //ÿ��������x����
			jlbookinfo[i]=new JLabel(bookinfo[i]);// ʵ����ÿһ��label
			if(i%2==0) {
				labelx=20;	
				textX=110;
			}else
			{labelx=280;
			textX=370;
			}
			
			jlbookinfo[i].setBounds(labelx, 60+40*(i/2), 100, 30);
			
			jtbookwrite[i]=new JTextField();
			jtbookwrite[i].setBounds(textX, 60+40*(i/2), 150, 30);
			add(jlbookinfo[i]);
			add(jtbookwrite[i]);
			}
		jtbookwrite[0].setVisible(false);
		//���Ͱ�ť
		send=new JButton("ȷ��");
		send.setBounds(180, 270, 60, 30);
		con.add(send);
		//���ð�ť
        reset=new JButton("����");
		reset.setBounds(290, 270, 60, 30);
		con.add(reset);
		
		this.setTitle("�������");
		this.setBounds(200, 250, 600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf=this;
		
		//��Ӽ�������
				send.addActionListener(this);
				reset.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==send){    
		//���ȷ����ť
			
			String id=jtbookwrite[1].getText();
			String typestr=booktype.getSelectedItem().toString();
			String[] inputstring=new String[10];
			boolean emptyequals=false;
			for(int i=1;i<inputstring.length;i++){
				inputstring[i]=jtbookwrite[i].getText();
				if(inputstring[i].equals("")){
					JOptionPane.showMessageDialog(null,"�������д����");
					return;
				}
			}
			if(id.equals("")){
				JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
				return;
			}
			if(Integer.parseInt(jtbookwrite[7].getText())<=0||
					Integer.parseInt(jtbookwrite[8].getText())<=0||
					Integer.parseInt(jtbookwrite[9].getText())<=0){
				JOptionPane.showMessageDialog(null,"���ֱ��������");
				return;
			}
			dao=new DAO();
		if(dao.IfBookIdExit(id)){
				JOptionPane.showMessageDialog(null,"ͼ�����Ѵ���,����������");
		}else {			
			
			if(dao.addbook(jtbookwrite[1].getText(),booktype.getSelectedItem(),jtbookwrite[2].getText(),
					jtbookwrite[3].getText(), jtbookwrite[4].getText(), jtbookwrite[5].getText(),
					jtbookwrite[6].getText(), jtbookwrite[7].getText(),
					Integer.parseInt(jtbookwrite[8].getText()), Integer.parseInt(jtbookwrite[9].getText())))
			{				
			JOptionPane.showMessageDialog(null, "��ӳɹ�");
			this.dispose();
			}
			else
			{JOptionPane.showMessageDialog(null, "���ʧ��");}
		}
		}else if (e.getSource()==reset) {
		//������ð�ť
			for(int i=1;i<jtbookwrite.length;i++){
				jtbookwrite[i].setText("");;
					
				}			
		}

	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new BookAddFrame();
	}

}
