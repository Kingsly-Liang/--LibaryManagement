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
	
	private JLabel[] jlbookinfo;  //显示需要填写的图书信息
	private JTextField[] jtbookwrite;  //填写图书信息
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
		setTitle("图书归还");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		add(jl);
				
		//显示信息
				String[] bookinfo={"图书编号:","图书名称:","逾期图书数量:","逾期原因:"};

				bookid=new Choice();
				bookname=new Choice();
				 // new Date()为获取当前系统时间
				
				//逾期图书信息需要从借阅表中通过比较归还时间和现在时间获取
				String sql="select* from borrow";
				dao=new DAO();
				ResultSet rs=dao.getRS(sql);		
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					nowtime=sdf.parse(sdf.format(new Date()));
					while(rs.next()) {
						
						Backtime= sdf.parse(rs.getString("backDate"));
						if(nowtime.getTime()>Backtime.getTime()) {    //在借阅表中查找逾期图书，然后在下拉列表显示
							//String ID=rs.getString("id");							
							bookid.add(rs.getString("book_id"));
							String sql2="select bookname from bookinfo where book_id='"+rs.getString("book_id")+"'";
							ResultSet rs2 =dao.getRS(sql2);
							while(rs2.next()) {
								bookname.add(rs2.getString("bookname"));//显示逾期图书名称
							}	
						}
					}
					} catch(ParseException e1){
						JOptionPane.showMessageDialog(null,"时间格式错误，正确为（年-月-日）");		
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,"sql错误）");	
					}
				bookid.setBounds(110, 113, 80, 40);		
				add(bookid);	
				bookname.setBounds(370, 113, 80, 40);		
				add(bookname);
				
				//实例化数组
				jlbookinfo=new JLabel[4];
				jtbookwrite=new JTextField[4];
						
					
				for(int i=0;i<bookinfo.length;i++)
				{
					int labelx;  //每个标签的x坐标
					int textX; //每个输入框的x坐标
					jlbookinfo[i]=new JLabel(bookinfo[i]);// 实例化每一个label
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
				
				 
				//保存按钮	
					send=new Button("保存");
					send.setBounds(210, 250, 60, 30);
					add(send);						
					
				//关闭按钮	
					close=new Button("关闭");
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
		
		//保存按钮事件
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//修改成功
				dao=new DAO();
		if(dao.OutDateModify(bookid.getSelectedItem(),bookname.getSelectedItem(),jtbookwrite[2].getText(),jtbookwrite[3].getText()))//参数是图书id和归还日期
		{
			JOptionPane.showMessageDialog(null,"逾期图书信息插入成功！");
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
			JOptionPane.showMessageDialog(null,"图书编号不能为空");
			return;
		}
		dao=new DAO();
		if( dao.IfBookBorrowExit(id)){  //判断图书借阅记录是否存在
			
			String sql="select * from borrow where book_id='"+idtxt.getText()+"'";
			dao=new DAO();
			ResultSet rs=dao.getRS(sql);		
			try {
				while(rs.next()) {
					//将查询到的信息显示出来	
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
			JOptionPane.showMessageDialog(null,"没有该借书记录");
		}
	}

	//清除信息
	public void clearAllText(){
		for(int i=0;i<jtbookwrite.length;i++){
			jtbookwrite[i].setText("");
		}
	}

	public static void main(String[] args){
		new BookOutDateFrame();
	}
}