package frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import dao.DAO;

public class BookOutDateFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel[] jlbookinfo;  //��ʾ��Ҫ��д��ͼ����Ϣ
	private JTextField[] jtbookwrite;  //��дͼ����Ϣ
	private DAO dao;
	private Button send;
	private Button close;
	private Button query;
	private TextField idtxt;
	private Choice isBack;
	private Date nowtime;
	private Date Backtime;
	private Choice bookid;
	private Choice bookname;
	
	public BookOutDateFrame(){
		setTitle("ͼ��黹");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		add(jl);
				
		//��ʾ��Ϣ
				String[] bookinfo={"ͼ����:","ͼ������:","����ͼ������:","����ԭ��:"};

				bookid=new Choice();
				bookname=new Choice();
				 // new Date()Ϊ��ȡ��ǰϵͳʱ��
				
				//����ͼ����Ϣ��Ҫ�ӽ��ı���ͨ���ȽϹ黹ʱ�������ʱ���ȡ
				String sql="select* from borrow";
				dao=new DAO();
				ResultSet rs=dao.getRS(sql);		
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
					nowtime=sdf.parse(sdf.format(new Date()));
					while(rs.next()) {
						
						Backtime= sdf.parse(rs.getString("backDate"));
						if(nowtime.getTime()>Backtime.getTime()) {    //�ڽ��ı��в�������ͼ�飬Ȼ���������б���ʾ
							//String ID=rs.getString("id");							
							bookid.add(rs.getString("book_id"));
							String sql2="select bookname from bookinfo where book_id='"+rs.getString("book_id")+"'";
							ResultSet rs2 =dao.getRS(sql2);
							while(rs2.next()) {
								bookname.add(rs2.getString("bookname"));//��ʾ����ͼ������
							}	
						}
					}
					} catch(ParseException e1){
						JOptionPane.showMessageDialog(null,"ʱ���ʽ������ȷΪ����-��-�գ�");		
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,"sql����");	
					}
				bookid.setBounds(110, 113, 80, 40);		
				add(bookid);	
				bookname.setBounds(370, 113, 80, 40);		
				add(bookname);
				
				//ʵ��������
				jlbookinfo=new JLabel[4];
				jtbookwrite=new JTextField[4];
						
					
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
				
				jtbookwrite[0].setVisible(false);
				jtbookwrite[1].setVisible(false);
				
				 
				//���水ť	
					send=new Button("����");
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
				

		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dao=new DAO();
				dao.close();
				dispose();			
			}
		});		
		
		//���水ť�¼�
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//�޸ĳɹ�
				dao=new DAO();
		if(dao.OutDateModify(bookid.getSelectedItem(),bookname.getSelectedItem(),jtbookwrite[2].getText(),jtbookwrite[3].getText()))//������ͼ��id�͹黹����
		{
			JOptionPane.showMessageDialog(null,"����ͼ����Ϣ����ɹ���");
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
		if( dao.IfBookBorrowExit(id)){  //�ж�ͼ����ļ�¼�Ƿ����
			
			String sql="select * from borrow where book_id='"+idtxt.getText()+"'";
			dao=new DAO();
			ResultSet rs=dao.getRS(sql);		
			try {
				while(rs.next()) {
					//����ѯ������Ϣ��ʾ����	
					jtbookwrite[0].setText((rs.getString("book_id")));
					jtbookwrite[1].setText((rs.getString("operatorId")));
					jtbookwrite[2].setText((rs.getString("reader_id")));
					jtbookwrite[3].setText((rs.getString("isback")));
					jtbookwrite[4].setText((rs.getString("borrowDate")));
					jtbookwrite[5].setText((rs.getString("backDate")));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
									
		}else{	
			JOptionPane.showMessageDialog(null,"û�иý����¼");
		}
	}

	//�����Ϣ
	public void clearAllText(){
		for(int i=0;i<jtbookwrite.length;i++){
			jtbookwrite[i].setText("");
		}
	}

	public static void main(String[] args){
		new BookOutDateFrame();
	}
}