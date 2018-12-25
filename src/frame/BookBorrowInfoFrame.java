package frame;

/*
 * 借阅表的修改
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
	
	private JLabel[] jlbookinfo;  //显示需要填写的信息
	private JTextField[] jtbookwrite;  //填写信息
	private DAO dao;
	private Button send;
	private Button close;
    private Choice booktype;
    private Choice isBack;
    //private Connection conn = null;
	
	
	public BookBorrowInfoFrame(){
		setTitle("图书借阅信息");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 30, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		add(jl);				
		
		//获取已经存在的图书类别,用下拉菜单表示
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

		//显示信息
		String[] bookinfo={"图书编号","操作员编号:","读者编号:","是否已还:","借出日期:","归还日期 :"};
						
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
			
			add(jlbookinfo[i]);
			add(jtbookwrite[i]);
					}
		//图书是否归还用下拉列表显示
		   isBack=new Choice();
		  // isBack.add("true");
		   isBack.add("false");
		   isBack.setBounds(380, 150, 80, 40);		
		   add(isBack);
		   jtbookwrite[0].setVisible(false);//第一个输入框不可见
		   jtbookwrite[3].setVisible(false);//第4个输入框不可见
		
		//保存按钮	
			send=new Button("确定");
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
		
		//确定按钮事件
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//填写的信息不能有空
		for(int i=1;i<jtbookwrite.length;i++)					
		{
			if(i==3) {i=4;}
			String id=jtbookwrite[i].getText();
			if(id.equals("")){
			JOptionPane.showMessageDialog(null,"信息不能为空");
			return;
				}
		}
		//修改成功
		
		dao=new DAO();
		if(!dao.IsReaderIdCardUsable(jtbookwrite[2].getText()))//判断读者ID卡是否可用
		{
			return ;}
		else {
		if(dao.BookBorrow(booktype.getSelectedItem(),jtbookwrite[1].getText(),jtbookwrite[2].getText(),
				isBack.getSelectedItem(),jtbookwrite[4].getText()
				,jtbookwrite[5].getText()))
		  {
			
			
			JOptionPane.showMessageDialog(null,"借阅信息填写成功！");
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