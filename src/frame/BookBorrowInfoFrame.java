package frame;

/*
 * ���ı���޸�
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import dao.DAO;

public class BookBorrowInfoFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel[] jlbookinfo;  //��ʾ��Ҫ��д����Ϣ
	private JTextField[] jtbookwrite;  //��д��Ϣ
	private DAO dao;
	private Button send;
	private Button close;
    private Choice booktype;
    private Choice isBack;
    //private Connection conn = null;
	
	
	public BookBorrowInfoFrame(){
		setTitle("ͼ�������Ϣ");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 30, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		add(jl);				
		
		//��ȡ�Ѿ����ڵ�ͼ�����,�������˵���ʾ
		booktype=new Choice();
		String sql="select book_id from bookinfo";
		dao=new DAO();
		ResultSet rs=dao.getRS(sql);		
		try {
			while(rs.next()) {
				
				booktype.add(rs.getString("book_id"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		booktype.setBounds(110, 113, 80, 40);		
		add(booktype);

		//��ʾ��Ϣ
		String[] bookinfo={"ͼ����","����Ա���:","���߱��:","�Ƿ��ѻ�:","�������:","�黹���� :"};
						
		//ʵ��������
		jlbookinfo=new JLabel[6];
		jtbookwrite=new JTextField[6];
				
			
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
					
			jlbookinfo[i].setBounds(labelx, 110+40*(i/2), 100, 30);					
			jtbookwrite[i]=new JTextField();
			jtbookwrite[i].setBounds(textX, 110+40*(i/2), 150, 30);
			
			add(jlbookinfo[i]);
			add(jtbookwrite[i]);
					}
		//ͼ���Ƿ�黹�������б���ʾ
		   isBack=new Choice();
		  // isBack.add("true");
		   isBack.add("false");
		   isBack.setBounds(380, 150, 80, 40);		
		   add(isBack);
		   jtbookwrite[0].setVisible(false);//��һ������򲻿ɼ�
		   jtbookwrite[3].setVisible(false);//��4������򲻿ɼ�
		
		//���水ť	
			send=new Button("ȷ��");
			send.setBounds(210, 250, 60, 30);
			add(send);						
			
		//�رհ�ť	
			close=new Button("�ر�");
			close.setBounds(310, 250, 60, 30);
			add(close);

		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dao=new DAO();
				dao.close();
				dispose();			
			}
		});
		
		//ȷ����ť�¼�
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//��д����Ϣ�����п�
		for(int i=1;i<jtbookwrite.length;i++)					
		{
			if(i==3) {i=4;}
			String id=jtbookwrite[i].getText();
			if(id.equals("")){
			JOptionPane.showMessageDialog(null,"��Ϣ����Ϊ��");
			return;
				}
		}
		//�޸ĳɹ�
		
		dao=new DAO();
		if(!dao.IsReaderIdCardUsable(jtbookwrite[2].getText()))//�ж϶���ID���Ƿ����
		{
			return ;}
		else {
		if(dao.BookBorrow(booktype.getSelectedItem(),jtbookwrite[1].getText(),jtbookwrite[2].getText(),
				isBack.getSelectedItem(),jtbookwrite[4].getText()
				,jtbookwrite[5].getText()))
		  {
			
			
			JOptionPane.showMessageDialog(null,"������Ϣ��д�ɹ���");
			clearAllText();
			
		  }
		}
		
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void clearAllText(){
		for(int i=0;i<jtbookwrite.length;i++){
			jtbookwrite[i].setText("");
		}
	}	

	public static void main(String[] args){
		new BookBorrowInfoFrame();
	}
}