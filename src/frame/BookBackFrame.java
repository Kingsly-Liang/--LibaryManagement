package frame;

//BookUpdate.java
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import dao.DAO;

public class BookBackFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel[] jlbookinfo;  //��ʾ��Ҫ��д��ͼ����Ϣ
	private JTextField[] jtbookwrite;  //��дͼ����Ϣ
	private TextField readerIdText;//��д����ID
	private DAO dao;
	private Button back;
	private Button close;
	private Button query;
	private TextField idtxt;
	 private Choice isBack;
	 Choice booktype=new Choice();
	
	public BookBackFrame(){
		setTitle("ͼ��黹");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		add(jl);
		
		Label idlb=new Label("�黹ͼ����:");
		idlb.setBounds(40, 70, 80, 30);
		add(idlb);
		
		idtxt=new TextField();
		idtxt.setBounds(120, 70, 100, 25);
		add(idtxt);
		
		Label readerId=new Label("����ID��:");
		readerId.setBounds(230, 70, 80, 30);
		add(readerId);
		readerIdText=new TextField();
		readerIdText.setBounds(310, 70, 100, 25);
		add(readerIdText);
		//��ʾ��Ϣ
				String[] bookinfo={"ͼ����:","����Ա���:","���߱��:","�Ƿ��ѻ�:","�������:","�黹���� :"};

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
					jtbookwrite[i].setEditable(false);
					add(jlbookinfo[i]);
					add(jtbookwrite[i]);
							}
				//ͼ���Ƿ�黹�������б���ʾ
				   isBack=new Choice();
				   isBack.add("true");
				   isBack.add("false");
				   isBack.setBounds(380, 150, 80, 40);		
				   add(isBack);
				   
				 //��ѯ��ť
					query=new Button("��ѯ");
					query.setBounds(450, 64, 60, 30);
				    add(query); 
				//���水ť	
					back=new Button("�黹");
					back.setBounds(210, 250, 60, 30);
					add(back);						
					
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
		//��ѯ�¼�
		query.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			querybtnActionPerformed();
					
			}
		});
		
		//�黹��ť�¼�
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//�޸ĳɹ�
				dao=new DAO();
		if(dao.BookBorrowDelete(idtxt.getText(),jtbookwrite[5].getText()))//������ͼ��id�͹黹����
		{
			JOptionPane.showMessageDialog(null,"ͼ��黹�ɹ���");
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
			
			String sql="select * from borrow where book_id='"+idtxt.getText()+"'and reader_id='"+readerIdText.getText()+"';";
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
		new BookBackFrame();
	}
}