//新书入库

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
	private JLabel[] jlbookinfo;  //显示需要填写的图书信息
	private JTextField[] jtbookwrite;  //填写图书信息
	private JButton send;  //提交
	private JButton reset;  //重置
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
		this.setLayout(null);//不设置布局
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		con.add(jl);
		
		//显示信息
		String[] bookinfo={"图书类别","图书编号:","图书名称:","图书作者:","翻译者:","出 版 社:","出版时间:","价格:","库存数量:","可借阅数:"};
		
		//实例化数组
		jlbookinfo=new JLabel[10];
		jtbookwrite=new JTextField[10];
		
		//获取已经存在的图书类别
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
			
			jlbookinfo[i].setBounds(labelx, 60+40*(i/2), 100, 30);
			
			jtbookwrite[i]=new JTextField();
			jtbookwrite[i].setBounds(textX, 60+40*(i/2), 150, 30);
			add(jlbookinfo[i]);
			add(jtbookwrite[i]);
			}
		jtbookwrite[0].setVisible(false);
		//发送按钮
		send=new JButton("确定");
		send.setBounds(180, 270, 60, 30);
		con.add(send);
		//重置按钮
        reset=new JButton("重置");
		reset.setBounds(290, 270, 60, 30);
		con.add(reset);
		
		this.setTitle("新书入库");
		this.setBounds(200, 250, 600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf=this;
		
		//添加监听内容
				send.addActionListener(this);
				reset.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==send){    
		//点击确定按钮
			
			String id=jtbookwrite[1].getText();
			String typestr=booktype.getSelectedItem().toString();
			String[] inputstring=new String[10];
			boolean emptyequals=false;
			for(int i=1;i<inputstring.length;i++){
				inputstring[i]=jtbookwrite[i].getText();
				if(inputstring[i].equals("")){
					JOptionPane.showMessageDialog(null,"请务必填写完整");
					return;
				}
			}
			if(id.equals("")){
				JOptionPane.showMessageDialog(null,"图书编号不能为空");
				return;
			}
			if(Integer.parseInt(jtbookwrite[7].getText())<=0||
					Integer.parseInt(jtbookwrite[8].getText())<=0||
					Integer.parseInt(jtbookwrite[9].getText())<=0){
				JOptionPane.showMessageDialog(null,"数字必须大于零");
				return;
			}
			dao=new DAO();
		if(dao.IfBookIdExit(id)){
				JOptionPane.showMessageDialog(null,"图书编号已存在,请重新输入");
		}else {			
			
			if(dao.addbook(jtbookwrite[1].getText(),booktype.getSelectedItem(),jtbookwrite[2].getText(),
					jtbookwrite[3].getText(), jtbookwrite[4].getText(), jtbookwrite[5].getText(),
					jtbookwrite[6].getText(), jtbookwrite[7].getText(),
					Integer.parseInt(jtbookwrite[8].getText()), Integer.parseInt(jtbookwrite[9].getText())))
			{				
			JOptionPane.showMessageDialog(null, "添加成功");
			this.dispose();
			}
			else
			{JOptionPane.showMessageDialog(null, "添加失败");}
		}
		}else if (e.getSource()==reset) {
		//点击重置按钮
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
