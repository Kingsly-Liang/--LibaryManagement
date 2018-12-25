package frame;

//BookUpdate.java
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DAO;

public class BookModifyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel[] jlbookinfo;  //��ʾ��Ҫ��д��ͼ����Ϣ
	private JTextField[] jtbookwrite;  //��дͼ����Ϣ
	private DAO dao;
	private Button save;
	private Button close;
	private Button query;
	private TextField idtxt;
	 Choice booktype=new Choice();
	
	public BookModifyFrame(){
		setTitle("ͼ����Ϣ�޸�");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		add(jl);
		
		Label idlb=new Label("ͼ����:");
		idlb.setBounds(40, 70, 80, 30);
		add(idlb);
		idtxt=new TextField();
		idtxt.setBounds(120, 70, 150, 20);
		add(idtxt);
		
		//��ȡ�Ѿ����ڵ�ͼ�����
		String sql="select typeId from bookinfo";
		dao=new DAO();
		ResultSet rs=dao.getRS(sql);		
		try {
			while(rs.next()) {
				
				booktype.add(rs.getString("typeId"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		booktype.setBounds(370, 113, 80, 40);
		add(booktype);

		//��ʾ��Ϣ
		String[] bookinfo={"ͼ����","ͼ�������:","ͼ������:","ͼ������:","������:","�� �� ��:","����ʱ��:","�۸�:","�������:","�ɽ�����:"};
				
		//ʵ��������
		jlbookinfo=new JLabel[10];
		jtbookwrite=new JTextField[10];
				
			
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
		
		   jtbookwrite[1].setVisible(false);//�ڶ�������򲻿ɼ�
		   
		//��ѯ��ť
			query=new Button("��ѯ");
			query.setBounds(320, 70, 60, 30);
		    add(query);
		//���水ť	
			save=new Button("����");
			save.setBounds(180, 320, 60, 30);
			add(save);
		//�رհ�ť	
			close=new Button("�ر�");
			close.setBounds(290, 320, 60, 30);
			add(close);

		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dao=new DAO();
				dao.close();
				dispose();			
			}
		});
		
		query.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			querybtnActionPerformed();
						
			}
		});
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//���벻��С����
		if(Float.parseFloat(jtbookwrite[7].getText())<=0||
						Integer.parseInt(jtbookwrite[8].getText())<=0||
						Integer.parseInt(jtbookwrite[9].getText())<=0){
					JOptionPane.showMessageDialog(null,"�۸񣬿���������ɽ��������������");
					return;
		}
		//�޸ĳɹ�
				dao=new DAO();
		if(	dao.savebook(jtbookwrite[0].getText(), booktype.getSelectedItem(), jtbookwrite[2].getText(), jtbookwrite[3].getText(), jtbookwrite[4].getText(), 
					jtbookwrite[5].getText(), jtbookwrite[6].getText(), jtbookwrite[7].getText(), jtbookwrite[8].getText(), jtbookwrite[9].getText(), idtxt.getText()))
		{
			JOptionPane.showMessageDialog(null,"ͼ���޸ĳɹ���");
			clearAllText();
		}						
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public  void querybtnActionPerformed(){
		String id=idtxt.getText();
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
			return;
		}
		
		dao=new DAO();
		if( dao.IfBookIdExit(id)){
			
			Book book =BookSelectFrame.SelectBookByID(id);		
			String[] infor=book.getBookinformation();
			
			if(book!=null){
				
				booktype.select(book.getTypeId());
				
				for(int i=0;i<infor.length;i++){
					jtbookwrite[i].setText(infor[i]);
				}
			}		
		}else{	
			JOptionPane.showMessageDialog(null,"û�и�ͼ��");
		}
	}

	
	public void clearAllText(){
		for(int i=0;i<jtbookwrite.length;i++){
			jtbookwrite[i].setText("");
		}
	}

	public static void main(String[] args){
		new BookModifyFrame();
	}
}