//图书类别添加

package frame;

import javax.swing.*;

import dao.DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookTypeFrame extends JFrame implements ActionListener {

	@SuppressWarnings("unused")
	private JFrame jf;
	private JLabel jlbooktype;
	private JLabel jlbookdays;
	private JLabel jlfine;
	private JTextField jtbooktype;
	private JTextField jtbookdays;
	private JTextField jtfine;
	private JButton send;
	private JButton reset;
	private Container con;
	private DAO dao;
	public BookTypeFrame(){
		super();
		jf=new JFrame();
		con=this.getContentPane();
		this.setLayout(null);//不设置布局
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 25, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		con.add(jl);
		
		//图书类别添加
		//标签
		jlbooktype=new JLabel("图书类别:");
		jlbooktype.setBounds(100, 100, 120, 30);
		con.add(jlbooktype);
		//文本
		jtbooktype=new JTextField();
		jtbooktype.setBounds(220, 100, 200, 30);
		con.add(jtbooktype);
		
		
		//可借阅天数
		//标签
		jlbookdays=new JLabel("可借阅天数:");
		jlbookdays.setBounds(100, 140, 120, 30);
		con.add(jlbookdays);
		//文本
		jtbookdays=new JTextField();
		jtbookdays.setBounds(220, 140, 200, 30);
		con.add(jtbookdays);
		
		//逾期后每日罚款金额
		jlfine=new JLabel("每日罚款金额:");
		jlfine.setBounds(100, 180, 120, 30);
		con.add(jlfine);
		
		//文本
		jtfine=new JTextField();
		jtfine.setBounds(220, 180, 200, 30);
		con.add(jtfine);
		
		//发送按钮
		send=new JButton("确定");
		send.setBounds(180, 230, 60, 30);
		con.add(send);
		//重置按钮
        reset=new JButton("重置");
		reset.setBounds(280, 230, 60, 30);
		con.add(reset);
		
		this.setTitle("图书类别");
		this.setBounds(150, 250, 600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf=this;
		
		//添加监听内容
		send.addActionListener(this);
		reset.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == send){
			dao=new DAO();
			if(jtbooktype.getText().equals("")||jtbooktype.getText().equals("")||jtfine.getText().equals(""))//没有输入内容
			{JOptionPane.showMessageDialog(null, "输入不能有空！请重新输入");}
			else {
			     if(dao.addtype(jtbooktype.getText(), Integer.parseInt(jtbookdays.getText()), Double.parseDouble(jtfine.getText()))) {				
			     JOptionPane.showMessageDialog(null, "添加成功");
			     this.dispose();
			     }
			    else {
			 	JOptionPane.showMessageDialog(null, "你输入的信息有错误或已有该类别，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);
			      }
			}
		}else if(e.getSource() == reset){
			jtbooktype.setText("");
			jtbookdays.setText("");
			jtfine.setText("");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new BookTypeFrame();
	}

}
