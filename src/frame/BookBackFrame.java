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
	
	private JLabel[] jlbookinfo;  //显示需要填写的图书信息
	private JTextField[] jtbookwrite;  //填写图书信息
	private TextField readerIdText;//填写读者ID
	private DAO dao;
	private Button back;
	private Button close;
	private Button query;
	private TextField idtxt;
	 private Choice isBack;
	 Choice booktype=new Choice();
	
	public BookBackFrame(){
		setTitle("图书归还");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		add(jl);
		
		Label idlb=new Label("归还图书编号:");
		idlb.setBounds(40, 70, 80, 30);
		add(idlb);
		
		idtxt=new TextField();
		idtxt.setBounds(120, 70, 100, 25);
		add(idtxt);
		
		Label readerId=new Label("读者ID号:");
		readerId.setBounds(230, 70, 80, 30);
		add(readerId);
		readerIdText=new TextField();
		readerIdText.setBounds(310, 70, 100, 25);
		add(readerIdText);
		//显示信息
				String[] bookinfo={"图书编号:","操作员编号:","读者编号:","是否已还:","借出日期:","归还日期 :"};

				//实例化数组
				jlbookinfo=new JLabel[6];
				jtbookwrite=new JTextField[6];
						
					
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
					jtbookwrite[i].setEditable(false);
					add(jlbookinfo[i]);
					add(jtbookwrite[i]);
							}
				//图书是否归还用下拉列表显示
				   isBack=new Choice();
				   isBack.add("true");
				   isBack.add("false");
				   isBack.setBounds(380, 150, 80, 40);		
				   add(isBack);
				   
				 //查询按钮
					query=new Button("查询");
					query.setBounds(450, 64, 60, 30);
				    add(query); 
				//保存按钮	
					back=new Button("归还");
					back.setBounds(210, 250, 60, 30);
					add(back);						
					
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
		//查询事件
		query.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			querybtnActionPerformed();
					
			}
		});
		
		//归还按钮事件
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//修改成功
				dao=new DAO();
		if(dao.BookBorrowDelete(idtxt.getText(),jtbookwrite[5].getText()))//参数是图书id和归还日期
		{
			JOptionPane.showMessageDialog(null,"图书归还成功！");
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
			
			String sql="select * from borrow where book_id='"+idtxt.getText()+"'and reader_id='"+readerIdText.getText()+"';";
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
		new BookBackFrame();
	}
}